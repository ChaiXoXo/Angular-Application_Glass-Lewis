package com.GLewis_WebApp.GLewis_Company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GLewis_WebApp.GLewis_Company.model.Company;
import com.GLewis_WebApp.GLewis_Company.services.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	// Create new Company record
	@PostMapping("/createCompany")
	public ResponseEntity<Object> addComapnyRecord(@RequestBody Company company) {
		if(companyService.isIsinExists(company.getIsin())) {
			return ResponseEntity.badRequest().body("ISIN already exists");
		}
		return ResponseEntity.ok(companyService.addComapnyRecord(company));
	}
	
	// Create new Company records
	@PostMapping("/createCompanies")
	public List<Company> addAllCompanies(@RequestBody List<Company> companies){
		return companyService.addAllCompanies(companies);
	}
	
	@GetMapping("/getAllCompanies")
	public List<Company> getAllCompanies() {
		return companyService.getAllCompanies();
	}
	
	// Retrieve Company record by ID
	@GetMapping("/getCompanyById/{id}")
	public ResponseEntity<Object> getCompanyById(@PathVariable int id){
		Company company = companyService.getCompanyById(id);
		if(company == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(company);
	}
	
	// Retrieve Company record by Isin
	@GetMapping("/getCompanyByIsin/{isin}")
	public ResponseEntity<Object> getCompanyByIsin(@PathVariable String isin){
		Company company = companyService.getCompanyByIsin(isin);
		if(company == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(company);
	}
	
	@PutMapping("/getCompanyById/")
	public ResponseEntity<Object> updateCustomer(@RequestBody Company company){
		Company existingCompany = companyService.getCompanyById(company.getId());
//		if(companyService.isIsinExists(company.getIsin())) {
//			return ResponseEntity.badRequest().body("ISIN already exists");
//		}
		if (!existingCompany.getIsin().equals(company.getIsin()) && companyService.isIsinExists(company.getIsin())) {
	        return ResponseEntity.badRequest().body("ISIN already exists");
	    }
		existingCompany.setName(company.getName());
		existingCompany.setTicker(company.getTicker());
		existingCompany.setExchange(company.getExchange());
		existingCompany.setIsin(company.getIsin());
		existingCompany.setWebsite(company.getWebsite());
		Company updatedComapny = companyService.updateCompany(existingCompany);
		return ResponseEntity.ok(updatedComapny);
	}
}
