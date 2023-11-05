package com.anorcle.tnp.backend.request.company;

import com.anorcle.tnp.backend.request.RequestParent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCompanyRequestBody extends RequestParent {
    @NotNull(message = "Company Id can't be null, undefined or empty string")
    private Integer Id;
    private String arn;
    private String name;
    private String description;
    private String websiteLink;
}