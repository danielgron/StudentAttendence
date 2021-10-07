package service;

import models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentEntityService extends JpaRepository<Student, UUID> {
}
