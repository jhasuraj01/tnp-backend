package com.anorcle.tnp.backend.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull(message = "Missing Required Properties: user.id")
    private Integer id;

    @NotBlank(message = "Missing Required Properties: user.firstName")
    private String firstName;
    private String middleName;
    private String lastName;

    @Email(message = "Invalid Value: user.email")
    private String email;
}
