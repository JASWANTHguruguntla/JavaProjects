# 🎓 Student Management System (JavaFX + MySQL)

This is a simple **CRUD**-based Student Management System built using **JavaFX** for the UI and **MySQL** for backend storage. It allows users to:

- Insert student records
- Fetch and display all students
- Store course and enrollment data (database-ready)

---

## 📁 Project Structure

project-root/ 

├── FXDatabaseInsert.java # Main JavaFX application file

├── README.md # Project documentation

└── database.sql # SQL file to create required tables

---

## 💻 Technologies Used

- **Java 17+**
- **JavaFX**
- **MySQL**
- **JDBC (Java Database Connectivity)**

---

## 🚀 Features

- 📥 Insert student records (ID, Name, Email)
- 📄 Fetch and display student data from MySQL
- 🛠️ Scalable structure for future enhancements like:
  - Course management
  - Enrollment mapping
  - Search & update functionality

---

## 🏗️ Database Setup

1. Start your MySQL server.
2. Open your MySQL client (like MySQL Workbench or CLI).
3. Run the following SQL code to create the database and tables:

```sql
CREATE DATABASE stdinf;
USE stdinf;

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
```
## 🧪 How to Run the App
✅ Prerequisites
. Java JDK 17 or above

. JavaFX SDK installed

. MySQL installed and running

. IDE (VS Code / IntelliJ / Eclipse)

▶️Run Instructions
1. Import FXDatabaseInsert.java into your Java project.
2. Add JavaFX library/module to your project setup.
3. Ensure MySQL server is running on:
Host: localhost
Port: 3306
Username: root
Password: root
Database: stdinf
4.Run the FXDatabaseInsert class.

📌 Notes
The current UI supports only inserting and fetching student records.

Course and enrollment tables are ready for further development.

UI can be enhanced with dropdowns, validation, and update/delete functionality.
