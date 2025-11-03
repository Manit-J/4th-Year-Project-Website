package org.example.controllers;

import org.example.Project;
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

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //Display Projects
    //Handle Get request to /project
    @GetMapping
    public String displayProjects(Model model) {
        //load all projects from the database and add them to the model
        model.addAttribute("project", projectRepository.findAll());
        model.addAttribute("newProject", new Project()); // for the form
        //return project.html view
        return "project";
    }

    //Add a project
    @PostMapping("/add")
    public String addProject(@ModelAttribute("newProject") Project newProject) {
        projectRepository.save(newProject);
        return "redirect:/project";
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
}
