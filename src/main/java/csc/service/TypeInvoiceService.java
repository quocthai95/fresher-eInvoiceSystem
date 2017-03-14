package csc.service;

import java.util.List;

import csc.models.TypeInvoice;

public interface TypeInvoiceService {
	TypeInvoice findById(Integer id);
	List<TypeInvoice> findAll();
}
