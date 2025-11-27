package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


//Project will be marked as a database table
@Entity
public class Project {

    @Id //Primary key
    @GeneratedValue //Automatically generates the id
    private Long id;

    private String name; //Project name/title
    private Integer capacity; //Maximum number of students that can join
    private String description; //short description of the project
    private String department; //e.g.,Software, Electrical, Mechanical...
    private String status; // Project Availability(e.g.,Available, Full, Completed...)
    private Integer academicYear;
    private String requiredSkills;
    private boolean archived = false;
    private LocalDate deadline;


    /**
     * One Project can have many students
     * CascadeType.aLL means all operations (persist...) apply to related students
     * FetchType.eAGER loads students whenever the project is loaded
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

    public Integer getCapacity() {
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

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
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

    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds student to the project.
     * Checks if the student belongs to the correct department and if there is space in the project.
     *
     * @param student The student to be added
     * @return true if student was added, false otherwise
     */
    public boolean addStudent(Student student){
        if (department.contains(student.getDepartment()) && capacity > 0){
            students.add(student);
            capacity--;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void addProfessor(Professor professor) {
        if (!this.professor.contains(professor)) {
            this.professor.add(professor);
            professor.getProjects().add(this);
        }
    }

    public Project(Long id, String name, int capacity, String description, String department, String status, String requiredSkills, int academicYear, List<Student> students, List<Professor> professor, LocalDate deadline) {
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
        this.deadline = deadline;
    }

    //Default constructor to be used by JPA
    public Project() {
        this.professor = new ArrayList<>();
        this.students = new ArrayList<>();
    }

}

