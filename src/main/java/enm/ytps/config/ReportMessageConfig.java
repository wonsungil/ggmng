package enm.ytps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class ReportMessageConfig {
    
    @Bean
    public ReloadableResourceBundleMessageSource dimensionMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/dimensionMessage");
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        source.setCacheSeconds(60);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public ReloadableResourceBundleMessageSource columnMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/columnMessage");
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        source.setCacheSeconds(60);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
