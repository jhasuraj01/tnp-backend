package com.anorcle.tnp.backend.request.job;

import com.anorcle.tnp.backend.model.constants.JobType;
import com.anorcle.tnp.backend.request.RequestParent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateJobRequestBody extends RequestParent {

    @NotBlank(message = "arn can't be null, undefined or empty string")
    private String arn;

    @NotBlank(message = "Title can't be null, undefined or empty string")
    private String title;

    @NotBlank(message = "Description can't be null, undefined or empty string")
    private String description;

    @NotBlank(message = "Company can't be null, undefined or empty string")
    private String company;

    @NotBlank(message = "Location can't be null, undefined or empty string")
    private String location;

    @NotNull(message = "Type can't be null, undefined or empty string")
    private JobType type;

    @NotNull(message = "Requirements can't be null or undefined")
    private String[] requirements;

}