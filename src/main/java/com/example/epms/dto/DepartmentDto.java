package com.example.epms.dto;

import com.example.epms.validation.ValidationGroup;
import com.example.epms.validation.annotation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Data
public class DepartmentDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotBlank(groups = ValidationGroup.Create.class)
    @NullOrNotBlank(groups = ValidationGroup.Update.class)
    @Size(max = 100, message = "must not exceed {max} characters", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    String name;

    @NotNull(groups = ValidationGroup.Create.class)
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    Double budget;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Set<EmployeeDto.Simple> employees;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Set<ProjectDto.Simple> projects;

    @Getter
    @AllArgsConstructor
    public static class Simple {
        Long id;
        String name;
    }
}
