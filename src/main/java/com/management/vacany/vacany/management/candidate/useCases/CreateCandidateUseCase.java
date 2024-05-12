package com.management.vacany.vacany.management.candidate.useCases;

import com.management.vacany.vacany.management.candidate.CandidateRepository;
import com.management.vacany.vacany.management.candidate.entity.CandidateEntity;
import com.management.vacany.vacany.management.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.candidateRepository.findByUserNameOrEmail(candidateEntity.getUserName(),candidateEntity.getEmail()).ifPresent((user)->{
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        return this.candidateRepository.save(candidateEntity);
    }
}
