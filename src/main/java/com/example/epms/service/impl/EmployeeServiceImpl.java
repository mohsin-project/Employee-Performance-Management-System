package com.example.epms.service.impl;

import com.example.epms.custom_exception.NotFoundException;
import com.example.epms.dto.EmployeeDto;
import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.entity.Employee;
import com.example.epms.mapper.EmployeeMapper;
import com.example.epms.repository.EmployeeRepository;
import com.example.epms.service.DepartmentService;
import com.example.epms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentService departmentService;

    @Override
    public List<Employee> getAllEmployees(LocalDate reviewDate, Set<Long> departmentIds, Set<Long> projectIds) {
        return employeeRepository.findAllEmployees(reviewDate, departmentIds, projectIds);
    }

    @Override
    public Employee getDetailedEmployeeById(Long id) {
        if (id == null) return null;
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager/Employee not found with id: " + id));
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeDto employeeDto = employeeMapper.toDto(getDetailedEmployeeById(id));

        employeeDto.setPerformanceReviews(
                employeeDto.getPerformanceReviews().stream()
                        .sorted(Comparator.comparing(PerformanceReviewDto.Simple::getReviewDate).reversed())
                        .limit(3)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );

        return employeeDto;
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);

        employee.setDepartment(departmentService.getDepartmentById(employeeDto.getDepartmentId()));
        employee.setManager(getDetailedEmployeeById(employeeDto.getManagerId()));

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager/Employee not found with id: " + id));

        if (employeeDto.getDepartmentId() != null)
            employee.setDepartment(departmentService.getDepartmentById(employeeDto.getDepartmentId()));

        if (employeeDto.getManagerId() != null)
            employee.setManager(getDetailedEmployeeById(employeeDto.getManagerId()));

        employeeMapper.updateEntityFromDto(employeeDto, employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager/Employee not found with id: " + id));

        employeeRepository.clearManagerReferences(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
