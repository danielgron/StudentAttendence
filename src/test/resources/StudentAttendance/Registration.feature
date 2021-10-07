Feature: Registration
As a school

  Scenario: Register a student
    Given I have a new student submission
    When I fill in the information
    Then the student is registred in the system