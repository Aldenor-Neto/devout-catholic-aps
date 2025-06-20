package com.aldenor_neto.devout_catholic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserControllerTest extends BaseControllerTest {

    @Test
    void testEfetuarLogin() throws Exception {
        String loginJson = "{\"email\":\"user@devoutcatholic\",\"password\":\"catholic\"}";
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testEfetuarLoginComCredenciaisInvalidas() throws Exception {
        String loginJson = "{\"email\":\"user@devoutcatholic\",\"password\":\"senhaerrada\"}";
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegistrarUsuario() throws Exception {
        String registerJson = "{\"name\":\"Novo Usuário\",\"email\":\"teste" + System.currentTimeMillis() + "@devoutcatholic.com\",\"password\":\"senha123\"}";
        mockMvc.perform(post("/login/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testRegistrarUsuarioComEmailDuplicado() throws Exception {
        String registerJson = "{\"name\":\"Usuário Duplicado\",\"email\":\"user@devoutcatholic\",\"password\":\"senha123\"}";
        mockMvc.perform(post("/login/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegistrarUsuarioComDadosInvalidos() throws Exception {
        String registerJson = "{\"name\":\"\",\"email\":\"emailinvalido\",\"password\":\"\"}";
        mockMvc.perform(post("/login/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
} 