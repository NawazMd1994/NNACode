package com.nissanusa.helios.ownerservice.util;

/**
 * 
 * @author x796314
 * @use Class will hold the constants
 * @lastModified x178099 Added email,newPassword and currentPassword constants
 */
public interface OwnerConstants {

    public static final String HELIOS_OWNERSERVICES_LOG = "HELIOS_OWNERSERVICES_LOG";
    public static final String UPLOAD_FILEPATH = "UPLOAD_FILEPATH";
    public static final String PDF_FILEPATH = "/data02/applicationConfig/helios/ownerService";
    public static final String PROPERTIES_FILE_PATH = "ownerservices.prop.loc";
    public static final String LOG4J_PROPERTIES = "log4j.properties";
    public static final String OWNERSERVICES_PROPERTIES_FILE = "OWNERSERVICES_CONF.properties";
    
    // Error Codes
    public static final String VALIDATION_FAILED_CODE = "4001";
    public static final String VALIDATION_FAILED_FILENAME_CODE = "4029";
    public static final String VALIDATION_FAILED_FIRSTNAME_CODE = "4002";
    public static final String VALIDATION_FAILED_LASTNAME_CODE = "4003";
    public static final String VALIDATION_FAILED_ADDRESSLINE1_CODE = "4004";
    public static final String VALIDATION_FAILED_POSTALCODE = "4012";
    public static final String VALIDATION_FAILED_MOBILENUMBER_CODE = "4013";
    public static final String VALIDATION_FAILED_LANDLINE_CODE = "4014";
    public static final String VALIDATION_FAILED_EMAIL_CODE = "4015";
    public static final String VALIDATION_INVALID_EMAIL_ADDRESS_CODE = "4016";
    public static final String VALIDATION_INVALID_USER_CODE = "4040";
    public static final String VALIDATION_FAILED_MISMATCH_CODE = "4020";
    public static final String VALIDATION_FAILED_INVALID_CODE = "4017";
    public static final String VALIDATION_FAILED_CONTENT_CODE = "4036";
    public static final String VALIDATION_FAILED_CONTENT_TYPE_CODE = "4037";
    public static final String VALIDATION_FAILED_MANDATORY_PARAMETER_CODE = "4038";
    public static final String VEHICLE_INVALID_USER_CODE = "4004";
    public static final String VEHICLE_UNAVAILABLE_VIN_CODE = "4002";
    public static final String VEHICLE_DUPLICATE_USER_CODE = "4005";
    public static final String VEHICLE_MISMATCH_CODE = "4024";
    
    public static final String VEHICLE_NOTAVAILABLE_VINANDUSER_CODE = "4027";
    public static final String INVALID_FEATURE_CODE = "4030";
    public static final String INVALID_VIN_CODE = "4021";
    public static final String EMAIL_ALREADY_EXIST_CODE = "4009";
    public static final String INVALID_MILEAGE_CODE = "4035";
    public static final String INVALID_NICKNAME_CODE = "4026";
    
    public static final String DUPLICATE_VIN_CODE = "4025";
    public static final String VIN_NOTAVAILABLE_CODE = "4022";
    public static final String VEHICLE_SPEC_UNAVAILABLE_CODE="4028";
    public static final String SERVICE_HISTORY_UNAVAILABLE_CODE="4039";
    public static final String RECALL_SERVICE_UNAVAILABLE_CODE="4039";
    //  created for connected service detail by x412935
    public static final String CONNECTED_SERVICE_DETAIL_ERROR_CODE="4050";
    public static final String MESSAGE_DETAILS_ERROR_CODE="4051";
    public static final String MESSAGE_USER_ACTION_ERROR_CODE="4052";
public static final String VIN_UNAVAILABLE_IN_BIDW_AND_OP_DB_ERROR_CODE="4007";
    

    // Error Messages
    public static final String VALIDATION_FAILED_MESSAGE = "Validation Failed";
    public static final String VALIDATION_FAILED_CONTENT_MESSAGE = "File size too large";
    public static final String VEHICLE_INVALID_USER_MESSAGE = "Ownership validation failed";
    public static final String VEHICLE_UNAVAILABLE_VIN_MESSAGE = "VIN not available in system";
    public static final String VEHICLE_DUPLICATE_USER_MESSAGE = "Duplicate Owner Vehicle";
    public static final String VALIDATION_FAILED_MADATORY_PARAMETER = "One or more mandatory parameters are missing while processing the request";
    public static final String VEHICLE_MISMATCH_MESSAGE = "Owner vehicle mismatch";
    public static final String INVALID_FEATURE_MESSAGE = "No Telematics Feature";
    public static final String EMAIL_ALREADY_EXIST_MESSAGE = "User already exists";
    public static final String AUTH_MISMATCH_MESSAGE = "Password mismatch";
    public static final String VEHICLE_SPEC_UNAVAILABLE_MESSAGE="Vehicle Specifications not available";
    public static final String SERVICE_HISTORY_UNAVAILABLE_MESSAGE="Service History not available";
    public static final String RECALL_SERVICE_UNAVAILABLE_MESSAGE="Recall service not available";

    // Error Description
    public static final String VEHICLE_ERROR_CONTENT_DESCRIPTION = "Application is neither MyNissan nor MyInfiniti";
    public static final String VEHICLE_ERROR_FAQCONTENT_DESCRIPTION = "Application/Brand is invalid";
    public static final String VEHICLE_INVALID_BRAND_DESCRIPTION = "Brand is neither Nissan nor Infiniti";
    public static final String VEHICLE_INVALID_VIN_DESCRIPTION = "VIN validation failed";
    public static final String VEHICLE_INVALID_VIN_DESCRIPTION1 = "VIN should not be empty or null";
    public static final String VEHICLE_INVALID_VINLENGTH_DESCRIPTION = "VIN does not contain 17 characters";
    public static final String VEHICLE_INVALID_VINCONTENT_DESCRIPTION = "Invalid VIN, contains letters I,Q,O";
    public static final String VEHICLE_VEHICLE_NOT_AVAILABLE_DESCRIPTION = "Vin is not available in Database";
    public static final String INVALID_MILEAGE_FORMAT_DESCRIPTION = "Mileage Information is not in number format";
    public static final String NICKNAME_VALIDATION_FAILED = "Nick Name validation failed ";
    public static final String CONFIRMATION_CODE_VALIDATION_FAILED = "Confirmation code is invalid";
    public static final String VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION = "Email is not available in Database";
    public static final String PRSNHASHID_NOT_AVAILABLE_DESCRIPTION = "PersonHashID is not available in Database";
    public static final String VEHICLE_INVALID_EMAIL_FOR_BRAND = "User is not available in the database for this brand";
    public static final String VEHICLE_INVALID_USER_DESCRIPTION = "Vehicle is already assigned to another user";
    public static final String VEHICLE_UNAVAILABLE_VIN_DESCRIPTION = "The provided VIN is not available in the VinLookUp";
    public static final String VEHICLE_INVALID_EMAIL_DESCRIPTION = "Email validation failed";
    public static final String VEHICLE_INVALID_CONTENT_DESCRIPTION = "File size exceeeded the maximum allowed limit";
   
    public static final String VEHICLE_INVALID_FIRSTNAME_DESCRIPTION = "FirstName validation failed";
    public static final String VEHICLE_INVALID_LASTNAME_DESCRIPTION = "LastName validation failed";
    public static final String VEHICLE_INVALID_ADDRESSLINE1_DESCRIPTION = "Address validation failed";
    
    public static final String VEHICLE_DUPLICATE_USER_DESCRIPTION = "Vehicle is already assigned to current user";
    public static final String VEHICLE_MISMATCH_DESCRIPTION = "VIN is not associated to the user";
    public static final String VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE = "Vehicle Profile Not Available";
    public static final String VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION = "VIN not available in Database";
    public static final String LINK_REFERENCEID_INVALID_DESCRIPTION = "Token passed in the request is invalid";
    public static final String CODE_MISMATCH_DESCRIPTION = "Email and Code mismatch";
    public static final String INVALID_FIRSTNAME_FORMAT_DESCRIPTION = "First name contains special characters or numbers";
    public static final String INVALID_LASTNAME_FORMAT_DESCRIPTION = "Last name contains special characters or numbers";
    public static final String INVALID_POSTAL_CODE_LENGTH_DESCRIPTION = "Postal Code length must be 5 digit";
    public static final String INVALID_POSTAL_CODE_FORMAT_DESCRIPTION = "Postal Code is not in number format";
    public static final String INVALID_LANDLINE_LENGTH_DESCRIPTION = "Landline number length must be 10 digit";
    public static final String INVALID_LANDLINE_NUMBER_FORMAT_DESCRIPTION = "Landline number is not in number format";
    public static final String INVALID_MOBILE_NUMBER_LENGTH_DESCRIPTION = "Mobile number length must be 10 digit";
    public static final String INVALID_MOBILE_NUMBER_FORMAT_DESCRIPTION = "Mobile number is not in number format";
    public static final String LINK_REFERENCEID_NULL_DESCRIPTION = "Link reference id is null";
    public static final String INVALID_FEATURE_DESCRIPTION = "VIN does not have any Telematics feature";
    public static final String INVALID_MIDDLENAME_FORMAT_DESCRIPTION = "Middle name contains special characters or numbers";
    public static final String INVALID_SECONDLASTNAME_FORMAT_DESCRIPTION = "Second Last name contains special characters or numbers";
    public static final String INVALID_ADDRESSLINE1_DESCRIPTION = "Address validation failed";
    public static final String INVALID_FIRST_NAME_DESCRIPTION = "First Name validation failed";
    public static final String INVALID_LAST_NAME_DESCRIPTION = "Last Name validation failed";
    public static final String EMAIL_ALREADY_EXIST_DESCRIPTION = "User already exists for the respected email ";
    public static final String INVALID_AUTH_POLICY_DESCRIPTION ="Password does not meet the format of password policy";
    public static final String INVALID_AUTH_DESCRIPTION = "Password validation failed";
    public static final String INVALID_CURRENT_AUTH_DESCRIPTION = "Current password does not match with existing password";
    public static final String INVALID_STATE_DESCRIPTION = "State validation failed";
    public static final String VEHICLE_SPECS_NOTAVAILABLE_DESCRIPTION = "Vehicle Specification for this VIN is not available";
    public static final String SERVICE_HISTORY_NOTAVAILABLE_DESCRIPTION = "Service History for this VIN is not available";
    public static final String RECALL_SERVICE_NOTAVAILABLE_DESCRIPTION = "Recall Service for this VIN is not available";
    public static final String INVALID_EMAIL_FORMAT_DESCRIPTION = "Not a valid Email Format";
    public static final String VEHICLE_INVALID_VINLENGTH_CODE = "4001";
    public static final String INVALID_VIN = "4001";
    public static final String INVALID_CONTENTTYPE_DESCRIPTION = "ContentType validation failed";
    public static final String VALIDATION_FAILED_CONTENT_ERROR_CODE = "4030";
    public static final String VALIDATION_FAILED_CONTENT_ERROR_DESCRIPTION = "Content validation failed";
   // created for connected service detail by x412935
    public static final String INVALID_ID_ERROR_MESSAGE = "Invalid ID";
    public static final String INVALID_ID_ERROR_DESCRIPTION = "Invalid ID provided.";
    public static final String INVALID_MESSAGE_ID_ERROR_MESSAGE = "Invalid Message ID";
    public static final String INVALID_MESSAGE_ID_ERROR_DESCRIPTION = "Invalid Message ID provided.";
    public static final String INVALID_ACTION_ERROR_MESSAGE = "Invalid Action";
    public static final String INVALID_ACTION_ERROR_DESCRIPTION = "Invalid Message User Action provided.";
    public static final String INVALID_UNSUBSCRIBE_ACTION_ERROR_DESCRIPTION = "Invalid Action provided.";
    public static final String UNUBSCRIPTION_ERROR_DESCRIPTION = "Invalid Action provided.";
    public static final String VIN_UNAVAILABLE_IN_BIDW_AND_OP_DB_DESCRIPTION="VIN is not available in BIDW and OP DB";

    
    
    public static final String INVALID_ADDRESS_CODE = "4001";
    
    public static final String USER_UNAVAILABLE_CODE = "4002";
    public static final String INVALID_USERANDVIN_CODE = "4003";
    public static final String VEHICLE_INVALID_REQUESTEDSERVICE_CODE = "4003";
    public static final String INVALID_MAKE_CODE = "4003";

    public static final String VEHICLE_INVALID_VIN_CODE = "4008";
    public static final String VEHICLE_INVALID_BRAND_CODE = "4001";
    public static final String LINK_REFERENCEID_NULL_CODE = "4008";

    public static final String VEHICLE_INVALID_CDIIDOWNER_CODE = "4008";

    public static final String GENERAL_ERROR_FAULT_CODE = "4034";
    public static final String INVALID_MILEAGE_FORMAT_CODE = "4015";
    public static final String REQUEST_NULL_CODE = "4019";
    public static final String INVALID_LINK_REFERENCE_CODE = "4017";
    public static final String VEHICLE_CDIID_AVAILABLE_CODE = "4018";
    public static final String LINK_REFERENCEID_INVALID_CODE = "4019";
    public static final String VALIDATION_FAILED_AUTH_CODE = "4008";
    public static final int GENERAL_CODE = 1040005;
    
    public static final String APP_NAME_VALIDATION_ERROR_MESSAGE = "Provided Application is invalid.";
    public static final String FAQ_NOT_FOUND_ERROR_MESSAGE = "No FAQs found for the provided Application Name.";
    public static final String FAQ_ERROR_MESSAGE = "Fetch FAQ Failed";

    public static final String GENERAL_ERROR_MESSAGE = "General Error";
    public static final String GENERAL_MESSAGE = "Document uploaded successfully in DB";
    public static final String VEHICLE_INVALID_VIN_MESSAGE = "Invalid vin";
    public static final String VEHICLE_INVALID_EMAIL_MESSAGE = "Invalid Email";
    public static final String VEHICLE_INVALID_BRAND_MESSAGE = "Validation Failed";
    public static final String VEHICLE_INVALID_JWT_MESSAGE = "Invalid JWT ";
    public static final String VEHICLE_INVALID_VINLENGTH_MESSAGE = "Invalid VIN";
    public static final String VEHICLE_INVALID_NICKNAME_MESSAGE = "Invalid NickName";
    public static final String VEHICLE_INVALID_CDIID_MESSAGE = "Invalid CDIID";
    public static final String VEHICLE_INVALID_CDIIDOWNER_MESSAGE = "Duplicate CDIID";
    public static final String VEHICLE_CDIID_AVAILABLE_MESSAGE = "CDIID already available";

    public static final String INVALID_MILEAGE_FORMAT_MESSAGE = "Invalid Number Format";
    public static final String INVALID_AVERAGEMILEAGE_FORMAT_MESSAGE = "Invalid Number Format";

    public static final String INVALID_NICKNAME_FORMAT_MESSAGE = "Validation Failed";
    
    public static final String REQUEST_NULL_MESSAGE = "Invalid Request";
    public static final String INVALID_CDIID_REQUEST_MESSAGE = "Invalid Request";

    public static final String INVALID_USERANDVIN_MESSAGE = "validation failed";
    public static final String INVALID_MAKE_MESSAGE = "validation failed";
    public static final String VEHICLE_INVALID_REQUESTEDSERVICE_MESSAGE = "Invalid Requested service";

    public static final String INVALID_POSTAL_CODE_MESSAGE = " Invalid Postal Code ";
    public static final String INVALID_NAME_FORMAT_MESSAGE = " Invalid Name";
    public static final String LINK_REFERENCEID_NULL_MESSAGE = "Validation Failed";
    public static final String LINK_REFERENCEID_INVALID_MESSAGE = "Validation Failed";
    public static final String USER_UNAVAILABLE_MESSAGE = "Invalid User";
    public static final String INVALID_SUBSCRIPTION_MESSAGE = "Invalid Subscription Code";
    public static final String INVALID_CONTRACT_MESSAGE = "No Contract";
    public static final String INVALID_RECALL_TYPE_MESSAGE = "Invalid Recall Type Code";
    public static final String INVALID_AS_SERVICE_CONTRACT_PRODUCT_MESSAGE = "Invalid Product";

    public static final String GENERAL_ERROR_DESCRIPTION = "System is unable to process the request now";

    public static final String VEHICLE_INVALID_JWT_DESCRIPTION = "JWT Validation Failed";
    public static final String VEHICLE_INVALID_JWTFORMAT_DESCRIPTION = " JWT Format is Invalid";

    public static final String INVALID_AVERAGEMILEAGE_FORMAT_DESCRIPTION = "Average Mileage Information is not in number format";

    public static final String REQUEST_NULL_DESCRIPTION = "Provided Request is invalid";
    public static final String INVALID_CDIID_REQUEST_DESCRIPTION = "Either of CDIID,Email and PersonHashId is null";
    public static final String VALIDATION_FAILED_CONTENT_TYPE_MESSAGE = "Invalid format";
    public static final String INVALID_PRSNHASHID_DESCRIPTION = "Person HashId validation failed ";
    public static final String INVALID_MAKENULL_DESCRIPTION = "Make validation failed ";
    public static final String INVALID_USERANDVIN_DESCRIPTION = "Invalid Person HashId or username or vin not available";
    public static final String INVALID_MAKE_DESCRIPTION = "Invalid make";
    public static final String VEHICLE_INVALID_REQUESTEDSERVICE_DESCRIPTION = "Requested service is null or invalid";

    public static final String VEHICLE_INVALID_NICKNAME_DESCRIPTION = "Nickname contains more than 14 character";
    public static final String INVALID_FILE_NAME_DESCRIPTION = "File Name validation failed";
    public static final String INVALID_CONTENT_TYPE_DESCRIPTION  = "File format is not allowed";
    public static final String INVALID_CONTENT_DESCRIPTION = "Content validation failed";
    public static final String VEHICLE_INVALID_CDIID_DESCRIPTION = "CDIID validation failed";
    public static final String VEHICLE_INVALID_CDIIDOWNER_DESCRIPTION = "CDIID alreday mapped to other user";
    public static final String VEHICLE_CDIID_AVAILABLE_DESCRIPTION = "CDIID already available for user";
    public static final String INVALID_SUBSCRIPTION_DESCRIPTION = "No Subscription Available";
    public static final String INVALID_CONTRACT_DESCRIPTION = "No Contract Available";
    public static final String INVALID_CONNECTED_SERVICES_VIN_DESCRIPTION = "This VIN is not eligible for any Connected Services";
    // error descriptions for account management

    public static final String USER_UNAVAILABLE_DESCRIPTION = "User not available in database for the userProfileId";
    
    //104026 Included Success Code and Message for Change Email
    public static final String CHANGE_EMAIL_SUCCESS_CODE = "1040006";
    public static final String CHANGE_EMAIL_SUCCESS_MESSAGE = "Email updated successfully";

    public static final String USERID = "HELIOSOS";
    public static final String LOCK = "LCK";
    public static final String UNLOCK = "ULCK";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String AVAILABLE = "available";
    public static final String CODE = "code";
    public static final char DRIVER = 'D';
    public static final String FAULT = "fault";
    public static final String VIN = "vin";
    public static final String USERPROFILEID = "userProfileId";
    public static final String VALIDATE_SYSID = "validateCustomerSystemId";
    public static final String CDIID = "cdiid";
    public static final String MESSAGE = "message";
    public static final String FAILEDSTATUS = "status";
    public static final String FAILEDMESSAGE= "failedMessage";
    public static final String DESCRIPTION = "description";
    public static final String LINK = "completeProfile";
    public static final String BIDW_SERVICE_URL = "VHCL_INFO_SRVC_URL";
    public static final String EMPTY_STRING = "";
    public static final int EMPTY_INT = 0;
   //SFDC Case Status API
    
    public static final String GRANT_KEY = "grant_type";
    public static final String GRANT_VALUE = "password";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SEC = "client_secret";
    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String AUTH_HEADER_VALUE = "Bearer ";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SFDC_ENC_KEY = "Key";
    public static final String SFDC_ENC_IV = "IV";
    public static final String SFDC_KEY_ID = "Key_Id";
    public static final String SFDC_CASE_STS_RESP = "Response";
    public static final String SFDC_RESP_STS_KEY = "Status";

    public static final String LDAP_USR_NM = "LDAP_USR_NM";

    public static final String BRAND_NISSAN = "Nissan";
    public static final String APP_NISSAN = "MyNissan";
    public static final String APP_INFINITI = "MyInfiniti";
    public static final char RELATION_CODE = 'O';
    public static final String NOTIFICATION_OPT = "Y";
    public static final String NISSAN = "N";
    public static final String FALSE = "false";
    public static final String TRUE = "true";

    public static final boolean IS_TRUE = true;
    public static final boolean IS_FALSE = false;

    public static final String INFINITI = "I";
    public static final String CUSTOMER = "CUSTOMER";
    public static final String DEALER = "dealer";
    public static final String BRAND_INFINITI = "Infiniti";

    public static final String ADD_VEHICLE = "add vehicle";
    public static final String VEHICLE_DISPOSAL = "vehicle disposal";
    public static final String UPDATE_VEHICLE = "update vehicle";
    public static final String GET_VEHICLE = "get vehicle";
    public static final String DELETE_VEHICLE = "delete vehicle";
    public static final String CONNECTED_SERVICES = "connected service";
    public static final String CONNECTED_SERVICES_DETAIL = "connected service detail";
    public static final String MESSAGE_DETAILS = "message details";
    public static final String MESSAGE_USER_ACTION = "message user action";
    public static final String VEHICLE_SPECIFICATION = "vehicle specification";
    public static final String SERVICE_CONTRACT = "service contract";
    public static final String RECALL_SERVICE = "recall service";
    public static final String GET_IMAGE_URL = "getImageUrl";
    public static final String UPLOAD_DOCUMENT = "uploadDocument";

    public static final String SERVICE_HISTORY = "service history";


    public static final String UPDATE_ACCOUNT = "update account";
    public static final String VIEW_ACCOUNT = "view account";
    public static final String MULTI_CHANNEL = "Multi channel";
    public static final String CHANGE_EMAIL = "change email";
    public static final String CHANGE_EMAIL_CONFIRMATION = "change email confirmation";
    public static final String PROOF_OF_OWNERSHIP = "proof of ownership";
    public static final String CHANGE_PASSWORD = "change password";
    public static final String NICKNAME = "nickname";
    public static final String FILENAME = "fileName";
    public static final String CONTENT_TYPE = "contentType";
    public static final String CONTENT = "content";
    public static final String LEASETERM = "leaseTerm";
    public static final String MILEAGE = "mileage";
    public static final String AVGMILEAGE = "averageMileage";
    public static final String MODELCODE = "modelCode";
    public static final String MAKECODE = "makeCode";
    public static final String MODELNAME = "modelName";
    public static final String LINECODE = "modelLineCode";
    public static final String INTERIORCOLORCODE = "interiorColorCode";
    public static final String INTERIORCOLORNAME = "interiorColorName";
    public static final String EXTERIORCOLORCODE = "exteriorColorCode";
    public static final String EXTERIORCOLORNAME = "exteriorColorName";
    public static final String OPTIONCODE = "optionCode";
    public static final String MAKE = "make";
    public static final String Make = "Make";
    public static final String YEAR = "modelYear";
    public static final String USRPRFLID = "userProfileId";
    public static final String ID = "id";
    public static final String ENDDATE = "endDate";
    public static final String CS_STATUS = "status";
    public static final String ACTION = "action";
    public static final String MESSAGE_ID = "messageId";
    public static final String BODYSTYLENAME = "bodyStyleName";
    
    // phone disclaimer options
    public static final String LANDLINE = "landLine";
    public static final String MOBILE = "mobile";

    // constants for person details
    public static final String FIRST_NAME = "firstName";
    public static final String TITLE = "title";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String SECOND_LAST_NAME = "secondLastName";
    public static final String ADDRESS_LINE1 = "addressLine1";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String REGION = "region";
    public static final String REGIONCODE = "regionCode";
    public static final String POSTAL_CODE = "postalCode";
    public static final String COUNTRY = "country";
    public static final String MOBILE_NUMBER = "mobileNumber";
    public static final String MOBILE_NETWORK_OPERATOR = "mobileNetworkOperator";
    public static final String LANDLINE_NUMBER = "landlineNumber";
    public static final String TERMSANDCONDITIONS = "termsAndConditions";
    public static final String EMAIL = "email";
    public static final String NEW_EMAIL = "newEmail";
    public static final String OLD_EMAIL = "oldEmail";
    public static final String PRSNHASH = "personHashId";
    public static final String JWT = "jwt";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String CURRENT_PASSWORD = "currentPassword";
    public static final String REQUESTEDSERVICE = "requestedService";
    public static final String ALLOW_TEXT = "allowText";
    public static final String ALLOW_AUTO_DIAL = "allowAutoDial";
    public static final String AUTH = "password";
    public static final String USER_EMAIL_EXISTS = " Email already exist in system";
    public static final String LINKREFERENCEID = "linkReferenceId";
    public static final String MARKETING_PREFERENCES = "marketingPreferences";
    public static final String OPT_IN = "optIn";
    public static final String TOPICS = "topics";
    public static final String CHANNELS = "channels";
    public static final String WarrantyDescription = "WarrantyDescription";
    public static final String WarrantyExpirationDate = "WarrantyExpirationDate";
    public static final String WarrantyExpirationMiles = "WarrantyExpirationMiles";
    
    
    public static final String MODELYEAR = "ModelYear";
    public static final String STATE_CODE = "StateCode";
    public static final String SOURCE_RECORD_DATE = "SourceRecordDate";
    public static final String RECORD_TYPE_CODE = "RecordTypeCode";
    public static final String POLK_RUN_DATE = "PolkRunDate";
    public static final String SOURCE_ID_CODE = "SourceIDCode";
    public static final String BRANDED_CODE = "BrandedCode";
    public static final String DUPLICATE_CODE = "DuplicateCode";
    public static final String TITLE_NUMBER = "TitleNumber";
    public static final String BRANDED_DESCRIPTION = "BrandedDescription";
    public static final String SOURCE_LAST_UPDATEDATE = "SourceLastUpdateDate";
    
    
 
    
    
    // constants for Recall Vehicle details
    

   
    public static final String Vin = "vin";
    public static final String oemRECALL = "OEMRecall";
    public static final String oemRECALLDATA = "OEMRecallData";
    public static final String VEHICLE_LINE_NAME = "VehicleLineName";
    public static final String MODEL_YEAR = "ModelYear";
    public static final String MAX_REFRESH_DATE = "MaxRefreshDate";
    public static final String MIN_RECALL_DATE = "MinRecallDate";
    public static final String NNA_RECALL_ID = "NNARecallId";
    public static final String NHTSA_RECALL_ID = "NHTSARecallId";
    public static final String RECALL_TYPE_CODE = "RecallTypeCode";
    public static final String RECALL_EFFECTIVE_DATE = "RecallEffectiveDate";
    public static final String DEFECT_SUMMARY = "DefectSummary";
    public static final String RECALL_SECONDARY_DESCRIPTION = "RecallSecondaryDescription";
    public static final String RISK_OF_NOT_DOING_REPAIR= "RiskofNotDoingRepair";
    public static final String REMEDY_DESCRIPTION = "RemedyDescription";
    public static final String STATUS = "OPEN";
    

    //constants for Service History details
    public static final String HISTORY = "history";
    public static final String DEALER_CODE = "dealerCode";
    public static final String DEALER_NAME = "dealerName";
    public static final String SERVICE_DATE = "serviceDate";
    public static final String DEALER_INTERNAL_REPAIR_ORDERID = "dealerInternalRepairOrderId";
    public static final String DEALER_INTERNAL_CUSTOMERID = "dealerInternalCustomerId";
    public static final String DEALER_INTERNAL_SERVICE_ADVISORID = "dealerInternalServiceAdvisorId";
    public static final String TOTAL_PARTS_AMOUNT = "totalPartsAmount";
    public static final String TOTAL_LABOR_AMOUNT = "totalLaborAmount";
    public static final String REPAIR_ORDER_TOTAL_AMOUNT = "repairOrderTotalAmount";
    public static final String CUSTOMER_TYPE = "customerType";
    public static final String REPAIR_ORDER_DETAIL_LINE_AUDIT = "repairOrderDetailLineAudit";
    public static final String TOTAL_CUSTOMER_PAY_LABOR_AMOUNT = "totalCustomerPayLaborAmount";
    public static final String TOTAL_CUSTOMER_PAY_PARTS_AMOUNT = "totalCustomerPayPartsAmount";
    public static final String TOTAL_CUSTOMER_PAY_MISC_AMOUNT = "totalCustomerPayMiscAmount";
    public static final String TOTAL_WARRANTY_PAY_LABOR_AMOUNT = "totalWarrantyPayLaborAmount";
    public static final String TOTAL_WARRANTY_PAY_PARTS_AMOUNT = "totalWarrantyPayPartsAmount";
    public static final String TOTAL_WARRANTY_PAY_MISC_AMOUNT = "totalWarrantyPayMiscAmount";
    public static final String TOTAL_INTERNAL_PAY_LABOR_AMOUNT = "totalInternalPayLaborAmount";
    public static final String TOTAL_INTERNAL_PAY_PARTS_AMOUNT = "totalInternalPayPartsAmount";
    public static final String TOTAL_INTERNAL_PAY_MISC_AMOUNT = "totalInternalPayMiscAmount";
    public static final String LAST_MOD_TSP = "lastModTsp";
    public static final String CREATE_DATE = "createDt";
    public static final String DEALER_INTERNAL_SERVICE_ADVISOR_LAST_NAME = "dealerInternalServiceAdvisorLastName";
    public static final String DEALER_INTERNAL_SERVICE_ADVISOR_FIRST_NAME = "dealerInternalServiceAdvisorFirstName";
    public static final String CONTACT_ID = "contactId";
    public static final String REPAIR_ORDER_DETAILS = "repairOrderDetails";
    public static final String DEALER_INTERNAL_TECHICIAN_ID = "dealerInternalTechicianId";
    public static final String SERVICE_OP_CODE = "serviceOpCode";
    public static final String SERVICE_OP_PAY_TYPE = "seviceOpPayType";
    public static final String SERVICE_JOB_OP_TIME = "serviceJobOpTime";
    public static final String DEALER_INTERNAL_SERVICE_OP_CODE = "dealerInternalServiceOpCode";
    public static final String DEALER_INTERNAL_TECHNICIAN_FIRST_NAME = "dealerInternalTechnicianFirstName";
    public static final String DEALER_INTERNAL_TECHNICIAN_LAST_NAME = "dealerInternalTechnicianLastName";
    public static final String SVC_OP_PARTS_AMOUNT = "svcOpPartsAmount";
    public static final String SVC_OP_LABOR_AMOUNT = "svcOpLaborAmount";
    public static final String SVC_OP_MISC_AMOUNT = "svcOpMiscAmount";
    public static final String SVC_OP_DESCRIPTION = "svcOpDescription";
    public static final String CLEAN_OP_DESCRIPTION = "cleanOpDescription";
    public static final String NNA_DEALER = "NNA";
 ///////PROD CONNECTED SERVICE DETAILS
    //SSO URLS NISSAN
    public static final String NISSANCONNECT_APPS_SSO_URL = "https://nna-mipb2c.viaaq.com/login/sso.html?ssoToken=";
    public static final String NISSANCONNECT_SSO_URL = "https://nissanlcn2-portal-prod.viaaq.com/aqPortal/externallogin";
    public static final String NISSANCONNECT_SERVICES_SSO_URL = "https://prd.api.telematics.net/user/login";
    public static final String NISSANCONNECTEV_SSO_URL = "https://qa2.owners.nissanusa.com/heliosnowners";
    public static final String NISSAN_EV_URL = "https://www.nissanusa.com/owners/EV/index";
    //SSO URLS INFINITI
    public static final String INFINITI_CONNECTION_URL = "https://prd.api.telematics.net/user/login";
    public static final String INFINITI_INTOUCH_SERVICES_SSO_URL = "https://prd.api.telematics.net/user/login";
    public static final String INFINITI_INTOUCH_APPS_SSO_URL = "https://nna-mipb2c.viaaq.com/login/sso.html?ssoToken=";   
    //AT&T WiFi Hot spot URL 
    //public static final String NISSANCONNECTWITHWIFIHOTSPOTURL = "https://myvehicle.att.com/#/nissan/learn?country=US";
    public static final String NISSANCONNECTWITHWIFIHOTSPOTURL = "https://myvehicle.att.com/#/nissan/learn";
    public static final String INFINITIINTOUCHWITHWIFIHOTSPOTURL = "https://myvehicle.att.com/#/infiniti/learn";
    // SSO Encrypted Keys
    public static final String NISSAN_ENCRYPTEDKEY = "n180bh41-0d3g-25ne-fnj4-ms73y827";
    public static final String INFINITI_ENCRYPTEDKEY = "n180bh41-0d3g-25ne-fnj4-ms73y827";   
    //RESET PASSWORD LINK
    public static final String NISSAN_WSO2_RESET_LINK = "https://mynissan.nissanusa.com/owners/reset-password-nissan.html";
    public static final String INFINITI_WSO2_RESET_LINK = "https://myinfiniti.infinitiusa.com/owners/reset-password-infiniti.html";   
    //API KEY
    public static final String INFINITI_INTOUCHAPPS_APIKEY = "3424394b-a381-4b62-bb25-150978c48a15";
    public static final String NISSANCONNECT_SERVICES_APIKEY = "5f6321d9-d396-4ee5-96c8-cda4750ce4b6";
    public static final String NISSANCONNECT_MOBILEAPPS_APIKEY = "3424394b-a381-4b62-bb25-150978c48a15";
    public static final String AIRBIQUITY_DELETE_APIKEY = "3424394b-a381-4b62-bb25-150978c48a15";
    //BIDW Username and PWD
  	public static final String BIDW_USER_NAME = "X980366";
   	public static final String BIDW_PASSWORD = "yaK3WO+pnTJlWeh+";
    public static final String END_POINT_URL = "https://b2bws3.na.nissan.biz/ServiceProxy/service?spx_serviceid=NNA-WS-VehicleInformationService-NEW";  
    //AAS Complete Profile Registration
    public static final String COMPLETE_PROFILE_NISSAN = "https://us.owners.nissanusa.com/owners/completeProfile";
    public static final String COMPLETE_PROFILE_INFINITI = "https://us.owners.infinitiusa.com/owners/completeProfile";
	//SFDC API- GET BIDW telematics Report  
    public static final String BIDW_WEBSERVICE_URL= "https://b2bws3.na.nissan.biz/ServiceProxy/service?spx_serviceid=NNA-WS-BIDW-TelematicsReportingService/getTelematicsReport/";
    public static final String	BIDW_AUTHORIZATION_KEY= "WDk4NjE4NDpvdUZHTzhpaFMzZ1ovYWpi";
    //SFDC Case Status API
    public static final String SFDC_TOKEN_URL = "https://login.salesforce.com/services/oauth2/token";
    public static final String SFDC_TOKEN_CLIENT_ID = "3MVG9yZ.WNe6byQCJiZd4hHGFszdrOYu49asLjr.u5HWott06s33F8Z8zoXvUz.WpvL3NCTYFDYDkNd2QJQme";
    public static final String SFDC_TOKEN_CLIENT_SECRET = "CAFEC71FDF25478376EE6C0BE2AAF046145F3828ADF7515B053C248397AA1C7F";
    public static final String SFDC_TOKEN_USERNAME = "nnaisownershipupdate@nissan-usa.com";
    public static final String SFDC_TOKEN_PASSWORD = "Nissansfdc123!v8KpilRQ8Q2tcql6Dly5SRo4i";
    public static final String SFDC_GET_KEY_URL = "https://nissanna.my.salesforce.com/services/apexrest/NNAPortalOwnershipUpdateKey/v1/UpdateOwnershipKeyWebService";
    public static final String SFDC_VALIDATE_CASE_URL = "https://nissanna.my.salesforce.com/services/apexrest/NNAPortalOwnershipUpdate/v1/ValidateCase?caseId=";
    public static final String SFDC_VALIDATE_CASENUMBER_URL = "https://nissanna.my.salesforce.com/services/data/v52.0/composite/sobjects/case?ids="; 
    public static final String SFDC_VALIDATE_CASENUMBER_SUFFIX = "&fields=casenumber";
    ///////////////////////////////  
    // Connected Services NAMES
    public static final String NISSANCONNECTMOBILEAPPS = "NissanConnectMobileApps";
    public static final String NISSANCONNECTSERVICES = "NissanConnectServices";
    public static final String INFINFITIINTOUCHAPPS = "InfinitiIntouchApps";
    public static final String INFINFITICONNECTION = "InfinitiConnection";
    public static final String NISSANCONNECTNAVIGATION = "NissanConnectNavigation";
    public static final String NISSANCONNECTEV = "NissanConnectEV";
    public static final String INFINITIINTOUCHSERVICES = "InfinitiIntouchServices";
    public static final String NISSANCONNECTWITHWIFIHOTSPOT = "NissanConnectWithWiFiHotSpot";
    public static final String INFINITIINTOUCHWITHWIFIHOTSPOT = "InfinitiIntouchWithWiFiHotSpot";
    
    // Static message id for all the connected service used in Connected service details
    //Balaji Onnappan
    public static final String NISSANCONNECTSERVICES_MSG_ID = "15675";
    public static final String INFINFITICONNECTION_MSG_ID = "15677";
    public static final String NISSANCONNECTNAVIGATION_MSG_ID = "15676"; 
    public static final String NISSANCONNECTEV_MSG_ID = "15679";
    public static final String INFINITIINTOUCHSERVICES_MSG_ID = "15678";
    public static final String COMMON_MSG_ID = "15680";

    // Static Title content for all the message id 
    // x497432 Balaji Onnappan
    public static final String NISSANCONNECTSERVICES_TITLE = "NissanConnect® Services Terms and Conditions";
    public static final String INFINFITICONNECTION_TITLE = "Infiniti Connection® Terms and Conditions";
    public static final String NISSANCONNECTNAVIGATION_TITLE = "NissanConnect® Navigation Terms and Conditions"; 
    public static final String NISSANCONNECTEV_TITLE = "NissanConnect® EV Terms and Conditions";
    public static final String NISSANCONNECTEV_SERVICE_TITLE = "NissanConnect® EV and Service Terms and Conditions";
    public static final String INFINITIINTOUCHSERVICES_TITLE = "Infiniti Intouch® Services Terms and Conditions";
    
    
    // Static  Body content for all the message id 
    //x497432 Balaji Onnappan
    public static final String NISSANCONNECTSERVICES_BODY = "Nissan reserves the right to amend, modify or withdraw any or all service commitments at its discretion. You will need to accept these updated terms and conditions for the NissanConnect Services portal before being redirected";
    public static final String INFINFITICONNECTION_BODY = "Nissan reserves the right to amend, modify or withdraw any or all service commitments at its discretion. You will need to accept these updated terms and conditions for the Infiniti Connection portal before being redirected";
    public static final String NISSANCONNECTNAVIGATION_BODY = "Nissan reserves the right to amend, modify or withdraw any or all service commitments at its discretion. You will need to accept these updated terms and conditions for the NissanConnect Navigation portal before being redirected"; 
    public static final String NISSANCONNECTEV_BODY = "Nissan reserves the right to amend, modify or withdraw any or all service commitments at its discretion. You will need to accept these updated terms and conditions for the NissanConnect EV portal before being redirected";
    public static final String INFINITIINTOUCHSERVICES_BODY = "Nissan reserves the right to amend, modify or withdraw any or all service commitments at its discretion. You will need to accept these updated terms and conditions for the Infiniti Intouch Services portal before being redirected";
    public static final String COMMON_MESSAGE_BODY = "You maybe eligible for some new service offers, please see the new service menu when arriving on the portal";
    
    
    // Connected Services Pending Status
    public static final String PENDINGSTATUS = "PENDING";
    public static final String ACTIVE = "ACTIVE";
    public static final String ENDED = "ENDED";
    public static final String SSO = "SSO";
    public static final String NOT_APPLICABLE = "NOT APPLICABLE";
    public static final String INACTIVE = "INACTIVE";
    public static final String NOT_ENROLLED = "NOT ENROLLED";
    public static final String ENROLLED = "ENROLLED";
    public static final String ELIGIBLE = "ELIGIBLE";
    public static final String NON_SUBSCRIBER = "non_subscriber";
    public static final String NON_RENEWAL = "non_renewal";
    public static final String SOLD = "sold";
    public static final String SHELL = "shell";
    public static final String SHELL2 = "shell2";
    public static final String SHELL_2 = "shell_2";
    public static final String TOTAL = "total";
    public static final String TOTALED = "totaled";
    public static final String CANCEL = "cancel";
    public static final String CANCELLED = "CANCELLED";
    public static final String SVL_NON_RECOVERED= "svl_non_recovered";
    public static final String NO_AGREEMENT1 = "no-agreement";
    public static final String NO_AGREEMENT = "no_agreement";
    public static final String DECLINED = "Declined";
    public static final String UNDEFINED = "UNDEFINED";
    
    public static final String S2 = "S2";
    public static final String NS = "NS";
    public static final String NC = "NC";
    public static final String EV = "EV";
    public static final String IC = "IC";
    public static final String CW = "CW";
    public static final String I2 = "I2";
    public static final String I3 = "I3";
    public static final String I4 = "I4";
    public static final String L2 = "L2";
    public static final String N2 = "N2";
    public static final String N3 = "N3";
    public static final String N4 = "N4";
    //added for AT&T wifi 
    public static final String N6 = "N6";
    public static final String N7 = "N7";
    public static final String I6 = "I6";
    public static final String I7 = "I7"; 
    public static final String I8 = "I8";
    public static final String I9 = "I9";
    public static final String M3 = "M3";
    public static final String M4 = "M4";
    public static final String M5 = "M5";
    
    public static final String E1 = "E1";
    
    //2g/3G Blocking Message
    public static final String X1 = "X1";
    public static final String X2 = "X2";
    public static final String X3 = "X3";
    public static final String X4 = "X4"; 
    public static final String X5 = "X5";
    public static final String X6 = "X6";
    public static final String X7 = "X7";
    
    //x100994 - OS-1742
    public static final String M6 = "M6";
    public static final String M7 = "M7";
    public static final String M8 = "M8";
    
    public static final String M9 = "M9";
    
    // Changed for preferred dealer
    public static final String PREFERRED_DEALER="preferredDealer";
    public static final String TRIALPERIOD="trialPeriod";
    public static final String DEALERID="dealerId";
	public static final String WORKPHONE = "workPhone";
	public static final String MOBILEPHONE = "mobilePhone";
	public static final String NEWSLETTER = "newsletter";
	public static final String PRODUCTOFFERS = "productOffers";
	public static final String SERVICEOFFERS = "serviceOffers";
	public static final String SCHEDULEDMAINTENANCE = "scheduledMaintenance";
	public static final String FEEDBACK = "feedback";
	public static final String DISCLAIMER_PREFERENCES = "disclaimerPreferences";
	public static final String OWNED = "owned";
	public static final String FACTORYOPTIONCODE = "factoryOptionCode";
	
	// NC token Enrollment type code update on Vehicle Connected - get Telematics endpoint
	public static final String CUSTOMERCODE = "CUST";
	
	//Default silhoutte images
	public static final String NISSAN_ALTIMA_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/altima.png";
	public static final String NISSAN_GTR_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/gtr.png";
    public static final String NISSAN_ROGUE_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/rogue.png";
	public static final String NISSAN_TITAN_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/titan.png";
	public static final String NISSAN_ALTIMA_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/altima.png";
	public static final String NISSAN_GTR_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/gtr.png";
	public static final String NISSAN_ROGUE_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/rogue.png";
	public static final String NISSAN_TITAN_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/titan.png";
	
	public static final String CREW_CAB="crew cab";
    public static final String KING_CAB="king cab";
	public static final String HATCHBACK = "hatchback";
	public static final String COUPE = "coupe";
	public static final String WAGON_4_DOOR = "wagon 4 door";
	public static final String MID_SUV = "Mid SUV";
	public static final String LARGE_SUV = "Large SUV";
	public static final String NV_VEHICLES = "NV Vehicles";
	public static final String VERSA_NOTE = "Versa Note";
	public static final String VERSA_HB = "Versa HB";
	public static final String VERSA_SD = "Versa SD";
	public static final String VERSA_SEDAN = "Versa Sedan";
	public static final String ROADSTER_370z = "370z Roadster";
	public static final String ROADSTER_350z = "350z Roadster";
	public static final String COUPE_370z = "370Z Coupe";
	public static final String COUPE_350z = "350Z Coupe";
	public static final String GT_R = "GT-R";
	public static final String SENTRA = "Sentra"; 
	public static final String ALTIMA = "Altima";
	public static final String ALTIMA_COUPE = "Altima Coupe";
	public static final String ALTIMA_HYBRID = "Altima Hybrid";
	public static final String ALTIMA_SEDAN = "Altima Sedan";
    public static final String MAXIMA = "Maxima";
	public static final String LEAF = "LEAF";
	public static final String KICKS = "Kicks";
	public static final String ROGUE = "Rogue";
	public static final String ROGUE_SELECT = "Rogue Select";
	public static final String ROGUE_HYBRID = "Rogue Hybrid";
	public static final String ROGUE_SPORT = "Rogue Sport";
	public static final String MURANO = "Murano";
	public static final String MURANO_HYBRID = "Murano Hybrid";
	public static final String MURANO_CROSSCABRIOLET = "Murano CrossCabriolet";
	public static final String PATHFINDER = "Pathfinder";
	public static final String PATHFINDER_HYBRID = "Pathfinder Hybrid";
    public static final String ARMADA = "Armada";
    public static final String CUBE = "cube";
    public static final String JUKE = "Juke";
	public static final String FRONTIER = "Frontier";
	public static final String TITAN = "Titan";
	public static final String TITAN_XD = "Titan XD";
	public static final String NV_CARGO = "NV Cargo";
	public static final String NV_PASSENGER = "NV Passenger";
	public static final String NV200_COMPACT_CARGO = "NV200 Compact Cargo";
	public static final String NV_TAXI  = "NV Taxi";
	public static final String NV1500  = "NV1500";
	public static final String NV2500_HD  = "NV2500 HD";
	public static final String NV3500_HD  = "NV3500 HD";
	public static final String NV200  = "NV200";
	public static final String QUEST  = "Quest";
	public static final String TAXI  = "TAXI";
	public static final String SEDAN  = "sedan";
	public static final String XTERRA  = "Xterra";
	public static final String CONVERTIBLE  = "Convertible";
	public static final String Q50 = "Q50";
	public static final String Q60 = "Q60";
	public static final String Q70 = "Q70";
	public static final String Q70L = "Q70L";
	public static final String QX30 = "QX30";
	public static final String QX60  = "QX60";
	public static final String QX80  = "QX80";
	public static final String G37_COUPE= "G37 Coupe";
	public static final String EX35  = "EX35";
	public static final String EX37  = "EX37";
	public static final String FX45  = "FX45";
	public static final String FX35  = "FX35";
	public static final String FX50  = "FX50";
	public static final String FX37  = "FX37";
	public static final String G37   = "G37";
	public static final String G35_COUPE  = "G35 Coupe";
	public static final String G35_SEDAN  = "G35 Sedan";
	public static final String G37_SEDAN  = "G37 Sedan";
	public static final String G25_SEDAN  = "G25 Sedan";
	public static final String JX35  = "JX35";
	public static final String M45  = "M45";
	public static final String M35  = "M35";
	public static final String M37  = "M37";
	public static final String M56  = "M56";
	public static final String M35_HYBRID  = "M35 Hybrid";
	public static final String QX56  = "QX56";
	public static final String Q40  = "Q40";
	public static final String Q50_HYBRID  = "Q50 Hybrid";
	public static final String Q60_COUPE  = "Q60 Coupe";
	public static final String Q60_CONVERTIBLE  = "Q60 Convertible";
	public static final String Q70_HYBRID  = "Q70 Hybrid";
	public static final String QX50  = "QX50";
    public static final String QX55  = "QX55";
	public static final String QX60_HYBRID  = "QX60 Hybrid";
	public static final String QX70  = "QX70";
    public static final String NOT_AVAILABLE  = "Not_Available";
	
	
	public static final String NISSAN_CROSSOVER_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/crossover.png";
	public static final String NISSAN_LEAF_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/leaf.png";
	public static final String NISSAN_SEDAN_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/sedan.png";
	public static final String NISSAN_SMALL_SEDAN_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/small-sedan.png";
	public static final String NISSAN_SUV_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/suv.png";
	public static final String NISSAN_TRUCK_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/truck.png";
	public static final String NISSAN_VAN_LARGE = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Large/van.png";


	public static final String NISSAN_CROSSOVER_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/crossover.png";
	public static final String NISSAN_LEAF_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/leaf.png";
    public static final String NISSAN_SEDAN_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/sedan.png";
	public static final String NISSAN_SMALL_SEDAN_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/small-sedan.png";
	public static final String NISSAN_SUV_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/suv.png";
	public static final String NISSAN_TRUCK_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/truck.png";
	public static final String NISSAN_VAN_SMALL = "https://www.nissanusa.com/content/dam/Nissan/us/owners/MyNissan/vehicle-images/Default/Small/van.png";
	
	
	
	public static final String INFINITI_CONVERTIBLE_LARGE = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Large/convertible.png";
	public static final String INFINITI_COUPE_LARGE = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Large/coupe.png";
    public static final String INFINITI_CROSSOVER_LARGE = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Large/crossover.png";
    public static final String INFINITI_SEDAN_LARGE = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Large/sedan.png";
	public static final String INFINITI_SMALL_SEDAN_LARGE = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Large/small-sedan.png";
	public static final String INFINITI_SUV_LARGE = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Large/suv.png";
    public static final String INFINITI_LEAF_LARGE = " ";
	public static final String INFINITI_TRUCK_LARGE = " ";
    public static final String INFINITI_VAN_LARGE = " ";

	public static final String INFINITI_CONVERTIBLE_SMALL = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Small/convertible.png";
	public static final String INFINITI_COUPE_SMALL = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Small/coupe.png";
    public static final String INFINITI_CROSSOVER_SMALL = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Small/crossover.png";
    public static final String INFINITI_SEDAN_SMALL = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Small/sedan.png";
	public static final String INFINITI_SMALL_SEDAN_SMALL = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Small/small-sedan.png";
	public static final String INFINITI_SUV_SMALL = "https://www.infinitiusa.com/content/dam/Nissan/us/owners/MyINFINITI/vehicle-images/Default/Small/suv.png";
    public static final String INFINITI_LEAF_SMALL = "";
	public static final String INFINITI_TRUCK_SMALL = " ";
    public static final String INFINITI_VAN_SMALL = "";

    public static final String INVALID_JSON_REQUEST_ERROR_MSG = "Invalid JSON Request";
    public static final String INVALID_JWT_ERROR_MSG = "Invalid JWT";
    public static final String INVALID_DISCLAIMER_REQUEST_ERROR_MSG = "Invalid Disclaimer Preferences format";
    public static final String INVALID_MARKETING_REQUEST_ERROR_MSG = "Invalid Marketing Preferences format";
    public static final String INVALID_JSON_REQUEST_DESCRIPTION = "Provided JSON request is invalid";


    
    //vehicle service static parameters
    public static final String SIZE_DESKTOP = "Desktop";
    public static final String SIZE_MOBILE = "Mobile";
    public static final String SIZE_TABLET = "Tablet";
    public static final String STANDARD = "Standard";
    public static final String HIGH_RESOLUTION = "High_Resolution";
    
    //isVehicleDeleted constants
    public static final String MARITZ = "Maritz";
    public static final String GDC = "GDC";
    public static final String SXM = "SXM";
    public static final String MARITZDELDATE = "MaritzDeletedDate";
    public static final String ISDELETED = "isVehicleDeleted";
    public static final String OPDB_SUCCESS = "S";
    public static final String OPDB_FAIL = "F";
    public static final String NA = "NA";
}
