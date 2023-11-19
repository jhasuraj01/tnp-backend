package com.anorcle.tnp.backend.response.auth;

import lombok.Data;

@Data
public class LoginResponseBody {
  private Integer id;
  private String email;
  private String userGroup;
  private String passwordHash;
  private String authToken;
  private String refreshToken;
}
