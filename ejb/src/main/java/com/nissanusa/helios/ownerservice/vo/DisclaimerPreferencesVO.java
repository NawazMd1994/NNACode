package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;


/**
 * @author X055765
 * @use this class will hold the json request object for getting disclaimer preferences in
 *      account endpoint
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class DisclaimerPreferencesVO implements Serializable{
	
	  
	    private static final long serialVersionUID = 1L;
		@JsonProperty(OwnerConstants.WORKPHONE)
	    private List<String> workPhone;
	    @JsonProperty(OwnerConstants.MOBILEPHONE)
	    private List<String> mobilePhone;
	    
	    /**
	     * @return the workPhone
	     */
		public List<String> getWorkPhone() {
			return workPhone;
		}
		
		 /**
	     * @param workPhone
	     *            the workPhone to set
	     */
		public void setWorkPhone(List<String> workPhone) {
			this.workPhone = workPhone;
		}
		
		   /**
	     * @return the mobilePhone
	     */
		public List<String> getMobilePhone() {
			return mobilePhone;
		}
		
		 /**
	     * @param mobilePhone
	     *            the mobilePhone to set
	     */
		public void setMobilePhone(List<String> mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

}
