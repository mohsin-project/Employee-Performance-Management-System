package com.example.epms.service.impl;

import com.example.epms.custom_exception.NotFoundException;
import com.example.epms.dto.EmployeeDto;
import com.example.epms.entity.Employee;
import com.example.epms.mapper.EmployeeMapper;
import com.example.epms.repository.EmployeeRepository;
import com.example.epms.service.DepartmentService;
import com.example.epms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentService departmentService;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        if (id == null) return null;

        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager/Employee not found with id: " + id));
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);

        employee.setDepartment(departmentService.getDepartmentById(employeeDto.getDepartmentId()));
        employee.setManager(getEmployeeById(employeeDto.getManagerId()));

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager/Employee not found with id: " + id));

        if (employeeDto.getDepartmentId() != null)
            employee.setDepartment(departmentService.getDepartmentById(employeeDto.getDepartmentId()));

        if (employeeDto.getManagerId() != null)
            employee.setManager(getEmployeeById(employeeDto.getManagerId()));

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
