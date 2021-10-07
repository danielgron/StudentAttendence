package repository;

import models.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStudentRepository {
    Student save(Student newStudent);

    Optional<Student> findById(UUID id);

    List<Student> findAll();
}
