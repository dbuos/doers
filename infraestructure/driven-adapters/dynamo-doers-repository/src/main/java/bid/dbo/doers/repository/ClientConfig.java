package bid.dbo.doers.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webClient(@Value("${app.doers.url}") String doersBaseUrl) {
        return WebClient.builder().baseUrl(doersBaseUrl).build();
    }
}
