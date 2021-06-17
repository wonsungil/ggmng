package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.Order;
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
public class OrderRemoteItemReader implements ItemReader<Order> {
    
    private final OrderService orderService;
    private List<Order> orderList = new LinkedList<>();
    private int page=1;
    private int perPage=100;
    private int totalCount = 0;
    
    @Override
    public Order read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        if (orderList.isEmpty()) {
            log.info("[Order][Reader][Page="+page+"]_[START]_[READ_TOTAL_COUNT="+totalCount+"]");
            
            Order[] orders = orderService.getOrderListForBatch(page, perPage);
            if (orders == null) {
                return null;
            }
            
            orderList.addAll(Arrays.asList(orders));
            page++;
        }

        if (orderList.isEmpty()) {
            return null;
        }
        totalCount++;
        return orderList.remove(0);
    }
}
