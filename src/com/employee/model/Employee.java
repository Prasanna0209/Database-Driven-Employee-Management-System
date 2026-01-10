package com.employee.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String department;
    private String designation;
    private double salary;
    private Date dateOfJoining;

    public Employee() {}

    public Employee(String name, String email, String department, String designation, double salary, Date dateOfJoining) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
    }
    
    public Employee(int id, String name, String email, String department, String designation, double salary, Date dateOfJoining) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public Date getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(Date dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s | Dept: %s | Desig: %s | Salary: %.2f | Joined: %s",
                id, name, email, department, designation, salary, dateOfJoining);
    }
}
