package com.anorcle.tnp.backend.model.doc;

import com.anorcle.tnp.backend.model.resource.Resource;

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
public class Resume extends Resource {
  private String url;
  private Integer createdAt;
}
