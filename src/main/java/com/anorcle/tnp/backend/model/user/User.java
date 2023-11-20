package com.anorcle.tnp.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties("passwordHash")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull(message = "Missing Required Properties: user.id")
  private Integer id;

  @Email(message = "Invalid Value: user.email")
  @Column(unique = true)
  private String email;

  @NotEmpty(message = "Missing Required Properties: user.userGroup")
  private String userGroup;

  @NotEmpty(message = "Missing Required Properties: user.passwordHash")
  private String passwordHash;

  @NotBlank(message = "Missing Required Properties: user.firstName")
  private String firstName;
  private String lastName;

}
