package com.anorcle.tnp.backend.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCodeEnum {
  INVALID_CREDENTIAL("INVALID_CREDENTIAL"),
  APPLICATION_NOT_FOUND("APPLICATION_NOT_FOUND"),
  COMPANY_NOT_FOUND("COMPANY_NOT_FOUND"),
  STAFF_NOT_FOUND("STAFF_NOT_FOUND"),
  STUDENT_NOT_FOUND("STUDENT_NOT_FOUND"),
  UNAUTHORIZED_TO_APPLY("UNAUTHORIZED_TO_APPLY"),
  USER_NOT_FOUND("USER_NOT_FOUND"),
  JOB_NOT_FOUND("JOB_NOT_FOUND"),
  NOT_FOUND("NOT_FOUND");

  @Getter
  private String value;

}
