package csc.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import csc.models.Customer;
import csc.models.Users;

public interface CustomerService {
	void saveCustomer(Customer customer);
	@Cacheable("users")
	Customer findByUser(Users user);
	Customer findByEmail(String email);
	Customer findById(long id);
	@CacheEvict(value = "users", allEntries = true)
	void updateCustomer(Customer currentCustomer);
}
