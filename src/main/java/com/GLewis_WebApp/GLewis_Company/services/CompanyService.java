package com.GLewis_WebApp.GLewis_Company.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GLewis_WebApp.GLewis_Company.model.Company;
import com.GLewis_WebApp.GLewis_Company.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public Company addComapnyRecord(Company company) {
		return companyRepository.save(company);
	}
	
	public List<Company> addAllCompanies(List<Company> companies) {
		return companyRepository.saveAll(companies);
	}

	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}

	public Company getCompanyById(int id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id).orElse(null);
	}

	public Company updateCompany(Company existingCompany) {
		// TODO Auto-generated method stub
		return companyRepository.save(existingCompany);
	}

	public Company getCompanyByIsin(String isin) {
		// TODO Auto-generated method stub
		return companyRepository.findByIsin(isin);
	}
	
	public boolean isIsinExists(String isin) {
        return companyRepository.findByIsin(isin) != null;
    }
	
	
}
