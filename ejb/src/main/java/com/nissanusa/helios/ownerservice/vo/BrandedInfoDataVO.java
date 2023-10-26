/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author MP00372143
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY) 
public class BrandedInfoDataVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	    @JsonProperty(OwnerConstants.STATE_CODE)
	    private String stateCode;
	    @JsonProperty(OwnerConstants.MODELYEAR)
	    private String modelYear;
	    @JsonProperty(OwnerConstants.SOURCE_RECORD_DATE)
	    private String sourceRecordDate; 
	    @JsonProperty(OwnerConstants.RECORD_TYPE_CODE)
	    private String recordTypeCode;
	    @JsonProperty(OwnerConstants.POLK_RUN_DATE)
	    private String polkRunDate;
	    @JsonProperty(OwnerConstants.SOURCE_ID_CODE)
	    private String sourceIDCode; 
	    @JsonProperty(OwnerConstants.BRANDED_CODE)
	    private String brandedCode;
	    @JsonProperty(OwnerConstants.DUPLICATE_CODE)
	    private String duplicateCode;
	    @JsonProperty(OwnerConstants.TITLE_NUMBER)
	    private String titleNumber; 
	    @JsonProperty(OwnerConstants.BRANDED_DESCRIPTION)
	    private String brandedDescription;
	    @JsonProperty(OwnerConstants.SOURCE_LAST_UPDATEDATE)
	    private String sourceLastUpdateDate;
		/**
		 * @return the stateCode
		 */
		public String getStateCode() {
			return stateCode;
		}
		/**
		 * @param stateCode the stateCode to set
		 */
		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}
		/**
		 * @return the modelYear
		 */
		public String getModelYear() {
			return modelYear;
		}
		/**
		 * @param modelYear the modelYear to set
		 */
		public void setModelYear(String modelYear) {
			this.modelYear = modelYear;
		}
		/**
		 * @return the sourceRecordDate
		 */
		public String getSourceRecordDate() {
			return sourceRecordDate;
		}
		/**
		 * @param sourceRecordDate the sourceRecordDate to set
		 */
		public void setSourceRecordDate(String sourceRecordDate) {
			this.sourceRecordDate = sourceRecordDate;
		}
		/**
		 * @return the recordTypeCode
		 */
		public String getRecordTypeCode() {
			return recordTypeCode;
		}
		/**
		 * @param recordTypeCode the recordTypeCode to set
		 */
		public void setRecordTypeCode(String recordTypeCode) {
			this.recordTypeCode = recordTypeCode;
		}
		/**
		 * @return the polkRunDate
		 */
		public String getPolkRunDate() {
			return polkRunDate;
		}
		/**
		 * @param polkRunDate the polkRunDate to set
		 */
		public void setPolkRunDate(String polkRunDate) {
			this.polkRunDate = polkRunDate;
		}
		/**
		 * @return the sourceIDCode
		 */
		public String getSourceIDCode() {
			return sourceIDCode;
		}
		/**
		 * @param sourceIDCode the sourceIDCode to set
		 */
		public void setSourceIDCode(String sourceIDCode) {
			this.sourceIDCode = sourceIDCode;
		}
		/**
		 * @return the brandedCode
		 */
		public String getBrandedCode() {
			return brandedCode;
		}
		/**
		 * @param brandedCode the brandedCode to set
		 */
		public void setBrandedCode(String brandedCode) {
			this.brandedCode = brandedCode;
		}
		/**
		 * @return the duplicateCode
		 */
		public String getDuplicateCode() {
			return duplicateCode;
		}
		/**
		 * @param duplicateCode the duplicateCode to set
		 */
		public void setDuplicateCode(String duplicateCode) {
			this.duplicateCode = duplicateCode;
		}
		/**
		 * @return the titleNumber
		 */
		public String getTitleNumber() {
			return titleNumber;
		}
		/**
		 * @param titleNumber the titleNumber to set
		 */
		public void setTitleNumber(String titleNumber) {
			this.titleNumber = titleNumber;
		}
		/**
		 * @return the brandedDescription
		 */
		public String getBrandedDescription() {
			return brandedDescription;
		}
		/**
		 * @param brandedDescription the brandedDescription to set
		 */
		public void setBrandedDescription(String brandedDescription) {
			this.brandedDescription = brandedDescription;
		}
		/**
		 * @return the sourceLastUpdateDate
		 */
		public String getSourceLastUpdateDate() {
			return sourceLastUpdateDate;
		}
		/**
		 * @param sourceLastUpdateDate the sourceLastUpdateDate to set
		 */
		public void setSourceLastUpdateDate(String sourceLastUpdateDate) {
			this.sourceLastUpdateDate = sourceLastUpdateDate;
		} 
	    
	   
	
		
		

}
