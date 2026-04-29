package com.finance.financeapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.financeapp.DTO.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateTransaction() throws Exception {
        TransactionDTO dto = new TransactionDTO();
        dto.setAmount(new BigDecimal("100.50"));
        dto.setDescription("Groceries");
        dto.setDate(LocalDateTime.now());
        dto.setType("EXPENSE");
        dto.setCategoryId(1L);

        mockMvc.perform(post("/api/transactions")
                        .header("Authorization", "Bearer <your-jwt-token>")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
