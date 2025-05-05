package com.aldenor_neto.devout_catholic.controller;

import com.aldenor_neto.devout_catholic.config.TokenService;
import com.aldenor_neto.devout_catholic.model.User;
import com.aldenor_neto.devout_catholic.model.DTO.DadosLogin;
import com.aldenor_neto.devout_catholic.model.DTO.DadosTokenJwt;
import com.aldenor_neto.devout_catholic.model.DTO.UserRegistration;
import com.aldenor_neto.devout_catholic.services.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody DadosLogin entity) {

        var token = new UsernamePasswordAuthenticationToken(entity.email(), entity.password());
        var authentication = manager.authenticate(token);

        var tokenJwt = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody UserRegistration newUser) {
        User user = service.userSignUp(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "mensagem", "Usuario criado com sucesso!",
                "nome", user.getNome(),
                "email", user.getEmail()));
    }
}
