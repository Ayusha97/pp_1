package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    Util util = new Util();

    public void createUsersTable() {
        Statement statement = null;

        try (Connection connection = util.connection()) {
            statement = connection.createStatement();
            String sql = "CREATE TABLE users " +
                    "(id BIGSERIAL NOT NULL ," +
                    "name VARCHAR(255), " +
                    "lastname VARCHAR(255), " +
                    "age INTEGER, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
            System.out.println("Таблица users создана");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        Statement statement = null;

        try (Connection connection = util.connection()) {
            statement = connection.createStatement();
            String sql = "DROP TABLE users";
            statement.executeQuery(sql);
//            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        try (Connection connection = util.connection()) {
            String sql = "INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        Statement statement = null;
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = util.connection()) {
            statement = connection.createStatement();
            statement.executeQuery(sql);
            System.out.println("User удалён");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT * FROM users";
        try (Connection connection = util.connection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                System.out.println(user);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        String sql = "TRUNCATE users";
        try (Connection connection = util.connection()) {
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
