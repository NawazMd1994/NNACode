
package com.vehicleinfo.vo.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vehicleinfo.vo.xsd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FaultDataFaultDetail_QNAME = new QName("http://vo.vehicleinfo.com/xsd", "faultDetail");
    private final static QName _FaultDataFaultString_QNAME = new QName("http://vo.vehicleinfo.com/xsd", "faultString");
    private final static QName _FaultDataFaultCode_QNAME = new QName("http://vo.vehicleinfo.com/xsd", "faultCode");
    private final static QName _FaultDataFaultActor_QNAME = new QName("http://vo.vehicleinfo.com/xsd", "faultActor");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vehicleinfo.vo.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FaultData }
     * 
     */
    public FaultData createFaultData() {
        return new FaultData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.vehicleinfo.com/xsd", name = "faultDetail", scope = FaultData.class)
    public JAXBElement<String> createFaultDataFaultDetail(String value) {
        return new JAXBElement<String>(_FaultDataFaultDetail_QNAME, String.class, FaultData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.vehicleinfo.com/xsd", name = "faultString", scope = FaultData.class)
    public JAXBElement<String> createFaultDataFaultString(String value) {
        return new JAXBElement<String>(_FaultDataFaultString_QNAME, String.class, FaultData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.vehicleinfo.com/xsd", name = "faultCode", scope = FaultData.class)
    public JAXBElement<String> createFaultDataFaultCode(String value) {
        return new JAXBElement<String>(_FaultDataFaultCode_QNAME, String.class, FaultData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.vehicleinfo.com/xsd", name = "faultActor", scope = FaultData.class)
    public JAXBElement<String> createFaultDataFaultActor(String value) {
        return new JAXBElement<String>(_FaultDataFaultActor_QNAME, String.class, FaultData.class, value);
    }

}
