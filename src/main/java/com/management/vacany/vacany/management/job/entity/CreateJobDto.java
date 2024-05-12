package com.management.vacany.vacany.management.job.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDto {
    @Schema(example = "Vacany for development")
    private String description;

    @Schema(example = "5000$")
    private String benefits;

    @Schema(example = "senior")
    private String level;
}
