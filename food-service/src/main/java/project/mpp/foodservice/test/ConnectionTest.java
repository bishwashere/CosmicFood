package project.mpp.foodservice.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3318/hello_java";
        String username = "demo_java";
        String password = "1234";

        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("No connection to the database!", e);
        }

    }
}
