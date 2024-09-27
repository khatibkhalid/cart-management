package com.carrefour.cartmanagement.controller;

import com.carrefour.cartmanagement.entity.Cart;
import com.carrefour.cartmanagement.service.CartService;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/carts")
@Api(tags = "Cart managemennt")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartService cartService;

    @GetMapping("/{id}")
    public EntityModel<Cart> findCartById(@PathVariable Long id) {
        log.info("*** Find cart by id: "+id);
        return cartService.findCartById(id);
    }
}
