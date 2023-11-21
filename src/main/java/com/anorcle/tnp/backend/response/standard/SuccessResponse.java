package com.anorcle.tnp.backend.response.standard;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse<T> extends Response {
  private final Boolean ok = true;
  private T result;

  public SuccessResponse(T result) {
    super(true);
    this.result = result;
  }

  public SuccessResponse() {
    super(true);
    this.result = null;
  }

}
