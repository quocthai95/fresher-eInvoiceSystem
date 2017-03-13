package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Invoice;
import java.util.Date;
import java.util.List;
import csc.models.Customer;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
	List<Invoice> findByDate(Date date);
}
