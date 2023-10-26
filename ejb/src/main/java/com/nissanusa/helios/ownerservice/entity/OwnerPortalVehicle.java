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
 * @use Entity implementation class for Entity: OwnerPortalVehicle
 */
@Entity
@Table(name = "OWNR_PORTL_VHCL", catalog = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "OwnerPortalVehicle.findAll", query = "SELECT o FROM OwnerPortalVehicle o"),
		@NamedQuery(name = "OwnerPortalVehicle.findByVin", query = "SELECT o FROM OwnerPortalVehicle o WHERE o.vin = :vin"),
		@NamedQuery(name = "OwnerPortalVehicle.findByVehicleMakeCodeAndVin", query = "SELECT o FROM OwnerPortalVehicle o WHERE o.vehicleMakeCode = :vehicleMakeCode and o.vin = :vin"), })

public class OwnerPortalVehicle implements Serializable {

	private static final long serialVersionUID = 15L;
	@Id
	@Basic(optional = false)
	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

	@Column(name = "VHCL_MK_CD")
	private String vehicleMakeCode;

	@Column(name = "VHCL_YR_NB", precision = 5, scale = 1)
	private String modelYearNumber;
	
	// Commenting as mdl_ln_cd is not available in OP DB
	/*@Column(name = "MDL_LN_CD", length = 5)
	private String modelLineCode;*/

	@Column(name = "VHCL_NMC_MDL_CD", length = 5)
	private String vehicleModelCode;

	@Column(name = "VHCL_MDL_NM", length = 28)
	private String vehicleModelName;

	@Column(name = "VHCL_MDL_DS", length = 28)
	private String modelDescription;

	@Column(name = "VHCL_INTR_CLR_CD")
	private String vehicleInteriorColorCode;

	@Column(name = "VHCL_INTR_CLR_NM", length = 50)
	private String vehicleInteriorColorName;

	@Column(name = "VHCL_EXTR_CLR_CD", length = 3)
	private String vehicleExteriorColorCode;

	@Column(name = "VHCL_EXTR_CLR_NM", length = 50)
	private String vehicleExteriorColorName;

	@Column(name = "VHCL_OPTN_CD_TX", length = 75)
	private String vehicleOptionCode;

	@Column(name = "VHCL_TLMTCS_CD", length = 10)
	private String vehicleTelematicsCode;

	@Column(name = "VHCL_IMG_ID")
	private String vehicleImageId;

	@Column(name = "LVL2_66_KW_CHRGR_ENBLD_IN")
	private String levelCharger;

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

	public OwnerPortalVehicle() {
		// Default Constructor
	}

	public OwnerPortalVehicle(String vin) {
		this.vin = vin;
	}

	public OwnerPortalVehicle(String vin, Date createTimestamp,
			String createUserId, Date updateTimestamp, String updateUserId) {
		this.vin = vin;
		this.createTimestamp = createTimestamp;
		this.createUserId = createUserId;
		this.updateTimestamp = updateTimestamp;
		this.updateUserId = updateUserId;
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

	/**
	 * @return the vehicleMakeCode
	 */
	public String getVehicleMakeCode() {
		return vehicleMakeCode;
	}

	/**
	 * @param vehicleMakeCode
	 *            the vehicleMakeCode to set
	 */
	public void setVehicleMakeCode(String vehicleMakeCode) {
		this.vehicleMakeCode = vehicleMakeCode;
	}

	/**
	 * @return the modelYearNumber
	 */
	public String getModelYearNumber() {
		return modelYearNumber;
	}

	/**
	 * @param modelYearNumber
	 *            the modelYearNumber to set
	 */
	public void setModelYearNumber(String modelYearNumber) {
		this.modelYearNumber = modelYearNumber;
	}

	/**
	 * @return the modelLineCode
	 */
	// Commenting as mdl_ln_cd is not available in OP DB
/*	public String getModelLineCode() {
		return modelLineCode;
	}

	*//**
	 * @param modelLineCode
	 *            the modelLineCode to set
	 *//*
	public void setModelLineCode(String modelLineCode) {
		this.modelLineCode = modelLineCode;
	}*/

	/**
	 * @return the vehicleModelCode
	 */
	public String getVehicleModelCode() {
		return vehicleModelCode;
	}

	/**
	 * @param vehicleModelCode
	 *            the vehicleModelCode to set
	 */
	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}

	/**
	 * @return the vehicleModelName
	 */
	public String getVehicleModelName() {
		return vehicleModelName;
	}

	/**
	 * @param vehicleModelName
	 *            the vehicleModelName to set
	 */
	public void setVehicleModelName(String vehicleModelName) {
		this.vehicleModelName = vehicleModelName;
	}

	/**
	 * @return the modelDescription
	 */
	public String getModelDescription() {
		return modelDescription;
	}

	/**
	 * @param modelDescription
	 *            the modelDescription to set
	 */
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	/**
	 * @return the vehicleInteriorColorCode
	 */
	public String getVehicleInteriorColorCode() {
		return vehicleInteriorColorCode;
	}

	/**
	 * @param vehicleInteriorColorCode
	 *            the vehicleInteriorColorCode to set
	 */
	public void setVehicleInteriorColorCode(String vehicleInteriorColorCode) {
		this.vehicleInteriorColorCode = vehicleInteriorColorCode;
	}

	/**
	 * @return the vehicleInteriorColorName
	 */
	public String getVehicleInteriorColorName() {
		return vehicleInteriorColorName;
	}

	/**
	 * @param vehicleInteriorColorName
	 *            the vehicleInteriorColorName to set
	 */
	public void setVehicleInteriorColorName(String vehicleInteriorColorName) {
		this.vehicleInteriorColorName = vehicleInteriorColorName;
	}

	/**
	 * @return the vehicleExteriorColorCode
	 */
	public String getVehicleExteriorColorCode() {
		return vehicleExteriorColorCode;
	}

	/**
	 * @param vehicleExteriorColorCode
	 *            the vehicleExteriorColorCode to set
	 */
	public void setVehicleExteriorColorCode(String vehicleExteriorColorCode) {
		this.vehicleExteriorColorCode = vehicleExteriorColorCode;
	}

	/**
	 * @return the vehicleExteriorColorName
	 */
	public String getVehicleExteriorColorName() {
		return vehicleExteriorColorName;
	}

	/**
	 * @param vehicleExteriorColorName
	 *            the vehicleExteriorColorName to set
	 */
	public void setVehicleExteriorColorName(String vehicleExteriorColorName) {
		this.vehicleExteriorColorName = vehicleExteriorColorName;
	}

	/**
	 * @return the vehicleOptionCode
	 */
	public String getVehicleOptionCode() {
		return vehicleOptionCode;
	}

	/**
	 * @param vehicleOptionCode
	 *            the vehicleOptionCode to set
	 */
	public void setVehicleOptionCode(String vehicleOptionCode) {
		this.vehicleOptionCode = vehicleOptionCode;
	}

	/**
	 * @return the vehicleTelematicsCode
	 */
	public String getVehicleTelematicsCode() {
		return vehicleTelematicsCode;
	}

	/**
	 * @param vehicleTelematicsCode
	 *            the vehicleTelematicsCode to set
	 */
	public void setVehicleTelematicsCode(String vehicleTelematicsCode) {
		this.vehicleTelematicsCode = vehicleTelematicsCode;
	}

	/**
	 * @return the vehicleImageId
	 */
	public String getVehicleImageId() {
		return vehicleImageId;
	}

	/**
	 * @param vehicleImageId
	 *            the vehicleImageId to set
	 */
	public void setVehicleImageId(String vehicleImageId) {
		this.vehicleImageId = vehicleImageId;
	}

	/**
	 * @return the levelCharger
	 */
	public String getLevelCharger() {
		return levelCharger;
	}

	/**
	 * @param levelCharger
	 *            the levelCharger to set
	 */
	public void setLevelCharger(String levelCharger) {
		this.levelCharger = levelCharger;
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

}
