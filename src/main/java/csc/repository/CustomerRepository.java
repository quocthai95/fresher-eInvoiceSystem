package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
