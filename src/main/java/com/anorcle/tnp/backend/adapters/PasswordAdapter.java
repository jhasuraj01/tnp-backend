package com.anorcle.tnp.backend.adapters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PasswordAdapter {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public PasswordAdapter() {
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  public String hashPassword(String password) {
    return this.bCryptPasswordEncoder.encode(password);
  }

  public Boolean matchPassword(String password, String passwordHash) {
    return this.bCryptPasswordEncoder.matches(password, passwordHash);
  }

}
