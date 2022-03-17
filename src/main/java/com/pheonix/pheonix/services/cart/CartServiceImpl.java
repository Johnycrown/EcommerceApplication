package com.pheonix.pheonix.services.cart;

import com.github.fge.jsonpatch.JsonPatch;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service

public class CartServiceImpl implements  CartService{
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;



    @Override
    public CartDtoResponse addItemToCart(CartDtoRequest cartDtoRequest) throws UserNotFoundException, ProductDoesNotExistException, BusinessLogicException {


        //check if user exist

       Optional<AppUser>  query = appUserRepository.findById(cartDtoRequest.getUserId());
        // Throw an exception if user is null
       if(query.isEmpty()){
           throw new UserNotFoundException("User with Id" + cartDtoRequest.getUserId() +" not found");
       }
       AppUser existingUser = query.get();

       // get user cart
        Cart mycart = existingUser.getMyCart();
        //check if product exist
        Optional<Product> querryProduct = productRepository.findById(13L);
        if(querryProduct.isEmpty()){
            throw new ProductDoesNotExistException("product with ID " + cartDtoRequest.getProductId() + " not found");
        }
        Product  product = querryProduct.get();
        if(!quantityIsValid(product, cartDtoRequest.getQuantity())){
            throw new BusinessLogicException("Quanitty too large");
        }


        Item cartItem = new Item(product, cartDtoRequest.getQuantity());
        mycart.addItem(cartItem);
        mycart.setTotalPrice(mycart.getTotalPrice() + calculateItemPrice(cartItem));
        cartRepository.save(mycart);
        return buildCartResponse(mycart);
    }

    private boolean quantityIsValid(Product product, int quantity){
        return quantity <= product.getQuantity();

    }

    private  CartDtoResponse buildCartResponse(Cart cart){
        return CartDtoResponse.builder()
                               .cartItem(cart.getItemsList())
                               .totalPrice(cart.getTotalPrice())
                                .build();
    }

    private Double calculateItemPrice(Item item ){
        return item.getProduct().getPrice() * item.getQuantityAdded();
    }

    @Override
    public Cart viewCart() {
        return null;
    }

    @Override
    public CartDtoResponse updateCart(CartUpdateDto cartUpdateDto) throws UserNotFoundException, BusinessLogicException {

        // get a cart by userId
        AppUser appUser = appUserRepository.findById(cartUpdateDto.getUserId()).orElse(null);
        if(appUser == null){
            throw new UserNotFoundException("userr with ID " + cartUpdateDto.getUserId() + "not found");
        }

        // get user cart
        Cart mycart = appUser.getMyCart();
        // find item in cart
        Item item = findItem(cartUpdateDto.getItemId(), mycart).orElse(null);
        if(item == null){
            throw new BusinessLogicException("Item not in cart");
        }
        if(cartUpdateDto.getQuantity()== QunatityOperation.INCREASE){

            item.setQuantityAddedToCart(item.getQuantityAdded()+1);
            mycart.setTotalPrice(mycart.getTotalPrice() + item.getProduct().getPrice());
        }
        else if(cartUpdateDto.getQuantity() == QunatityOperation.DECREASE){
            item.setQuantityAddedToCart(item.getQuantityAdded()-1);
            mycart.setTotalPrice(mycart.getTotalPrice() - item.getProduct().getPrice());
        }
      cartRepository.save(mycart);
        return buildCartResponse(mycart);




        // find item within the cart

    }

    private Optional<Item> findItem(Long itemId, Cart cart){
        Predicate<Item> itemPredicate = i -> i.getId().equals(itemId);
        return cart.getItemsList().stream().filter(itemPredicate).findFirst();
    }

}
