package com.anorcle.tnp.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anorcle.tnp.backend.model.user.User;
import com.anorcle.tnp.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isEmpty()) {
      throw new UsernameNotFoundException(String.format("User with Email (%s) Not Found!", email));
    }

    User user = userOptional.get();

    return user;
  }

}
