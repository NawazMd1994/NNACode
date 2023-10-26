package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold SaveVehicleWrapper object for saveVehicle endpoint
 */
public class GetUserWrapper {

    private SavePersonVO getUser;

    /**
     * @return the getUser
     */
    public SavePersonVO getGetUser() {
        return getUser;
    }

    /**
     * @param getUser
     *            the getUser to set
     */
    public void setGetUser(SavePersonVO getUser) {
        this.getUser = getUser;
    }

}
