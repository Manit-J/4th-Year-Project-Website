package org.example.controllers;

import org.example.Student;
import org.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            model.addAttribute("error", "student not found");
            return "error"; // optional error page
        }

        model.addAttribute("student", student);
        return "addressBookView"; // the Thymeleaf template name
    }

    @GetMapping("/student")
    public String allStudents(Model model) {
        List<Student> allStudents = studentRepository.findAll();
        model.addAttribute("students", allStudents);
        return "student";
    }

}