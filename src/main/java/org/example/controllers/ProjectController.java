package org.example.controllers;

import org.example.Project;
import org.example.Student;
import org.example.repositories.ProjectRepository;
import org.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final StudentRepository studentRepository;


    private final Map<Long, String> scheduledPresentations = new ConcurrentHashMap<>();

    public ProjectController(ProjectRepository projectRepository, StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }


    // List all projects

    @GetMapping
    public String displayProjects(Model model,
                                  @ModelAttribute("error") String error,
                                  @ModelAttribute("success") String success) {
        model.addAttribute("project", projectRepository.findAll());
        model.addAttribute("newProject", new Project());
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        return "project";  // project.html
    }


    // Add a project

    @PostMapping("/add")
    public String addProject(@ModelAttribute("newProject") Project newProject) {
        projectRepository.save(newProject);
        return "redirect:/project";
    }


    // Delete a project

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/project";
    }


    // View project details

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable Long id, Model model,
                              @ModelAttribute("error") String error,
                              @ModelAttribute("success") String success) {

        return projectRepository.findById(id)
                .map(project -> {
                    model.addAttribute("project", project);
                    model.addAttribute("error", error);
                    model.addAttribute("success", success);

                    // Students in this project
                    List<Student> studentsInProject = studentRepository.findAll().stream()
                            .filter(s -> s.getProject() != null && s.getProject().getId().equals(id))
                            .collect(Collectors.toList());
                    model.addAttribute("studentsInProject", studentsInProject);

                    return "ProjectDetails"; // ProjectDetails.html
                })
                .orElse("redirect:/project");
    }


    // Student joins a project

    @PostMapping("/{id}/join")
    public String joinProject(@PathVariable Long id,
                              @RequestParam String studentName,
                              @RequestParam String studentEmail,
                              RedirectAttributes redirectAttributes) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        long currentCount = studentRepository.findAll().stream()
                .filter(s -> s.getProject() != null && s.getProject().getId().equals(id))
                .count();

        if (currentCount >= project.getCapacity()) {
            redirectAttributes.addFlashAttribute("error", "Project is full — you cannot join.");
            return "redirect:/project/" + id;
        }

        Student student = new Student();
        student.setStudentName(studentName);
        student.setStudentEmail(studentEmail);
        student.setProject(project);
        student.setStudentAvailability(new ArrayList<>());

        studentRepository.save(student);

        redirectAttributes.addFlashAttribute("success", "You have joined the project!");
        return "redirect:/project/" + id;
    }


    // Calendar

    @GetMapping("/{id}/calendar")
    public String showCalendar(@PathVariable Long id, Model model,
                               @ModelAttribute("success") String success) {
        return projectRepository.findById(id)
                .map(project -> {
                    model.addAttribute("project", project);
                    model.addAttribute("scheduledPresentation", scheduledPresentations.get(id));
                    model.addAttribute("success", success);
                    return "calendar";
                })
                .orElse("redirect:/project");
    }

    @PostMapping("/{id}/calendar/save")
    public String saveCalendar(@PathVariable Long id,
                               @RequestParam String presentationDate,
                               @RequestParam String presentationTime,
                               @RequestParam String room,
                               RedirectAttributes redirectAttributes) {

        String schedule = presentationDate + " " + presentationTime + " in Room " + room;
        scheduledPresentations.put(id, schedule);
        redirectAttributes.addFlashAttribute("success", "Oral presentation scheduled successfully!");
        return "redirect:/project/" + id + "/calendar";
    }


    // Availability

    @GetMapping("/{id}/availability")
    public String showAvailability(@PathVariable Long id, Model model) {
        return projectRepository.findById(id)
                .map(project -> {
                    model.addAttribute("project", project);

                    // Students in this project
                    List<Student> studentsInProject = studentRepository.findAll().stream()
                            .filter(s -> s.getProject() != null && s.getProject().getId().equals(id))
                            .peek(s -> {
                                if (s.getStudentAvailability() == null) {
                                    s.setStudentAvailability(List.of());
                                }
                            })
                            .toList();

                    model.addAttribute("participants", studentsInProject);
                    return "enter-availability";
                })
                .orElse("redirect:/project");
    }

    @PostMapping("/{id}/availability/save")
    public String saveAvailability(@PathVariable Long id,
                                   @RequestParam Map<String, String> formData,
                                   RedirectAttributes redirectAttributes) {
        try {
            List<Student> studentsInProject = studentRepository.findAll().stream()
                    .filter(s -> s.getProject() != null && s.getProject().getId().equals(id))
                    .peek(s -> {
                        if (s.getStudentAvailability() == null) {
                            s.setStudentAvailability(List.of());
                        }
                    })
                    .toList();

            for (int i = 0; i < studentsInProject.size(); i++) {
                String key = "availability_" + i;
                if (formData.containsKey(key)) {
                    String value = formData.get(key);
                    List<String> availabilityList = (value == null || value.trim().isEmpty())
                            ? List.of()
                            : Arrays.stream(value.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .toList();
                    studentsInProject.get(i).setStudentAvailability(availabilityList);
                    studentRepository.save(studentsInProject.get(i));
                }
            }

            redirectAttributes.addFlashAttribute("success", "Availability saved successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving availability: " + e.getMessage());
        }

        return "redirect:/project/" + id + "/availability";
    }
}