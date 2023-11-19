package com.anorcle.tnp.backend.model.resource;

import com.anorcle.tnp.backend.model.constants.ApplicationStatus;
import com.anorcle.tnp.backend.model.user.Student;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application extends Resource {

  @NotNull(message = "Missing Required Properties: application.job")
  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
  private Job job;

  @NotNull(message = "Missing Required Properties: application.student")
  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
  private Student student;

  @NotNull(message = "Missing Required Properties: application.status")
  private ApplicationStatus status;

}
