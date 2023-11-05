package com.anorcle.tnp.backend.model.user;

import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.model.resource.Job;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

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
