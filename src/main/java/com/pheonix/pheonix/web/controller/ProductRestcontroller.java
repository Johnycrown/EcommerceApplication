package com.pheonix.pheonix.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pheonix.pheonix.data.dto.ProductDto;
import com.pheonix.pheonix.data.model.Product;
import com.pheonix.pheonix.services.Product.ProductService;
import com.pheonix.pheonix.web.exceptions.BusinessLogicException;
import com.pheonix.pheonix.web.exceptions.ProductDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductRestcontroller {
    @Autowired
    ProductService productService;
    @GetMapping()
    public ResponseEntity<?> findAllProduct(){
        List<Product> productList = productService.getAllProduct();
        return ResponseEntity.ok().body(productList);

    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto, @RequestBody MultipartFile productImage){


        try{
            Product savedProduct = productService.createProduct(productDto);
            return ResponseEntity.ok().body(savedProduct);

        } catch (BusinessLogicException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }



    }
    @PatchMapping(path= "/{id}", consumes =  "application/json-patch+json" )
            public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody JsonPatch productPatch){
        try{
            Product updateProduct = productService.updateProduct(id, productPatch);
            return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
        } catch (BusinessLogicException | JsonPatchException | JsonProcessingException e) {
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
      //  catch()
    }
   // @PutMapping ("/{ProductName}/ProductDto")
//    public ResponseEntity<?> updateProduct(String ProductName, ProductDto productDto){
//        try{
//            Product updatedProduct = productService.updateProduct(ProductName, productDto);
//            return  ResponseEntity.ok().body(updatedProduct);
//        } catch (IllegalArgumentException | ProductDoesNotExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//
//        }
//    }


}
