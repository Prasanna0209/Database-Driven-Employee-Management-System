package com.employee.main;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EmployeeService service = new EmployeeService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" Employee Management System (JDBC/MVC) ");
        System.out.println("=========================================");

        while (true) {
            printMenu();
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    viewAllEmployees();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    System.out.println("Exiting System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add Employee");
        System.out.println("2. View Employee by ID");
        System.out.println("3. View All Employees (Pagination)");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Department: ");
            String dept = scanner.nextLine();
            
            System.out.print("Designation: ");
            String desig = scanner.nextLine();
            
            System.out.print("Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Date of Joining (YYYY-MM-DD): ");
            String dojLabel = scanner.nextLine();
            Date doj = Date.valueOf(dojLabel);

            Employee emp = new Employee(name, email, dept, desig, salary, doj);
            service.addEmployee(emp);
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    private static void viewEmployee() {
        System.out.print("\nEnter Employee ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Employee emp = service.getEmployeeById(id);
            if (emp != null) {
                System.out.println(emp);
            } else {
                System.out.println("Employee not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void viewAllEmployees() {
        System.out.print("\nEnter Page Number (1, 2, 3...): ");
        int page = 1;
        try {
            page = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            page = 1;
        }

        int pageSize = 10;
        List<Employee> list = service.getAllEmployees(page, pageSize);
        if (list.isEmpty()) {
            System.out.println("No records found on this page.");
        } else {
            System.out.println("\n--- Employee List (Page " + page + ") ---");
            for (Employee e : list) {
                System.out.println(e);
            }
        }
    }

    private static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");
        System.out.print("Enter Employee ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Employee existing = service.getEmployeeById(id);
            
            if (existing == null) {
                System.out.println("Employee not found.");
                return;
            }

            System.out.println("Current details: " + existing);
            System.out.println("Enter new details (press Enter to keep current):");

            System.out.print("Name (" + existing.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) existing.setName(name);

            System.out.print("Email (" + existing.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) existing.setEmail(email);

            System.out.print("Department (" + existing.getDepartment() + "): ");
            String dept = scanner.nextLine();
            if (!dept.isEmpty()) existing.setDepartment(dept);

            System.out.print("Designation (" + existing.getDesignation() + "): ");
            String desig = scanner.nextLine();
            if (!desig.isEmpty()) existing.setDesignation(desig);

            System.out.print("Salary (" + existing.getSalary() + "): ");
            String salStr = scanner.nextLine();
            if (!salStr.isEmpty()) existing.setSalary(Double.parseDouble(salStr));

            service.updateEmployee(existing);
        } catch (Exception e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {
        System.out.print("\nEnter Employee ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            service.deleteEmployee(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }
}
