Feature: Book a developer to a project
  Description: Book a available developer to project
  Actors: procject manager

  Scenario: Book developer
    Given that there is a Developer with initials "ekki"
    And that the developer is a procject manager
    And there is a project with id "22001"
    And the developer with initials "vic7" is available
    When the procject manager books the developer
    Then the developer is added to the project

  Scenario: Developer is not available
    Given that there is a Developer with initials "ekki"
    And that the developer is a procject manager
    And there is a project with id "22001"
    And the developer with initials "vic7" is not available
    When the procject manager books the developer
    Then the error message "Developer is not available for this projects time frame" is given

  Scenario: Developer is not a project manager
    Given that there is a Developer with initials "ekki"
    And that the developer is not a procject manager
    Then the error message "Developer is not a project manager" is given