package csc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csc.models.Customer;
import csc.models.Invoice;
import csc.models.Report;
import csc.models.Users;
import csc.repository.CustomerRepository;
import csc.repository.InvoiceRepository;
import csc.repository.ReportRepository;
import csc.service.CustomerService;
import csc.service.UserService;

/**
 * URL test= http://localhost:8080/EInvoice/user/getReport/start=2016-01-10&end=2016-04-10
 * Query MySQL test= SELECT * FROM eis.invoice u where u.id_customer = 'CUS2017031' and u.date between '2016-01-10' and '2016-04-10'
 * @author user
 *
 */
@RestController
public class ReportController {
	@Autowired
	UserService userService;

	@Autowired
	CustomerService customerService;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	ReportRepository reportRepository;

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping(value = "/user/getReport/start={start}&end={end}", method = RequestMethod.GET)
	public ResponseEntity<Page<Report>> getListReport(Pageable pageable,
			@PathVariable("start") String dateStart, @PathVariable("end") String dateEnd) {
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = new Users();
		user = userService.findByName(username);
		
		Customer cus = new Customer();
		cus = customerService.findByUser(user);
		
		String idCus = cus.getIdCustomer();
		
		Page<Report> reports = this.isExistReport(pageable, idCus, dateStart, dateEnd);
		List<Invoice> invoices = null;
		System.out.println(idCus + " - " + dateStart + " - " + dateEnd);
		if (reports.getContent().size() != 0) { // if having report, use it
			System.out.println("get Report with size= " + reports.getContent().size());
			return new ResponseEntity<Page<Report>>(reports, HttpStatus.OK);
		} else { // If don't have report, generate report and use it
			invoices = this.createListInvoices(idCus, dateStart, dateEnd);
			System.out.println("invoices= " + invoices.size());
			if (invoices.size() == 0) {
				return new ResponseEntity<Page<Report>>(HttpStatus.NO_CONTENT);
			} else {
				this.generateReport(invoices);
				System.out.println("generateReport");
			}
			// use report
			reports = reportRepository.findReport(idCus, dateStart, dateEnd, pageable);
		}
		return new ResponseEntity<Page<Report>>(reports, HttpStatus.OK);
	}

	private Page<Report> isExistReport(Pageable pageable, String id, String dateStart, String dateEnd) {
		Page<Report> lstReport = null;
		try {
			lstReport = reportRepository.findReport(id, dateStart, dateEnd, pageable);
		} catch (Exception ex) {
			System.out.println("isExistReport error= " + ex.getMessage());
		}
		return lstReport;
	}

	private boolean generateReport(List<Invoice> invoices) {
		boolean flag = false;
		Report rp;
		System.out.println("invoices generateReport=" + invoices.size());
		try {
			for (int index = 0; index < invoices.size(); index++) {
				rp = new Report();
				rp.setIdCustomer((invoices.get(index).getIdCustomer()).getIdCustomer());
				rp.setDate(invoices.get(index).getDate());
				rp.setNameInvoice(invoices.get(index).getIdType().getNameInvoice());
				rp.setContractNumber(invoices.get(index).getContractNumber());
				rp.setGrandTotal(invoices.get(index).getGrandTotal());
				Report tmp = reportRepository.save(rp);
				 System.out.println("generate= " + tmp.getContractNumber());
			}
			flag = true;
		} catch (Exception ex) {
			System.out.println("generateReport error= " + ex.getMessage());
		}

		return flag;
	}

	private List<Invoice> createListInvoices(String id, String dateStart, String dateEnd) {
		try {
			return invoiceRepository.findDateByIdCus(id, dateStart, dateEnd);
		} catch (Exception ex) {
			System.out.println("createListInvoices error= " + ex.getMessage());
		}
		return null;
	}

}
