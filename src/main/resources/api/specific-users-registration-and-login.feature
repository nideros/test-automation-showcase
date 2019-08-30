Feature: Multiple Users Registration amd Login

  Scenario Outline: I register a new user on my API, then I try to login on the website
    Given I am a new user with email <email> and password <password>
    When I register on ReqRes website
    Then I check if the response code is 201
    When I try to login
    Then The login is successful

    Examples:
      | email                   | password     |
      | eve.holt@reqres.in      | cityslicka   |
