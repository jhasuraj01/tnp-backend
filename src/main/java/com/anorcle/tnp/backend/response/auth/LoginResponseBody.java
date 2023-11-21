package com.anorcle.tnp.backend.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseBody {
  private Integer id;
  private String email;
  private String userGroup;
  private String authToken;
  private String refreshToken;
}
