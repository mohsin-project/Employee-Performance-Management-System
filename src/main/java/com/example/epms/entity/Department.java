package com.example.epms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Check;

import java.util.Set;

@Data
@Entity
@Table(name = "department")
@Check(name = "budget_check", constraints = "budget >= 0")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    String name;

    @Column(nullable = false)
    @PositiveOrZero
    Double budget;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Employee> employees;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Project> projects;
}
