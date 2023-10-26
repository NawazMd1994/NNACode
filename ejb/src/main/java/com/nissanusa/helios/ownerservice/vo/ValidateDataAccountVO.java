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
public class ValidateDataAccountVO implements Serializable {

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
	@JsonProperty(OwnerConstants.TERMSANDCONDITIONS)
	private String termsAndConditions;
	@JsonProperty(OwnerConstants.JWT)
	private String jwt;
	@JsonProperty(OwnerConstants.REQUESTEDSERVICE)
	private String requestedService;
	private String brand;
	@JsonProperty(OwnerConstants.LINKREFERENCEID)
	private String linkReferenceId;
	@JsonProperty(OwnerConstants.EMAIL)
	private String email;
	@JsonProperty(OwnerConstants.NEW_EMAIL)
	private String newEmail;
	@JsonProperty(OwnerConstants.NEW_PASSWORD)
	private String newPassword;
	@JsonProperty(OwnerConstants.CURRENT_PASSWORD)
	private String currentPassword;
	@JsonProperty(OwnerConstants.CODE)
	private String code;
	@JsonProperty(OwnerConstants.MARKETING_PREFERENCES)
	private MarketingPreferenceVO marketingPreference;
	@JsonProperty(OwnerConstants.DISCLAIMER_PREFERENCES)
	private DisclaimerPreferencesVO disclaimerPreferences;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the currentPassword
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * @param currentPassword
	 *            the currentPassword to set
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	/**
	 * @return the marketingPreference
	 */
	public MarketingPreferenceVO getMarketingPreference() {
		return marketingPreference;
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
	 * @return the termsAndConditions
	 */
	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	/**
	 * @param termsAndConditions
	 *            the termsAndConditions to set
	 */
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	/**
	 * @return the linkReferenceId
	 */
	public String getLinkReferenceId() {
		return linkReferenceId;
	}

	/**
	 * @param linkReferenceId
	 *            the linkReferenceId to set
	 */
	public void setLinkReferenceId(String linkReferenceId) {
		this.linkReferenceId = linkReferenceId;
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

}
