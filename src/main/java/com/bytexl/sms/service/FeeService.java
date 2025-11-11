package com.bytexl.sms.service;

import com.bytexl.sms.dao.StudentDAO;
import com.bytexl.sms.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * FeeService - Service layer for Fee Payment and Refund operations
 * Demonstrates Transaction Management with @Transactional
 * Ensures atomicity - either all operations succeed or all fail
 */
@Service
public class FeeService {

    private static final Logger logger = LoggerFactory.getLogger(FeeService.class);

    @Autowired
    private StudentDAO studentDAO;

    /**
     * Process fee payment for a student
     * Transaction will rollback if any exception occurs
     */
    @Transactional
    public void processPayment(Long studentId, BigDecimal amount) {
        logger.info("Processing payment of {} for student ID: {}", amount, studentId);
        
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }

        // Deduct amount from balance
        BigDecimal newBalance = student.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient balance. Current balance: " + student.getBalance());
        }

        student.setBalance(newBalance);
        studentDAO.update(student);
        
        logger.info("Payment processed successfully. New balance: {}", newBalance);
    }

    /**
     * Process refund for a student
     * Transaction will rollback if any exception occurs
     */
    @Transactional
    public void processRefund(Long studentId, BigDecimal amount) {
        logger.info("Processing refund of {} for student ID: {}", amount, studentId);
        
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Refund amount must be positive");
        }

        // Add amount to balance
        BigDecimal newBalance = student.getBalance().add(amount);
        student.setBalance(newBalance);
        studentDAO.update(student);
        
        logger.info("Refund processed successfully. New balance: {}", newBalance);
    }

    /**
     * Get current balance for a student
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long studentId) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
        return student.getBalance();
    }

    /**
     * Pay full fee - Demonstrates complex transaction
     * If student doesn't have a course, transaction will rollback
     */
    @Transactional
    public void payFullFee(Long studentId) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        if (student.getCourse() == null) {
            throw new RuntimeException("Student is not enrolled in any course");
        }

        BigDecimal courseFee = student.getCourse().getFee();
        processPayment(studentId, student.getBalance());
        
        logger.info("Full fee paid for student: {}", student.getName());
    }
}
