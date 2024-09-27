package com.carrefour.cartmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "cart_item")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int quantity;

    @Column(nullable = false)
    String productName;

    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    Cart cart;

}
