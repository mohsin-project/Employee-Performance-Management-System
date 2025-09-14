package com.example.epms.service;

import com.example.epms.dto.AddEmployeeToProjectDto;
import com.example.epms.dto.AddProjectToEmployeeDto;
import com.example.epms.entity.Employee;
import com.example.epms.entity.Project;

import java.util.Set;

public interface EmployeeProjectService {

    Employee addProjectsForEmployee(Long employeeId, Set<AddProjectToEmployeeDto> projects);

    Project addEmployeesForProject(Long projectId, Set<AddEmployeeToProjectDto> employees);

    Employee removeProjectsFromEmployee(Long employeeId, Set<Long> projectIds);

    Project removeEmployeesFromProject(Long projectId, Set<Long> employeeIds);
}
