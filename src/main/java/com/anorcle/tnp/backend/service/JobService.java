package com.anorcle.tnp.backend.service;

import com.anorcle.tnp.backend.model.Job;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final List<Job> jobs = new ArrayList<>();

    public List<Job> getAllJobs() {
        return jobs;
    }

    public Job getJob(String id) {
        Optional<Job> job = jobs.stream().filter(j -> j.getId().equals(id)).findFirst();
        return job.orElse(null);
    }

    public String createJob(Job job) {
        jobs.add(job);
        return "Job created";
    }

    public boolean updateJob(String id, Job job) {
        int index = jobs.indexOf(getJob(id));
        if (index >= 0) {
            jobs.set(index, job);
            return true;
        } else {
            return false;
        }
    }

    public String deleteJob(String id) {
        Job job = getJob(id);
        if (job != null) {
            jobs.remove(job);
            return "Job deleted";
        } else {
            return "Job not found";
        }
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
