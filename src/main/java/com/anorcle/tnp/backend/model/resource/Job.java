package com.anorcle.tnp.backend.model.resource;

import com.anorcle.tnp.backend.model.constants.JobType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Job extends Resource {

    @NotBlank(message = "Missing Required Properties: job.title")
    private String title;

    @NotBlank(message = "Missing Required Properties: job.description")
    private String description;

    @NotNull(message = "Missing Required Properties: job.company")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private Company company;

    @NotBlank(message = "Missing Required Properties: job.location")
    private String location;

    @NotNull(message = "Missing Required Properties: job.type")
    @Enumerated(EnumType.STRING)
    private JobType type;

    @NotNull(message = "Missing Required Properties: job.requirements")
    private String[] requirements;

    @NotNull(message = "Missing Required Properties: job.isArchived")
    private boolean isArchived;

    @NotNull(message = "Missing Required Properties: job.totalSalary")
    private Integer totalSalary;
}
