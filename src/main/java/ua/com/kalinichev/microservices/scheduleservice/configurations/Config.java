package ua.com.kalinichev.microservices.scheduleservice.configurations;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    private static final String CORE_SERVICE_ID = "CORE-SERVICE";

    @Autowired
    private EurekaClient discoveryClient;

    @Bean
    public WebClient getWebClient() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(CORE_SERVICE_ID, false);
        return WebClient.builder().baseUrl(instance.getHomePageUrl() + "core/").build();
    }

}
