package br.com.gabrielrodrigues.post.config;

import br.com.gabrielrodrigues.post.client.UserClientProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, UserClientProperties properties) {
        return builder.additionalRequestCustomizers( request -> {
            request.getHeaders().setBasicAuth(properties.getEncodedCredentials());
        }).build();
    }
}
