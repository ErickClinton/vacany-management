package com.management.vacany.vacany.management.job;

import com.management.vacany.vacany.management.job.entity.CreateJobDto;
import com.management.vacany.vacany.management.job.entity.JobEntity;
import com.management.vacany.vacany.management.job.useCases.CreateJobUseCase;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "vacany", description = "Information vacany")
    @Operation(summary = "register vacany", description = "register vacany")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation =  JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobEntity create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");
        var jobEntity=JobEntity.builder().benefits(createJobDto.getBenefits()).description(createJobDto.getDescription()).level(createJobDto.getLevel()).build();
        jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        return this.createJobUseCase.execute(jobEntity);
    }
}
