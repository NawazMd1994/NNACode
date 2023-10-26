/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author GV101559 To get the service contract json object from request json
 *
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class ASServiceContractVO {
    private static final long serialVersionUID = 795376177764367778L;
    private PersonVO person;
    private VehicleVO vehicle;
    
    @JsonProperty("contracts")
    private List<ASContractVO> asContract;

    /**
     * @return the person
     */
    public PersonVO getPerson() {
        return person;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(PersonVO person) {
        this.person = person;
    }

    /**
     * @return the vehicle
     */
    public VehicleVO getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle
     *            the vehicle to set
     */
    public void setVehicle(VehicleVO vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the asContract
     */
    public List<ASContractVO> getAsContract() {
        return asContract;
    }

    /**
     * @param asContract
     *            the asContract to set
     */
    public void setAsContract(List<ASContractVO> asContract) {
        this.asContract = asContract;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
