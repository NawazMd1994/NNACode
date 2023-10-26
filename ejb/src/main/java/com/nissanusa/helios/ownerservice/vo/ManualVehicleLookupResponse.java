/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ManualVehicleLookupResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String modelCode;

    private String exteriorColorCode;

    private String trimCode;

    private String optionCode;

    private String make;

    private Integer modelYear;

    private String vin;

    public ManualVehicleLookupResponse() {
    }

    /**
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode
     *            the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * @return the exteriorColorCode
     */
    public String getExteriorColorCode() {
        return exteriorColorCode;
    }

    /**
     * @param exteriorColorCode
     *            the exteriorColorCode to set
     */
    public void setExteriorColorCode(String exteriorColorCode) {
        this.exteriorColorCode = exteriorColorCode;
    }

    /**
     * @return the trimCode
     */
    public String getTrimCode() {
        return trimCode;
    }

    /**
     * @param trimCode
     *            the trimCode to set
     */
    public void setTrimCode(String trimCode) {
        this.trimCode = trimCode;
    }

    /**
     * @return the optionCode
     */
    public String getOptionCode() {
        return optionCode;
    }

    /**
     * @param optionCode
     *            the optionCode to set
     */
    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make
     *            the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the modelYear
     */
    public Integer getModelYear() {
        return modelYear;
    }

    /**
     * @param modelYear
     *            the modelYear to set
     */
    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    /**
     * @return the vin
     */
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

}
