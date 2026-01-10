package com.employee.dao;

import com.employee.model.Employee;
import com.employee.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void addEmployee(Employee employee) throws Exception {
        String sql = "INSERT INTO employees (name, email, department, designation, salary, date_of_joining) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setString(4, employee.getDesignation());
            pstmt.setDouble(5, employee.getSalary());
            pstmt.setDate(6, employee.getDateOfJoining());
            
            pstmt.executeUpdate();
            System.out.println("Employee added successfully!");
        }
    }

    @Override
    public Employee getEmployeeById(int id) throws Exception {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEmployee(rs);
                }
            }
        }
        return null; // Not found
    }

    @Override
    public List<Employee> getAllEmployees(int offset, int limit) throws Exception {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees LIMIT ? OFFSET ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }
            }
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) throws Exception {
        String sql = "UPDATE employees SET name=?, email=?, department=?, designation=?, salary=? WHERE employee_id=?";
        
        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false); // Start Transaction

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setString(4, employee.getDesignation());
            pstmt.setDouble(5, employee.getSalary());
            pstmt.setInt(6, employee.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                conn.commit(); // Commit Transaction
                System.out.println("Employee updated successfully!");
            } else {
                conn.rollback();
                System.out.println("Update failed. Employee ID not found.");
            }
        } catch (SQLException e) {
            conn.rollback(); // Rollback on error
            throw e;
        } finally {
            conn.setAutoCommit(true); // Reset to default
        }
    }

    @Override
    public void deleteEmployee(int id) throws Exception {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee ID not found.");
            }
        }
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getInt("employee_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("department"),
            rs.getString("designation"),
            rs.getDouble("salary"),
            rs.getDate("date_of_joining")
        );
    }
}
