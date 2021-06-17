package enm.ytps.dto.report;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ReportRow {
    private List<ReportCell> reportCells;
    
    @Builder
    public ReportRow(List<ReportCell> reportCells) {
        this.reportCells = reportCells;
    }
}
