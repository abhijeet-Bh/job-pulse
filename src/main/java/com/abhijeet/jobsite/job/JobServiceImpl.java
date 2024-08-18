package com.abhijeet.jobsite.job;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    @Override
    public Job getJobById(long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJobById(long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job targetJob = jobOptional.get();
            targetJob.setTitle(job.getTitle());
            targetJob.setLocation(job.getLocation());
            targetJob.setDescription(job.getDescription());
            targetJob.setMaxSalary(job.getMaxSalary());
            targetJob.setMinSalary(job.getMinSalary());
            jobRepository.save(targetJob);
            return true;
        }
        return false;
    }
}
