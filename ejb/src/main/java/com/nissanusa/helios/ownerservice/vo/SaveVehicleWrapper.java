package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold SaveVehicleWrapper object for saveVehicle endpoint
 */
public class SaveVehicleWrapper {

    private PersonVehicleVO saveVehicle;

    /**
     * @return the saveVehicle
     */
    public PersonVehicleVO getSaveVehicle() {
        return saveVehicle;
    }

    /**
     * @param saveVehicle
     *            the saveVehicle to set
     */
    public void setSaveVehicle(PersonVehicleVO saveVehicle) {
        this.saveVehicle = saveVehicle;
    }

}
