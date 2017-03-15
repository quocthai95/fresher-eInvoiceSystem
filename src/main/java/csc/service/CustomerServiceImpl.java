package csc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csc.models.Customer;
import csc.models.Users;
import csc.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

	@Override
	public Customer findByUser(Users user) {
		// TODO Auto-generated method stub
		return customerRepository.findByUser(user);
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(email);
	}

}
