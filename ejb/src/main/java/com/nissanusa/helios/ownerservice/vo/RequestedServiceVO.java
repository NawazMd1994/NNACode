package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class RequestedServiceVO implements Serializable  {

	private static final long serialVersionUID = 1L;

	@JsonProperty(OwnerConstants.ID)
	private String id;
	
	@JsonProperty(OwnerConstants.CS_STATUS)
	private String status;
	
	@JsonProperty(OwnerConstants.ENDDATE)
	private String endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
