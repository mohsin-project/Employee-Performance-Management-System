package com.example.epms.mapper;

import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.entity.PerformanceReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerformanceReviewMapper extends DtoEntityMapper<PerformanceReviewDto, PerformanceReview> {

    @Override
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employee.id", source = "employee.id")
    @Mapping(target = "employee.name", source = "employee.name")
    @Mapping(target = "employee.email", source = "employee.email")
    PerformanceReviewDto toDto(PerformanceReview performanceReview);
}
