package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: OwnerPortalUserPhonePk
 */
@Embeddable
public class OwnerPortalUserPhonePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "USR_PRFL_ID", nullable = false, length = 20)
	private String userProfileId;
//	@Basic(optional = false)
//	@Column(name = "PHN_TYP_ID", nullable = false, length = 8)
//	private String phoneTypeId;

	public OwnerPortalUserPhonePK() {
		// Default Constructor
	}

	public OwnerPortalUserPhonePK(String userProfileId, String phoneTypeId) {
		this.userProfileId = userProfileId;
//		this.phoneTypeId = phoneTypeId;
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

//	/**
//	 * @return the phoneTypeId
//	 */
//	public String getPhoneTypeId() {
//		return phoneTypeId;
//	}
//
//	/**
//	 * @param phoneTypeId
//	 *            the phoneTypeId to set
//	 */
//	public void setPhoneTypeId(String phoneTypeId) {
//		this.phoneTypeId = phoneTypeId;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result
//				+ ((phoneTypeId == null) ? 0 : phoneTypeId.hashCode());
		result = prime * result
				+ ((userProfileId == null) ? 0 : userProfileId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Boolean response = true;
		if (this == obj)
			return true;
		if (obj == null)
			response = false;
		if(null !=obj){
		if (getClass() != obj.getClass())
			response = false;
		OwnerPortalUserPhonePK other = (OwnerPortalUserPhonePK) obj;
//		if (phoneTypeId == null) {
//			if (other.phoneTypeId != null)
//				response = false;
//		} else if (!phoneTypeId.equals(other.phoneTypeId))
//			response = false;
		if (userProfileId == null) {
			if (other.userProfileId != null)
				response = false;
		} else if (!userProfileId.equals(other.userProfileId)){
			response = false;
		}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OwnerPortalUserPhonePK [userProfileId=" + userProfileId + "]";
//				+ ", phoneTypeId=" + phoneTypeId + "]";
	}

}
