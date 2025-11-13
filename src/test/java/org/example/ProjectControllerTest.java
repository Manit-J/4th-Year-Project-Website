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

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectRepository projectRepository;


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
