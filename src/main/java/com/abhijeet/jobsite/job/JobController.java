package com.abhijeet.jobsite.job;

import com.abhijeet.jobsite.configs.ApiResponse;
import com.abhijeet.jobsite.configs.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Job>>> findAllJobs() {

        return new ResponseEntity<>(ApiResponse.success(jobService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Job>> creteJob(@RequestBody Job job) {

        if (Authentication.isAdmin()) {
            jobService.createJob(job);
            return new ResponseEntity<>(ApiResponse.success(job, HttpStatus.CREATED.value()),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(ApiResponse.failure(HttpStatus.UNAUTHORIZED.value(),
                    "Unauthorised Access, Please login as Admin!"),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable UUID id) {
        var job = jobService.getJobById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No job found with requested id");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable UUID id) {
        boolean isDeleted = jobService.deleteJobById(id);
        if (isDeleted) return ResponseEntity.ok("Job deleted!");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("requested job not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable UUID id, @RequestBody Job job) {
        boolean isUpdated = jobService.updateJobById(id, job);
        if (isUpdated) return ResponseEntity.ok("Job Updated!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("requested job not found!");
    }
}
