package ua.com.kalinichev.microservices.scheduleservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    private static final String CORE_SERVICE_URL = "http://localhost:4040";

    @Bean
    public WebClient getWebClient() {
        return  WebClient.create(CORE_SERVICE_URL);
    }

}
