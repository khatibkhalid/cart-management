package com.carrefour.cartmanagement.unit;

import com.carrefour.cartmanagement.entity.Cart;
import com.carrefour.cartmanagement.exception.ResourceNotFoundException;
import com.carrefour.cartmanagement.repository.CartRepository;
import com.carrefour.cartmanagement.service.CartService;
import com.carrefour.cartmanagement.service.impl.CartServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImp cartService;

    private Cart cart;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart();
        cart.setId(1L);
    }

    @Test
    public void testFindCartById_Success() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        EntityModel<Cart> result = cartService.findCartById(1L);

        assertNotNull(result);
        assertEquals(cart.getId(), result.getContent().getId());
        assertEquals(1L, result.getContent().getId());
        assertTrue(result.hasLink("self"));
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindCartById_NotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            cartService.findCartById(1L);
        });

        assertEquals("Cart with id: 1 not found", exception.getMessage());
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll_Success() {
        // Arrange
        Cart cart1 = new Cart();
        cart1.setId(1L);
        Cart cart2 = new Cart();
        cart2.setId(2L);
        List<Cart> carts = List.of(cart1, cart2);
        Pageable pageable = PageRequest.of(0, 10);

        when(cartRepository.findAll(pageable)).thenReturn(new PageImpl<>(carts, pageable, carts.size()));

        var result = cartService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(1L, result.getContent().get(0).getContent().getId());
        assertEquals(2L, result.getContent().get(1).getContent().getId());
        verify(cartRepository, times(1)).findAll(pageable);
    }

}