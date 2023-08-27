package com.anorcle.tnp.backend.model.user;

import com.anorcle.tnp.backend.model.doc.Resume;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    private String prn;
    private Resume resume;
}
