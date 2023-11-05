package com.anorcle.tnp.backend.response.standard;

import lombok.Data;

@Data
public class SuccessResponse extends Response {
    private final Boolean ok = true;
    public SuccessResponse() {
        super(true);
    }
}
