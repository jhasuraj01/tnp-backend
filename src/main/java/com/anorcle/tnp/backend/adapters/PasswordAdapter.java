package com.anorcle.tnp.backend.adapters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PasswordAdapter {
  private final PasswordEncoder passwordEncoder;

  public PasswordAdapter() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public String hashPassword(String password) {
    return this.passwordEncoder.encode(password);
  }

  public Boolean matchPassword(String password, String passwordHash) {
    return this.passwordEncoder.matches(password, passwordHash);
  }

  public PasswordEncoder getEncoder() {
    return this.passwordEncoder;
  }

}
