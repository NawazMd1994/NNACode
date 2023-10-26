package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold the GetVehicleWrapper for view Vehicle endpoint
 */
public class GetVehicleWrapper {

    private PersonVehicleVO getVehicle;

    /**
     * @return the getVehicle
     */
    public PersonVehicleVO getGetVehicle() {
        return getVehicle;
    }

    /**
     * @param getVehicle
     *            the getVehicle to set
     */
    public void setGetVehicle(PersonVehicleVO getVehicle) {
        this.getVehicle = getVehicle;
    }

}
