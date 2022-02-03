package com.pheonix.pheonix.data.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
public class ProductDto {
    private String name;
    private  String description;
    private double price;
    private  int quantity;

    private String imageUrl;
}
