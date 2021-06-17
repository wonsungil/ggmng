package enm.ytps.dto.report;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ReportCell implements Cloneable{
    private String key;
    private String orgKey;
    private String korKey;
    private String value;
    
    @Builder
    public ReportCell(String key, String orgKey, String korKey, String value) {
        this.key = key;
        this.orgKey = orgKey;
        this.korKey = korKey;
        this.value = value;
    }
    
    @Override
    public Object clone() {
        ReportCell column = null;
        try {
            column = (ReportCell) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return column;
    }
}
