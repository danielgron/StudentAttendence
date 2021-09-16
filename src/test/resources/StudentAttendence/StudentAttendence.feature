Feature: StudentAttendence
As a teacher

  Scenario: Register a student's attendence in class
    Given the student is attending the class
    When I fill in the student id
    Then the student's attendence the given day should be registered

  Scenario: View student attendence
    Given I check the attendence system
    When I fill in the class and date
    Then I should see a list of students who was present