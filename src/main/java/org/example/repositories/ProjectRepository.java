package org.example.repositories;

import org.example.Project;
import org.springframework.data.repository.CrudRepository;


public interface ProjectRepository extends CrudRepository<Project,Long>
{ }