package com.example.epms.dto;

import com.example.epms.validation.ValidationGroup;
import com.example.epms.validation.annotation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProjectDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotBlank(groups = ValidationGroup.Create.class)
    @NullOrNotBlank(groups = ValidationGroup.Update.class)
    @Size(max = 50, message = "must not exceed {max} characters", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    String name;

    @NotNull(groups = ValidationGroup.Create.class)
    LocalDate startDate;

    @NotNull(groups = ValidationGroup.Create.class)
    LocalDate endDate;

    Long departmentId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Set<Long> employees;
}
