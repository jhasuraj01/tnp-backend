package com.anorcle.tnp.backend.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum JobType {
  FULL_TIME("FULL_TIME"),
  INTERNSHIP("INTERNSHIP");

  @Getter
  private String value;

}
