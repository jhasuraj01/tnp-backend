package com.anorcle.tnp.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.user.Student;
import com.anorcle.tnp.backend.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> createStudents(List<Student> students) {
    return studentRepository.saveAll(students);
  }

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Optional<Student> getStudentById(Integer id) {
    return studentRepository.findById(id);
  }

  public List<Student> getStudentsByCompany(Company company) {
    return studentRepository.findAllByOfferedJobs_Company(company);
  }

  public List<Student> getStudentsByCompanies(Set<Company> companies) {
    return studentRepository.findAllByOfferedJobs_CompanyIn(companies);
  }

  public Student updateStudent(Student student) {
    return studentRepository.save(student);
  }

  public void deleteStudents(List<Integer> ids) {
    studentRepository.deleteAllById(ids);
  }

}
