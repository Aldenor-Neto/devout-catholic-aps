package com.aldenor_neto.devout_catholic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class AnotacaoControllerTest extends BaseControllerTest {

    @Test
    void testGetAllAnotacoes() throws Exception {
        mockMvc.perform(get("/anotacoes")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllAnotacoesWithoutToken() throws Exception {
        mockMvc.perform(get("/anotacoes"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testCreateAnotacao() throws Exception {
        String anotacaoJson = "{\"titulo\":\"Nova Anotação\",\"conteudo\":\"Conteúdo da nova anotação\"}";
        mockMvc.perform(post("/anotacoes")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(anotacaoJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateAnotacaoWithoutToken() throws Exception {
        String anotacaoJson = "{\"titulo\":\"Nova Anotação\",\"conteudo\":\"Conteúdo da nova anotação\"}";
        mockMvc.perform(post("/anotacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(anotacaoJson))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAnotacaoById() throws Exception {
        // Testar com um ID que pode não existir - esperar 404
        mockMvc.perform(get("/anotacoes/999")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAnotacaoByIdWithoutToken() throws Exception {
        mockMvc.perform(get("/anotacoes/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateAnotacao() throws Exception {
        String anotacaoJson = "{\"id\":999,\"titulo\":\"Anotação Atualizada\",\"conteudo\":\"Conteúdo atualizado\"}";
        mockMvc.perform(put("/anotacoes/999")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(anotacaoJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateAnotacaoWithoutToken() throws Exception {
        String anotacaoJson = "{\"id\":1,\"titulo\":\"Anotação Atualizada\",\"conteudo\":\"Conteúdo atualizado\"}";
        mockMvc.perform(put("/anotacoes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(anotacaoJson))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testDeleteAnotacao() throws Exception {
        // Testar com um ID que pode não existir - esperar 404
        mockMvc.perform(delete("/anotacoes/999")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAnotacaoWithoutToken() throws Exception {
        mockMvc.perform(delete("/anotacoes/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
} 