package com.abhijeet.jobsite.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    Job createJob(Job job);

    Job getJobById(long id);

    boolean deleteJobById(long id);

    boolean updateJobById(long id, Job job);
}
