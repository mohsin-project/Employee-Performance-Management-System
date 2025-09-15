package com.example.epms.entity;

import com.example.epms.validation.ValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Check;

import java.time.Clock;
import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "performance_review",
        uniqueConstraints = @UniqueConstraint(name = "employee_review_date_unique", columnNames = {"employee_id", "review_date"})
)
@Check(name = "score_check", constraints = "score BETWEEN 0 AND 100")
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
            columnDefinition = "DATE DEFAULT CURRENT_DATE"
    )
    LocalDate reviewDate;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    @DecimalMax(value = "100.0", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    Double score;

    @Column(name = "review_comments")
    @Size(max = 1000)
    String reviewComments;

    @PrePersist
    public void prePersist() {
        if (reviewDate == null)
            reviewDate = LocalDate.now(Clock.systemUTC());
    }
}
