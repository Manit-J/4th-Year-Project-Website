package org.example;

import org.example.controllers.ProfessorController;
import org.example.controllers.ProjectController;
import org.example.repositories.ProfessorRepository;

import org.example.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
/**
 * Tests the ProjectController by sending HTTP requests to the project page ("/project")
 * and verifying the response.
 * Uses MockMvc to simulate requests without starting a real server.
 * Checks that the correct view ("project") is returned and that the model contains
 * the expected attributes: "project" (list of existing projects) and "newProject"
 * (used for creating a new project).
 */
@WebMvcTest(ProjectController.class)//loads the project web controller (only the web layer) without loading the full app
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;//simulate HTTP requests without starting a real web server

    @MockBean
    private ProjectRepository projectRepository;//replaces spring beans with mock beans


    @Test
    void shouldDisplayAllStudents() throws Exception {
        ArrayList<Professor> professors = new ArrayList<Professor>();
        ArrayList<Student> students = new ArrayList<Student>();

        given(projectRepository.findAll()).willReturn(List.of(new Project(1L, "Shopify", 4, "online shopping platform", "software engineering", "available", "strong programming skills", 4, students, professors)));

        mockMvc.perform(get("/project"))
                .andExpect(status().isOk())
                .andExpect(view().name("project"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attributeExists("newProject"));
    }
}
