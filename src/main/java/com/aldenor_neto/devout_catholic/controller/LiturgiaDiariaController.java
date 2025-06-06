package com.aldenor_neto.devout_catholic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aldenor_neto.devout_catholic.model.LiturgiaDiaria;
import com.aldenor_neto.devout_catholic.services.LiturgiaDiariaServices;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/liturgia")
public class LiturgiaDiariaController {

    @Autowired
    private LiturgiaDiariaServices service;

    @GetMapping
    public LiturgiaDiaria liturgiaDoDia() {
        return service.liturgiaDoDia();
    }

    @GetMapping("/{dia}/{mes}")
    public LiturgiaDiaria liturgiaPorData(@PathVariable int dia, @PathVariable int mes) {
        return service.liturgiaPorData(dia, mes);
    }
}
