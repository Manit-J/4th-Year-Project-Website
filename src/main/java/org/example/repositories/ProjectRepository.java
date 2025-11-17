package org.example.repositories;

import org.example.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProjectRepository extends CrudRepository<Project,Long>
{
    List<Project> findByArchivedTrue();
    List<Project> findByArchivedFalse();
}