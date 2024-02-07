package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());
        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 10);
        userService.saveUser("Petr", "Petrov", (byte) 11);
        userService.saveUser("Anton", "Antonov", (byte) 10);
        userService.saveUser("Roman", "Romanov", (byte) 10);

        List<User> userList = userService.getAllUsers();
        System.out.println(userList);

//        userService.removeUserById(2);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
