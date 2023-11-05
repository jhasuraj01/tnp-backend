package com.anorcle.tnp.backend.service;

import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    @Autowired
    CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> createCompanies(List<Company> companies) {
        return companyRepository.saveAll(companies);
    }
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Optional<Company> getCompanyById(Integer id) {
        return companyRepository.findById(id);
    }
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }
    public void deleteCompanies(List<Integer> ids) {
        companyRepository.deleteAllById(ids);
    }
}