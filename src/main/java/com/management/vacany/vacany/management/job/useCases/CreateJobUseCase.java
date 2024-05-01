package com.management.vacany.vacany.management.job.useCases;

import com.management.vacany.vacany.management.job.JobRepository;
import com.management.vacany.vacany.management.job.entity.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }
}
