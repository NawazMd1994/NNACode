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
 * @use Entity implementation class for Entity: State
 */
@Entity
@Table(name = "ST", catalog = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "State.findAll", query = "SELECT s FROM State s"),
		@NamedQuery(name = "State.findByStateKey", query = "SELECT s FROM State s WHERE s.stateKey = :stateKey"),
		@NamedQuery(name = "State.findByStateName", query = "SELECT s FROM State s WHERE s.stateName = :stateName"),
		@NamedQuery(name = "State.findByStateCode", query = "SELECT s FROM State s WHERE s.stateCode = :stateCode") })
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ST_KY", nullable = false, precision = 22, scale = 0)
	private String stateKey;

	@Basic(optional = false)
	@Column(name = "ST_CD", nullable = false, length = 2)
	private String stateCode;

	@Basic(optional = false)
	@Column(name = "ST_NM", nullable = false, length = 55)
	private String stateName;

	@Column(name = "CNTRY_CD", length = 2)
	private String countryCode;

	@Basic(optional = false)
	@Column(name = "PRMRY_LNG_NM", nullable = false, length = 75)
	private String primaryLanguage;

	@Column(name = "SCNDRY_LNG_NM", length = 75)
	private String secondaryLanguage;

	@Basic(optional = false)
	@Column(name = "TMZN_CD", nullable = false, length = 5)
	private String timeZoneCode;

	@Basic(optional = false)
	@Column(name = "CRTE_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@Basic(optional = false)
	@Column(name = "CRTE_USR_ID", nullable = false, length = 6)
	private String createUserId;

	@Basic(optional = false)
	@Column(name = "UPDT_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;

	@Basic(optional = false)
	@Column(name = "UPDT_USR_ID", nullable = false, length = 6)
	private String updateUserId;

	public State() {
		// Default Constructor
	}

	public State(String stateKey) {
		this.stateKey = stateKey;
	}

	public State(String stateKey, String stateCode, String stateName,
			String primaryLanguage, String timeZoneCode, Date createTimestamp,
			String createUserId, Date updateTimestamp, String updateUserId) {
		this.stateKey = stateKey;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.primaryLanguage = primaryLanguage;
		this.timeZoneCode = timeZoneCode;
		this.createTimestamp = createTimestamp;
		this.createUserId = createUserId;
		this.updateTimestamp = updateTimestamp;
		this.updateUserId = updateUserId;
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
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode
	 *            the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the primaryLanguage
	 */
	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	/**
	 * @param primaryLanguage
	 *            the primaryLanguage to set
	 */
	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	/**
	 * @return the secondaryLanguage
	 */
	public String getSecondaryLanguage() {
		return secondaryLanguage;
	}

	/**
	 * @param secondaryLanguage
	 *            the secondaryLanguage to set
	 */
	public void setSecondaryLanguage(String secondaryLanguage) {
		this.secondaryLanguage = secondaryLanguage;
	}

	/**
	 * @return the timeZoneCode
	 */
	public String getTimeZoneCode() {
		return timeZoneCode;
	}

	/**
	 * @param timeZoneCode
	 *            the timeZoneCode to set
	 */
	public void setTimeZoneCode(String timeZoneCode) {
		this.timeZoneCode = timeZoneCode;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (stateKey != null ? stateKey.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		Boolean response = true;
		if (!(object instanceof State)) {
			response = false;
		}
		State other = (State) object;
		if ((this.stateKey == null && other.stateKey != null)
				|| (this.stateKey != null && !this.stateKey
						.equals(other.stateKey))) {
			response = false;
		}
		return response;
	}

	@Override
	public String toString() {
		return "com.nissanglobal.helios.ownerservice.entity.State[ stateKey="
				+ stateKey + " ]";
	}

}
