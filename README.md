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
For the next milestone, the team will focus on preparing the alpha release of the system. We will be making sure that the app is functional and provides a meaningful experience for users, even if all planned features are not yet included. The goal is to have several core features working together smoothly, allowing users to perform useful tasks within the app. Additionally, the README on GitHub will be updated to include a clear and detailed plan for the upcoming sprint, outlining the next steps, priorities, and feature implementations to guide further development.

## Database Schema
  **Professor Table:**
      - `professor_id` (INT, PRIMARY KEY)
      - `name` (VARCHAR(...), UNIQUE, NOT NULL)
    

  **Projects Table:**
    - `id` (INT, PRIMARY KEY)
    - `name` (NOTNULL)
    - `capacity` (VARCHAR(...), NOT NULL)
    - `description` (VARCHAR(...))
    - `status` (VARCHAR(...))
    - ’academic_year’(INT)

  **Student Table**
    - 'id' (INT, PRIMARY KEY)
    - 'student_name' (VARCHAR(...), NOT NULL)
    - 'student_email' (VARCHAR(...))
    - 'report_submitted' (BOOLEAN)
    - 'project_id' (INT, FOREIGN KEY references id in Project)


[![Java CI with Maven](https://github.com/Manit-J/4th-Year-Project-Website/actions/workflows/maven.yml/badge.svg)](https://github.com/Manit-J/4th-Year-Project-Website/actions/workflows/maven.yml)
