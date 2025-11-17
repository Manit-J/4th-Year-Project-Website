package org.example;

import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class StudentTest {

    private Student student;
    private Project project;

    /**
     * Creates a new Student object before each test.
     */
    @Before
    public void setUp() {
        student = new Student();
        project = new Project();
        project.setName("Test Project");
    }

    /**
     * Tests the default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        assertEquals("", student.getStudentName());
        assertEquals("", student.getStudentEmail());
        assertFalse(student.getReportSubmitted());
        assertNotNull(student.getStudentAvailability());
        assertTrue(student.getStudentAvailability().isEmpty());
    }

    /**
     * Tests the constructor with parameters.
     */
    @Test
    public void testParameterizedConstructor() {
        Student s = new Student("Alice", "alice@example.com");

        assertEquals("Alice", s.getStudentName());
        assertEquals("alice@example.com", s.getStudentEmail());
        assertFalse(s.getReportSubmitted());
        assertNotNull(s.getStudentAvailability());
        assertTrue(s.getStudentAvailability().isEmpty());
    }

    /**
     * Tests the setter and getter for the attribute studentName.
     */
    @Test
    public void testSetAndGetStudentName() {
        student.setStudentName("Bob");
        assertEquals("Bob", student.getStudentName());
    }

    /**
     * Tests the setter and getter for the attribute studentEmail.
     */
    @Test
    public void testSetAndGetStudentEmail() {
        student.setStudentEmail("bob@example.com");
        assertEquals("bob@example.com", student.getStudentEmail());
    }

    /**
     * Tests the setter and getter for the attribute studentID.
     */
    @Test
    public void testSetAndGetStudentID() {
        student.setStudentID(12345L);
        assertEquals(12345L, student.getStudentID());
    }

    /**
     * Tests the setter and getter for the attribute isReportSubmitted.
     */
    @Test
    public void testSetAndGetReportSubmitted() {
        student.setReportSubmitted(true);
        assertTrue(student.getReportSubmitted());
    }

    /**
     * Tests the setter and getter for the attribute studentAvailability.
     */
    @Test
    public void testSetAndGetStudentAvailability() {
        List<String> availability = new ArrayList<>();
        availability.add("Monday");
        availability.add("Wednesday");

        student.setStudentAvailability(availability);
        assertEquals(availability, student.getStudentAvailability());
        assertEquals(2, student.getStudentAvailability().size());
    }

    /**
     * Test the setter and getter for the attribute project.
     */
    @Test
    public void testProjectGetterSetter() {
        project = new Project();
        project.setDepartment("SYSC, CIVE, ELEC");
        project.setCapacity(5);

        student.setStudentID(898138024);
        student.setStudentName("Harry Potter");
        student.setDepartment("CIVE");
        student.setProject(project);

        assertEquals(student.getProject(), project);
    }

    /**
     * Tests email reminders.
     */
    @Test
    public void testSendReminder() throws MessagingException {
        Student s = new Student();
        s.setStudentName("Manit");
        s.setStudentEmail("manitjawa@cmail.carleton.ca");
        s.sendReminder();
    }
    /**
     * Tests that toString() correctly combines the
     * student's name and email in the expected format.
     */
    @Test
    public void testToString() {
        student.setStudentName("Charlie");
        student.setStudentEmail("charlie@example.com");

        String expected = "Charlie charlie@example.com";
        assertEquals(expected, student.toString());
    }

    /**
     * Verifies that two references to the same Student object
     * are considered equal.
     */
    @Test
    public void testEquals_SameObject() {
        assertTrue(student.equals(student));
    }

    /**
     * Verifies that comparing a Student to an object of a different
     * class returns false.
     */
    @Test
    public void testEquals_DifferentClass() {
        assertFalse(student.equals("Not a Student"));
    }

    /**
     * Tests that two distinct Student objects with identical
     * field values are considered equal.
     */
    @Test
    public void testEquals_EqualStudents() {
        Student s1 = new Student("Dana", "dana@example.com");
        s1.setStudentID(1L);
        s1.setReportSubmitted(true);
        s1.setStudentAvailability(List.of("Tuesday"));

        Student s2 = new Student("Dana", "dana@example.com");
        s2.setStudentID(1L);
        s2.setReportSubmitted(true);
        s2.setStudentAvailability(List.of("Tuesday"));

        assertEquals(s1, s2);
    }

    /**
     * Tests that two Student objects with different IDs are not equal,
     * even if other fields match.
     */
    @Test
    public void testEquals_DifferentStudents() {
        Student s1 = new Student("Eve", "eve@example.com");
        s1.setStudentID(2L);

        Student s2 = new Student("Eve", "eve@example.com");
        s2.setStudentID(3L); // different ID

        assertNotEquals(s1, s2);
    }
}