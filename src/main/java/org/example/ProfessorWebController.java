package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professors")
public class ProfessorWebController {

    private final ProfessorRepository professorRepository;
    private final ProjectRepository projectRepository; // if professors choose projects

    public ProfessorWebController(ProfessorRepository professorRepository, ProjectRepository projectRepository) {
        this.professorRepository = professorRepository;
        this.projectRepository = projectRepository;
    }

    //View all professors
    @GetMapping
    public String viewAllProfessors(Model model) {
        model.addAttribute("professors", professorRepository.findAll());
        return "professors"; // templates/professors.html/
    }

    //Show form to add a new professor
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("projects", projectRepository.findAll());
        return "add-professor"; //templates/add-professor.html
    }

    //Add a new professor
    @PostMapping("/add")
    public String addProfessor(@ModelAttribute Professor professor) {
        professorRepository.save(professor);
        return "redirect:/professors"; // redirect to list
    }

    // Delete a professor by ID
    @GetMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
        return "redirect:/professors";
    }
}
