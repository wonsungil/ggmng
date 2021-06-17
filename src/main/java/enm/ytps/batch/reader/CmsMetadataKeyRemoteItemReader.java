package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.CmsMetadataKey;
import enm.ytps.service.CmsMetaDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@StepScope
public class CmsMetadataKeyRemoteItemReader implements ItemReader<CmsMetadataKey> {
    
    private final CmsMetaDataService cmsMetaDataService;
    private List<CmsMetadataKey> cmsMetadataKeyList = new LinkedList<>();
    
    private int page = 1;
    private int perPage = 50;
    private int totalCount = 0;
    
    @Override
    public CmsMetadataKey read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    
        if (cmsMetadataKeyList.isEmpty()) {
            CmsMetadataKey[] cmsMetadataKeys = cmsMetaDataService.getKeyList(page, perPage);
            if (cmsMetadataKeys == null) {
                 return null;
            }
            cmsMetadataKeyList.addAll(Arrays.asList(cmsMetadataKeys));
            page++;
        }
        
        if (cmsMetadataKeyList.isEmpty()) {
            return null;
        }
        return cmsMetadataKeyList.remove(0);
    }
}
