package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
