package com.anorcle.tnp.backend.repository;

import com.anorcle.tnp.backend.model.resource.Application;
import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findAllByJob(Job job);
}
