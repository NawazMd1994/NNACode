/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: mobileProvider
 */
@Entity
@Table(name = "MBL_CARR_PRVDR", catalog = "")
@XmlRootElement
@NamedQueries({

		@NamedQuery(name = "MobileProvider.findByMobileProviderKey", query = "SELECT s FROM MobileProvider s WHERE s.mobileProviderKey = :mobileProviderKey"),
		@NamedQuery(name = "MobileProvider.findByMobileProviderName", query = "SELECT s FROM MobileProvider s WHERE s.mobileProviderName = :mobileProviderName") })
public class MobileProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "MBL_CARR_CD", nullable = false, length = 10)
	private String mobileProviderKey;

	public MobileProvider() {
		// default constructor
	}

	/**
	 * @return the mobileProviderKey
	 */
	public String getMobileProviderKey() {
		return mobileProviderKey;
	}

	/**
	 * @param mobileProviderKey
	 *            the mobileProviderKey to set
	 */
	public void setMobileProviderKey(String mobileProviderKey) {
		this.mobileProviderKey = mobileProviderKey;
	}

	/**
	 * @return the mobileProviderName
	 */
	public String getMobileProviderName() {
		return mobileProviderName;
	}

	/**
	 * @param mobileProviderName
	 *            the mobileProviderName to set
	 */
	public void setMobileProviderName(String mobileProviderName) {
		this.mobileProviderName = mobileProviderName;
	}

	@Column(name = "MBL_CARR_NM ", nullable = true, length = 60)
	private String mobileProviderName;

	public MobileProvider(String mobileProviderKey) {
		this.mobileProviderKey = mobileProviderKey;
	}

	public MobileProvider(String mobileProviderKey, String mobileProviderName) {
		this.mobileProviderKey = mobileProviderKey;
		this.mobileProviderName = mobileProviderName;

	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mobileProviderKey != null ? mobileProviderKey.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		Boolean response = true;
		if (!(object instanceof MobileProvider)) {
			response = false;
		}
		MobileProvider other = (MobileProvider) object;
		if ((this.mobileProviderKey == null && other.mobileProviderKey != null)
				|| (this.mobileProviderKey != null && !this.mobileProviderKey
						.equals(other.mobileProviderKey))) {
			response = false;
		}
		return response;
	}

	@Override
	public String toString() {
		return "com.nissanglobal.helios.ownerservice.entity.MobileProvider[ mobileProviderKey="
				+ mobileProviderKey + " ]";
	}

}
