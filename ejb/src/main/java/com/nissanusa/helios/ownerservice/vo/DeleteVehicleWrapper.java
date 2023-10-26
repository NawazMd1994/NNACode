package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x796314
 * @use this class will hold the json request object for deleteVehicle endpoint
 */
public class DeleteVehicleWrapper {

    private PersonVehicleVO deleteVehicle;

    /**
     * @return the deleteVehicle
     */
    public PersonVehicleVO getDeleteVehicle() {
        return deleteVehicle;
    }

    /**
     * @param deleteVehicle
     *            the deleteVehicle to set
     */
    public void setDeleteVehicle(PersonVehicleVO deleteVehicle) {
        this.deleteVehicle = deleteVehicle;
    }

}
