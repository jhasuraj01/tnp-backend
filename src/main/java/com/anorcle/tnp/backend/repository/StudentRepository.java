package com.anorcle.tnp.backend.repository;

import com.anorcle.tnp.backend.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<User, Integer> {

}
