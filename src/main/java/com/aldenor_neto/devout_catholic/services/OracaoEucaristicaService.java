package com.aldenor_neto.devout_catholic.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.aldenor_neto.devout_catholic.model.OracaoEucaristica;

@Service
public class OracaoEucaristicaService {

    public List<OracaoEucaristica> getOracao() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = "json/oracoes-eucaristicas.json";

            ClassPathResource resource = new ClassPathResource(json);

            if (!resource.exists()) {
                System.out.println("arquivo não encontrado!");
                return null;
            }

            List<OracaoEucaristica> oracoes = objectMapper.readValue(resource.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, OracaoEucaristica.class));

            return oracoes;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
