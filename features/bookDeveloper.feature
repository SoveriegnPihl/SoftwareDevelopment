## @author Jakob Hansen

Feature: Book a developer to a project
  Description: Book a available developer to project
  Actors: project manager

  Scenario: Book developer
    Given that there is a manager and a Developer with initials "ekki" and "vic7"
    And that the developer is a project manager
    Then The developer is a project manager
    And there is a project with id "22001"
    And the developer is available for the project
    When the project manager books the developer
    Then the developer is added to the project

  Scenario: Developer is not available
    Given that there is a manager and a Developer with initials "ekki" and "vic7"
    And that the developer is a project manager
    And there is a project with id "22001"
    And the developer is not available
    And the error message "Developer is not available for this projects time frame" is given

  Scenario: Developer is not a project manager
    Given that there is a manager and a Developer with initials "ekki" and "vic7"
    And that the developer is not a project manager
    Then the error message "Developer is not a project manager" is given