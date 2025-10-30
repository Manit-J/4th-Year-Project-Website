package SYSC4806.Project.repositories;

import SYSC4806.Project.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long>
{ }