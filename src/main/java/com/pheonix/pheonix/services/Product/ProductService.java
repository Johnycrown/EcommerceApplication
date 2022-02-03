package com.pheonix.pheonix.services.Product;

import com.pheonix.pheonix.data.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product findProductByID();
    Product createProduct();
}
