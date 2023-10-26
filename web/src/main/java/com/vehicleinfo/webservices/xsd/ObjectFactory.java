
package com.vehicleinfo.webservices.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.vehicleinfo.vo.xsd.FaultData;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vehicleinfo.webservices.xsd package. 
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

    private final static QName _VehicleInformationRequestTypeVIN_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "VIN");
    private final static QName _VehicleInformationRequestTypeLookupOpt_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "lookupOpt");
    private final static QName _VehicleInformationResponseTypeInteriorColorCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "interiorColorCode");
    private final static QName _VehicleInformationResponseTypeExteriorColorName_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "exteriorColorName");
    private final static QName _VehicleInformationResponseTypeTransmissionTypeCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "transmissionTypeCode");
    private final static QName _VehicleInformationResponseTypeExteriorColorId_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "exteriorColorId");
    private final static QName _VehicleInformationResponseTypeDrivetrainCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "drivetrainCode");
    private final static QName _VehicleInformationResponseTypeOptionCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "optionCode");
    private final static QName _VehicleInformationResponseTypeModelName_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "modelName");
    private final static QName _VehicleInformationResponseTypeFaultData_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "faultData");
    private final static QName _VehicleInformationResponseTypeStatus_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "status");
    private final static QName _VehicleInformationResponseTypeBodyStyleName_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "bodyStyleName");
    private final static QName _VehicleInformationResponseTypeBodyStyleCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "bodyStyleCode");
    private final static QName _VehicleInformationResponseTypeMake_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "make");
    private final static QName _VehicleInformationResponseTypeNMCModelCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "NMCModelCode");
    private final static QName _VehicleInformationResponseTypeInteriorColorName_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "interiorColorName");
    private final static QName _VehicleInformationResponseTypeModelLineCode_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "modelLineCode");
    private final static QName _VehicleInformationResponseTypeTrimLevelDescription_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "trimLevelDescription");
    private final static QName _VehicleInformationResponseTypeModelYear_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "modelYear");
    private final static QName _VehicleInformationResponseTypeDrivetrainName_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "drivetrainName");
    private final static QName _VehicleInformationResponseTypeTransmissionTypeName_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "transmissionTypeName");
    private final static QName _VehicleInformationResponseTypeEIM_QNAME = new QName("http://webservices.vehicleinfo.com/xsd", "EIM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vehicleinfo.webservices.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VehicleInformationResponseType }
     * 
     */
    public VehicleInformationResponseType createVehicleInformationResponseType() {
        return new VehicleInformationResponseType();
    }

    /**
     * Create an instance of {@link VehicleInformationRequestType }
     * 
     */
    public VehicleInformationRequestType createVehicleInformationRequestType() {
        return new VehicleInformationRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "VIN", scope = VehicleInformationRequestType.class)
    public JAXBElement<String> createVehicleInformationRequestTypeVIN(String value) {
        return new JAXBElement<String>(_VehicleInformationRequestTypeVIN_QNAME, String.class, VehicleInformationRequestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "lookupOpt", scope = VehicleInformationRequestType.class)
    public JAXBElement<String> createVehicleInformationRequestTypeLookupOpt(String value) {
        return new JAXBElement<String>(_VehicleInformationRequestTypeLookupOpt_QNAME, String.class, VehicleInformationRequestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "interiorColorCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeInteriorColorCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeInteriorColorCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "exteriorColorName", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeExteriorColorName(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeExteriorColorName_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "transmissionTypeCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeTransmissionTypeCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeTransmissionTypeCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "exteriorColorId", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeExteriorColorId(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeExteriorColorId_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "drivetrainCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeDrivetrainCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeDrivetrainCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "optionCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeOptionCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeOptionCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "modelName", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeModelName(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeModelName_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "faultData", scope = VehicleInformationResponseType.class)
    public JAXBElement<FaultData> createVehicleInformationResponseTypeFaultData(FaultData value) {
        return new JAXBElement<FaultData>(_VehicleInformationResponseTypeFaultData_QNAME, FaultData.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "status", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeStatus(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeStatus_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "bodyStyleName", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeBodyStyleName(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeBodyStyleName_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "bodyStyleCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeBodyStyleCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeBodyStyleCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "make", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeMake(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeMake_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "VIN", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeVIN(String value) {
        return new JAXBElement<String>(_VehicleInformationRequestTypeVIN_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "NMCModelCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeNMCModelCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeNMCModelCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "interiorColorName", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeInteriorColorName(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeInteriorColorName_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "modelLineCode", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeModelLineCode(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeModelLineCode_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "trimLevelDescription", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeTrimLevelDescription(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeTrimLevelDescription_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "modelYear", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeModelYear(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeModelYear_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "drivetrainName", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeDrivetrainName(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeDrivetrainName_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "transmissionTypeName", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeTransmissionTypeName(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeTransmissionTypeName_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.vehicleinfo.com/xsd", name = "EIM", scope = VehicleInformationResponseType.class)
    public JAXBElement<String> createVehicleInformationResponseTypeEIM(String value) {
        return new JAXBElement<String>(_VehicleInformationResponseTypeEIM_QNAME, String.class, VehicleInformationResponseType.class, value);
    }

}
