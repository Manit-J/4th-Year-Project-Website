# SYSC-4806 4th Year Project Website
A website showing 4th Year Projects available.

## Description

The purpose of this website is to be able to view the list of projects available for students to join. The projects will have descriptions detailing the title, professor supervising, and department it is being offered under. 

## Authors
* Sandy Alzabadani
* Zeena Ford
* Raiqah Islam
* Manit Jawa
* Zeina Mouhtadi
  
## Current State of the Project

Currently,the project has foundational components of the system in place. The core entities of **Student**, **Professor**, and **Project** along with their repositories, have been implemented and integrated with the database. The web layer using Spring Boot and Thymeleaf is also set up, which allows users to perform basic operations such as viewing, adding, and removing professors, students, and projects.

The application supports essential CRUD functionality for these entities through simple, functional web interfaces. The existing HTML templates provide basic data display and input capabilities, though they are minimal in design.

## Work Done this Milestone

1. Student was implemented
   * Student class that has attributes such as name, id, email, and functions such as setting/getting names, id, email, project, etc
2. Professor was implemented 
   * Professor class that has attributes such as name, id, and functions to add and view projects, add/remove students from projects
3. Project was implemented
   * Project class has attributes that describe the project and functions associated with setting/getting those details
4. Professor Controllers and Templates implemented 
   * The ProfessorWebController handles web requests about professors in a Spring Boot app.
   * It gets professor data from the database and shows it on web pages using Thymeleaf.
   * It lets users add new professors and delete existing ones through simple web forms and URLs.
5. Project Controller and Templates implemented
   * This controller manages web requests related to projects under the /project URL.
   * It displays all projects, lets users add new projects, delete projects, and view project details.
   * Data is fetched from the database using ProjectRepository and shown on Thymeleaf pages like project.html and projectDetails.html
6. Student Controller and Templates implemented
   * This controller handles requests related to students, like viewing one student by ID or listing all students.
   * It fetches student data from the StudentRepository and adds it to the model for Thymeleaf views.
   * Depending on the request, it shows either a detailed student page or a list of all students (student.html).
  
## Work for next Milestone
For the next milestone, the team will focus on preparing the alpha release of the system. We will ensure that the app is functional and provides a meaningful experience for users, even if all planned features are not yet implemented. The goal is to have several core features working together smoothly, allowing users to perform useful tasks within the app. 

Looking ahead, the team plans to enhance the user experience by improving the HTML templates to be more user-friendly and visually appealing. Key upcoming features include:

- Linking projects with professors and students to better manage relationships between entities.
- Expanding project attributes and statuses to accurately represent academic projects.
- Adding more interactive webpages for tasks such as assigning students to projects, viewing supervisors, and tracking student involvement.
- Introducing search and filtering capabilities to help users find relevant projects or professors efficiently.

These enhancements will help ensure that the application supports meaningful workflows, providing a valuable tool for both students and faculty.

Additionally, the README on GitHub will be updated to include a clear and detailed plan for the upcoming sprint, outlining the next steps, priorities, and feature implementations to guide further development.

## Class Diagram

<img width="2716" height="4340" alt="uml diagram" src="https://github.com/user-attachments/assets/ee9564f0-d515-41c6-b4ed-cba437ea69cc" />

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
