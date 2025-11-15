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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professor_id;

    private String name; //professor name
    private String email; //prof's email
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_professor",
            joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private List<Project> listOfProjects; //list of projects they supervise

    /***
     * Constructor
     * @param name prof name
     *        id prof id
     * */
    public Professor(String name, String email) {
        this.name = name;
        this.email = email;
        listOfProjects = new ArrayList<>();
    }

    /**
     * Default constructor
     *
     */
    public Professor() {
        name = "";
        professor_id = 0L;
        email = "";
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

    /***
     * Get professor's email
     * @return String of email
     * */
    public String getEmail() {
        return email;
    }
    /***
     * Get professor's name
     * @param name
     * */

    /***
     * Get professor's name
     * @param email
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID:" + professor_id + " Name: " + name;
    }


    /*******
     * Allows prof to add project they want to supervise
     * @param project
     **/
    public void addProject(Project project) {
        if (!listOfProjects.contains(project)) {
            listOfProjects.add(project);
            project.getProfessor().add(this); // keep both sides in sync
        }
    }

    /**
     * Return list of projects professor is supervising
     *
     * @return List
     *
     */

    public List<Project> getProjects() {
        return listOfProjects;


    }
}



