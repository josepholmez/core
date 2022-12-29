package com.olmez.core.services;

import java.util.List;

import com.olmez.core.model.Employee;

public interface EmployeeService {

    List<Employee> getEmployees();

    boolean addEmployee(Employee newEmployee);

    Employee getEmployeeById(Long empId);

    boolean deleteEmployee(Long empId);

    Employee updateEmployee(Long existingEmpId, Employee givenEmployee);

}
