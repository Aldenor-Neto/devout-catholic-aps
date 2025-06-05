package com.aldenor_neto.devout_catholic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    public WebClient createWebClient(String baseUrl) {
        return webClientBuilder()
                .baseUrl(baseUrl)
                .build();
    }
}
