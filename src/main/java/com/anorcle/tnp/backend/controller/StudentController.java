package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.user.Student;
import com.anorcle.tnp.backend.request.student.CreateStudentRequestBody;
import com.anorcle.tnp.backend.request.student.UpdateStudentRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
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
                    .isBanned(false)
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
                responses.add(new ErrorResponse("NOT_FOUND", "Student Not Found"));
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
    }

    @DeleteMapping("/delete/bulk")
    public ResponseEntity<HttpStatus> deleteStudents(@Valid @RequestBody List<Integer> ids) {
        studentService.deleteStudents(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
