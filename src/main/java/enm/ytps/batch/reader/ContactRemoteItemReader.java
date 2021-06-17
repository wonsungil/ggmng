package enm.ytps.batch.reader;


import com.google.api.ads.admanager.axis.v202005.Contact;
import enm.ytps.service.ContactService;
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
public class ContactRemoteItemReader implements ItemReader<Contact> {
    
    private final ContactService contactService;
    private List<Contact> contactList = new LinkedList<>();
    
    private int page = 1;
    private int perPage = 100;
    private int totalCount = 0;
    
    @Override
    public Contact read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (contactList.isEmpty()) {
            log.info("[Order][Reader][Page="+page+"]_[START]_[READ_TOTAL_COUNT="+totalCount+"]");
            
            Contact[] contacts = contactService.getContactList(page, perPage);
            if (contacts == null) {
                return null;
            }

            contactList.addAll(Arrays.asList(contacts));
            page++;
        }

        if (contactList.isEmpty()) {
            return null;
        }
        
        totalCount++;
        return contactList.remove(0);
    }
}
