package com.abhijeet.jobsite.company;

import com.abhijeet.jobsite.configs.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<ApiResponse<List<Company>>> getAllCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal().equals("anonymousUser"))
            return new ResponseEntity<>(new ApiResponse<>(false, HttpStatus.UNAUTHORIZED.value(), null,
                    "Unauthorised Access, Please Login!"), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(new ApiResponse<>(true, HttpStatus.OK.value(),
                companyService.getAllCompanies(), null),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) return ResponseEntity.ok(company);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_USER")
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody Company company) {
        var response = companyService.updateCompany(company, id);
        if (response != null) return ResponseEntity.ok(response);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        boolean isDeleted = companyService.deleteCompany(id);
        if (isDeleted) return new ResponseEntity<>("Company Deleted!", HttpStatus.OK);
        else return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        var response = companyService.ceateCompany(company);
        if (response != null) {
            return new ResponseEntity<>(company, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
