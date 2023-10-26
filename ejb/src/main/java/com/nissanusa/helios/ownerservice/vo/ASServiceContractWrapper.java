/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author GV101559 To get the service contract object from request json
 *
 */
public class ASServiceContractWrapper {
    @JsonProperty("getASContract")
    private ASServiceContractVO asServiceContractVO;

    /**
     * @return the asServiceContractVO
     */
    public ASServiceContractVO getASServiceContractVO() {
        return asServiceContractVO;
    }

    /**
     * @param asServiceContractVO
     *            the asServiceContractVO to set
     */
    public void setASServiceContractVO(ASServiceContractVO asServiceContractVO) {
        this.asServiceContractVO = asServiceContractVO;
    }
}
