#Marcus Pihl

Feature: Check for Developers
  Actor: Project manager
  Description:

  Scenario: Project manager looks up who is available for a project
    Given There is a project with id "22001"
    And There is a project manager
    And There is a list of employees
    Then A list of available employees is made
    And There is a list with available employees
