package com.abhijeet.jobsite.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company updateCompany(Company company, long id);

    Company ceateCompany(Company company);

    Company getCompanyById(long id);

    boolean deleteCompany(long id);
}
