Feature: User places an order via front-end automation

  Scenario Outline: I finalize an order on an online store
    Given I am surfing MyStore website
    When I surf on <Category> category
    Then I add an item to cart
    And I want to proceed to checkout
    When the website asks me to register in order to finalize checkout
    Then I want to register
    And I fill the registration form
    When I select the shipping address
    And I select a delivery method
    And I select a payment method
    And I confirm I want to place the order
    Then the order is successfully finalized


    Examples:
      | Category |
      | T-SHIRTS |