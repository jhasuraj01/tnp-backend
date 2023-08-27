package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.Job;
import com.anorcle.tnp.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Job> getJob(@PathVariable String id) {
        Job job = jobService.getJob(id);

        if(job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("/")
    public String createJob(@Valid @RequestBody Job job) {
        return jobService.createJob(job);
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
    public String deleteJob(@PathVariable String id) {
        return jobService.deleteJob(id);
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
