package com.management.vacany.vacany.management.job;

import com.management.vacany.vacany.management.job.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
