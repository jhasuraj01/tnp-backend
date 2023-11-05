package com.anorcle.tnp.backend.request.staff;

import com.anorcle.tnp.backend.request.RequestParent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStaffRequestBody extends RequestParent {

    @NotBlank(message = "User Group can't be null, undefined or empty string")
    private String userGroup;

    @NotBlank(message = "First Name can't be null, undefined or empty string")
    private String firstName;
    private String lastName;

    @NotBlank(message = "Email can't be null, undefined or empty string")
    private String email;
}