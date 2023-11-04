package com.anorcle.tnp.backend.model.resource.drive;

import com.anorcle.tnp.backend.model.resource.Resource;
import com.anorcle.tnp.backend.model.user.Student;
import com.anorcle.tnp.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDrive extends Resource {
    private Integer id;
    private Integer job_id;
    private Integer company_id;
    private RecruitmentRound[] recruitmentRounds;
    private User[] volunteer;
    private User[] recruiter;

    @Data
    public class RecruitmentRound extends Resource {
        private String roundName;
        /** registered and eligible students */
        private Student[] students;
        private User[] volunteer;
    }
}