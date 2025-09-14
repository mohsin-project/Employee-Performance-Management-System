package com.example.epms.service.impl;

import com.example.epms.custom_exception.NotFoundException;
import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.entity.PerformanceReview;
import com.example.epms.mapper.PerformanceReviewMapper;
import com.example.epms.repository.PerformanceReviewRepository;
import com.example.epms.service.EmployeeService;
import com.example.epms.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

    @Autowired
    PerformanceReviewMapper performanceReviewMapper;

    @Autowired
    PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    EmployeeService employeeService;

    @Override
    public List<PerformanceReview> getAllPerformanceReviews() {
        return performanceReviewRepository.findAll();
    }

    @Override
    public PerformanceReview getPerformanceReviewById(Long id) {
        if (id == null) return null;

        return performanceReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Performance review not found with id: " + id));
    }

    @Override
    public PerformanceReview addPerformanceReview(PerformanceReviewDto performanceReviewDto) {
        PerformanceReview performanceReview = performanceReviewMapper.toEntity(performanceReviewDto);
        performanceReview.setEmployee(employeeService.getEmployeeById(performanceReviewDto.getEmployeeId()));

        return performanceReviewRepository.save(performanceReview);
    }

    @Override
    public PerformanceReview updatePerformanceReview(Long id, PerformanceReviewDto performanceReviewDto) {
        PerformanceReview performanceReview = performanceReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Performance review not found with id: " + id));

        performanceReviewMapper.updateEntityFromDto(performanceReviewDto, performanceReview);
        return performanceReviewRepository.save(performanceReview);
    }

    @Override
    public PerformanceReview deletePerformanceReview(Long id) {
        PerformanceReview performanceReview = performanceReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PerformanceReview not found with id: " + id));

        performanceReviewRepository.delete(performanceReview);
        return performanceReview;
    }
}
