package com.pheonix.pheonix.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pheonix.pheonix.data.dto.CartDtoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/Db/insert.sql")
class CartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("ad item to cart")
   void addItemToCartTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CartDtoRequest cartDtoRequest = new CartDtoRequest();
        cartDtoRequest.setProductId(14L);
        cartDtoRequest.setQuantity(1);
        cartDtoRequest.setUserId(5011L);

        mockMvc.perform(post("/api/cart")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cartDtoRequest)))
                .andExpect(status().is(200))
                .andDo(print());
    }
}