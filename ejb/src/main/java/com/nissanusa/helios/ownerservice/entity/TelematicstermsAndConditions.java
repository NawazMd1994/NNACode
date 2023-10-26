package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TLMTCS_TERM_CNDTN database table.
 * 
 */
@Entity
@Table(name="TLMTCS_TERM_CNDTN")
@NamedQueries({
@NamedQuery(name="TelematicstermsAndConditions.findAll", query="SELECT t FROM TelematicstermsAndConditions t"),
@NamedQuery(name="TelematicstermsAndConditions.findByvhclTlmtcsCd", query="SELECT t FROM TelematicstermsAndConditions t WHERE t.vhclTlmtcsCd = :vhclTlmtcsCd")})
public class TelematicstermsAndConditions implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="VHCL_TLMTCS_CD", nullable=false, length=2)
	private String vhclTlmtcsCd;
	
	@Column(name="CRTE_TS", nullable=false)
	private Timestamp crteTs;

	@Column(name="CRTE_USR_ID", nullable=false, length=60)
	private String crteUsrId;

	
	@Column(name="TERM_CNDTN_CNTNT_TX", nullable=false)
	private String termCndtnCntntTx;

	@Column(name="UPDT_TS", nullable=false)
	private Timestamp updtTs;

	@Column(name="UPDT_USR_ID", nullable=false, length=60)
	private String updtUsrId;

	

	public TelematicstermsAndConditions() {
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

	public String getTermCndtnCntntTx() {
		return this.termCndtnCntntTx;
	}

	public void setTermCndtnCntntTx(String termCndtnCntntTx) {
		this.termCndtnCntntTx = termCndtnCntntTx;
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

	public String getVhclTlmtcsCd() {
		return this.vhclTlmtcsCd;
	}

	public void setVhclTlmtcsCd(String vhclTlmtcsCd) {
		this.vhclTlmtcsCd = vhclTlmtcsCd;
	}

}