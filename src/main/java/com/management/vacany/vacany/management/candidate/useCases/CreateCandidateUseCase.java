package com.management.vacany.vacany.management.candidate.useCases;

import com.management.vacany.vacany.management.candidate.CandidateRepository;
import com.management.vacany.vacany.management.candidate.entity.CandidateEntity;
import com.management.vacany.vacany.management.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate){
        this.candidateRepository.findByUserNameOrEmail(candidate.getUserName(),candidate.getEmail()).ifPresent((user)->{
            throw new UserFoundException();
        });
        return this.candidateRepository.save(candidate);
    }
}
