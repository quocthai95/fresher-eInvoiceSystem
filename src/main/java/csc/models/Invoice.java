package csc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "invoice")
public class Invoice {
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	 @NotNull
	  private String id_customer;
	 
	 @NotNull
	  private String id_cpn;
	 
	 @NotNull
	  private int id_type;
	 
	 @NotNull
	//  private  Date date;
	 
	 @Null
	 private String contract_number;
	 
	 @Null
	 private String name_service;
	 
	 @NotNull
	 private String index_consumed;
	 
	 
	 
}

