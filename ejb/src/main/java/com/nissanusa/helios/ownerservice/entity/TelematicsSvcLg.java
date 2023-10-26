package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TLMTCS_SVC_LG database table.
 * 
 */
@Entity
@Table(name="TLMTCS_SVC_LG")
@NamedQueries({
@NamedQuery(name="TelematicsSvcLg.findAll", query="SELECT t FROM TelematicsSvcLg t"),
@NamedQuery(name="TelematicsSvcLg.findvin", query ="SELECT t FROM TelematicsSvcLg t where t.id.vin =:vin ")
})
public class TelematicsSvcLg implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TelematicsSvcLgPK id;

	@Column(name="CRTE_TS", nullable=false)
	private Timestamp crteTs;

	@Column(name="CRTE_USR_ID", nullable=false, length=60)
	private String crteUsrId;

	@Column(name="RSPNS_ERR_CD", length=20)
	private String rspnsErrCd;

	@Column(name="RSPNS_MSG_TX", length=500)
	private String rspnsMsgTx;

	@Column(name="RSPNS_TS", nullable=false)
	private Timestamp rspnsTs;

	@Column(name="SVC_RSPNS_CD", nullable=false, length=3)
	private String svcRspnsCd;

	@Column(name="UPDT_TS", nullable=false)
	private Timestamp updtTs;

	@Column(name="UPDT_USR_ID", nullable=false, length=60)
	private String updtUsrId;

//	//bi-directional many-to-one association to OwnrPortlUsrVhcl
//	@ManyToOne
//	@JoinColumns({
//		@JoinColumn(name="USR_PRFL_ID", referencedColumnName="USR_PRFL_ID", nullable=false, insertable=false, updatable=false),
//		@JoinColumn(name="VIN", referencedColumnName="VIN", nullable=false, insertable=false, updatable=false)
//		})
//	private OwnrPortlUsrVhcl ownrPortlUsrVhcl;
//
//	//bi-directional many-to-one association to TlmtcsOptnTypLkup
//	@ManyToOne
//	@JoinColumn(name="TLMTCS_OPTN_TYP_CD", nullable=false, insertable=false, updatable=false)
//	private TlmtcsOptnTypLkup tlmtcsOptnTypLkup;
//
//	//bi-directional many-to-one association to TlmtcsSvcLkup
//	@ManyToOne
//	@JoinColumns({
//		@JoinColumn(name="SVC_ID", referencedColumnName="SVC_ID", nullable=false),
//		@JoinColumn(name="TSP_CD", referencedColumnName="TSP_CD", nullable=false)
//		})
//	private TlmtcsSvcLkup tlmtcsSvcLkup;

	public TelematicsSvcLg() {
	}

	public TelematicsSvcLgPK getId() {
		return this.id;
	}

	public void setId(TelematicsSvcLgPK id) {
		this.id = id;
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

	public String getRspnsErrCd() {
		return this.rspnsErrCd;
	}

	public void setRspnsErrCd(String rspnsErrCd) {
		this.rspnsErrCd = rspnsErrCd;
	}

	public String getRspnsMsgTx() {
		return this.rspnsMsgTx;
	}

	public void setRspnsMsgTx(String rspnsMsgTx) {
		this.rspnsMsgTx = rspnsMsgTx;
	}

	public Timestamp getRspnsTs() {
		return this.rspnsTs;
	}

	public void setRspnsTs(Timestamp rspnsTs) {
		this.rspnsTs = rspnsTs;
	}

	public String getSvcRspnsCd() {
		return this.svcRspnsCd;
	}

	public void setSvcRspnsCd(String svcRspnsCd) {
		this.svcRspnsCd = svcRspnsCd;
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

//	public TlmtcsOptnTypLkup getTlmtcsOptnTypLkup() {
//		return this.tlmtcsOptnTypLkup;
//	}
//
//	public void setTlmtcsOptnTypLkup(TlmtcsOptnTypLkup tlmtcsOptnTypLkup) {
//		this.tlmtcsOptnTypLkup = tlmtcsOptnTypLkup;
//	}

//	public TlmtcsSvcLkup getTlmtcsSvcLkup() {
//		return this.tlmtcsSvcLkup;
//	}
//
//	public void setTlmtcsSvcLkup(TlmtcsSvcLkup tlmtcsSvcLkup) {
//		this.tlmtcsSvcLkup = tlmtcsSvcLkup;
//	}

}
