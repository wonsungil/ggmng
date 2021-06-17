package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.CmsMetadataValue;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CmsMetadataValueRemoteItemReader implements ItemReader<CmsMetadataValue> {
    
    @Override
    public CmsMetadataValue read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
