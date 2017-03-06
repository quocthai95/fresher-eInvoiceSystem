package csc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class customer {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	 @NotNull
	 private long id_user;
	 
	 @NotNull
	 private String id_customer;
	 
	 @NotNull
	 private String name_customer;
	 
	 @NotNull
	 private String address;
	 
	 @NotNull
	 private String email;
	 
	 @NotNull
	 private int phone;
	 
	 @NotNull
	 private int tax_code;
	 
	 @NotNull
	 private double limit_consume;
	 
	 public customer() { }
	 public customer(long id) { 
		    this.id = id;
		  }
	 public customer(long id_user, String id_customer, String name_customer, String address, String email, int phone,
			int tax_code, double limit_consume) {
		
		this.id_user = id_user;
		this.id_customer = id_customer;
		this.name_customer = name_customer;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.tax_code = tax_code;
		this.limit_consume = limit_consume;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	public String getId_customer() {
		return id_customer;
	}
	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
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
	public int getTax_code() {
		return tax_code;
	}
	public void setTax_code(int tax_code) {
		this.tax_code = tax_code;
	}
	public double getLimit_consume() {
		return limit_consume;
	}
	public void setLimit_consume(double limit_consume) {
		this.limit_consume = limit_consume;
	}
}
