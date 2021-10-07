package repository;

import models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import service.StudentEntityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*@Configuration
@ComponentScan*/
public class StudentRepository implements IStudentRepository {

    @Autowired
    StudentEntityService service;

    @Override
    public Student save(Student newStudent) {
       return service.save(newStudent);
    }

    @Override
    public Optional<Student> findById(UUID id) {
        /*return service.findById(id);*/
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return service.findAll();
    }
}
