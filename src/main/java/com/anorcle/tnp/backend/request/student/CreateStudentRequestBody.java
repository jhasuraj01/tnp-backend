package com.anorcle.tnp.backend.request.student;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateStudentRequestBody extends RequestParent {

  @NotBlank(message = "User Group can't be null, undefined or empty string")
  private String userGroup;

  @NotBlank(message = "First Name can't be null, undefined or empty string")
  private String firstName;
  private String lastName;
  private String middleName;

  @NotBlank(message = "Email can't be null, undefined or empty string")
  private String email;
  @NotBlank(message = "Password can't be null, undefined or empty string")
  private String password;
  @NotBlank(message = "PRN can't be null, undefined or empty string")
  private String prn;

}
