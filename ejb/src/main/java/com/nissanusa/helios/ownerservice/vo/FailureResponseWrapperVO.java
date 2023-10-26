package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold the FailureResponseWrapper for fault response
 */
public class FailureResponseWrapperVO {

    private FailureResponseVO fault;

    /**
     * @return the fault
     */
    public FailureResponseVO getFault() {
        return fault;
    }

    /**
     * @param fault
     *            the fault to set
     */
    public void setFault(FailureResponseVO fault) {
        this.fault = fault;
    }

}
