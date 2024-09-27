package com.carrefour.cartmanagement.integration.steps;

import com.carrefour.cartmanagement.entity.Cart;
import com.carrefour.cartmanagement.exception.ResourceNotFoundException;
import com.carrefour.cartmanagement.repository.CartRepository;
import com.carrefour.cartmanagement.service.CartService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest public class CartManagementSteps {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    private EntityModel<Cart> response;
    private String errorMessage;

    @Given("a cart with ID {long} exists")
    public void a_cart_with_id_exists(Long id) {
        Cart cart = new Cart();
        cart.setId(id);
        when(cartRepository.findById(id)).thenReturn(Optional.of(cart));
    }

    @When("I request the cart with ID {long}")
    public void i_request_the_cart_with_id(Long id) {
        try {
            response = cartService.findCartById(id);
        } catch (ResourceNotFoundException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I should receive the cart details")
    public void i_should_receive_the_cart_details() {
        assertNotNull(response);
        assertEquals(1L, response.getContent().getId());
    }

    @Then("the response should contain a self link")
    public void the_response_should_contain_a_self_link() {
        assertTrue(response.hasLink("self"));
    }

    @Then("I should receive an error message")
    public void i_should_receive_an_error_message() {
        assertNotNull(errorMessage);
    }
}
