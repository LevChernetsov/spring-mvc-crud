package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration  //  Говорим Spring: это конфигурационный класс
@ComponentScan({"com.example.service", "com.example.dao"})  // Сканируем service и dao
@EnableTransactionManagement  // Включаем управление транзакциями
public class AppConfig {

    // (4) Настройка подключения к базе данных
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Параметры подключения к MySQL
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mysql1501?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("ktd@)!@");

        return dataSource;
    }

    // (5) Настройка EntityManagerFactory (это вместо SessionFactory)
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        // Даем наш DataSource
        em.setDataSource(dataSource());

        // Указываем, где искать классы с @Entity
        em.setPackagesToScan("com.example.model");

        // Используем Hibernate как реализацию JPA
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Дополнительные настройки Hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");  // (6) Авто-создание таблиц
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");  // Диалект MySQL
        properties.setProperty("hibernate.show_sql", "true");  // Показывать SQL в консоли
        properties.setProperty("hibernate.format_sql", "true");  // Красиво форматировать SQL

        em.setJpaProperties(properties);

        return em;
    }

    // (7) Настройка менеджера транзакций
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
