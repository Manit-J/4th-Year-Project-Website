package org.example;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/professors")//This class handles HTTP requests (like GET, POST, PUT, DELETE) and returns data directly to the client
public class ProfessorController {
    private final ProfessorRepository professorRepository;
    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    @GetMapping
    public Iterable<Professor> getAll() {
        return professorRepository.findAll();
    }
    @GetMapping("/search/byName")
    public List<Professor> findByProjectName(@RequestParam String name) {
        return professorRepository.findByListOfProjects_Name(name);

    }
    @GetMapping("/search/byID")
    public List<Professor> findByProjectId(@RequestParam Long projectId) {
        return professorRepository.findByListOfProjects_Id(projectId);
    }
    @PostMapping
    public Professor create(@RequestBody Professor professor) {
        for (Project project: professor.getProjects()) {
            //project.setProfessor(professor);
        }
        return professorRepository.save(professor);
    }

}
