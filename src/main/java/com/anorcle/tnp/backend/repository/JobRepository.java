package com.anorcle.tnp.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.anorcle.tnp.backend.model.resource.Job;

public interface JobRepository extends CrudRepository<Job, Integer> {

}
