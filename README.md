# SYSC-4806 4th Year Project Website
A website showing 4th Year Projects available.

## Description

The purpose of this website is to be able to view the list of projects available for students to join. The projects will have descriptions detailing the title, professor supervising, and department it is being offered under. 

The website can be found here: project-11-sysc-4th-year-project-website-c6g3cjdpb4djffeq.canadacentral-01.azurewebsites.net

## Authors
* Sandy Alzabadani
* Zeena Ford
* Raiqah Islam
* Manit Jawa
* Zeina Mouhtadi

## Instructions to Run the Project
1. Clone the repo in IntellJ.
2. Go to maven in right hand side bar or click on view->tool windows->maven.
3. Double click on Fourth Year Project Website->lifecycle->clean
4. Then, double click on install in the same window.
5. Go to target folder, find app.jar and then run it.

## Alternative Way to Run the Project (Using terminal)
1. Clone the repo in IntelliJ.
2. Go to terminal and enter the command `mvn clean install`
3. Go to target folder, find app.jar and then run it.
  
## Current State of the Project

The project has numerous functional components in place that make the product usable and useful. The core entities of **Student**, **Professor**, **Project** and **Coordinator** along with their repositories, have been implemented and integrated with the database to enable the user interface of the system. The web layer using Spring Boot and Thymeleaf is also set up, which allows users to perform basic operations such as viewing, adding, and removing professors, students, and projects. 

The application supports essential CRUD functionality for these entities through simple, functional web interfaces. The existing HTML templates provide basic data display and input capabilities, though they are minimal in design.

## Work Done this Milestone

1. New Feature - Student Logins and Templates updated
   * Student logins was added, and templates for Student were updated.
2. New Feature - Professor Logins
   * ProfLogin class with attributes such as username and password and corresponding setter/getter methods were added.
   * Template for Professor Login was added.
3. Student Template updated
   * Student template was updated to enhance UI.
4. Professor Template updated
   * Professor template was updated to look more attractive and to enable better usability with the Bootstrap framework.
   * Projects can be added directly from professor's page to view their projects in a table.
5. Professor Controller updated
   * Professor Controller was updated to work with the login controller and make sure that the professor page appears after login is successful.
6. Project Status Updated
   * Project status is updated to automatically display a full project when it reaches maximum capacity. 
7. Student Binding Issue fixed
   * Student binding issue was fixed to list the name, id and email for the student, to remove undefined values.
8. Oral Presentation Template updated
   * UI added to calendar template that displays the scheduled project by the project coordinator.
9. New Feature - Upload Button
   * PDF upload button was made to student reports in PDF form on the project page before the deadline, and to not be accepted after the deadline.

  

## Class Diagram

<img width="1170" height="769" alt="image" src="https://github.com/user-attachments/assets/62db8412-cc29-473a-aeeb-0febebea4faa" />

## Database Schema

<img width="1032" height="1648" alt="image2" src="https://github.com/user-attachments/assets/dd384f44-0d4f-4223-ab37-5dbef85c6e5d" />


  **Professor Table:**
      - `professor_id` (INT, PRIMARY KEY)
      - `name` (VARCHAR(...), UNIQUE, NOT NULL)
      <img width="764" height="263" alt="image" src="https://github.com/user-attachments/assets/2d4834b0-e2cf-44ab-9703-861321e40dec" />

    
  **Projects Table:**
    - `id` (INT, PRIMARY KEY)
    - `name` (NOTNULL)
    - `capacity` (VARCHAR(...), NOT NULL)
    - `description` (VARCHAR(...))
    - `status` (VARCHAR(...))
    - `academic_year` (INT)
<img width="1006" height="388" alt="image" src="https://github.com/user-attachments/assets/3c17d78c-0bd0-458b-8d20-cf28b4b8aae0" />

  **Student Table**
    - `id` (INT, PRIMARY KEY)
    - `student_name` (VARCHAR(...), NOT NULL)
    - `student_email` (VARCHAR(...))
    - `report_submitted` (BOOLEAN)
    - `project_id` (INT, FOREIGN KEY references id in Project)
<img width="1047" height="288" alt="image" src="https://github.com/user-attachments/assets/d16d0f5e-a636-4c8b-9c13-e1c2c3924e79" />

[![Java CI with Maven](https://github.com/Manit-J/4th-Year-Project-Website/actions/workflows/maven.yml/badge.svg)](https://github.com/Manit-J/4th-Year-Project-Website/actions/workflows/maven.yml)
