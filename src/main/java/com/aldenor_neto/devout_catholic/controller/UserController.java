package com.aldenor_neto.devout_catholic.controller;

import com.aldenor_neto.devout_catholic.config.TokenService;
import com.aldenor_neto.devout_catholic.model.User;
import com.aldenor_neto.devout_catholic.model.DTO.DadosLogin;
import com.aldenor_neto.devout_catholic.model.DTO.DadosTokenJwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody DadosLogin entity) {

        var token = new UsernamePasswordAuthenticationToken(entity.email(), entity.password());
        var authentication = manager.authenticate(token);

        var tokenJwt = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));
    }
}
