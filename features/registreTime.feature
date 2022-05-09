Feature: Register work hours
  Description: A developer registers hours worked on a activity
  Actors: Developer

  Scenario: Developer wants to register hours worked
    Given there is a developer with initials "ekki"
    When the developer registers 2 hours worked on "Prepare project"
    Then the developers hours is added to the total hours worked on the activity

  Scenario: Developer wants to register additional hours
    Given there is a developer with initials "vicc"
    And there is already registered hours
    When the developer registers hours
    Then total hours is hours before plus registered hours

  Scenario: A non-existing developer wants to register work hours
    Given that there is not a developer with initials "Tyl4"
    Then the error message "No developer with such name exists" is given

  Scenario: Developer registers hours worked on a activity in a project they're not assigned to
    Given there is a developer with initials "ekki"
    And there is a project with id "22001"
    And not assigned to selected project
    When the developer registers 3 hours worked on "Collect logs"
    Then the developers hours is added to the total hours worked on the activity


