
package com.vehicleinfo.webservices.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.vehicleinfo.vo.xsd.FaultData;


/**
 * <p>Java class for VehicleInformationResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VehicleInformationResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interiorColorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NMCModelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drivetrainName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="make" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transmissionTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transmissionTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelLineCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exteriorColorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drivetrainCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exteriorColorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trimLevelDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interiorColorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultData" type="{http://vo.vehicleinfo.com/xsd}FaultData" minOccurs="0"/>
 *         &lt;element name="VIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bodyStyleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bodyStyleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EIM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VehicleInformationResponseType", propOrder = {
    "interiorColorCode",
    "nmcModelCode",
    "drivetrainName",
    "make",
    "transmissionTypeCode",
    "transmissionTypeName",
    "modelLineCode",
    "modelYear",
    "exteriorColorId",
    "drivetrainCode",
    "modelName",
    "exteriorColorName",
    "trimLevelDescription",
    "optionCode",
    "interiorColorName",
    "faultData",
    "vin",
    "bodyStyleCode",
    "status",
    "bodyStyleName",
    "eim"
})
public class VehicleInformationResponseType {

    @XmlElementRef(name = "interiorColorCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> interiorColorCode;
    @XmlElementRef(name = "NMCModelCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nmcModelCode;
    @XmlElementRef(name = "drivetrainName", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> drivetrainName;
    @XmlElementRef(name = "make", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> make;
    @XmlElementRef(name = "transmissionTypeCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transmissionTypeCode;
    @XmlElementRef(name = "transmissionTypeName", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transmissionTypeName;
    @XmlElementRef(name = "modelLineCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> modelLineCode;
    @XmlElementRef(name = "modelYear", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> modelYear;
    @XmlElementRef(name = "exteriorColorId", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> exteriorColorId;
    @XmlElementRef(name = "drivetrainCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> drivetrainCode;
    @XmlElementRef(name = "modelName", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> modelName;
    @XmlElementRef(name = "exteriorColorName", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> exteriorColorName;
    @XmlElementRef(name = "trimLevelDescription", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> trimLevelDescription;
    @XmlElementRef(name = "optionCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optionCode;
    @XmlElementRef(name = "interiorColorName", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> interiorColorName;
    @XmlElementRef(name = "faultData", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<FaultData> faultData;
    @XmlElementRef(name = "VIN", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> vin;
    @XmlElementRef(name = "bodyStyleCode", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bodyStyleCode;
    @XmlElementRef(name = "status", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> status;
    @XmlElementRef(name = "bodyStyleName", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bodyStyleName;
    @XmlElementRef(name = "EIM", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> eim;

    /**
     * Gets the value of the interiorColorCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteriorColorCode() {
        return interiorColorCode;
    }

    /**
     * Sets the value of the interiorColorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteriorColorCode(JAXBElement<String> value) {
        this.interiorColorCode = value;
    }

    /**
     * Gets the value of the nmcModelCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNMCModelCode() {
        return nmcModelCode;
    }

    /**
     * Sets the value of the nmcModelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNMCModelCode(JAXBElement<String> value) {
        this.nmcModelCode = value;
    }

    /**
     * Gets the value of the drivetrainName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDrivetrainName() {
        return drivetrainName;
    }

    /**
     * Sets the value of the drivetrainName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDrivetrainName(JAXBElement<String> value) {
        this.drivetrainName = value;
    }

    /**
     * Gets the value of the make property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMake() {
        return make;
    }

    /**
     * Sets the value of the make property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMake(JAXBElement<String> value) {
        this.make = value;
    }

    /**
     * Gets the value of the transmissionTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransmissionTypeCode() {
        return transmissionTypeCode;
    }

    /**
     * Sets the value of the transmissionTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransmissionTypeCode(JAXBElement<String> value) {
        this.transmissionTypeCode = value;
    }

    /**
     * Gets the value of the transmissionTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransmissionTypeName() {
        return transmissionTypeName;
    }

    /**
     * Sets the value of the transmissionTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransmissionTypeName(JAXBElement<String> value) {
        this.transmissionTypeName = value;
    }

    /**
     * Gets the value of the modelLineCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModelLineCode() {
        return modelLineCode;
    }

    /**
     * Sets the value of the modelLineCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModelLineCode(JAXBElement<String> value) {
        this.modelLineCode = value;
    }

    /**
     * Gets the value of the modelYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModelYear() {
        return modelYear;
    }

    /**
     * Sets the value of the modelYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModelYear(JAXBElement<String> value) {
        this.modelYear = value;
    }

    /**
     * Gets the value of the exteriorColorId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExteriorColorId() {
        return exteriorColorId;
    }

    /**
     * Sets the value of the exteriorColorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExteriorColorId(JAXBElement<String> value) {
        this.exteriorColorId = value;
    }

    /**
     * Gets the value of the drivetrainCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDrivetrainCode() {
        return drivetrainCode;
    }

    /**
     * Sets the value of the drivetrainCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDrivetrainCode(JAXBElement<String> value) {
        this.drivetrainCode = value;
    }

    /**
     * Gets the value of the modelName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModelName() {
        return modelName;
    }

    /**
     * Sets the value of the modelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModelName(JAXBElement<String> value) {
        this.modelName = value;
    }

    /**
     * Gets the value of the exteriorColorName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExteriorColorName() {
        return exteriorColorName;
    }

    /**
     * Sets the value of the exteriorColorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExteriorColorName(JAXBElement<String> value) {
        this.exteriorColorName = value;
    }

    /**
     * Gets the value of the trimLevelDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTrimLevelDescription() {
        return trimLevelDescription;
    }

    /**
     * Sets the value of the trimLevelDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTrimLevelDescription(JAXBElement<String> value) {
        this.trimLevelDescription = value;
    }

    /**
     * Gets the value of the optionCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOptionCode() {
        return optionCode;
    }

    /**
     * Sets the value of the optionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOptionCode(JAXBElement<String> value) {
        this.optionCode = value;
    }

    /**
     * Gets the value of the interiorColorName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteriorColorName() {
        return interiorColorName;
    }

    /**
     * Sets the value of the interiorColorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteriorColorName(JAXBElement<String> value) {
        this.interiorColorName = value;
    }

    /**
     * Gets the value of the faultData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link FaultData }{@code >}
     *     
     */
    public JAXBElement<FaultData> getFaultData() {
        return faultData;
    }

    /**
     * Sets the value of the faultData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link FaultData }{@code >}
     *     
     */
    public void setFaultData(JAXBElement<FaultData> value) {
        this.faultData = value;
    }

    /**
     * Gets the value of the vin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVIN() {
        return vin;
    }

    /**
     * Sets the value of the vin property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVIN(JAXBElement<String> value) {
        this.vin = value;
    }

    /**
     * Gets the value of the bodyStyleCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBodyStyleCode() {
        return bodyStyleCode;
    }

    /**
     * Sets the value of the bodyStyleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBodyStyleCode(JAXBElement<String> value) {
        this.bodyStyleCode = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatus(JAXBElement<String> value) {
        this.status = value;
    }

    /**
     * Gets the value of the bodyStyleName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBodyStyleName() {
        return bodyStyleName;
    }

    /**
     * Sets the value of the bodyStyleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBodyStyleName(JAXBElement<String> value) {
        this.bodyStyleName = value;
    }

    /**
     * Gets the value of the eim property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEIM() {
        return eim;
    }

    /**
     * Sets the value of the eim property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEIM(JAXBElement<String> value) {
        this.eim = value;
    }

}
