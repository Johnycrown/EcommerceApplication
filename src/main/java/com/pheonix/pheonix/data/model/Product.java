package com.pheonix.pheonix.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable = false)
    private String name;
    private  String description;
    private  int quantity;
    private double price;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-ss-HH-mm-ss")
    private LocalDateTime datecreated;
    private String imageUrl;

}
