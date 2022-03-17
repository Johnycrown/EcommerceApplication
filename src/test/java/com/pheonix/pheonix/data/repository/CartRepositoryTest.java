package com.pheonix.pheonix.data.repository;

import com.pheonix.pheonix.data.model.Cart;
import com.pheonix.pheonix.data.model.Item;
import com.pheonix.pheonix.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("add an item to cart")
    void addProductToCart(){
        Product product = productRepository.findByName("Macbook Air").orElse(null);
         assertThat(product).isNotNull();
        Item item = new Item(product,2);
        Cart cart = new Cart();
        cart.addItem(item);
        cartRepository.save(cart);
        assertThat(cart.getId()).isNotNull();
        assertThat(cart.getItemsList().isEmpty()).isFalse();
      //  assertThat(cart.getItemsList().get(0).getProduct()).isNotNull();




    }

    @Test
    @DisplayName("view all cart")
    void viewItemInCart(){
        Cart savedCart = cartRepository.findById(345l).orElse(null);
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getItemsList().size()).isEqualTo(3);
        log.info("cart retreived from the DB -> {}", savedCart);
    }


}