package com.anorcle.tnp.backend.request.auth;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestBody extends RequestParent {

  @NotEmpty(message = "Email can't be null, undefined or empty string")
  private String email;

  @NotEmpty(message = "Password can't be null, undefined or empty string")
  private String password;

}
