package com.aldenor_neto.devout_catholic.controller;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class OracaoEucaristicaControllerTest extends BaseControllerTest {

    @Test
    void testGetOracoesEucaristicas() throws Exception {
        mockMvc.perform(get("/oracao-eucaristica")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetOracoesEucaristicasWithoutToken() throws Exception {
        mockMvc.perform(get("/oracao-eucaristica"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
} 