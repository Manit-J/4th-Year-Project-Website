package org.example.controllers;

import org.example.Student;
import org.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    private final StudentRepository studentRepository;

    @Autowired
    public CoordinatorController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //view students without a project
    @GetMapping("/students-without-project")
    public String viewStudentsWithoutProject(Model model) {
        List<Student> noProject = studentRepository.findByProjectIsNull();
        model.addAttribute("students", noProject);
        return "students_without_project";
    }

    //send reminders to students
    @PostMapping("/send-reminders")
    public String sendReminders() {
        List<Student> noProject = studentRepository.findByProjectIsNull();

        noProject.forEach(student ->
                System.out.println("Reminder sent to: " + student.getStudentEmail())
        );

        return "redirect:/coordinator/students-without-project";
    }

}
