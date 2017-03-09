package csc.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	@OneToOne(optional = false)
//    @Basic(optional = false)
//    @Column(name = "id_user")
    private Users user;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "id_customer")
    private String idCustomer;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "name_customer")
    private String nameCustomer;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "address")
    private String address;
    
    @Basic(optional = false)
    @Length(max= 100)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "phone")
    private int phone;
    
    @Basic(optional = false)
    @Column(name = "tax_code")
    private int taxCode;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limit_consume")
    private BigDecimal limitConsume;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomer")
    private Collection<Invoice> invoiceCollection;
    
    
    
    		
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
//    private Users user;

    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(Long id, String idCustomer, String nameCustomer, String address, String email, int phone, int taxCode) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.taxCode = taxCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public long getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(long idUser) {
//        this.idUser = idUser;
//    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(int taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getLimitConsume() {
        return limitConsume;
    }

    public void setLimitConsume(BigDecimal limitConsume) {
        this.limitConsume = limitConsume;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
