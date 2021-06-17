package enm.ytps.dto.report;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Report {
    private List<ReportRow> reportRows;
    
    @Builder
    public Report(List<ReportRow> reportRows) {
        this.reportRows = reportRows;
    }
}
