
package com.nissanusa.www.VehicleInformation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.vehicleinfo.webservices.xsd.VehicleInformationRequestType;
import com.vehicleinfo.webservices.xsd.VehicleInformationResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nissanusa.vehicleinformation package. 
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

    private final static QName _GetVehicleInformationRegParams_QNAME = new QName("http://www.nissanusa.com/VehicleInformation/", "regParams");
    private final static QName _GetVehicleInformationResponseReturn_QNAME = new QName("http://www.nissanusa.com/VehicleInformation/", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nissanusa.vehicleinformation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetVehicleInformationResponse }
     * 
     */
    public GetVehicleInformationResponse createGetVehicleInformationResponse() {
        return new GetVehicleInformationResponse();
    }

    /**
     * Create an instance of {@link GetVehicleInformation }
     * 
     */
    public GetVehicleInformation createGetVehicleInformation() {
        return new GetVehicleInformation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehicleInformationRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nissanusa.com/VehicleInformation/", name = "regParams", scope = GetVehicleInformation.class)
    public JAXBElement<VehicleInformationRequestType> createGetVehicleInformationRegParams(VehicleInformationRequestType value) {
        return new JAXBElement<VehicleInformationRequestType>(_GetVehicleInformationRegParams_QNAME, VehicleInformationRequestType.class, GetVehicleInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehicleInformationResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.nissanusa.com/VehicleInformation/", name = "return", scope = GetVehicleInformationResponse.class)
    public JAXBElement<VehicleInformationResponseType> createGetVehicleInformationResponseReturn(VehicleInformationResponseType value) {
        return new JAXBElement<VehicleInformationResponseType>(_GetVehicleInformationResponseReturn_QNAME, VehicleInformationResponseType.class, GetVehicleInformationResponse.class, value);
    }

}
