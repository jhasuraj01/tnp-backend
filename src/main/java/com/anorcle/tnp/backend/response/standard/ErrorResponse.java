package com.anorcle.tnp.backend.response.standard;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse extends Response{
    private final String errorMessage;
    private final String errorCode;

    public ErrorResponse(String errorCode, String errorMessage) {
        super(false);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}