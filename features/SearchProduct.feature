Feature: Search for a product on Flipkart

  Scenario: Search for a product using the search bar
    Given User is on Flipkart Home Pages
    When User searches for "Laptop"
    Then Search results are displayed
