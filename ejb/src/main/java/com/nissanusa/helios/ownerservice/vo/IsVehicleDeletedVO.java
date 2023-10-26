package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class IsVehicleDeletedVO implements Serializable {

	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		@JsonProperty(OwnerConstants.MARITZ)
	    private boolean maritz;
	    @JsonProperty(OwnerConstants.GDC)
	    private boolean gdc;
	    @JsonProperty(OwnerConstants.SXM)
	    private boolean sxm;
	    @JsonProperty(OwnerConstants.MARITZDELDATE)
	    private Date maritzDelDate;
	    
		public boolean isMaritz() {
			return maritz;
		}
		public void setMaritz(boolean maritz) {
			this.maritz = maritz;
		}
		public boolean isGdc() {
			return gdc;
		}
		public void setGdc(boolean gdc) {
			this.gdc = gdc;
		}
		public boolean isSxm() {
			return sxm;
		}
		public void setSxm(boolean sxm) {
			this.sxm = sxm;
		}
		public Date getMaritzDelDate() {
			return maritzDelDate;
		}
		public void setMaritzDelDate(Date maritzDelDate) {
			this.maritzDelDate = maritzDelDate;
		}
		
	    
	    
}
