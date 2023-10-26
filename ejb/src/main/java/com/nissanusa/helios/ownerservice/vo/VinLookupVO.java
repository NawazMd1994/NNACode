package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for getting address in
 *      vinLookup endpoint
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class VinLookupVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String vin;
    private String brand;

    /**
     * @return the vin
     */
    @JsonProperty(OwnerConstants.VIN)
    public String getVin() {
        return vin;
    }

    /**
     * @param vin
     *            the vin to set
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     *            the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

}
