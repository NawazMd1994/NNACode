/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author GV101559 To get the contracts from request json
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ASContractVO {

    private static final long serialVersionUID = 1L;
    @JsonProperty("product")
    String product;
    @JsonProperty("policyNumber")
    String policyNumber;
    @JsonProperty("planTerm")
    Integer planTerm;
    @JsonProperty("effectiveDate")
    String effectiveDate;
    @JsonProperty("expireDate")
    String expireDate;
    @JsonProperty("cancelEffectiveDate")
    String cancelEffectiveDate;
    @JsonProperty("expireMileage")
    Integer expireMileage;

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the policyNumber
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * @param policyNumber
     *            the policyNumber to set
     */
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    /**
     * @return the planTerm
     */
    public Integer getPlanTerm() {
        return planTerm;
    }

    /**
     * @param planTerm
     *            the planTerm to set
     */
    public void setPlanTerm(Integer planTerm) {
        this.planTerm = planTerm;
    }

    /**
     * @return the effectiveDate
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate
     *            the effectiveDate to set
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return the expireDate
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate
     *            the expireDate to set
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the cancelEffectiveDate
     */
    public String getCancelEffectiveDate() {
        return cancelEffectiveDate;
    }

    /**
     * @param cancelEffectiveDate
     *            the cancelEffectiveDate to set
     */
    public void setCancelEffectiveDate(String cancelEffectiveDate) {
        this.cancelEffectiveDate = cancelEffectiveDate;
    }

    /**
     * @return the expireMileage
     */
    public Integer getExpireMileage() {
        return expireMileage;
    }

    /**
     * @param expireMileage
     *            the expireMileage to set
     */
    public void setExpireMileage(Integer expireMileage) {
        this.expireMileage = expireMileage;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
