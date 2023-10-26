package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TERM_CNDTN_AGRMT_STS database table.
 * 
 */
@Embeddable
public class TermsAndConditionsAgreementStPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USR_PRFL_ID", insertable=false, updatable=false, unique=true, nullable=false, length=20)
	private String usrPrflId;

	@Column(name="TERM_CNDTN_SRC_CD", insertable=false, updatable=false, unique=true, nullable=false, length=8)
	private String termCndtnSrcCd;

	@Column(unique=true, nullable=false, length=17)
	private String vin;

	
	public String getUsrPrflId() {
		return this.usrPrflId;
	}
	public void setUsrPrflId(String usrPrflId) {
		this.usrPrflId = usrPrflId;
	}
	public String getTermCndtnSrcCd() {
		return this.termCndtnSrcCd;
	}
	public void setTermCndtnSrcCd(String termCndtnSrcCd) {
		this.termCndtnSrcCd = termCndtnSrcCd;
	}
	public String getVin() {
		return this.vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TermsAndConditionsAgreementStPK)) {
			return false;
		}
		TermsAndConditionsAgreementStPK castOther = (TermsAndConditionsAgreementStPK)other;
		return 
			this.usrPrflId.equals(castOther.usrPrflId)
			
			&& this.vin.equals(castOther.termCndtnSrcCd)
     		&& this.vin.equals(castOther.vin);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usrPrflId.hashCode();
		hash = hash * prime + this.vin.hashCode();
		hash = hash * prime + this.termCndtnSrcCd.hashCode();
		
		return hash;
	}
}