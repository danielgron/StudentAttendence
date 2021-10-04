Feature: StudentAttendance
As a teacher

  Scenario: Register a student's attendance in class
    Given the student is attending the class
    When I fill in the student id
    Then the student's attendance the given day should be registered

  Scenario: View student attendance
    Given I check the attendance system
    When I fill in the class and date
    Then I should see a list of students who was present