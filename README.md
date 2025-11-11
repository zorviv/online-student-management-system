# Online Student Management System

## Spring + Hibernate Mini Project

### Project Overview
A comprehensive **Spring and Hibernate-based Student Management System** demonstrating enterprise Java development concepts including Dependency Injection, CRUD operations, and Transaction Management.

---

## 🎯 Project Objectives

This project demonstrates:
1. **Dependency Injection** using Spring Java-based configuration
2. **CRUD Operations** using Hibernate ORM
3. **Transaction Management** for fee payment and refund operations
4. **Spring + Hibernate Integration** for real-world data management

---

## 🏗️ Architecture

```
src/main/java/com/bytexl/sms/
├── config/
│   └── AppConfig.java          # Spring Java Configuration
├── model/
│   ├── Student.java            # JPA Entity
│   └── Course.java             # JPA Entity
├── dao/
│   ├── StudentDAO.java         # Data Access Layer
│   └── CourseDAO.java          # Data Access Layer
├── service/
│   ├── StudentService.java     # Business Logic
│   └── FeeService.java         # Transaction Management
└── MainApplication.java        # Console Application

src/main/resources/
└── database.properties          # Database Configuration

database/
└── schema.sql                   # Database Schema
```

---

## 💡 Key Features

### 1. **Dependency Injection**
- Java-based configuration using `@Configuration` and `@Bean`
- Component scanning with `@ComponentScan`
- Autowired dependencies using `@Autowired`

### 2. **CRUD Operations**
- Create: Add new students and courses
- Read: View all students, search by ID/email
- Update: Modify student information
- Delete: Remove student records

### 3. **Transaction Management**
- `@Transactional` annotation for automatic transaction handling
- Rollback support for failed operations
- Fee payment and refund with atomicity guarantees

### 4. **Features**
- Student enrollment management
- Course assignment
- Fee payment processing
- Balance tracking
- Refund handling

---

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|----------|
| Java | 11+ | Programming Language |
| Spring Framework | 5.3.30 | DI & Transaction Management |
| Hibernate | 5.6.15 | ORM Framework |
| MySQL | 8.0+ | Database |
| Maven | 3.6+ | Build Tool |
| HikariCP | 5.0.1 | Connection Pooling |

---

## 📋 Prerequisites

- Java Development Kit (JDK) 11 or higher
- MySQL Server 8.0+
- Maven 3.6+
- IDE (IntelliJ IDEA / Eclipse)

---

## 🚀 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/zorviv/online-student-management-system.git
cd online-student-management-system
```

### 2. Configure Database

**Create Database:**
```bash
mysql -u root -p
```

**Run Schema:**
```sql
source database/schema.sql
```

**Update Configuration:**
Edit `src/main/resources/database.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/student_management
db.username=your_username
db.password=your_password
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn exec:java -Dexec.mainClass="com.bytexl.sms.MainApplication"
```

Or run directly from IDE:
- Right-click `MainApplication.java`
- Select "Run"

---

## 📖 Usage

### Menu Options

1. **Add New Student** - Register a new student
2. **View All Students** - Display all enrolled students
3. **Update Student** - Modify student information
4. **Delete Student** - Remove student from system
5. **Process Fee Payment** - Handle fee transactions
6. **Process Refund** - Issue refunds
7. **Check Student Balance** - View outstanding fees

---

## 🔑 Key Concepts Demonstrated

### Dependency Injection Example
```java
@Configuration
@EnableTransactionManagement
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        // Configuration
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        // Hibernate setup
    }
}
```

### Transaction Management Example
```java
@Service
public class FeeService {
    
    @Transactional
    public void processPayment(Long studentId, BigDecimal amount) {
        // Atomic operation - rollback on error
    }
}
```

---

## 📊 Database Schema

### Tables

**courses**
- course_id (PK)
- course_name
- duration
- fee
- description

**students**
- student_id (PK)
- name
- email (UNIQUE)
- phone
- balance
- course_id (FK)
- enrollment_date
- status

**payments**
- payment_id (PK)
- student_id (FK)
- amount
- payment_type
- payment_date

---

## 🧪 Testing

### Sample Operations

1. Add a student
2. Enroll in a course (balance automatically set)
3. Process payment (transaction committed)
4. Try invalid payment (transaction rolled back)
5. Process refund

---

## 📝 Project Structure Highlights

- **Layered Architecture**: Model → DAO → Service → Controller
- **Separation of Concerns**: Clear responsibility boundaries
- **Spring Configuration**: Pure Java-based, no XML
- **Hibernate Integration**: Seamless ORM with Spring
- **Transaction Boundaries**: Service layer transactions

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

---

## 📄 License

This project is open source and available for educational purposes.

---

## 👨‍💻 Author

**ByteXL Nimbus Project**

For questions or support, please open an issue on GitHub.

---

## 🎓 Learning Outcomes

By studying this project, you will learn:

✅ Spring Framework dependency injection
✅ Hibernate ORM configuration and usage
✅ Transaction management with @Transactional
✅ Layered architecture design
✅ CRUD operations implementation
✅ Database integration with Spring
✅ Connection pooling with HikariCP
✅ Java-based Spring configuration
✅ Entity relationships in JPA
✅ Exception handling and rollback

---

**Happy Coding! 🚀**
