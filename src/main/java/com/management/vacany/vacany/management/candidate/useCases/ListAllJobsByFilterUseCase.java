package com.management.vacany.vacany.management.candidate.useCases;

import com.management.vacany.vacany.management.job.JobRepository;
import com.management.vacany.vacany.management.job.entity.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter){
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
