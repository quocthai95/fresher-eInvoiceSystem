package csc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")

public class Company {
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	 @NotNull
	  private String id_cpn;
	 
	 @NotNull
	  private String name_cpn;
	 
	 @NotNull
	  private String address;
	 
	 @NotNull
	  private int phone_cpn;
	 
	 @NotNull
	  private int fax;
	 
	 @NotNull
	  private String bank_account;
	 
	 @NotNull
	  private String tax_code;
	 
	 public company() { }
	 public company(long id) { 
		    this.id = id;
		  }
	 public company(String id_cpn, String name_cpn, String address, int phone_cpn, int fax, String bank_account,
			String tax_code) {
		
		this.id_cpn = id_cpn;
		this.name_cpn = name_cpn;
		this.address = address;
		this.phone_cpn = phone_cpn;
		this.fax = fax;
		this.bank_account = bank_account;
		this.tax_code = tax_code;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getId_cpn() {
		return id_cpn;
	}
	public void setId_cpn(String id_cpn) {
		this.id_cpn = id_cpn;
	}
	public String getName_cpn() {
		return name_cpn;
	}
	public void setName_cpn(String name_cpn) {
		this.name_cpn = name_cpn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone_cpn() {
		return phone_cpn;
	}
	public void setPhone_cpn(int phone_cpn) {
		this.phone_cpn = phone_cpn;
	}
	public int getFax() {
		return fax;
	}
	public void setFax(int fax) {
		this.fax = fax;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}	
}
