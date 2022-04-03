Feature: Create activity

  Scenario: Create activities
    Given that there is a Project manager or a developer with initials "ekki"
    And there is a project named "22001"
    When they need a new activity named "Test" with expected work hours "10"
    Then the system refers to a list of available developers for this activity

  Scenario: There has already been created an activity with same name
    Given that there is a Project manager or a developer with initials "ekki"
    And there is a project named "22001"
    When they need a new activity named "Test" with expected work hours "10"
    Then the system writes an error message "An activity with this name already exists"


