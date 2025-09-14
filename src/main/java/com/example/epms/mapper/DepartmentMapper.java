package com.example.epms.mapper;

import com.example.epms.dto.DepartmentDto;
import com.example.epms.entity.Department;
import com.example.epms.entity.Employee;
import com.example.epms.entity.Project;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends DtoEntityMapper<DepartmentDto, Department> {

    @Mapping(target = "employees", source = "employees", qualifiedByName = "mapEmployeesToIds")
    @Mapping(target = "projects", source = "projects", qualifiedByName = "mapProjectsToIds")
    DepartmentDto toDto(Department department);

    @Override
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "projects", ignore = true)
    Department toEntity(DepartmentDto departmentDto);

    @Override
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DepartmentDto dto, @MappingTarget Department entity);

    @Named("mapEmployeesToIds")
    default Set<Long> mapEmployeesToIds(Set<Employee> employees) {
        if (employees == null) return null;
        return employees.stream()
                .map(com.example.epms.entity.Employee::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapProjectsToIds")
    default Set<Long> mapProjectsToIds(Set<Project> projects) {
        if (projects == null) return null;
        return projects.stream()
                .map(com.example.epms.entity.Project::getId)
                .collect(Collectors.toSet());
    }
}
