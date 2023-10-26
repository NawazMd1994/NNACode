
package com.vehicleinfo.vo.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FaultData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FaultData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faultString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultActor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultData", propOrder = {
    "faultString",
    "faultCode",
    "faultDetail",
    "faultActor"
})
public class FaultData {

    @XmlElementRef(name = "faultString", namespace = "http://vo.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faultString;
    @XmlElementRef(name = "faultCode", namespace = "http://vo.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faultCode;
    @XmlElementRef(name = "faultDetail", namespace = "http://vo.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faultDetail;
    @XmlElementRef(name = "faultActor", namespace = "http://vo.vehicleinfo.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faultActor;

    /**
     * Gets the value of the faultString property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaultString() {
        return faultString;
    }

    /**
     * Sets the value of the faultString property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaultString(JAXBElement<String> value) {
        this.faultString = value;
    }

    /**
     * Gets the value of the faultCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaultCode() {
        return faultCode;
    }

    /**
     * Sets the value of the faultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaultCode(JAXBElement<String> value) {
        this.faultCode = value;
    }

    /**
     * Gets the value of the faultDetail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaultDetail() {
        return faultDetail;
    }

    /**
     * Sets the value of the faultDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaultDetail(JAXBElement<String> value) {
        this.faultDetail = value;
    }

    /**
     * Gets the value of the faultActor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaultActor() {
        return faultActor;
    }

    /**
     * Sets the value of the faultActor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaultActor(JAXBElement<String> value) {
        this.faultActor = value;
    }

}
