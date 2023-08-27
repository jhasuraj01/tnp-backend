package com.anorcle.tnp.backend.model;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class Job {

    @NotBlank(message = "Id can't be null, undefined or empty string")
    private String id;

    @NotBlank(message = "Title can't be null, undefined or empty string")
    private String title;

    @NotBlank(message = "Description can't be null, undefined or empty string")
    private String description;

    @NotBlank(message = "Company can't be null, undefined or empty string")
    private String company;

    @NotBlank(message = "Location can't be null, undefined or empty string")
    private String location;

    @NotBlank(message = "Type can't be null, undefined or empty string")
    private String type;

    @NotNull(message = "Requirements can't be null or undefined")
    private String[] requirements;

    @NotNull(message = "Archive status can't be null or undefined")
    private boolean isArchived;

    @PositiveOrZero
    @NotNull(message = "CTC can't be null or undefined")
    private Integer ctc;
    public void setCtc(Integer ctc) {
        if(this.baseSalary != null && ctc < this.baseSalary)
            throw new ValidationException("CTC Can't be less than Base Salary");
        this.ctc = ctc;
    }

    @PositiveOrZero
    @NotNull(message = "Base Salary can't be null or undefined")
    private Integer baseSalary;
    public void setBaseSalary(Integer baseSalary) {
        if(this.ctc != null && this.ctc < baseSalary)
            throw new ValidationException("Base Salary Can't be greater than CTC");
        this.baseSalary = baseSalary;
    }
}

