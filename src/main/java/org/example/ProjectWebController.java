package main.java.org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
//handles requests for /project
// RequestMapping maps all HTTP operations
@RequestMapping("/project")
public class ProjectWebController {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectWebController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //Display Projects
    //Handle Get request to /project
    @GetMapping
    public String displayProjects(Model model){
        //load all projects from the database and add them to the model
        model.addAttribute("project",projectRepository.findAll());
        //return project.html view
        return "project";
    }

    //Add a project
    @PostMapping("/add")
    public String addProject(Model model){
        model.addAttribute("newProject", new Project());
        return "project";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id){
        projectRepository.deleteById(id);
        return "project";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id).orElse(null);
        model.addAttribute("project", project);
        return "details";
    }
}
