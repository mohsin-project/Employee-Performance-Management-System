package com.example.epms.mapper;

import com.example.epms.dto.ProjectDto;
import com.example.epms.entity.Employee;
import com.example.epms.entity.EmployeeProject;
import com.example.epms.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends DtoEntityMapper<ProjectDto, Project> {

    @Override
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "employees", source = "employeeProjects", qualifiedByName = "mapEmployeeProjectsToEmployeeIds")
    ProjectDto toDto(Project project);

    @Override
    @Mapping(target = "department", ignore = true)
    Project toEntity(ProjectDto projectDto);

    @Named("mapEmployeeProjectsToEmployeeIds")
    default Set<Long> mapEmployeeProjectsToEmployeeIds(Set<EmployeeProject> employeeProjects) {
        if (employeeProjects == null) return null;
        return employeeProjects.stream()
                .map(EmployeeProject::getEmployee)
                .map(Employee::getId)
                .collect(Collectors.toSet());
    }
}
