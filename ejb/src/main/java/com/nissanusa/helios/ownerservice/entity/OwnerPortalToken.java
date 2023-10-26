/**
 * 
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rs101547 Token table to store the SSO token for NissanConnect
 *         Navigation
 *
 */

@Entity
@Table(name = "OWNR_PORTL_TKN", catalog = "")
public class OwnerPortalToken implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8034138976456092664L;
    @Id
    @Basic(optional = false)
    @Column(name = "OWNR_TKN_ID", nullable = false)
    String ownerToken;

    @Basic(optional = false)
    @Column(name = "USR_PRFL_ID", nullable = false)
    String userprofileId;

    @Basic(optional = false)
    @Column(name = "USR_SLCTD_VIN", nullable = false)
    String selectedVin;

    @Basic(optional = false)
    @Column(name = "TKN_STS_CD", nullable = false)
    String tokenStatusCode;

    @Basic(optional = false)
    @Column(name = "LMS_ID", nullable = false)
    String lmsId;

    @Basic(optional = false)
    @Column(name = "ENRLMT_TYP_CD", nullable = false)
    String enrollmentTypeCode;

    @Basic(optional = false)
    @Column(name = "CRTE_TS", nullable = false)
    Timestamp createdate;

    @Basic(optional = false)
    @Column(name = "CRTE_USR_ID", nullable = false)
    String createUserId;

    /**
     * @return the ownerToken
     */
    public String getOwnerToken() {
        return ownerToken;
    }

    /**
     * @param ownerToken
     *            the ownerToken to set
     */
    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }

    /**
     * @return the userprofileId
     */
    public String getUserprofileId() {
        return userprofileId;
    }

    /**
     * @param userprofileId
     *            the userprofileId to set
     */
    public void setUserprofileId(String userprofileId) {
        this.userprofileId = userprofileId;
    }

    /**
     * @return the selectedVin
     */
    public String getSelectedVin() {
        return selectedVin;
    }

    /**
     * @param selectedVin
     *            the selectedVin to set
     */
    public void setSelectedVin(String selectedVin) {
        this.selectedVin = selectedVin;
    }

    /**
     * @return the tokenStatusCode
     */
    public String getTokenStatusCode() {
        return tokenStatusCode;
    }

    /**
     * @param tokenStatusCode
     *            the tokenStatusCode to set
     */
    public void setTokenStatusCode(String tokenStatusCode) {
        this.tokenStatusCode = tokenStatusCode;
    }

    /**
     * @return the lmsId
     */
    public String getLmsId() {
        return lmsId;
    }

    /**
     * @param lmsId
     *            the lmsId to set
     */
    public void setLmsId(String lmsId) {
        this.lmsId = lmsId;
    }

    /**
     * @return the enrollmentTypeCode
     */
    public String getEnrollmentTypeCode() {
        return enrollmentTypeCode;
    }

    /**
     * @param enrollmentTypeCode
     *            the enrollmentTypeCode to set
     */
    public void setEnrollmentTypeCode(String enrollmentTypeCode) {
        this.enrollmentTypeCode = enrollmentTypeCode;
    }

    /**
     * @return the createdate
     */
    public Timestamp getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     *            the createdate to set
     */
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
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
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
