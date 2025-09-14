package com.example.epms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "employee_project",
        uniqueConstraints = @UniqueConstraint(name = "employee_project_unique", columnNames = {"employee_id", "project_id"})
)
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Project project;

    @Column(
            name = "assigned_date",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "DATE DEFAULT CURRENT_DATE"
    )
    LocalDate assignedDate;

    @Column(nullable = false)
    @Size(max = 20)
    String role;
}
