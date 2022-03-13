Feature: Assign project manager
    Description: A project manager gets assigned by a developer
    Actors: Developer

Scenario: Assign project manager to a project
    Given that the developer is logged in
    And  there is a project named "11111"
    When the developer assigns the project manager "MMMM"
    Then the project manager "MMMM" is assigned to the project

Scenario: Assign project manager to a project when not logged in
    Given that the developer is not logged in
    And there is a project named "11111"
    When the developer assigns the project manager "MMMM"
    Then the error message "Developer login required" is given

Scenario: Assign project manager that is already assigned
    Given that another developer is logged in
    And there is a project named "11111"
    And there is a project manager named "Victor"
    When the developer assigns the project manager "MMMM"
    Then the error message "PM already assigned to this project" is given

Scenario: Assign project manager to a non-exiting project
    Given that the developer is logged in
    And there is not a project named "11111"
    When the developer assigns the project manager "MMMM"
    Then the project manager "MMMM" is not assigned to the project
    And the error message "No such project found"

Scenario: Assign project manager whom not exist
    Given that the developer is logged in
    And there is not a project named "11111"
    When the developer assigns the project manager "MMMM"
    Then the project manager "MMMM" is not assigned to the project
    And the error message "No project manager with this name found"