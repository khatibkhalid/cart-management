package com.carrefour.cartmanagement.service;

import com.carrefour.cartmanagement.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

public interface CartService {

    EntityModel<Cart> findCartById(Long id);

    Page<EntityModel<Cart>> findAll(Pageable pageable);
}
