package csc.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import csc.models.Service;
import csc.models.TypeInvoice;

public interface TypeInvoiceService {
	
	@Cacheable("invoice")
	TypeInvoice findById(Integer id);
	
	@Cacheable("invoice")
	List<TypeInvoice> findAll();
	
	@Cacheable("invoice")
	List<Service> findByIdType(TypeInvoice idtype);
	
	@Cacheable("invoice")
	Service findByNameServiceAndIdType(String nameservice, TypeInvoice idType);
}
