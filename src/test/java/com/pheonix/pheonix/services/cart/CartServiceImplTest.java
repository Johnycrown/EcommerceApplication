package com.pheonix.pheonix.services.cart;

import com.pheonix.pheonix.data.dto.CartDtoRequest;
import com.pheonix.pheonix.data.dto.CartDtoResponse;
import com.pheonix.pheonix.data.dto.CartUpdateDto;
import com.pheonix.pheonix.data.model.*;
import com.pheonix.pheonix.data.repository.AppUserRepository;
import com.pheonix.pheonix.data.repository.CartRepository;
import com.pheonix.pheonix.data.repository.ProductRepository;
import com.pheonix.pheonix.web.exceptions.BusinessLogicException;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;
import com.pheonix.pheonix.web.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("/Db/insert.sql")
@Slf4j
class CartServiceImplTest {

@Autowired
    AppUserRepository appUserRepository;
@Autowired
    ProductRepository productRepository;
@Autowired
    CartRepository cartRepository;

@Autowired
CartService cartService;

    @Test
    @DisplayName("add item to cart")
    void addItemToCart() throws UserNotFoundException, ProductDoesNotExistException, BusinessLogicException {
        CartDtoRequest cartDtoRequest = new CartDtoRequest();
        cartDtoRequest.setProductId(13l);
        cartDtoRequest.setUserId(5011L);
        cartDtoRequest.setQuantity(1);
        CartDtoResponse cartDtoResponse = cartService.addItemToCart(cartDtoRequest);
        assertThat(cartDtoResponse.getCartItem()).isNotNull();
        assertThat(cartDtoResponse.getCartItem().size()).isEqualTo(1);
//        AppUser existingUser = appUserRepository.findById(cartDtoRequest.getUserId()).orElse(null);
//    assertThat(existingUser).isNotNull();
//
//
//
//    Cart mycart = existingUser.getMyCart();
//    Product product = productRepository.findById(13L).orElse(null);
//    assertThat(product).isNotNull();
//    assertThat(product.getQuantity()).isGreaterThan(cartDtoRequest.getQuantity());
//
//    Item cartItem = new Item(product, cartDtoRequest.getQuantity());
//    mycart.addItem(cartItem);
//    cartRepository.save(mycart);

    }
    @Test
    @DisplayName("update cart")
    void updateCartItemTest() throws UserNotFoundException, BusinessLogicException {
        CartUpdateDto cartUpdateDto =  CartUpdateDto.builder()
                                                     .itemId(511l)
                                                      .userId(5011l)
                                                        .quantity(QunatityOperation.INCREASE)
                                                        .build();

        AppUser appUser = appUserRepository.findById(5011L).orElse(null);
        assertThat(appUser).isNotNull();

        Cart userCart = appUser.getMyCart();
        assertThat(userCart.getItemsList().size()).isEqualTo(1);
        Item item = userCart.getItemsList().get(0);
        log.info("item -> {}", item);
        assertThat(item).isNotNull();
        assertThat(item.getQuantityAdded()).isEqualTo(2);
        log.info("cart update obj -> ", userCart);
        CartDtoResponse cartDtoResponse = cartService.updateCart(cartUpdateDto);
        assertThat(cartDtoResponse).isNotNull();
        assertThat(cartDtoResponse);


//        for(int i = 0; i <= userCart.getItemsList().size(); i++){
//
//            item = userCart.getItemsList().get(i);
//            if(item.getId()== cartUpdateDto.getItemId()){
//                break;
//            }
//        }

    }

}