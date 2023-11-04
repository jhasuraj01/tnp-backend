package com.anorcle.tnp.backend.model.resource;

import jakarta.persistence.*;
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
public class Resource {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull(message = "Missing Required Properties: resource.id")
    private Integer id;

    @NotNull(message = "Missing Required Properties: resource.arn")
    @Column(unique = true)
    private String arn;
}
