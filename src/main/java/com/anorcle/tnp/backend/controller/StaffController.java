package com.anorcle.tnp.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anorcle.tnp.backend.adapters.PasswordAdapter;
import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.user.Staff;
import com.anorcle.tnp.backend.request.staff.CreateStaffRequestBody;
import com.anorcle.tnp.backend.request.staff.UpdateStaffRequestBody;
import com.anorcle.tnp.backend.request.standard.DeleteRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.StaffService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/staffs")
public class StaffController {
  private final StaffService staffService;
  private final PasswordAdapter passwordAdapter;

  public StaffController(StaffService staffService, PasswordAdapter passwordAdapter) {
    this.staffService = staffService;
    this.passwordAdapter = passwordAdapter;
  }

  @GetMapping("/")
  public ResponseEntity<List<Staff>> getAllStaffs() {
    return new ResponseEntity<>(staffService.getAllStaffs(), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<List<Staff>> createStaffs(@Valid @RequestBody List<CreateStaffRequestBody> staffRequestBodies) {
    List<Staff> staffs = staffRequestBodies.stream().map(staffRequestBody -> Staff.builder()
        .userGroup(staffRequestBody.getUserGroup())
        .email(staffRequestBody.getEmail())
        .passwordHash(passwordAdapter.hashPassword(staffRequestBody.getPassword()))
        .firstName(staffRequestBody.getFirstName())
        .lastName(staffRequestBody.getLastName())
        .designation(staffRequestBody.getDesignation())
        .build()).collect(Collectors.toList());
    return new ResponseEntity<>(staffService.createStaffs(staffs), HttpStatus.CREATED);
  }

  @PutMapping("/")
  public ResponseEntity<List<Response>> updateStaffs(
      @Valid @RequestBody ArrayList<UpdateStaffRequestBody> updateStaffRequestBodies) {

    List<Response> responses = new ArrayList<>();

    for (int i = 0; i < updateStaffRequestBodies.size(); ++i) {
      UpdateStaffRequestBody updateStaffRequestBody = updateStaffRequestBodies.get(i);

      Optional<Staff> staffOptional = staffService.getStaffById(updateStaffRequestBody.getId());
      if (staffOptional.isEmpty()) {
        responses.add(new ErrorResponse(ErrorCodeEnum.STAFF_NOT_FOUND, "Staff Not Found"));
        continue;
      }
      Staff staff = staffOptional.get();
      updateStaffProperties(staff, updateStaffRequestBody);
      staffService.updateStaff(staff);
      responses.add(new SuccessResponse<>());
    }

    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  private void updateStaffProperties(Staff staff, UpdateStaffRequestBody updateStaffRequestBody) {
    if (updateStaffRequestBody.getFirstName() != null)
      staff.setFirstName(updateStaffRequestBody.getFirstName());
    if (updateStaffRequestBody.getLastName() != null)
      staff.setLastName(updateStaffRequestBody.getLastName());
    if (updateStaffRequestBody.getEmail() != null)
      staff.setEmail(updateStaffRequestBody.getEmail());
    if (updateStaffRequestBody.getUserGroup() != null)
      staff.setUserGroup(updateStaffRequestBody.getUserGroup());
    if (updateStaffRequestBody.getDesignation() != null)
      staff.setDesignation(updateStaffRequestBody.getDesignation());
  }

  @DeleteMapping("/")
  public ResponseEntity<HttpStatus> deleteStaffs(@Valid @RequestBody DeleteRequestBody deleteRequestBody) {
    staffService.deleteStaffs(deleteRequestBody.getIds());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
