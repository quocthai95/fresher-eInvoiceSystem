package csc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "typeinvoice")

public class TypeInvoice {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  
	  
	  @NotNull
	  private String name_invoice;
	  
	  @NotNull
	  private String description;
	  
	  @NotNull
	  private float vat;
	  
	  public typeinvoice() { }

	  public typeinvoice(long id) { 
	    this.id = id;
	  }

	public typeinvoice(String name_invoice, String description, float vat) {
		super();
		this.name_invoice = name_invoice;
		this.description = description;
		this.vat = vat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName_invoice() {
		return name_invoice;
	}

	public void setName_invoice(String name_invoice) {
		this.name_invoice = name_invoice;
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
}
