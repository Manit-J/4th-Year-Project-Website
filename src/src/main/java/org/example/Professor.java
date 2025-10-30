package org.example;
import jakarta.persistence.*;

import java.util.ArrayList;


@Entity
public class Professor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long professor_id;
    private String name;
 //   private ArrayList<Project> listOfProjects;

    public Professor(String name, Long id) {
        this.name = name;
        this.professor_id = id;
        //listOfProjects = new ArrayList<>();
    }

    public Professor() {
        name = "Professor";
        professor_id = 0L;
    }

    public Long getId() {
        return professor_id;
    }

    public void setId(Long id) {
        this.professor_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /***
    public List<Project> getProjects(){
        return listOfProjects;
    }

    public void addStudent(Student student){
       //implement code to add students to projects
    }
    public void removeStudent(Student student){
     //implement code to remove students to projects

     }
    public void viewStudents(){
     //implement code to view students in projects


     }
     **/
}
