package pokemon_battle_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class RestConfiguration {

    String username;

    String password;

    @Value("${trainer.service.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    @Value("${trainer.service.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    RestTemplate trainerApiRestTemplate() {
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new BasicAuthenticationInterceptor(username, password);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(clientHttpRequestInterceptor));
        return restTemplate;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}