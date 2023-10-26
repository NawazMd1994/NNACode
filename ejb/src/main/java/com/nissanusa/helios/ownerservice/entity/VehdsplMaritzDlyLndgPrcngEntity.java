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

@Entity
@Table(name = "VEHDSP_MARITZ_DLY_LNDG_PRCSNG", catalog = "")
@NamedQueries({
		@NamedQuery(name = "VehdsplMaritzDlyLndgPrcngEntity.findAll", query = "SELECT o FROM VehdsplMaritzDlyLndgPrcngEntity o"),
		@NamedQuery(name = "VehdsplMaritzDlyLndgPrcngEntity.findByVin", query = "SELECT o FROM VehdsplMaritzDlyLndgPrcngEntity o WHERE o.vin = :vin"), })
public class VehdsplMaritzDlyLndgPrcngEntity implements Serializable {
    private static final long serialVersionUID = 1L;

  
    @Id
    @Column(name = "VIN_ID", nullable = false, length = 17)
    private String vin;

    @Column(name = "PRSN_HSH_ID")
    private String persionId ;

	@Column(name = "USR_PRFL_ID", nullable = false, length = 20)
	private String userProfileId;
    
    
    @Column(name = "DSTBTN_CHNL_DS")
    private String brand;

    @Column(name = "DSPSL_DT")
    private Date diposalDate;
    
    @Column(name = "MARITZ_DLT_TS")
    private Date maritzDelDate;
    
    @Column(name = "MARITZ_DLT_STS_CD")
    private String maritzStatus;

    @Column(name = "SXM_DLT_STS_CD")
    private String sxmStatus;

    @Column(name = "GDC_DLT_STS_CD")
    private String gdcStatus;
    
    @Column(name = "NSN_DLT_STS_CD")
    private String nissanStatus;
    
    @Column(name = "NSN_DLT_TS")
    private Date nissanDelDate;
    
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

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getPersionId() {
		return persionId;
	}

	public void setPersionId(String persionId) {
		this.persionId = persionId;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Date getDiposalDate() {
		return diposalDate;
	}

	public void setDiposalDate(Date diposalDate) {
		this.diposalDate = diposalDate;
	}

	public Date getMaritzDelDate() {
		return maritzDelDate;
	}

	public void setMaritzDelDate(Date maritzDelDate) {
		this.maritzDelDate = maritzDelDate;
	}

	public String getMaritzStatus() {
		return maritzStatus;
	}

	public void setMaritzStatus(String maritzStatus) {
		this.maritzStatus = maritzStatus;
	}

	public String getSxmStatus() {
		return sxmStatus;
	}

	public void setSxmStatus(String sxmStatus) {
		this.sxmStatus = sxmStatus;
	}

	public String getGdcStatus() {
		return gdcStatus;
	}

	public void setGdcStatus(String gdcStatus) {
		this.gdcStatus = gdcStatus;
	}

	public String getNissanStatus() {
		return nissanStatus;
	}

	public void setNissanStatus(String nissanStatus) {
		this.nissanStatus = nissanStatus;
	}

	public Date getNissanDelDate() {
		return nissanDelDate;
	}

	public void setNissanDelDate(Date nissanDelDate) {
		this.nissanDelDate = nissanDelDate;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	} 
    
    

}
