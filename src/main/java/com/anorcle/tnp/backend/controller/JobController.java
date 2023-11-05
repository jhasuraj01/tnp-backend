package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.request.job.CreateJobRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.CompanyService;
import com.anorcle.tnp.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;
    private final CompanyService companyService;

    @Autowired
    public JobController(JobService jobService, CompanyService companyService) {
        this.jobService = jobService;
        this.companyService = companyService;
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
    public ResponseEntity<Response> createJob(@Valid @RequestBody CreateJobRequestBody jobRequestBody) {
        Optional<Company> companyOptional = companyService.getCompanyById(jobRequestBody.getCompanyId());

        if(companyOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("COMPANY_NOT_FOUND", "Company Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        Company company = companyOptional.get();

        Job job = Job.builder()
                .arn(jobRequestBody.getArn())
                .title(jobRequestBody.getTitle())
                .description(jobRequestBody.getDescription())
                .company(company)
                .location(jobRequestBody.getLocation())
                .type(jobRequestBody.getType())
                .requirements(jobRequestBody.getRequirements())
                .isArchived(false)
                .totalSalary(jobRequestBody.getTotalSalary())
                .build();
        Job jobCreatedResponse = jobService.createJob(job);
        return new ResponseEntity(new SuccessResponse<Job>(jobCreatedResponse), HttpStatus.CREATED);
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
    public ResponseEntity<Response> deleteJob(@PathVariable Integer id) {
        boolean isDeleted = jobService.deleteJob(id);
        if(!isDeleted) {
            ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", "Job Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        SuccessResponse response = new SuccessResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
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
