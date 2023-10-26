package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold the GetVehicleSpecificationWrapper for vehicle
 *      specification endpoint
 */
public class GetVehicleSpecificationWrapper {

    private PersonVehicleVO vehicleSpec;

    /**
     * @return the vehicleSpec
     */
    public PersonVehicleVO getVehicleSpec() {
        return vehicleSpec;
    }

    /**
     * @param vehicleSpec
     *            the vehicleSpec to set
     */
    public void setVehicleSpec(PersonVehicleVO vehicleSpec) {
        this.vehicleSpec = vehicleSpec;
    }

}
