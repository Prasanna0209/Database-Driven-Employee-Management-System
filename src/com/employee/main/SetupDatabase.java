package com.employee.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupDatabase {
    // Connect to MySQL server directly, not the specific database yet
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Empty password as confirmed

    public static void main(String[] args) {
        System.out.println("Initializing Database...");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                // 1. Create Database
                System.out.println("Creating database 'employee_db'...");
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS employee_db");
                
                // 2. Select Database
                stmt.execute("USE employee_db");
                
                // 3. Create Table
                System.out.println("Creating table 'employees'...");
                String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                        "employee_id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(100) UNIQUE NOT NULL, " +
                        "department VARCHAR(50), " +
                        "designation VARCHAR(50), " +
                        "salary DECIMAL(10, 2) NOT NULL, " +
                        "date_of_joining DATE NOT NULL" +
                        ")";
                stmt.executeUpdate(createTableSQL);
                
                System.out.println("Database and Table created successfully!");
                
            }
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
