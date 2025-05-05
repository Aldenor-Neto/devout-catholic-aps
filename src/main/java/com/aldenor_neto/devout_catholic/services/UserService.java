package com.aldenor_neto.devout_catholic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aldenor_neto.devout_catholic.model.User;
import com.aldenor_neto.devout_catholic.model.DTO.UserRegistration;
import com.aldenor_neto.devout_catholic.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User userSignUp(UserRegistration entity) {
        if (repository.existsByEmail(entity.email())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(entity.password());

        var user = new User(entity.name(), entity.email(), senhaCriptografada);

        return repository.save(user);
    }
}
