package enm.ytps.util;

import com.google.common.io.ByteSource;
import enm.ytps.dto.report.Report;
import enm.ytps.dto.report.ReportCell;
import enm.ytps.dto.report.ReportRow;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ReportUtil {

    private MessageSource dimensionMessageSource;
    private MessageSource columnMessageSource;
    
    public ReportUtil(MessageSource dimensionMessageSource, MessageSource columnMessageSource) {
        this.dimensionMessageSource = dimensionMessageSource;
        this.columnMessageSource = columnMessageSource;
    }
    
    private final String DIMENSION = "Dimension";
    private final String COLUMN = "Column";

    /**
     * Report ByteStream -> Report Object
     * @param byteSource
     * @return
     */
    public Report parseByteSource(ByteSource byteSource) {
        BufferedInputStream bf = null;
        BufferedReader reader = null;
        Report report = Report.builder().build();
        
        try {
            bf = (BufferedInputStream) byteSource.openBufferedStream();
            reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));

            List<ReportCell> headers = new ArrayList<>();
            List<ReportRow> rowList = new ArrayList<ReportRow>();
            
            String line;
            
            // Empty Stream
            if((line = reader.readLine()) == null) {
                return null;
            }

            // Header parse
            String korValue = null;
            for (String strCell : line.split(",")) {
                String[] cellSplit = strCell.split("\\.");
                if (DIMENSION.equals(cellSplit[0])) {
                    korValue = dimensionMessageSource.getMessage(cellSplit[1], null, Locale.KOREA);
                } else if (COLUMN.equals(cellSplit[0])) {
                    korValue = columnMessageSource.getMessage(cellSplit[1], null, Locale.KOREA);
                }
                ReportCell cell = ReportCell.builder().orgKey(strCell).key(cellSplit[1]).korKey(korValue).build();
                headers.add(cell);
            }
            
            // Data parse
            while ((line = reader.readLine()) != null) {
                List<ReportCell> dataCells = new ArrayList<>();
                String[] dataSplit = line.split(",");
                for (int i=0; i<dataSplit.length; i++) {
                    ReportCell temp = (ReportCell) headers.get(i).clone();
                    temp.setValue(dataSplit[i]);
                    dataCells.add(temp);
                }
                ReportRow reportRow = ReportRow.builder().reportCells(dataCells).build();
                rowList.add(reportRow);
            }
            report.setReportRows(rowList);
            return report;
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { if (bf != null) { bf.close(); }} catch (IOException e) { e.printStackTrace(); }
            try { if (reader != null) { reader.close(); }} catch (IOException e) { e.printStackTrace(); }
        }
        return null;
    }
}
