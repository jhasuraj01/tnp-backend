package com.anorcle.tnp.backend.request.auth;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RefreshTokenRequestBody extends RequestParent {

  @NotEmpty(message = "Refresh Token can't be null, undefined or empty string")
  private String refreshToken;

}
