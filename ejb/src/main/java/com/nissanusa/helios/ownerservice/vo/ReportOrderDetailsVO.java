package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * X055765 To get the service history details from request json
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ReportOrderDetailsVO implements Serializable{

	private static final long serialVersionUID = -4751732523673061682L;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_TECHICIAN_ID)
	private String dealerInternalTechicianId;

	@JsonProperty(OwnerConstants.SERVICE_OP_CODE)
	private String serviceOpCode;

	@JsonProperty(OwnerConstants.SERVICE_OP_PAY_TYPE)
	private String seviceOpPayType;

	@JsonProperty(OwnerConstants.SERVICE_JOB_OP_TIME)
	private String serviceJobOpTime;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_SERVICE_OP_CODE)
	private String dealerInternalServiceOpCode;

	@JsonProperty(OwnerConstants.LAST_MOD_TSP)
	private String lastModTsp;

	@JsonProperty(OwnerConstants.CREATE_DATE )
	private String createDt;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_TECHNICIAN_FIRST_NAME)
	private String dealerInternalTechnicianFirstName;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_TECHNICIAN_LAST_NAME)
	private String dealerInternalTechnicianLastName;

	@JsonProperty(OwnerConstants.SVC_OP_PARTS_AMOUNT)
	private Integer svcOpPartsAmount;

	@JsonProperty(OwnerConstants.SVC_OP_LABOR_AMOUNT)
	private Integer svcOpLaborAmount;

	@JsonProperty(OwnerConstants.SVC_OP_MISC_AMOUNT)
	private Integer svcOpMiscAmount;

	@JsonProperty(OwnerConstants.SVC_OP_DESCRIPTION)
	private String svcOpDescription;

	@JsonProperty(OwnerConstants.CLEAN_OP_DESCRIPTION)
	private String cleanOpDescription;

	public String getDealerInternalTechicianId() {
		return dealerInternalTechicianId;
	}

	public void setDealerInternalTechicianId(String dealerInternalTechicianId) {
		this.dealerInternalTechicianId = dealerInternalTechicianId;
	}

	public String getServiceOpCode() {
		return serviceOpCode;
	}

	public void setServiceOpCode(String serviceOpCode) {
		this.serviceOpCode = serviceOpCode;
	}

	public String getSeviceOpPayType() {
		return seviceOpPayType;
	}

	public void setSeviceOpPayType(String seviceOpPayType) {
		this.seviceOpPayType = seviceOpPayType;
	}

	public String getServiceJobOpTime() {
		return serviceJobOpTime;
	}

	public void setServiceJobOpTime(String serviceJobOpTime) {
		this.serviceJobOpTime = serviceJobOpTime;
	}

	public String getDealerInternalServiceOpCode() {
		return dealerInternalServiceOpCode;
	}

	public void setDealerInternalServiceOpCode(String dealerInternalServiceOpCode) {
		this.dealerInternalServiceOpCode = dealerInternalServiceOpCode;
	}

	public String getLastModTsp() {
		return lastModTsp;
	}

	public void setLastModTsp(String lastModTsp) {
		this.lastModTsp = lastModTsp;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getDealerInternalTechnicianFirstName() {
		return dealerInternalTechnicianFirstName;
	}

	public void setDealerInternalTechnicianFirstName(
			String dealerInternalTechnicianFirstName) {
		this.dealerInternalTechnicianFirstName = dealerInternalTechnicianFirstName;
	}

	public String getDealerInternalTechnicianLastName() {
		return dealerInternalTechnicianLastName;
	}

	public void setDealerInternalTechnicianLastName(
			String dealerInternalTechnicianLastName) {
		this.dealerInternalTechnicianLastName = dealerInternalTechnicianLastName;
	}

	public Integer getSvcOpPartsAmount() {
		return svcOpPartsAmount;
	}

	public void setSvcOpPartsAmount(Integer svcOpPartsAmount) {
		this.svcOpPartsAmount = svcOpPartsAmount;
	}

	public Integer getSvcOpLaborAmount() {
		return svcOpLaborAmount;
	}

	public void setSvcOpLaborAmount(Integer svcOpLaborAmount) {
		this.svcOpLaborAmount = svcOpLaborAmount;
	}

	public Integer getSvcOpMiscAmount() {
		return svcOpMiscAmount;
	}

	public void setSvcOpMiscAmount(Integer svcOpMiscAmount) {
		this.svcOpMiscAmount = svcOpMiscAmount;
	}

	public String getSvcOpDescription() {
		return svcOpDescription;
	}

	public void setSvcOpDescription(String svcOpDescription) {
		this.svcOpDescription = svcOpDescription;
	}

	public String getCleanOpDescription() {
		return cleanOpDescription;
	}

	public void setCleanOpDescription(String cleanOpDescription) {
		this.cleanOpDescription = cleanOpDescription;
	}




}
