package com.anorcle.tnp.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.request.job.CreateJobRequestBody;
import com.anorcle.tnp.backend.request.job.UpdateJobRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.CompanyService;
import com.anorcle.tnp.backend.service.JobService;

import jakarta.validation.Valid;

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
    Optional<Job> job = jobService.getJobById(id);

    return job
        .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

  @PostMapping("/")
  public ResponseEntity<Response> createJob(@Valid @RequestBody CreateJobRequestBody jobRequestBody) {
    Optional<Company> companyOptional = companyService.getCompanyById(jobRequestBody.getCompanyId());

    if (companyOptional.isEmpty()) {
      ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.COMPANY_NOT_FOUND, "Company Not Found");
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

  @PutMapping("/")
  public ResponseEntity<Response> updateJob(@Valid @RequestBody UpdateJobRequestBody updateJobRequestBody) {

    Optional<Job> jobOptional = jobService.getJobById(updateJobRequestBody.getId());
    if (jobOptional.isEmpty()) {
      ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.JOB_NOT_FOUND, "Job Not Found");
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    Job job = jobOptional.get();

    if (updateJobRequestBody.getArn() != null)
      job.setArn(updateJobRequestBody.getArn());
    if (updateJobRequestBody.getTitle() != null)
      job.setTitle(updateJobRequestBody.getTitle());
    if (updateJobRequestBody.getDescription() != null)
      job.setDescription(updateJobRequestBody.getDescription());
    if (updateJobRequestBody.getLocation() != null)
      job.setLocation(updateJobRequestBody.getLocation());
    if (updateJobRequestBody.getType() != null)
      job.setType(updateJobRequestBody.getType());
    if (updateJobRequestBody.getRequirements() != null)
      job.setRequirements(updateJobRequestBody.getRequirements());
    if (updateJobRequestBody.getTotalSalary() != null)
      job.setTotalSalary(updateJobRequestBody.getTotalSalary());

    Job updateJob = jobService.updateJob(job);

    return new ResponseEntity<>(new SuccessResponse<>(updateJob), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response> deleteJob(@PathVariable Integer id) {
    boolean isDeleted = jobService.deleteJob(id);
    if (!isDeleted) {
      ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.JOB_NOT_FOUND, "Job Not Found");
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    SuccessResponse response = new SuccessResponse();
    return new ResponseEntity<>(response, HttpStatus.OK);
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
