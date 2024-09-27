package com.carrefour.cartmanagement.service;

import com.carrefour.cartmanagement.entity.Cart;
import org.springframework.hateoas.EntityModel;

public interface CartService {

    EntityModel<Cart> findCartById(Long id);
}
