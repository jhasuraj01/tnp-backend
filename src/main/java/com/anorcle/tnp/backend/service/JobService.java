package com.anorcle.tnp.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.repository.JobRepository;

@Service
public class JobService {

  private final JobRepository jobRepository;

  public JobService(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  public List<Job> getAllJobs() {
    List<Job> list = new ArrayList<>();
    jobRepository.findAll().forEach(list::add);
    return list;
  }

  public Optional<Job> getJobById(Integer id) {
    Optional<Job> job = jobRepository.findById(id);
    return job;
  }

  public Job createJob(Job job) {
    return jobRepository.save(job);
  }

  public Job updateJob(Job job) {
    return jobRepository.save(job);
  }

  public boolean deleteJob(Integer id) {
    jobRepository.deleteById(id);
    return true;
  }

  public String archiveJob(String id) {
    // Archive job by id
    return "Job archived";
  }

  public String unarchiveJob(String id) {
    // Make job private by id
    return "Job unarchived";
  }

}
