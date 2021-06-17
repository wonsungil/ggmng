package enm.ytps.controller.api;

import com.google.api.ads.admanager.axis.v202005.ReportQuery;
import enm.ytps.service.ReportQueryService;
import enm.ytps.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/report")
@Controller
public class ReportController {
    
    private ReportService reportService;
    private ReportQueryService reportQueryService;
    
    public ReportController(ReportService reportService, ReportQueryService reportQueryService) {
        this.reportService = reportService;
        this.reportQueryService = reportQueryService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(reportService.getSavedQueryList(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/run/{savedQueryId}", method = RequestMethod.GET)
    public ResponseEntity run(@PathVariable Long savedQueryId) {
        return new ResponseEntity<>(reportService.runReport(savedQueryId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/status/{jobId}", method = RequestMethod.GET)
    public ResponseEntity status(@PathVariable Long jobId) {
        return new ResponseEntity<>(reportService.getReportJobStatus(jobId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/download/{jobId}", method = RequestMethod.GET)
    public ResponseEntity download(@PathVariable Long jobId) {
        return new ResponseEntity<>(reportService.reportDownloadCsv(jobId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/parse/{jobId}", method = RequestMethod.GET)
    public ResponseEntity parse(@PathVariable Long jobId) {
        return new ResponseEntity<>(reportService.getReportData(jobId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/run/dailyQuery", method = RequestMethod.GET)
    public ResponseEntity dailyQuery() {
        long[] orderIds = new long[] {2733931864L,2724845664L, 2731642223L};
        ReportQuery dailyQuery = reportQueryService.generateDailyReportQuery(orderIds);
        return new ResponseEntity<>(reportService.runReport(dailyQuery), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/run/defaultQuery", method = RequestMethod.GET)
    public ResponseEntity defaultQuery() {
        ReportQuery reportQuery = reportQueryService.generateDefaultReportQuery();
        return new ResponseEntity<>(reportService.runReport(reportQuery), HttpStatus.OK);
    }
}
