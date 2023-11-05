package com.anorcle.tnp.backend.model.user;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends User {
    @NotBlank(message = "Missing Required Properties: staff.firstName")
    private String firstName;
    private String lastName;
}
