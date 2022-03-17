package com.pheonix.pheonix.data.dto;

import com.pheonix.pheonix.data.model.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDtoResponse {
    private List<Item> cartItem;
    private double totalPrice;
}
