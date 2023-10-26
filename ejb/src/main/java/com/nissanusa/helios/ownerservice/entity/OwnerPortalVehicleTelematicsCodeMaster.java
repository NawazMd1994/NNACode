/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity:
 *      OwnerPortalVehicleTelematicsCodeMaster
 */ 
@Entity
@Table(name = "OWNR_PORTL_VHCL_TLMTCS_CD_MSTR", catalog = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "OwnerPortalVehicleTelematicsCodeMaster.findAll", query = "SELECT o FROM OwnerPortalVehicleTelematicsCodeMaster o"),
		@NamedQuery(name = "OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCode", query = "SELECT o FROM OwnerPortalVehicleTelematicsCodeMaster o WHERE o.ownerPortalVehicleTelematicsCodeMasterPK.vehicleModelCode = :vehicleModelCode"),
		@NamedQuery(name = "OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYear", query = "SELECT o FROM OwnerPortalVehicleTelematicsCodeMaster o WHERE o.ownerPortalVehicleTelematicsCodeMasterPK.vehicleModelCode = :vehicleModelCode AND o.modelYear = :modelYear"),
		@NamedQuery(name = "OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYearandMakeCode", query = "SELECT o FROM OwnerPortalVehicleTelematicsCodeMaster o WHERE o.ownerPortalVehicleTelematicsCodeMasterPK.vehicleModelCode = :vehicleModelCode AND o.modelYear = :modelYear AND o.makeCode = :makeCode"),
		@NamedQuery(name = "OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYearandOptionCode", query = "SELECT o FROM OwnerPortalVehicleTelematicsCodeMaster o WHERE o.ownerPortalVehicleTelematicsCodeMasterPK.vehicleModelCode = :vehicleModelCode AND o.modelYear = :modelYear AND o.vehicleOptionCode = :vehicleOptionCode"),
		@NamedQuery(name = "OwnerPortalVehicleTelematicsCodeMaster.findByOptionCodeList", query = "SELECT o FROM OwnerPortalVehicleTelematicsCodeMaster o WHERE o.ownerPortalVehicleTelematicsCodeMasterPK.vehicleModelCode = :vehicleModelCode AND o.modelYear = :modelYear AND o.vehicleOptionCode in :vocList")})
public class OwnerPortalVehicleTelematicsCodeMaster implements Serializable {
 
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected OwnerPortalVehicleTelematicsCodeMasterPK ownerPortalVehicleTelematicsCodeMasterPK;

	@Column(name = "VHCL_YR_NB", nullable = false)
	private String modelYear;
	
	@Column(name = "VHCL_TLMTCS_OPTN_CD", nullable = false, insertable=false, updatable=false)
	private String vehicleOptionCode;

//	@Column(name = "MDL_NM")
//	private String modelName;

	@Column(name = "VHCL_MK_CD", nullable = false)
	private String makeCode;

//	@Column(name = "EML_CD")
//	private String emailTypeCode;

	@Column(name = "VHCL_HEAD_UNT_CD")
	private String headUnitCode;

	@Column(name = "TLMTCS_OPTN_CD_ACTV_IN")
	private String activeIn;

//	@Column(name = "LANCH_DT")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date lanchDate;

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

	@Column(name="VHCL_TLMTCS_OPTN_TYP_CD", nullable=false, insertable=false, updatable=false)
	private String optionTypeCode;
	
	//x100994 - OS-1742
	@Column(name = "TCU_TYP_NM")
	private String tcuTypeCode;
	
	public String getOptionTypeCode() {
		return optionTypeCode;
	}

	public void setOptionTypeCode(String optionTypeCode) {
		this.optionTypeCode = optionTypeCode;
	}

	public OwnerPortalVehicleTelematicsCodeMaster() {
		// Default Constructor
	}

	public OwnerPortalVehicleTelematicsCodeMaster(
			OwnerPortalVehicleTelematicsCodeMasterPK ownerPortalVehicleTelematicsCodeMasterPK,
			Date createTimestamp, String createUserId, Date updateTimestamp,
			String updateUserId) {
		this.ownerPortalVehicleTelematicsCodeMasterPK = ownerPortalVehicleTelematicsCodeMasterPK;
		this.createTimestamp = createTimestamp;
		this.createUserId = createUserId;
		this.updateTimestamp = updateTimestamp;
		this.updateUserId = updateUserId;

	}

	public OwnerPortalVehicleTelematicsCodeMaster(String vhclNmcMdlCd,
			String vhclTlmtcsOptnCd) {
		this.ownerPortalVehicleTelematicsCodeMasterPK = new OwnerPortalVehicleTelematicsCodeMasterPK(
				vhclNmcMdlCd, vhclTlmtcsOptnCd);
	}

	public OwnerPortalVehicleTelematicsCodeMasterPK getOwnerPortalVehicleTelematicsCodeMasterPK() {
		return ownerPortalVehicleTelematicsCodeMasterPK;
	}

	public void setOwnerPortalVehicleTelematicsCodeMasterPK(
			OwnerPortalVehicleTelematicsCodeMasterPK ownerPortalVehicleTelematicsCodeMasterPK) {
		this.ownerPortalVehicleTelematicsCodeMasterPK = ownerPortalVehicleTelematicsCodeMasterPK;
	}

	/**
	 * @return the modelYear
	 */
	public String getModelYear() {
		return modelYear;
	}

	/**
	 * @param modelYear
	 *            the modelYear to set
	 */
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	private String getVehicleOptionCode() {
		return vehicleOptionCode;
	}

	private void setVehicleOptionCode(String vehicleOptionCode) {
		this.vehicleOptionCode = vehicleOptionCode;
	}


	/**
	 * @return the modelName
	 */
//	public String getModelName() {
//		return modelName;
//	}
//
//	/**
//	 * @param modelName
//	 *            the modelName to set
//	 */
//	public void setModelName(String modelName) {
//		this.modelName = modelName;
//	}

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
	 * @return the emailTypeCode
	 */
//	public String getEmailTypeCode() {
//		return emailTypeCode;
//	}
//
//	/**
//	 * @param emailTypeCode
//	 *            the emailTypeCode to set
//	 */
//	public void setEmailTypeCode(String emailTypeCode) {
//		this.emailTypeCode = emailTypeCode;
//	}

	/**
	 * @return the headUnitCode
	 */
	public String getHeadUnitCode() {
		return headUnitCode;
	}

	/**
	 * @param headUnitCode
	 *            the headUnitCode to set
	 */
	public void setHeadUnitCode(String headUnitCode) {
		this.headUnitCode = headUnitCode;
	}

	/**
	 * @return the activeIn
	 */
	public String getActiveIn() {
		return activeIn;
	}

	/**
	 * @param activeIn
	 *            the activeIn to set
	 */
	public void setActiveIn(String activeIn) {
		this.activeIn = activeIn;
	}

	/**
	 * @return the lanchDate
	 */
//	public Date getLanchDate() {
//		return lanchDate;
//	}
//
//	/**
//	 * @param lanchDate
//	 *            the lanchDate to set
//	 */
//	public void setLanchDate(Date lanchDate) {
//		this.lanchDate = lanchDate;
//	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OwnerPortalVehicleTelematicsCodeMaster [OwnerPortalVehicleTelematicsCodeMasterPK="
				+ ownerPortalVehicleTelematicsCodeMasterPK
				+ ", modelYear="
				+ modelYear
				+ ", modelName="
			
				+ ", makeCode="
				
				
				+ ", headUnitCode="
				+ headUnitCode
				+ ", activeIn="
				+ activeIn
				+ ", lanchDate="
				
				+ ", createTimestamp="
				+ createTimestamp
				+ ", createUserId="
				+ createUserId
				+ ", updateTimestamp="
				+ updateTimestamp
				+ ", updateUserId="
				+ updateUserId + "]";
	}

	
	public String getTcuTypeCode() {
		return tcuTypeCode;
	}

	public void setTcuTypeCode(String tcuTypeCode) {
		this.tcuTypeCode = tcuTypeCode;
	}
	
	

}
