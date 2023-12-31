package com.anorcle.tnp.backend.request.student;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateStudentRequestBody extends RequestParent {
  @NotNull(message = "User Id can't be null, undefined or empty string")
  private Integer Id;
  private String userGroup;
  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
  private String prn;
  private Boolean isBlocked;

}
