package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Connection connection() {

         String url = "jdbc:postgresql://localhost:5432/lesson_db";
         String username = "postgres";
         String password = "postgres";

         Connection connection = null;

         try {
             connection = DriverManager.getConnection(url, username, password);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return connection;
    }
}
