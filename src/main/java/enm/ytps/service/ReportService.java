package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.ReportJob;
import com.google.api.ads.admanager.axis.v202005.ReportJobStatus;
import com.google.api.ads.admanager.axis.v202005.ReportQuery;
import com.google.api.ads.admanager.axis.v202005.SavedQuery;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import enm.ytps.dto.report.Report;
import enm.ytps.repository.ReportJobRepository;
import enm.ytps.service.ads.GoogleAdsReportService;
import enm.ytps.util.ReportUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ReportService {
    
    private GoogleAdsReportService googleAdsReportService;
    private ReportJobRepository reportJobRepository;
    private ReportUtil reportUtil;
    
    public ReportService(GoogleAdsReportService googleAdsReportService,
                         ReportJobRepository reportJobRepository,
                         ReportUtil reportUtil) {
        this.googleAdsReportService = googleAdsReportService;
        this.reportJobRepository = reportJobRepository;
        this.reportUtil = reportUtil;
    }

    /**
     * 리포트 쿼리 리스트
     * @return
     */
    public SavedQuery[] getSavedQueryList() {
        return googleAdsReportService.getSavedQueryList();
    }
    
    public ReportJob runReport(Long savedQueryId) {
        ReportJob rj = googleAdsReportService.runBySavedQueryId(savedQueryId);
        enm.ytps.model.ReportJob entity = enm.ytps.model.ReportJob
                .builder().jobId(rj.getId()).build();
        reportJobRepository.save(entity);
        return rj;
    }
    
    public ReportJob runReport(ReportQuery reportQuery) {
        ReportJob rj = googleAdsReportService.runByReportQuery(reportQuery);
        enm.ytps.model.ReportJob entity = enm.ytps.model.ReportJob.builder()
                .jobId(rj.getId()).build();
        reportJobRepository.save(entity);
        return rj;
    }

    /**
     * 리포트잡 실행 상태 조회
     * @param jobId
     * @return
     */
    public ReportJobStatus getReportJobStatus(Long jobId) {
        return googleAdsReportService.getReportJobStatus(jobId);
    }

    /**
     * 리포트 CSV 다운로드
     * @param jobId
     * @param isCompression
     * @param suffix
     * @return
     */
    public File reportDownloadCsv(Long jobId, boolean isCompression, String suffix) {
        File file = null;
        ByteSource bs = googleAdsReportService.getReportByteSource(jobId, isCompression);
        
        if (bs == null) {
            return null;
        }
        try {
            file = File.createTempFile(String.valueOf(jobId), suffix);
            bs.copyTo(Files.asByteSink(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    
    public File reportDownloadCsv(Long jobId) {
        return reportDownloadCsv(jobId, false, ".csv");
    }
    
    /**
     * 리포트 GZ 압축 csv 다운로드
     * @param jobId
     * @return
     */
    public File reportDownloadCompressionGzCsv(Long jobId) {
        return reportDownloadCsv(jobId, true, ".csv.gz");
    }

    /**
     * 리포트 object parsing
     * @return
     */
    
    public Report getReportData(Long jobId) {
        return reportUtil.parseByteSource(googleAdsReportService.getReportByteSource(jobId));
    }
}
