/**
 * 
 */
package com.nissanusa.helios.ownerservice.vo;

import java.util.List;

/**
 * @author GV101559 To get the contracts object from request json
 *
 */
public class ASContractWrapper {
    private List<ASContractVO> asContractVO;

    /**
     * @return the asContractVO
     */
    public List<ASContractVO> getAsContractVO() {
        return asContractVO;
    }

    /**
     * @param asContractVO
     *            the asContractVO to set
     */
    public void setAsContractVO(List<ASContractVO> asContractVO) {
        this.asContractVO = asContractVO;
    }
}
