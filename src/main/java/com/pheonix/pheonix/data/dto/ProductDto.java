package com.pheonix.pheonix.data.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
public class ProductDto {
    private String name;
    private  String description;
    private double price;
    private  int quantity;
    private MultipartFile image;

   // private String imageUrl;
}
