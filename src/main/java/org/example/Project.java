package org.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Project will be marked as a database table
@Entity
public class Project {

    @Id //Primary key
    @GeneratedValue //Automatically generates the id
    private Long id;

    private String name; //Project name/title
    private int capacity; //Maximum number of students that can join
    private String description; //short description of the project
    private String department; //e.g.,Software, Electrical, Mechanical...
    private String status; // Project Availability(e.g.,Available, Full, Completed...)
    private int academicYear;

    @ElementCollection // adding this annotation here since JPA doesn't allow lists of strings otherwise (Manit)
    private List<String> requiredSkills = new ArrayList<>();

    /**
     * One Project can have many students
     * CascadeType.ALL means all operations (persist...) apply to related students
     * FetchType.EAGER loads students whenever the project is loaded
     */

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
    public List<Student> students = new ArrayList<>();

    //One project can have multiple professors
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "listOfProjects")
    public List<Professor> professor = new ArrayList<>();

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Professor> getProfessor() {
        return professor;
    }

    public void setProfessor(List<Professor> professor) {
        this.professor = professor;
    }

    public List<Student> getStudent() {
        return students;
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project(Long id, String name, int capacity, String description, String department, String status, List<String> requiredSkills, int academicYear, List<Student> students, List<Professor> professor) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.department = department;
        this.status = status;
        this.requiredSkills = requiredSkills;
        this.academicYear = academicYear;
        this.students = students;
        this.professor = professor;
    }

    //Default constructor to be used by JPA
    public Project() {}

}

