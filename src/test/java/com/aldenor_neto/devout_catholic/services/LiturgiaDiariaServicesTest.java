package com.aldenor_neto.devout_catholic.services;

import com.aldenor_neto.devout_catholic.config.WebClientConfig;
import com.aldenor_neto.devout_catholic.model.LiturgiaDiaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LiturgiaDiariaServicesTest {
    @Mock
    private WebClientConfig webClientConfig;
    @InjectMocks
    private LiturgiaDiariaServices service;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientConfig.createWebClient(anyString())).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(LiturgiaDiaria.class)).thenReturn(Mono.just(new LiturgiaDiaria()));
    }

    @Test
    void testLiturgiaDoDia() {
        assertDoesNotThrow(() -> service.liturgiaDoDia());
    }
} 