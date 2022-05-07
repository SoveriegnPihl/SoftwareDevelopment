Feature: Check number of worked hours today
    Description: A developer can see how many hours have been reported today
    Actors: Developer
## Author: Victor Larsen-Saldeen
Scenario: Developer requests number of worked hours today
    Given that there is a developer with initials "ekki"
    And there is registered hours
    When the developer requests reported worked hours for today
    Then daily worked hours is given for "vic7"

Scenario: Non-existing developer requests number of worked hours today
    Given that there isn't a developer with initials "hans"
    When the non-developer requests reported worked hours for today
    Then the error message "No developer with such name found" is given

Scenario: Developer requests number of worked hours today when no time is registered
    Given that there is a developer with initials "ekki"
    And there is no registered time
    When the other developer requests reported worked hours for today
    Then the error message "No hours registered for today" is given


