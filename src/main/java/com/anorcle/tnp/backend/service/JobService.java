package com.anorcle.tnp.backend.service;

import com.anorcle.tnp.backend.model.resource.Job;
import com.anorcle.tnp.backend.repository.JobRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public boolean updateJob(String id, Job job) {
        if(!jobRepository.existsById(job.getId())) {
            return false;
        }
        jobRepository.save(job);
        return true;
    }

    public boolean deleteJob(Integer id) {
        jobRepository.deleteById(id);
        return true;
    }

    public String applyForJob(String id) {
        // Apply for job by id
        return "Applied for job";
    }

    public String undoApplyForJob(String id) {
        // Undo apply for job by id
        return "Application withdrawn";
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
