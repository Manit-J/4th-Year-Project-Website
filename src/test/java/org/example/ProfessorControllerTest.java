package org.example;

import org.example.controllers.ProfessorController;
import org.example.controllers.ProfessorWebController;
import org.example.repositories.ProfessorRepository;

import org.example.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
/**
 * Web layer test for ProfessorWebController.
 * Sends HTTP requests and verifies the results contain the relevant model attributes:
 * - "professors" : list of all professors
 * - "professor"  : a single professor object for the form
 *
 * This is a Spring MVC test using MockMvc, which loads the professor web controller without start a real server.
 */
@WebMvcTest(ProfessorWebController.class)
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;//simulate HTTP requests without starting a real web server

    @MockBean
    private ProfessorRepository professorRepository;//replaces spring beans with mock beans

    @MockBean
    private ProjectRepository projectRepository;

    @Test
    void shouldDisplayAllStudents() throws Exception {
        given(professorRepository.findAll())
                .willReturn(List.of(new Professor()));

        mockMvc.perform(get("/professors"))
                .andExpect(status().isOk())// Expect HTTP 200 OK status
                .andExpect(view().name("professors"))// Expect the view returned to be named "professors"
                .andExpect(model().attributeExists("professors"))// Expect the model to contain an attribute "professors" (list of all professors)
                .andExpect(model().attributeExists("professor"));// Expect the model to contain an attribute "professor" when creating a new professor
    }
}
