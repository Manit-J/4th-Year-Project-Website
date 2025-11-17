package org.example;

import jakarta.persistence.*;
import org.apache.tomcat.util.bcel.Const;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * org.example.Student class represents a student in the 4th Year Project Management System
 */
@Entity
public class Student {
    // Unique identifier for the student
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentID;   // The student's student ID

    private String studentName; // The student's first and last name
    private String studentEmail;  // The student's email
    private boolean reportSubmitted;
    private String department; // The student's department

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;  // The project that the student applied for

    @ElementCollection
    private List<String> studentAvailability;  // The student's availability for oral presentations

    /**
     * Constructs a Student object. No arguments required.
     * Sets default values for all attributes.
     *
     * @return Student
     */
    public Student() {
        studentName = "";
        studentEmail = "";
        reportSubmitted = false;
        studentAvailability = new ArrayList<String>();
        department = "";
    }

    /**
     * Constructs a Student object. Name and email required.
     * Sets default values for all other attributes.
     *
     * @param name  Full name of the student
     * @param email email of the student
     * @return Student
     */
    public Student(String name, String email) {
        studentName = name;
        studentEmail = email;
        reportSubmitted = false;
        studentAvailability = new ArrayList<String>();
        department = "";
    }

    /**
     * Gets the name of the student.
     *
     * @return name of the student
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the name of the student.
     *
     * @param studentName Name of the student
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Gets the student #ID.
     *
     * @return student #ID
     */
    public long getStudentID() {
        return studentID;
    }

    /**
     * Sets the student #ID
     *
     * @param studentID
     */
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    /**
     * Gets the email ID of the student.
     *
     * @return student's email id
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Sets the email ID of the student.
     *
     * @param studentEmail
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Gets report submission status.
     *
     * @return True if report submitted by the student's group, false otherwise.
     */
    public boolean getReportSubmitted() {
        return reportSubmitted;
    }

    /**
     * Sets report submission status.
     *
     * @param reportSubmitted
     */
    public void setReportSubmitted(boolean reportSubmitted) {
        this.reportSubmitted = reportSubmitted;
    }

    /**
     * Gets the student's availability for oral presentation.
     *
     * @return student's availability
     */
    public List<String> getStudentAvailability() {
        return studentAvailability;
    }

    /**
     * Sets the student's availability.
     *
     * @param studentAvailability
     */
    public void setStudentAvailability(List<String> studentAvailability) {
        this.studentAvailability = studentAvailability;
    }

    /**
     * Gets the project that the student applied for.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project that the student applied for.
     *
     * @param project the project to set
     */
    public void setProject(Project project) {
        if (project.addStudent(this)){
            this.project = project;
        }
    }

    /**
     * Gets the department of the student.
     *
     * @return the student's department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the student.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    public void sendReminder() throws MessagingException {
        EmailService emailService = new EmailService();

        emailService.sendEmail(
                studentEmail,
                "Project Registration Reminder",
                "Dear Student,\nPlease register in one of the projects available on the Capstone Project website ASAP.\n" +
                        "Please let me know if you have questions.\n" +
                        "Project Coordinator");
    }

    /**
     * Returns student as a string.
     *
     * @return student as a string.
     */
    @Override
    public String toString() {
        return studentName + " " + studentEmail;
    }

    /**
     * Compares an object with this Student.
     *
     * @param o the object to be compared
     * @return true if o is the same object as this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID &&
                reportSubmitted == student.reportSubmitted &&
                java.util.Objects.equals(studentName, student.studentName) &&
                java.util.Objects.equals(studentEmail, student.studentEmail) &&
                java.util.Objects.equals(studentAvailability, student.studentAvailability) &&
                java.util.Objects.equals(department, student.department);
    }

}
