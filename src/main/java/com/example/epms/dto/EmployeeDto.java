package com.example.epms.dto;

import com.example.epms.validation.ValidationGroup;
import com.example.epms.validation.annotation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotBlank(groups = ValidationGroup.Create.class)
    @NullOrNotBlank(groups = ValidationGroup.Update.class)
    @Size(max = 100, message = "must not exceed {max} characters", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    String name;

    @NotBlank(groups = ValidationGroup.Create.class)
    @NullOrNotBlank(groups = ValidationGroup.Update.class)
    @Email(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    @Size(max = 50, message = "must not exceed {max} characters", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    String email;

    Long departmentId;

    @NotNull(groups = ValidationGroup.Create.class)
    @PastOrPresent(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    LocalDate dateOfJoining;

    @NotNull(groups = ValidationGroup.Create.class)
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    BigDecimal salary;

    Long managerId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Set<Long> subordinates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Set<Long> performanceReviews;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Set<Long> projects;
}
