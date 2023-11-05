package com.anorcle.tnp.backend.repository;

import com.anorcle.tnp.backend.model.user.Staff;
import com.anorcle.tnp.backend.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
