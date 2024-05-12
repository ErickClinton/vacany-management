package com.management.vacany.vacany.management.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCandidateResponseDto {

    private String description;
    private String name;
    private String email;
    private UUID id;
    private String username;
}
