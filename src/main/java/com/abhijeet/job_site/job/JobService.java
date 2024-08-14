package com.abhijeet.job_site.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    Job createJob(Job job);
}
