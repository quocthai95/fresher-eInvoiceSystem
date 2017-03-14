package csc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csc.models.Company;
import csc.repository.CompanyRepository;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Company findByNameCpn(String namecpn) {
		// TODO Auto-generated method stub
		return companyRepository.findByNameCpn(namecpn);
	}

	@Override
	public Company findById(Integer id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id);
	}
	
	
}
