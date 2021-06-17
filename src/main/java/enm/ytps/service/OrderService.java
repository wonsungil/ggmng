package enm.ytps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.dto.lineItem.LineItemCreativeDto;
import enm.ytps.dto.order.OrderDto;
import enm.ytps.dto.order.OrderListDto;
import enm.ytps.dto.order.mixIn.MixInOrderStatus;
import enm.ytps.service.ads.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private GoogleAdsOrderService googleAdsOrderService;
    private GoogleAdsLineItemService googleAdsLineItemService;
    private GoogleAdsLineItemCreativeAssociationService googleAdsLineItemCreativeAssociationService;
    private GoogleAdsCreativeService googleAdsCreativeService;
    private GoogleAdsCompanyService googleAdsCompanyService;
    
    public OrderService(
            GoogleAdsOrderService googleAdsOrderService
            , GoogleAdsLineItemService googleAdsLineItemService
            , GoogleAdsLineItemCreativeAssociationService googleAdsLineItemCreativeAssociationService
            , GoogleAdsCreativeService googleAdsCreativeService
            , GoogleAdsCompanyService googleAdsCompanyService
    ) {
        this.googleAdsOrderService = googleAdsOrderService;
        this.googleAdsLineItemService = googleAdsLineItemService;
        this.googleAdsLineItemCreativeAssociationService = googleAdsLineItemCreativeAssociationService;
        this.googleAdsCreativeService = googleAdsCreativeService;
        this.googleAdsCompanyService = googleAdsCompanyService;
    }
    
    public OrderDto getOrderById(Long orderId) {
        OrderDto orderDto = new OrderDto();
        Order order = googleAdsOrderService.getOrderById(orderId);
        List<LineItemCreativeDto> licaList = new ArrayList<>();
        
        if( order == null ) {
            return null;
        }

        LineItem[] lineItems = googleAdsLineItemService.getLineItemsByOrderId(order.getId());

        if( lineItems == null ) {
            return null;
        }

        for (LineItem li : lineItems) {
            LineItemCreativeAssociation[] licas = googleAdsLineItemCreativeAssociationService
                    .getLineItemCreativeAssociationsByLineItemId(li.getId());
            if (licas != null) {
                for (LineItemCreativeAssociation lica: licas) {
                    LineItemCreativeDto lineItemCreativeDto = new LineItemCreativeDto();
                    lineItemCreativeDto.setLineItem(li);
                    lineItemCreativeDto.setCreative(googleAdsCreativeService.getCreativeById(lica.getCreativeId()));
                    lineItemCreativeDto.setLineItemCreativeAssociation(lica);
                    licaList.add(lineItemCreativeDto);
                }   
            }
        }

        orderDto.setOrder(order);
        orderDto.setLineItemCreativeDtoArrayList(licaList);
        
        return orderDto;
    }
    
    public List<OrderListDto> getOrderList(int page, int perPage) {

        List<OrderListDto> orderList = new ArrayList<>();
        String sortBy = "startDateTime Desc";
        Order[] orders = googleAdsOrderService.getOrders(sortBy, page, perPage);

        if (orders == null) {
            return null;
        }

        OrderListDto orderWithAssociative = null;
        Company[] companies = googleAdsCompanyService.getAdvertisersWhereInIdsByOrders(orders);

        for (Order order : orders) {
            orderWithAssociative = new OrderListDto();
            orderWithAssociative.setOrder(order);

            orderWithAssociative.setAdvertiser(
                    Arrays.stream(companies)
                            .filter( c -> c.getId().equals(order.getAdvertiserId()))
                            .findAny()
                            .orElse(null));
            orderList.add(orderWithAssociative);
        }
        return orderList;
    }

    public List<OrderListDto> getOrderList(int page) {
        return getOrderList(page, 20);
    }

    public List<OrderListDto> getOrderList() {
        return getOrderList(1, 20);
    }

    private Order toOrder(String jsonOrder) {
        Order order = null;
        try {
            order = new ObjectMapper()
                    .addMixIn(OrderStatus.class, MixInOrderStatus.class)
                    .readValue(jsonOrder, Order.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return order;
    }
    
    public Order create(String jsonOrder) {
        return googleAdsOrderService.createOrder(toOrder(jsonOrder));
    }
    
    public Order[] getOrderListForBatch(int page, int perPage) {
        String sortBy = "lastModifiedDateTime ASC";
        return googleAdsOrderService.getOrders(sortBy, page, perPage);
    }
    
    public Order updateOrder(Order order) {
        return googleAdsOrderService.updateOrder(order);
    }
}
