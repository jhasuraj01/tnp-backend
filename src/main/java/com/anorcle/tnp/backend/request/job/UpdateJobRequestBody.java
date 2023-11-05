package com.anorcle.tnp.backend.request.job;

import com.anorcle.tnp.backend.model.constants.JobType;
import com.anorcle.tnp.backend.request.RequestParent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateJobRequestBody extends RequestParent {
    @NotNull(message = "Job id can't be null or undefined")
    private Integer id;
    private String arn;
    private String title;
    private String description;
    private String location;
    private JobType type;
    private String[] requirements;
    private Integer totalSalary;
}