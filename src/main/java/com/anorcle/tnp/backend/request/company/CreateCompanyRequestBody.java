package com.anorcle.tnp.backend.request.company;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateCompanyRequestBody extends RequestParent {

  @NotBlank(message = "Company Group can't be null, undefined or empty string")
  private String arn;

  @NotBlank(message = "First Name can't be null, undefined or empty string")
  private String name;
  private String description;
  private String websiteLink;

}
