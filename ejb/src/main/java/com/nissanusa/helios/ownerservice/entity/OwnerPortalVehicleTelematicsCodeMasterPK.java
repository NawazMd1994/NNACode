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
 * @use Entity implementation class for Entity:
 *      OwnerPortalVehicleTelematicsCodeMasterPK
 */
@Embeddable
public class OwnerPortalVehicleTelematicsCodeMasterPK implements Serializable {

	private static final long serialVersionUID = 9000437804555662043L;
	@Basic(optional = false)
	@Column(name = "VHCL_NMC_MDL_CD", nullable = false, length = 5)
	private String vehicleModelCode;
	@Basic(optional = false)
	@Column(name = "VHCL_TLMTCS_OPTN_CD", nullable = false, length = 3)
	private String vehicleTelematicsOptionCode;
	@Basic(optional = false)
	@Column(name = "VHCL_TLMTCS_OPTN_TYP_CD", nullable = false, length = 2)
	private String vehicleTelematicsOptionTypeCode;

	public OwnerPortalVehicleTelematicsCodeMasterPK() {
		// Default Constructor
	}

	public OwnerPortalVehicleTelematicsCodeMasterPK(
			String vehicleNissanModelCode, String vehicleTelematicsOptionCode) {
		this.vehicleModelCode = vehicleNissanModelCode;
		this.vehicleTelematicsOptionCode = vehicleTelematicsOptionCode;
	}

	@Override
	public String toString() {
		return "OwnerPortalVehicleTelematicsCodeMasterPK [vehicleNissanModelCode="
				+ vehicleModelCode
				+ ", vehicleTelematicsOptionCode="
				+ vehicleTelematicsOptionCode
				+ ", vehicleTelematicsOptionTypeCode="
				+ vehicleTelematicsOptionTypeCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((vehicleModelCode == null) ? 0 : vehicleModelCode.hashCode());
		result = prime
				* result
				+ ((vehicleTelematicsOptionCode == null) ? 0
						: vehicleTelematicsOptionCode.hashCode());
		result = prime
				* result
				+ ((vehicleTelematicsOptionTypeCode == null) ? 0
						: vehicleTelematicsOptionTypeCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Boolean response = true;
		if (this == obj) {
			response = true;
		}
		if (obj == null) {
			response = false;
		}
		if(null !=obj){
		if (getClass() != obj.getClass()) {
			response = false;
		}
		OwnerPortalVehicleTelematicsCodeMasterPK other = (OwnerPortalVehicleTelematicsCodeMasterPK) obj;
		if (vehicleModelCode == null) {
			if (other.vehicleModelCode != null) {
				response = false;
			}
		} else if (!vehicleModelCode.equals(other.vehicleModelCode))
			response = false;
		if (vehicleTelematicsOptionCode == null) {
			if (other.vehicleTelematicsOptionCode != null)
				response = false;
		} else if (!vehicleTelematicsOptionCode
				.equals(other.vehicleTelematicsOptionCode)) {
			response = false;
		}
		if (vehicleTelematicsOptionTypeCode == null) {
			if (other.vehicleTelematicsOptionTypeCode != null)
				response = false;
		} else if (!vehicleTelematicsOptionTypeCode
				.equals(other.vehicleTelematicsOptionTypeCode)) {
			response = false;
		}
		}
		return response;
	}

	/**
	 * @return the vehicleNissanModelCode
	 */
	public String getVehicleModelCode() {
		return vehicleModelCode;
	}

	/**
	 * @param vehicleNissanModelCode
	 *            the vehicleNissanModelCode to set
	 */
	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}

	/**
	 * @return the vehicleTelematicsOptionCode
	 */
	public String getVehicleTelematicsOptionCode() {
		return vehicleTelematicsOptionCode;
	}

	/**
	 * @param vehicleTelematicsOptionCode
	 *            the vehicleTelematicsOptionCode to set
	 */
	public void setVehicleTelematicsOptionCode(
			String vehicleTelematicsOptionCode) {
		this.vehicleTelematicsOptionCode = vehicleTelematicsOptionCode;
	}

	/**
	 * @return the vehicleTelematicsOptionTypeCode
	 */
	public String getVehicleTelematicsOptionTypeCode() {
		return vehicleTelematicsOptionTypeCode;
	}

	/**
	 * @param vehicleTelematicsOptionTypeCode
	 *            the vehicleTelematicsOptionTypeCode to set
	 */
	public void setVehicleTelematicsOptionTypeCode(
			String vehicleTelematicsOptionTypeCode) {
		this.vehicleTelematicsOptionTypeCode = vehicleTelematicsOptionTypeCode;
	}

}
