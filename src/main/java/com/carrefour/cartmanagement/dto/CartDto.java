package com.carrefour.cartmanagement.dto;

import com.carrefour.cartmanagement.entity.CartItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDto {
    private Long id;

    private List<CartItem> items;
}