package com.anorcle.tnp.backend.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.user.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
  List<Student> findAllByOfferedJobs_Company(Company company);

  List<Student> findAllByOfferedJobs_CompanyIn(Set<Company> companies);

}
