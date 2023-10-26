package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;

/**
 * @author x178099
 * @use this class will hold the json request object for getting person and
 *      vehicle vo for save,update,view and delete endpoints
 */
public class PersonVehicleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private PersonVO person;
    private VehicleVO vehicle;
    
    private String brand;
    @JsonProperty(OwnerConstants.FAILEDMESSAGE)
	private FailedMessageVO failedMessage;
    
    /**
   	 * @return the failedMessage
   	 */
   	public FailedMessageVO getFailedMessage() {
   		return failedMessage;
   	}

   	/**
   	 * @param failedMessage the failedMessage to set
   	 */
   	public void setFailedMessage(FailedMessageVO failedMessage) {
   		this.failedMessage = failedMessage;
   	}

   	/**
        * @return the failedVehicle
        */
	
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

}
