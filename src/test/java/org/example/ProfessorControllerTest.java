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

@WebMvcTest(ProfessorWebController.class)
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @Test
    void shouldDisplayAllStudents() throws Exception {
        given(professorRepository.findAll())
                .willReturn(List.of(new Professor()));

        mockMvc.perform(get("/professors")) // ✅ matches @RequestMapping("/professors")
                .andExpect(status().isOk())
                .andExpect(view().name("professors"))
                .andExpect(model().attributeExists("professors"))
                .andExpect(model().attributeExists("professor"));
    }
}
