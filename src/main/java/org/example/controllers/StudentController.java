package org.example.controllers;

import org.example.Student;
import org.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String allStudents(Model model) {
        List<Student> allStudents = studentRepository.findAll();
        model.addAttribute("students", allStudents);
        return "student";
    }

    @PostMapping
    @ResponseBody
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
