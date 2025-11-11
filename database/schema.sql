database/schema.sql-- Database Schema for Student Management System
-- MySQL Script

CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- Courses Table
CREATE TABLE IF NOT EXISTS courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    duration VARCHAR(50),
    fee DECIMAL(10, 2),
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Students Table
CREATE TABLE IF NOT EXISTS students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    balance DECIMAL(10, 2) DEFAULT 0.00,
    course_id BIGINT,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE SET NULL
);

-- Payments Table (Optional - for tracking payment history)
CREATE TABLE IF NOT EXISTS payments (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_type VARCHAR(20),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- Sample Data
INSERT INTO courses (course_name, duration, fee, description) VALUES
('Java Full Stack Development', '6 months', 50000.00, 'Complete Java development with Spring and Hibernate'),
('Python Data Science', '4 months', 40000.00, 'Data Science with Python, Pandas, and Machine Learning'),
('Web Development', '3 months', 30000.00, 'HTML, CSS, JavaScript, React');

INSERT INTO students (name, email, phone, balance, course_id, status) VALUES
('John Doe', 'john.doe@example.com', '9876543210', 50000.00, 1, 'ACTIVE'),
('Jane Smith', 'jane.smith@example.com', '9876543211', 40000.00, 2, 'ACTIVE'),
('Mike Johnson', 'mike.j@example.com', '9876543212', 15000.00, 3, 'ACTIVE');
