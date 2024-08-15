package com.abhijeet.jobsite.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) return ResponseEntity.ok(company);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody Company company) {
        var response = companyService.updateCompany(company, id);
        if (response != null) return ResponseEntity.ok(response);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        boolean isDeleted = companyService.deleteCompany(id);
        if (isDeleted) return new ResponseEntity<>("Company Deleted!", HttpStatus.OK);
        else return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        var response = companyService.ceateCompany(company);
        if (response != null) {
            return new ResponseEntity<>(company, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
