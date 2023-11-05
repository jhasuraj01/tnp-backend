package com.anorcle.tnp.backend.repository;

import com.anorcle.tnp.backend.model.resource.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
