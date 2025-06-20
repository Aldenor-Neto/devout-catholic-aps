package com.aldenor_neto.devout_catholic.controller;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class BibliaControllerTest extends BaseControllerTest {

    @Test
    void testGetBiblia() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetBibliaWithoutToken() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testListarLivros() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/list-livros")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testListarLivrosWithoutToken() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/list-livros"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetLivro() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/Gênesis")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetLivroWithoutToken() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/Gênesis"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetCapitulo() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/Gênesis/1")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCapituloWithoutToken() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/Gênesis/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetVersiculo() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/Gênesis/1/1")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetVersiculoWithoutToken() throws Exception {
        mockMvc.perform(get("/biblia/catolica-jerusalem/Gênesis/1/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
} 