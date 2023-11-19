package com.anorcle.tnp.backend.request.student;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetStudentsByCompanyRequestBody extends RequestParent {
  @NotNull(message = "Company Id can't be null, undefined or empty string")
  private Integer companyId;

}
