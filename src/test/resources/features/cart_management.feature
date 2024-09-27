Feature: Cart Management

  Scenario: Add product to cart
    Given the cart is empty
    When I add a product "Laptop" with quantity 1
    Then the cart should contain 1 item

  Scenario: Remove product from cart
    Given I have a cart with a product "Laptop" with quantity 1
    When I remove the product "Laptop"
    Then the cart should be empty

  Scenario: Confirm order
    Given I have a cart with a product "Laptop" with quantity 1
    When I confirm the order
    Then the order status should be "CONFIRMED"