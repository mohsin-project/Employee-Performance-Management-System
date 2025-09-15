package com.example.epms.service;

import com.example.epms.dto.EmployeeDto;
import com.example.epms.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee addEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    Employee deleteEmployee(Long id);
}
