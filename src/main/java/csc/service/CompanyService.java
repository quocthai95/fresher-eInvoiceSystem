package csc.service;

import java.util.List;

import csc.models.Company;
import csc.models.TypeInvoice;

public interface CompanyService {
	Company findByNameCpn(String namecpn);

	Company findById(Integer id);
	
	List<Company> findByIdType(TypeInvoice idtype);
}
