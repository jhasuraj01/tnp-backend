package com.anorcle.tnp.backend.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestParent {
  @NotBlank(message = "Missing Required Field: body.organizationId")
  private String organizationId;

}
