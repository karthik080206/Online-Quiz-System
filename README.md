# Online-Quiz-System

Project Description

Online Quiz System is a Java-based application developed to conduct and manage online quizzes efficiently.

The system allows users to participate in quizzes, answer questions, and view their results. The application uses JDBC for database connectivity and follows a Layered Architecture for better organization and maintainability.

Features

- User Registration
- User Login
- Display Quiz Questions
- Submit Answers
- Automatic Score Calculation
- View Quiz Results
- Question Management
- Database Connectivity

Technologies Used

- Java
- JDBC
- MySQL
- Eclipse IDE

Architecture

The project follows a Layered Architecture:

- Model Layer – Represents users, questions, and results.
- Repository Layer – Handles database operations.
- Service Layer – Contains application and business logic.
- Controller Layer – Handles user requests and connects different layers.

Architecture Flow

User
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
JDBC
  ↓
MySQL Database

Database

MySQL is used to store quiz-related information.

Main Tables

- User
- Questions
- Results

How to Run

1. Install Java JDK.
2. Install MySQL.
3. Create the required database and tables.
4. Configure the database username and password.
5. Open the project in Eclipse.
6. Add MySQL Connector/J.
7. Run the main Java class.

Project Outcome

The Online Quiz System provides a simple and efficient platform for conducting quizzes and managing quiz results using Java, JDBC, MySQL, and Layered Architecture.

Author

Gayathri
