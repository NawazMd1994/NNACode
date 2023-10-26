package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RCL_TYP_LKUP")
/**
 * 
 * @author x787640	
 * @use  holds the recall type code and its description
 *
 */
public class RecallTypeLookup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RCL_TYP_CD")
    private String recallTypeCode;

    @Column(name = "RCL_TYP_DS")
    private String recallTypeDescription;

    /**
     * @return the recallTypeCode
     */
    public String getRecallTypeCode() {
        return recallTypeCode;
    }

    /**
     * @param recallTypeCode
     *            the recallTypeCode to set
     */
    public void setRecallTypeCode(String recallTypeCode) {
        this.recallTypeCode = recallTypeCode;
    }

    /**
     * @return the recallTypeDescription
     */
    public String getRecallTypeDescription() {
        return recallTypeDescription;
    }

    /**
     * @param recallTypeDescription
     *            the recallTypeDescription to set
     */
    public void setRecallTypeDescription(String recallTypeDescription) {
        this.recallTypeDescription = recallTypeDescription;
    }

}
