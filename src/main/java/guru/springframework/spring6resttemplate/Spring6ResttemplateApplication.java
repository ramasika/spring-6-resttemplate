package guru.springframework.spring6resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class Spring6ResttemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring6ResttemplateApplication.class, args);
    }



}
