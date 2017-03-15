package csc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import csc.models.Customer;
import csc.models.Invoice;
import csc.repository.InvoiceRepository;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	public InvoiceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Invoice findById(long id) {
		// TODO Auto-generated method stub
		return invoiceRepository.findOne(id);
	}

	@Override
	public void saveInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceRepository.save(invoice);
	}

	@Override
	public void updateInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		//invoiceRepository.save(invoice);
		invoiceRepository.saveAndFlush(invoice);
	}

	@Override
	public void deleteInvoiceById(long id) {
		// TODO Auto-generated method stub
		invoiceRepository.delete(id);
	}

	@Override
	public Page<Invoice> findAllInvoice(Pageable pageable) {
		// TODO Auto-generated method stub
		return invoiceRepository.findAll(pageable);
	}

	@Override
	public Page<Invoice> findByIdCustomer(Customer idcustomer, Pageable pageable) {
		// TODO Auto-generated method stub
		return invoiceRepository.findByIdCustomer(idcustomer, pageable);
	}

	@Override
	public Page<Invoice> getListReport(String idCus, String dateStart, String dateEnd, int page, int pageSize) {
		PageRequest pageable = new PageRequest(page, pageSize);
		return invoiceRepository.findDateByIdCus(idCus, dateStart, dateEnd, pageable);
	}
}
