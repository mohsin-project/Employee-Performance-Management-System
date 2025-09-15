package com.example.epms.service;

import com.example.epms.dto.DepartmentDto;
import com.example.epms.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    Department addDepartment(DepartmentDto departmentDto);

    Department updateDepartment(Long id, DepartmentDto departmentDto);

    Department deleteDepartment(Long id);
}
