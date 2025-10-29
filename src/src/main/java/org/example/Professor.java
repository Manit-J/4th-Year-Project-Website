package org.example;
import jakarta.persistence.*;


@Entity
public class Professor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long coordinator_id;
    private String name;

    public Professor(String name, Long id) {
        this.name = name;
        this.coordinator_id = id;
    }

    public Professor() {
        name = "Coordinator";
        coordinator_id = 0L;
    }

    public Long getId() {
        return coordinator_id;
    }

    public void setId(Long id) {
        this.coordinator_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /***
    public void addStudent(Student student){
        project.add(student);
    }
    public void removeStudent(Student student){
        project.remove(student);
    }
    public void viewStudents(){

    }
     **/
}
