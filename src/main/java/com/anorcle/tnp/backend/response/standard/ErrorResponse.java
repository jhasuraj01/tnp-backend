package com.anorcle.tnp.backend.response.standard;

import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;

import lombok.Data;

@Data
public class ErrorResponse extends Response {
  private final String errorMessage;
  private final ErrorCodeEnum errorCode;

  public ErrorResponse(ErrorCodeEnum errorCode, String errorMessage) {
    super(false);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
