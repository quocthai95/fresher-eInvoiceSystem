package csc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import csc.models.Company;
import csc.models.Customer;
import csc.models.Invoice;
import csc.models.Users;
import csc.service.CompanyService;
import csc.service.CustomerService;
import csc.service.InvoiceService;
import csc.service.UserService;

/**
 * A class to test interactions with the SQLSERVER database using the UserDao
 * class.
 *
 */
@RestController
public class InvoiceController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CompanyService companyService;
		
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/invoice/getAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Invoice>> listAllInvoice(Pageable pageable) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		user = userService.findByName(username);
		
		Customer cus = new Customer();
		cus = customerService.findByUser(user);
		
    	Page<Invoice> invoice = invoiceService.findByIdCustomer(cus, pageable);
        if(invoice.getSize() == 0){
            return new ResponseEntity<Page<Invoice>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Page<Invoice>>(invoice, HttpStatus.OK);
    }
  
  
     
    //-------------------Retrieve Single User--------------------------------------------------------
      
    @RequestMapping(value = "/invoice/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
        System.out.println("Fetching invoice with id " + id);
        Invoice invoice = invoiceService.findById(id);
        if (invoice == null) {
            System.out.println("Invoice with id " + id + " not found");
            return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }    

      
    //-------------------Create a User--------------------------------------------------------
      
    @RequestMapping(value = "/invoice/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createInvoice(@RequestBody Invoice invoice,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Invoice " + invoice.getId());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		user = userService.findByName(username);
		System.out.println("Type invoice" +invoice.getIdType()); 
		Customer cus = new Customer();
		cus = customerService.findByUser(user);
		
		Company com = new Company();
		com = companyService.findById(1);
		
		invoice.setIdCustomer(cus);
		invoice.setIdCpn(com);
    
        invoiceService.saveInvoice(invoice);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/invoice/{id}").buildAndExpand(invoice.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a User --------------------------------------------------------
      
    @RequestMapping(value = "/invoice/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        System.out.println("Updating Invoice " + id);
          
        Invoice currentInvoice = invoiceService.findById(id);
          
        if (currentInvoice==null) {
            System.out.println("Invoice with id " + id + " not found");
            return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		user = userService.findByName(username);
		System.out.println("Type invoice" +invoice.getIdType()); 
		Customer cus = new Customer();
		cus = customerService.findByUser(user);
        Company com = new Company();
		com = companyService.findById(1);
        currentInvoice.setDate(invoice.getDate());
        currentInvoice.setContractNumber(invoice.getContractNumber());
        currentInvoice.setNameService(invoice.getNameService());
        currentInvoice.setIndexConsumed(invoice.getIndexConsumed());
        currentInvoice.setTotal(invoice.getTotal());
        currentInvoice.setVat(invoice.getVat());
        currentInvoice.setPtef(invoice.getPtef());
        currentInvoice.setGrandTotal(invoice.getGrandTotal());
        currentInvoice.setIdCustomer(cus);
        currentInvoice.setIdCpn(com);
        currentInvoice.setIdType(invoice.getIdType());
          
        invoiceService.updateInvoice(currentInvoice);
        return new ResponseEntity<Invoice>(currentInvoice, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a User --------------------------------------------------------
      
    @RequestMapping(value = "/invoice/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Invoice with id " + id);
  
        Invoice invoice = invoiceService.findById(id);
        if (invoice == null) {
            System.out.println("Unable to delete. Invoice with id " + id + " not found");
            return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
        }
  
        invoiceService.deleteInvoiceById(id);
        return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
    }
    
   

} // class UserController
