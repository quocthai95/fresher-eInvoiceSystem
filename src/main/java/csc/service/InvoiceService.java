package csc.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import csc.models.Customer;
import csc.models.Invoice;

public interface InvoiceService {
	
	Invoice findById(long id);
	
	@CacheEvict(value="report", allEntries=true)
	void saveInvoice(Invoice invoice);
	
	@CacheEvict(value="report", allEntries=true)
	void updateInvoice(Invoice invoice);
	
	@CacheEvict(value="report", allEntries=true)
	void deleteInvoiceById(long id);

	Page<Invoice> findAllInvoice(Pageable pageable);
	
	Page<Invoice> findByIdCustomer(Customer idcustomer, Pageable pageable);
	
	@Cacheable("report")
	Page<Invoice> getListReport(String idCus, String dateStart, String dateEnd, int page, int pageSize);

}
