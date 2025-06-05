package com.aldenor_neto.devout_catholic.services;

import com.aldenor_neto.devout_catholic.config.WebClientConfig;
import com.aldenor_neto.devout_catholic.model.AiRequest;
import com.aldenor_neto.devout_catholic.model.AiResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AiService {

    private final WebClientConfig webClientConfig;
    private final String apiKey;

    public AiService(WebClientConfig webClientConfig, @org.springframework.beans.factory.annotation.Value("${api.gpt.key}") String apiKey) {
        this.webClientConfig = webClientConfig;
        this.apiKey = apiKey;
    }

    public String getReflexao(String question) {
        WebClient webClient = webClientConfig.createWebClient("https://generativelanguage.googleapis.com");

        AiRequest requestPayload = new AiRequest(question);

        Mono<AiResponse> responseMono = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-1.5-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(requestPayload)
                .retrieve()
                .bodyToMono(AiResponse.class);

        AiResponse aiResponse = responseMono.block();

        if (aiResponse != null && aiResponse.getCandidates() != null && !aiResponse.getCandidates().isEmpty()) {
            return aiResponse.getCandidates().get(0).getContent().getParts().get(0).getText();
        } else {
            return "Não foi possível obter uma resposta da API.";
        }
    }
}
