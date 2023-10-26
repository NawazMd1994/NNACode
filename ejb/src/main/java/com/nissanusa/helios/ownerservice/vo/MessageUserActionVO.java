package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class MessageUserActionVO  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(OwnerConstants.VIN)
	private String vin;
	
	@JsonProperty(OwnerConstants.ID)
	private String id;
	
	@JsonProperty(OwnerConstants.ACTION)
	private String action;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}
