package ua.com.kalinichev.microservices.scheduleservice.configurations;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    //private static final String CORE_SERVICE_URL = "http://localhost:3030/core/";
    private static final String CORE_SERVICE_URL = "http://CORE-SERVICE/core/";

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebClient getWebClient(WebClient.Builder builder) {
        return builder.baseUrl(CORE_SERVICE_URL).build();
    }

}
