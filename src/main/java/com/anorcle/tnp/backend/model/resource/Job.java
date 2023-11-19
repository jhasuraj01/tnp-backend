package com.anorcle.tnp.backend.model.resource;

import com.anorcle.tnp.backend.model.constants.JobType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Job extends Resource {

  @NotBlank(message = "Missing Required Properties: job.title")
  private String title;

  @NotBlank(message = "Missing Required Properties: job.description")
  private String description;

  @NotNull(message = "Missing Required Properties: job.company")
  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
  private Company company;

  @NotBlank(message = "Missing Required Properties: job.location")
  private String location;

  @NotNull(message = "Missing Required Properties: job.type")
  @Enumerated(EnumType.STRING)
  private JobType type;

  @NotNull(message = "Missing Required Properties: job.requirements")
  private String[] requirements;

  @NotNull(message = "Missing Required Properties: job.isArchived")
  private boolean isArchived;

  @NotNull(message = "Missing Required Properties: job.totalSalary")
  private Integer totalSalary;

}
