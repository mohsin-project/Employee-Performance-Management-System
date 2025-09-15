package com.example.epms.mapper;

import com.example.epms.dto.DepartmentDto;
import com.example.epms.dto.EmployeeDto;
import com.example.epms.dto.ProjectDto;
import com.example.epms.entity.Department;
import com.example.epms.entity.Employee;
import com.example.epms.entity.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends DtoEntityMapper<DepartmentDto, Department> {

    @Mapping(target = "employees", source = "employees", qualifiedByName = "mapEmployee")
    @Mapping(target = "projects", source = "projects", qualifiedByName = "mapProject")
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

    @Named("mapEmployee")
    default EmployeeDto.Simple mapEmployee(Employee employee) {
        return EmployeeDto.Simple.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    @Named("mapProject")
    default ProjectDto.Simple mapProject(Project project) {
        return ProjectDto.Simple.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }
}
