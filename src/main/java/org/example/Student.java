package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private long studentID;   // The student's student ID
    private String studentEmail;  // The student's email

    private boolean reportSubmitted; //

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;  // The project that the student applied for

    @ElementCollection
    private List<String> studentAvailability;  // The student's availability for oral presentations

}
