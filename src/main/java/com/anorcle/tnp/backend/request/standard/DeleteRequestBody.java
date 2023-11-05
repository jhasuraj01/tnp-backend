package com.anorcle.tnp.backend.request.standard;

import com.anorcle.tnp.backend.request.RequestParent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class DeleteRequestBody extends RequestParent {
    @NotBlank(message = "Missing Required Field: body.ids[]")
    List<Integer> ids;
}
