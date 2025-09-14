package com.example.epms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "performance_review",
        uniqueConstraints = @UniqueConstraint(name = "employee_review_date_unique", columnNames = {"employee_id", "review_date"})
)
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Employee employee;

    @Column(
            name = "review_date",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "DATE DEFAULT CURRENT_DATE"
    )
    LocalDate reviewDate;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    Integer score;

    @Column(name = "review_comments")
    @Size(max = 1000)
    String reviewComments;
}
