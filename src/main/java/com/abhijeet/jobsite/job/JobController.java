package com.abhijeet.jobsite.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAllJobs() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creteJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("New Job Created!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable long id) {
        var job = jobService.getJobById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No job found with requested id");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable long id) {
        boolean isDeleted = jobService.deleteJobById(id);
        if (isDeleted) return ResponseEntity.ok("Job deleted!");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("requested job not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job job) {
        boolean isUpdated = jobService.updateJobById(id, job);
        if (isUpdated) return ResponseEntity.ok("Job Updated!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("requested job not found!");
    }
}
