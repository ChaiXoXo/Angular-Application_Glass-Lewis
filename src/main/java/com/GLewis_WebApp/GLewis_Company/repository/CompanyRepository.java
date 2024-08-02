package com.GLewis_WebApp.GLewis_Company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GLewis_WebApp.GLewis_Company.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	Company findByIsin(String isin);

}
