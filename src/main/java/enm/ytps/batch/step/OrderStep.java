package enm.ytps.batch.step;


import com.google.api.ads.admanager.axis.v202005.Order;
import enm.ytps.batch.reader.OrderRemoteItemReader;
import enm.ytps.model.AmOrder;
import enm.ytps.repository.OrderRepository;
import enm.ytps.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class OrderStep {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final OrderRemoteItemReader orderRemoteItemReader;
    private final OrderRepository orderRepository;
    
    private final int chunkSize = 50;
    
    @Bean
    @JobScope
    public Step orderSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amOrderSaveStep")
                .<Order, AmOrder> chunk(chunkSize)
                .reader(orderRemoteItemReader)
                .processor(orderItemProcessor())
                .writer(orderItemWriter())
                .build();
    }
    
    @Bean
    @StepScope
    public ItemProcessor<Order, AmOrder> orderItemProcessor() {
        return order -> {

            /* Google.DateTime-> LocalDateTime StartDateTime */
            LocalDateTime startDateTime = DateUtil.convertGoogleDateTimeToLocalDateTime(order.getStartDateTime());
            
            /* Google.DateTime-> LocalDateTime EndDateTime */
            LocalDateTime endDateTime = DateUtil.convertGoogleDateTimeToLocalDateTime(order.getEndDateTime());

            /* Google.DateTime-> LocalDateTime LastModifiedDateTime */
            LocalDateTime lastModifiedDateTime = DateUtil.convertGoogleDateTimeToLocalDateTime(order.getLastModifiedDateTime());
            
            return AmOrder.builder()
                    .id(order.getId())
                    .name(order.getName())
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .unlimitedEndDateTime(order.getUnlimitedEndDateTime())
                    .status(order.getStatus().getValue())
                    .isArchived(order.getIsArchived())
                    .notes(order.getNotes())
                    .externalOrderId(order.getExternalOrderId())
                    .poNumber(order.getPoNumber())
                    .currencyCode(order.getCurrencyCode())
                    .advertiserId(order.getAdvertiserId())
                    .agencyId(order.getAgencyId())
                    .creatorId(order.getCreatorId())
                    .traffickerId(order.getTraffickerId())
                    .salespersonId(order.getSalespersonId())
                    .totalImpressionsDelivered(order.getTotalImpressionsDelivered())
                    .totalClicksDelivered(order.getTotalClicksDelivered())
                    .totalViewableImpressionsDelivered(order.getTotalViewableImpressionsDelivered())
                    .totalBudget((long) (order.getTotalBudget().getMicroAmount() * 0.000001))
                    .lastModifiedByApp(order.getLastModifiedByApp())
                    .isProgrammatic(order.getIsProgrammatic())
                    .lastModifiedDateTime(lastModifiedDateTime)
                    .build();
        };
    }

    @Bean
    @StepScope
    public ItemWriter<AmOrder> orderItemWriter() {
        return (orgList) -> {
            for (AmOrder order : orgList) {
                orderRepository.save(order);
            }
        };
    }
}
