Feature: Add product to cart on Flipkart

  Scenario: Add a product to cart after searching
    Given User is on flipkart home page
    When User searchs for "mixer grinder bajaj"
    And User select the first product from the search result
    And User adding the product to the cart
    And Product should successfully added to the cart
    Then Display Number of Products in Cart
    
