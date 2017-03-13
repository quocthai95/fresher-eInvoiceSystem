package csc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csc.models.TypeInvoice;
import csc.repository.TypeInvoiceRepository;

@Service("typeInvoiceService")
public class TypeInvoiceServiceImpl implements TypeInvoiceService{

	@Autowired
	TypeInvoiceRepository typeInvoiceRepository;

	@Override
	public TypeInvoice findById(long id) {
		// TODO Auto-generated method stub
		return typeInvoiceRepository.findOne(id);
	}
			
}
