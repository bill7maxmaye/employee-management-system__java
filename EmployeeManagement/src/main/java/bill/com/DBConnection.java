package bill.com;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class DBConnection {
//    private static final String URL = "jdbc:mysql://localhost:3306/employee_management";
//    private static final String USER = "root";
//    private static final String PASSWORD = "bill@5213";
//
//    public static Connection getConnection() {
//        try {
//            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Database connection successful!");
//            return connection;
//        } catch (SQLException e) {
//            System.err.println("Error connecting to the database: " + e.getMessage());
//            throw new RuntimeException("Failed to connect to the database.");
//        }
//    }
//}


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";
    private static final String PASSWORD = "bill@5213";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver.");
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            throw new RuntimeException("Failed to connect to the database.");
        }
    }
}