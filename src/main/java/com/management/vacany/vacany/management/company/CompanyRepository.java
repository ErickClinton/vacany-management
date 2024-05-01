package com.management.vacany.vacany.management.company;

import com.management.vacany.vacany.management.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUserNameOrEmail(String username, String email);
    Optional<CompanyEntity> findByUserName(String username);
}
