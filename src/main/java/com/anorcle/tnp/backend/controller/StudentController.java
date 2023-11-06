package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.model.user.Student;
import com.anorcle.tnp.backend.request.standard.DeleteRequestBody;
import com.anorcle.tnp.backend.request.student.CreateStudentRequestBody;
import com.anorcle.tnp.backend.request.student.GetStudentsByCompaniesRequestBody;
import com.anorcle.tnp.backend.request.student.GetStudentsByCompanyRequestBody;
import com.anorcle.tnp.backend.request.student.UpdateStudentRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.CompanyService;
import com.anorcle.tnp.backend.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final CompanyService companyService;

    @Autowired
    public StudentController(StudentService studentService, CompanyService companyService) {
        this.studentService = studentService;
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }
    @GetMapping("/byCompany")
    public ResponseEntity<Response> getAllStudentsByCompany(@Valid @RequestBody GetStudentsByCompanyRequestBody getStudentsByCompanyRequestBody) {
        Optional<Company> companyOptional = companyService.getCompanyById(getStudentsByCompanyRequestBody.getCompanyId());

        if(companyOptional.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.COMPANY_NOT_FOUND, "Company Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        Company company = companyOptional.get();
        List<Student> students = studentService.getStudentsByCompany(company);
        return new ResponseEntity<>(new SuccessResponse<>(students), HttpStatus.OK);
    }
    @GetMapping("/byCompanies")
    public ResponseEntity<Response> getAllStudentsByCompanies(@Valid @RequestBody GetStudentsByCompaniesRequestBody getStudentsByCompaniesRequestBody) {
        List<Company> companies = companyService.getCompaniesByIds(getStudentsByCompaniesRequestBody.getCompanyIds());
        Set<Company> companiesSet = new HashSet<>(companies);
        List<Student> students = studentService.getStudentsByCompanies(companiesSet);
        return new ResponseEntity<>(new SuccessResponse<>(students), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<List<Student>> createStudents(@Valid @RequestBody List<CreateStudentRequestBody> studentRequestBodies) {
        List<Student> students = studentRequestBodies.stream().map(studentRequestBody ->
            Student.builder()
                    .userGroup(studentRequestBody.getUserGroup())
                    .email(studentRequestBody.getEmail())
                    .prn(studentRequestBody.getPrn())
                    .firstName(studentRequestBody.getFirstName())
                    .middleName(studentRequestBody.getMiddleName())
                    .lastName(studentRequestBody.getLastName())
                    .isBlocked(false)
                    .isPlaced(false)
                    .offeredJobs(new HashSet<>())
                    .build()
        ).collect(Collectors.toList());
        return new ResponseEntity<>(studentService.createStudents(students), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<List<Response>> updateStudents(@Valid @RequestBody ArrayList<UpdateStudentRequestBody> updateStudentRequestBodies) {

        List<Response> responses = new ArrayList<>();

        for (int i = 0; i < updateStudentRequestBodies.size(); ++i) {
            UpdateStudentRequestBody updateStudentRequestBody = updateStudentRequestBodies.get(i);

            Optional<Student> studentOptional = studentService.getStudentById(updateStudentRequestBody.getId());
            if(studentOptional.isEmpty()) {
                responses.add(new ErrorResponse(ErrorCodeEnum.STUDENT_NOT_FOUND, "Student Not Found"));
                continue;
            }
            Student student = studentOptional.get();
            updateStudentProperties(student, updateStudentRequestBody);
            studentService.updateStudent(student);
            responses.add(new SuccessResponse());
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    private void updateStudentProperties(Student student, UpdateStudentRequestBody updateStudentRequestBody) {
        if(updateStudentRequestBody.getFirstName() != null)
            student.setFirstName(updateStudentRequestBody.getFirstName());
        if(updateStudentRequestBody.getLastName() != null)
            student.setLastName(updateStudentRequestBody.getLastName());
        if(updateStudentRequestBody.getMiddleName() != null)
            student.setMiddleName(updateStudentRequestBody.getMiddleName());
        if(updateStudentRequestBody.getEmail() != null)
            student.setEmail(updateStudentRequestBody.getEmail());
        if(updateStudentRequestBody.getPrn() != null)
            student.setPrn(updateStudentRequestBody.getPrn());
        if(updateStudentRequestBody.getUserGroup() != null)
            student.setUserGroup(updateStudentRequestBody.getUserGroup());
        if(updateStudentRequestBody.getIsBlocked() != null)
            student.setIsBlocked(updateStudentRequestBody.getIsBlocked());
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteStudents(@Valid @RequestBody DeleteRequestBody deleteRequestBody) {
        studentService.deleteStudents(deleteRequestBody.getIds());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
