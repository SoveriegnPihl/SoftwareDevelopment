
Feature: Create activity
  Description: Create a activity in a project
  Actors: project manager/developer

  Scenario: Create activities
    Given that there is a developer with initials "ekki"
    And there is a project with id "22001"
    When the developer wants to add a activity "troubleshooting" with 5 hours estimated
    And the activity is not already in the project
    Then the activity is added to the given project
    ##Then the system refers to a list of available developers for this activity and asks the creater the expected time for the activity

  Scenario: There has already been created an activity with same name
    Given that there is a developer with initials "ekki"
    And there is a project with id "22001"
    When the developer wants to add a activity "troubleshooting" with 5 hours estimated
    And the activity is already in the project
    Then the error message "Activity is already in this project" is given

  Scenario: No project with such name
    Given that there is a developer with initials "ekki"
    And there is not a project named "22a01"
    Then the error message "Activity is already in this project" is given

