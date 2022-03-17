package com.pheonix.pheonix.services.Product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pheonix.pheonix.data.dto.ProductDto;
import com.pheonix.pheonix.data.model.Product;
import com.pheonix.pheonix.web.exceptions.BusinessLogicException;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product findProductByID(Long productId) throws ProductDoesNotExistException;
    Product createProduct(ProductDto productDto) throws BusinessLogicException;
  //  Product  updateProduct(String productName, ProductDto productDto) throws ProductDoesNotExistException;
  Product  updateProduct(Long productName, JsonPatch patch) throws BusinessLogicException, JsonProcessingException, JsonPatchException;

}
