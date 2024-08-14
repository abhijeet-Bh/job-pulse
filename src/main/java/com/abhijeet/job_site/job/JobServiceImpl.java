package com.abhijeet.job_site.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private int jobId = 1;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job createJob(Job job) {
        job.setId(jobId++);
        jobs.add(job);
        return job;
    }
}
