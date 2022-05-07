
#Marcus Pihl




Feature: Creating a new project

  Scenario: Creating a project with a name, start and end date and a budget.
    Given That there is a project name "Test"
    And That there is a start date "26/3-22"
    And There is an end date "1/5-22"
    And There is a budget "10" kr
    Then A new project with name "Test" with start date "26/3-22" and end date "1/5-22" with a budget of "10"kr and id "22001" is made
    And There is a project with id "22001"

  Scenario: Creating a project with a start date that's not valid
    Given: Given That there is a project name "Test"
    And That there is a start date "26/2-22"
    Then The date is not valid
    And the error "Date not valid" is given


  Scenario: Creating a project with a start date that's not valid
  Given: Given That there is a project name "Test"
    And That there is a start date "26/3-22"
    And There is an end date "1/3-22"
    Then The date is not valid
    And the error "Date not valid" is given
