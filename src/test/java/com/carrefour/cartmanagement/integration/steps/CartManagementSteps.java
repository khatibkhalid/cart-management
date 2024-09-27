package com.carrefour.cartmanagement.integration.steps;

import com.carrefour.cartmanagement.entity.Cart;
import com.carrefour.cartmanagement.exception.ResourceNotFoundException;
import com.carrefour.cartmanagement.repository.CartRepository;
import com.carrefour.cartmanagement.service.CartService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest public class CartManagementSteps {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    private EntityModel<Cart> responseFindOne;
    private Page<EntityModel<Cart>> responseFindAll;
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
            responseFindOne = cartService.findCartById(id);
        } catch (ResourceNotFoundException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I should receive the cart details")
    public void i_should_receive_the_cart_details() {
        assertNotNull(responseFindOne);
        assertEquals(1L, responseFindOne.getContent().getId());
    }

    @Then("the response should contain a self link")
    public void the_response_should_contain_a_self_link() {
        assertTrue(responseFindOne.hasLink("self"));
    }

    @Then("I should receive an error message")
    public void i_should_receive_an_error_message() {
        assertNotNull(errorMessage);
    }

    @Given("the following carts exist:")
    public void the_following_carts_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Long> cartIds = dataTable.asList().stream().map(Long::parseLong).collect(Collectors.toList());
        List<Cart> carts = cartIds.stream().map(id -> {
            Cart cart = new Cart();
            cart.setId(id);
            return cart;
        }).collect(Collectors.toList());

        when(cartRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(carts, PageRequest.of(0, carts.size()), carts.size()));
    }

    @When("I request all carts with page {int} and size {int}")
    public void i_request_all_carts_with_page_and_size(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        responseFindAll = cartService.findAll(pageable);
    }

    @Then("I should receive a list of carts")
    public void i_should_receive_a_list_of_carts() {
        Assertions.assertNotNull(responseFindAll);
        Assertions.assertFalse(responseFindAll.getContent().isEmpty());
    }

    @Then("the number of carts should be {int}")
    public void the_number_of_carts_should_be(int expectedCount) {
        Assertions.assertEquals(expectedCount, responseFindAll.getContent().size());
    }
}
