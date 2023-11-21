package com.anorcle.tnp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anorcle.tnp.backend.model.resource.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
