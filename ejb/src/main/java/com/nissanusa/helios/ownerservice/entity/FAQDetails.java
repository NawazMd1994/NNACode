package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: FAQDetails
 *
 */
@Entity
@Table(name = "MNS_NNACOP.FAQ_DTL", catalog = "")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "FAQDetails.findByBrandAndAppName", query = "SELECT o FROM FAQDetails o WHERE lower(o.brandName) = :brandName AND lower(o.appName) = :appName AND o.faqStatus = :faqStatus")
})

public class FAQDetails implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "FAQ_CD", nullable = false, length = 10)
	private Integer faqCode;
	
	@Basic(optional = false)
	@Column(name = "FAQ_HDNG_CD", nullable = false, length = 20)
	private String faqHeadingCode;
	
	@Basic(optional = false)
	@Column(name = "FAQ_SUBHDNG_CD", nullable = false, length = 20)
	private String faqSubHeadingCode;
	
	@Basic(optional = false)
	@Column(name = "BRND_NM", nullable = true, length = 150)
	private String brandName;
	
	@Basic(optional = false)
	@Column(name = "APP_NM", nullable = true, length = 55)
	private String appName;
	
	@Basic(optional = false)
	@Column(name = "FAQ_TX", nullable = true, length = 2000)
	private String faqQuestion;
	
	@Lob
	@Basic(optional = false)
	@Column(name = "FAQ_ANSWR_TX", nullable = true)
	private String faqAnswer;
	
	@Basic(optional = false)
	@Column(name = "FAQ_CMNT_TX", nullable = true, length = 4000)
	private String faqComment;
	
	@Basic(optional = false)
	@Column(name = "FAQ_DO_NB", nullable = true, length = 6)
	private Integer faqPosition;
	
	@Basic(optional = false)
	@Column(name = "FAQ_ATHR_FULL_NM", nullable = true, length = 75)
	private String faqAuthor;
	
	@Basic(optional = false)
	@Column(name = "RCRD_DSPLY_IN", nullable = false, length = 38)
	private Integer faqStatus;
	
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

	public FAQDetails() {
		super();
	}

	public Integer getFaqCode() {
		return faqCode;
	}

	public void setFaqCode(Integer faqCode) {
		this.faqCode = faqCode;
	}

	public String getFaqHeadingCode() {
		return faqHeadingCode;
	}

	public void setFaqHeadingCode(String faqHeadingCode) {
		this.faqHeadingCode = faqHeadingCode;
	}

	public String getFaqSubHeadingCode() {
		return faqSubHeadingCode;
	}

	public void setFaqSubHeadingCode(String faqSubHeadingCode) {
		this.faqSubHeadingCode = faqSubHeadingCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getFaqQuestion() {
		return faqQuestion;
	}

	public void setFaqQuestion(String faqQuestion) {
		this.faqQuestion = faqQuestion;
	}

	public String getFaqAnswer() {
		return faqAnswer;
	}

	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}

	public String getFaqComment() {
		return faqComment;
	}

	public void setFaqComment(String faqComment) {
		this.faqComment = faqComment;
	}

	public Integer getFaqPosition() {
		return faqPosition;
	}

	public void setFaqPosition(Integer faqPosition) {
		this.faqPosition = faqPosition;
	}

	public String getFaqAuthor() {
		return faqAuthor;
	}

	public void setFaqAuthor(String faqAuthor) {
		this.faqAuthor = faqAuthor;
	}

	public Integer getFaqStatus() {
		return faqStatus;
	}

	public void setFaqStatus(Integer faqStatus) {
		this.faqStatus = faqStatus;
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
