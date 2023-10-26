/**
 * 
 */
package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rs101547 entity to fetch the model name from model code
 */
@Entity
@Table(name = "MDL_LN_MAPNG", catalog = "")
@NamedQuery(name = "ModelLineMapping.findByModelCode", query = "SELECT o FROM ModelLineMapping o WHERE o.modelCode = :modelCode")
public class ModelLineMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7334993055471061202L;
    @Id
    @Basic(optional = false)
    @Column(name = "MDL_LN_CD")
    String modelCode;
    @Basic(optional = false)
    @Column(name = "MDL_LN_NM")
    String modelName;
    @Basic(optional = false)
    @Column(name = "MK_CD")
    String makeCode;

    /**
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode
     *            the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName
     *            the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the makeCode
     */
    public String getMakeCode() {
        return makeCode;
    }

    /**
     * @param makeCode
     *            the makeCode to set
     */
    public void setMakeCode(String makeCode) {
        this.makeCode = makeCode;
    }

}
