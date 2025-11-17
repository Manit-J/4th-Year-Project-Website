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

/**
 * Tests the HomePageController by sending HTTP requests to the home page ("/")
 * and verifying the response.
 * Uses MockMvc to simulate requests without starting a real server.
 * Checks that the correct view ("index") is returned and that the web layer loads properly.
 */
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



