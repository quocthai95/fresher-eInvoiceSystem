package csc.controllers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csc.models.Customer;
import csc.models.Users;
import csc.service.CustomerService;
import csc.service.RoleService;
import csc.service.UserService;

@RestController
public class CustomerController {

	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Resource
	@Qualifier("roleService")
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/customer/getEmail", method = RequestMethod.POST)
	public boolean getCustomer(@RequestBody String email) {
		try {
			Customer cus = customerService.findByEmail(email);
			System.out.println("Email " + cus.getEmail());
		} catch (Exception ex) {
			System.out.println("Email does not exist");
			return false;
		}
		System.out.println("Email " + email + " does exist");
		return true;
	}
	
	   
    //-------------------Retrieve Single Customer--------------------------------------------------------
      
    @RequestMapping(value = "/customer/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer() {
    			
		Customer cus = this.getCustomerAuthen();
		cus.getUser().setPassword(null);
		cus.getUser().setRoles(null);
		
        System.out.println("Fetching Customer with id " + cus.getId());
        
        if (cus == null) {
            System.out.println("User with id " + cus.getId() + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(cus, HttpStatus.OK);
    } 
    
    //------------------- Update a Customer--------------------------------------------------------
    
    @RequestMapping(value = "/customer/update", method = RequestMethod.POST)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
          
        Customer currentCustomer = this.getCustomerAuthen();
          
        if (currentCustomer==null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
  
        currentCustomer.setNameCustomer(customer.getNameCustomer());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setPhone(customer.getPhone());
        currentCustomer.setTaxCode(customer.getTaxCode());
        currentCustomer.setLimitConsume(customer.getLimitConsume());
          
        customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
    }
    
    private Customer getCustomerAuthen() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		Customer cus = new Customer();
		user = userService.findByName(username);
		cus = customerService.findByUser(user);
		
		return cus;
	}
    
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateWithInitialDelayTask() {
      
        long now = System.currentTimeMillis() / 1000;
        System.out.println(
          "Fixed rate task with one second initial delay - " + now);
    }
}
