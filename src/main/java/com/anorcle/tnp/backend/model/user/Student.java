package com.anorcle.tnp.backend.model.user;

import com.anorcle.tnp.backend.model.doc.Resume;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    @NotBlank(message = "Missing Required Properties: user.firstName")
    private String prn;

//    @NotBlank(message = "Missing Required Properties: user.firstName")
//    private Resume resume;

}
