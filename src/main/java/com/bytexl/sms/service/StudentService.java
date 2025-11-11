package com.bytexl.sms.service;

import com.bytexl.sms.dao.CourseDAO;
import com.bytexl.sms.dao.StudentDAO;
import com.bytexl.sms.model.Course;
import com.bytexl.sms.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * StudentService - Service layer for Student operations
 * Demonstrates Dependency Injection and Transaction Management
 */
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    public void addStudent(Student student) {
        studentDAO.save(student);
    }

    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    public void deleteStudent(Long studentId) {
        studentDAO.delete(studentId);
    }

    public Student getStudentById(Long studentId) {
        return studentDAO.findById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentDAO.findById(studentId);
        Course course = courseDAO.findById(courseId);
        
        if (student != null && course != null) {
            student.setCourse(course);
            student.setBalance(course.getFee());
            studentDAO.update(student);
        }
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        return studentDAO.findByCourse(courseId);
    }

    public Student getStudentByEmail(String email) {
        return studentDAO.findByEmail(email);
    }
}
