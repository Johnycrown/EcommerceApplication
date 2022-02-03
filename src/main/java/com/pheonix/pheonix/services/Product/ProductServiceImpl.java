package com.pheonix.pheonix.services.Product;

import com.pheonix.pheonix.data.dto.ProductDto;
import com.pheonix.pheonix.data.model.Product;
import com.pheonix.pheonix.data.repository.ProductRepository;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductByID(Long productId) throws ProductDoesNotExistException {
        if (productId == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<Product> queryResult = productRepository.findById(productId);
        if( queryResult.isPresent()){
            return  queryResult.get();
        }

        throw new ProductDoesNotExistException("Product with ID : " + productId + " : does not exists");

    }

    @Override
    public Product createProduct(ProductDto productDto) {
        if(productDto == null){
            throw  new IllegalArgumentException("Argument cannot be null");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());

        return productRepository.save(product);
    }
}
