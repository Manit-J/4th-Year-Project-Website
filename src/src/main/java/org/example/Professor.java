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
    private List<Project> listOfProjects; //list of projects they supervise

    /***
     * Constructor
     * @param name prof name
     *        id prof id
     * */
    public Professor(String name, Long id) {
        this.name = name;
        this.professor_id = id;
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

    public void addStudent(Student student){
       //implement code to add students to projects

    }
    /**
     * Remove students to projects
     * @param student
     * */
    public void removeStudent(Student student){
     //implement code to remove students to projects

     }
    /**
     * View students in projects
     *
     * */
    public void viewStudents(){
        for (Project p: listOfProjects){
            System.out.println(p.getStudent().toString());
        }

     }


}
