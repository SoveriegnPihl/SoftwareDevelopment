Feature: A developer  registers sick-day or holiday
  Description: A developer registers that they have a sick-day or that they leave on holiday
  Actors: User
  Scenario: User registers a sick-day
    Given that there is a developer with initials "ekki"
    When the developer "ekki" registers a sick-day
    Then developer "ekki" is not available for any projects for the next 24 hours

  Scenario: User registers a holiday
    Given that there is a developer with initials "ekki"
    When the developer "ekki" registers a holiday from 25/03/2022 to 01/04/2022
    Then the developer "ekki" will be unavailable for work
    And the developer "ekki" will not be available for any projects between 25/03/2022 and 01/04/2022

  Scenario: User registers a holiday but has had more than 4 weeks of holiday already
    Given that there is a developer with initials "ekki"
    And the developer "ekki" already has 4 weeks of holiday registered
    Then a message "ekki, you do not have any more available holiday left" is displayed
    And the developer "ekki"'s status in the system will be that he is not on holiday
    And the developer "ekki" will still be available for projects