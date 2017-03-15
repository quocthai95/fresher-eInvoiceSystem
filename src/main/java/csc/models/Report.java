/*package csc.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Report implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String idCustomer;
	private String contractNumber;
	private String nameInvoice;
	private BigDecimal grandTotal;
	private Date date;

	public Report() {
	}

	public Report(Long id) {
		this.id = id;
	}

	public Report(Long id, String idCustomer, String contractNumber, String nameInvoice, BigDecimal grandTotal) {
		this.id = id;
		this.idCustomer = idCustomer;
		this.contractNumber = contractNumber;
		this.nameInvoice = nameInvoice;
		this.grandTotal = grandTotal;
	}

	public Report(String idCustomer, String contractNumber, String nameInvoice, BigDecimal grandTotal) {
		this.idCustomer = idCustomer;
		this.contractNumber = contractNumber;
		this.nameInvoice = nameInvoice;
		this.grandTotal = grandTotal;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getNameInvoice() {
		return nameInvoice;
	}

	public void setNameInvoice(String nameInvoice) {
		this.nameInvoice = nameInvoice;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
*/