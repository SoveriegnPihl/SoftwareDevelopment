## Author: Thomas Schiøler Hansen
Feature: An employee  registers sick-day or holiday
  Description: An employee registers that they have a sick-day or that they leave on holiday
  Actors: Developer

  Scenario: Developer registers a sick-day
    Given there is a developer with initials "jako"
    When the developer registers a sick-day
    Then the developer is not available for a day

  Scenario: Developer registers a holiday
    Given there is a developer with initials "jako"
    When the developer registers a holiday from 25 - 6 - 2022 to 1 - 7 - 2022
    Then the developer has a occupation in the system
    And the developer will not be available between first and last day of holiday

