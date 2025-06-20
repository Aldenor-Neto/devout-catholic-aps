package com.aldenor_neto.devout_catholic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class AiControllerTest extends BaseControllerTest {

    @Test
    void testGetReflexao() throws Exception {
        String json = "{\"question\":\"Qual o sentido da vida?\"}";
        mockMvc.perform(post("/ai")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetReflexaoWithoutToken() throws Exception {
        String json = "{\"question\":\"Qual o sentido da vida?\"}";
        mockMvc.perform(post("/ai")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
} 