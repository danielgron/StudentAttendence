package StudentAttendance;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fakes.FakeStudentRepository;
import models.Lecture;
import models.Student;
import models.Subject;
import models.Teacher;
import repository.IStudentRepository;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private Student student;
    private Student newStudent;
    private Teacher teacher;
    private Lecture lecture;
    private Subject subject;
    private Date date;
    private List<Student> students = new ArrayList();
    private IStudentRepository repository = new FakeStudentRepository();

    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String city;
    private String zipcode;
    private Date birthdate;

    @Given("^the student is attending the class$")
    public void the_student_is_attending_the_class() throws Exception {
        student = new Student("Benjamin");
        teacher = new Teacher("Lars");
        subject = new Subject("Software Testing", teacher);

        student.addSubject(subject);
        teacher.addSubject(subject);

        date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        subject.addLecture(date);
        lecture = subject.getLecture(date);

    }

    @When("^I fill in the student id$")
    public void i_fill_in_the_student_id() throws Exception {
        lecture.addAttendee(student);
        students = lecture.getAttendees();

    }

    @Then("^the student's attendance the given day should be registered$")
    public void the_student_s_attendance_the_given_day_should_be_registered() throws Exception {
        assertTrue(students.contains(student));
    }


    @Given("^I have a new student submission$")
    public void i_have_a_new_student_submission() throws Exception {
        name = "Yrsa";
        email = "yrsa@school.dk";
        phonenumber = "20304050";
        address = "Standenvejen 1";
        city = "Hellerup";
        zipcode = "2900";
        birthdate = new GregorianCalendar(1995, Calendar.JUNE, 4).getTime();

        newStudent = new Student(name, email, phonenumber, address, city, zipcode, birthdate);

    }

    @When("^I fill in the information$")
    public void i_fill_in_the_information() throws Exception {
        newStudent = repository.save(newStudent);
    }

    @Then("^the student is registred in the system$")
    public void the_student_is_registred_in_the_system() throws Exception {

        Optional<Student> student = repository.findById(newStudent.getId());
        assertTrue(student.isPresent());
    }


    @Given("^I check the attendance system$")
    public void i_check_the_attendance_system() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I fill in the class and date$")
    public void i_fill_in_the_class_and_date() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should see a list of students who was present$")
    public void i_should_see_a_list_of_students_who_was_present() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}
