package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE users " +
                "(id BIGSERIAL NOT NULL , " +
                "name VARCHAR(255), " +
                "lastname VARCHAR(255), " +
                "age INTEGER, " +
                "PRIMARY KEY (id))";
        Session session = Hibernate.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        Session session = Hibernate.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        Session session = Hibernate.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.setParameter(1, name);
        query.setParameter(2, lastName);
        query.setParameter(3, age);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        Session session = Hibernate.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "FROM User";
        Session session = Hibernate.getSession();
        session.getTransaction();
        Query query = session.createQuery(sql);
        try {
            userList =(List<User>) query.list();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        Session session = Hibernate.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
    }
}
