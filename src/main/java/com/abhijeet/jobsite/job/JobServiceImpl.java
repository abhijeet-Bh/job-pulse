package com.abhijeet.jobsite.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private long jobId = 1L;

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

    @Override
    public Job getJobById(long id) {
        for (Job job : jobs)
            if (job.getId() == id) return job;
        return null;
    }

    @Override
    public boolean deleteJobById(long id) {
        Job job = getJobById(id);
        if (job != null) {
            jobs.remove(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJobById(long id, Job job) {
        for (Job targetJob : jobs) {
            if (targetJob.getId() == id) {
                targetJob.setTitle(job.getTitle());
                targetJob.setLocation(job.getLocation());
                targetJob.setDescription(job.getDescription());
                targetJob.setMaxSalary(job.getMaxSalary());
                targetJob.setMinSalary(job.getMinSalary());
                return true;
            }
        }
        return false;
    }
}
