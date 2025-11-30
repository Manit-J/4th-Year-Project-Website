package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class ProjectTest {

    private Student student;
    private Project project;

    /**
     * Creates a new Student object before each test.
     */
    @Before
    public void setUp() {
        student = new Student();
        project = new Project();
    }

    /**
     * Tests if student from a different department is added to the project or not.
     */
    @Test
    public void testAddStudent1() {
        project.setDepartment("SYSC");
        project.setCapacity(4);

        student.setStudentID(90909090);
        student.setStudentName("Clark Kent");
        student.setDepartment("CIVE");

        // Wrong department → should fail
        assertEquals("Student department does not match project department.", project.addStudent(student));
        assertEquals(4, (long) project.getCapacity());
        assertFalse(project.getStudent().contains(student));

        // Correct department → should succeed
        student.setDepartment("SYSC");
        assertEquals("Successfully registered!", project.addStudent(student));
        assertEquals(4, (long) project.getCapacity()); // still 4
        assertTrue(project.getStudent().contains(student));
    }
}