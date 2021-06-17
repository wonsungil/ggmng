package enm.ytps.controller;

import com.google.api.ads.admanager.axis.v202005.DayPartTargeting;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    
    private MessageSource dimensionMessageSource;
    private MessageSource columnMessageSource;
    
    public TestController(MessageSource dimensionMessageSource, MessageSource columnMessageSource) {
        this.dimensionMessageSource = dimensionMessageSource;
        this.columnMessageSource = columnMessageSource;
    }
    
    @RequestMapping("test")
    public @ResponseBody
    String test() {
        System.out.println(dimensionMessageSource.getMessage("test", null, null));
        System.out.println(columnMessageSource.getMessage("test", null, null));
        return "test";
    }
    
}
