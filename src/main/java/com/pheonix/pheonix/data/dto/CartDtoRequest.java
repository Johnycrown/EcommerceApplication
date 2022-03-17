package com.pheonix.pheonix.data.dto;

import lombok.Data;

@Data
public class CartDtoRequest {
    private Long userId;
    private Long productId;
    private int quantity;
}
