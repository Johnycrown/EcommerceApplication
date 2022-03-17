package com.pheonix.pheonix.data.repository;

import com.pheonix.pheonix.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Sql(scripts = {"/Db/insert.sql/"} )
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){

    }
    @Test
    @DisplayName("save a new product to the database")
    void saveproduct(){
        Product product = new Product();
        product.setName("Bambom Chair");
        product.setPrice(5540);
        product.setQuantity(9);
        assertThat(product.getId()).isNull();
        productRepository.save(product);
        log.info("product save :: {}", product);
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Bambom Chair");
        assertThat(product.getDatecreated()).isNotNull();
        assertThat(product.getQuantity()).isEqualTo(9);
        assertThat(product.getPrice()).isEqualTo(5540);


    }
    @Test
    @DisplayName("find existing product from database")
    void findExistingProduct(){
       // (12, 'Luxury Mop', 2430, 3
        Product product = productRepository.findById(12L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Mop");
        assertThat(product.getPrice()).isEqualTo(2430);
        assertThat(product.getQuantity()).isEqualTo(3);
        //log.info("product retr")

    }
    @Test
    @DisplayName("find all product in the database")
    void findAllProductIntheDatabase(){
        List<Product> productList = productRepository.findAll();
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(4);
    }
    @Test
    @DisplayName("find product by name")
    void findProductByName(){
        Product product = productRepository.findByName("Luxury Mop").orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Mop");
        assertThat(product.getPrice()).isEqualTo(2430);
        assertThat(product.getQuantity()).isEqualTo(3);
        log.info("product retrieve :: {}", product);

    }
    @Test
    @DisplayName("update product")
    void updateProductAttreibuteTest(){
        //check product exist
        Product savedProduct = productRepository.findByName("Luxury Mop").orElse(null);
        assertThat(savedProduct).isNotNull();
        //update Product
        assertThat(savedProduct.getName()).isEqualTo("Luxury Mop");
        assertThat(savedProduct.getPrice()).isEqualTo(2430);
        savedProduct.setName("Luxury Mop update");
        savedProduct.setPrice(123457);
        //save Product
        productRepository.save(savedProduct);
        assertThat(savedProduct.getName()).isEqualTo("Luxury Mop update");
        assertThat(savedProduct.getPrice()).isEqualTo(123457);

    }

}