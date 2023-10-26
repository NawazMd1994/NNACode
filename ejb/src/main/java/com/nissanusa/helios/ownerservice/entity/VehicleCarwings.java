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

/**
 * 
 * @author x178099
 * @use Entity implementation class for Entity: VehicleCarwings
 *
 */
@Entity
@Table(name = "VHCL_CRWNGS", catalog = "")
@NamedQueries({ @NamedQuery(name = "VehicleCarwings.findByVin", query = "select o from VehicleCarwings o where o.vin = :vin")

})
public class VehicleCarwings implements Serializable {

    private static final long serialVersionUID = 2711229407594428308L;

    @Id
    @Column(name = "VIN", nullable = false)
    private String vin;

    @Column(name = "OWNR_USR_ID")
    private String ownerCarwingsUserId;

    @Column(name = "DCM_ID")
    private String dataCenterId;

    @Column(name = "PSWRD_TX")
    private String password;

    @Column(name = "ATHRZN_TKN_TX")
    private String authToken;

    @Column(name = "ATHRZN_TKN_LST_MDFYD_DT")
    private Date authTokenLastModifiedDate;

    @Column(name = "CRWNGS_AGRMT_STS_CD")
    private String carwingsAgreementStatusCode;

    @Column(name = "CRWNGS_AGRMT_STS_LST_MDFYD_DT")
    private Date carwingsAgreementStatusLastModifiedDate;

    @Column(name = "EXTRNL_VHCL_NCKNM_NM")
    private String externalVehicleNickname;

    @Column(name = "CHARGING_STS_CD")
    private String chargingStatusCode;

    @Column(name = "BTRY_LVL_NB")
    private Long batteryLevel;

    @Column(name = "HVAC_IN")
    private String acStatus;

    @Column(name = "CHRG_TM_TX")
    private String chargeTime;

    @Column(name = "CHRG_TM_220_KV_TX")
    private String chargeTime220KV;

    @Column(name = "RNG_HVAC_ON_NB")
    private Long distanceWhenAcOn;

    @Column(name = "RNG_HVAC_OFF_NB")
    private Long distanceWhenAcOff;

    @Column(name = "CHRGR_CNCTD_CD")
    private String chargerConnectedCode;

    @Column(name = "CRWNGS_INFRMN_LST_MDFYD_DT")
    private Date carwingsInfoLastModifiedCode;

    @Column(name = "HVAC_EXCT_TS")
    private Date acExecuteTime;

   /* @Column(name = "HVAC_CNTNU_TS")
    private Date acContinuedTime;*/

    @Column(name = "HVAC_LST_MDFYD_DT")
    private Date acLastModifiedDate;

    @Column(name = "CHRG_RQST_DT")
    private Date chargeRequestDate;

    @Column(name = "CHRG_STS_RQST_DT")
    private Date chargeStatusRequestDate;

    @Column(name = "HVAC_STS_RQST_DT")
    private Date acStatusRequestDate;

    @Column(name = "LTST_BTRY_HTR_STRT_TS")
    private Date lastBatteryHeaterStartTime;

    @Column(name = "LTST_BTRY_HTR_STOP_TS")
    private Date lastBatteryHeaterStopTime;

    @Column(name = "RMNG_CHRG_TM_220_KV_CHRGR_TX")
    private String remainingChargeTime220KVCharger;

    @Basic(optional = false)
    @Column(name = "CRTE_TS", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimeStamp;

    @Column(name = "CRTE_USR_ID", nullable = false)
    private String createUserId;

    @Basic(optional = false)
    @Column(name = "UPDT_TS", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTimeStamp;

    @Column(name = "UPDT_USR_ID", nullable = false)
    private String updateUsrId;

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
     * @return the ownerCarwingsUserId
     */
    public String getOwnerCarwingsUserId() {
        return ownerCarwingsUserId;
    }

    /**
     * @param ownerCarwingsUserId
     *            the ownerCarwingsUserId to set
     */
    public void setOwnerCarwingsUserId(String ownerCarwingsUserId) {
        this.ownerCarwingsUserId = ownerCarwingsUserId;
    }

    /**
     * @return the dataCenterId
     */
    public String getDataCenterId() {
        return dataCenterId;
    }

    /**
     * @param dataCenterId
     *            the dataCenterId to set
     */
    public void setDataCenterId(String dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken
     *            the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the authTokenLastModifiedDate
     */
    public Date getAuthTokenLastModifiedDate() {
        return authTokenLastModifiedDate;
    }

    /**
     * @param authTokenLastModifiedDate
     *            the authTokenLastModifiedDate to set
     */
    public void setAuthTokenLastModifiedDate(Date authTokenLastModifiedDate) {
        this.authTokenLastModifiedDate = authTokenLastModifiedDate;
    }

    /**
     * @return the carwingsAgreementStatusCode
     */
    public String getCarwingsAgreementStatusCode() {
        return carwingsAgreementStatusCode;
    }

    /**
     * @param carwingsAgreementStatusCode
     *            the carwingsAgreementStatusCode to set
     */
    public void setCarwingsAgreementStatusCode(
            String carwingsAgreementStatusCode) {
        this.carwingsAgreementStatusCode = carwingsAgreementStatusCode;
    }

    /**
     * @return the carwingsAgreementStatusLastModifiedDate
     */
    public Date getCarwingsAgreementStatusLastModifiedDate() {
        return carwingsAgreementStatusLastModifiedDate;
    }

    /**
     * @param carwingsAgreementStatusLastModifiedDate
     *            the carwingsAgreementStatusLastModifiedDate to set
     */
    public void setCarwingsAgreementStatusLastModifiedDate(
            Date carwingsAgreementStatusLastModifiedDate) {
        this.carwingsAgreementStatusLastModifiedDate = carwingsAgreementStatusLastModifiedDate;
    }

    /**
     * @return the externalVehicleNickname
     */
    public String getExternalVehicleNickname() {
        return externalVehicleNickname;
    }

    /**
     * @param externalVehicleNickname
     *            the externalVehicleNickname to set
     */
    public void setExternalVehicleNickname(String externalVehicleNickname) {
        this.externalVehicleNickname = externalVehicleNickname;
    }

    /**
     * @return the chargingStatusCode
     */
    public String getChargingStatusCode() {
        return chargingStatusCode;
    }

    /**
     * @param chargingStatusCode
     *            the chargingStatusCode to set
     */
    public void setChargingStatusCode(String chargingStatusCode) {
        this.chargingStatusCode = chargingStatusCode;
    }

    /**
     * @return the batteryLevel
     */
    public Long getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * @param batteryLevel
     *            the batteryLevel to set
     */
    public void setBatteryLevel(Long batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * @return the acStatus
     */
    public String getAcStatus() {
        return acStatus;
    }

    /**
     * @param acStatus
     *            the acStatus to set
     */
    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    /**
     * @return the chargeTime
     */
    public String getChargeTime() {
        return chargeTime;
    }

    /**
     * @param chargeTime
     *            the chargeTime to set
     */
    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    /**
     * @return the chargeTime220KV
     */
    public String getChargeTime220KV() {
        return chargeTime220KV;
    }

    /**
     * @param chargeTime220KV
     *            the chargeTime220KV to set
     */
    public void setChargeTime220KV(String chargeTime220KV) {
        this.chargeTime220KV = chargeTime220KV;
    }

    /**
     * @return the distanceWhenAcOn
     */
    public Long getDistanceWhenAcOn() {
        return distanceWhenAcOn;
    }

    /**
     * @param distanceWhenAcOn
     *            the distanceWhenAcOn to set
     */
    public void setDistanceWhenAcOn(Long distanceWhenAcOn) {
        this.distanceWhenAcOn = distanceWhenAcOn;
    }

    /**
     * @return the distanceWhenAcOff
     */
    public Long getDistanceWhenAcOff() {
        return distanceWhenAcOff;
    }

    /**
     * @param distanceWhenAcOff
     *            the distanceWhenAcOff to set
     */
    public void setDistanceWhenAcOff(Long distanceWhenAcOff) {
        this.distanceWhenAcOff = distanceWhenAcOff;
    }

    /**
     * @return the chargerConnectedCode
     */
    public String getChargerConnectedCode() {
        return chargerConnectedCode;
    }

    /**
     * @param chargerConnectedCode
     *            the chargerConnectedCode to set
     */
    public void setChargerConnectedCode(String chargerConnectedCode) {
        this.chargerConnectedCode = chargerConnectedCode;
    }

    /**
     * @return the carwingsInfoLastModifiedCode
     */
    public Date getCarwingsInfoLastModifiedCode() {
        return carwingsInfoLastModifiedCode;
    }

    /**
     * @param carwingsInfoLastModifiedCode
     *            the carwingsInfoLastModifiedCode to set
     */
    public void setCarwingsInfoLastModifiedCode(
            Date carwingsInfoLastModifiedCode) {
        this.carwingsInfoLastModifiedCode = carwingsInfoLastModifiedCode;
    }

    /**
     * @return the acExecuteTime
     */
    public Date getAcExecuteTime() {
        return acExecuteTime;
    }

    /**
     * @param acExecuteTime
     *            the acExecuteTime to set
     */
    public void setAcExecuteTime(Date acExecuteTime) {
        this.acExecuteTime = acExecuteTime;
    }

    /**
     * @return the acContinuedTime
     */
    /*  public Date getAcContinuedTime() {
       return acContinuedTime;
    }

    *//**
     * @param acContinuedTime
     *            the acContinuedTime to set
     *//*
    public void setAcContinuedTime(Date acContinuedTime) {
        this.acContinuedTime = acContinuedTime;
    }*/

    /**
     * @return the acLastModifiedDate
     */
    public Date getAcLastModifiedDate() {
        return acLastModifiedDate;
    }

    /**
     * @param acLastModifiedDate
     *            the acLastModifiedDate to set
     */
    public void setAcLastModifiedDate(Date acLastModifiedDate) {
        this.acLastModifiedDate = acLastModifiedDate;
    }

    /**
     * @return the chargeRequestDate
     */
    public Date getChargeRequestDate() {
        return chargeRequestDate;
    }

    /**
     * @param chargeRequestDate
     *            the chargeRequestDate to set
     */
    public void setChargeRequestDate(Date chargeRequestDate) {
        this.chargeRequestDate = chargeRequestDate;
    }

    /**
     * @return the chargeStatusRequestDate
     */
    public Date getChargeStatusRequestDate() {
        return chargeStatusRequestDate;
    }

    /**
     * @param chargeStatusRequestDate
     *            the chargeStatusRequestDate to set
     */
    public void setChargeStatusRequestDate(Date chargeStatusRequestDate) {
        this.chargeStatusRequestDate = chargeStatusRequestDate;
    }

    /**
     * @return the acStatusRequestDate
     */
    public Date getAcStatusRequestDate() {
        return acStatusRequestDate;
    }

    /**
     * @param acStatusRequestDate
     *            the acStatusRequestDate to set
     */
    public void setAcStatusRequestDate(Date acStatusRequestDate) {
        this.acStatusRequestDate = acStatusRequestDate;
    }

    /**
     * @return the lastBatteryHeaterStartTime
     */
    public Date getLastBatteryHeaterStartTime() {
        return lastBatteryHeaterStartTime;
    }

    /**
     * @param lastBatteryHeaterStartTime
     *            the lastBatteryHeaterStartTime to set
     */
    public void setLastBatteryHeaterStartTime(Date lastBatteryHeaterStartTime) {
        this.lastBatteryHeaterStartTime = lastBatteryHeaterStartTime;
    }

    /**
     * @return the lastBatteryHeaterStopTime
     */
    public Date getLastBatteryHeaterStopTime() {
        return lastBatteryHeaterStopTime;
    }

    /**
     * @param lastBatteryHeaterStopTime
     *            the lastBatteryHeaterStopTime to set
     */
    public void setLastBatteryHeaterStopTime(Date lastBatteryHeaterStopTime) {
        this.lastBatteryHeaterStopTime = lastBatteryHeaterStopTime;
    }

    /**
     * @return the remainingChargeTime220KVCharger
     */
    public String getRemainingChargeTime220KVCharger() {
        return remainingChargeTime220KVCharger;
    }

    /**
     * @param remainingChargeTime220KVCharger
     *            the remainingChargeTime220KVCharger to set
     */
    public void setRemainingChargeTime220KVCharger(
            String remainingChargeTime220KVCharger) {
        this.remainingChargeTime220KVCharger = remainingChargeTime220KVCharger;
    }

    /**
     * @return the createTimeStamp
     */
    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    /**
     * @param createTimeStamp
     *            the createTimeStamp to set
     */
    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
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
     * @return the updateTimeStamp
     */
    public Date getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    /**
     * @param updateTimeStamp
     *            the updateTimeStamp to set
     */
    public void setUpdateTimeStamp(Date updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    /**
     * @return the updateUsrId
     */
    public String getUpdateUsrId() {
        return updateUsrId;
    }

    /**
     * @param updateUsrId
     *            the updateUsrId to set
     */
    public void setUpdateUsrId(String updateUsrId) {
        this.updateUsrId = updateUsrId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VehicleCarwings [vin=" + vin + ", ownerCarwingsUserId="
                + ownerCarwingsUserId + ", dataCenterId=" + dataCenterId
                + ", password=" + password + ", authToken=" + authToken
                + ", authTokenLastModifiedDate=" + authTokenLastModifiedDate
                + ", carwingsAgreementStatusCode="
                + carwingsAgreementStatusCode
                + ", carwingsAgreementStatusLastModifiedDate="
                + carwingsAgreementStatusLastModifiedDate
                + ", externalVehicleNickname=" + externalVehicleNickname
                + ", chargingStatusCode=" + chargingStatusCode
                + ", batteryLevel=" + batteryLevel + ", acStatus=" + acStatus
                + ", chargeTime=" + chargeTime + ", chargeTime220KV="
                + chargeTime220KV + ", distanceWhenAcOn=" + distanceWhenAcOn
                + ", distanceWhenAcOff=" + distanceWhenAcOff
                + ", chargerConnectedCode=" + chargerConnectedCode
                + ", carwingsInfoLastModifiedCode="
                + carwingsInfoLastModifiedCode + ", acExecuteTime="
                + acExecuteTime + 
                //", acContinuedTime=" + acContinuedTime
                ", acLastModifiedDate=" + acLastModifiedDate
                + ", chargeRequestDate=" + chargeRequestDate
                + ", chargeStatusRequestDate=" + chargeStatusRequestDate
                + ", acStatusRequestDate=" + acStatusRequestDate
                + ", lastBatteryHeaterStartTime=" + lastBatteryHeaterStartTime
                + ", lastBatteryHeaterStopTime=" + lastBatteryHeaterStopTime
                + ", remainingChargeTime220KVCharger="
                + remainingChargeTime220KVCharger + ", createTimeStamp="
                + createTimeStamp + ", createUserId=" + createUserId
                + ", updateTimeStamp=" + updateTimeStamp + ", updateUsrId="
                + updateUsrId + "]";
    }

}
