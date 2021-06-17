package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.ReportDownloader;
import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;

@Service
public class GoogleAdsReportService {
    
    private ReportServiceInterface amReportService;
    
    public GoogleAdsReportService(ReportServiceInterface amReportService) {
        this.amReportService = amReportService;
    }
    
    public SavedQuery[] getSavedQueryList() {
        SavedQueryPage page = null;

        try {
            page = amReportService.getSavedQueriesByStatement(new StatementBuilder()
                    .limit(10)
                    .orderBy("id DESC")
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        if (page == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return page.getResults();
    }
    
    public SavedQuery getSavedQueryById(Long savedQueryId) {
        SavedQueryPage page = null;
        
        try {
            page = amReportService.getSavedQueriesByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", savedQueryId)
                    .limit(1)
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    ,  ErrorMessages.CONNECTION_ERROR.getMessage()
                    ,  e.getCause());
        }

        if (page == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return Iterables.getOnlyElement(Arrays.asList(page.getResults()));
    }

    public ReportJob runByReportQuery(ReportQuery reportQuery) {
        ReportJob reportJob = new ReportJob();
        reportJob.setReportQuery(reportQuery);
        try {
            reportJob = amReportService.runReportJob(reportJob);
            
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }

        if (reportJob == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return reportJob;
    }   
    
    public ReportJob runBySavedQuery(SavedQuery savedQuery) {
        return runByReportQuery(savedQuery.getReportQuery());    
    }
    
    public ReportJob runBySavedQueryId(Long savedQueryId) {
        SavedQuery sq = getSavedQueryById(savedQueryId);
        return runByReportQuery(sq.getReportQuery());
    }

    public ReportJobStatus getReportJobStatus(Long jobId) {
        ReportJobStatus reportJobStatus = null;
        try {
            reportJobStatus = amReportService.getReportJobStatus(jobId);
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }
        
        if (reportJobStatus == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return reportJobStatus; 
    }
    
    public ByteSource getReportByteSource(Long jobId, boolean isCompression) {
        
        ReportJobStatus status = getReportJobStatus(jobId);
        ByteSource bs = null;
        
        if (status != ReportJobStatus.COMPLETED) {
            return null;
        }
        
        try {
            ReportDownloader reportDownloader = new ReportDownloader(amReportService, jobId);
            reportDownloader.waitForReportReady();
            ReportDownloadOptions options = new ReportDownloadOptions();
            options.setExportFormat(ExportFormat.CSV_DUMP);
            options.setUseGzipCompression(isCompression);
            URL url = reportDownloader.getDownloadUrl(options);
            bs = Resources.asByteSource(url);
        } catch (RemoteException  e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        } catch (InterruptedException e) {
            
        } catch (MalformedURLException e) {
            
        }

        return bs;
    }
    
    public ByteSource getReportByteSource(Long jobId) {
        return getReportByteSource(jobId, false);
    }
}
