package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * X055765 To get the service history details from request json
 */

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class HistoryVO implements Serializable{


	private static final long serialVersionUID = -5752211322572801550L;


	@JsonProperty(OwnerConstants.DEALER_CODE)
	private String dealerCode;

	@JsonProperty(OwnerConstants.DEALER_NAME)
	private String dealerName;

	@JsonProperty(OwnerConstants.SERVICE_DATE)
	private String serviceDate;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_REPAIR_ORDERID)
	private String dealerInternalRepairOrderId;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_CUSTOMERID)
	private String dealerInternalCustomerId;

	@JsonProperty(OwnerConstants.MILEAGE)
	private Integer mileage;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_SERVICE_ADVISORID )
	private String dealerInternalServiceAdvisorId;

	@JsonProperty(OwnerConstants.TOTAL_PARTS_AMOUNT)
	private String totalPartsAmount;

	@JsonProperty(OwnerConstants.TOTAL_LABOR_AMOUNT)
	private String totalLaborAmount;

	@JsonProperty(OwnerConstants.REPAIR_ORDER_TOTAL_AMOUNT)
	private String repairOrderTotalAmount;

	@JsonProperty(OwnerConstants.CUSTOMER_TYPE)
	private String customerType;

	@JsonProperty(OwnerConstants.REPAIR_ORDER_DETAIL_LINE_AUDIT)
	private String repairOrderDetailLineAudit;

	@JsonProperty(OwnerConstants.TOTAL_CUSTOMER_PAY_LABOR_AMOUNT)
	private String totalCustomerPayLaborAmount;

	@JsonProperty(OwnerConstants.TOTAL_CUSTOMER_PAY_PARTS_AMOUNT)
	private String totalCustomerPayPartsAmount;

	@JsonProperty(OwnerConstants.TOTAL_CUSTOMER_PAY_MISC_AMOUNT)
	private Integer totalCustomerPayMiscAmount;

	@JsonProperty(OwnerConstants.TOTAL_WARRANTY_PAY_LABOR_AMOUNT)
	private String totalWarrantyPayLaborAmount;

	@JsonProperty(OwnerConstants.TOTAL_WARRANTY_PAY_PARTS_AMOUNT)
	private String totalWarrantyPayPartsAmount;

	@JsonProperty(OwnerConstants.TOTAL_WARRANTY_PAY_MISC_AMOUNT)
	private String totalWarrantyPayMiscAmount;

	@JsonProperty(OwnerConstants.TOTAL_INTERNAL_PAY_LABOR_AMOUNT)
	private String totalInternalPayLaborAmount;

	@JsonProperty(OwnerConstants.TOTAL_INTERNAL_PAY_PARTS_AMOUNT)
	private String totalInternalPayPartsAmount;

	@JsonProperty(OwnerConstants.TOTAL_INTERNAL_PAY_MISC_AMOUNT )
	private String totalInternalPayMiscAmount;

	@JsonProperty(OwnerConstants.LAST_MOD_TSP)
	private String lastModTsp;

	@JsonProperty(OwnerConstants.CREATE_DATE)
	private String createDt;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_SERVICE_ADVISOR_LAST_NAME)
	private String dealerInternalServiceAdvisorLastName;

	@JsonProperty(OwnerConstants.DEALER_INTERNAL_SERVICE_ADVISOR_FIRST_NAME)
	private String dealerInternalServiceAdvisorFirstName;

	@JsonProperty(OwnerConstants.CONTACT_ID)
	private String contactId;

	@JsonProperty(OwnerConstants.REPAIR_ORDER_DETAILS)
	private List<ReportOrderDetailsVO> reportOrderDetails;

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getDealerInternalRepairOrderId() {
		return dealerInternalRepairOrderId;
	}

	public void setDealerInternalRepairOrderId(String dealerInternalRepairOrderId) {
		this.dealerInternalRepairOrderId = dealerInternalRepairOrderId;
	}

	public String getDealerInternalCustomerId() {
		return dealerInternalCustomerId;
	}

	public void setDealerInternalCustomerId(String dealerInternalCustomerId) {
		this.dealerInternalCustomerId = dealerInternalCustomerId;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getDealerInternalServiceAdvisorId() {
		return dealerInternalServiceAdvisorId;
	}

	public void setDealerInternalServiceAdvisorId(
			String dealerInternalServiceAdvisorId) {
		this.dealerInternalServiceAdvisorId = dealerInternalServiceAdvisorId;
	}

	public String getTotalPartsAmount() {
		return totalPartsAmount;
	}

	public void setTotalPartsAmount(String totalPartsAmount) {
		this.totalPartsAmount = totalPartsAmount;
	}

	public String getTotalLaborAmount() {
		return totalLaborAmount;
	}

	public void setTotalLaborAmount(String totalLaborAmount) {
		this.totalLaborAmount = totalLaborAmount;
	}

	public String getRepairOrderTotalAmount() {
		return repairOrderTotalAmount;
	}

	public void setRepairOrderTotalAmount(String repairOrderTotalAmount) {
		this.repairOrderTotalAmount = repairOrderTotalAmount;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getRepairOrderDetailLineAudit() {
		return repairOrderDetailLineAudit;
	}

	public void setRepairOrderDetailLineAudit(String repairOrderDetailLineAudit) {
		this.repairOrderDetailLineAudit = repairOrderDetailLineAudit;
	}

	public String getTotalCustomerPayLaborAmount() {
		return totalCustomerPayLaborAmount;
	}

	public void setTotalCustomerPayLaborAmount(String totalCustomerPayLaborAmount) {
		this.totalCustomerPayLaborAmount = totalCustomerPayLaborAmount;
	}

	public String getTotalCustomerPayPartsAmount() {
		return totalCustomerPayPartsAmount;
	}

	public void setTotalCustomerPayPartsAmount(String totalCustomerPayPartsAmount) {
		this.totalCustomerPayPartsAmount = totalCustomerPayPartsAmount;
	}

	public Integer getTotalCustomerPayMiscAmount() {
		return totalCustomerPayMiscAmount;
	}

	public void setTotalCustomerPayMiscAmount(Integer totalCustomerPayMiscAmount) {
		this.totalCustomerPayMiscAmount = totalCustomerPayMiscAmount;
	}

	public String getTotalWarrantyPayLaborAmount() {
		return totalWarrantyPayLaborAmount;
	}

	public void setTotalWarrantyPayLaborAmount(String totalWarrantyPayLaborAmount) {
		this.totalWarrantyPayLaborAmount = totalWarrantyPayLaborAmount;
	}

	public String getTotalWarrantyPayPartsAmount() {
		return totalWarrantyPayPartsAmount;
	}

	public void setTotalWarrantyPayPartsAmount(String totalWarrantyPayPartsAmount) {
		this.totalWarrantyPayPartsAmount = totalWarrantyPayPartsAmount;
	}

	public String getTotalWarrantyPayMiscAmount() {
		return totalWarrantyPayMiscAmount;
	}

	public void setTotalWarrantyPayMiscAmount(String totalWarrantyPayMiscAmount) {
		this.totalWarrantyPayMiscAmount = totalWarrantyPayMiscAmount;
	}

	public String getTotalInternalPayLaborAmount() {
		return totalInternalPayLaborAmount;
	}

	public void setTotalInternalPayLaborAmount(String totalInternalPayLaborAmount) {
		this.totalInternalPayLaborAmount = totalInternalPayLaborAmount;
	}

	public String getTotalInternalPayPartsAmount() {
		return totalInternalPayPartsAmount;
	}

	public void setTotalInternalPayPartsAmount(String totalInternalPayPartsAmount) {
		this.totalInternalPayPartsAmount = totalInternalPayPartsAmount;
	}

	public String getTotalInternalPayMiscAmount() {
		return totalInternalPayMiscAmount;
	}

	public void setTotalInternalPayMiscAmount(String totalInternalPayMiscAmount) {
		this.totalInternalPayMiscAmount = totalInternalPayMiscAmount;
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

	public String getDealerInternalServiceAdvisorLastName() {
		return dealerInternalServiceAdvisorLastName;
	}

	public void setDealerInternalServiceAdvisorLastName(
			String dealerInternalServiceAdvisorLastName) {
		this.dealerInternalServiceAdvisorLastName = dealerInternalServiceAdvisorLastName;
	}

	public String getDealerInternalServiceAdvisorFirstName() {
		return dealerInternalServiceAdvisorFirstName;
	}

	public void setDealerInternalServiceAdvisorFirstName(
			String dealerInternalServiceAdvisorFirstName) {
		this.dealerInternalServiceAdvisorFirstName = dealerInternalServiceAdvisorFirstName;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public List<ReportOrderDetailsVO> getReportOrderDetails() {
		return reportOrderDetails;
	}

	public void setReportOrderDetails(List<ReportOrderDetailsVO> reportOrderDetails) {
		this.reportOrderDetails = reportOrderDetails;
	}
}
