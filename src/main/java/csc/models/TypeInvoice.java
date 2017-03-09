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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "typeinvoice")
public class TypeInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idType")
    private Collection<Invoice> invoiceCollection;

    public TypeInvoice() {
    }

    public TypeInvoice(Integer id) {
        this.id = id;
    }

    public TypeInvoice(Integer id, String nameInvoice, String description, float vat) {
        this.id = id;
        this.nameInvoice = nameInvoice;
        this.description = description;
        this.vat = vat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
