package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: OwnerPortalUserPhone
 */

@Entity
@Table(name = "OWNR_PORTL_PHN_DISCLMER", catalog = "")
@XmlRootElement
@NamedQuery(name = "OwnerPortalUserPhone.findByuserProfileId", query = "SELECT o FROM OwnerPortalUserPhone o WHERE o.ownerPortalUserPhonePK.userProfileId = :userProfileId")
public class OwnerPortalUserPhone implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OwnerPortalUserPhonePK ownerPortalUserPhonePK;

    @Column(name="MBL_PHN_AUTO_DILR_OPT_IN", nullable=false, length=1)
	private String mblPhnAutoDilrOptIn;

	@Column(name="MBL_PHN_SMS_OPT_IN", nullable=false, length=1)
	private String mblPhnSmsOptIn;
	

	@Column(name="WRK_PHN_AUTO_DILR_OPT_IN", nullable=false, length=1)
	private String wrkPhnAutoDilrOptIn;

	@Column(name="WRK_PHN_SMS_OPT_IN", nullable=false, length=1)
	private String wrkPhnSmsOptIn;
	
    @Column(name = "CRTE_USR_ID", nullable = false, length = 60)
    private String createUserId;

    @Column(name = "CRTE_TS", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;

    @Column(name = "UPDT_USR_ID", nullable = false, length = 60)
    private String updateUserId;

    @Column(name = "UPDT_TS", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTimestamp;

    /**
     * @return the ownerPortalUserPhonePK
     */
    public OwnerPortalUserPhonePK getOwnerPortalUserPhonePK() {
        return ownerPortalUserPhonePK;
    }

    /**
     * @param ownerPortalUserPhonePK
     *            the ownerPortalUserPhonePK to set
     */
    public void setOwnerPortalUserPhonePK(
            OwnerPortalUserPhonePK ownerPortalUserPhonePK) {
        this.ownerPortalUserPhonePK = ownerPortalUserPhonePK;
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

    public String getMblPhnAutoDilrOptIn() {
		return mblPhnAutoDilrOptIn;
	}

    public void setMblPhnAutoDilrOptIn(String mblPhnAutoDilrOptIn) {
		this.mblPhnAutoDilrOptIn = mblPhnAutoDilrOptIn;
	}

    public String getMblPhnSmsOptIn() {
		return mblPhnSmsOptIn;
	}

    public void setMblPhnSmsOptIn(String mblPhnSmsOptIn) {
		this.mblPhnSmsOptIn = mblPhnSmsOptIn;
	}

    public String getWrkPhnAutoDilrOptIn() {
		return wrkPhnAutoDilrOptIn;
	}

    public void setWrkPhnAutoDilrOptIn(String wrkPhnAutoDilrOptIn) {
		this.wrkPhnAutoDilrOptIn = wrkPhnAutoDilrOptIn;
	}

    public String getWrkPhnSmsOptIn() {
		return wrkPhnSmsOptIn;
	}

    public void setWrkPhnSmsOptIn(String wrkPhnSmsOptIn) {
		this.wrkPhnSmsOptIn = wrkPhnSmsOptIn;
	}

}
