package csc.service;

import java.util.List;

import csc.models.Service;
import csc.models.TypeInvoice;

public interface TypeInvoiceService {
	TypeInvoice findById(Integer id);
	List<TypeInvoice> findAll();
	
	List<Service> findByIdType(TypeInvoice idtype);
	
	Service findByNameServiceAndIdType(String nameservice, TypeInvoice idType);
}
