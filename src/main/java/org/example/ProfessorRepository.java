package org.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
//
public interface ProfessorRepository extends CrudRepository<Professor,Long>
{
    List<Professor> findByListOfProjects_Name(String name);
    List<Professor> findByListOfProjects_Id(Long id);

}
