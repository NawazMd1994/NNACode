package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold the UpdateVehicleWrapper for UpdateVehicle endpoint
 */
public class UpdateVehicleWrapper {

    private PersonVehicleVO updateVehicle;

    /**
     * @return the updateVehicle
     */
    public PersonVehicleVO getUpdateVehicle() {
        return updateVehicle;
    }

    /**
     * @param updateVehicle
     *            the updateVehicle to set
     */
    public void setUpdateVehicle(PersonVehicleVO updateVehicle) {
        this.updateVehicle = updateVehicle;
    }

}
