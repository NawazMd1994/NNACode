package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author x796314
 * @use Entity implementation class for Entity: Equipment
 */

@Entity
@Table(name = "VW_GPAS_STD_EQUIP", catalog = "")
@XmlRootElement
@NamedQuery(name = "Equipment.modelCode", query = "SELECT o FROM Equipment o WHERE o.equipmentPK.modelCode = :modelCode")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 15L;

    @EmbeddedId
    protected EquipmentPK equipmentPK;

    @Column(name = "MAKE", length = 1)
    private String make;

    @Column(name = "MODEL_YEAR", nullable = false, length = 1)
    private String modelYear;

    @Column(name = "MODEL_NAME", length = 1)
    private String modelName;

    @Column(name = "VERSION", length = 1)
    private String version;

    @Column(name = "CATEGORY", length = 1)
    private String category;

    @Column(name = "EQUIPMENT_NAME", length = 1)
    private String equipmentName;

    /**
     * @return the equipmentPK
     */
    public EquipmentPK getEquipmentPK() {
        return equipmentPK;
    }

    /**
     * @param equipmentPK
     *            the equipmentPK to set
     */
    public void setEquipmentPK(EquipmentPK equipmentPK) {
        this.equipmentPK = equipmentPK;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make
     *            the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the modelYear
     */
    public String getModelYear() {
        return modelYear;
    }

    /**
     * @param modelYear
     *            the modelYear to set
     */
    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
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
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the equipmentName
     */
    public String getEquipmentName() {
        return equipmentName;
    }

    /**
     * @param equipmentName
     *            the equipmentName to set
     */
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

}
