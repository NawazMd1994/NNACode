
package com.vehicleinfo.webservices.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VehicleInformationRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VehicleInformationRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lookupOpt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VehicleInformationRequestType", propOrder = {
    "vin",
    "lookupOpt"
})
public class VehicleInformationRequestType {

    @XmlElementRef(name = "VIN", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> vin;
    @XmlElementRef(name = "lookupOpt", namespace = "http://webservices.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> lookupOpt;

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
     * Gets the value of the lookupOpt property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLookupOpt() {
        return lookupOpt;
    }

    /**
     * Sets the value of the lookupOpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLookupOpt(JAXBElement<String> value) {
        this.lookupOpt = value;
    }

}
