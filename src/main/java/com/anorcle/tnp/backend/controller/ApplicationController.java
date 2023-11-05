package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.constants.ApplicationStatus;
import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.constants.JobType;
import com.anorcle.tnp.backend.model.resource.Application;
import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.model.user.Student;
import com.anorcle.tnp.backend.request.application.CreateApplicationRequestBody;
import com.anorcle.tnp.backend.request.application.UpdateApplicationStatusRequestBody;
import com.anorcle.tnp.backend.request.standard.DeleteRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.ApplicationService;
import com.anorcle.tnp.backend.service.CompanyService;
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
import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final JobService jobService;
    private final StudentService studentService;
    private final CompanyService companyService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, JobService jobService, StudentService studentService, CompanyService companyService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.studentService = studentService;
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Application>> getAllApplications() {
        return new ResponseEntity<>(applicationService.getAllApplications(), HttpStatus.OK);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Response> getAllApplicationsByJobId(@PathVariable Integer id) {
        Optional<Job> jobOptional = jobService.getJobById(id);
        if(jobOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.JOB_NOT_FOUND, "Job Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Job job = jobOptional.get();
        List<Application> applicationsResponse = applicationService.getAllApplicationsByJob(job);

        return new ResponseEntity<>(new SuccessResponse<>(applicationsResponse), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Response> getAllApplicationsByStudentId(@PathVariable Integer id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if(studentOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.STUDENT_NOT_FOUND, "Student Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Student student = studentOptional.get();
        List<Application> applicationsResponse = applicationService.getAllApplicationsByStudent(student);

        return new ResponseEntity<>(new SuccessResponse<>(applicationsResponse), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Response> getAllApplicationsByCompanyId(@PathVariable Integer id) {
        Optional<Company> companyOptional = companyService.getCompanyById(id);
        if(companyOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.COMPANY_NOT_FOUND, "Company Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Company company = companyOptional.get();
        List<Application> applicationsResponse = applicationService.getAllApplicationsByCompany(company);

        return new ResponseEntity<>(new SuccessResponse<>(applicationsResponse), HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}/student/{studentId}")
    public ResponseEntity<Response> getAllApplicationsByCompanyAndStudentId(@PathVariable Integer companyId, @PathVariable Integer studentId) {
        Optional<Company> companyOptional = companyService.getCompanyById(companyId);
        if(companyOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.COMPANY_NOT_FOUND, "Company Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<Student> studentOptional = studentService.getStudentById(studentId);
        if(studentOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.STUDENT_NOT_FOUND, "Student Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Company company = companyOptional.get();
        Student student = studentOptional.get();

        List<Application> applicationsResponse = applicationService.getAllApplicationsByStudentAndCompany(student, company);

        return new ResponseEntity<>(new SuccessResponse<>(applicationsResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getApplicationById(@PathVariable Integer id) {
        Optional<Application> applicationOptional = applicationService.getApplicationById(id);
        if(applicationOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.APPLICATION_NOT_FOUND, "Application Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Application application = applicationOptional.get();

        return new ResponseEntity<>(new SuccessResponse<>(application), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Response> createApplication(@Valid @RequestBody CreateApplicationRequestBody applicationRequestBody) {

        Optional<Student> studentOptional = studentService.getStudentById(applicationRequestBody.getStudentId());
        if(studentOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.STUDENT_NOT_FOUND, "Student Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Student student = studentOptional.get();

        if(student.getIsBlocked()) {
            ErrorResponse errorResponse = new ErrorResponse(
                    ErrorCodeEnum.UNAUTHORIZED_TO_APPLY,
                    "Failed to apply for Job Application as you have been blocked"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Optional<Job> jobOptional = jobService.getJobById(applicationRequestBody.getJobId());
        if(jobOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.JOB_NOT_FOUND, "Job Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Job job = jobOptional.get();

        Application application = Application.builder()
                .arn(applicationRequestBody.getArn())
                .job(job)
                .student(student)
                .status(ApplicationStatus.REVIEW_PENDING)
                .build();

        Application applicationResponse = applicationService.createApplication(application);

        return new ResponseEntity<>(new SuccessResponse<>(applicationResponse), HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteApplications(@Valid @RequestBody DeleteRequestBody deleteRequestBody) {
        applicationService.deleteApplications(deleteRequestBody.getIds());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("/status")
    public ResponseEntity<Response> updateApplicationStatus(@Valid @RequestBody UpdateApplicationStatusRequestBody updateApplicationStatusRequestBody) {
        Optional<Application> applicationOptional = applicationService.getApplicationById(updateApplicationStatusRequestBody.getId());
        if(applicationOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.APPLICATION_NOT_FOUND, "Application Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Application application = applicationOptional.get();
        application.setStatus(updateApplicationStatusRequestBody.getStatus());

        if(updateApplicationStatusRequestBody.getStatus() == ApplicationStatus.OFFERED) {
            Student student = application.getStudent();
            Set<Job> offeredJobs = student.getOfferedJobs();
            offeredJobs.add(application.getJob());
            if(application.getJob().getType() == JobType.FULL_TIME) {
                student.setIsPlaced(true);
            }
        }
        else if(updateApplicationStatusRequestBody.getStatus() == ApplicationStatus.BLOCKED) {
            Student student = application.getStudent();
            student.setIsBlocked(true);
        }

        Application updatedApplication = applicationService.updateApplication(application);

        return new ResponseEntity<>(new SuccessResponse<>(updatedApplication), HttpStatus.OK);
    }
}
