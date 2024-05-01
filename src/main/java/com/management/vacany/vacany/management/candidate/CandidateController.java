package com.management.vacany.vacany.management.candidate;

import com.management.vacany.vacany.management.candidate.entity.CandidateEntity;
import com.management.vacany.vacany.management.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping()
    public ResponseEntity<Object> save(@Valid @RequestBody CandidateEntity candidate){
        try {
            return ResponseEntity.ok().body(this.createCandidateUseCase.execute(candidate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
