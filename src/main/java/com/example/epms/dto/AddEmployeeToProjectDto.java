package com.example.epms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddEmployeeToProjectDto {

    @NotNull
    Long employeeId;

    @NotBlank
    String role;
}
