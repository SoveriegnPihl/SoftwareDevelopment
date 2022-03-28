Feature: Create activity

  Scenario: Create activities
    Given that there is a Project manager or a developer with initials "ekki"
    And there is a project named "22001"
    When they need a new activity named "Test"
    Then the system refers to a list of available developers for this activity and asks the creater the expected time for the activity

  Scenario: There has already been created an activity with same name
    Given that there is a Project manager or a developer with initials "ekki"
    And there is a project named "22001"
    When they need a new activity named "Test"
    Then the system writes an error message "An activity with this name has already been created"


