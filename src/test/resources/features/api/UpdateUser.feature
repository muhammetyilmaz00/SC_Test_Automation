@regression @API @users @updateUserFeature
Feature: Update User
  As a user I want to update details of a user

  @updateUser @createUser
  Scenario: Update an existing user's details
    When I request to create a new user with the following details
      | name       | job    |
      | Alice Test | leader |
    And the response status code should be 201
    When I request to update the user with the following details
      | name       | job    |
      | Alice Test | tester |
    Then the response status code should be 200
    And the response must contain the updated user details
      | name       | job    |
      | Alice Test | tester |

  @partialUpdateUser @createUser
  Scenario: Partially update an existing user's details
    When I request to create a new user with the following details
      | name       | job    |
      | Alice Test | leader |
    And the response status code should be 201
    When I request to partially update the user with the following details
      | job    |
      | tester |
    Then the response status code should be 200
    And the response must contain the updated user details
      | job    |
      | tester |

