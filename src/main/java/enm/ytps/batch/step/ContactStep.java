package enm.ytps.batch.step;

import com.google.api.ads.admanager.axis.v202005.Contact;
import enm.ytps.batch.reader.ContactRemoteItemReader;
import enm.ytps.model.AmContact;
import enm.ytps.repository.ContactRepository;
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

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ContactStep {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final ContactRemoteItemReader contactRemoteItemReader;
    private final ContactRepository contactRepository;
    
    private final int chunkSize = 50;

    @Bean
    @JobScope
    public Step contactSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amContactInsertStep")
                .<Contact, AmContact> chunk(chunkSize)
                .reader(contactRemoteItemReader)
                .processor(contactItemProcessor())
                .writer(contactItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Contact, AmContact> contactItemProcessor() {
        return contact-> AmContact.builder()
                .id(contact.getId())
                .name(contact.getName())
                .companyId(contact.getCompanyId())
                .status(contact.getStatus().getValue())
                .address(contact.getAddress())
                .cellPhone(contact.getCellPhone())
                .comment(contact.getComment())
                .email(contact.getEmail())
                .faxPhone(contact.getFaxPhone())
                .title(contact.getTitle())
                .workPhone(contact.getWorkPhone())
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<AmContact> contactItemWriter() {
        return (chunkList) -> {
            for (AmContact contact : chunkList) {
                contactRepository.save(contact);
            }
        };
    }
}
