package com.anorcle.tnp.backend.response.auth;

import lombok.Data;

@Data
public class RefreshTokenResponseBody {
  private String authToken;
}
