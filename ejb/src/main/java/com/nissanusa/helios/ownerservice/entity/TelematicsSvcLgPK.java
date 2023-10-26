package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TLMTCS_SVC_LG database table.
 * 
 */

@Embeddable
public class TelematicsSvcLgPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USR_PRFL_ID", insertable=false, updatable=false, unique=true, nullable=false, length=20)
	private String usrPrflId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=17)
	private String vin;

	@Column(name="TLMTCS_OPTN_TYP_CD", insertable=false, updatable=false, unique=true, nullable=false, length=2)
	private String tlmtcsOptnTypCd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SVC_RQST_TS", unique=true, nullable=false)
	private java.util.Date svcRqstTs;

	public TelematicsSvcLgPK() {
	}
	public String getUsrPrflId() {
		return this.usrPrflId;
	}
	public void setUsrPrflId(String usrPrflId) {
		this.usrPrflId = usrPrflId;
	}
	public String getVin() {
		return this.vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getTlmtcsOptnTypCd() {
		return this.tlmtcsOptnTypCd;
	}
	public void setTlmtcsOptnTypCd(String tlmtcsOptnTypCd) {
		this.tlmtcsOptnTypCd = tlmtcsOptnTypCd;
	}
	public java.util.Date getSvcRqstTs() {
		return this.svcRqstTs;
	}
	public void setSvcRqstTs(java.util.Date svcRqstTs) {
		this.svcRqstTs = svcRqstTs;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TelematicsSvcLgPK)) {
			return false;
		}
		TelematicsSvcLgPK castOther = (TelematicsSvcLgPK)other;
		return 
			this.usrPrflId.equals(castOther.usrPrflId)
			&& this.vin.equals(castOther.vin)
			&& this.tlmtcsOptnTypCd.equals(castOther.tlmtcsOptnTypCd)
			&& this.svcRqstTs.equals(castOther.svcRqstTs);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usrPrflId.hashCode();
		hash = hash * prime + this.vin.hashCode();
		hash = hash * prime + this.tlmtcsOptnTypCd.hashCode();
		hash = hash * prime + this.svcRqstTs.hashCode();
		
		return hash;
	}
}
