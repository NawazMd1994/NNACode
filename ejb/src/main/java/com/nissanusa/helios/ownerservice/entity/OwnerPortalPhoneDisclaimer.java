/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.entity;

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
 * @use Entity implementation class for Entity: OwnerPortalPhoneDisclaimer
 */
@Entity
@Table(name = "OWNR_PORTL_PHN_DSCLMR", catalog = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "OwnerPortalPhoneDisclaimer.findAll", query = "SELECT o FROM OwnerPortalPhoneDisclaimer o"),
		@NamedQuery(name = "OwnerPortalPhoneDisclaimer.findByUserProfileId", query = "SELECT o FROM OwnerPortalPhoneDisclaimer o WHERE o.userProfileId = :userProfileId"), })
public class OwnerPortalPhoneDisclaimer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "USR_PRFL_ID", nullable = false, length = 20)
	private String userProfileId;
	@Basic(optional = false)
	@Column(name = "MBL_PHN_AUTO_DILR_OPT_IN", length = 1)
	private String mobilePhoneAutoDialerOpt;
	@Basic(optional = false)
	@Column(name = "MBL_PHN_SMS_OPT_IN", nullable = false, length = 1)
	private String mobilePhoneSmsOpt;
	@Basic(optional = false)
	@Column(name = "WRK_PHN_AUTO_DILR_OPT_IN", length = 1)
	private String workPhoneAutoDialerOpt;
	@Basic(optional = false)
	@Column(name = "WRK_PHN_SMS_OPT_IN", nullable = false, length = 1)
	private String workPhoneSmsOpt;
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

	public OwnerPortalPhoneDisclaimer() {
		// Default Constructor
	}

	public OwnerPortalPhoneDisclaimer(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public OwnerPortalPhoneDisclaimer(String userProfileId,
			String mobilePhoneAutoDialerOpt, String mobilePhoneSmsOpt,
			String workPhoneAutoDialerOpt, String workPhoneSmsOpt,
			Date createTimestamp, String createUserId, Date updateTimestamp,
			String updateUserId) {
		this.userProfileId = userProfileId;
		this.mobilePhoneAutoDialerOpt = mobilePhoneAutoDialerOpt;
		this.mobilePhoneSmsOpt = mobilePhoneSmsOpt;
		this.workPhoneAutoDialerOpt = workPhoneAutoDialerOpt;
		this.workPhoneSmsOpt = workPhoneSmsOpt;
		this.createTimestamp = createTimestamp;
		this.createUserId = createUserId;
		this.updateTimestamp = updateTimestamp;
		this.updateUserId = updateUserId;
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
	 * @return the mobilePhoneAutoDialerOpt
	 */
	public String getMobilePhoneAutoDialerOpt() {
		return mobilePhoneAutoDialerOpt;
	}

	/**
	 * @param mobilePhoneAutoDialerOpt
	 *            the mobilePhoneAutoDialerOpt to set
	 */
	public void setMobilePhoneAutoDialerOpt(String mobilePhoneAutoDialerOpt) {
		this.mobilePhoneAutoDialerOpt = mobilePhoneAutoDialerOpt;
	}

	/**
	 * @return the mobilePhoneSmsOpt
	 */
	public String getMobilePhoneSmsOpt() {
		return mobilePhoneSmsOpt;
	}

	/**
	 * @param mobilePhoneSmsOpt
	 *            the mobilePhoneSmsOpt to set
	 */
	public void setMobilePhoneSmsOpt(String mobilePhoneSmsOpt) {
		this.mobilePhoneSmsOpt = mobilePhoneSmsOpt;
	}

	/**
	 * @return the workPhoneAutoDialerOpt
	 */
	public String getWorkPhoneAutoDialerOpt() {
		return workPhoneAutoDialerOpt;
	}

	/**
	 * @param workPhoneAutoDialerOpt
	 *            the workPhoneAutoDialerOpt to set
	 */
	public void setWorkPhoneAutoDialerOpt(String workPhoneAutoDialerOpt) {
		this.workPhoneAutoDialerOpt = workPhoneAutoDialerOpt;
	}

	/**
	 * @return the workPhoneSmsOpt
	 */
	public String getWorkPhoneSmsOpt() {
		return workPhoneSmsOpt;
	}

	/**
	 * @param workPhoneSmsOpt
	 *            the workPhoneSmsOpt to set
	 */
	public void setWorkPhoneSmsOpt(String workPhoneSmsOpt) {
		this.workPhoneSmsOpt = workPhoneSmsOpt;
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

	@Override
	public boolean equals(Object object) {
		Boolean response = true;
		if (!(object instanceof OwnerPortalPhoneDisclaimer)) {
			response = false;
		}
		OwnerPortalPhoneDisclaimer other = (OwnerPortalPhoneDisclaimer) object;
		if ((this.userProfileId == null && other.userProfileId != null)
				|| (this.userProfileId != null && !this.userProfileId
						.equals(other.userProfileId))) {
			response = false;
		}
		return response;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userProfileId != null ? userProfileId.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return "com.nissanglobal.helios.ownerservice.entity.OwnerPortalPhoneDisclaimer[ usrPrflId="
				+ userProfileId + " ]";
	}

}
