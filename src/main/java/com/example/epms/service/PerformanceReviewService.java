package com.example.epms.service;

import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.entity.PerformanceReview;

import java.util.List;

public interface PerformanceReviewService {

    List<PerformanceReview> getAllPerformanceReviews();

    PerformanceReview getPerformanceReviewById(Long id);

    PerformanceReview addPerformanceReview(PerformanceReviewDto performanceReviewDto);

    PerformanceReview updatePerformanceReview(Long id, PerformanceReviewDto performanceReviewDto);

    PerformanceReview deletePerformanceReview(Long id);
}
