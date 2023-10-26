 package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: FAQHeaderLookup
 *
 */
@Entity
@Table(name = "MNS_NNACOP.FAQ_HDNG_LKUP", catalog = "")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "FAQHeaderLookup.findByHeadingCode", query = "SELECT o FROM FAQHeaderLookup o WHERE lower(o.faqHeadingCode) = :faqHeadingCode AND o.faqHeadingStatus = :faqHeadingStatus")
})

public class FAQHeaderLookup implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "FAQ_HDNG_CD", nullable = false, length = 20)
	private String faqHeadingCode;
	
	@Basic(optional = false)
	@Column(name = "FAQ_HDNG_NM", nullable = true, length = 150)
	private String faqHeadingName;
	
	@Basic(optional = false)
	@Column(name = "FAQ_HDNG_DS", nullable = true, length = 2000)
	private String faqHeadingDesc;
	
	@Basic(optional = false)
	@Column(name = "APP_NM", nullable = true, length = 55)
	private String appName;
	
	@Basic(optional = false)
	@Column(name = "RCRD_DSPLY_IN", nullable = false)
	private Integer faqHeadingStatus;
	
	@Basic(optional = false)
	@Column(name = "CRTE_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@Basic(optional = false)
	@Column(name = "CRTE_USR_ID", nullable = false, length = 50)
	private String createUserId;

	@Basic(optional = false)
	@Column(name = "UPDT_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;

	@Basic(optional = false)
	@Column(name = "UPDT_USR_ID", nullable = false, length = 50)
	private String updateUserId;

	public FAQHeaderLookup() {
		super();
	}

	public String getFaqHeadingCode() {
		return faqHeadingCode;
	}

	public void setFaqHeadingCode(String faqHeadingCode) {
		this.faqHeadingCode = faqHeadingCode;
	}

	public String getFaqHeadingName() {
		return faqHeadingName;
	}

	public void setFaqHeadingName(String faqHeadingName) {
		this.faqHeadingName = faqHeadingName;
	}

	public Integer getFaqHeadingStatus() {
		return faqHeadingStatus;
	}

	public void setFaqHeadingStatus(Integer faqHeadingStatus) {
		this.faqHeadingStatus = faqHeadingStatus;
	}

	public String getFaqHeadingDesc() {
		return faqHeadingDesc;
	}

	public void setFaqHeadingDesc(String faqHeadingDesc) {
		this.faqHeadingDesc = faqHeadingDesc;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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
