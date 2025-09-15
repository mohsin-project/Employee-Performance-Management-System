package com.example.epms.service.impl;

import com.example.epms.dto.AddEmployeeToProjectDto;
import com.example.epms.dto.AddProjectToEmployeeDto;
import com.example.epms.entity.Employee;
import com.example.epms.entity.EmployeeProject;
import com.example.epms.entity.Project;
import com.example.epms.repository.EmployeeProjectRepository;
import com.example.epms.repository.EmployeeRepository;
import com.example.epms.repository.ProjectRepository;
import com.example.epms.service.EmployeeProjectService;
import com.example.epms.service.EmployeeService;
import com.example.epms.service.ProjectService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Override
    public Employee addProjectsForEmployee(Long employeeId, Set<AddProjectToEmployeeDto> projects) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        List<EmployeeProject> employeeProjects = new ArrayList<>();

        for (AddProjectToEmployeeDto dto : projects) {
            Project project = projectService.getProjectById(dto.getProjectId());

            boolean alreadyAssigned = employee.getEmployeeProjects().stream()
                    .anyMatch(ep -> ep.getProject().getId().equals(project.getId()));

            if (!alreadyAssigned) {
                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setEmployee(employee);
                employeeProject.setProject(project);
                employeeProject.setRole(dto.getRole());


                employeeProjects.add(employeeProject);
            }
        }

        employeeProjectRepository.saveAllAndFlush(employeeProjects);
        entityManager.refresh(employee);

        return employee;
    }

    @Override
    public Project addEmployeesForProject(Long projectId, Set<AddEmployeeToProjectDto> employees) {
        Project project = projectService.getProjectById(projectId);

        List<EmployeeProject> employeeProjects = new ArrayList<>();

        for (AddEmployeeToProjectDto dto : employees) {
            Employee employee = employeeService.getEmployeeById(dto.getEmployeeId());

            boolean alreadyAssigned = project.getEmployeeProjects().stream()
                    .anyMatch(ep -> ep.getEmployee().getId().equals(employee.getId()));

            if (!alreadyAssigned) {
                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setProject(project);
                employeeProject.setEmployee(employee);
                employeeProject.setRole(dto.getRole());

                employeeProjects.add(employeeProject);
            }
        }

        employeeProjectRepository.saveAllAndFlush(employeeProjects);
        entityManager.refresh(project);

        return project;
    }

    @Override
    public Employee removeProjectsFromEmployee(Long employeeId, Set<Long> projectIds) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        employee.getEmployeeProjects().removeIf(ep -> projectIds.contains(ep.getProject().getId()));
        return employeeRepository.save(employee);
    }

    @Override
    public Project removeEmployeesFromProject(Long projectId, Set<Long> employeeIds) {
        Project project = projectService.getProjectById(projectId);

        project.getEmployeeProjects().removeIf(ep -> employeeIds.contains(ep.getEmployee().getId()));
        return projectRepository.save(project);
    }
}
