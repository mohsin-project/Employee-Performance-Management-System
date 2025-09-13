package com.example.epms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "employee_project",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "project_id"})
)
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

    @Column(name = "assigned_date", nullable = false)
    LocalDate assignedDate;

    @Column(nullable = false)
    String role;
}
