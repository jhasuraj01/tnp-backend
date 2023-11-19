package com.anorcle.tnp.backend.request.application;

import com.anorcle.tnp.backend.model.constants.ApplicationStatus;
import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateApplicationStatusRequestBody extends RequestParent {

  @NotNull(message = "Application Id can't be null, undefined or empty string")
  private Integer id;

  @NotNull(message = "Application Status can't be null, undefined or empty string")
  private ApplicationStatus status;

}
