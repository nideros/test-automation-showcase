Feature: Multiple Users Registration

  Scenario Outline: I register a new user on my API
    Given I am a new user with email <email> and password <password>
    And I register on ReqRes website
    When I check if the response code is 201

    Examples:
      | email                   | password     |
      | eve.holt@reqres.in      | cityslicka   |