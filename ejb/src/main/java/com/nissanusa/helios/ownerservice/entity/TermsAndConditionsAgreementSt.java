package com.nissanusa.helios.ownerservice.entity;



import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the TERM_CNDTN_AGRMT_STS database table.
 * 
 */
@Entity
@Table(name="TERM_CNDTN_AGRMT_STS")
@XmlRootElement
@NamedQueries({
@NamedQuery(name="TermsAndConditionsAgreementSt.getUsingByVin", query="SELECT t FROM TermsAndConditionsAgreementSt t where t.id.vin  =:vin and t.id.usrPrflId =:usrPrflId  order by t.id.termCndtnSrcCd desc "),
@NamedQuery(name="TermsAndConditionsAgreementSt.getUsingByVinandusrPrflIdandtermCndtnSrcCd", query="SELECT t FROM TermsAndConditionsAgreementSt t where t.id.vin  =:vin and t.id.usrPrflId =:usrPrflId and  t.id.termCndtnSrcCd =:termCndtnSrcCd ")
})
public class TermsAndConditionsAgreementSt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TermsAndConditionsAgreementStPK  id;

	@Column(name="CRTE_TS", nullable=false)
	private Timestamp crteTs;

	@Column(name="CRTE_USR_ID", nullable=false, length=60)
	private String crteUsrId;

	@Column(name="TERM_CNDTN_AGRMT_TS", nullable=false)
	private Timestamp termCndtnAgrmtTs;

	@Column(name="UPDT_TS", nullable=false)
	private Timestamp updtTs;

	@Column(name="UPDT_USR_ID", nullable=false, length=60)
	private String updtUsrId;
	
//	@Column(name = "VIN", nullable = false, length = 17)
//	private String vin;

	//bi-directional many-to-one association to TermCndtnCdLkup
	
//	@Column(name="TERM_CNDTN_SRC_CD", nullable=false, insertable=false, updatable=false)
//	private String termCndtnCdLkup;

	public TermsAndConditionsAgreementSt() {
	}

	public TermsAndConditionsAgreementStPK getId() {
		return this.id;
	}

	public void setId(TermsAndConditionsAgreementStPK id) {
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

	public Timestamp getTermCndtnAgrmtTs() {
		return this.termCndtnAgrmtTs;
	}

	public void setTermCndtnAgrmtTs(Timestamp termCndtnAgrmtTs) {
		this.termCndtnAgrmtTs = termCndtnAgrmtTs;
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

//	public String getTermCndtnCdLkup() {
//		return termCndtnCdLkup;
//	}
//
//	public void setTermCndtnCdLkup(String termCndtnCdLkup) {
//		this.termCndtnCdLkup = termCndtnCdLkup;
//	}
//
//	public String getVin() {
//		return vin;
//	}
//
//	public void setVin(String vin) {
//		this.vin = vin;
//	}

	

}