##Author: Thomas Schiøler Hansen
Feature: Adding a developer to the system
  Description: A developer, with up to 4 initials, is added and registered in the system.
  Actors: User
  Scenario: User creates a non-existent employee
    Given There is an unregistered developer "JEJE"
    And the developer "JEJE" is not registered in the system
    When the developer "JEJE" is created
    Then developer "JEJE" is registered in the system

  Scenario: User creates an employee with more than 4 initials
    Given the developer "Jens Jensen" is created
    And the developer "Jens Jensen" does not exist
    When the developer "Jens Jensen" is created
    Then the developer "Jens Jensen" does not exist
    And an error message "Developers can only have 4 intitials" is displayed

  Scenario: User creates an existing employee
    Given There is a developer "JEJE"
    And the developer "JEJE" exists
    Then the developer "JEJE" is not created
    Then an error message "The developer JEJE already exists in the system" is displayed

  Scenario: User creates an employee with no initials
    Given there is an developer ""
    And "" is created
    Then the developer "" is not created
    Then an error message "Developers must have intitials" is displayed