package com.pheonix.pheonix.data.dto;

import com.pheonix.pheonix.data.model.QunatityOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartUpdateDto {
    private  Long userId;
    private Long itemId;
    private QunatityOperation quantity;
}
