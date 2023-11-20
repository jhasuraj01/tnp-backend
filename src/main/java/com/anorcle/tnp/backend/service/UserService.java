package com.anorcle.tnp.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anorcle.tnp.backend.model.user.User;
import com.anorcle.tnp.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

}
