package csc.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import csc.models.Company;
import csc.models.TypeInvoice;

public interface CompanyService {
	@Cacheable("invoice")
	Company findByNameCpn(String namecpn);

	@Cacheable("invoice")
	Company findById(Integer id);
	
	@Cacheable("invoice")
	List<Company> findByIdType(TypeInvoice idtype);
}
