package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for ValidateData endpoint
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ValidateDataVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(OwnerConstants.VIN)
	private String vin;
	@JsonProperty(OwnerConstants.USERPROFILEID)
	private String userProfileId;
	@JsonProperty(OwnerConstants.MODELCODE)
	private String modelCode;
	@JsonProperty(OwnerConstants.MAKECODE)
	private String makeCode;
	@JsonProperty(OwnerConstants.JWT)
	private String jwt;
	@JsonProperty(OwnerConstants.PRSNHASH)
	private String personHashId;
	@JsonProperty(OwnerConstants.REQUESTEDSERVICE)
	private String requestedservice;
	@JsonProperty(OwnerConstants.EMAIL)
	private String emailId;
	@JsonProperty(OwnerConstants.MILEAGE)
	private Integer mileage;
	@JsonProperty(OwnerConstants.AVGMILEAGE)
	private Integer averageMileage;
	@JsonProperty(OwnerConstants.NICKNAME)
	private String nickname;
	@JsonProperty(OwnerConstants.FILENAME)
	private String fileName;
	@JsonProperty(OwnerConstants.CONTENT_TYPE)
	private String contentType;
	@JsonProperty(OwnerConstants.CONTENT)
	private byte[] content;
	@JsonProperty(OwnerConstants.LEASETERM)
	private Integer leaseTerm;
	private String brand;
	@JsonProperty(OwnerConstants.ID)
	private String id;
	@JsonProperty(OwnerConstants.ACTION)
	private String action;
	@JsonProperty(OwnerConstants.MESSAGE_ID)
	private String messageId;

	
	public String getPersonHashId() {
		return personHashId;
	}

	public void setPersonHashId(String personHashId) {
		this.personHashId = personHashId;
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
	 * @return the makeCode
	 */
	public String getMakeCode() {
		return makeCode;
	}

	/**
	 * @param makeCode
	 *            the makeCode to set
	 */
	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}

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
	 * @return the nickName
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emlId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the vin
	 */
	@JsonProperty(OwnerConstants.VIN)
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
	 * @return the modelCode
	 */
	public String getModelCode() {
		return modelCode;
	}

	/**
	 * @param modelCode
	 *            the modelCode to set
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	/**
	 * @return the jwtToken
	 */
	@JsonProperty(OwnerConstants.JWT)
	public String getJwt() {
		return jwt;
	}

	/**
	 * @param jwtToken
	 *            the jwtToken to set
	 */

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @return the requestedservice
	 */
	@JsonProperty(OwnerConstants.REQUESTEDSERVICE)
	public String getRequestedservice() {
		return requestedservice;
	}

	/**
	 * @param requestedservice
	 *            the requestedservice to set
	 */
	public void setRequestedservice(String requestedservice) {
		this.requestedservice = requestedservice;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	
	
}
