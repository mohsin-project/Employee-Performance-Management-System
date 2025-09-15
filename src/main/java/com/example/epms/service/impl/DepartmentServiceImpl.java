package com.example.epms.service.impl;

import com.example.epms.custom_exception.NotFoundException;
import com.example.epms.dto.DepartmentDto;
import com.example.epms.entity.Department;
import com.example.epms.mapper.DepartmentMapper;
import com.example.epms.repository.DepartmentRepository;
import com.example.epms.repository.EmployeeRepository;
import com.example.epms.repository.ProjectRepository;
import com.example.epms.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        log.info("department id: " + id);

        if (id == null) return null;

        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
    }

    @Override
    public Department addDepartment(DepartmentDto departmentDto) {
        return departmentRepository.save(departmentMapper.toEntity(departmentDto));
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));

        departmentMapper.updateEntityFromDto(departmentDto, department);
        return departmentRepository.save(department);
    }

    @Override
    public Department deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));

        employeeRepository.clearDepartmentReferences(id);
        projectRepository.clearDepartmentReferences(id);

        departmentRepository.delete(department);
        return department;
    }
}
