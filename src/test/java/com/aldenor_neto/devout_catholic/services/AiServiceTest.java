package com.aldenor_neto.devout_catholic.services;

import com.aldenor_neto.devout_catholic.config.WebClientConfig;
import com.aldenor_neto.devout_catholic.model.AiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.function.Function;
import org.springframework.web.util.UriBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AiServiceTest {
    @Mock
    private WebClientConfig webClientConfig;
    @InjectMocks
    private AiService service;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientConfig.createWebClient(anyString())).thenReturn(webClient);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(Function.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(AiResponse.class)).thenReturn(Mono.just(new AiResponse()));
    }

    @Test
    void testGetReflexao() {
        assertDoesNotThrow(() -> service.getReflexao("pergunta de teste"));
    }
} 