/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author MP00372143
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ServiceWarrantyVO {
	
	@JsonProperty(OwnerConstants.VIN) 
	private String vin;
	
	@JsonProperty("ApplicableWarrantyData")
    private List<ApplicableWarrantyDataVO> applicableWarrantyData;
	
	@JsonProperty("BrandedInfoData")
    private List<BrandedInfoDataVO> brandedInfoData;

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * @return the applicableWarrantyData
	 */
	public List<ApplicableWarrantyDataVO> getApplicableWarrantyData() {
		return applicableWarrantyData;
	}

	/**
	 * @param applicableWarrantyData the applicableWarrantyData to set
	 */
	public void setApplicableWarrantyData(
			List<ApplicableWarrantyDataVO> applicableWarrantyData) {
		this.applicableWarrantyData = applicableWarrantyData;
	}

	/**
	 * @return the brandedInfoData
	 */
	public List<BrandedInfoDataVO> getBrandedInfoData() {
		return brandedInfoData;
	}

	/**
	 * @param brandedInfoData the brandedInfoData to set
	 */
	public void setBrandedInfoData(List<BrandedInfoDataVO> brandedInfoData) {
		this.brandedInfoData = brandedInfoData;
	}

}
