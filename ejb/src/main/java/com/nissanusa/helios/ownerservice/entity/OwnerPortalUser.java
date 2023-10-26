/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
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
 * @use Entity implementation class for Entity: OwnerPortalUser
 */
@Entity
@Table(name = "OWNR_PORTL_USR", catalog = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "OwnerPortalUser.findAll", query = "SELECT o FROM OwnerPortalUser o"),
		@NamedQuery(name = "OwnerPortalUser.findByuserProfileId", query = "SELECT o FROM OwnerPortalUser o WHERE o.userProfileId = :userProfileId"),
		@NamedQuery(name = "OwnerPortalUser.findBycustomerDataIntegrationId", query = "SELECT o FROM OwnerPortalUser o WHERE o.customerDataIntegrationId = :customerDataIntegrationId"),
		@NamedQuery(name = "OwnerPortalUser.findBypersonalHashId", query = "SELECT o FROM OwnerPortalUser o WHERE o.personalHashId = :personalHashId"),
		@NamedQuery(name = "OwnerPortalUser.findBypersonalHashIdandChannelCode", query = "SELECT o FROM OwnerPortalUser o WHERE o.personalHashId = :personalHashId AND o.ownerPortalTypeCode = :ownerPortalTypeCode"),
		@NamedQuery(name = "OwnerPortalUser.findByemailId", query = "SELECT o FROM OwnerPortalUser o WHERE lower(o.emailId) = :emailId"),
		@NamedQuery(name = "OwnerPortalUser.findByemailIdandownerPortalTypeCode", query = "SELECT o FROM OwnerPortalUser o WHERE lower(o.emailId) = :emailId AND o.ownerPortalTypeCode = :ownerPortalTypeCode"),
		@NamedQuery(name = "OwnerPortalUser.findByemailIdandpersonalHashIdandownerPortalTypeCode", query = "SELECT o FROM OwnerPortalUser o WHERE lower(o.emailId) = :emailId AND o.personalHashId = :personalHashId AND o.ownerPortalTypeCode = :ownerPortalTypeCode"),
		@NamedQuery(name = "OwnerPortalUser.findByemailIdandpersonalHashId", query = "SELECT o FROM OwnerPortalUser o WHERE lower(o.emailId) = :emailId AND o.personalHashId = :personalHashId"),
		@NamedQuery(name = "OwnerPortalUser.findBycustomerDataIntegrationIdandemailId", query = "SELECT o FROM OwnerPortalUser o WHERE o.customerDataIntegrationId = :customerDataIntegrationId AND o.emailId = :emailId"),
		@NamedQuery(name = "OwnerPortalUser.findBycustomerDataIntegrationIdEmailIdandpersonalHashId", query = "SELECT o FROM OwnerPortalUser o WHERE o.customerDataIntegrationId = :customerDataIntegrationId AND o.personalHashId = :personalHashId AND o.emailId = :emailId"), })
public class OwnerPortalUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "USR_PRFL_ID", nullable = false, length = 20)
	private String userProfileId;

	@Basic(optional = false)
	@Column(name = "DSTBTN_CHNL_CD", nullable = false, length = 1)
	private String ownerPortalTypeCode;
	
	@Basic(optional = false)
	@Column(name = "EML_ID", nullable = false, length = 100)
	private String emailId;

	@Column(name="CELL_PHN_NB", precision=12)
	private BigDecimal cellPhoneNumber;
	
	@Column(name="MBL_CARR_NM", length=20)
	private String mobileCarrNm;
	
	@Column(name="WRK_PHN_NB", precision=12)
	private BigDecimal workPhoneNumber;
	
	@Basic(optional = false)
	@Column(name = "PRVS_EML_ID", nullable = false, length = 60)
	private String previousEmailId;

	@Basic(optional = false)
	@Column(name = "PSWRD_TX", nullable = false, length = 80)
	private String password;

	@Column(name = "CSTMR_DATA_INTGRN_ID", length = 12)
	private String customerDataIntegrationId;

	@Basic(optional = false)
	@Column(name = "PRSNL_HSH_ID", nullable = false, length = 100)
	private String personalHashId;

	@Basic(optional = false)
	@Column(name = "ACNT_STS_CD", nullable = false, length = 5)
	private String accountStatusCode;

	@Column(name = "PRFX_NM", length = 5)
	private String prefixName;

	@Basic(optional = false)
	@Column(name = "FRST_NM", nullable = false, length = 60)
	private String firstName;

	@Column(name = "MIDL_NM", length = 55)
	private String middleName;

	@Basic(optional = false)
	@Column(name = "LST_NM", nullable = false, length = 60)
	private String lastName;

	@Basic(optional = false)
	@Column(name = "LCL_UTLTY_OPT_OUT_CD",nullable=false, precision=1)
	private BigDecimal localUtilityOptOutCode;

	public OwnerPortalUser() {
		// Default constructor
	}

	/**
	 * @return the localUtilityOptOutCode
	 */
	public BigDecimal getLocalUtilityOptOutCode() {
		return localUtilityOptOutCode;
	}

	/**
	 * @param localUtilityOptOutCode
	 *            the localUtilityOptOutCode to set
	 */
	public void setLocalUtilityOptOutCode(BigDecimal localUtilityOptOutCode) {
		this.localUtilityOptOutCode = localUtilityOptOutCode;
	}

	@Column(name = "SFX_NM", length = 5)
	private String suffixName;

	@Column(name = "ADRS_TX", length = 100)
	private String addressText;

	@Column(name = "CTY_NM", length = 45)
	private String cityName;

	@Column(name = "ST_KY")
	private String stateKey;

	@Column(name = "ZIP_CD", nullable = false, length = 5)
	private String postalCode;

	@Column(name = "CNTRY_CD", nullable = false, length = 2)
	private String countryCode;

	@Column(name = "RGN_CD", length = 3)
	private String regionCode;

	@Column(name = "LNG_CD", length = 2)
	private String languageCode;

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

	public OwnerPortalUser(String userProfileId) {
		this.userProfileId = userProfileId;
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
	 * @return the ownerPortalTypeCode
	 */
	public String getOwnerPortalTypeCode() {
		return ownerPortalTypeCode;
	}

	/**
	 * @param ownerPortalTypeCode
	 *            the ownerPortalTypeCode to set
	 */
	public void setOwnerPortalTypeCode(String ownerPortalTypeCode) {
		this.ownerPortalTypeCode = ownerPortalTypeCode;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the customerDataIntegrationId
	 */
	public String getCustomerDataIntegrationId() {
		return customerDataIntegrationId;
	}

	/**
	 * @param customerDataIntegrationId
	 *            the customerDataIntegrationId to set
	 */
	public void setCustomerDataIntegrationId(String customerDataIntegrationId) {
		this.customerDataIntegrationId = customerDataIntegrationId;
	}

	/**
	 * @return the personalHashId
	 */
	public String getPersonalHashId() {
		return personalHashId;
	}

	/**
	 * @param personalHashId
	 *            the personalHashId to set
	 */
	public void setPersonalHashId(String personalHashId) {
		this.personalHashId = personalHashId;
	}

	/**
	 * @return the accountStatusCode
	 */
	public String getAccountStatusCode() {
		return accountStatusCode;
	}

	/**
	 * @param accountStatusCode
	 *            the accountStatusCode to set
	 */
	public void setAccountStatusCode(String accountStatusCode) {
		this.accountStatusCode = accountStatusCode;
	}

	/**
	 * @return the prefixName
	 */
	public String getPrefixName() {
		return prefixName;
	}

	/**
	 * @param prefixName
	 *            the prefixName to set
	 */
	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the suffixName
	 */
	public String getSuffixName() {
		return suffixName;
	}

	/**
	 * @param suffixName
	 *            the suffixName to set
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	/**
	 * @return the addressText
	 */
	public String getAddressText() {
		return addressText;
	}

	/**
	 * @param addressText
	 *            the addressText to set
	 */
	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the stateKey
	 */
	public String getStateKey() {
		return stateKey;
	}

	/**
	 * @param stateKey
	 *            the stateKey to set
	 */
	public void setStateKey(String stateKey) {
		this.stateKey = stateKey;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}

	/**
	 * @param regionCode
	 *            the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
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

	/**
	 * @return the previousEmailId
	 */
	public String getPreviousEmailId() {
		return previousEmailId;
	}

	/**
	 * @param previousEmailId
	 *            the previousEmailId to set
	 */
	public void setPreviousEmailId(String previousEmailId) {
		this.previousEmailId = previousEmailId;
}

	public BigDecimal getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(BigDecimal cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getMobileCarrNm() {
		return mobileCarrNm;
	}

	public void setMobileCarrNm(String mobileCarrNm) {
		this.mobileCarrNm = mobileCarrNm;
	}

	public BigDecimal getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(BigDecimal workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

}
