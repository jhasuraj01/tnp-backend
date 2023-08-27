package com.anorcle.tnp.backend.model.user;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
}
