package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.User;
import enm.ytps.service.UserService;
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
public class UserRemoteItemReader implements ItemReader<User> {

    private final UserService userService;
    private List<User> userList = new LinkedList<>(); 
    
    private int page = 1;
    private int perPage = 100;
    private int totalCount = 0;
    
    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        if(userList.isEmpty()) {
            User[] users = userService.getUserList(page, perPage);
            
            if (users == null) {
                return null;
            }
            
            userList.addAll(Arrays.asList(users));
            page++;
        }
        
        if (userList.isEmpty()) {
            return null;
        }
        
        totalCount++;
        return userList.remove(0);
    }
}
