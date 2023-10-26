/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;






/**
 * @author x178099
 * @use Entity implementation class for table: MNL_VHCL_LKUP
 */
@Entity
@Table(name = "MNL_VHCL_LOAD", catalog = "")
@NamedQueries({
		@NamedQuery(name = "ManualVehicleLookup.findAll", query = "SELECT m FROM ManualVehicleLookup m"),
		@NamedQuery(name = "ManualVehicleLookup.findByVin", query = "SELECT m FROM ManualVehicleLookup m WHERE m.vin = :vin"), })
public class ManualVehicleLookup implements Serializable {
	private static final long serialVersionUID = 1L;
  
	 
	@Id
	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

//	@Column(name = "MK_CD", nullable = false)
	@Column(name = "VHCL_MK_CD",length = 1)
	private String vehicleMakeCode;

//	@Column(name = "MDL_YR_NB", nullable = false, precision = 5, scale = 1)
	@Column(name = "VHCL_YR_NB", precision = 5, scale = 1)
	private BigDecimal vehicleModelYear;

	@Column(name = "MDL_LN_CD",  length = 99)
	private String modelLineCode;
	
//	@Column(name = "MDL_CD ", length = 5)
	@Column(name = "NMC_MDL_CD", length = 5)
	private String modelCode;

	@Column(name = "TRM_CD ", length = 99)
	private String trimCode;

	
//	@Column(name = "OPTN_CD_LIST_TX", length = 75)
	@Column(name = "OPTN_CD", length = 100)
	private String optionCode;

	@Basic(optional = false)
	@Column(name = "CRTE_TS", nullable = false)
	private Timestamp createTimestamp;

	@Basic(optional = false)
	@Column(name = "CRTE_USR_ID", nullable = false, length = 8)
	private String createUserId;

	@Basic(optional = false)
	@Column(name = "UPDT_TS", nullable = false)
	private Timestamp updateTimestamp;

	@Basic(optional = false)
	@Column(name = "UPDT_USR_ID", nullable = false, length = 8)
	private String updateUserId;
	
//	@Column(name = "INTR_CLR_CD ", length = 1)
	@Column(name = "PRC_CD", length = 1)
	private String interiorColorCode;

	@Column(name="ASGNMT_DT")
	private Date asgnmtDt;

	@Column(name="CRNT_DLR_NB", precision=22)
	private BigDecimal crntDlrNb;	

	 
	@Column(name="DLR_ESTMTD_TM_OF_ALCTN_DT")
	private Date dlrEstmtdTmOfAlctnDt;

	@Column(name="FRGHT_CHRG", precision=22)
	private BigDecimal frghtChrg;

	@Column(name="LKUP_FL_TYP_CD", nullable=false, length=3)
	private String lkupFlTypCd;
	
	
//	@Column(name = "EXTR_CLR_CD ", length = 99)
	@Column(name = "CLR_CD", length = 99)
	private String exteriorColorCode;
	

	
	public Date getAsgnmtDt() {
		return this.asgnmtDt;
	}

	public void setAsgnmtDt(Date asgnmtDt) {
		this.asgnmtDt = asgnmtDt;
	}
	
	
	public BigDecimal getCrntDlrNb() {
		return this.crntDlrNb;
	}

	public void setCrntDlrNb(BigDecimal crntDlrNb) {
		this.crntDlrNb = crntDlrNb;
	}

	public Date getDlrEstmtdTmOfAlctnDt() {
		return this.dlrEstmtdTmOfAlctnDt;
	}

	public void setDlrEstmtdTmOfAlctnDt(Date dlrEstmtdTmOfAlctnDt) {
		this.dlrEstmtdTmOfAlctnDt = dlrEstmtdTmOfAlctnDt;
	}
	
	public BigDecimal getFrghtChrg() {
		return this.frghtChrg;
	}

	public void setFrghtChrg(BigDecimal frghtChrg) {
		this.frghtChrg = frghtChrg;
	}
	
	public String getLkupFlTypCd() {
		return this.lkupFlTypCd;
	}

	public void setLkupFlTypCd(String lkupFlTypCd) {
		this.lkupFlTypCd = lkupFlTypCd;
	}
	public ManualVehicleLookup() {
		// Default Constructor
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
	 * @return the vehicleModelYear
	 */
//	public String getVehicleModelYear() {
//		return vehicleModelYear;
//	}
//
//	/**
//	 * @param vehicleModelYear
//	 *            the vehicleModelYear to set
//	 */
//	public void setVehicleModelYear(String vehicleModelYear) {
//		this.vehicleModelYear = vehicleModelYear;
//	}

	public BigDecimal getVehicleModelYear() {
		return vehicleModelYear;
	}

	/**
	 * @param vehicleModelYear
	 *            the vehicleModelYear to set
	 */
	public void setVehicleModelYear(BigDecimal vehicleModelYear) {
		this.vehicleModelYear = vehicleModelYear;
	}
	
	/**
	 * @return the modelLineCode
	 */
	public String getModelLineCode() {
		return modelLineCode;
	}

	/**
	 * @param modelLineCode
	 *            the modelLineCode to set
	 */
	public void setModelLineCode(String modelLineCode) {
		this.modelLineCode = modelLineCode;
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
	 * @return the trimCode
	 */
	public String getTrimCode() {
		return trimCode;
	}

	/**
	 * @param trimCode
	 *            the trimCode to set
	 */
	public void setTrimCode(String trimCode) {
		this.trimCode = trimCode;
	}

	/**
	 * @return the exteriorColorCode
	 */
	public String getExteriorColorCode() {
		return exteriorColorCode;
	}

	/**
	 * @param exteriorColorCode
	 *            the exteriorColorCode to set
	 */
	public void setExteriorColorCode(String exteriorColorCode) {
		this.exteriorColorCode = exteriorColorCode;
	}

	/**
	 * @return the interiorColorCode
	 */
	public String getInteriorColorCode() {
		return interiorColorCode;
	}

	/**
	 * @param interiorColorCode
	 *            the interiorColorCode to set
	 */
	public void setInteriorColorCode(String interiorColorCode) {
		this.interiorColorCode = interiorColorCode;
	}

	/**
	 * @return the optionCode
	 */
	public String getOptionCode() {
		return optionCode;
	}

	/**
	 * @param optionCode
	 *            the optionCode to set
	 */
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	/**
	 * @return the createTimestamp
	 */
	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	/**
	 * @param createTimestamp
	 *            the createTimestamp to set
	 */
	public void setCreateTimestamp(Timestamp createTimestamp) {
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
	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	/**
	 * @param updateTimestamp
	 *            the updateTimestamp to set
	 */
	public void setUpdateTimestamp(Timestamp updateTimestamp) {
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
