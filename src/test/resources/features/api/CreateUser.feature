@regression @API @users @createUserFeature
Feature: Create User
  As a user I want to create a new user

  @createUser
  Scenario Outline: Create a new user
    When I request to create a new user with the following details
      | name   | job   |
      | <name> | <job> |
    Then the response status code should be 201
    And the response must contain the user details
      | name   | job   |
      | <name> | <job> |
    Examples:
      | name       | job           |
      | Jane Doe   | leader        |
      | John Smith | zion resident |
