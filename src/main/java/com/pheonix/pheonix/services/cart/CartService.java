package com.pheonix.pheonix.services.cart;

import com.github.fge.jsonpatch.JsonPatch;
import com.pheonix.pheonix.data.dto.CartDtoRequest;
import com.pheonix.pheonix.data.dto.CartDtoResponse;
import com.pheonix.pheonix.data.dto.CartUpdateDto;
import com.pheonix.pheonix.data.model.Cart;
import com.pheonix.pheonix.web.exceptions.BusinessLogicException;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;
import com.pheonix.pheonix.web.exceptions.UserNotFoundException;

public interface CartService {
    CartDtoResponse addItemToCart(CartDtoRequest cartDtoRequest) throws UserNotFoundException, ProductDoesNotExistException, BusinessLogicException;
    Cart viewCart();
   CartDtoResponse updateCart(CartUpdateDto cartUpdateDto) throws UserNotFoundException, BusinessLogicException;
}
