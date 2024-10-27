@regression @API @users @getUserFeature
Feature: Get User
  As a user I want to get details of a user

  @getAllUsers
  Scenario: Get list of all users
    When I request to get all users
    Then the response status code should be 200
    And the response should match the schema of a list of users