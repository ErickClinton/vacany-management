package com.management.vacany.vacany.management.candidate.useCases;

import com.management.vacany.vacany.management.candidate.CandidateRepository;
import com.management.vacany.vacany.management.candidate.dto.ProfileCandidateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase{

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        var candidateDto = ProfileCandidateResponseDto.builder()
                .description(candidate.getDescription())
                .username(candidate.getUserName())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();

        return candidateDto;
    }

}
