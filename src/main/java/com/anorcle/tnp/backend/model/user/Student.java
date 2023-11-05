package com.anorcle.tnp.backend.model.user;

import com.anorcle.tnp.backend.model.doc.Resume;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
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

    private Boolean isBanned;
}
