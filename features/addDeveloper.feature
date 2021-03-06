##Thomas

Feature: Adding an employee to the system
  Description: An employee, with up to 4 initials, is added and registered in the system.
  Actors: Developer/Manager

  Scenario: User creates a non-existent employee
    Given that "marc" is not a developer
    And that the initials isn't greater than 4
    Then the new developer gets registered

  Scenario: User creates an employee with more than 4 initials
    Given that "marcus" is not a developer
    And that the initials is greater than 4
    Then "marcus" is not created

  Scenario: User creates an existing employee
    Given that "ekki" is a developer
    Then "ekki" is still an employee

  Scenario: Adds developer with holiday
    Given that "thom" is not a developer
    And that the initials isn't greater than 4
    And the developer has a holiday starting 1 - 7 - 2022 and finishing 5 - 7 - 2022
    Then the developer has a occupation

  Scenario: Adds developer with old holiday
    Given that "thom" is not a developer
    And that the initials isn't greater than 4
    And the developer has a holiday starting 1 - 2 - 2022 and finishing 5 - 2 - 2022
    Then the developer dont have a occupation