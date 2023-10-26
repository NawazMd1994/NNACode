/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: OwnerPortalUserVehiclePk
 */
@Embeddable
public class OwnerPortalUserVehiclePK implements Serializable {

	private static final long serialVersionUID = 2473670110863250989L;
	@Basic(optional = false)
	@Column(name = "USR_PRFL_ID", nullable = false, length = 20)
	private String userProfileId;
	@Basic(optional = false)
	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

	public OwnerPortalUserVehiclePK() {
		// Default Constructor
	}

	public OwnerPortalUserVehiclePK(String userProfileId, String vin) {
		this.userProfileId = userProfileId;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userProfileId != null ? userProfileId.hashCode() : 0);
		hash += (vin != null ? vin.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		Boolean response = true;
		if (!(object instanceof OwnerPortalUserVehiclePK)) {
			response = false;
		}
		OwnerPortalUserVehiclePK other = (OwnerPortalUserVehiclePK) object;
		if ((this.userProfileId == null && other.userProfileId != null)
				|| (this.userProfileId != null && !this.userProfileId
						.equals(other.userProfileId))) {
			response = false;
		}
		if ((this.vin == null && other.vin != null)
				|| (this.vin != null && !this.vin.equals(other.vin))) {
			response = false;
		}
		return response;
	}

	@Override
	public String toString() {
		return "com.nissanglobal.helios.ownerservice.entity.OwnerPortalUserVehiclePK[ userProfileId="
				+ userProfileId + ", vin=" + vin + " ]";
	}

}
