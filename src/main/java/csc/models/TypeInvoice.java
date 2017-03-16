package csc.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "typeinvoice")
public class TypeInvoice implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_type")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "code")
	private String code;

	@Basic(optional = false)
	@Column(name = "name_invoice")
	private String nameInvoice;

	@Basic(optional = false)
	@Lob
	@Column(name = "description")
	private String description;

	@Basic(optional = false)
	@Column(name = "vat")
	private float vat;

	// @Basic(optional = false)
	// @Column(name = "unit")
	// private BigDecimal unit;

	@JsonIgnore
	@OneToMany(mappedBy = "idType") // cascade = CascadeType.ALL,
	private Collection<Invoice> invoiceCollection;

	// @JsonIgnore
	// @JoinColumn(name = "id_cpn", referencedColumnName = "id_cpn")
	// @ManyToOne(optional = false)
	// private Company idCpn;

	@JsonIgnore
	@OneToMany(mappedBy = "idType") // cascade = CascadeType.ALL,
	private Collection<Company> cpnCollection;

	@JsonIgnore
	@OneToMany(mappedBy = "idType") // cascade = CascadeType.ALL,
	private Collection<Service> serviceCollection;

	public TypeInvoice() {
	}

	public TypeInvoice(Integer id, String code, String nameInvoice, String description, float vat) {
		this.id = id;
		this.code = code;
		this.nameInvoice = nameInvoice;
		this.description = description;
		this.vat = vat;
	}

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameInvoice() {
		return nameInvoice;
	}

	public void setNameInvoice(String nameInvoice) {
		this.nameInvoice = nameInvoice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getVat() {
		return vat;
	}

	public void setVat(float vat) {
		this.vat = vat;
	}

	@XmlTransient
	public Collection<Invoice> getInvoiceCollection() {
		return invoiceCollection;
	}

	public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
		this.invoiceCollection = invoiceCollection;
	}

	@XmlTransient
	public Collection<Company> getCpnCollection() {
		return cpnCollection;
	}

	public void setCpnCollection(Collection<Company> cpnCollection) {
		this.cpnCollection = cpnCollection;
	}

	@XmlTransient
	public Collection<Service> getServiceCollection() {
		return serviceCollection;
	}

	public void setServiceCollection(Collection<Service> serviceCollection) {
		this.serviceCollection = serviceCollection;
	}
	// public Company getIdCpn() {
	// return idCpn;
	// }
	//
	// public void setIdCpn(Company idCpn) {
	// this.idCpn = idCpn;
	// }

	// public BigDecimal getUnit() {
	// return unit;
	// }
	//
	// public void setUnit(BigDecimal unit) {
	// this.unit = unit;
	// }

}
