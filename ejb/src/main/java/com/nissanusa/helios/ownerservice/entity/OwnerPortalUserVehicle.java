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
 * @use Entity implementation class for Entity: OwnerPortalUserVehicle
 */
@Entity
@Table(name = "OWNR_PORTL_USR_VHCL", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "OwnerPortalUserVehicle.findAll", query = "SELECT o FROM OwnerPortalUserVehicle o"),
        //@NamedQuery(name = "OwnerPortalUserVehicle.findByUserProfileId", query = "SELECT o FROM OwnerPortalUserVehicle o WHERE o.ownerPortalUserVehiclePK.userProfileId = :userProfileId"),
		//x055765 - Show only 6 vehicles in garage page - OS 1216-Owners2.0
        @NamedQuery(name = "OwnerPortalUserVehicle.findByUserProfileId", query = "SELECT o FROM OwnerPortalUserVehicle o WHERE o.ownerPortalUserVehiclePK.userProfileId = :userProfileId order by o.createTimestamp DESC"),
        @NamedQuery(name = "OwnerPortalUserVehicle.findVinsByUserProfileId", query = "SELECT o.ownerPortalUserVehiclePK.vin FROM OwnerPortalUserVehicle o WHERE o.ownerPortalUserVehiclePK.userProfileId = :userProfileId order by o.createTimestamp DESC"),
        @NamedQuery(name = "OwnerPortalUserVehicle.findByUserProfileIdAndVin", query = "SELECT o FROM OwnerPortalUserVehicle o WHERE o.ownerPortalUserVehiclePK.userProfileId = :userProfileId AND o.ownerPortalUserVehiclePK.vin = :vin"),
        @NamedQuery(name = "OwnerPortalUserVehicle.findByVin", query = "SELECT o FROM OwnerPortalUserVehicle o WHERE o.ownerPortalUserVehiclePK.vin = :vin"),
        @NamedQuery(name = "OwnerPortalUserVehicle.findByVinAndvehicleRelationCode", query = "SELECT o FROM OwnerPortalUserVehicle o WHERE o.ownerPortalUserVehiclePK.vin = :vin AND o.vehicleRelationCode = :vehicleRelationCode"),
        

})
public class OwnerPortalUserVehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OwnerPortalUserVehiclePK ownerPortalUserVehiclePK;

    @Basic(optional = false)
    @Column(name = "VHCL_NICK_NM", nullable = false, length = 17)
    private String vehicleNickName;

    @Column(name = "VHCL_CRNT_MLG_NB")
    private Integer mileage;

    @Column(name = "VHCL_ANL_ESTMTD_MLG_NB")
    private Integer averageMileage;

    @Basic(optional = false)
    @Column(name = "VHCL_RLTN_CD", nullable = false)
    private char vehicleRelationCode;

    @Column(name = "NTFCTN_SMS_NB")
    private Long notificationSmsNumber;

    @Column(name = "NTFCTN_SMS_CARR_NM")
    private String notificationSmsCarrierName;

    @Column(name = "NTFCTN_EML_ADRS_TX")
    private String notificationEmailId;

    @Column(name = "NTFCTN_OPT_IN_CD")
    private String notificationOptIn;

    @Column(name = "NTFCTN_OPT_IN_UPDT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notificationOptInUpdatedTime;

    @Column(name = "DLR_USR_ID")
    private String dealerUserName;
    
//    @Column(name = "PREFERED_DLR_NB")
//    private String preferredDealer;

    @Column(name = "PREFERED_DLR_NB")
    private String dealerId;
    
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

    /**
     * @return the ownerPortalUserVehiclePK
     */
    public OwnerPortalUserVehiclePK getOwnerPortalUserVehiclePK() {
        return ownerPortalUserVehiclePK;
    }

    /**
     * @param ownerPortalUserVehiclePK
     *            the ownerPortalUserVehiclePK to set
     */
    public void setOwnerPortalUserVehiclePK(
            OwnerPortalUserVehiclePK ownerPortalUserVehiclePK) {
        this.ownerPortalUserVehiclePK = ownerPortalUserVehiclePK;
    }

    /**
     * @return the vehicleNickName
     */
    public String getVehicleNickName() {
        return vehicleNickName;
    }

    /**
     * @param vehicleNickName
     *            the vehicleNickName to set
     */
    public void setVehicleNickName(String vehicleNickName) {
        this.vehicleNickName = vehicleNickName;
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
     * @return the vehicleRelationCode
     */
    public char getVehicleRelationCode() {
        return vehicleRelationCode;
    }

    /**
     * @param vehicleRelationCode
     *            the vehicleRelationCode to set
     */
    public void setVehicleRelationCode(char vehicleRelationCode) {
        this.vehicleRelationCode = vehicleRelationCode;
    }

    /**
     * @return the notificationSmsNumber
     */
    public Long getNotificationSmsNumber() {
        return notificationSmsNumber;
    }

    /**
     * @param notificationSmsNumber
     *            the notificationSmsNumber to set
     */
    public void setNotificationSmsNumber(Long notificationSmsNumber) {
        this.notificationSmsNumber = notificationSmsNumber;
    }

    /**
     * @return the notificationSmsCarrierName
     */
    public String getNotificationSmsCarrierName() {
        return notificationSmsCarrierName;
    }

    /**
     * @param notificationSmsCarrierName
     *            the notificationSmsCarrierName to set
     */
    public void setNotificationSmsCarrierName(String notificationSmsCarrierName) {
        this.notificationSmsCarrierName = notificationSmsCarrierName;
    }

    /**
     * @return the notificationEmailId
     */
    public String getNotificationEmailId() {
        return notificationEmailId;
    }

    /**
     * @param notificationEmailId
     *            the notificationEmailId to set
     */
    public void setNotificationEmailId(String notificationEmailId) {
        this.notificationEmailId = notificationEmailId;
    }

    /**
     * @return the notificationOptIn
     */
    public String getNotificationOptIn() {
        return notificationOptIn;
    }

    /**
     * @param notificationOptIn
     *            the notificationOptIn to set
     */
    public void setNotificationOptIn(String notificationOptIn) {
        this.notificationOptIn = notificationOptIn;
    }

    /**
     * @return the notificationOptInUpdatedTime
     */
    public Date getNotificationOptInUpdatedTime() {
        return notificationOptInUpdatedTime;
    }

    /**
     * @param notificationOptInUpdatedTime
     *            the notificationOptInUpdatedTime to set
     */
    public void setNotificationOptInUpdatedTime(
            Date notificationOptInUpdatedTime) {
        this.notificationOptInUpdatedTime = notificationOptInUpdatedTime;
    }

    /**
     * @return the dealerUserName
     */
    public String getDealerUserName() {
        return dealerUserName;
    }

    /**
     * @param dealerUserName
     *            the dealerUserName to set
     */
    public void setDealerUserName(String dealerUserName) {
        this.dealerUserName = dealerUserName;
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
   	 * @return the preferredDealer
   	 */
//   	public String getPreferredDealer() {
//   		return preferredDealer;
//   	}
//
//   	/**
//   	 * @param preferredDealer the preferredDealer to set
//   	 */
//   	public void setPreferredDealer(String preferredDealer) {
//   		this.preferredDealer = preferredDealer;
//   	}

    /**
   	 * @return the preferredDealer
   	 */
    public String getDealerId() {
		return dealerId;
	}

    /**
//	 * @param preferredDealer the preferredDealer to set
//	 */
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ownerPortalUserVehiclePK != null ? ownerPortalUserVehiclePK
                .hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        Boolean response = true;
        if (!(object instanceof OwnerPortalUserVehicle)) {
            response = false;
        }
        OwnerPortalUserVehicle other = (OwnerPortalUserVehicle) object;
        if ((this.ownerPortalUserVehiclePK == null && other.ownerPortalUserVehiclePK != null)
                || (this.ownerPortalUserVehiclePK != null && !this.ownerPortalUserVehiclePK
                        .equals(other.ownerPortalUserVehiclePK))) {
            response = false;
        }
        return response;
    }

    @Override
    public String toString() {
        return "com.nissanglobal.helios.ownerservice.entity.OwnerPortalUserVehicle[ OwnerPortalUserVehiclePK="
                + ownerPortalUserVehiclePK + " ]";
    }

}
