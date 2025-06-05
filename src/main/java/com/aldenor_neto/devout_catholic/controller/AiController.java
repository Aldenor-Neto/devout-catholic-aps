package com.aldenor_neto.devout_catholic.controller;

import com.aldenor_neto.devout_catholic.model.Question;
import com.aldenor_neto.devout_catholic.services.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AiController {
    private static final Logger logger = LoggerFactory.getLogger(AiController.class);

    @Autowired
    private AiService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getReflexao(@RequestBody Question entity) {
        try {
            logger.info("Recebendo requisição para gerar reflexão. Pergunta: {}", entity.getQuestion());
            String resposta = service.getReflexao(entity.getQuestion());
            logger.info("Reflexão gerada com sucesso");
            
            Map<String, Object> response = new HashMap<>();
            response.put("error", new HashMap<>());
            response.put("text", resposta);
            
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        } catch (Exception e) {
            logger.error("Erro ao gerar reflexão", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("text", "Erro ao gerar reflexão: " + e.getMessage());
            return ResponseEntity.internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
        }
    }
}
