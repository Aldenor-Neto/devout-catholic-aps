package com.aldenor_neto.devout_catholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aldenor_neto.devout_catholic.model.OracaoEucaristica;
import com.aldenor_neto.devout_catholic.services.OracaoEucaristicaService;

@RestController
@RequestMapping("/oracao-eucaristica")
public class OracaoEucaristicaController {

    @Autowired
    private OracaoEucaristicaService service;

    @GetMapping
    public List<OracaoEucaristica> getOracoesEucaristicas() {
        return service.getOracao();
    }
}
