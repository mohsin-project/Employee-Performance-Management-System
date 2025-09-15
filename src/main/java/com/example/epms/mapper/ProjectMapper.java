package com.example.epms.mapper;

import com.example.epms.dto.EmployeeDto;
import com.example.epms.dto.ProjectDto;
import com.example.epms.entity.Employee;
import com.example.epms.entity.EmployeeProject;
import com.example.epms.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends DtoEntityMapper<ProjectDto, Project> {

    @Override
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "department.id", source = "department.id")
    @Mapping(target = "department.name", source = "department.name")
    @Mapping(target = "employees", source = "employeeProjects", qualifiedByName = "mapEmployee")
    ProjectDto toDto(Project project);

    @Override
    @Mapping(target = "department", ignore = true)
    Project toEntity(ProjectDto projectDto);

    @Named("mapEmployee")
    default EmployeeDto.Simple mapEmployee(EmployeeProject employeeProject) {
        Employee employee = employeeProject.getEmployee();
        return EmployeeDto.Simple.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .role(employeeProject.getRole())
                .build();
    }
}
