package com.example.epms.controller;


import com.example.epms.constant.PerformanceReviewConstant;
import com.example.epms.dto.PerformanceReviewDto;
import com.example.epms.mapper.PerformanceReviewMapper;
import com.example.epms.service.PerformanceReviewService;
import com.example.epms.validation.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PerformanceReviewConstant.PERF_REVIEW)
public class PerformanceReviewController {

    @Autowired
    PerformanceReviewMapper performanceReviewMapper;

    @Autowired
    PerformanceReviewService performanceReviewService;

    @GetMapping(PerformanceReviewConstant.PERF_REVIEW_ALL)
    public ResponseEntity<List<PerformanceReviewDto>> getAllPerformanceReviews() {
        return ResponseEntity.ok(performanceReviewMapper.toDtoList(performanceReviewService.getAllPerformanceReviews()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReviewDto> getPerformanceReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(performanceReviewMapper.toDto(performanceReviewService.getPerformanceReviewById(id)));
    }

    @Transactional
    @PostMapping(PerformanceReviewConstant.PERF_REVIEW_ADD)
    public ResponseEntity<PerformanceReviewDto> addPerformanceReview(@Validated(ValidationGroup.Create.class) @RequestBody PerformanceReviewDto performanceReviewDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(performanceReviewMapper.toDto(performanceReviewService.addPerformanceReview(performanceReviewDto)));
    }

    @Transactional
    @PutMapping(PerformanceReviewConstant.PERF_REVIEW_UPDATE)
    public ResponseEntity<PerformanceReviewDto> updatePerformanceReview(@PathVariable Long id, @Validated(ValidationGroup.Update.class) @RequestBody PerformanceReviewDto performanceReviewDto) {
        return ResponseEntity.accepted()
                .body(performanceReviewMapper.toDto(performanceReviewService.updatePerformanceReview(id, performanceReviewDto)));
    }

    @Transactional
    @DeleteMapping(PerformanceReviewConstant.PERF_REVIEW_DELETE)
    public ResponseEntity<PerformanceReviewDto> deletePerformanceReview(@PathVariable Long id) {
        return ResponseEntity.ok(performanceReviewMapper.toDto(performanceReviewService.deletePerformanceReview(id)));
    }
}
