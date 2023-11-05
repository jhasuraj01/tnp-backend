package com.anorcle.tnp.backend.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApplicationStatus {
    REVIEW_PENDING("REVIEW_PENDING"),
    UNDER_EVALUATION("UNDER_EVALUATION"),
    EXPIRED("EXPIRED"),
    BLOCKED("BLOCKED"),
    OFFERED("OFFERED"),
    REJECTED("REJECTED"),
    REVOKED("REVOKED");

    @Getter
    private String value;

}