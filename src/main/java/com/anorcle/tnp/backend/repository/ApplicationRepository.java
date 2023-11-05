package com.anorcle.tnp.backend.repository;

import com.anorcle.tnp.backend.model.resource.Application;
import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findAllByJob(Job job);
    List<Application> findAllByStudent(Student student);
    List<Application> findAllByJob_Company(Company company);
    List<Application> findAllByStudentAndJob_Company(Student student, Company company);
}
