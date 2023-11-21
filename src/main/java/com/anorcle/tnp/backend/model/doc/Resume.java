package com.anorcle.tnp.backend.model.doc;

import com.anorcle.tnp.backend.model.resource.Resource;

import jakarta.persistence.Entity;
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
public class Resume extends Resource {
  private String url;
  private Integer createdAt;
}
