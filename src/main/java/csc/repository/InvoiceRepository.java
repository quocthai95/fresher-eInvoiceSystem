package csc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Customer;
import csc.models.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

	@Query(value = "select * from Invoice u where u.id_customer = ?#{[0]} and u.date between ?#{[1]} and ?#{[2]} ", nativeQuery=true)
	List<Invoice> findDateByIdCus(String idCus, String dateStart, String dateEnd);

	Invoice findByContractNumber(String contractnumber);

	List<Invoice> findByIdCustomer(Customer idcustomer);

Page<Invoice> findAll(Pageable pageable);
	Page<Invoice> findByIdCustomer(Customer idcustomer, Pageable pageable);

}
