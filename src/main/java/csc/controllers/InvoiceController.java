package csc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * URL test=
 * http://localhost:8080/EInvoice/user/getReport/start=2016-01-10&end=2016-04-10&page=0&pageSize=10
 * Query MySQL test= SELECT * FROM eis.invoice u where u.id_customer =
 * 'CUS2017031' and u.date between '2016-01-10' and '2016-04-10'
 * 
 * @author user
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

	@RequestMapping(value = "/user/getReport/start={start}&end={end}&page={page}&pageSize={pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<Invoice>> getListReport(@PathVariable("start") String dateStart,
			@PathVariable("end") String dateEnd, @PathVariable("page") int page,
			@PathVariable("pageSize") int pageSize) {
		System.out.println("getListReport");
		
		String idCus = this.getIdCustomer();

		Page<Invoice> invoices = invoiceService.getListReport(idCus, dateStart, dateEnd, page, pageSize);
		System.out.println("invoices count= " + invoices.getContent().size());
		if (invoices.getContent() == null) {
			return new ResponseEntity<Page<Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<Invoice>>(invoices, HttpStatus.OK);
	}

	// -------------------Retrieve All Users--------------------------------------------------------

	@RequestMapping(value = "/invoice/getAll", method = RequestMethod.GET)
	public ResponseEntity<Page<Invoice>> listAllInvoice(Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		user = userService.findByName(username);

		Customer cus = new Customer();
		cus = customerService.findByUser(user);

		Page<Invoice> invoice = invoiceService.findByIdCustomer(cus, pageable);
		if (invoice.getSize() == 0) {
			return new ResponseEntity<Page<Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<Invoice>>(invoice, HttpStatus.OK);
	}

	// -------------------Retrieve Single User--------------------------------------------------------

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

	// -------------------Create a User--------------------------------------------------------

	@RequestMapping(value = "/invoice/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createInvoice(@RequestBody Invoice invoice, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Invoice " + invoice.getId());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		user = userService.findByName(username);
		System.out.println("Type invoice" + invoice.getIdType());
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

	// ------------------- Update a User --------------------------------------------------------

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

	// ------------------- Delete a User --------------------------------------------------------

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

	private String getIdCustomer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		Customer cus = new Customer();
		String idCus = null;
		
		user = userService.findByName(username);

		cus = customerService.findByUser(user);

		idCus = cus.getIdCustomer();
		
		return idCus;
	}

} // class UserController
