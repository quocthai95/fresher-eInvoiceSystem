package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Customer;
import java.lang.String;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	Customer findById(Long id);
	
	Customer findByIdCustomer(String idcustomer);

}
