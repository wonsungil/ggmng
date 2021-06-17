package enm.ytps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enm.ytps.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/view/order")
@Controller
public class OrderViewController {

    private OrderService orderService;
    public OrderViewController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String List(Model model) {
        model.addAttribute("orders", orderService.getOrderList(1));
        return "/order/list";
    }
    
    @RequestMapping(value = "{orderId}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable Long orderId) throws JsonProcessingException {
        model.addAttribute("order", new ObjectMapper().writeValueAsString(orderService.getOrderById(orderId)));
        return "/order/detail";
    }
}
