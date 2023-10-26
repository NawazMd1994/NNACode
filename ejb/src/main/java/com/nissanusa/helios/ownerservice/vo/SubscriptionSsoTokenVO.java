package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * rs101547 To get the telematics SSO Token from request json
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class SubscriptionSsoTokenVO implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3753794152972675073L;
    String nissanConnectMobileAppsToken;
    String nissanConnectServicesToken;
    String nissanConnectNavigationToken;
    String infinitiInTouchAppsToken;
    String infinitiConnectionToken;
    String infinitiInTouchServicesToken;

    /**
     * @return the nissanConnectMobileAppsToken
     */
    public String getNissanConnectMobileAppsToken() {
        return nissanConnectMobileAppsToken;
    }

    /**
     * @param nissanConnectMobileAppsToken
     *            the nissanConnectMobileAppsToken to set
     */
    public void setNissanConnectMobileAppsToken(
            String nissanConnectMobileAppsToken) {
        this.nissanConnectMobileAppsToken = nissanConnectMobileAppsToken;
    }

    /**
     * @return the nissanConnectServicesToken
     */
    public String getNissanConnectServicesToken() {
        return nissanConnectServicesToken;
    }

    /**
     * @param nissanConnectServicesToken
     *            the nissanConnectServicesToken to set
     */
    public void setNissanConnectServicesToken(String nissanConnectServicesToken) {
        this.nissanConnectServicesToken = nissanConnectServicesToken;
    }

    /**
     * @return the nissanConnectNavigationToken
     */
    public String getNissanConnectNavigationToken() {
        return nissanConnectNavigationToken;
    }

    /**
     * @param nissanConnectNavigationToken
     *            the nissanConnectNavigationToken to set
     */
    public void setNissanConnectNavigationToken(
            String nissanConnectNavigationToken) {
        this.nissanConnectNavigationToken = nissanConnectNavigationToken;
    }

    /**
     * @return the infinitiInTouchAppsToken
     */
    public String getInfinitiInTouchAppsToken() {
        return infinitiInTouchAppsToken;
    }

    /**
     * @param infinitiInTouchAppsToken
     *            the infinitiInTouchAppsToken to set
     */
    public void setInfinitiInTouchAppsToken(String infinitiInTouchAppsToken) {
        this.infinitiInTouchAppsToken = infinitiInTouchAppsToken;
    }

    /**
     * @return the infinitiConnectionToken
     */
    public String getInfinitiConnectionToken() {
        return infinitiConnectionToken;
    }

    /**
     * @param infinitiConnectionToken
     *            the infinitiConnectionToken to set
     */
    public void setInfinitiConnectionToken(String infinitiConnectionToken) {
        this.infinitiConnectionToken = infinitiConnectionToken;
    }

    /**
     * @return the infinitiInTouchServicesToken
     */
    public String getInfinitiInTouchServicesToken() {
        return infinitiInTouchServicesToken;
    }

    /**
     * @param infinitiInTouchServicesToken
     *            the infinitiInTouchServicesToken to set
     */
    public void setInfinitiInTouchServicesToken(
            String infinitiInTouchServicesToken) {
        this.infinitiInTouchServicesToken = infinitiInTouchServicesToken;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
