package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * rs101547 To get the telematics subscription from request json
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class TelematicsSubscriptionVO implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1487040285304495404L;
    String productCode;
    String description;
    String startDate;
    String endDate;
    Timestamp renewalDate;
    Timestamp cancelDate;
    String status;
    String paymentType;
    String term;

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     *            the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the renewalDate
     */
    public Timestamp getRenewalDate() {
        return renewalDate;
    }

    /**
     * @param renewalDate
     *            the renewalDate to set
     */
    public void setRenewalDate(Timestamp renewalDate) {
        this.renewalDate = renewalDate;
    }

    /**
     * @return the cancelDate
     */
    public Timestamp getCancelDate() {
        return cancelDate;
    }

    /**
     * @param cancelDate
     *            the cancelDate to set
     */
    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType
     *            the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term
     *            the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
