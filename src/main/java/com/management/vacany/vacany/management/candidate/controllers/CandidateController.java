package com.management.vacany.vacany.management.candidate.controllers;

import com.management.vacany.vacany.management.candidate.dto.ProfileCandidateResponseDto;
import com.management.vacany.vacany.management.candidate.entity.CandidateEntity;
import com.management.vacany.vacany.management.candidate.useCases.CreateCandidateUseCase;
import com.management.vacany.vacany.management.candidate.useCases.ListAllJobsByFilterUseCase;
import com.management.vacany.vacany.management.candidate.useCases.ProfileCandidateUseCase;
import com.management.vacany.vacany.management.job.entity.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Information candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping()
    public ResponseEntity<Object> save(@Valid @RequestBody CandidateEntity candidate){
        try {
            return ResponseEntity.ok().body(this.createCandidateUseCase.execute(candidate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil user", description = "find perfil user")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    public ResponseEntity<Object> getUser(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");
        try {
            return ResponseEntity.ok().body(this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString())));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "list vacany by filter", description = "list vacany")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter){
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

}
