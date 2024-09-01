Feature: Search for a product and apply filters on Flipkart

Scenario: Search for a product and apply brand filter
    Given User is on Flipkart Home Page
    When User searching for "Laptop"
    And User applies brand filter as "HP"
    Then Filtered results by brand are displayed