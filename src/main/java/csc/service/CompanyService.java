package csc.service;

import csc.models.Company;

public interface CompanyService {
	Company findByNameCpn(String namecpn);

	Company findById(Integer id);
}
