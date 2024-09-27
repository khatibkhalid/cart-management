package com.carrefour.cartmanagement.service.impl;

import com.carrefour.cartmanagement.controller.CartController;
import com.carrefour.cartmanagement.entity.Cart;
import com.carrefour.cartmanagement.exception.ResourceNotFoundException;
import com.carrefour.cartmanagement.repository.CartRepository;
import com.carrefour.cartmanagement.service.CartService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImp implements CartService {

    CartRepository cartRepository;

    @Override
    public EntityModel<Cart> findCartById(Long id) {
        return toEntityModel(cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart with id: " + id + " not found")));
    }

    private EntityModel<Cart> toEntityModel(Cart cart) {
        log.debug("Found cart: " + cart.getId());
        EntityModel<Cart> resource = EntityModel.of(cart);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CartController.class).findCartById(cart.getId())).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
