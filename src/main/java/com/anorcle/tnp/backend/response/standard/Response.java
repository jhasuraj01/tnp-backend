package com.anorcle.tnp.backend.response.standard;

import lombok.Data;

@Data
public class Response {
  private final Boolean ok;

  public Response() {
    this.ok = false;
  }

  public Response(Boolean ok) {
    this.ok = ok;
  }

}
