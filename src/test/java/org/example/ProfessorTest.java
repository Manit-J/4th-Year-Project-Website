package main.java.org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfessorTest {

    //variables
    Professor professor;
    Project project;
    List<Project> projects = new ArrayList<>();
    Student student1;
    Student student2;


    @Before
    public void setUp() {
        //initialize these objects
        professor = new Professor("John Doe");
        project = new Project();
        student1 = new Student();
        student2 = new Student();
    }

    @Test
    public void testConstructor(){
        //test if name is same
        assertEquals("John Doe", professor.getName());

    }
    @Test
    public void testAddProject(){

        //add a project to professors list
        projects.add(project);
        //assert it is equal to 1
        assertEquals(1, projects.size());

    }

    @Test
    public void testListofProjects(){
        projects.add(project);
        assertEquals(1,projects.size());
    }

    /***
    @Test
    public void testViewStudents() {

        //add students to list
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        //add those students to a project
        project.setStudent(students);

        //add professor to the project
        project.getProfessor().add(professor);

        //add this to professor list of project
        professor.addProject(project);

        //get the list of students in that project
        List<Student> allStudents = professor.viewStudents();

        //assert the size is same and correct students are inside
        assertEquals(2, allStudents.size());
        assertTrue(allStudents.contains(student1));
        assertTrue(allStudents.contains(student2));
    }
***/



}