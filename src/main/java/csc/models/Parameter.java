package csc.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "parameter")
public class Parameter implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "para_key")
	private String paraKey;

	@Basic(optional = false)
	@Column(name = "para_value")
	private String paraValue;
	
	@Column(name = "description",length=10485760)
	private String description;
	
	public Parameter() {
	}
			
	public Parameter(Integer id, String paraKey, String paraValue, String description) {
		super();
		this.id = id;
		this.paraKey = paraKey;
		this.paraValue = paraValue;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParaKey() {
		return paraKey;
	}

	public void setParaKey(String paraKey) {
		this.paraKey = paraKey;
	}

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
