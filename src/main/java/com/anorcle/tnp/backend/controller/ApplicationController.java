package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.resource.Application;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.model.user.Student;
import com.anorcle.tnp.backend.request.application.CreateApplicationRequestBody;
import com.anorcle.tnp.backend.request.standard.DeleteRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.ApplicationService;
import com.anorcle.tnp.backend.service.JobService;
import com.anorcle.tnp.backend.service.StudentService;
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
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final JobService jobService;
    private final StudentService studentService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, JobService jobService, StudentService studentService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Application>> getAllApplications() {
        return new ResponseEntity<>(applicationService.getAllApplications(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAllApplicationById(@PathVariable Integer id) {
        Optional<Application> applicationOptional = applicationService.getApplicationById(id);
        if(applicationOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.APPLICATION_NOT_FOUND, "Application Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Application application = applicationOptional.get();

        return new ResponseEntity<>(new SuccessResponse<>(application), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Response> createApplications(@Valid @RequestBody CreateApplicationRequestBody applicationRequestBody) {

        Optional<Job> jobOptional = jobService.getJobById(applicationRequestBody.getJobId());
        if(jobOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.JOB_NOT_FOUND, "Job Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Optional<Student> studentOptional = studentService.getStudentById(applicationRequestBody.getStudentId());
        if(studentOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.STUDENT_NOT_FOUND, "Student Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Job job = jobOptional.get();
        Student student = studentOptional.get();

        Application application = Application.builder()
                .arn(applicationRequestBody.getArn())
                .job(job)
                .student(student)
                .build();

        Application applicationResponse = applicationService.createApplication(application);

        return new ResponseEntity<>(new SuccessResponse<>(applicationResponse), HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteApplications(@Valid @RequestBody DeleteRequestBody deleteRequestBody) {
        applicationService.deleteApplications(deleteRequestBody.getIds());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
