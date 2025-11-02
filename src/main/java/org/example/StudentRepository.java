package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestResource
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Override
    List<Student> findAll();
}