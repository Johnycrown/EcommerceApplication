package com.pheonix.pheonix.services.Product;

import com.pheonix.pheonix.data.dto.ProductDto;
import com.pheonix.pheonix.data.model.Product;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product findProductByID(Long productId) throws ProductDoesNotExistException;
    Product createProduct(ProductDto productDto);

}
