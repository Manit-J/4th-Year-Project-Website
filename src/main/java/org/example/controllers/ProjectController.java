package org.example.controllers;

import org.example.Professor;
import org.example.Project;
import org.example.repositories.ProfessorRepository;
import org.example.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//handles requests for /project
// RequestMapping maps all HTTP operations
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private final ProjectRepository projectRepository;
    private final ProfessorRepository professorRepository;

    public ProjectController(ProjectRepository projectRepository, ProfessorRepository prepository) {
        this.projectRepository = projectRepository;
        this.professorRepository = prepository;
    }

    /***
     //Display Projects
     //Handle Get request to /project
     @GetMapping public String displayProjects(Model model) {
     //load all projects from the database and add them to the model
     model.addAttribute("project", projectRepository.findAll());
     model.addAttribute("newProject", new Project()); // for the form
     //return project.html view
     return "project";
     }***/

    @GetMapping
    public String displayProjects(Model model, @RequestParam(value = "professorId", required = false) Long professorId) {
        model.addAttribute("project", projectRepository.findAll());
        model.addAttribute("newProject", new Project());
        model.addAttribute("professorId", professorId);
        return "project";
    }

    //Add a project

    /**
     * @PostMapping("/add") public String addProject(@ModelAttribute("newProject") Project newProject) {
     * projectRepository.save(newProject);
     * return "redirect:/project";
     * }
     **/

    @PostMapping("/add")
    public String addProject(@ModelAttribute("newProject") Project newProject,
                             @RequestParam(value = "professorId", required = false) Long professorId) {
        if (professorId != null) {
            Professor professor = professorRepository.findById(professorId).orElse(null);
            if (professor != null) {
                professor.addProject(newProject);
                professorRepository.save(professor);
            }
            return "redirect:/project?professorId=" + professorId;
        } else {
            projectRepository.save(newProject);
            return "redirect:/project";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/project";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        return projectRepository.findById(id)
                .map(project -> {
                    model.addAttribute("project", project);
                    return "ProjectDetails";
                })
                .orElse("redirect:/project");
    }

    @GetMapping("/new")
    public String showProjectForm(@RequestParam("professorId") Long professorId, Model model) {
        Project project = new Project();
        model.addAttribute("newProject", project);
        model.addAttribute("professorId", professorId);
        return "project";
    }


    @PostMapping("/save")
    public String saveProject(@ModelAttribute("newProject") Project project, @RequestParam("professorId") Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        if (professor != null) {
            project.getProfessor().add(professor);
            projectRepository.save(project);
        }
        return "redirect:/professors";
    }

}



