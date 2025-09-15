package com.example.epms.dto;

import com.example.epms.validation.ValidationGroup;
import com.example.epms.validation.annotation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class PerformanceReviewDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotNull(groups = ValidationGroup.Create.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    Long employeeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    EmployeeDto.Simple employee;

    @PastOrPresent(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    LocalDate reviewDate;

    @NotNull(groups = {ValidationGroup.Create.class})
    @DecimalMin(value = "0.0", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    @DecimalMax(value = "100.0", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    Double score;

    @NullOrNotBlank(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    @Size(max = 1000, message = "must not exceed {max} characters", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    String reviewComments;

    @Getter
    @AllArgsConstructor
    public static class Simple {
        Long id;
        LocalDate reviewDate;
        Double score;
        String reviewComments;
    }
}
