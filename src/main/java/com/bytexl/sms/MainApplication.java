package com.bytexl.sms;

import com.bytexl.sms.config.AppConfig;
import com.bytexl.sms.model.Course;
import com.bytexl.sms.model.Student;
import com.bytexl.sms.service.FeeService;
import com.bytexl.sms.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * MainApplication - Console-based Student Management System
 * Demonstrates complete Spring + Hibernate application
 */
public class MainApplication {

    private static ApplicationContext context;
    private static StudentService studentService;
    private static FeeService feeService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Initialize Spring Application Context
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        studentService = context.getBean(StudentService.class);
        feeService = context.getBean(FeeService.class);
        scanner = new Scanner(System.in);

        System.out.println("=" .repeat(60));
        System.out.println("    ONLINE STUDENT MANAGEMENT SYSTEM");
        System.out.println("    Spring + Hibernate Integration Demo");
        System.out.println("=" .repeat(60));

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        processPayment();
                        break;
                    case 6:
                        processRefund();
                        break;
                    case 7:
                        checkBalance();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\nThank you for using Student Management System!");
                        break;
                    default:
                        System.out.println("\nInvalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

        scanner.close();
        ((AnnotationConfigApplicationContext) context).close();
    }

    private static void displayMenu() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("MAIN MENU");
        System.out.println("-".repeat(60));
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Process Fee Payment");
        System.out.println("6. Process Refund");
        System.out.println("7. Check Student Balance");
        System.out.println("0. Exit");
        System.out.println("-".repeat(60));
    }

    private static void addStudent() {
        System.out.println("\n=== ADD NEW STUDENT ===");
        String name = getStringInput("Enter student name: ");
        String email = getStringInput("Enter email: ");
        String phone = getStringInput("Enter phone: ");

        Student student = new Student(name, email, phone);
        studentService.addStudent(student);
        
        System.out.println("\n✓ Student added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.printf("%-5s %-20s %-30s %-15s %-10s%n", 
                "ID", "Name", "Email", "Phone", "Balance");
            System.out.println("-".repeat(85));
            for (Student s : students) {
                System.out.printf("%-5d %-20s %-30s %-15s %-10s%n",
                    s.getStudentId(), s.getName(), s.getEmail(), 
                    s.getPhone(), s.getBalance());
            }
        }
    }

    private static void updateStudent() {
        System.out.println("\n=== UPDATE STUDENT ===");
        Long id = getLongInput("Enter student ID: ");
        Student student = studentService.getStudentById(id);
        
        if (student != null) {
            String name = getStringInput("Enter new name (current: " + student.getName() + "): ");
            student.setName(name);
            studentService.updateStudent(student);
            System.out.println("\n✓ Student updated successfully!");
        } else {
            System.out.println("\nStudent not found!");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n=== DELETE STUDENT ===");
        Long id = getLongInput("Enter student ID to delete: ");
        studentService.deleteStudent(id);
        System.out.println("\n✓ Student deleted successfully!");
    }

    private static void processPayment() {
        System.out.println("\n=== PROCESS PAYMENT ===");
        Long id = getLongInput("Enter student ID: ");
        BigDecimal amount = getBigDecimalInput("Enter payment amount: ");
        
        feeService.processPayment(id, amount);
        System.out.println("\n✓ Payment processed successfully!");
        System.out.println("New balance: " + feeService.getBalance(id));
    }

    private static void processRefund() {
        System.out.println("\n=== PROCESS REFUND ===");
        Long id = getLongInput("Enter student ID: ");
        BigDecimal amount = getBigDecimalInput("Enter refund amount: ");
        
        feeService.processRefund(id, amount);
        System.out.println("\n✓ Refund processed successfully!");
        System.out.println("New balance: " + feeService.getBalance(id));
    }

    private static void checkBalance() {
        System.out.println("\n=== CHECK BALANCE ===");
        Long id = getLongInput("Enter student ID: ");
        BigDecimal balance = feeService.getBalance(id);
        System.out.println("\nCurrent balance: " + balance);
    }

    // Utility methods
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static Long getLongInput(String prompt) {
        System.out.print(prompt);
        Long value = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static BigDecimal getBigDecimalInput(String prompt) {
        System.out.print(prompt);
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine(); // consume newline
        return value;
    }
          }
