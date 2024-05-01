package com.management.vacany.vacany.management.candidate;

import com.management.vacany.vacany.management.candidate.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUserNameOrEmail(String username, String email);
}
