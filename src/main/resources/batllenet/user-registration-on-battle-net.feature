Feature: User Registration

  Scenario: I register a new user on Blizzard website
    Given I am surfing Battle.net website
    When I want to register
    Then I fill the registration form
    Then I can access myAccount area
    When I logout
    Then I am logged out