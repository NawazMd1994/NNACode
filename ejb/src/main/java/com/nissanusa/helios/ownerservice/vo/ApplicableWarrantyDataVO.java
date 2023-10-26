/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author MP00372143
 *
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)  
   
public class ApplicableWarrantyDataVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @JsonProperty(OwnerConstants.WarrantyDescription)
	    private String warrantyDescription;
	    @JsonProperty(OwnerConstants.WarrantyExpirationDate)
	    private String warrantyExpirationDate;
	    @JsonProperty(OwnerConstants.WarrantyExpirationMiles)
	    private Integer warrantyExpirationMiles;
		/**
		 * @return the warrantyDescription
		 */
		public String getWarrantyDescription() {
			return warrantyDescription;
		}
		/**
		 * @param warrantyDescription the warrantyDescription to set
		 */
		public void setWarrantyDescription(String warrantyDescription) {
			this.warrantyDescription = warrantyDescription;
		}
		/**
		 * @return the warrantyExpirationDate
		 */
		public String getWarrantyExpirationDate() {
			return warrantyExpirationDate;
		}
		/**
		 * @param warrantyExpirationDate the warrantyExpirationDate to set
		 */
		public void setWarrantyExpirationDate(String warrantyExpirationDate) {
			this.warrantyExpirationDate = warrantyExpirationDate;
		}
		/**
		 * @return the warrantyExpirationMiles
		 */
		public Integer getWarrantyExpirationMiles() {
			return warrantyExpirationMiles;
		}
		/**
		 * @param warrantyExpirationMiles the warrantyExpirationMiles to set
		 */
		public void setWarrantyExpirationMiles(Integer warrantyExpirationMiles) {
			this.warrantyExpirationMiles = warrantyExpirationMiles;
		}
	    



}
