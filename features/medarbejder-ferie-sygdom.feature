##Author: Thomas Schiøler Hansen
Feature: A developer  registers sick-day or holiday
  Description: A developer registers that they have a sick-day or that they leave on holiday
  Actors: Developer
  Scenario: Developer registers a sick-day
    Given there is a developer "JEJE"
    And the developer "JEJE" exists in the system
    When the developer "JEJE" registers a sick-day
    Then developer "JEJE" is not available for any projects for the next 24 hours

  Scenario: Developer registers a holiday
    Given there is a developer "JEJE"
    And the developer "JEJE" exists in the system
    When the developer "JEJE" registers a holiday from 25/03/2022 to 01/04/2022
    Then the developer "JEJE"'s status in the system will be that he is on holiday
    And the developer "JEJE" will not be available for any projects between 25/03/2022 and 01/04/2022

  Scenario: Developer registers a holiday but has had more than 4 weeks of holiday already
    Given there is a developer "JEJE"
    And the developer "JEJE" exists in the system
    And the developer "JEJE" already has 4 weeks of holiday registered
    Then a message "JEJE, you do not have any more available holiday left" is displayed
    And the developer "JEJE"'s status in the system will be that he is not on holiday
    And the developer "JEJE" will still be available for projects