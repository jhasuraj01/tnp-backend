package com.anorcle.tnp.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anorcle.tnp.backend.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);
}
