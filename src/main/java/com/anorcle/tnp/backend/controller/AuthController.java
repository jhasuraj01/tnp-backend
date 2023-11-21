package com.anorcle.tnp.backend.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anorcle.tnp.backend.adapters.PasswordAdapter;
import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.user.User;
import com.anorcle.tnp.backend.request.auth.LoginRequestBody;
import com.anorcle.tnp.backend.request.auth.RefreshTokenRequestBody;
import com.anorcle.tnp.backend.response.auth.LoginResponseBody;
import com.anorcle.tnp.backend.response.auth.RefreshTokenResponseBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.UserService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;
  private final PasswordAdapter passwordAdapter;

  public AuthController(UserService userService, PasswordAdapter passwordAdapter) {
    this.userService = userService;
    this.passwordAdapter = passwordAdapter;
  }

  @PostMapping("/login")
  public ResponseEntity<Response> login(@Valid @RequestBody LoginRequestBody loginRequestBody) {

    Optional<User> userOptional = userService.findByEmail(loginRequestBody.getEmail());

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(
          new ErrorResponse(ErrorCodeEnum.USER_NOT_FOUND, "User Not Found"),
          HttpStatus.UNAUTHORIZED);
    }

    User user = userOptional.get();
    String passwordHash = user.getPasswordHash();

    Boolean isPasswordCorrect = passwordAdapter.matchPassword(loginRequestBody.getPassword(), passwordHash);

    if (!isPasswordCorrect) {
      return new ResponseEntity<>(
          new ErrorResponse(ErrorCodeEnum.INVALID_CREDENTIAL, "Incorrect Password"),
          HttpStatus.UNAUTHORIZED);
    }

    LoginResponseBody loginResponseBody = LoginResponseBody.builder()
        .id(user.getId())
        .email(user.getEmail())
        .userGroup(user.getUserGroup())
        .refreshToken("random-refresh-token")
        .authToken("random-auth-token")
        .build();

    return new ResponseEntity<>(new SuccessResponse<LoginResponseBody>(loginResponseBody), HttpStatus.OK);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<Response> refreshToken(@Valid @RequestBody RefreshTokenRequestBody refreshTokenRequestBody) {

    RefreshTokenResponseBody refreshTokenResponseBody = RefreshTokenResponseBody.builder()
        .authToken("random-auth-token")
        .build();

    return new ResponseEntity<>(new SuccessResponse<RefreshTokenResponseBody>(refreshTokenResponseBody), HttpStatus.OK);
  }

}
