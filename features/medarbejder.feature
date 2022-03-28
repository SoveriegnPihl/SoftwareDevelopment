Feature: Adding an employee to the system
  Description: An employee, with up to 4 initials, is added and registered in the system.
  Actors: User
  Scenario: User creates a non-existent employee
    Given There is an unregistered employee "JEJE"
    And the employee "JEJE" is not registered in the system
    When the employee "JEJE" is created
    Then employee "JEJE" is registered in the system

  Scenario: User creates an employee with more than 4 initials
    Given the employee "Jens Jensen" is created
    And the employee "Jens Jensen" does not exist
    When the employee "Jens Jensen" is created
    Then the employee "Jens Jensen" does not exist
    And an error message "Employees can only have 4 intitials" is displayed

  Scenario: User creates an existing employee
    Given There is an employee "JEJE"
    And the employee "JEJE" exists
    Then the employee "JEJE" is not created
    Then an error message "The employee JEJE already exists in the system" is displayed

  Scenario: User creates an employee with no initials
    Given there is an employee ""
    And "" is created
    Then the employee "" is not created
    Then an error message "Employees must have intitials" is displayed