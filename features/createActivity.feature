##Victor Eyde

Feature: Create activity
  Description: Create a activity in a project
  Actors: project manager/developer

  Scenario: Create activities
    Given that there is a developer with initials "ekki"
    And there is a project with id "22001"
    When the developer wants to add an activity "troubleshooting" with 10 hours estimated starting the 6 - 1 - 2022, finishing the 13 - 1 - 2022
    And the activity is not already in the project
    Then the activity is added to the given project

  Scenario: There has already been created an activity with same name
    Given that there is a developer with initials "ekki"
    And there is a project with id "22001"
    When the developer wants to add an activity "Distribute tasks" with 5 hours estimated starting the 6 - 5 - 2022, finishing the 14 - 5 - 2022
    And the activity is already in the project
    Then the error message "Activity is already in this project" is given

  Scenario: No project with such name
    Given that there is a developer with initials "ekki"
    And there is not a project named "22000"
    Then the error message "Activity is already in this project" is given

  Scenario: End date before start date
    Given that there is a developer with initials "ekki"
    And there is a project with id "22001"
    When the developer wants to add an activity "Distribute tasks" with 5 hours estimated starting the 15 - 6 - 2022, finishing the 10 - 6 - 2022
    And the activity is already in the project
    Then the error message "End date before start date" is given



