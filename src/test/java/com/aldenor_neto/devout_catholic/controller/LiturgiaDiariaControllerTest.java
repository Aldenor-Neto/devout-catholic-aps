package com.aldenor_neto.devout_catholic.controller;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class LiturgiaDiariaControllerTest extends BaseControllerTest {

    @Test
    void testLiturgiaDoDia() throws Exception {
        mockMvc.perform(get("/liturgia")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testLiturgiaDoDiaWithoutToken() throws Exception {
        mockMvc.perform(get("/liturgia"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testLiturgiaPorData() throws Exception {
        mockMvc.perform(get("/liturgia/25/12")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testLiturgiaPorDataWithoutToken() throws Exception {
        mockMvc.perform(get("/liturgia/25/12"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
} 