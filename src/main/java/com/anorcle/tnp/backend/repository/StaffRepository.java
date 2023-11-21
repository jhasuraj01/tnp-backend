package com.anorcle.tnp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anorcle.tnp.backend.model.user.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
