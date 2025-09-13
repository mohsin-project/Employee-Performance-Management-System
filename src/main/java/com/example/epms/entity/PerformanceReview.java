package com.example.epms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "performance_review")
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @Column(name = "review_date", nullable = false)
    LocalDate reviewDate;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    Integer score;
}
