package enm.ytps.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.ads.admanager.axis.v202005.Order;
import com.google.api.ads.admanager.axis.v202005.OrderStatus;
import enm.ytps.dto.order.OrderDto;
import enm.ytps.dto.order.mixIn.MixInOrderStatus;
import enm.ytps.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 광고주문 상세 정보 조회
     * @param orderId order primary id
     * @return
     */
    @RequestMapping(value = "/byOrderId/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    /**
     * 광고주문 리스트 조회
     * @param page current page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getOrderList(@RequestParam("page") int page) {
        return new ResponseEntity<>(orderService.getOrderList(page), HttpStatus.OK);
    }

    /**
     * 광고주문 생성
     * @param request Order Json String
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody String request) {
        return new ResponseEntity<>(orderService.create(request), HttpStatus.OK);
    }
    
    /**
     * 광고주문 수정
     * @param request Order Json String
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseEntity updateOrder() {
        Order order = new Order();
        order.setId(2777028391L);
        order.setAdvertiserId(4944918217L);
//        order.setAdvertiserId(4864389078L);
        
        order.setTraffickerId(245231263L);
        order.setName("테스트 광고주문 변경");
        return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
    }
    
}
