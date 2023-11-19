package com.anorcle.tnp.backend.model.user;

import java.util.Set;

import com.anorcle.tnp.backend.model.resource.Job;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
  @NotBlank(message = "Missing Required Properties: user.firstName")
  @Column(unique = true)
  private String prn;

  @NotBlank(message = "Missing Required Properties: student.firstName")
  private String firstName;
  private String middleName;
  private String lastName;

  @NotNull(message = "Missing Required Properties: student.isBlocked")
  private Boolean isBlocked;

  @NotNull(message = "Missing Required Properties: student.isPlaced")
  private Boolean isPlaced;

  @NotNull(message = "Missing Required Properties: student.jobs[]")
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private Set<Job> offeredJobs;

}
