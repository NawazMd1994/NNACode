package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * 
 * @author x178099
 * @use this class will hold the json request object for validate data-update
 *      account endpoint
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class AccountVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(OwnerConstants.TITLE)
	private String title;
	@JsonProperty(OwnerConstants.FIRST_NAME)
	private String firstName;
	@JsonProperty(OwnerConstants.MIDDLE_NAME)
	private String middleName;
	@JsonProperty(OwnerConstants.LAST_NAME)
	private String lastName;
	@JsonProperty(OwnerConstants.SECOND_LAST_NAME)
	private String secondLastName;
	@JsonProperty(OwnerConstants.ADDRESS)
	private AddressVO address;
	@JsonProperty(OwnerConstants.MOBILE_NUMBER)
	private String mobileNumber;
	@JsonProperty(OwnerConstants.MOBILE_NETWORK_OPERATOR)
	private String mobileNetworkOperator;
	@JsonProperty(OwnerConstants.LANDLINE_NUMBER)
	private String landlineNumber;
	@JsonProperty(OwnerConstants.JWT)
	private String jwt;
	@JsonProperty(OwnerConstants.REQUESTEDSERVICE)
	private String requestedService;
	@JsonProperty(OwnerConstants.PRSNHASH)
	private String personHashId;
	@JsonProperty(OwnerConstants.CDIID)
	private String cdiid;
	@JsonProperty(OwnerConstants.EMAIL)
	private String email;
	@JsonProperty(OwnerConstants.NEW_EMAIL)
	private String newEmail;
	@JsonProperty(OwnerConstants.OLD_EMAIL)
	private String oldEmail;
	@JsonProperty(OwnerConstants.TERMSANDCONDITIONS)
	private boolean termsAndConditions;
	private OptInVO optIn;
	@JsonProperty(OwnerConstants.AUTH)
	private String password;
	@JsonProperty(OwnerConstants.MARKETING_PREFERENCES)
	private MarketingPreferenceVO marketingPreference;
	@JsonProperty(OwnerConstants.DISCLAIMER_PREFERENCES)
	private DisclaimerPreferencesVO disclaimerPreferences;
	private String brand;
	private String notificationCode;


	/**
	 * @return the marketingPreference
	 */
	public MarketingPreferenceVO getMarketingPreference() {
		return marketingPreference;
	}

	/**
	 * @param marketingPreference
	 *            the marketingPreference to set
	 */
	public void setMarketingPreference(MarketingPreferenceVO marketingPreference) {
		this.marketingPreference = marketingPreference;
	}

	/**
	 * @return the DisclaimerPreferences
	 */
	public DisclaimerPreferencesVO getDisclaimerPreferences() {
		return disclaimerPreferences;
	}

	/**
	 * @param marketingPreference
	 *            the marketingPreference to set
	 */
	public void setDisclaimerPreferences(
			DisclaimerPreferencesVO disclaimerPreferences) {
		this.disclaimerPreferences = disclaimerPreferences;
	}

	/**
	 * @return the optIn
	 */
	public OptInVO getOptIn() {
		return optIn;
	}

	/**
	 * @return the termsAndConditions
	 */
	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}

	/**
	 * @param termsAndConditions
	 *            the termsAndConditions to set
	 */
	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	/**
	 * @param optIn
	 *            the optIn to set
	 */
	public void setOptIn(OptInVO optIn) {
		this.optIn = optIn;
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
	 * @return the personHashId
	 */
	public String getPersonHashId() {
		return personHashId;
	}

	/**
	 * @param personHashId
	 *            the personHashId to set
	 */
	public void setPersonHashId(String personHashId) {
		this.personHashId = personHashId;
	}

	/**
	 * @return the cdiid
	 */
	public String getCdiid() {
		return cdiid;
	}

	/**
	 * @param cdiid
	 *            the cdiid to set
	 */
	public void setCdiid(String cdiid) {
		this.cdiid = cdiid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the address
	 */
	public AddressVO getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(AddressVO address) {
		this.address = address;
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
	 * @return the secondLastName
	 */
	public String getSecondLastName() {
		return secondLastName;
	}

	/**
	 * @param secondLastName
	 *            the secondLastName to set
	 */
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the mobileNetworkOperator
	 */
	public String getMobileNetworkOperator() {
		return mobileNetworkOperator;
	}

	/**
	 * @param mobileNetworkOperator
	 *            the mobileNetworkOperator to set
	 */
	public void setMobileNetworkOperator(String mobileNetworkOperator) {
		this.mobileNetworkOperator = mobileNetworkOperator;
	}

	/**
	 * @return the landlineNumber
	 */
	public String getLandlineNumber() {
		return landlineNumber;
	}

	/**
	 * @param landlineNumber
	 *            the landlineNumber to set
	 */
	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @param jwt
	 *            the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @return the requestedService
	 */
	public String getRequestedService() {
		return requestedService;
	}

	/**
	 * @param requestedService
	 *            the requestedService to set
	 */
	public void setRequestedService(String requestedService) {
		this.requestedService = requestedService;
	}

	/**
	 * @return the newEmail
	 */
	public String getNewEmail() {
		return newEmail;
	}

	/**
	 * @param newEmail
	 *            the newEmail to set
	 */
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	/**
	 * @return the oldEmail
	 */
	public String getOldEmail() {
		return oldEmail;
	}

	/**
	 * @param oldEmail
	 *            the oldEmail to set
	 */
	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public String getNotificationCode() {
		return notificationCode;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

}
