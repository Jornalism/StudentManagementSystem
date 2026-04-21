CREATE DATABASE IF NOT EXISTS student_management_system;
USE student_management_system;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'professor', 'student') NOT NULL
);

CREATE TABLE students (
    student_id VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    course VARCHAR(50) NOT NULL,
    section VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL,
    subject_code VARCHAR(20) NOT NULL,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES users(user_id)
);

CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20),
    subject_id INT,
    date DATE NOT NULL,
    status ENUM('present', 'absent', 'tardy') NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

CREATE TABLE grades (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20),
    subject_id INT,
    quiz DECIMAL(5,2) DEFAULT 0,
    exam DECIMAL(5,2) DEFAULT 0,
    assignment DECIMAL(5,2) DEFAULT 0,
    final_grade DECIMAL(5,2) DEFAULT 0,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

INSERT INTO users (username, password, role) 
VALUES ('admin', 'admin123', 'admin');