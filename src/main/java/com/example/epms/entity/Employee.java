package com.example.epms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String email;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    Department department;

    @Column(name = "date_of_joining", nullable = false)
    LocalDate dateOfJoining;

    @Column(nullable = false)
    BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    Employee manager;

    @OneToMany(mappedBy = "manager")
    List<Employee> subordinates;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    PerformanceReview performanceReview;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    List<EmployeeProject> employeeProjects;
}
