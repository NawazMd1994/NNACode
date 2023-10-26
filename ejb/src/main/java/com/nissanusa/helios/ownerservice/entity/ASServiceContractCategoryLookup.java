package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SVC_CNTRCT_CTGRY_LKUP")
@NamedQuery(name = "ASServiceContractCategoryLookup.findByServiceContractCategoryCode", query = "SELECT o FROM ASServiceContractCategoryLookup o WHERE o.serviceContractCategoryCode = :serviceContractCategoryCode")
/**
 * 
 * @author x787640	
 * @use  holds the After Sales - service contract Category Details
 *
 */
public class ASServiceContractCategoryLookup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SVC_CNTRCT_CTGRY_CD")
	private String serviceContractCategoryCode;

	@Column(name = "SVC_CNTRCT_CTGRY_SHRT_DS")
	private String serviceContractCategoryName;

	@Column(name = "SVC_CNTRCT_CTGRY_LONG_DS")
	private String serviceContractCategoryDescription;

	/**
	 * @return the serviceContractCategoryCode
	 */
	public String getServiceContractCategoryCode() {
		return serviceContractCategoryCode;
	}

	/**
	 * @param serviceContractCategoryCode
	 *            the serviceContractCategoryCode to set
	 */
	public void setServiceContractCategoryCode(
			String serviceContractCategoryCode) {
		this.serviceContractCategoryCode = serviceContractCategoryCode;
	}

	/**
	 * @return the serviceContractCategoryName
	 */
    public String getServiceContractCategoryName() {
		return serviceContractCategoryName;
	}

	/**
	 * @param serviceContractCategoryName
	 *            the serviceContractCategoryName to set
	 */
    public void setServiceContractCategoryName(
			String serviceContractCategoryName) {
		this.serviceContractCategoryName = serviceContractCategoryName;
	}

	/**
	 * @return the serviceContractCategoryDescription
	 */
    public String getServiceContractCategoryDescription() {
		return serviceContractCategoryDescription;
	}

	/**
	 * @param serviceContractCategoryDescription
	 *            the serviceContractCategoryDescription to set
	 */
    public void setServiceContractCategoryDescription(
			String serviceContractCategoryDescription) {
		this.serviceContractCategoryDescription = serviceContractCategoryDescription;
	}

}
