package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Hibernate {

    public static final SessionFactory sessionFactory;

    static  {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/lesson_db");
            properties.setProperty("hibernate.connection.username", "postgres");
            properties.setProperty("hibernate.connection.password", "postgres");
            properties.setProperty("dialect", "org.hiberanate.dialect.PostreSQL10Dialect");
            properties.setProperty("hibernate.show_sql", "true");

            sessionFactory = new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
