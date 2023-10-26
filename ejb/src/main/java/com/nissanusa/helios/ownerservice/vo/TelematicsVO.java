package com.nissanusa.helios.ownerservice.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * x566325 To get the telematics from request json
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)

public class TelematicsVO implements Serializable {
	
    /**
	 * 
	 */
    private static final long serialVersionUID = -3753794152972675073L;
    String nissanConnectMobileApps;
    String nissanConnectServices;
    String nissanConnect;
	String infinitiInTouchApps;
    String infinitiConnection;
    String infinitiInTouchServices;
    String nissanConnectEV;
    String nissanConnectwithWifiHotspot;
    String infinitiIntouchwithWifiHotspot;
    
    
    
    /**
     * @return the nissanConnectEV value
     */
    public String getNissanConnectEV() {
		return nissanConnectEV;
	}


    /**
     * @param nissanConnectEV
     *            the nissanConnectEV to set
     */
	public void setNissanConnectEV(String nissanConnectEV) {
		this.nissanConnectEV = nissanConnectEV;
	}


	/**
     * @return the nissanConnect value
     */
    public String getNissanConnect() {
		return nissanConnect;
	}

    
    /**
     * @param nissanConnect
     *            the nissanConnect to set
     */
	public void setNissanConnect(String nissanConnect) {
		this.nissanConnect = nissanConnect;
	}

    
    /**
     * @return the nissanConnectMobileApps value
     */
	public String getNissanConnectMobileApps() {
		return nissanConnectMobileApps;
	}
	
	/**
     * @param nissanConnectMobileApps
     *            the nissanConnectMobileApps to set
     */
	public void setNissanConnectMobileApps(String nissanConnectMobileApps) {
		this.nissanConnectMobileApps = nissanConnectMobileApps;
	}
	
	/**
     * @return the nissanConnectServices value
     */
	public String getNissanConnectServices() {
		return nissanConnectServices;
	}
	
	
	/**
     * @param nissanConnectServices
     *            the nissanConnectServices to set
     */
	public void setNissanConnectServices(String nissanConnectServices) {
		this.nissanConnectServices = nissanConnectServices;
	}
	
		
	/**
     * @return the infinitiInTouchApps value
     */
	public String getInfinitiInTouchApps() {
		return infinitiInTouchApps;
	}
	
	/**
     * @param infinitiInTouchApps
     *            the infinitiInTouchApps to set
     */
	public void setInfinitiInTouchApps(String infinitiInTouchApps) {
		this.infinitiInTouchApps = infinitiInTouchApps;
	}
	
	/**
     * @return the infinitiConnection value
     */
	public String getInfinitiConnection() {
		return infinitiConnection;
	}
	
	/**
     * @param infinitiConnection
     *            the infinitiConnection to set
     */
	public void setInfinitiConnection(String infinitiConnection) {
		this.infinitiConnection = infinitiConnection;
	}
	
	/**
     * @return the infinitiInTouchServices value
     */
	public String getInfinitiInTouchServices() {
		return infinitiInTouchServices;
	}
	
	/**
     * @param infinitiInTouchServices
     *            the infinitiInTouchServices to set
     */
	public void setInfinitiInTouchServices(String infinitiInTouchServices) {
		this.infinitiInTouchServices = infinitiInTouchServices;
	}

	public String getNissanConnectwithWifiHotspot() {
		return nissanConnectwithWifiHotspot;
	}


	public void setNissanConnectwithWifiHotspot(String nissanConnectwithWifiHotspot) {
		this.nissanConnectwithWifiHotspot = nissanConnectwithWifiHotspot;
	}


	public String getInfinitiIntouchwithWifiHotspot() {
		return infinitiIntouchwithWifiHotspot;
	}


	public void setInfinitiIntouchwithWifiHotspot(
			String infinitiIntouchwithWifiHotspot) {
		this.infinitiIntouchwithWifiHotspot = infinitiIntouchwithWifiHotspot;
	}
	
	

}
