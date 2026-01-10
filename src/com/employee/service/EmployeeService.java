package com.employee.service;

import com.employee.dao.EmployeeDAO;
import com.employee.dao.EmployeeDAOImpl;
import com.employee.model.Employee;
import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    public void addEmployee(Employee employee) {
        try {
            // Business Logic Validations
            if (employee.getSalary() < 0) {
                System.out.println("Error: Salary cannot be negative.");
                return;
            }
            if (employee.getName() == null || employee.getName().isEmpty()) {
                System.out.println("Error: Name cannot be empty.");
                return;
            }
            
            employeeDAO.addEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            return employeeDAO.getEmployeeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> getAllEmployees(int page, int pageSize) {
        try {
            int offset = (page - 1) * pageSize;
            return employeeDAO.getAllEmployees(offset, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            employeeDAO.updateEmployee(employee);
        } catch (Exception e) {
            System.err.println("Failed to update employee: " + e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        try {
            employeeDAO.deleteEmployee(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
