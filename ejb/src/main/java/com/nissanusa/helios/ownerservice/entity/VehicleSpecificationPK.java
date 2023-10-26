package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: VehicleSpecificationPK
 */
@Embeddable
public class VehicleSpecificationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "MODEL_CODE", nullable = false, length = 10)
	private String modelCode;
	@Basic(optional = false)
	@Column(name = "EQUIP_CODE", nullable = false, length = 25)
	private String equipmentCode;

	public VehicleSpecificationPK() {
		// Default Constructor
	}

	public VehicleSpecificationPK(String modelCode, String equipmentCode) {
		this.modelCode = modelCode;
		this.equipmentCode = equipmentCode;
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
	 * @return the equipmentCode
	 */
	public String getEquipmentCode() {
		return equipmentCode;
	}

	/**
	 * @param equipmentCode
	 *            the equipmentCode to set
	 */
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((equipmentCode == null) ? 0 : equipmentCode.hashCode());
		result = prime * result
				+ ((modelCode == null) ? 0 : modelCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleSpecificationPK other = (VehicleSpecificationPK) obj;
		if (equipmentCode == null) {
			if (other.equipmentCode != null)
				return false;
		} else if (!equipmentCode.equals(other.equipmentCode))
			return false;
		if (modelCode == null) {
			if (other.modelCode != null)
				return false;
		} else if (!modelCode.equals(other.modelCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EquipmentPK [modelCode=" + modelCode + ", equipmentCode="
				+ equipmentCode + "]";
	}

}
