package com.example.epms.mapper;

import com.example.epms.dto.EmployeeDto;
import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.dto.ProjectDto;
import com.example.epms.entity.Employee;
import com.example.epms.entity.EmployeeProject;
import com.example.epms.entity.PerformanceReview;
import com.example.epms.entity.Project;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends DtoEntityMapper<EmployeeDto, Employee> {

    @Override
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "department.id", source = "department.id")
    @Mapping(target = "department.name", source = "department.name")
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "manager", source = "manager", qualifiedByName = "mapEmployee")
    @Mapping(target = "subordinates", source = "subordinates", qualifiedByName = "mapEmployee")
    @Mapping(target = "performanceReviews", source = "performanceReviews", qualifiedByName = "mapPerformanceReview")
    @Mapping(target = "projects", source = "employeeProjects", qualifiedByName = "mapProject")
    EmployeeDto toDto(Employee employee);

    @Override
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "subordinates", ignore = true)
    @Mapping(target = "performanceReviews", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);

    @Override
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "subordinates", ignore = true)
    @Mapping(target = "performanceReviews", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EmployeeDto dto, @MappingTarget Employee entity);

    @Named("mapSubordinatesToIds")
    default Set<Long> mapSubordinatesToIds(Set<Employee> subordinates) {
        if (subordinates == null) return null;
        return subordinates.stream()
                .map(Employee::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapEmployee")
    default EmployeeDto.Simple mapEmployee(Employee employee) {
        if (employee == null) return null;
        return EmployeeDto.Simple.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    @Named("mapPerformanceReview")
    default PerformanceReviewDto.Simple mapPerformanceReview(PerformanceReview performanceReview) {
        return new PerformanceReviewDto.Simple(
                performanceReview.getId(),
                performanceReview.getReviewDate(),
                performanceReview.getScore(),
                performanceReview.getReviewComments());
    }

    @Named("mapProject")
    default ProjectDto.Simple mapProject(EmployeeProject employeeProject) {
        Project project = employeeProject.getProject();
        return ProjectDto.Simple.builder()
                .id(project.getId())
                .name(project.getName())
                .role(employeeProject.getRole())
                .build();
    }
}
