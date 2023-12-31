package com.anorcle.tnp.backend.request.standard;

import java.util.List;

import com.anorcle.tnp.backend.request.RequestParent;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteRequestBody extends RequestParent {
  @NotNull(message = "Missing Required Field: body.ids[]")
  List<Integer> ids;
}
