Feature: Add product to cart on Flipkart

  Scenario: Add a product to cart after searching
    Given User is on Flipkart home page
    When User search for "mixer grinder bajaj"
    And User selects the first product from the search result
    And User adds the product to the cart
    Then Product should be successfully added to the cart
    
