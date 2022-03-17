package com.pheonix.pheonix.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pheonix.pheonix.data.model.Product;
import com.pheonix.pheonix.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/Db/insert.sql"})
class ProductRestcontrollerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;
    ObjectMapper objectMapper;
     @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
     }

    @Test
    @DisplayName("get product api")
    void getProductApi() throws Exception {
         mockMvc.perform(get("/api/product").contentType("application/json-patch+json")).
                 andExpect(status().is(200)).
                 andDo(print());


    }
    @Test
    @DisplayName("create product api")
    void createProductApi() throws Exception {
        Product product = new Product();
        product.setName("Bambom Chair");
        product.setPrice(5540);
        product.setQuantity(9);
        //assertThat(product.getId()).isNull();
        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/api/product").contentType("application/json-patch+json").
                content(requestBody)).
                andExpect(status().is(200)).
                andDo(print());
    }
    @Test
    @DisplayName("create product api")
    void updateProductApi() throws Exception {
        Product product = productRepository.findById(12L).orElse(null);
        assertThat(product).isNotNull();
        mockMvc.perform(patch("/api/product/14").contentType("application/json-patch+json").
                content(Files.readAllBytes(Path.of("ypayload.json")))).
                andExpect(status().is(200)).
                andDo(print());
    }



}