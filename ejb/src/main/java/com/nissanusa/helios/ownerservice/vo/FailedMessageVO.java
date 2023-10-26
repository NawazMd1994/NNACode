package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for person in
 *      save,update,view and delete endpoints
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class FailedMessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(OwnerConstants.MESSAGE)
    private String message;
    @JsonProperty(OwnerConstants.FAILEDSTATUS)
    private String status;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    

    
}

