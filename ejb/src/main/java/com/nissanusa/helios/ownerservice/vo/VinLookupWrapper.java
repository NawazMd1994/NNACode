package com.nissanusa.helios.ownerservice.vo;

/**
 * @author x178099
 * @use this class will hold the VinLookupWrapper for VinLookup endpoint
 */
public class VinLookupWrapper {

    private VinLookupVO validateVin;

    public VinLookupWrapper() {
    }

    /**
     * @return the validateVin
     */
    public VinLookupVO getValidateVin() {
        return validateVin;
    }

    /**
     * @param validateVin
     *            the validateVin to set
     */
    public void setValidateVin(VinLookupVO validateVin) {
        this.validateVin = validateVin;
    }

}
