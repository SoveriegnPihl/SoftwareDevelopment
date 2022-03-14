Feature: Creating a new project
  Actor: Project manager
  Description:

  Scenario: Project manager looks up who is available for a project
    Given There is a project with name "Test" with start date "26/3-22" and end date "1/5-22" with a budget of "10"kr and id "22001"
    And There is a project manager
    And There is a list of employees
    Then A list of available employees is made
    And There is a list with available employees

  Scenario: