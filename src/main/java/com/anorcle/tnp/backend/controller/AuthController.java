package com.anorcle.tnp.backend.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anorcle.tnp.backend.request.auth.LoginRequestBody;
import com.anorcle.tnp.backend.request.auth.RefreshTokenRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.UserService;
import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.user.User;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public ResponseEntity<Response> login(@Valid @RequestBody LoginRequestBody loginRequestBody) {
    Optional<User> userOptional = userService.findByEmailAndPassword(loginRequestBody.getEmail(),
        loginRequestBody.getPassword());

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(
          new ErrorResponse(ErrorCodeEnum.INVALID_CREDENTIAL, "email or password may be incorrect"),
          HttpStatus.UNAUTHORIZED);
    }

    User user = userOptional.get();

    return new ResponseEntity<>(new SuccessResponse<User>(user), HttpStatus.OK);
  }

  @GetMapping("/refresh-token")
  public ResponseEntity<Response> login(@Valid @RequestBody RefreshTokenRequestBody refreshTokenRequestBody) {
    return new ResponseEntity<>(new SuccessResponse<>(), HttpStatus.OK);
  }

}
