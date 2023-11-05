package com.anorcle.tnp.backend.controller;

import com.anorcle.tnp.backend.model.constants.ErrorCodeEnum;
import com.anorcle.tnp.backend.model.resource.Company;
import com.anorcle.tnp.backend.request.company.CreateCompanyRequestBody;
import com.anorcle.tnp.backend.request.company.UpdateCompanyRequestBody;
import com.anorcle.tnp.backend.request.standard.DeleteRequestBody;
import com.anorcle.tnp.backend.response.standard.ErrorResponse;
import com.anorcle.tnp.backend.response.standard.Response;
import com.anorcle.tnp.backend.response.standard.SuccessResponse;
import com.anorcle.tnp.backend.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<List<Company>> createCompanies(@Valid @RequestBody List<CreateCompanyRequestBody> companyRequestBodies) {
        List<Company> companies = companyRequestBodies.stream().map(companyRequestBody ->
            Company.builder()
                    .arn(companyRequestBody.getArn())
                    .name(companyRequestBody.getName())
                    .description(companyRequestBody.getDescription())
                    .websiteLink(companyRequestBody.getWebsiteLink())
                    .build()
        ).collect(Collectors.toList());
        return new ResponseEntity<>(companyService.createCompanies(companies), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<List<Response>> updateCompanies(@Valid @RequestBody ArrayList<UpdateCompanyRequestBody> updateCompanyRequestBodies) {

        List<Response> responses = new ArrayList<>();

        for (int i = 0; i < updateCompanyRequestBodies.size(); ++i) {
            UpdateCompanyRequestBody updateCompanyRequestBody = updateCompanyRequestBodies.get(i);

            Optional<Company> companyOptional = companyService.getCompanyById(updateCompanyRequestBody.getId());
            if(companyOptional.isEmpty()) {
                responses.add(new ErrorResponse(ErrorCodeEnum.COMPANY_NOT_FOUND, "Company Not Found"));
                continue;
            }
            Company company = companyOptional.get();
            updateCompanyProperties(company, updateCompanyRequestBody);
            companyService.updateCompany(company);
            responses.add(new SuccessResponse());
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    private void updateCompanyProperties(Company company, UpdateCompanyRequestBody updateCompanyRequestBody) {
        if(updateCompanyRequestBody.getArn() != null)
            company.setArn(updateCompanyRequestBody.getArn());
        if(updateCompanyRequestBody.getName() != null)
            company.setName(updateCompanyRequestBody.getName());
        if(updateCompanyRequestBody.getDescription() != null)
            company.setDescription(updateCompanyRequestBody.getDescription());
        if(updateCompanyRequestBody.getWebsiteLink() != null)
            company.setWebsiteLink(updateCompanyRequestBody.getWebsiteLink());
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteCompanies(@Valid @RequestBody DeleteRequestBody deleteRequestBody) {
        companyService.deleteCompanies(deleteRequestBody.getIds());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
