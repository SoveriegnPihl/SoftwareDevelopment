Feature: An employee  registers sick-day or holiday
  Description: An employee registers that they have a sick-day or that they leave on holiday
  Actors: User
  Scenario: User registers a sick-day
    Given that there is a developer with initials "vic7"
    And the employee "JEJE" exists in the system
    When the employee "JEJE" registers a sick-day
    Then employee "JEJE" is not available for any projects for the next 24 hours

  Scenario: User registers a holiday
    Given there is an employee "JEJE"
    And the employee "JEJE" exists in the system
    When the employee "JEJE" registers a holiday from 25/03/2022 to 01/04/2022
    Then the employee "JEJE"'s status in the system will be that he is on holiday
    And the employee "JEJE" will not be available for any projects between 25/03/2022 and 01/04/2022

  Scenario: User registers a holiday but has had more than 4 weeks of holiday already
    Given there is an employee "JEJE"
    And the employee "JEJE" exists in the system
    And the employee "JEJE" already has 4 weeks of holiday registered
    Then a message "JEJE, you do not have any more available holiday left" is displayed
    And the employee "JEJE"'s status in the system will be that he is not on holiday
    And the employee "JEJE" will still be available for projects