package org.example;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/***
 * Class Professor represents Professors that are coordinating 4th year projects
 * ***/

@Entity
public class Professor {

    @Id //Primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long professor_id;
    private String name; //professor name
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_professor",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id",
                    referencedColumnName = "professor_id"))
    private List<Project> listOfProjects; //list of projects they supervise

    /***
     * Constructor
     * @param name prof name
     *        id prof id
     * */
    public Professor(String name) {
        this.name = name;
        listOfProjects = new ArrayList<>();
    }

    /**
     * Default constructor
     * */
    public Professor() {
        name = "Professor";
        professor_id = 0L;
    }

    /***
     * Get professor's id
     * @return Long
     * */
    public Long getId() {
        return professor_id;
    }
    /***
     * Set professor's id
     * @param id
     * */
    public void setId(Long id) {
        this.professor_id = id;
    }

    /***
     * Get professor's name
     * @return String of name
     * */
    public String getName() {
        return name;
    }
    /***
     * Get professor's name
     * @param name
     * */

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "ID:" + professor_id + " Name: " + name;
    }

    /**
     * Allows prof to add project they want to supervise
     * @param project
     * */
    public void addProject(Project project){
        if (project.getProfessor().contains(this)) {
            listOfProjects.add(project);
        }
        else{
            System.out.println(name + " not supervising this project");
        }
    }

    /**
     * Return list of projects professor is supervising
     * @return List
     * */

    public List<Project> getProjects(){
        return listOfProjects;

    }
    /**
     * Add students to projects
     * @param student
     * */

    public void addStudent(Project project, Student student) {
        if (listOfProjects.contains(project)) {
            project.getStudent().add(student);
        } else {
            System.out.println("Professor " + name + " does not supervise this project.");
        }
    }
    /**
     * Remove students to projects
     * @param student
     * */
    public void removeStudent(Project project, Student student) {
        if (listOfProjects.contains(project)) {
            project.getStudent().remove(student);
        } else {
            System.out.println("Professor " + name + " does not supervise this project.");
        }
    }
    /**
     * View students in projects
     *
     * */
    public List<Student> viewStudents(){
        List<Student> students = new ArrayList<>();
        for (Project p: listOfProjects){
            students.addAll(p.getStudent());
        }
        return students;

     }


}
