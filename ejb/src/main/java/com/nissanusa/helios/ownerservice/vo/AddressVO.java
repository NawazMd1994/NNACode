package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for getting address in
 *      account endpoint
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class AddressVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty(OwnerConstants.ADDRESS_LINE1)
    private String addressLine1;
    @JsonProperty(OwnerConstants.CITY)
    private String city;
    @JsonProperty(OwnerConstants.REGION)
    private String region;
    @JsonProperty(OwnerConstants.REGIONCODE)
    private String regionCode;
    @JsonProperty(OwnerConstants.POSTAL_CODE)
    private String postalCode;
    @JsonProperty(OwnerConstants.COUNTRY)
    private String country;

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1
     *            the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     *            the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	/**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     *            the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
