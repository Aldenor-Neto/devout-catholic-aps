package com.aldenor_neto.devout_catholic.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class BibliaServiceTest {
    @InjectMocks
    private BibliaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarLivros() {
        assertDoesNotThrow(() -> service.listarLivros("avemaria"));
    }
} 