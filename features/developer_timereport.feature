Feature: Check number of worked hours today
    Description: A developer can see how many hours have been reported today
    Actors: Developer

Scenario: Developer requests number of worked hours today
    Given that there is a developer with initials "ekki"
    And there is registered hours for the date "14-03-2022" / today
    When the developer requests reported worked hours for today
    Then daily worked hours is given

Scenario: Non-existing developer requests number of worked hours today
    Given that there isn't a developer with initials "ekki"
    When the developer requests reported worked hours for today
    Then the error message "No developer with such name found" is given

Scenario: Developer requests number of worked hours today when no time is registered
    Given that there is a developer with initials "ekki"
    And there is no registered time
    When the developer requests reported worked hours for today
    Then the error message "No hours registered for today" is given

Scenario: Developer hours request but no hours reported for today
    Given that there is a developer with initials "ekki"
    And there is registered hours for the date "14-03-2022" / today
    And the hours registered is zero
    When the developer requests reported worked hours for today
    Then Then the error message "Zero hours registered for today" is given

