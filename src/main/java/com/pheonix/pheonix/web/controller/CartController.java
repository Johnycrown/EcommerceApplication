package com.pheonix.pheonix.web.controller;

import com.pheonix.pheonix.data.dto.CartDtoRequest;
import com.pheonix.pheonix.data.dto.CartDtoResponse;
import com.pheonix.pheonix.services.cart.CartService;
import com.pheonix.pheonix.web.exceptions.BusinessLogicException;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;
import com.pheonix.pheonix.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;





    @PostMapping
    public ResponseEntity<?> adddItemToCart(@RequestBody  CartDtoRequest cartDtoRequest){
        CartDtoResponse cartDtoResponse = null;
        try{
         cartDtoResponse =   cartService.addItemToCart(cartDtoRequest);
        } catch (UserNotFoundException | BusinessLogicException | ProductDoesNotExistException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().body(cartDtoResponse);
    }
}
