package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

@JsonSerialize(include = Inclusion.NON_EMPTY)

public class OEMRecallDataVO implements Serializable {
	
	private static final long serialVersionUID = -1487040285304495404L;
	
	@JsonProperty(OwnerConstants.Vin)
	private String vin;
	
	@JsonProperty(OwnerConstants.NNA_RECALL_ID)
	private String nnaRecallId;
	
	@JsonProperty(OwnerConstants.NHTSA_RECALL_ID)
	private String nhtsaRecallId;
	
	@JsonProperty(OwnerConstants.RECALL_TYPE_CODE)
	private String recallTypeCode;
	
	@JsonProperty(OwnerConstants.RECALL_EFFECTIVE_DATE)
	private String recallEffectiveDate;
	
	@JsonProperty(OwnerConstants.DEFECT_SUMMARY)
	private String defectSummary;
	
	@JsonProperty(OwnerConstants.RECALL_SECONDARY_DESCRIPTION )
	private String recallSecondaryDescription;
	
	@JsonProperty(OwnerConstants.RISK_OF_NOT_DOING_REPAIR)
	private String riskofNotDoingRepair;
	
	@JsonProperty(OwnerConstants.REMEDY_DESCRIPTION)
	private String remedyDescription;

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	

	/**
	 * @return the nnaRecallId
	 */
	public String getNnaRecallId() {
		return nnaRecallId;
	}

	/**
	 * @param nnaRecallId the nnaRecallId to set
	 */
	public void setNnaRecallId(String nnaRecallId) {
		this.nnaRecallId = nnaRecallId;
	}

	/**
	 * @return the nhtsaRecallId
	 */
	public String getNhtsaRecallId() {
		return nhtsaRecallId;
	}

	/**
	 * @param nhtsaRecallId the nhtsaRecallId to set
	 */
	public void setNhtsaRecallId(String nhtsaRecallId) {
		this.nhtsaRecallId = nhtsaRecallId;
	}

	/**
	 * @return the recallTypeCode
	 */
	public String getRecallTypeCode() {
		return recallTypeCode;
	}

	/**
	 * @param recallTypeCode the recallTypeCode to set
	 */
	public void setRecallTypeCode(String recallTypeCode) {
		this.recallTypeCode = recallTypeCode;
	}

	/**
	 * @return the recallEffectiveDate
	 */
	public String getRecallEffectiveDate() {
		return recallEffectiveDate;
	}

	/**
	 * @param recallEffectiveDate the recallEffectiveDate to set
	 */
	public void setRecallEffectiveDate(String recallEffectiveDate) {
		this.recallEffectiveDate = recallEffectiveDate;
	}

	/**
	 * @return the defectSummary
	 */
	public String getDefectSummary() {
		return defectSummary;
	}

	/**
	 * @param defectSummary the defectSummary to set
	 */
	public void setDefectSummary(String defectSummary) {
		this.defectSummary = defectSummary;
	}

	/**
	 * @return the recallSecondaryDescription
	 */
	public String getRecallSecondaryDescription() {
		return recallSecondaryDescription;
	}

	/**
	 * @param recallSecondaryDescription the recallSecondaryDescription to set
	 */
	public void setRecallSecondaryDescription(String recallSecondaryDescription) {
		this.recallSecondaryDescription = recallSecondaryDescription;
	}

	/**
	 * @return the riskofNotDoingRepair
	 */
	public String getRiskofNotDoingRepair() {
		return riskofNotDoingRepair;
	}

	/**
	 * @param riskofNotDoingRepair the riskofNotDoingRepair to set
	 */
	public void setRiskofNotDoingRepair(String riskofNotDoingRepair) {
		this.riskofNotDoingRepair = riskofNotDoingRepair;
	}

	/**
	 * @return the remedyDescription
	 */
	public String getRemedyDescription() {
		return remedyDescription;
	}

	/**
	 * @param remedyDescription the remedyDescription to set
	 */
	public void setRemedyDescription(String remedyDescription) {
		this.remedyDescription = remedyDescription;
	}
	
	
	
	
	
}
