/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author MP00372143
 *
 */
public class ServiceWarrantyWrapper {
	
	
	
	
	 @JsonProperty("VehicleCoverageData")
	    private ServiceWarrantyVO serviceWarrantyVO;

	    /**
	     * @return the serviceWarrantyVO
	     */
	    public ServiceWarrantyVO getServiceWarrantyVO() {
	        return serviceWarrantyVO;
	    }

	    /**
	     * @param serviceWarrantyVO
	     *            the serviceWarrantyVO to set
	     */
	    public void setServiceWarrantyVO(ServiceWarrantyVO serviceWarrantyVO) {
	        this.serviceWarrantyVO = serviceWarrantyVO;
	    }
	
	

}
