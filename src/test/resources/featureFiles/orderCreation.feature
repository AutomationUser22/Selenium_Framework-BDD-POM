Feature: Validate user is able to place an order

  Scenario: Validate user is able to place an order for ZARA item
    Given Login Page is open
    When User provider valid credentials
    And User adds ZARA product to the Cart
    And User adds details on Cart Page
    And User adds details on CheckOut Page
    Then Browser get closed