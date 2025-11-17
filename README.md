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
  
## Current State of the Project

Currently,the project has foundational components of the system in place. The core entities of **Student**, **Professor**, **Project** and **Coordinator** along with their repositories, have been implemented and integrated with the database. The web layer using Spring Boot and Thymeleaf is also set up, which allows users to perform basic operations such as viewing, adding, and removing professors, students, and projects.

The application supports essential CRUD functionality for these entities through simple, functional web interfaces. The existing HTML templates provide basic data display and input capabilities, though they are minimal in design.

## Work Done this Milestone

1. New Feature - Check for Program Restrictions
   * An error is displayed when adding a student to a project from a different department
2. New Feature - Send Reminders to Students
   * Project coordinator sends reminders to students without a project
3. Student Template updated
   * Student template was updated to enhance UI
4. Professor Template updated
    * Professor template was updated to look more attractive and to enable better usability with the Boostrap framework.
5. Coordinator Controller implemented
   * Coordinator controller enables new functionalities, such as allowing professors to view a list of students without a project
6. Web controller and Integration Tests
   * ProjectCrontrollerTest StudentControllerTest, WebMockTest and ProfessorControllerTest were implemented to test each controller
7. Archived Projects template
   * archivedprojects HTML template was made to archive projects in relation to updates from the project class, projectController and projectRepository
8. New Feature - Oral Presentation
   * A new template displays a calender view for each project showing the date and time scheduled by the project coordinator for the oral presentation
9. New Feature - Enter Availability
   * An enter availability is displayed next to each participant and professors for their project
  
## Work for next Milestone
For the next milestone, the team will focus on preparing the Final demo of the system. We will ensure that the app is functional and provides a meaningful experience for users. We will also ensure that the overall product is usable and useful. 

Looking ahead, the team plans to enhance the user experience by improving the HTML templates and controllers to be more user-friendly and visually appealing. Key upcoming features include:

- Fully implementing the coordinator's functionailities to ensure that one professor has coordinator privileges.
- Allocating oral presentations to rooms based on availability.
- Enhancing the UI for the student page to make it more aesthetically appealing and functional.
- Complete any outstanding features.

These enhancements will help ensure that the application supports meaningful workflows, providing a valuable tool for both students and faculty.

Additionally, the README on GitHub will be updated to include a clear and detailed outline the features implemented.

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
