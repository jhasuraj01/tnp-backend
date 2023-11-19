package com.anorcle.tnp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anorcle.tnp.backend.model.user.Staff;
import com.anorcle.tnp.backend.repository.StaffRepository;

@Service
public class StaffService {

  private final StaffRepository staffRepository;

  @Autowired
  StaffService(StaffRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  public List<Staff> createStaffs(List<Staff> staffs) {
    return staffRepository.saveAll(staffs);
  }

  public List<Staff> getAllStaffs() {
    return staffRepository.findAll();
  }

  public Optional<Staff> getStaffById(Integer id) {
    return staffRepository.findById(id);
  }

  public Staff updateStaff(Staff staff) {
    return staffRepository.save(staff);
  }

  public void deleteStaffs(List<Integer> ids) {
    staffRepository.deleteAllById(ids);
  }

}
