package org.example.controllers;

import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/viewAllStudents")
    public String viewAllStudents(Model model) {
        List<Student> allStudents = studentRepository.findAll();
        model.addAttribute("students", allStudents);
        return "viewAllStudents";
    }

    @PostMapping("/studentRegistration")
    @ResponseBody
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @GetMapping("/studentRegistration")
    public String studentRegistration(Model model, HttpSession session) {
        if (session.getAttribute("message") != null) {
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        return "studentRegistration";
    }

    @PostMapping("/studentRegistration/login")
    public String loginStudent(@RequestParam String studentEmailLogin,
                               @RequestParam String studentPasswordLogin,
                               HttpSession session) {

        Student s = studentRepository.findByStudentEmail(studentEmailLogin);

        if (s != null && s.getPassword().equals(studentPasswordLogin)) {
            session.setAttribute("loggedInStudent", s);
            session.setAttribute("message", "Login successful! Welcome " + s.getStudentName());
        } else {
            session.setAttribute("message", "Login failed! Check your email and password.");
        }

        return "redirect:/student/studentRegistration";
    }


}
