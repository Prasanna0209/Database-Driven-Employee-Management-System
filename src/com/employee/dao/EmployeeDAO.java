package com.employee.dao;

import com.employee.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee) throws Exception;
    Employee getEmployeeById(int id) throws Exception;
    List<Employee> getAllEmployees(int offset, int limit) throws Exception;
    void updateEmployee(Employee employee) throws Exception;
    void deleteEmployee(int id) throws Exception;
}
