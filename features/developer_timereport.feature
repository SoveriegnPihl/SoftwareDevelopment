Feature: Check number of worked hours today
    Description: A developer can see how many hours have been reported today
    Actors: Developer
## Author: Victor Larsen-Saldeen
Scenario: Developer requests number of worked hours today
    Given that there is a developer with initials "vic7"
    And there is registered hours
    When the developer requests reported worked hours for today
    Then daily worked hours is given for "vic7"

Scenario: Non-existing developer requests number of worked hours today
    Given that there isn't a developer with initials "hans"
    When "hans" requests reported worked hours for today
    Then hours is not reported for "hans"

Scenario: Developer requests number of worked hours today when no time is registered
    Given that there is a developer with initials "vicc"
    And there is no registered time
    When the other developer requests reported worked hours for today
    Then zero hours is given for "vicc"


