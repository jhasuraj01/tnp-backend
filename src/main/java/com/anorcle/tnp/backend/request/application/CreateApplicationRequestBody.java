package com.anorcle.tnp.backend.request.application;

import com.anorcle.tnp.backend.request.RequestParent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateApplicationRequestBody extends RequestParent {

    @NotBlank(message = "Application Group can't be null, undefined or empty string")
    private String arn;

    @NotNull(message = "Job Id can't be null, undefined or empty string")
    private Integer jobId;

    @NotNull(message = "Student Id can't be null, undefined or empty string")
    private Integer studentId;
}