package csc.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "id_cpn")
    private String idCpn;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "name_cpn")
    private String nameCpn;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "address")
    private String address;
    
    @Basic(optional = false)
    @Column(name = "phone_cpn")
    private int phoneCpn;
    
    @Basic(optional = false)
    @Column(name = "fax")
    private int fax;
    
    @Basic(optional = false)
    @Column(name = "bank_account",length=10485760)
    private String bankAccount;
    
    @Basic(optional = false)
    @Column(name = "tax_code")
    private int taxCode;
    
//    @JsonIgnore
//    @OneToMany(mappedBy = "idCpn")//cascade = CascadeType.ALL,
//    private Collection<TypeInvoice> typeInvoiceCollection;
    
    @JsonIgnoreProperties("cpnCollection")
	@JoinColumn(name = "id_type", referencedColumnName = "id_type")
	@ManyToOne(optional = false)
	private TypeInvoice idType;
    
    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String idCpn, String nameCpn, String address, int phoneCpn, int fax, String bankAccount, int taxCode) {
        this.id = id;
        this.idCpn = idCpn;
        this.nameCpn = nameCpn;
        this.address = address;
        this.phoneCpn = phoneCpn;
        this.fax = fax;
        this.bankAccount = bankAccount;
        this.taxCode = taxCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCpn() {
        return idCpn;
    }

    public void setIdCpn(String idCpn) {
        this.idCpn = idCpn;
    }

    public String getNameCpn() {
        return nameCpn;
    }

    public void setNameCpn(String nameCpn) {
        this.nameCpn = nameCpn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneCpn() {
        return phoneCpn;
    }

    public void setPhoneCpn(int phoneCpn) {
        this.phoneCpn = phoneCpn;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(int taxCode) {
        this.taxCode = taxCode;
    }
    
//    @XmlTransient
//    public Collection<TypeInvoice> getTypeInvoiceCollection() {
//		return typeInvoiceCollection;
//	}
//    
//    public void setTypeInvoiceCollection(Collection<TypeInvoice> typeInvoiceCollection) {
//		this.typeInvoiceCollection = typeInvoiceCollection;
//	}
    
    public TypeInvoice getIdType() {
		return idType;
	}
    
    public void setIdType(TypeInvoice idType) {
		this.idType = idType;
	}
}
