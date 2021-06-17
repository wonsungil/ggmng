package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.LineItem;
import com.google.api.ads.admanager.axis.v202005.Order;
import enm.ytps.service.LineItemService;
import enm.ytps.service.OrderService;
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
public class LineItemRemoteItemReader implements ItemReader<LineItem> {
    private final LineItemService lineItemService;
    private List<LineItem> lineItemList = new LinkedList<>();
    private int page=1;
    private int perPage=100;
    private int totalCount = 0;

    @Override
    public LineItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (lineItemList.isEmpty()) {
            LineItem[] lineItems = lineItemService.getLineItemList(page, perPage);
            if (lineItems == null) {
                return null;
            }

            lineItemList.addAll(Arrays.asList(lineItems));
            page++;
        }

        if (lineItemList.isEmpty()) {
            return null;
        }
        totalCount++;
        return lineItemList.remove(0);
    }
}
