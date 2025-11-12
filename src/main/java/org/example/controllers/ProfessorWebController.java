package org.example.controllers;

import org.example.Professor;
import org.example.repositories.ProfessorRepository;
import org.example.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.Project;

@Controller
@RequestMapping("/professors")
public class ProfessorWebController {

    private final ProfessorRepository professorRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProfessorWebController(ProfessorRepository professorRepository, ProjectRepository projectRepository) {
        this.professorRepository = professorRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String viewAllProfessors(Model model) {
        model.addAttribute("professors", professorRepository.findAll());
        model.addAttribute("professor", new Professor()); // for add form
        return "professors"; // templates/professors.html
    }

    @PostMapping("/add")
    public String addProfessor(@ModelAttribute Professor professor) {
        professorRepository.save(professor);
        return "redirect:/professors"; // redirect to list
    }

    @GetMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
        return "redirect:/professors"; // go back to list
    }

    @GetMapping("/new")
    public String showProjectForm(@RequestParam("professorId") Long professorId, Model model) {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        if (professor == null) {
            return "redirect:/professors";
        }

        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("professor", professor);
        return "project";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project, @RequestParam("professorId") Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        if (professor != null) {
            professor.addProject(project);  // helper method sets both sides of the relationship
            professorRepository.save(professor);
        }
        return "redirect:/professors";
    }
}
