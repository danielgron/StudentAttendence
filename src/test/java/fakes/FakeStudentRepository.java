package fakes;

import models.Student;
import repository.IStudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeStudentRepository implements IStudentRepository {

    private List<Student> students = new ArrayList();

    @Override
    public Student save(Student newStudent) {
        if(!students.contains(newStudent))
            students.add(newStudent);
        return newStudent;
    }

    @Override
    public Optional<Student> findById(UUID id) {

        Optional<Student> OpStudent = Optional.empty();

        for (Student student: students) {
            if(student.getId().equals(id)) {
                OpStudent = Optional.of(student);
            }
        }
        return OpStudent;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
