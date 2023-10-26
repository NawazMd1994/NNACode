
package com.nissanusa.www.VehicleInformation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.vehicleinfo.webservices.xsd.VehicleInformationRequestType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="regParams" type="{http://webservices.vehicleinfo.com/xsd}VehicleInformationRequestType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "regParams"
})
@XmlRootElement(name = "getVehicleInformation")
public class GetVehicleInformation {

    @XmlElementRef(name = "regParams", namespace = "http://www.nissanusa.com/VehicleInformation/", type = JAXBElement.class, required = false)
    protected JAXBElement<VehicleInformationRequestType> regParams;

    /**
     * Gets the value of the regParams property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VehicleInformationRequestType }{@code >}
     *     
     */
    public JAXBElement<VehicleInformationRequestType> getRegParams() {
        return regParams;
    }

    /**
     * Sets the value of the regParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VehicleInformationRequestType }{@code >}
     *     
     */
    public void setRegParams(JAXBElement<VehicleInformationRequestType> value) {
        this.regParams = value;
    }

}
