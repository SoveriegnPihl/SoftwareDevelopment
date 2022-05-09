## Author: Victor Larsen-Saldeen

Feature: Assign project manager
  Description: A project manager gets assigned by a developer
  Actors: Developer

  Scenario: Assign project manager to a project
    Given that there is a developer with initials "vicc"
    And There is a project with id "22003"
    When the developer assigns "vicc" to "22003"
    Then the project manager "vicc" is assigned to the project

  Scenario: Assign project manager to a project when no developer with such initials
    Given that there isn't a developer with initials "vigo"
    And There is a project with id "22001"
    When the developer assigns "vigo" to "22001"
    Then "vigo" is not assigned as project manager to the project

  Scenario: Assign project manager that is already assigned
    Given that there is a developer with initials "ekki"
    And There is a project with id "22001"
    When the developer assigns "ekki" to "22001"
    Then "ekki" is still project manager

  Scenario: Assign project manager to a non-exiting project
    Given that there is a developer with initials "ekki"
    And There is not a project with id "20000"
    When the developer tries to assigns the project manager with initials "ekki"
    Then no project manager assigned to the project
