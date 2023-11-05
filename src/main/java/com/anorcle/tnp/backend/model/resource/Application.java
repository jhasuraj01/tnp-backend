package com.anorcle.tnp.backend.model.resource;

import com.anorcle.tnp.backend.model.user.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application extends Resource {

    @NotNull(message = "Missing Required Properties: application.job")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Job job;

    @NotNull(message = "Missing Required Properties: application.student")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Student student;
}
