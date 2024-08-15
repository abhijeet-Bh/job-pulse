package com.abhijeet.jobsite.company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Company company, long id) {
        Optional<Company> jobOptional = companyRepository.findById(id);
        if (jobOptional.isPresent()) {
            Company targetCompany = jobOptional.get();
            targetCompany.setName(company.getName());
            targetCompany.setDescription(company.getDescription());
            targetCompany.setJobs(company.getJobs());
            companyRepository.save(targetCompany);
            return targetCompany;
        }
        return null;
    }

    @Override
    public Company ceateCompany(Company company) {
        try {
            companyRepository.save(company);
            return company;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Company getCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
