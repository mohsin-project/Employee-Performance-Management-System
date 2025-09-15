package com.example.epms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
@Check(name = "diff_manager_employee_check", constraints = "id != manager_id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @Size(max = 100)
    String name;

    @Column(unique = true, nullable = false)
    @Size(max = 50)
    String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Department department;

    @Column(name = "date_of_joining", nullable = false)
    LocalDate dateOfJoining;

    @Column(nullable = false)
    BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    Employee manager;

    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Employee> subordinates;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<PerformanceReview> performanceReviews;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<EmployeeProject> employeeProjects;
}
