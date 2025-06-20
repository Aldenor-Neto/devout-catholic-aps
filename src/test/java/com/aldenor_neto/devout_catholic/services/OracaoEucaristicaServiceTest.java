package com.aldenor_neto.devout_catholic.services;

import com.aldenor_neto.devout_catholic.model.OracaoEucaristica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class OracaoEucaristicaServiceTest {
    @InjectMocks
    private OracaoEucaristicaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOracao() throws Exception {
        assertNotNull(service.getOracao());
    }
} 