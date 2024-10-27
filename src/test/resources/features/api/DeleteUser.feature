@regression @API @users @deleteUserFeature
Feature: Delete User
  As a user I want to delete details of a user

  @deleteUser
  Scenario: Delete an existing user
    When I request to create a new user with the following details
      | name     | job    |
      | John Doe | leader |
    And the response status code should be 201
    When I request to delete the user
    Then the response status code should be 204
    And the response must have no body content
