## @author Jakob Hansen

Feature: Get report for a project
  Description: Get the report for a project
  Actors: Procject manager

  Scenario: Get project report
    Given that there is a manager with initials "ekki"
    And that the developer is a project manager
    And there is a project with id "22001"
    Then the project manager is able to get time and budget used
    And a report is created for the project
    And the report contains all relevant information

  Scenario: project is not found
    Given that there is a manager with initials "ekki"
    And that the developer is a project manager
    And there is not a project named "22000"
    Then the error message "Project not found" is given