package com.aldenor_neto.devout_catholic.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aldenor_neto.devout_catholic.model.User;
import com.aldenor_neto.devout_catholic.model.DTO.UserRegistration;
import com.aldenor_neto.devout_catholic.repository.UserRepository;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenUserSignUpWithValidDataThenReturnUser() {
        // Arrange
        UserRegistration registration = new UserRegistration("Test User", "test@test.com", "password123");
        User user = new User("Test User", "test@test.com", "encodedPassword");
        user.setId(1L);

        when(repository.existsByEmail(registration.email())).thenReturn(false);
        when(passwordEncoder.encode(registration.password())).thenReturn("encodedPassword");
        when(repository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.userSignUp(registration);

        // Assert
        assertNotNull(result);
        assertEquals(registration.name(), result.getNome());
        assertEquals(registration.email(), result.getEmail());
    }

    @Test
    void whenUserSignUpWithExistingEmailThenThrowException() {
        // Arrange
        UserRegistration registration = new UserRegistration("Test User", "existing@test.com", "password123");
        when(repository.existsByEmail(registration.email())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.userSignUp(registration));
    }
} 