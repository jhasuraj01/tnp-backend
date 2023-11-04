package com.anorcle.tnp.backend.repository;

import com.anorcle.tnp.backend.model.resource.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Integer> {

}
