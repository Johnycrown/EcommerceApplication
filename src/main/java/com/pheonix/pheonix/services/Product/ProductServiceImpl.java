package com.pheonix.pheonix.services.Product;

import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pheonix.pheonix.data.dto.ProductDto;
import com.pheonix.pheonix.data.model.Product;
import com.pheonix.pheonix.data.repository.ProductRepository;
import com.pheonix.pheonix.services.cloud.CloudinaryService;
import com.pheonix.pheonix.web.exceptions.BusinessLogicException;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CloudinaryService cloudinaryService;
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
    public Product createProduct(ProductDto productDto) throws BusinessLogicException {
        if(productDto == null){
            throw  new IllegalArgumentException("Argument cannot be null");
        }
        Optional<Product> query = productRepository.findByName(productDto.getName());
        if(query.isPresent()){
            throw new BusinessLogicException("Product with name " + productDto.getName() + "already exist");
        }

       // cloudinaryService.upload(productDto.getImage().)

        Product product = new Product();
        try{
            if(productDto.getImage() != null) {
                Map<?, ?> uploadResult = cloudinaryService.upload(productDto.getImage().getBytes(), ObjectUtils.asMap("public", "inventory/" + productDto.getImage().getOriginalFilename(), "overwrite", true));
                product.setImageUrl(uploadResult.get("urls").toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());

        return productRepository.save(product);
    }
    private  Product savedorUpdate(Product product) throws BusinessLogicException {
        if(product== null){
            throw new BusinessLogicException("product cannot be null");
        }
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Long productName, JsonPatch productPatch) throws BusinessLogicException, JsonProcessingException, JsonPatchException  {
        Optional<Product> productQuery = productRepository.findById(productName);
        if(productQuery.isEmpty()){
            throw new BusinessLogicException("Product with ID"+ productName + "does not exit");
        }
        Product targetProduct = productQuery.get();
        try {
            targetProduct = applyPatchToProduct(productPatch, targetProduct);
            return savedorUpdate(targetProduct);

        } catch (JsonPatchException | JsonProcessingException | BusinessLogicException e) {
            throw new BusinessLogicException("update failed");
        }
    }
    private  Product applyPatchToProduct(JsonPatch productPatch, Product targetProduct) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = productPatch.
                apply(objectMapper.convertValue(targetProduct, JsonNode.class));
        return objectMapper.treeToValue(patched, Product.class);
    }
// Crud operations C- POST ,   R-GET    , U PUT&PATCH     ,   D- DELETE
   // @Override
//    public Product updateProduct(String productName, ProductDto productDto) throws ProductDoesNotExistException {
//        Optional<Product> queryProduct = productRepository.findByName(productName);
//
//        if(queryProduct.isPresent()){
//            queryProduct.get().setName(productDto.getName());
//            queryProduct.get().setDescription(productDto.getDescription());
//            queryProduct.get().setPrice(productDto.getPrice());
//            queryProduct.get().setQuantity(productDto.getQuantity());
//            queryProduct.get().setImageUrl(productDto.getImageUrl());
//            productRepository.save(queryProduct.get());
//            return queryProduct.get();
//        }
//        throw new ProductDoesNotExistException("Product with ID : " + productName + " : does not exists");
//
//    }

}
