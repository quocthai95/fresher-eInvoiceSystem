package csc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csc.models.Company;
import csc.models.TypeInvoice;
import csc.repository.CompanyRepository;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Company findByNameCpn(String namecpn) {
		return companyRepository.findByNameCpn(namecpn);
	}

	@Override
	public Company findById(Integer id) {
		return companyRepository.findById(id);
	}

	@Override
	public List<Company> findByIdType(TypeInvoice idtype) {
		return companyRepository.findByIdType(idtype);
	}
}
