package com.abhijeet.jobsite.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> creteJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("New Job Created!", HttpStatus.OK);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id) {
        var job = jobService.getJobById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable long id) {
        boolean isDeleted = jobService.deleteJobById(id);
        if (isDeleted) return ResponseEntity.ok("Job deleted!");
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job job) {
        boolean isUpdated = jobService.updateJobById(id, job);
        if (isUpdated) return ResponseEntity.ok("Job Updated!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
