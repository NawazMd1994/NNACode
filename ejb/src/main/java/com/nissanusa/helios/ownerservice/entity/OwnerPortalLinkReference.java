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

@Entity
@Table(name = "OWNR_PORTL_LNK_RFRNC", catalog = "")
/**
 * 
 * @author x796314
 * @use  holds the OwnerPortalLinkReference  table info
 *
 */
@NamedQueries({
@NamedQuery(name = "OwnerPortalLinkReference.findByLinkReferenceHashId", query = "SELECT o FROM OwnerPortalLinkReference o WHERE o.linkReferenceHashId = :linkReferenceHashId and o.activeIn=:activeIn"),
@NamedQuery(name = "OwnerPortalLinkReference.findByLinkReferenceHashIdandEmailId", query = "SELECT o FROM OwnerPortalLinkReference o WHERE o.linkReferenceHashId = :linkReferenceHashId AND o.userProfileId = :userProfileId AND o.activeIn=:activeIn" ) })

public class OwnerPortalLinkReference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "LNK_RFRNC_HSH_CD", nullable = false)
    private String linkReferenceHashId;

    @Basic(optional = false)
    @Column(name = "USR_PRFL_ID", nullable = false, length = 20)
    private String userProfileId;

    @Basic(optional = false)
    @Column(name = "ACTV_IN ", nullable = false)
    private String activeIn;

    @Column(name = "FNCTN_TYP_CD", nullable = false)
    private String functionTypeId;

    @Basic(optional = false)
    @Column(name = "CRTE_TS", nullable = false, length = 6)
    private Date createTimestamp;

    @Basic(optional = false)
    @Column(name = "CRTE_USR_ID", nullable = false, length = 60)
    private String createUserId;

    /**
     * @return the linkReferenceHashId
     */
    public String getLinkReferenceHashId() {
        return linkReferenceHashId;
    }

    /**
     * @param linkReferenceHashId
     *            the linkReferenceHashId to set
     */
    public void setLinkReferenceHashId(String linkReferenceHashId) {
        this.linkReferenceHashId = linkReferenceHashId;
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
     * @return the functionTypeId
     */
    public String getFunctionTypeId() {
        return functionTypeId;
    }

    /**
     * @param functionTypeId
     *            the functionTypeId to set
     */
    public void setFunctionTypeId(String functionTypeId) {
        this.functionTypeId = functionTypeId;
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

}
