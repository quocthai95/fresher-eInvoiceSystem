package csc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role {
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 
	 @NotNull
	  private String name_role;
	 
	 @NotNull
	  private String desciption;
	 
	  public role() { }

	  public role(long id) { 
	    this.id = id;
	  }

	public role(String name_role, String desciption) {
		super();
		this.name_role = name_role;
		this.desciption = desciption;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName_role() {
		return name_role;
	}

	public void setName_role(String name_role) {
		this.name_role = name_role;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	  
}
