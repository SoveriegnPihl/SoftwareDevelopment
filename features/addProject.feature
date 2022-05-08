
#Marcus Pihl

Feature: Creating a new project

  Scenario: Creating a project with a name, start and end date and a budget.
    Given you want to add a new project starting 26 - 3 - 22 and ending 1 - 5 - 22 with 10 kr budget
    And the end date is after the start date
    Then the new project is added
    And the new project is an existing project

  Scenario: Creating a project with a start date that's not valid
    Given you want to add a new project starting 26 - 3 - 22 and ending 1 - 5 - 22 with 10 kr budget
    And the end date is before the start date
    Then the error message "End date can't be before start date" is given
