package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

/**
 * @author x178099
 * @use this class will hold the json request object for getting address in
 *      account endpoint
 */

public class SavePersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private AccountVO person;
    private VehicleVO vehicles;
    /**
     * @return the person
     */
    public AccountVO getPerson() {
        return person;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(AccountVO person) {
        this.person = person;
    }

	public VehicleVO getVehicles() {
		return vehicles;
	}

	public void setVehicles(VehicleVO vehicles) {
		this.vehicles = vehicles;
	}

}
