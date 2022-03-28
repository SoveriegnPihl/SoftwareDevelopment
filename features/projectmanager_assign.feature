Feature: Assign project manager
    Description: A project manager gets assigned by a developer
    Actors: Developer

Scenario: Assign project manager to a project
    Given that there is a developer with initials "ekki"
    And  there is a project named "22001"
    When the developer assigns the project manager with initials "huba"
    Then the project manager "MMMM" is assigned to the project

Scenario: Assign project manager to a project when no developer with such initials
    Given that there isn't a developer with initials "ekki"
    And there is a project named "22001"
    When the developer assigns the project manager with initials "huba"
    Then the error message "No developer with such name found" is given

Scenario: Assign project manager that is already assigned
    Given that there is a developer with initials "ahaa"
    And there is a project named "22001"
    And there is a project manager with initials "huba"
    When the developer assigns the project manager with initials "akai"
    Then the error message "PM already assigned to this project" is given

Scenario: Assign project manager to a non-exiting project
    Given that there is a developer with initials "ekki"
    And there is not a project named "22001"
    When the developer assigns the project manager with initials "huba"
    Then the project manager with initials "huba" is not assigned to the project
    And the error message "No such project found"

Scenario: Assign project manager whom not exist
    Given that there is a developer with initials "ekki"
    And there isn't a project named "22001"
    When the developer assigns the project manager with initials "huba"
    Then the project manager with initials "huba" is not assigned to the project
    And the error message "No project manager with this name found"