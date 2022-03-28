Feature: Register work hours

  Scenario: Developer wants to register work hours
    Given that there is a developer with initials "ekki"
    And the developer has finished working for the day
    When they register time spent for the day "14-03-2022" / today
    Then the system updates working hours

  Scenario: A non-existing developer wants to register work hours
    Given that there is a developer with initials "ekki"
    And the developer has finished working for the day
    When they register time spent for the day "14-03-2022" / today
    Then the system writes an error message "No developer with such name exists"

  Scenario: A developer is registering more than 8 work hours for the day
    Given that there is a developer with initials "ekki"
    And the developer has finished working for the day
    When they register time spent for the day "14-03-2022" / today
    Then the system updates working hours and sends a message "Please check number of registered hours"

  Scenario: A developer is registering a negative amount of work hours
    Given that there is a developer with initials "ekki"
    And the developer has finished working for the day
    When they register time spent for the day "14-03-2022" / today
    Then the system writes an error message "Please enter worked hours correctly"


