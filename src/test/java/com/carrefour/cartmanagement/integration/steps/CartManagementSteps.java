package com.carrefour.cartmanagement.integration.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartManagementSteps {
    @Given("the following products exist:")
    public void the_following_products_exist(io.cucumber.datatable.DataTable dataTable) {
    }

    @When("I add {string} to my cart")
    public void i_add_to_my_cart(String productName) {
    }

    @Then("my cart should have {int} item with name {string}")
    public void my_cart_should_have_item_with_name(int quantity, String productName) {
    }

    @When("I remove {string} from my cart")
    public void i_remove_from_my_cart(String productName) {
    }

    @Then("my cart should be empty")
    public void my_cart_should_be_empty() {
    }

    @When("I confirm my cart")
    public void i_confirm_my_cart() {
    }

    @Then("my cart should be confirmed")
    public void my_cart_should_be_confirmed() {
    }

    @When("I check my cart status")
    public void i_check_my_cart_status() {
    }

    @Then("I should see the status as {string}")
    public void i_should_see_the_status_as(String status) {
    }
}
