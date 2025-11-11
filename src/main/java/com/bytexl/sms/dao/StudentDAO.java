package com.bytexl.sms.dao;

import com.bytexl.sms.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StudentDAO - Data Access Layer for Student entity
 * Demonstrates Hibernate CRUD operations
 */
@Repository
public class StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    public void update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

    public void delete(Long studentId) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            session.delete(student);
        }
    }

    public Student findById(Long studentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, studentId);
    }

    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    public Student findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery(
                "FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    public List<Student> findByCourse(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery(
                "FROM Student s WHERE s.course.courseId = :courseId", Student.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }
}
