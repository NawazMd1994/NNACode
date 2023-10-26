package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EVNT_NTFCTN database table.
 * 
 */


@Entity
@Table(name="EVNT_NTFCTN")
@NamedQuery(name="EventNotificationFunction.findAll", query="SELECT e FROM EventNotificationFunction e")
public class EventNotificationFunction  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EVNT_NTFCTN_KY", unique=true, nullable=false, precision=22)
	private long evntNtfctnKy;

	@Column(name="CRTE_TS", nullable=false)
	private Timestamp crteTs;

	@Column(name="CRTE_USR_ID", nullable=false, length=8)
	private String crteUsrId;

	@Column(name="EVNT_NTFCTN_CD", nullable=false, length=50)
	private String evntNtfctnCd;

	@Column(name="EVNT_NTFCTN_TRGT_CD", nullable=false, length=99)
	private String evntNtfctnTrgtCd;

	@Column(name="UPDT_TS", nullable=false)
	private Timestamp updtTs;

	@Column(name="UPDT_USR_ID", nullable=false, length=8)
	private String updtUsrId;

	//bi-directional many-to-one association to OwnrPortlUsrVhcl
//	@ManyToOne
//	@JoinColumns({
//		@JoinColumn(name="USR_PRFL_ID", referencedColumnName="USR_PRFL_ID"),
//		@JoinColumn(name="VIN", referencedColumnName="VIN")
//		})
//	private OwnrPortlUsrVhcl ownrPortlUsrVhcl;

	public EventNotificationFunction() {
	}

	public long getEvntNtfctnKy() {
		return this.evntNtfctnKy;
	}

	public void setEvntNtfctnKy(long evntNtfctnKy) {
		this.evntNtfctnKy = evntNtfctnKy;
	}

	public Timestamp getCrteTs() {
		return this.crteTs;
	}

	public void setCrteTs(Timestamp crteTs) {
		this.crteTs = crteTs;
	}

	public String getCrteUsrId() {
		return this.crteUsrId;
	}

	public void setCrteUsrId(String crteUsrId) {
		this.crteUsrId = crteUsrId;
	}

	public String getEvntNtfctnCd() {
		return this.evntNtfctnCd;
	}

	public void setEvntNtfctnCd(String evntNtfctnCd) {
		this.evntNtfctnCd = evntNtfctnCd;
	}

	public String getEvntNtfctnTrgtCd() {
		return this.evntNtfctnTrgtCd;
	}

	public void setEvntNtfctnTrgtCd(String evntNtfctnTrgtCd) {
		this.evntNtfctnTrgtCd = evntNtfctnTrgtCd;
	}

	public Timestamp getUpdtTs() {
		return this.updtTs;
	}

	public void setUpdtTs(Timestamp updtTs) {
		this.updtTs = updtTs;
	}

	public String getUpdtUsrId() {
		return this.updtUsrId;
	}

	public void setUpdtUsrId(String updtUsrId) {
		this.updtUsrId = updtUsrId;
	}

//	public OwnrPortlUsrVhcl getOwnrPortlUsrVhcl() {
//		return this.ownrPortlUsrVhcl;
//	}
//
//	public void setOwnrPortlUsrVhcl(OwnrPortlUsrVhcl ownrPortlUsrVhcl) {
//		this.ownrPortlUsrVhcl = ownrPortlUsrVhcl;
//	}

}