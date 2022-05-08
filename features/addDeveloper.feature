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
