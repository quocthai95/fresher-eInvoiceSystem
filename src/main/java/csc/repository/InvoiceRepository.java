package csc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Customer;
import csc.models.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
	Page<Invoice> findAll(Pageable pageable);
	Page<Invoice> findByIdCustomer(Customer idcustomer, Pageable pageable);
}
