package com.example.epms.service;

import com.example.epms.dto.EmployeeDto;
import com.example.epms.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    List<Employee> getAllEmployees(LocalDate reviewDate, Set<Long> departmentIds, Set<Long> projectIds);

    Employee getDetailedEmployeeById(Long id);

    EmployeeDto getEmployeeById(Long id);

    Employee addEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    Employee deleteEmployee(Long id);
}
