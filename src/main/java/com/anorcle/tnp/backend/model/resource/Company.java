package com.anorcle.tnp.backend.model.resource;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Company extends Resource {
  private String name;
  private String description;
  private String websiteLink;

}
