##Author: Thomas Schiøler Hansen
Feature: Adding a developer to the system
  Description: A developer, with 4 initials, is added and registered in the system.
  Actors: User

  Scenario: User creates a developer with 4 initials
    When someone tries to create a developer "tom1"
    Then the developer "tom1" exists

  Scenario: User creates a developer with more than 4 initials
    When someone tries to create a developer "ekki123"
    Then the developer "ekki123" does not exist

  Scenario: User creates an existing developer
    When someone tries to create a developer "ekki"
    And the developer "ekki" exists
    Then the developer "ekki" is not created

  Scenario: User creates a developer with no initials
    When someone tries to create a developer ""
    Then the developer "" is not created
