package csc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csc.models.Report;
import csc.repository.InvoiceRepository;
import csc.repository.ReportRepository;
import csc.service.CustomerService;
import csc.service.UserService;

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

    @RequestMapping(value = "/user/getReport", method = RequestMethod.GET)
    public ResponseEntity<Page<Report>> listAllUsers(Pageable pageable, @PathVariable("idCus") long idCus, @PathVariable("time") String time) {
    	boolean flag = generateReport(idCus, time);
    	Page<Report> reports = null;
    	if (flag) {
        	reports = reportRepository.findAll(pageable);
            if(reports.getSize() == 0){
                return new ResponseEntity<Page<Report>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
            }
    	}
    	return new ResponseEntity<Page<Report>>(reports, HttpStatus.OK);
    }
	
	private boolean generateReport(long id, String time) {
		boolean tmp = false;
		
//		invoiceRepository.findByDate(date)
		return tmp;
	}
}
