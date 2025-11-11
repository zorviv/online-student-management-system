package com.bytexl.sms.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * AppConfig - Spring Java-based Configuration
 * Demonstrates:
 * - Dependency Injection using @Bean
 * - Component Scanning
 * - Transaction Management
 * - Spring + Hibernate Integration
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.bytexl.sms")
@PropertySource("classpath:database.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    /**
     * DataSource Bean - HikariCP for connection pooling
     * Demonstrates dependency injection of database configuration
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("db.url", "jdbc:mysql://localhost:3306/student_management"));
        config.setUsername(env.getProperty("db.username", "root"));
        config.setPassword(env.getProperty("db.password", ""));
        config.setDriverClassName(env.getProperty("db.driver", "com.mysql.cj.jdbc.Driver"));
        
        // Connection pool settings
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        
        return new HikariDataSource(config);
    }

    /**
     * SessionFactory Bean - Hibernate configuration
     * Demonstrates Spring-Hibernate integration
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.bytexl.sms.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Hibernate Properties
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

    /**
     * Transaction Manager Bean
     * Enables @Transactional annotation support
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
}
