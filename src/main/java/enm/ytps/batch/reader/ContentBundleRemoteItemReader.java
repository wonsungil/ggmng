package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.ContentBundle;
import enm.ytps.service.ContentBundleService;
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
public class ContentBundleRemoteItemReader implements ItemReader<ContentBundle> {

    private final ContentBundleService contentBundleService;
    private List<ContentBundle> contentBundleList = new LinkedList<>();

    private int page = 1;
    private int perPage = 100;
    private int totalCount = 0;

    @Override
    public ContentBundle read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        if (contentBundleList.isEmpty()) {
            ContentBundle[] contentBundles = contentBundleService.getContentBundleList(page, perPage);
            
            if (contentBundles == null) {
                return null;
            }
            
            contentBundleList.addAll(Arrays.asList(contentBundles));
            page++;
        }
        
        if (contentBundleList.isEmpty()) {
            return null;
        }
        totalCount++;
        return contentBundleList.remove(0);
    }
}
