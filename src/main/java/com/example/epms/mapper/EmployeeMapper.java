package com.example.epms.mapper;

import com.example.epms.dto.EmployeeDto;
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
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "subordinates", source = "subordinates", qualifiedByName = "mapSubordinatesToIds")
    @Mapping(target = "performanceReviews", source = "performanceReviews", qualifiedByName = "mapPerformanceReviewsToIds")
    @Mapping(target = "projects", source = "employeeProjects", qualifiedByName = "mapProjectsToIds")
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


    @Named("mapPerformanceReviewsToIds")
    default Set<Long> mapPerformanceReviewsToIds(Set<PerformanceReview> performanceReviews) {
        if (performanceReviews == null) return null;
        return performanceReviews.stream()
                .map(PerformanceReview::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapProjectsToIds")
    default Set<Long> mapProjectsToIds(Set<EmployeeProject> employeeProjects) {
        if (employeeProjects == null) return null;
        return employeeProjects.stream()
                .map(EmployeeProject::getProject)
                .map(Project::getId)
                .collect(Collectors.toSet());
    }
}
