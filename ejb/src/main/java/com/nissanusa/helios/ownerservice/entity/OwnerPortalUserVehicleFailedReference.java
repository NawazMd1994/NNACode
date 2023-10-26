/**
 * 
 */
package com.nissanusa.helios.ownerservice.entity;

/**
 * @author MP00372143
 *
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: OwnerPortalUserVehicle
 */
@Entity
@Table(name = "OWNR_PORTL_USR_VHCL_FALD_RFRNC", catalog = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "OwnerPortalUserVehicleFailedReference.findAll", query = "SELECT o FROM OwnerPortalUserVehicleFailedReference o"),
		@NamedQuery(name = "OwnerPortalUserVehicleFailedReference.findByvehicleFailedReferenceKeyAndpreferedDocumentPath", query = "SELECT o FROM OwnerPortalUserVehicleFailedReference o where o.vehicleFailedReferenceKey = :vehicleFailedReferenceKey AND o.preferedDocumentPath = :preferedDocumentPath"),
		@NamedQuery(name = "OwnerPortalUserVehicleFailedReference.findByuserProfileIdAndCaseStatus", query = "SELECT o FROM OwnerPortalUserVehicleFailedReference o where o.userProfileId = :userProfileId AND o.validateStatusCode IS NOT NULL AND o.crmCaseStatus IS NOT NULL AND o.crmCaseId IS NOT NULL ORDER BY o.createTimestamp DESC"),
		@NamedQuery(name = "OwnerPortalUserVehicleFailedReference.findByvin", query = "SELECT o FROM OwnerPortalUserVehicleFailedReference o WHERE o.vin = :vin"),
		@NamedQuery(name = "OwnerPortalUserVehicleFailedReference.findByuserProfileIdAndvin", query = "SELECT o FROM OwnerPortalUserVehicleFailedReference o WHERE o.userProfileId = :userProfileId AND o.vin = :vin") })
public class OwnerPortalUserVehicleFailedReference implements Serializable,
		Comparable<OwnerPortalUserVehicleFailedReference> {
	private static final long serialVersionUID = 1L;

	@Id
	/*
	 * @SequenceGenerator(name="iFailedRefKey",
	 * sequenceName="USR_VHCL_FALD_RFRNC_SEQ", allocationSize=7)
	 * 
	 * @GeneratedValue(strategy=GenerationType.SEQUENCE,
	 * generator="iFailedRefKey")
	 */
	@Basic(optional = false)
	@Column(name = "USR_VHCL_FALD_RFRNC_KY", unique = true, nullable = false)
	private long vehicleFailedReferenceKey;

	@Basic(optional = false)
	@Column(name = "VHCL_NICK_NM", nullable = false, length = 17)
	private String vehicleNickName;

	@Column(name = "USR_PRFL_ID", nullable = false, length = 20)
	private String userProfileId;

	@Basic(optional = false)
	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

//	@Column(name = "MK_CD")
//	private String vehicleMakeCode;

	@Column(name = "VHCL_CRNT_MLG_NB")
	private Integer mileage;

	@Column(name = "VHCL_ANL_ESTMTD_MLG_NB")
	private Integer averageMileage;

	@Column(name = "PRCHS_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;

	@Column(name = "LSE_IN")
	private String leaseIn;

	@Column(name = "NEW_PREOWNR_STS_CD", nullable = false)
	private String preownerStatusCode;

	@Column(name = "LSE_TTL_TERM_NB")
	private Integer leaseTerm;

	@Column(name = "LSE_TTL_HLDER_NM")
	private String leaseTermHolder;

	@Column(name = "PRF_DCMNT_PTH_TX")
	private String preferedDocumentPath;

	@Column(name = "VLDTN_CMNT_TX")
	private String validateCommentText;

	@Column(name = "VLDTN_STS_CD")
	private String validateStatusCode;

	@Basic(optional = false)
	@Column(name = "CRTE_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@Basic(optional = false)
	@Column(name = "CRTE_USR_ID", nullable = false, length = 8)
	private String createUserId;

	@Basic(optional = false)
	@Column(name = "UPDT_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;

	@Basic(optional = false)
	@Column(name = "UPDT_USR_ID", nullable = false, length = 8)
	private String updateUserId;

	@Column(name = "CRM_CASE_STS_TX")
	private String crmCaseStatus;
	
	@Column(name = "CRM_CASE_ID")
	private String crmCaseId;
	
	@Column(name = "CRM_NTFCTN_IN")
	private String crmCaseNtftcn;
	
	@Column(name = "CRM_RMRKS_TX")
	private String crmRemarks;

	/**
	 * @return the vehicleFailedReferenceKey
	 */
	public long getVehicleFailedReferenceKey() {
		return vehicleFailedReferenceKey;
	}

	/**
	 * @param vehicleFailedReferenceKey
	 *            the vehicleFailedReferenceKey to set
	 */
	public void setVehicleFailedReferenceKey(long vehicleFailedReferenceKey) {
		this.vehicleFailedReferenceKey = vehicleFailedReferenceKey;
	}

	/**
	 * @return the vehicleNickName
	 */
	public String getVehicleNickName() {
		return vehicleNickName;
	}

	/**
	 * @param vehicleNickName
	 *            the vehicleNickName to set
	 */
	public void setVehicleNickName(String vehicleNickName) {
		this.vehicleNickName = vehicleNickName;
	}

	/**
	 * @return the userProfileId
	 */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId
	 *            the userProfileId to set
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin
	 *            the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

//	/**
//	 * @return the vehicleMakeCode
//	 */
//	public String getVehicleMakeCode() {
//		return vehicleMakeCode;
//	}
//
//	/**
//	 * @param vehicleMakeCode
//	 *            the vehicleMakeCode to set
//	 */
//	public void setVehicleMakeCode(String vehicleMakeCode) {
//		this.vehicleMakeCode = vehicleMakeCode;
//	}

	/**
	 * @return the mileage
	 */
	public Integer getMileage() {
		return mileage;
	}

	/**
	 * @param mileage
	 *            the mileage to set
	 */
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the averageMileage
	 */
	public Integer getAverageMileage() {
		return averageMileage;
	}

	/**
	 * @param averageMileage
	 *            the averageMileage to set
	 */
	public void setAverageMileage(Integer averageMileage) {
		this.averageMileage = averageMileage;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate
	 *            the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the leaseIn
	 */
	public String getLeaseIn() {
		return leaseIn;
	}

	/**
	 * @param leaseIn
	 *            the leaseIn to set
	 */
	public void setLeaseIn(String leaseIn) {
		this.leaseIn = leaseIn;
	}

	public String getPreownerStatusCode() {
		return preownerStatusCode;
	}

	public void setPreownerStatusCode(String preownerStatusCode) {
		this.preownerStatusCode = preownerStatusCode;
	}

	/**
	 * @return the leaseTerm
	 */
	public Integer getLeaseTerm() {
		return leaseTerm;
	}

	/**
	 * @param leaseTerm
	 *            the leaseTerm to set
	 */
	public void setLeaseTerm(Integer leaseTerm) {
		this.leaseTerm = leaseTerm;
	}

	/**
	 * @return the leaseTermHolder
	 */
	public String getLeaseTermHolder() {
		return leaseTermHolder;
	}

	/**
	 * @param leaseTermHolder
	 *            the leaseTermHolder to set
	 */
	public void setLeaseTermHolder(String leaseTermHolder) {
		this.leaseTermHolder = leaseTermHolder;
	}

	/**
	 * @return the dealerUserName
	 */
	public String getPreferedDocumentPath() {
		return preferedDocumentPath;
	}

	/**
	 * @param dealerUserName
	 *            the dealerUserName to set
	 */
	public void setPreferedDocumentPath(String preferedDocumentPath) {
		this.preferedDocumentPath = preferedDocumentPath;
	}

	/**
	 * @return the validateCommentText
	 */
	public String getValidateCommentText() {
		return validateCommentText;
	}

	/**
	 * @param validateCommentText
	 *            the validateCommentText to set
	 */
	public void setValidateCommentText(String validateCommentText) {
		this.validateCommentText = validateCommentText;
	}

	/**
	 * @return the validateStatusCode
	 */
	public String getValidateStatusCode() {
		return validateStatusCode;
	}

	/**
	 * @param validateStatusCode
	 *            the validateStatusCode to set
	 */
	public void setValidateStatusCode(String validateStatusCode) {
		this.validateStatusCode = validateStatusCode;
	}

	/**
	 * @return the createTimestamp
	 */
	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	/**
	 * @param createTimestamp
	 *            the createTimestamp to set
	 */
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the updateTimestamp
	 */
	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	/**
	 * @param updateTimestamp
	 *            the updateTimestamp to set
	 */
	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	/**
	 * @return the updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId
	 *            the updateUserId to set
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getCrmCaseStatus() {
		return crmCaseStatus;
	}

	/**
	 * @param validateCommentText
	 *            the validateCommentText to set
	 */
	public void setCrmCaseStatus(String crmCaseStatus) {
		this.crmCaseStatus = crmCaseStatus;
	}
	
	public String getCrmCaseId() {
		return crmCaseId;
	}

	public void setCrmCaseId(String crmCaseId) {
		this.crmCaseId = crmCaseId;
	}

	public String getCrmCaseNtftcn() {
		return crmCaseNtftcn;
	}

	public void setCrmCaseNtftcn(String crmCaseNtftcn) {
		this.crmCaseNtftcn = crmCaseNtftcn;
	}

	public String getCrmRemarks() {
		return crmRemarks;
	}

	public void setCrmRemarks(String crmRemarks) {
		this.crmRemarks = crmRemarks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (vehicleFailedReferenceKey ^ (vehicleFailedReferenceKey >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OwnerPortalUserVehicleFailedReference other = (OwnerPortalUserVehicleFailedReference) obj;
		if (vehicleFailedReferenceKey != other.vehicleFailedReferenceKey)
			return false;
		return true;
	}

	@Override
	public int compareTo(OwnerPortalUserVehicleFailedReference o) {
		return this.vehicleFailedReferenceKey > o.vehicleFailedReferenceKey ? 1
				: (this.vehicleFailedReferenceKey < o.vehicleFailedReferenceKey ? -1
						: 0);
	}

	/*
	 * implementing toString method to print orderId of Order
	 */
	@Override
	public String toString() {
		return String.valueOf(vehicleFailedReferenceKey);
	}

}
