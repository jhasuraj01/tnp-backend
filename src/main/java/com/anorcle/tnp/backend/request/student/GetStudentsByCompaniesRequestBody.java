package com.anorcle.tnp.backend.request.student;

import java.util.Set;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetStudentsByCompaniesRequestBody extends RequestParent {
  @NotNull(message = "Company Ids can't be null, undefined or empty string")
  private Set<Integer> companyIds;

}
