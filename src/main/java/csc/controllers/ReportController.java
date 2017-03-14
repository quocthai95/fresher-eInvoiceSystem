package csc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csc.models.Invoice;
import csc.models.Report;
import csc.repository.CustomerRepository;
import csc.repository.InvoiceRepository;
import csc.repository.ReportRepository;
import csc.service.CustomerService;
import csc.service.UserService;

/**
 * URL test= http://localhost:8080/EInvoice/user/getReport/id=CUS2017031&start=2016-01-10&end=2016-04-10
 * Query MySQL test= SELECT * FROM eis.invoice u where u.id_customer = 'CUS2017031' and u.date between '2016-01-10' and '2016-04-10'
 * @author user
 *
 */
@RestController
public class ReportController {
	static List<Invoice> LIST;
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

	@RequestMapping(value = "/user/getReport/id={id}&start={start}&end={end}", method = RequestMethod.GET)
	public ResponseEntity<Page<Report>> getListReport(Pageable pageable, @PathVariable("id") String idCus,
			@PathVariable("start") String dateStart, @PathVariable("end") String dateEnd) {
		Page<Report> reports = this.isExistReport(pageable, idCus, dateStart, dateEnd);
		List<Invoice> invoices = null;
		System.out.println(idCus + " - " + dateStart + " - " + dateEnd);
		if (reports.getContent().size() != 0) { // if having report, use it
			System.out.println("get Report with size= " + reports.getContent().size());
			return new ResponseEntity<Page<Report>>(HttpStatus.NO_CONTENT);
		} else { // If don't have report, generate report and use it
			invoices = this.createListInvoices(idCus, dateStart, dateEnd);
			System.out.println("invoices= " + invoices.size());
			if (invoices.size() == 0) {
				return new ResponseEntity<Page<Report>>(HttpStatus.NO_CONTENT);
			} else {
				this.generateReport(idCus, dateStart, dateEnd);
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

	private boolean generateReport(String id, String dateStart, String dateEnd) {
		boolean flag = false;
		Report rp;
		List<Invoice> lstInv = LIST;
		System.out.println("LIST=" + LIST.size());
		try {
			for (int index = 0; index < lstInv.size(); index++) {
				rp = new Report();
				rp.setIdCustomer((lstInv.get(index).getIdCustomer()).getIdCustomer());
				rp.setDate(lstInv.get(index).getDate());
				rp.setNameInvoice(lstInv.get(index).getIdType().getNameInvoice());
				rp.setContractNumber(lstInv.get(index).getContractNumber());
				rp.setGrandTotal(lstInv.get(index).getGrandTotal());
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
			LIST = invoiceRepository.findDateByIdCus(id, dateStart, dateEnd);
			return LIST;
		} catch (Exception ex) {
			System.out.println("createListInvoices error= " + ex.getMessage());
		}
		return null;
	}

}
