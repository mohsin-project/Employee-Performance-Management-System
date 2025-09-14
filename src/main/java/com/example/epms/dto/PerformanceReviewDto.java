package com.example.epms.dto;

import com.example.epms.validation.ValidationGroup;
import com.example.epms.validation.annotation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PerformanceReviewDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotNull(groups = ValidationGroup.Create.class)
    Long employeeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @PastOrPresent(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    LocalDate reviewDate;

    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    @Min(0)
    @Max(100)
    Integer score;

    @NullOrNotBlank(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    @Size(max = 1000, message = "must not exceed {max} characters", groups = {ValidationGroup.Create.class, ValidationGroup.Update.class})
    String reviewComments;
}
