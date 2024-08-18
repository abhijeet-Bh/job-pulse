package com.abhijeet.jobsite.job;

import java.util.List;
import java.util.UUID;

public interface JobService {
    List<Job> findAll();

    Job createJob(Job job);

    Job getJobById(UUID id);

    boolean deleteJobById(UUID id);

    boolean updateJobById(UUID id, Job job);
}
