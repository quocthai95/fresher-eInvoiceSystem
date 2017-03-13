package csc.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "report")
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Basic(optional = false)
	@Column(name = "id_customer")
	private Long idCustomer;

	@Basic(optional = false)
	@Column(name = "contract_number")
	private String contractNumber;

	@Basic(optional = false)
	@Column(name = "name_invoice")
	private String nameInvoice;

	@Basic(optional = false)
	@Column(name = "grand_total")
	private BigDecimal grandTotal;

	public Report() {
	}

	public Report(Long id) {
		this.id = id;
	}

	public Report(Long id, Long idCustomer, String contractNumber, String nameInvoice, BigDecimal grandTotal) {
		this.id = id;
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

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
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
	
}
