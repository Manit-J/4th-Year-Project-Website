package org.example.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profLogin")
public class ProfLoginController {

    @Autowired
    ProfService profService;

    //show the login page
    @GetMapping
    public String showLoginPage() {
        return "profLogin";
    }

    //handle login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (profService.authenticate(username, password)) {

            //store login in session
            session.setAttribute("profLoggedIn", true);

            //if login success go to professors page
            return "redirect:/professors";
        }

        model.addAttribute("error", "Invalid username or password");
        return "profLogin"; //stay on login page if username/password incorrect
    }
}
