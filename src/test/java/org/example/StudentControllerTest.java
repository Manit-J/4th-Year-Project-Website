package org.example;


import org.example.Student;
import org.example.controllers.StudentController;
import org.example.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
/**
 * Tests the StudentController by sending HTTP requests to the student page ("/student")
 * and verifying the response.
 * Uses MockMvc to simulate requests without starting a real server.
 * Checks that the correct view ("student") is returned and that the model contains
 * the expected attribute: "students" (list of all students retrieved from the repository).
 */
@WebMvcTest(StudentController.class)// loads the web layer for the student controller without running the whole app
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;//

    @MockBean
    private StudentRepository studentRepository;//replaces spring beans with mock beans


//    @Test
//    void shouldDisplayAllStudents() throws Exception {
//        given(studentRepository.findAll()).willReturn(List.of(new Student("Bob","Bob.carleton@cmail.com")));
//
//        mockMvc.perform(get("/student"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("student"))
//                .andExpect(model().attributeExists("students"));
//    }

}
