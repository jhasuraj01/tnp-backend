package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.request.job.CreateJobRequestBody;
import com.anorcle.tnp.backend.response.standard.EmptyResponseBody;
import com.anorcle.tnp.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Integer id) {
        Optional<Job> job = jobService.getJob(id);

        return job
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/")
    public ResponseEntity<Job> createJob(@Valid @RequestBody CreateJobRequestBody jobRequestBody) {
        Job job = Job.builder()
                .title(jobRequestBody.getTitle())
                .description(jobRequestBody.getDescription())
                .company(jobRequestBody.getCompany())
                .location(jobRequestBody.getLocation())
                .type(jobRequestBody.getType())
                .requirements(jobRequestBody.getRequirements())
                .isArchived(false)
                .build();
        Job jobCreatedResponse = jobService.createJob(job, jobRequestBody.getOrganizationId());
        return new ResponseEntity(jobCreatedResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@PathVariable String id, @RequestBody Job job) {
        boolean isJobUpdated = jobService.updateJob(id, job);

        if(!isJobUpdated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmptyResponseBody> deleteJob(@PathVariable Integer id) {
        boolean isDeleted = jobService.deleteJob(id);
        if(!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/apply")
    public String applyForJob(@PathVariable String id) {
        return jobService.applyForJob(id);
    }

    @DeleteMapping("/{id}/apply")
    public String undoApplyForJob(@PathVariable String id) {
        return jobService.undoApplyForJob(id);
    }

    @PostMapping("/{id}/archive")
    public String archiveJob(@PathVariable String id) {
        return jobService.archiveJob(id);
    }

    @PostMapping("/{id}/unarchive")
    public String unarchiveJob(@PathVariable String id) {
        return jobService.unarchiveJob(id);
    }
}
