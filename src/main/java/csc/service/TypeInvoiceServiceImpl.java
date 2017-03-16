package csc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csc.models.TypeInvoice;
import csc.repository.ServiceRepository;
import csc.repository.TypeInvoiceRepository;

@Service("typeInvoiceService")
public class TypeInvoiceServiceImpl implements TypeInvoiceService{

	@Autowired
	TypeInvoiceRepository typeInvoiceRepository;
	
	@Autowired
	ServiceRepository serviceRepository;

	@Override
	public TypeInvoice findById(Integer id) {
		// TODO Auto-generated method stub
		return typeInvoiceRepository.findById(id);
	}

	@Override
	public List<TypeInvoice> findAll() {
		// TODO Auto-generated method stub
		return (List<TypeInvoice>) typeInvoiceRepository.findAll();
	}

	@Override
	public List<csc.models.Service> findByIdType(TypeInvoice idtype) {
		// TODO Auto-generated method stub
		return serviceRepository.findByIdType(idtype);
	}

	@Override
	public csc.models.Service findByNameServiceAndIdType(String nameservice, TypeInvoice idType) {
		// TODO Auto-generated method stub
		return serviceRepository.findByNameServiceAndIdType(nameservice, idType);
	}
	
}
