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

    private final ProjectRepository projectRepository;

    @Autowired
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

    @PostMapping("/delete")
    public String deleteProject(@RequestParam Long id) {
        projectRepository.deleteById(id);
        return "redirect:/project";
    }
    @PostMapping("/archive/{id}")
    public String archiveProject(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setArchived(true);
            projectRepository.save(project);
        }
        return "redirect:/project";
    }
    @GetMapping("/archived")
    public String viewArchived(Model model) {
        model.addAttribute("project", projectRepository.findByArchivedTrue());
        return "archivedProjects";
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
