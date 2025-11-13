package org.example;
import org.example.controllers.HomePageController;
import org.junit.jupiter.api.Test; // JUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HomePageController.class) // only loads web layer for HomePageController
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // Ensures Spring MVC layer loads without errors
    }

    @Test
    void shouldReturnHomePage() throws Exception {
        mockMvc.perform(get("/")) // GET request to home page
                .andExpect(status().isOk()) // expect HTTP 200 OK
                .andExpect(view().name("index")); // expect Thymeleaf template "index.html"
    }
}



