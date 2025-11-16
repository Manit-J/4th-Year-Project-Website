package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        assertFalse(project.addStudent(student));
        assertEquals(project.getCapacity(), 4);
        assertFalse(project.getStudent().contains(student));

        student.setDepartment("SYSC");
        assertTrue(project.addStudent(student));
        assertEquals(project.getCapacity(), 3);
        assertTrue(project.getStudent().contains(student));
    }
}