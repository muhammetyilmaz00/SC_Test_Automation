@saucedemo @web @regression
Feature: Saucedemo Automation
  As a user of saucedemo.com
  I want to add items to my cart and purchase them

  Background:
    Given I login to the saucedemo.com

  @UI @purchase
  Scenario: Successful purchase an item
    When I add to chart the item "Sauce Labs Backpack"
    And I click the shopping cart button
    And I check the item "Sauce Labs Backpack" in the cart
    And I click the checkout button
    And I fill in first name with "Peter" and last name with "Peet" and postcode with "9713RD"
    And I click the continue button
    And I check the payment information and shipping information
    And I click the finish button
    Then I should see the "Thank you for your order!" message

  @UI @remove
  Scenario: Add an item to the cart and remove it
    When I add to chart the item "Sauce Labs Backpack"
    And I click the shopping cart button
    And I check the item "Sauce Labs Backpack" in the cart
    And I click the remove button of the item "Sauce Labs Backpack"
    Then I check the item "Sauce Labs Backpack" is not in the cart
    And I log out from the saucedemo.com

  @UI @sort
  Scenario Outline: Sort products <sortType>
    When I sort the products with "<sortType>"
    Then I should see the products sorted by "<sortType>"
    Examples:
      | sortType            |
      | Price (low to high) |
      | Price (high to low) |
      | Name (A to Z)       |
      | Name (Z to A)       |