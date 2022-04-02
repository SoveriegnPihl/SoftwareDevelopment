## @author Jakob Hansen

Feature: Get report for a project
  Description: Get the report for a project
  Actors: Procject manager

  Scenario: Get project report
    Given that there is a Developer with initials "ekki"
    And that the developer is a procject manager
    Then The developer is a project manager
    And there is a project with id "22001"
    When the procject manager requests a report for the project
    Then the procject manager get's the project report

  Scenario: Can't get report on project
    Given that there is a Developer with initials "ekki"
    And that the developer is a procject manager
    And there is not a project with id "22001"
    When the procject manager requests a report for the project
    Then the error message "Project not found" is given