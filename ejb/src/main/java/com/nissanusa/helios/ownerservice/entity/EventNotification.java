package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVNT_NTFCTN", catalog = "")
/**
 * 
 * @author x796314
 * @use  holds the  event notification table info
 *
 */
public class EventNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "EVNT_NTFCTN_KY", nullable = false)
    private BigInteger eventNotificationKey;

    @Basic(optional = false)
    @Column(name = "VIN", nullable = false, length = 17)
    private String vin;

    @Basic(optional = false)
    @Column(name = "EVNT_NTFCTN_CD", nullable = false, length = 50)
    private String eventNotificationCode;

    @Basic(optional = false)
    @Column(name = "EVNT_NTFCTN_TRGT_CD", nullable = false, length = 99)
    private String eventNotificationTargetCode;

    @Basic(optional = false)
    @Column(name = "USR_PRFL_ID", nullable = false, length = 20)
    private String userProfileId;

    @Basic(optional = false)
    @Column(name = "CRTE_TS", nullable = false, length = 6)
    private Date createTimestamp;

    @Basic(optional = false)
    @Column(name = "CRTE_USR_ID", nullable = false, length = 60)
    private String createUserId;

    @Basic(optional = false)
    @Column(name = "UPDT_TS", nullable = false, length = 6)
    private Date updateTimestamp;

    @Basic(optional = false)
    @Column(name = "UPDT_USR_ID", nullable = false, length = 60)
    private String updateUserId;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "EventNotification [eventNotificationKey="
                + eventNotificationKey + ", eventNotificationCode="
                + eventNotificationCode + ", eventNotificationTargetCode="
                + eventNotificationTargetCode + ", userProfileId="
                + userProfileId + ", createTimestamp=" + createTimestamp
                + ", createUserId=" + createUserId + ", updateTimestamp="
                + updateTimestamp + ", updateUserId=" + updateUserId + ", vin="
                + vin + "]";
    }

    /**
     * @return the eventNotificationKey
     */
    public BigInteger getEventNotificationKey() {
        return eventNotificationKey;
    }

    /**
     * @param eventNotificationKey
     *            the eventNotificationKey to set
     */
    public void setEventNotificationKey(BigInteger eventNotificationKey) {
        this.eventNotificationKey = eventNotificationKey;
    }

    /**
     * @return the eventNotificationCode
     */
    public String getEventNotificationCode() {
        return eventNotificationCode;
    }

    /**
     * @param eventNotificationCode
     *            the eventNotificationCode to set
     */
    public void setEventNotificationCode(String eventNotificationCode) {
        this.eventNotificationCode = eventNotificationCode;
    }

    /**
     * @return the eventNotificationTargetCode
     */
    public String getEventNotificationTargetCode() {
        return eventNotificationTargetCode;
    }

    /**
     * @param eventNotificationTargetCode
     *            the eventNotificationTargetCode to set
     */
    public void setEventNotificationTargetCode(
            String eventNotificationTargetCode) {
        this.eventNotificationTargetCode = eventNotificationTargetCode;
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
