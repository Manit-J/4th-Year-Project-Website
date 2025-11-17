package org.example.repositories;

import org.example.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Override
    List<Student> findAll();

    List<Student> findByProjectIsNull();

}