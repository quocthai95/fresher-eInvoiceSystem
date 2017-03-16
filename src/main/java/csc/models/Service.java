package csc.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "service")
public class Service implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "name_service",length=10485760)
    private String nameService;
    
	@Basic(optional = false)
	@Column(name = "unit")
	private BigDecimal unit;
	
    @JsonIgnoreProperties("serviceCollection")
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private TypeInvoice idType;
	
	public Service() {
		// TODO Auto-generated constructor stub
	}

	public Service(Long id, String nameService, BigDecimal unit, TypeInvoice idType) {
		super();
		this.id = id;
		this.nameService = nameService;
		this.unit = unit;
		this.idType = idType;
	}
	public Long getId() {
		return id;
	}

	public BigDecimal getUnit() {
		return unit;
	}
	
	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public TypeInvoice getIdType() {
		return idType;
	}

	public void setIdType(TypeInvoice idType) {
		this.idType = idType;
	}
	
}
