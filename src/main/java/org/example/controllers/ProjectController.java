package org.example.controllers;

import org.example.Professor;
import org.example.Project;
import org.example.Student;
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

    private final ProjectRepository projectRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, ProfessorRepository prepository) {
        this.projectRepository = projectRepository;
        this.professorRepository = prepository;
    }

    //Display Projects
    //Handle Get request to /project
    @GetMapping
    public String displayProjects(Model model, @RequestParam(value = "professorId", required = false) Long professorId) {
        //load all projects from the database and add them to the model
        model.addAttribute("project", projectRepository.findAll());
        model.addAttribute("newProject", new Project()); // for the form
        model.addAttribute("professorId", professorId);
        //return project.html view
        return "project";
    }

    //Add a project
    @PostMapping("/add")
    public String addProject(@ModelAttribute("newProject") Project newProject, @RequestParam(value = "professorId", required = false) Long professorId) {
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

    @GetMapping("/apply/{id}")
    public String viewApplyForm(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id).orElse(null);
        //If project doesn't exist or capacity is full user will be redirected to the project page
        if (project == null || project.getStudent().size() >= project.getCapacity()) {
            return "redirect:/project";
        }
        model.addAttribute("project", project);
        model.addAttribute("student", new Student());
        //return ApplyForm.html
        return "applyForm";
    }

    @PostMapping("/apply/{id}")
    public String applyToProject(@PathVariable Long id, @ModelAttribute Student student) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null && project.getStudent().size() < project.getCapacity()) {
            //link student to the project
            student.setProject(project);
            //Add student to the project's student list
            project.getStudent().add(student);
            projectRepository.save(project);
        }
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