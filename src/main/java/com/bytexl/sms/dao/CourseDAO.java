package com.bytexl.sms.dao;

import com.bytexl.sms.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.save(course);
    }

    public void update(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.update(course);
    }

    public void delete(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        if (course != null) {
            session.delete(course);
        }
    }

    public Course findById(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Course.class, courseId);
    }

    public List<Course> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM Course", Course.class);
        return query.getResultList();
    }
}
