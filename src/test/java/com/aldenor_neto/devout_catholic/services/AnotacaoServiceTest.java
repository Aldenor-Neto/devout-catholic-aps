package com.aldenor_neto.devout_catholic.services;

import com.aldenor_neto.devout_catholic.model.Anotacao;
import com.aldenor_neto.devout_catholic.model.User;
import com.aldenor_neto.devout_catholic.repository.AnotacaoRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnotacaoServiceTest {
    @Mock
    private AnotacaoRepository repository;
    @InjectMocks
    private AnotacaoService service;
    private User user;
    private Anotacao anotacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("user@email.com");
        anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setUser(user);
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
    }

    @Test
    void testFindAllByCurrentUser() {
        when(repository.findByUser(user)).thenReturn(List.of(anotacao));
        List<Anotacao> result = service.findAllByCurrentUser();
        assertEquals(1, result.size());
        assertEquals(user, result.get(0).getUser());
    }

    @Test
    void testFindByIdAndCurrentUser_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(anotacao));
        Anotacao result = service.findByIdAndCurrentUser(1L);
        assertEquals(anotacao, result);
    }

    @Test
    void testFindByIdAndCurrentUser_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> service.findByIdAndCurrentUser(2L));
    }

    @Test
    void testSaveOrUpdateForCurrentUser() {
        when(repository.saveAndFlush(any(Anotacao.class))).thenReturn(anotacao);
        Anotacao result = service.saveOrUpdateForCurrentUser(anotacao);
        assertEquals(user, result.getUser());
        verify(repository).saveAndFlush(anotacao);
    }

    @Test
    void testDeleteByIdAndCurrentUser_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(anotacao));
        service.deleteByIdAndCurrentUser(1L);
        verify(repository).delete(anotacao);
    }

    @Test
    void testDeleteByIdAndCurrentUser_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> service.deleteByIdAndCurrentUser(2L));
    }
} 