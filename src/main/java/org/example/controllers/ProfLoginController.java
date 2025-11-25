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

    // Show the login page
    @GetMapping
    public String showLoginPage() {
        return "profLogin";
    }

    // Handle login
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        if (profService.authenticate(username, password)) {

            // store login in session
            session.setAttribute("profLoggedIn", true);

            return "redirect:/professors";
        }

        model.addAttribute("error", "Invalid username or password");
        return "profLogin";
    }
}
