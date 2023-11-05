package com.anorcle.tnp.backend.service;

import com.anorcle.tnp.backend.model.resource.Application;
import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    @Autowired
    ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }
    public Application updateApplication(Application application) {
        return applicationRepository.save(application);
    }
    public void deleteApplications(List<Integer> ids) {
        applicationRepository.deleteAllById(ids);
    }
    public Optional<Application> getApplicationById(Integer id) {
        return applicationRepository.findById(id);
    }
    public List<Application> getApplicationsByJobId(Job job) {
        return applicationRepository.findAllByJob(job);
    }
}