Feature: User Registration via front-end automation

  Scenario: I register a new user on an online store
    Given I am surfing MyStore website
    When I want to register
    Then I fill the registration form
    Then I can access myAccount area
    When I logout
    Then I am logged out