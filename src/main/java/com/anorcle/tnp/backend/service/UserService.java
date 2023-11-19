package com.anorcle.tnp.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anorcle.tnp.backend.adapters.PasswordAdapter;
import com.anorcle.tnp.backend.model.user.User;
import com.anorcle.tnp.backend.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordAdapter passwordAdapter;

  UserService(UserRepository userRepository, PasswordAdapter passwordAdapter) {
    this.userRepository = userRepository;
    this.passwordAdapter = passwordAdapter;
  }

  public Optional<User> findByEmailAndPassword(String email, String password) {
    String passwordHash = passwordAdapter.hashPassword(password);
    return userRepository.findByEmailAndPasswordHash(email, passwordHash);
  }

}
