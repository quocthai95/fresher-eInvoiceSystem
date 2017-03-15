package csc.service;

import csc.models.Customer;
import csc.models.Users;

public interface CustomerService {
	void saveCustomer(Customer customer);
	Customer findByUser(Users user);
	Customer findByEmail(String email);
	Customer findById(long id);
	void updateCustomer(Customer currentCustomer);
}
