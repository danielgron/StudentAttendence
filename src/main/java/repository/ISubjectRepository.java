package repository;

import models.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISubjectRepository {
    Subject save(Subject subject);

    Optional<Subject> findById(UUID subjectId);

    void deleteAll();

    List<Subject> findAll();
}
