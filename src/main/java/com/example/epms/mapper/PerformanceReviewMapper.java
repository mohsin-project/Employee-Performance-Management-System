package com.example.epms.mapper;

import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.entity.PerformanceReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerformanceReviewMapper extends DtoEntityMapper<PerformanceReviewDto, PerformanceReview> {

    @Override
    @Mapping(target = "employeeId", source = "employee.id")
    PerformanceReviewDto toDto(PerformanceReview performanceReview);
}
