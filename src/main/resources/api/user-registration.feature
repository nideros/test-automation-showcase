Feature: User Registration

  Scenario: I register a new user on my API
    Given I am a new user
    And I register on ReqRes website
    When I check if the response code is 201