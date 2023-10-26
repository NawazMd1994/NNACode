package com.nissanusa.helios.ownerservice.ws;

/*
 *last modified date 24-10-2016 by x178099

 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.itextpdf.text.DocumentException;
import com.nissanusa.helios.ownerservice.entity.ASServiceContractCategoryLookup;
import com.nissanusa.helios.ownerservice.entity.Equipment;
import com.nissanusa.helios.ownerservice.entity.FAQDetails;
import com.nissanusa.helios.ownerservice.entity.ManualVehicleLookup;
import com.nissanusa.helios.ownerservice.entity.MobileProvider;
import com.nissanusa.helios.ownerservice.entity.ModelLineMapping;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalSubScriptionProduct;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhone;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicleFailedReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicleTelematicsCodeMaster;
import com.nissanusa.helios.ownerservice.entity.State;
import com.nissanusa.helios.ownerservice.entity.TelematicstermsAndConditions;
import com.nissanusa.helios.ownerservice.entity.TermsAndConditionsAgreementSt;
import com.nissanusa.helios.ownerservice.entity.TermsAndConditionsAgreementStPK;
import com.nissanusa.helios.ownerservice.entity.VehdsplMaritzDlyLndgPrcngEntity;
import com.nissanusa.helios.ownerservice.entity.VehicleCarwings;
import com.nissanusa.helios.ownerservice.entity.VehicleSpecification;
import com.nissanusa.helios.ownerservice.facade.UserLocal;
import com.nissanusa.helios.ownerservice.facade.VehicleLocal;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.PropertiesLoader;
import com.nissanusa.helios.ownerservice.util.Utility;
import com.nissanusa.helios.ownerservice.vo.ASContractVO;
import com.nissanusa.helios.ownerservice.vo.ASServiceContractWrapper;
import com.nissanusa.helios.ownerservice.vo.ApplicableWarrantyDataVO;
import com.nissanusa.helios.ownerservice.vo.DeleteVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.FailedVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.GetMessageDetailWrapper;
import com.nissanusa.helios.ownerservice.vo.GetTelematicsDetailWrapper;
import com.nissanusa.helios.ownerservice.vo.GetTelematicsWrapper;
import com.nissanusa.helios.ownerservice.vo.GetVehicleSpecificationWrapper;
import com.nissanusa.helios.ownerservice.vo.GetVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.HistoryVO;
import com.nissanusa.helios.ownerservice.vo.MessageUserActionWrapper;
import com.nissanusa.helios.ownerservice.vo.OEMRecallDataVO;
import com.nissanusa.helios.ownerservice.vo.RecallWrapper;
import com.nissanusa.helios.ownerservice.vo.ReportOrderDetailsVO;
import com.nissanusa.helios.ownerservice.vo.SaveVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.ServiceWarrantyWrapper;
import com.nissanusa.helios.ownerservice.vo.ServiceHistoryWrapper;
import com.nissanusa.helios.ownerservice.vo.SubscriptionSsoTokenVO;
import com.nissanusa.helios.ownerservice.vo.TelematicsSubscriptionVO;
import com.nissanusa.helios.ownerservice.vo.TelematicsVO;
import com.nissanusa.helios.ownerservice.vo.UpdateVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UploadDocumentWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateCdiidWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateDataWrapper;
import com.nissanusa.helios.ownerservice.vo.VinLookupWrapper;
import com.nissanusa.www.VehicleInformation.HeaderHandlerResolver;
import com.nissanusa.www.VehicleInformation.VehicleInformationServiceImplService;
import com.nissanusa.www.VehicleInformation.VehicleInformationServiceImplServicePortType;
import com.vehicleinfo.webservices.xsd.ObjectFactory;
import com.vehicleinfo.webservices.xsd.VehicleInformationRequestType;
import com.vehicleinfo.webservices.xsd.VehicleInformationResponseType;

@Path("/vehicles")
/**
 * 
 * @author x178099
 * @use WS class will hold the rest services involving add,view,get and delete
 * vehicle services.
 *
 */
public class VehicleWS implements OwnerConstants {

	private static final Logger LOG = Logger.getLogger(VehicleWS.class);

	@Inject
	VehicleLocal vehicleLocal;
	@Inject
	UserLocal userLocal;

	public VehicleWS() {
		try {
			PropertiesLoader.getLog4j();

		} catch (Exception e) {
			LOG.info("PropertiesLoaderException Exception VehicleWS: "
					+ e.getMessage());

		}
	}

	/**
	 * @author x796314
	 * @use To delete vehicle information of a user account
	 * @method-POST
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/deleteVehicle")
	public Response deleteVehicle(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Delete Vehicle JSON request = " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		String response = "";
		String result = "";
		OwnerPortalUser validUser;
		OwnerPortalUserVehicle vinMappedToSameUser;
		OwnerPortalVehicle vehicleInfo;
		DeleteVehicleWrapper deleteVehicleWrapper = null;
		String vin = "";
		String email = "";
		String personHashId = "";
		boolean generalError = false;
		boolean emailNotAvailable = false;
		boolean isvalidJSON = true;

		
		Date maritzDelDate=null;
		String maritz=EMPTY_STRING;
		String gdc=EMPTY_STRING;
		String sxm=EMPTY_STRING;
		String opDBDelST=EMPTY_STRING;
		Timestamp opDBDelTS=null;
		
		JSONObject jsonReqObj = new JSONObject(requestJson);
		JSONObject jsonKeyObj =jsonReqObj.getJSONObject("deleteVehicle").getJSONObject("vehicle").getJSONObject("isVehicleDeleted");
	
		
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				deleteVehicleWrapper = mapper.readValue(requestJson,
						DeleteVehicleWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(deleteVehicleWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(deleteVehicleWrapper
							.getDeleteVehicle())) {
						if (Utility
								.isObjectNotNullorNotEmpty(deleteVehicleWrapper
										.getDeleteVehicle().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(deleteVehicleWrapper
										.getDeleteVehicle()
										.getVehicle())) {
							isValidBrand = isBrandNull(brand, jsonObj,
									jsonFinalObj);

							vin = deleteVehicleWrapper.getDeleteVehicle()
									.getVehicle().getVin();
							email = deleteVehicleWrapper.getDeleteVehicle()
									.getPerson().getEmail();
							personHashId = deleteVehicleWrapper
									.getDeleteVehicle().getPerson()
									.getPersonHashId();
							
							//X336061 - OS -2690 - As part of Vehicle Disposal, log 3rd party responses in OP DB. - Starts
						
							VehdsplMaritzDlyLndgPrcngEntity vehdsplMaritzDlyLndgPrcngEntity=vehicleLocal.checkVin(vin);
							
								
							if(Utility
									.isObjectNotNullorNotEmpty(deleteVehicleWrapper.getDeleteVehicle()
										.getVehicle().getIsVehicleDeletedVO()) && vehdsplMaritzDlyLndgPrcngEntity!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin().equals(vin)){
								LOG.info("Action class = vehicleWS ::: Method = DeleteVehicle API ::: Log 3rd party responses for Disposal VINs alone in OP DB");

								if(Utility
										.isObjectNotNullorNotEmpty(deleteVehicleWrapper.getDeleteVehicle()
												.getVehicle().getIsVehicleDeletedVO().isMaritz()) && jsonKeyObj.has(MARITZ)){
									if(deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeletedVO().isMaritz())
										maritz = OPDB_SUCCESS;
									else //if(!deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeleteVO().isMaritz())
									maritz = OPDB_FAIL;
								}else
									maritz = NA;
									
								if(Utility
										.isObjectNotNullorNotEmpty(deleteVehicleWrapper.getDeleteVehicle()
												.getVehicle().getIsVehicleDeletedVO().isGdc()) && jsonKeyObj.has(GDC)){
									if(deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeletedVO().isGdc())
										gdc = OPDB_SUCCESS;
									else //if(!deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeleteVO().isGdc())
									gdc = OPDB_FAIL;
								}else
									gdc = NA;
								if(Utility
										.isObjectNotNullorNotEmpty(deleteVehicleWrapper.getDeleteVehicle()
												.getVehicle().getIsVehicleDeletedVO().isSxm()) && jsonKeyObj.has(SXM)){
									if(deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeletedVO().isSxm())
										sxm = OPDB_SUCCESS;
									else //if(!deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeleteVO().isSxm())
									sxm = OPDB_FAIL;
								}else
									sxm = NA;
								
								if(deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeletedVO().getMaritzDelDate()!=null)
									maritzDelDate=deleteVehicleWrapper.getDeleteVehicle().getVehicle().getIsVehicleDeletedVO().getMaritzDelDate();
								
								LOG.info("Action class = VehicleWS ::: Method = DeleteVehicle ::: 3rd party responses from Fuse : Maritz = " +maritz +"GDC = "+gdc+"SXM = "+sxm);
								
								LOG.info("Action class = VehicleWS ::: Method= DeleteVehicle ::: VIN = " +vin);
								
									if(vehdsplMaritzDlyLndgPrcngEntity!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin()!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin().equals(vin) && vehdsplMaritzDlyLndgPrcngEntity.getDiposalDate()!=null ){
										LOG.info("Action class = VehicleWS ::: Method= DeleteVehicle :: if vin is available in vehdsplMaritzDlyLndgPrcng table VIN = " +vehdsplMaritzDlyLndgPrcngEntity.getVin());
										
										vehdsplMaritzDlyLndgPrcngEntity.setMaritzStatus(maritz);
										vehdsplMaritzDlyLndgPrcngEntity.setGdcStatus(gdc);
										vehdsplMaritzDlyLndgPrcngEntity.setSxmStatus(sxm);
										
											if(maritzDelDate!=null)
												vehdsplMaritzDlyLndgPrcngEntity.setMaritzDelDate(maritzDelDate);
											
										
											
									}else{
										LOG.info("Action class =vehicleWS ::: Method= deleteVehicle :: vin is not available in Disposal table, so not updating 3rd party responses in OP DB");
										//System.out.println("VIN is not available in OwnerPortalUserVehicleDisposalTracker Table");
									}
								
							}
							
							//X336061 - OS -2690 - As part of Vehicle Disposal, log 3rd party responses in OP DB. - Ends
							
							
							
							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
								} else {
									LOG.info("Brand is neither Nissan or Infiniti");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}
							if (isValidBrand && isValidVin
									&& isValidPersonHashId && isValidEmail) {

								if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
									deleteVehicleWrapper.getDeleteVehicle()
									.setBrand(NISSAN);
								} else if (brand
										.equalsIgnoreCase(BRAND_INFINITI)) {
									deleteVehicleWrapper.getDeleteVehicle()
									.setBrand(INFINITI);
								}

								// x566325 - Brand Segregation - send brand also
								/*
								 * validUser = userLocal.validateEmail(email,
								 * personHashId);
								 */

								validUser = userLocal.validateEmail(email,
										personHashId, brand);
								// x566325 - Added below condition for email is
								// not available in database case
								if (!Utility
										.isObjectNotNullorNotEmpty(validUser)) {

									Util.setFaultDataToJSON(
											jsonObj,
											jsonFinalObj,
											VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
									emailNotAvailable = true;

								} else if (Utility
										.isObjectNotNullorNotEmpty(validUser)) {

									vinMappedToSameUser = vehicleLocal
											.getUserVehicleInfo(validUser
													.getUserProfileId(), vin);

									if (Utility
											.isObjectNotNullorNotEmpty(vinMappedToSameUser)) {
										vehicleInfo = vehicleLocal
												.getVehicleInfo(vin);

										response = vehicleLocal.deleteVehicle(
												vin, vinMappedToSameUser,
												vehicleInfo);
										
										if(response.equals("success")){
											 Date date= new Date();
											 long time = date.getTime();
											 opDBDelTS = new Timestamp(time);
											 opDBDelST=OPDB_SUCCESS;
										}else
											opDBDelST=OPDB_FAIL;
										if(vehdsplMaritzDlyLndgPrcngEntity!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin()!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin().equals(vin) && vehdsplMaritzDlyLndgPrcngEntity.getDiposalDate()!=null ){
										vehdsplMaritzDlyLndgPrcngEntity.setNissanStatus(opDBDelST);
										vehdsplMaritzDlyLndgPrcngEntity.setNissanDelDate(opDBDelTS);
										}

									} else if (!Utility
											.isObjectNotNullorNotEmpty(vinMappedToSameUser)) {
										jsonObj.accumulate(CODE,
												VEHICLE_NOTAVAILABLE_VINANDUSER_CODE);
										jsonObj.accumulate(MESSAGE,
												VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = FAILURE;
									}
								} else {
									jsonObj.accumulate(CODE,
											VALIDATION_INVALID_EMAIL_ADDRESS_CODE);
									jsonObj.accumulate(MESSAGE,
											VALIDATION_FAILED_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
								}

							}else{
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
										INVALID_JSON_REQUEST_DESCRIPTION);
								LOG.info("Invalid json request");
								result = FAILURE;
							}

							if (Utility.isStringNotNullorNotEmpty(response)
									&& response.equalsIgnoreCase(SUCCESS)) {
								LOG.info("Delete vehicle JSON success response");

								result = SUCCESS;

							}
							if(vehdsplMaritzDlyLndgPrcngEntity!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin()!=null && vehdsplMaritzDlyLndgPrcngEntity.getVin().equals(vin) && vehdsplMaritzDlyLndgPrcngEntity.getDiposalDate()!=null )
							vehicleLocal.updateVehdsplMaritzDlyLndgPrcngEntity(vehdsplMaritzDlyLndgPrcngEntity);
						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							LOG.info("Invalid json request");
						      isvalidJSON=false;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
					      isvalidJSON=false;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
				      isvalidJSON=false;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
			      isvalidJSON=false;
			}
			
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("Delete vehicle JSON success response for the vin = " + vin
						+ "with email = " + email);
				return Response.ok().status(200).entity(EMPTY_STRING).build();
			} else if (generalError || !isvalidJSON) {
				LOG.info("Delete vehicle JSON failed response");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			// x566325 - Brand Segregation -added emailNotAvailable condition below
			else if (!isValidVin || !isValidBrand
					|| result.equalsIgnoreCase(FAILURE) || emailNotAvailable) {
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request else block");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
				
			}
			
			

		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JSONException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "General exception  during delete vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		
	}

	/**
	 * @author x178099
	 * @use To get vehicle information of a user account
	 * @method-POST
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getVehicle")
	public Response getVehicle(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		LOG.info("Get Vehicle JSON request = " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isvalidJSON = true;
		String result = "";
		String vinMappedToSameUser = "";
		OwnerPortalUser validUser;
		OwnerPortalVehicle vehicleInfo;
		OwnerPortalUserVehicle userVehicleInfo;
		boolean isValidNickName = true;
		boolean isValidMake = true;
		String userProfileId = "";
		String vin = "";
		String email = "";
		String personHashId = "";
		String nickName = "";
		String make = "";
		boolean emailNotAvailable = false;

		boolean generalError = false;
		String largeImageUrl = "";
		String smallImageUrl = "";
		String modelCode = "";
		String exteriorColorCode = "";
		String factoryOptionCode = "";
		ManualVehicleLookup manualVehicleLookup = null;
		String bodyStyleName = "";
		String modelName = "";
		OwnerPortalVehicle ownerPortalVehicle;

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				GetVehicleWrapper getVehicleWrapper = mapper.readValue(
						requestJson, GetVehicleWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(getVehicleWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(getVehicleWrapper
							.getGetVehicle())) {
						if (Utility.isObjectNotNullorNotEmpty(getVehicleWrapper
								.getGetVehicle().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(getVehicleWrapper
										.getGetVehicle().getVehicle())) {
							isValidBrand = isBrandNull(brand, jsonObj,
									jsonFinalObj);
							vin = getVehicleWrapper.getGetVehicle()
									.getVehicle().getVin();
							email = getVehicleWrapper.getGetVehicle()
									.getPerson().getEmail();
							personHashId = getVehicleWrapper.getGetVehicle()
									.getPerson().getPersonHashId();
							nickName = getVehicleWrapper.getGetVehicle()
									.getVehicle().getNickname();
							make = getVehicleWrapper.getGetVehicle()
									.getVehicle().getMake();
							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									/*if (isValidVin) {
										isValidNickName = isValidNickName(nickName, jsonObj, jsonFinalObj);
									}*/
									if (isValidVin) {
										isValidMake = isMakeNull(make, jsonObj,
												jsonFinalObj);
									}
								} else {
									LOG.info("Brand is neither Nissan nor Infiniti");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}
							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidNickName
									&& isValidMake) {

								if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
									getVehicleWrapper.getGetVehicle().setBrand(
											NISSAN);
								} else if (brand
										.equalsIgnoreCase(BRAND_INFINITI)) {
									getVehicleWrapper.getGetVehicle().setBrand(
											INFINITI);
								}

								// x566325 - Brand Segregation - send brand also

								/*
								 * validUser = userLocal.validateEmail(email,
								 * personHashId);
								 */

								validUser = userLocal.validateEmail(email,
										personHashId, brand);

								if (!Utility
										.isObjectNotNullorNotEmpty(validUser)) {
									Util.setFaultDataToJSON(
											jsonObj,
											jsonFinalObj,
											VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
									emailNotAvailable = true;
								} else if (Utility
										.isObjectNotNullorNotEmpty(validUser)) {

									if (getVehicleWrapper.getGetVehicle()
											.getVehicle().getMake()
											.equalsIgnoreCase(BRAND_NISSAN)

											) {

										userProfileId = validUser
												.getUserProfileId();
										vinMappedToSameUser = vehicleLocal
												.validateVehicleOwner(
														userProfileId, vin);
									} else if (getVehicleWrapper
											.getGetVehicle().getVehicle()
											.getMake()
											.equalsIgnoreCase(BRAND_INFINITI)

											) {
										userProfileId = validUser
												.getUserProfileId();
										vinMappedToSameUser = vehicleLocal
												.validateVehicleOwner(
														userProfileId, vin);
										LOG.info("Status of vehicle association is "
												+ vinMappedToSameUser
												+ "for the vin =" + vin);
									} else {
										jsonObj.accumulate(CODE,
												VEHICLE_MISMATCH_CODE);
										jsonObj.accumulate(MESSAGE,
												VEHICLE_MISMATCH_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_MISMATCH_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = FAILURE;
									}
									
									if (("sameUser")
											.equalsIgnoreCase(vinMappedToSameUser)) {
										LOG.info("Status of vehicle association is inside same user"

												+ "for the vin =" + vin);
										vehicleInfo = vehicleLocal
												.getVehicleInfo(vin);
										userVehicleInfo = vehicleLocal
												.getUserVehicleInfo(validUser
														.getUserProfileId(),
														vin);
										/*//x116202 - OS - 1218 - if not in BIDW, then call the mnl_vhcl_load table and get the values for this 3 fields and pass it to GPAS func to form the largeimage and smallimage url
										if(getVehicleWrapper.getGetVehicle().getVehicle().getModelCode()!= null &&
										   getVehicleWrapper.getGetVehicle().getVehicle().getOptionCode()!= null &&
										   getVehicleWrapper.getGetVehicle().getVehicle().getExteriorColorCode()!= null) {

											modelCode =  getVehicleWrapper.getGetVehicle().getVehicle().getModelCode();
											factoryOptionCode =  getVehicleWrapper.getGetVehicle().getVehicle().getOptionCode();
											exteriorColorCode = getVehicleWrapper.getGetVehicle().getVehicle().getExteriorColorCode();

											LOG.info("Values from Request:" +""+ "modelCode" + modelCode + "exteriorColorCode" + exteriorColorCode + "factoryOptionCode" + factoryOptionCode);

											if (modelCode.equals("") || exteriorColorCode.equals("") || factoryOptionCode.equals("") ){
												manualVehicleLookup = vehicleLocal.getVehicleInfoForTestVin(vehicleInfo.getVin());
												if(Utility.isObjectNotNullorNotEmpty(manualVehicleLookup)){
													modelCode = manualVehicleLookup.getModelCode();
													factoryOptionCode = manualVehicleLookup.getOptionCode();
													exteriorColorCode = manualVehicleLookup.getExteriorColorCode();
												}
												LOG.info("vin" + vehicleInfo.getVin() + " "+ "modelCode" + modelCode + "exteriorColorCode" + exteriorColorCode + "factoryOptionCode" + factoryOptionCode);

											} 
										}*/

										modelCode =  getVehicleWrapper.getGetVehicle().getVehicle().getModelCode();
										exteriorColorCode = getVehicleWrapper.getGetVehicle().getVehicle().getExteriorColorCode();
										factoryOptionCode =  getVehicleWrapper.getGetVehicle().getVehicle().getOptionCode();
										bodyStyleName = getVehicleWrapper.getGetVehicle().getVehicle().getBodyStyleName();
										modelName = getVehicleWrapper.getGetVehicle().getVehicle().getModelName();

										//x055765 - If the URL is not there in GPAS, Display Silhouette Image based on BodyStyleName and ModelName - starts
										if(Utility.isStringNotNullorNotEmpty(modelCode) && Utility.isStringNotNullorNotEmpty(exteriorColorCode) && Utility.isStringNotNullorNotEmpty(factoryOptionCode)) {

											// condition  to check if the vehicle year is >= 2016. if not set silhoutte images 
											if(Integer.parseInt( vehicleInfo.getModelYearNumber()) >= 2016) {

												LOG.info("Values from Request:" +""+ "modelCode" + modelCode + "exteriorColorCode" + exteriorColorCode + "factoryOptionCode" + factoryOptionCode);

												// x566325 - Update getVehicle API to
												// include Image URL's in the response
												largeImageUrl = vehicleLocal
														.getVehicleFunctionLargeImage1(modelCode,exteriorColorCode,factoryOptionCode);
												smallImageUrl = vehicleLocal
														.getVehicleFunctionSmallImage1(modelCode,exteriorColorCode,factoryOptionCode);
												LOG.info("Final valid BIDW imageurl " +largeImageUrl + "smallimage " +  smallImageUrl);
											} else {
												LOG.info("Vehicle Model Year is less that 2016 so setting silhoutte images:" + vehicleInfo.getModelYearNumber());
												largeImageUrl = "Not_Available";
												smallImageUrl = "Not_Available";

											}
											//check url check
											if(largeImageUrl.contains("exterior-.png") || largeImageUrl.equalsIgnoreCase("Not_Available")) {
												largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
												LOG.info("final silhoutte largeImageUrl" + largeImageUrl);

											} 
											if(smallImageUrl.contains("exterior-.png") || smallImageUrl.equalsIgnoreCase("Not_Available")) {

												smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
												LOG.info("final silhoutte smallImageUrl" + smallImageUrl);
											}


										}else {

											LOG.info("all the 5 values are null from BIDW are null & vin = " + vin);

											//check the owner portal vehicle table for ModelName
											ownerPortalVehicle = vehicleLocal.getModelNameUsingVin(vin);
											modelName = ownerPortalVehicle.getVehicleModelName();
											LOG.info("all the 5 values are null from BIDW are null & modeLName from DB: " + modelName);


											largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
											LOG.info("final silhoutte largeImageUrl using ModelName" + largeImageUrl);
											smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
											LOG.info("final silhoutte smallImageUrl using ModelName" + smallImageUrl);


										}

										//x055765 - If the URL is not there in GPAS, Display Silhoutte Image based on BodyStyleName and ModelName - ends

										// set jsonObj for success response
										jsonObj = setGetVehicleResponse(
												getVehicleWrapper,
												userVehicleInfo, vehicleInfo,
												jsonObj, largeImageUrl,
												smallImageUrl);
										result = SUCCESS;
									} else if (("otherUser")
											.equalsIgnoreCase(vinMappedToSameUser)) {
										LOG.info("Status of vehicle association is inside other user"

												+ "for the vin =" + vin);
										jsonObj.accumulate(CODE,
												VEHICLE_MISMATCH_CODE);
										jsonObj.accumulate(MESSAGE,
												VEHICLE_MISMATCH_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_MISMATCH_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = FAILURE;
									} else if (("notAvailable")
											.equalsIgnoreCase(vinMappedToSameUser)) {
										jsonObj.accumulate(CODE,
												VEHICLE_NOTAVAILABLE_VINANDUSER_CODE);
										jsonObj.accumulate(MESSAGE,
												VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = FAILURE;
									}

								}
							}

						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							LOG.info("Invalid json request");
							isvalidJSON=false;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
						isvalidJSON=false;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
					isvalidJSON=false;
				}
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
				isvalidJSON=false;
			}
			
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("Get vehicle JSON success response for the vin = " + vin
						+ jsonObj.toString());
				return Response.ok().status(200).entity(jsonObj.toString()).build();
			} else if (generalError || !isValidMake || !isValidPersonHashId || !isvalidJSON) {
				LOG.info("Inside else if generalError");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			// x566325 - Brand Segregation - Email not available in database case -
			// added emailNotAvailable condition
			else if (!isValidVin || !isValidBrand || !isValidEmail
					|| !isValidNickName || emailNotAvailable
					|| result.equalsIgnoreCase(FAILURE)) {
				LOG.info("Inside else if failure");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Get vehicle JSON unexpected failed response for the vin");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}	
			
		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid json Mapping Exception");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonParseException e) {
			

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
			
			
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid IOException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "General exception  during get vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		
	}

	/**
	 * @author x178099
	 * @use To get vehicle information of a user account
	 * @method-POST
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getVehicleSpecification")
	public Response getVehicleSpecification(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		LOG.info("Get Vehicle Specification JSON request" + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		String result = "";
		String vinMappedToSameUser = "";
		OwnerPortalUser validUser;
		OwnerPortalVehicle vehicleInfo;
		List<VehicleSpecification> vehicleSpecifications;
		List<Equipment> equipmentInfo;
		boolean isValidNickName = true;
		boolean isValidMake = true;
		boolean isValidModelCode = true;
		String userProfileId = "";
		String vin = "";
		String email = "";
		String make = "";
		String personHashId = "";
		String modelCode = "";
		boolean generalError = false;
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				GetVehicleSpecificationWrapper getVehicleWrapper = mapper
						.readValue(requestJson,
								GetVehicleSpecificationWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(getVehicleWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(getVehicleWrapper
							.getVehicleSpec())) {
						if (Utility.isObjectNotNullorNotEmpty(getVehicleWrapper
								.getVehicleSpec().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(getVehicleWrapper
										.getVehicleSpec().getVehicle())) {
							isValidBrand = isBrandNull(brand, jsonObj,
									jsonFinalObj);
							vin = getVehicleWrapper.getVehicleSpec()
									.getVehicle().getVin();
							email = getVehicleWrapper.getVehicleSpec()
									.getPerson().getEmail();
							personHashId = getVehicleWrapper.getVehicleSpec()
									.getPerson().getPersonHashId();
							modelCode = getVehicleWrapper.getVehicleSpec()
									.getVehicle().getModelCode();
							make = getVehicleWrapper.getVehicleSpec()
									.getVehicle().getMake();

							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidMake = isMakeNull(make, jsonObj,
												jsonFinalObj);
									}

								} else {
									LOG.info("Brand is neither Nissan nor Infiniti");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}
							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId) {

								if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
									getVehicleWrapper.getVehicleSpec()
									.setBrand(NISSAN);
								} else if (brand
										.equalsIgnoreCase(BRAND_INFINITI)) {
									getVehicleWrapper.getVehicleSpec()
									.setBrand(INFINITI);
								}

								// x566325 - Brand Segregation - send brand also
								/*
								 * validUser = userLocal.validateEmail(email,
								 * personHashId);
								 */

								validUser = userLocal.validateEmail(email,
										personHashId, brand);

								if (!Utility
										.isObjectNotNullorNotEmpty(validUser)) {
									Util.setFaultDataToJSON(
											jsonObj,
											jsonFinalObj,
											VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
									isValidEmail = false;
								} else if (Utility
										.isObjectNotNullorNotEmpty(validUser)) {
									if (Utility
											.isStringNotNullorNotEmpty(getVehicleWrapper
													.getVehicleSpec()
													.getVehicle().getMake())) {

										if (getVehicleWrapper.getVehicleSpec()
												.getVehicle().getMake()
												.equalsIgnoreCase(BRAND_NISSAN)
												|| getVehicleWrapper
												.getVehicleSpec()
												.getVehicle()
												.getMake()
												.equalsIgnoreCase(
														BRAND_INFINITI)) {

											userProfileId = validUser
													.getUserProfileId();
											vinMappedToSameUser = vehicleLocal
													.validateVehicleOwner(
															userProfileId, vin);
											LOG.info("Status of vehicle association is "
													+ vinMappedToSameUser

													+ "for the vin =" + vin);
										} else {
											jsonObj.accumulate(CODE,
													VALIDATION_FAILED_CODE);
											jsonObj.accumulate(MESSAGE,
													VALIDATION_FAILED_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_INVALID_BRAND_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
											result = FAILURE;
										}
									}
									LOG.info("Status of vehicle association is "
											+ vinMappedToSameUser
											+ "for the vin =" + vin);
									if (("sameUser")
											.equalsIgnoreCase(vinMappedToSameUser)) {
										LOG.info("Status of vehicle association is inside same user"

												+ "for the vin =" + vin);
										vehicleInfo = vehicleLocal
												.getVehicleInfo(vin);

										if (vehicleInfo.getVehicleModelCode()
												.equalsIgnoreCase(modelCode)) {
											vehicleSpecifications = vehicleLocal
													.getVehicleSpecifications(modelCode);
											equipmentInfo = vehicleLocal
													.getEquipmentInfo(modelCode);

											// set jsonObj for success response

											if (Utility
													.isObjectNotNullorNotEmpty(vehicleSpecifications)
													&& Utility
													.isObjectNotNullorNotEmpty(equipmentInfo)) {
												LOG.info("size of vehicleSpecifications="
														+ vehicleSpecifications
														.size());

												LOG.info(" size of equipmentInfo ="
														+ equipmentInfo.size());
												jsonObj = setGetVehicleSpecificationResponse(
														getVehicleWrapper,
														vehicleSpecifications,
														equipmentInfo, jsonObj);
												result = SUCCESS;
											} else {
												Util.setFaultDataToJSON(
														jsonObj,
														jsonFinalObj,
														VEHICLE_SPEC_UNAVAILABLE_CODE,
														VEHICLE_SPEC_UNAVAILABLE_MESSAGE,
														VEHICLE_SPECS_NOTAVAILABLE_DESCRIPTION);
												isValidModelCode = false;
											}
										} else {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VEHICLE_SPEC_UNAVAILABLE_CODE,
													VEHICLE_SPEC_UNAVAILABLE_MESSAGE,
													VEHICLE_SPECS_NOTAVAILABLE_DESCRIPTION);
											isValidModelCode = false;
										}
									} else if (("otherUser")
											.equalsIgnoreCase(vinMappedToSameUser)) {
										LOG.info("Status of vehicle association is inside other user"

												+ "for the vin =" + vin);
										jsonObj.accumulate(CODE,
												VEHICLE_MISMATCH_CODE);
										jsonObj.accumulate(MESSAGE,
												VEHICLE_MISMATCH_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_MISMATCH_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = FAILURE;
									} else if (("notAvailable")
											.equalsIgnoreCase(vinMappedToSameUser)) {
										jsonObj.accumulate(CODE,
												VEHICLE_UNAVAILABLE_VIN_CODE);
										jsonObj.accumulate(MESSAGE,
												VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = FAILURE;
									}

								}
							}

						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							LOG.debug("Request is null and response is"
									+ jsonFinalObj.toString());
							generalError = true;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());
						generalError = true;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					generalError = true;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				generalError = true;
			}
		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "Json exception  during get vehicle specification : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "JsonParseException during get vehicle specification : ",
					e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to Fuse for the vin ="
							+ vin
							+ "with email ="
							+ email
							+ "is"
							+ jsonFinalObj.toString()
							+ "JsonMappingException  during get vehicle specification : ",
							e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "General exception  during  get vehicle specification: ",
					e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {
			LOG.info("Get vehicle specification JSON success response for the vin = "
					+ vin + jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();
		} else if (generalError || !isValidMake || !isValidPersonHashId) {

			LOG.info("Response to Fuse on get vehicle specification for the vin ="
					+ vin
					+ "with email ="
					+ email
					+ "is"
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidVin || !isValidBrand || !isValidEmail
				|| !isValidNickName || result.equalsIgnoreCase(FAILURE)
				|| !isValidModelCode) {
			LOG.info("Response to Fuse on get vehicle specification for the vin ="
					+ vin
					+ "with email ="
					+ email
					+ "is"
					+ jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Get vehicle specification JSON unexpected failed response for teh vin ="
					+ vin + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	/**
	 * @author x178099
	 * @use To save vehicle in user account
	 * @method-POST
	 * @header brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/saveVehicle")
	public Response saveVehicle(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Save Vehicle JSON request is " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isValidNickName = true;
		String response = "";
		OwnerPortalUser validUser;
		OwnerPortalVehicle vehicleInfo;
		String vin = "";
		String email = "";
		String personHashId = "";
		String nickName = "";
		boolean generalError = false;
		String result = "";
		String largeImageUrl = "";
		String smallImageUrl = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				SaveVehicleWrapper saveVehicleWrapper = mapper.readValue(
						requestJson, SaveVehicleWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(saveVehicleWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(saveVehicleWrapper
							.getSaveVehicle())) {
						if (Utility
								.isObjectNotNullorNotEmpty(saveVehicleWrapper
										.getSaveVehicle().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(saveVehicleWrapper
										.getSaveVehicle().getVehicle())) {
							isValidBrand = isBrandNull(brand, jsonObj,
									jsonFinalObj);
							vin = saveVehicleWrapper.getSaveVehicle()
									.getVehicle().getVin();
							email = saveVehicleWrapper.getSaveVehicle()
									.getPerson().getEmail();
							personHashId = saveVehicleWrapper.getSaveVehicle()
									.getPerson().getPersonHashId();
							nickName = saveVehicleWrapper.getSaveVehicle()
									.getVehicle().getNickname();
							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidNickName = isValidNickName(
												nickName, jsonObj, jsonFinalObj);
									}
								} else {
									LOG.info("Brand is neither Nissan nor Infiniti");
									jsonObj.accumulate(CODE,
											VALIDATION_FAILED_CODE);
									jsonObj.accumulate(MESSAGE,
											VALIDATION_FAILED_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
									isValidBrand = false;
								}
							}
							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidNickName) {

								if (Utility
										.isStringNotNullorNotEmpty(saveVehicleWrapper
												.getSaveVehicle().getVehicle()
												.getMake())
										&& saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getMake()
										.equalsIgnoreCase(BRAND_NISSAN)
										|| saveVehicleWrapper
										.getSaveVehicle()
										.getVehicle()
										.getMake()
										.equalsIgnoreCase(
												BRAND_INFINITI)) {
									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										saveVehicleWrapper.getSaveVehicle()
										.setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										saveVehicleWrapper.getSaveVehicle()
										.setBrand(INFINITI);
									}

									// x566325 - Brand Segregation - send brand
									// also
									/*
									 * validUser =
									 * userLocal.validateEmail(email,
									 * personHashId);
									 */

									validUser = userLocal.validateEmail(email,
											personHashId, brand);

									LOG.info("Inside save vehicle JSON success response for the vin"
											+ vin);

									if (!Utility
											.isObjectNotNullorNotEmpty(validUser)) {
										Util.setFaultDataToJSON(
												jsonObj,
												jsonFinalObj,
												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
										isValidEmail = false;
									}

									if (Utility
											.isObjectNotNullorNotEmpty(validUser)) {
										LOG.info("Inside valid user JSON success response for the vin"
												+ vin);

										response = vehicleLocal.saveVehicle(
												saveVehicleWrapper, validUser);
									}

								}
							}

							// x566325 - Update saveVehicle API to include Image
							// URL's in the response
							vehicleInfo = vehicleLocal.getVehicleInfo(vin);
							largeImageUrl = vehicleLocal
									.getVehicleFunctionLargeImage(vehicleInfo);
							smallImageUrl = vehicleLocal
									.getVehicleFunctionSmallImage(vehicleInfo);

							LOG.info("Response for Save Vehicle for the vin "
									+ vin + " is" + response);
							if (Utility.isStringNotNullorNotEmpty(response)
									&& response.equalsIgnoreCase(SUCCESS)) {
								LOG.info("Inside save vehicle JSON success response for the vin"
										+ vin);
								jsonObj.accumulate("vin", saveVehicleWrapper
										.getSaveVehicle().getVehicle().getVin());

								if (saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getModelName()
										.contains("LEAF")
										|| saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getModelName()
										.contains("LEF")) {
									jsonObj.accumulate("electric", true);
								} else {
									jsonObj.accumulate("electric", false);
								}
								jsonObj.accumulate("vehicleName",
										saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getModelName());
								jsonObj.accumulate("detailedVehicleName",
										saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getModelName());
								if (saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getNickname() == null
										|| saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getNickname().equals("")) {

									jsonObj.accumulate("nickname",
											saveVehicleWrapper.getSaveVehicle()
											.getVehicle()
											.getModelName());
								} else {
									jsonObj.accumulate("nickname",
											saveVehicleWrapper.getSaveVehicle()
											.getVehicle().getNickname());
								}

								jsonObj.accumulate("modelIdentifier",
										saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getModelCode());
								jsonObj.accumulate("colour", saveVehicleWrapper
										.getSaveVehicle().getVehicle()
										.getExteriorColorName());
								jsonObj.accumulate("mileage",
										saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getMileage());
								jsonObj.accumulate("averageMileage",
										saveVehicleWrapper.getSaveVehicle()
										.getVehicle()
										.getAverageMileage());

								if (!(saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getPreferredDealer() == null || saveVehicleWrapper
										.getSaveVehicle().getVehicle()
										.getPreferredDealer().equals(""))) {

									//									jsonObj.accumulate(DEALERID, "NNA"
									//											+ saveVehicleWrapper
									//													.getSaveVehicle()
									//													.getVehicle().getDealerId());
									jsonObj.accumulate(PREFERRED_DEALER, "NNA"
											+ saveVehicleWrapper
											.getSaveVehicle()
											.getVehicle().getPreferredDealer());
								}
								// Added by 455144 for model year change

								jsonObj.accumulate("modelYear",
										saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getModelYear());

								// x566325 - Update saveVehicle API to include
								// Image URL's in the response
								jsonObj.accumulate("largeImage", largeImageUrl);
								jsonObj.accumulate("smallImage", smallImageUrl);

								result = "success";

							} else if (Utility
									.isStringNotNullorNotEmpty(response)
									&& ("sameUser").equalsIgnoreCase(response)) {
								LOG.info("same user");
								jsonObj.accumulate(CODE, DUPLICATE_VIN_CODE);
								jsonObj.accumulate(MESSAGE,
										VEHICLE_DUPLICATE_USER_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_DUPLICATE_USER_DESCRIPTION);
								jsonFinalObj.accumulate("fault", jsonObj);

								result = "failure";

							} else if (Utility
									.isStringNotNullorNotEmpty(response)
									&& ("otherUser").equalsIgnoreCase(response)) {
								LOG.info("otherUser");
								jsonObj.accumulate(CODE, VEHICLE_MISMATCH_CODE);
								jsonObj.accumulate(MESSAGE,
										VEHICLE_INVALID_USER_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_INVALID_USER_DESCRIPTION);
								jsonFinalObj.accumulate("fault", jsonObj);

								result = "failure";

							}
						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							LOG.debug("Request is null");
							generalError = true;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null");
						generalError = true;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null");
					generalError = true;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				generalError = true;
			}
		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + " with email ="
					+ email + "is " + jsonFinalObj.toString()
					+ "Json exception  during update vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to Fuse in JsonParseException for the vin ="
							+ vin + " with email =" + email + "is "
							+ jsonFinalObj.toString()
							+ "JsonParseException  during update vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to Fuse in JsonMappingException  for the vin ="
							+ vin + " with email =" + email + "is "
							+ jsonFinalObj.toString()
							+ "JsonMappingException  during update vehicle : ",
							e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse in IOException for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "IOException  during update vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse in Exception for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "General exception  during update vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {
			LOG.info("Save vehicle JSON success response for the vin =" + vin
					+ " with email =" + email + "is " + jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();
		} else if (!isValidVin || !isValidBrand
				|| result.equalsIgnoreCase(FAILURE) || !isValidNickName
				|| !isValidEmail) {

			LOG.info("Save vehicle JSON failed response for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (generalError || !isValidPersonHashId) {
			LOG.info("Save vehicle JSON failed response for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Save vehicle JSON unexpected failed response for the vin ="
					+ vin
					+ " with email ="
					+ email
					+ "is "
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	/**
	 * @author x178099
	 * @use To validate CDIID if present in Helios DB and persist if null
	 *      previously
	 * @method-POST
	 * @header brand
	 * @param cdiid
	 * @param email
	 * @param personhashid
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/validatecdiid")
	public Response validatecdiid(@HeaderParam("Brand") String brand,
		String requestJson) throws JSONException {
		LOG.info("Validate cdiid JSON request is " + requestJson);
		boolean isValidBrand = true;
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidCdiid = true;
		boolean isValidEmailId = true;
		boolean isValidPersonHashId = true;
		OwnerPortalUser validEmail;
		String response = "";
		String cdiid = "";
		String email = "";
		String personHashId = "";
		boolean generalError = false;

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {

				ValidateCdiidWrapper validatecdiidwrapper = mapper.readValue(
						requestJson, ValidateCdiidWrapper.class);

				if (Utility.isObjectNotNullorNotEmpty(validatecdiidwrapper)) {

					if (Utility.isObjectNotNullorNotEmpty(validatecdiidwrapper
							.getValidateCustomerSystemId())) {

						if (Utility
								.isStringNotNullorNotEmpty(validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getCdiid())
								|| Utility
								.isStringNotNullorNotEmpty(validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getEmail())
								|| Utility
								.isStringNotNullorNotEmpty(validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getPersonHashId())) {

							isValidBrand = isBrandNull(brand, jsonObj,
									jsonFinalObj);
							if (Utility
									.isStringNotNullorNotEmpty(validatecdiidwrapper
											.getValidateCustomerSystemId()
											.getEmail())) {
								email = validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getEmail();
							}
							if (Utility
									.isStringNotNullorNotEmpty(validatecdiidwrapper
											.getValidateCustomerSystemId()
											.getPersonHashId())) {
								personHashId = validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getPersonHashId();
							}
							if (Utility
									.isStringNotNullorNotEmpty(validatecdiidwrapper
											.getValidateCustomerSystemId()
											.getCdiid())) {
								cdiid = validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getCdiid();
								LOG.info("cdiid = " + cdiid);
							}
							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;

									isValidCdiid = isCDIIDNull(cdiid, jsonObj,
											jsonFinalObj);

									if (isValidCdiid) {
										isValidEmailId = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidCdiid) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidCdiid && isValidEmailId
											&& isValidBrand
											&& isValidPersonHashId) {

										// x566325 - Brand Segregation - send
										// brand also
										/*
										 * validEmail = userLocal.validateEmail(
										 * email, personHashId);
										 */
										validEmail = userLocal.validateEmail(
												email, personHashId, brand);
										if (!Utility
												.isObjectNotNullorNotEmpty(validEmail)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
											isValidEmailId = false;
										} else {

											response = userLocal
													.validateCdiid(validatecdiidwrapper);

										}

									}

								} else {
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}
						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							LOG.debug("CDIID/Email/PersonHashId is null and response is"
									+ jsonFinalObj.toString());
							generalError = true;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());
						generalError = true;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					generalError = true;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				generalError = true;
			}
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the cdiid =" + cdiid
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "JsonParseException in validate cdiid : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the cdiid =" + cdiid
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "JsonMappingException in validate cdiid : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the cdiid =" + cdiid
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "IOException in validate cdiid : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (Utility.isStringNotNullorNotEmpty(response)
				&& response.equalsIgnoreCase(SUCCESS)) {
			LOG.info("Validate CDIID JSON success response for the cdiid"
					+ cdiid);
			return Response.ok().status(200).build();
		} else if (response != null && response.equalsIgnoreCase(FAILURE)) {

			jsonObj.accumulate(CODE, VEHICLE_INVALID_CDIIDOWNER_CODE);
			jsonObj.accumulate(MESSAGE, VEHICLE_INVALID_CDIIDOWNER_MESSAGE);
			jsonObj.accumulate(DESCRIPTION,
					VEHICLE_INVALID_CDIIDOWNER_DESCRIPTION);
			jsonFinalObj.accumulate("fault", jsonObj);
			LOG.info("Validate CDIID JSON failure response for the cdiid"
					+ cdiid + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} else if (Utility.isStringNotNullorNotEmpty(response)
				&& response.equalsIgnoreCase(AVAILABLE)) {

			jsonObj.accumulate(CODE, VEHICLE_CDIID_AVAILABLE_CODE);
			jsonObj.accumulate(MESSAGE, VEHICLE_CDIID_AVAILABLE_MESSAGE);
			jsonObj.accumulate(DESCRIPTION, VEHICLE_CDIID_AVAILABLE_DESCRIPTION);
			jsonFinalObj.accumulate("fault", jsonObj);
			LOG.info(" Validate CDIID JSON cdiid available response for the cdiid"
					+ cdiid + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} else if (!isValidBrand || !isValidEmailId) {

			LOG.info("Validate CDIID JSON failed response for the cdiid"
					+ cdiid + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidCdiid || generalError || !isValidPersonHashId) {
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);

			LOG.info("Validate CDIID JSON unexpected failed response for the cdiid"
					+ cdiid + jsonFinalObj.toString());

			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}

	/**
	 * @author x796314
	 * @use To validate data while add, update, view and delete vehicle
	 * @method-POST
	 * @param brand
	 * @param vin
	 *            , jwttoken for username,requestedservice
	 * @return user details
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/validatedata")
	public Response validateData(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		
		
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonlastObj = new JSONObject();
		ValidateDataWrapper validateDataWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidVin = true;
		boolean isValidMileage = true;
		boolean success = false;
		boolean isValidBrand = true;
		boolean isvalidvinandbrand = true;
		boolean isValidNickName = true;
		boolean isValidContentType = true;
		boolean isValidFileName = true;
		boolean isValidContent = true;
		boolean vinNotAvailabe = false;
		boolean isValidRequestService = true;
		boolean isValidId = true;
		boolean invalidjwt = true;
		boolean idNotAvailable = true;
		boolean isValidAction = true;
		boolean isValidUser=true;
		boolean isValidMessageId = true;
		String userName = "";
		byte[] content = null;
		OwnerPortalUser validUser = null;
		List<OwnerPortalUserPhone> ownerPortalUserPhone = null;
		VehicleCarwings vehicleCarwings = null;
		OwnerPortalUser driverVehicleInfo = null;
		OwnerPortalUser isUserEmailValid = null;
		OwnerPortalVehicle vehicleInfo = null;
		OwnerPortalVehicle vehicleInfoCheck = null;
		OwnerPortalUserVehicle userVehicleInfo = null;
		State state = null;
		MobileProvider mobileProvider = null;
		String vinMappedToSameUser = "";
		String vinMappedToUser = "";
		String vinValidationForUnsubscription = "";
		String vin = "";
		String id = "";
		String action = "";
		/*String istestvinavailable="";*/
		String brandcheck="";
		boolean generalError = false;
		boolean emailNotAvailable = false;

		Set<String> telematicsInfo = null;
		Set<String> telematicsInfoCheck = null;
		String vinValidation = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
			
				validateDataWrapper = mapper.readValue(requestJson,
						ValidateDataWrapper.class);
				
				if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper)) {
					
					if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper
							.getValidateData())) {

						String jwtToken = validateDataWrapper.getValidateData()
								.getJwt();

						vin = validateDataWrapper.validateData.getVin();

						Integer mileage = validateDataWrapper.validateData
								.getMileage();

						Integer averageMileage = validateDataWrapper.validateData
								.getAverageMileage();

						String nickName = validateDataWrapper.validateData
								.getNickname();

						String fileName = validateDataWrapper.validateData
								.getFileName();
						String contentType = validateDataWrapper.validateData
								.getContentType();
						content = validateDataWrapper.validateData.getContent();
						action = validateDataWrapper.validateData.getAction();
						id = validateDataWrapper.validateData.getId();

						isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
						if (isValidBrand) {

							if (brand.equalsIgnoreCase(BRAND_INFINITI)
									|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
								isValidBrand = true;
								isValidVin = isVinNull(vin, jsonObj,
										jsonFinalObj);

								if (isValidVin) {
									isValidVin = isVinValid(vin, jsonObj,
											jsonFinalObj);
								}
								if (isValidVin) {

									if (Utility
											.isStringNotNullorNotEmpty(validateDataWrapper
													.getValidateData()
													.getRequestedservice())) {
										if (!((validateDataWrapper
												.getValidateData()
												.getRequestedservice()
												.equalsIgnoreCase(ADD_VEHICLE))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(UPDATE_VEHICLE))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(GET_VEHICLE))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(DELETE_VEHICLE))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(CONNECTED_SERVICES))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(SERVICE_CONTRACT))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(RECALL_SERVICE))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(UPLOAD_DOCUMENT))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(VEHICLE_SPECIFICATION))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(SERVICE_HISTORY))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(CONNECTED_SERVICES_DETAIL))
												|| (validateDataWrapper
														.getValidateData()
														.getRequestedservice()
														.equalsIgnoreCase(MESSAGE_DETAILS)) || (validateDataWrapper
																.getValidateData()
																.getRequestedservice()
																.equalsIgnoreCase(MESSAGE_USER_ACTION)))) {
											LOG.debug("Request Service is null");

											Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
													GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
													INVALID_JSON_REQUEST_DESCRIPTION);
											LOG.info("Invalid json request");

											isValidRequestService = false;

										} else {
											if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															UPDATE_VEHICLE)) {

												if (validateDataWrapper
														.getValidateData()
														.getMileage() != null) {
													isValidMileage = isValidMileage(
															mileage, jsonObj,
															jsonFinalObj);
												}

												if (validateDataWrapper
														.getValidateData()
														.getAverageMileage() != null) {
													isValidMileage = isValidAverageMileage(
															averageMileage,
															jsonObj,
															jsonFinalObj);

												}
												if (Utility
														.isStringNotNullorNotEmpty(validateDataWrapper
																.getValidateData()
																.getNickname())) {

													isValidNickName = isValidNickName(
															nickName, jsonObj,
															jsonFinalObj);
												}

											} else if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															ADD_VEHICLE)) {

												if (Utility
														.isStringNotNullorNotEmpty(validateDataWrapper
																.getValidateData()
																.getNickname())) {
													isValidNickName = isValidNickName(
															nickName, jsonObj,
															jsonFinalObj);
												}
											} else if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															UPLOAD_DOCUMENT)) {
												LOG.info("content type"
														+ contentType);

												isValidContentType = isValidContentType(
														contentType, jsonObj,
														jsonFinalObj);

												//LOG.info("content" + content);

												isValidContent = isValidContent(
														content, contentType,
														jsonObj, jsonFinalObj);

												isValidFileName = isValidFileName(
														fileName, jsonObj,
														jsonFinalObj);

											} else if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															CONNECTED_SERVICES)) {
												LOG.info("JWT = " + jwtToken);
												userName=Util.getUsernameFromJWT(jwtToken);
												LOG.info ("EmailId from validatedata = " + userName);
												if(!validateUser(vin,userName)){
													System.out.println(" VIN is not associated to the user  ");
													jsonObj.accumulate(CODE,
															VEHICLE_MISMATCH_CODE);
													jsonObj.accumulate(MESSAGE,
															VEHICLE_MISMATCH_MESSAGE);
													jsonObj.accumulate(
															DESCRIPTION,
															VEHICLE_MISMATCH_DESCRIPTION);
													jsonFinalObj.accumulate(
															"fault", jsonObj);
													isValidUser=false;
												}

											}else if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															CONNECTED_SERVICES_DETAIL)) {
												LOG.info("JWT = " + jwtToken);
												userName=Util.getUsernameFromJWT(jwtToken);
												LOG.info ("EmailId from validatedata = " + userName);
												if(!validateUser(vin,userName)){
													LOG.info(" VIN is not associated to the user  ");
													jsonObj.accumulate(CODE,
															VEHICLE_MISMATCH_CODE);
													jsonObj.accumulate(MESSAGE,
															VEHICLE_MISMATCH_MESSAGE);
													jsonObj.accumulate(
															DESCRIPTION,
															VEHICLE_MISMATCH_DESCRIPTION);
													jsonFinalObj.accumulate(
															"fault", jsonObj);
													isValidUser=false;
												}else{

													// created for connected service
													// detail by vp00481845
													LOG.info("Connected Service detail "
															+ id);


													if (Utility
															.isObjectNotNullorNotEmpty(id)) {

														List<String> serviceNames = new ArrayList<String>();

														// Validation added to check the connected service in connected service details

														vehicleInfoCheck = vehicleLocal.getVehicleInfo(validateDataWrapper.getValidateData().getVin());
														telematicsInfo = vehicleLocal.getTelematicsInfo(vehicleInfoCheck);
														LOG.info("telematicsInfo CONNECTED_SERVICES_DETAIL"
																+ telematicsInfo);
														if((telematicsInfo.contains(CW) || telematicsInfo.contains(EV))&& telematicsInfo.contains(L2)){
															serviceNames.add(NISSANCONNECTEV);
															serviceNames.add(NISSANCONNECTSERVICES);
														}
														else if(telematicsInfo.contains(NS)
																|| telematicsInfo.contains(N2)
																|| telematicsInfo.contains(N3)
																|| telematicsInfo.contains(N4)
																|| telematicsInfo.contains(M6) 
																|| telematicsInfo.contains(M7)
																|| telematicsInfo.contains(M8)
																|| telematicsInfo.contains(M9)
																|| telematicsInfo.contains(E1)
																|| telematicsInfo.contains(X2)
																|| telematicsInfo.contains(X3)){													
															serviceNames.add(NISSANCONNECTSERVICES);	
														}
														else if(telematicsInfo.contains(IC) || telematicsInfo.contains(X1)){
															serviceNames.add(INFINFITICONNECTION);
														}
														else if(telematicsInfo.contains(NC)){
															serviceNames.add(NISSANCONNECTNAVIGATION);
														}
														else if(telematicsInfo.contains(CW) || telematicsInfo.contains(EV) || telematicsInfo.contains(X4)){
															serviceNames.add(NISSANCONNECTEV);
														}
														else if(telematicsInfo.contains(I2)
																|| telematicsInfo.contains(I3)|| telematicsInfo.contains(I4) || telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5)
																 || telematicsInfo.contains(X5)  || telematicsInfo.contains(X6)  || telematicsInfo.contains(X7)){
															serviceNames.add(INFINITIINTOUCHSERVICES);
														}

														// Validation end to check the connected service 													

														/*serviceNames
															.add(NISSANCONNECTMOBILEAPPS);
													serviceNames
															.add(NISSANCONNECTSERVICES);
													serviceNames
															.add(INFINFITIINTOUCHAPPS);
													serviceNames
															.add(INFINFITICONNECTION);
													serviceNames
															.add(NISSANCONNECTNAVIGATION);
													serviceNames
															.add(NISSANCONNECTEV);
													serviceNames
															.add(INFINITIINTOUCHSERVICES);*/

														if (!serviceNames
																.contains(id)) {
															LOG.info("Connected Services Detail - ID is not available ["
																	+ id + "]");
															jsonObj.accumulate(
																	CODE,
																	CONNECTED_SERVICE_DETAIL_ERROR_CODE);
															jsonObj.accumulate(
																	MESSAGE,
																	INVALID_ID_ERROR_MESSAGE);
															jsonObj.accumulate(
																	DESCRIPTION,
																	INVALID_ID_ERROR_DESCRIPTION);
															jsonFinalObj
															.accumulate(
																	"fault",
																	jsonObj);
															idNotAvailable = false;
														}

													} else {
														LOG.info("Connected Services Detail - ID is null ["
																+ id + "]");
														jsonObj.accumulate(CODE,
																CONNECTED_SERVICE_DETAIL_ERROR_CODE);
														jsonObj.accumulate(MESSAGE,
																INVALID_ID_ERROR_MESSAGE);
														jsonObj.accumulate(
																DESCRIPTION,
																INVALID_ID_ERROR_DESCRIPTION);
														jsonFinalObj.accumulate(
																"fault", jsonObj);
														isValidId = false;
													}

												}

											} else if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															MESSAGE_DETAILS)) {
												LOG.info ("JWT = " + jwtToken);
												userName=Util.getUsernameFromJWT(jwtToken);
												LOG.info ("EmailId from validateData = "+userName);
												if(!validateUser(vin,userName)){
													LOG.info(" message user action invalid message id  ");

													jsonObj.accumulate(CODE,
															VEHICLE_MISMATCH_CODE);
													jsonObj.accumulate(MESSAGE,
															VEHICLE_MISMATCH_MESSAGE);
													jsonObj.accumulate(
															DESCRIPTION,
															VEHICLE_MISMATCH_DESCRIPTION);
													jsonFinalObj.accumulate(
															"fault", jsonObj);
													isValidUser=false;

												}else{

													LOG.info("Message Details "
															+ id);

													id = validateDataWrapper.validateData
															.getMessageId();
													if (Utility
															.isObjectNotNullorNotEmpty(id)) {

														List<String> serviceNames = new ArrayList<String>();

														// Validation added to check the message id passed for the message details 

														vehicleInfoCheck = vehicleLocal.getVehicleInfo(validateDataWrapper.getValidateData().getVin());
														telematicsInfo = vehicleLocal.getTelematicsInfo(vehicleInfoCheck);

														if((telematicsInfo.contains(CW) || telematicsInfo.contains(EV))&& telematicsInfo.contains(L2)){
															serviceNames.add(NISSANCONNECTEV_MSG_ID);
															serviceNames.add(NISSANCONNECTSERVICES_MSG_ID);
														}
														else if(telematicsInfo.contains(NS)
																|| telematicsInfo.contains(N2)
																|| telematicsInfo.contains(N3)
																|| telematicsInfo.contains(N4)
																|| telematicsInfo.contains(M6) 
																|| telematicsInfo.contains(M7)
																|| telematicsInfo.contains(M8)
																|| telematicsInfo.contains(M9)
																
																|| telematicsInfo.contains(E1)) {
															serviceNames.add(NISSANCONNECTSERVICES_MSG_ID);
														}
														else if(telematicsInfo.contains(IC)){
															serviceNames.add(INFINFITICONNECTION_MSG_ID);
														}
														else if(telematicsInfo.contains(NC)){
															serviceNames.add(NISSANCONNECTNAVIGATION_MSG_ID);
														}
														else if(telematicsInfo.contains(CW) || telematicsInfo.contains(EV)){
															serviceNames.add(NISSANCONNECTEV_MSG_ID);
														}
														else if(telematicsInfo.contains(I2) || telematicsInfo.contains(I3)|| telematicsInfo.contains(I4) || telematicsInfo.contains(I8)|| telematicsInfo.contains(I9) || telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5)){
															serviceNames.add(INFINITIINTOUCHSERVICES_MSG_ID);
														}

														//validation end for message id check 
														serviceNames
														.add(COMMON_MSG_ID);

														if (!serviceNames
																.contains(id)) {
															LOG.info("Message Details - ID is not available ["
																	+ id + "]");
															jsonObj.accumulate(
																	CODE,
																	MESSAGE_DETAILS_ERROR_CODE);
															jsonObj.accumulate(
																	MESSAGE,
																	INVALID_MESSAGE_ID_ERROR_MESSAGE);
															jsonObj.accumulate(
																	DESCRIPTION,
																	INVALID_MESSAGE_ID_ERROR_DESCRIPTION);
															jsonFinalObj
															.accumulate(
																	"fault",
																	jsonObj);
															idNotAvailable = false;
														}
													} else {
														LOG.info("Message Details - ID is null ["
																+ id + "]");
														jsonObj.accumulate(CODE,
																MESSAGE_DETAILS_ERROR_CODE);
														jsonObj.accumulate(MESSAGE,
																INVALID_MESSAGE_ID_ERROR_MESSAGE);
														jsonObj.accumulate(
																DESCRIPTION,
																INVALID_MESSAGE_ID_ERROR_DESCRIPTION);
														jsonFinalObj.accumulate(
																"fault", jsonObj);
														isValidId = false;
													}
												}

											} else if (validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(
															MESSAGE_USER_ACTION)) {
												
												LOG.info ("JWT = "+jwtToken);
												userName=Util.getUsernameFromJWT(jwtToken);
												LOG.info ("EmailId from Validate Data =  "+userName);
												if(!validateUser(vin,userName)){
													LOG.info ("IF>>>>>>>>>>>>>>>>1");
													jsonObj.accumulate(CODE,
															VEHICLE_MISMATCH_CODE);
													jsonObj.accumulate(MESSAGE,
															VEHICLE_MISMATCH_MESSAGE);
													jsonObj.accumulate(
															DESCRIPTION,
															VEHICLE_MISMATCH_DESCRIPTION);
													jsonFinalObj.accumulate(
															"fault", jsonObj);
													isValidUser=false;


												}else{
													id = validateDataWrapper.validateData
															.getId();
													LOG.info("Message user action "+ id);
													if (Utility.isObjectNotNullorNotEmpty(id)) {

														List<String> messageList = new ArrayList<String>();
														messageList.add(COMMON_MSG_ID);
														if (messageList.contains(id)) {
															if (!action.equalsIgnoreCase("dismiss")) {
																jsonObj.accumulate(
																		CODE,
																		MESSAGE_USER_ACTION_ERROR_CODE);
																jsonObj.accumulate(
																		MESSAGE,
																		INVALID_ACTION_ERROR_MESSAGE);
																jsonObj.accumulate(
																		DESCRIPTION,
																		INVALID_ACTION_ERROR_DESCRIPTION
																		+ " "
																		+ action);
																jsonFinalObj
																.accumulate(
																		"fault",
																		jsonObj);

																isValidAction = false;
															}
														} else {
															vehicleInfoCheck = vehicleLocal
																	.getVehicleInfo(validateDataWrapper
																			.getValidateData()
																			.getVin());
															telematicsInfoCheck = vehicleLocal
																	.getTelematicsInfo(vehicleInfoCheck);

															LOG.info("Message user action : Telematics Info: "	+ telematicsInfoCheck);
															if(validateMessageIdForMessageUserAction(id,telematicsInfoCheck)){
																isValidMessageId =true;

																if (!action.equalsIgnoreCase("accept")
																		&& !action.equalsIgnoreCase("decline")
																		&& !action.equalsIgnoreCase("dismiss")) {
																	LOG.info("Message user action - ID is not available ["
																			+ id + "]");
																	jsonObj.accumulate(
																			CODE,
																			MESSAGE_USER_ACTION_ERROR_CODE);
																	jsonObj.accumulate(
																			MESSAGE,
																			INVALID_ACTION_ERROR_MESSAGE);
																	jsonObj.accumulate(
																			DESCRIPTION,
																			INVALID_ACTION_ERROR_DESCRIPTION
																			+ " "
																			+ action);
																	jsonFinalObj
																	.accumulate(
																			"fault",
																			jsonObj);

																	isValidAction = false;
																}
															}else{
																jsonObj.accumulate(CODE,
																		MESSAGE_USER_ACTION_ERROR_CODE);
																jsonObj.accumulate(MESSAGE,
																		INVALID_MESSAGE_ID_ERROR_MESSAGE);
																jsonObj.accumulate(
																		DESCRIPTION,
																		INVALID_MESSAGE_ID_ERROR_DESCRIPTION);
																jsonFinalObj.accumulate(
																		"fault", jsonObj);
																isValidMessageId = false;
															}

														}

													} else {
														LOG.info("Message user action - ID is null ["
																+ id + "]");
														jsonObj.accumulate(CODE,
																MESSAGE_USER_ACTION_ERROR_CODE);
														jsonObj.accumulate(MESSAGE,
																INVALID_MESSAGE_ID_ERROR_MESSAGE);
														jsonObj.accumulate(
																DESCRIPTION,
																INVALID_MESSAGE_ID_ERROR_DESCRIPTION);
														jsonFinalObj.accumulate(
																"fault", jsonObj);
														isValidId = false;
													}
												}
											}
										}
									} else {
										
										Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
												INVALID_JSON_REQUEST_DESCRIPTION);
										LOG.info("Invalid json request");

										isValidRequestService = false;
									}

								}
							}

							else {
								LOG.info("Brand is neither Nissan nor Infiniti");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_CODE,
										VALIDATION_FAILED_MESSAGE,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								isValidBrand = false;
							}
						}

						LOG.info("Requested Service::::::::"
								+ validateDataWrapper.getValidateData()
								.getRequestedservice());

						if (isValidBrand && isValidVin && isValidMileage
								&& isValidNickName && isValidRequestService
								&& isValidFileName && isValidContent
								&& isValidContentType && isValidId
								&& idNotAvailable && isValidAction && isValidUser && isValidMessageId) {

							if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
								validateDataWrapper.getValidateData().setBrand(
										NISSAN);
							} else if (brand.equalsIgnoreCase(BRAND_INFINITI)) {
								validateDataWrapper.getValidateData().setBrand(
										INFINITI);
							}

							if (!Utility.isStringNotNullorNotEmpty(jwtToken)) {
								
										LOG.info("JWT is null ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										GENERAL_ERROR_FAULT_CODE,
										INVALID_JWT_ERROR_MSG,
										INVALID_JSON_REQUEST_DESCRIPTION);

								invalidjwt=false;

							} else if (Utility
									.isStringNotNullorNotEmpty(jwtToken)) {

								int counter = 0;
								for (int i = 0; i < jwtToken.length(); i++) {
									if (jwtToken.charAt(i) == '.') {
										counter++;
									}
								}

								LOG.info("Count of '.' in JWT" + counter);
								if (counter == 2) {

									LOG.info("JWT = "+jwtToken);
									userName = Util
											.getUsernameFromJWT(jwtToken);
									LOG.info("Email ID = " + userName);

								} else {
									LOG.info("Invalid JWT format");
									Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
											GENERAL_ERROR_FAULT_CODE,
											INVALID_JWT_ERROR_MSG,
											INVALID_JSON_REQUEST_DESCRIPTION);

									invalidjwt=false;

								}
							}

							if (Utility.isStringNotNullorNotEmpty(userName)) {

								validateDataWrapper.getValidateData()
								.setEmailId(userName);

								// x566325 - Brand Segregation - included
								// OwnerPortalTypeCode - N or I
								validUser = userLocal.validateUser(userName,
										validateDataWrapper.getValidateData()
										.getBrand());

								if (!Utility
										.isObjectNotNullorNotEmpty(validUser)) {

									Util.setFaultDataToJSON(
											jsonObj,
											jsonFinalObj,
											VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
									emailNotAvailable = true;

								} else if (Utility
										.isObjectNotNullorNotEmpty(validUser)) {

									LOG.info("Valid user="
											+ validUser.getEmailId()
											+ "UserprofileId="
											+ validUser.getUserProfileId());

									ownerPortalUserPhone = userLocal
											.getUserPhoneDetails(validUser
													.getUserProfileId());

									state = userLocal
											.getStateByStateKey(validUser
													.getStateKey());
									/*istestvinavailable= vehicleLocal
											.vinLookupfordeletevehicle(vin);*/

									if (validateDataWrapper.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(UPDATE_VEHICLE)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													GET_VEHICLE)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													CONNECTED_SERVICES)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													SERVICE_CONTRACT)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													RECALL_SERVICE)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													DELETE_VEHICLE)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													VEHICLE_SPECIFICATION)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													SERVICE_HISTORY)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													CONNECTED_SERVICES_DETAIL)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													MESSAGE_DETAILS)
											|| validateDataWrapper
											.getValidateData()
											.getRequestedservice()
											.equalsIgnoreCase(
													MESSAGE_USER_ACTION)) {

										// x875352 Change

										vinMappedToSameUser = vehicleLocal
												.validateUserVehicle(
														validateDataWrapper,
														validUser, brand);
										if (("sameUser")
												.equalsIgnoreCase(vinMappedToSameUser)) {

											vehicleInfo = vehicleLocal
													.getVehicleInfo(validateDataWrapper
															.getValidateData()
															.getVin());

											userVehicleInfo = vehicleLocal
													.getUserVehicleInfo(
															validUser
															.getUserProfileId(),
															vin);
											driverVehicleInfo = vehicleLocal
													.getDriverInfo(vin);

											telematicsInfo = vehicleLocal
													.getTelematicsInfo(vehicleInfo);
											if (vehicleInfo
													.getVehicleModelName()
													.contains("LEAF")
													|| vehicleInfo
													.getVehicleModelName()
													.contains("LEF")) {
												vehicleCarwings = vehicleLocal
														.getVehicleCarwings(vin);
											}

											success = true;
											// x055765 change for all api's -
											// makeCode brand mismatch
											if (!((vehicleInfo
													.getVehicleMakeCode()
													.contains(NISSAN) && brand
													.contains(BRAND_NISSAN)) || (vehicleInfo
															.getVehicleMakeCode()
															.contains(INFINITI) && brand
															.contains(BRAND_INFINITI)))) {

												vinValidation = "failure";
												jsonObj.accumulate(CODE,
														INVALID_VIN_CODE);
												jsonObj.accumulate(MESSAGE,
														VALIDATION_FAILED_MESSAGE);
												jsonObj.accumulate(DESCRIPTION,
														VEHICLE_INVALID_VIN_DESCRIPTION);
												jsonFinalObj.accumulate(
														"fault", jsonObj);
												isvalidvinandbrand = false;
											} else {
												success = true;
											}

										}

										if (("notAvailable")
												.equalsIgnoreCase(vinMappedToSameUser)) {
											jsonObj.accumulate(CODE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
											vinNotAvailabe = true;
										} else if (("otherUser")
												.equalsIgnoreCase(vinMappedToSameUser)) {
											jsonObj.accumulate(CODE,
													VEHICLE_MISMATCH_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_INVALID_USER_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_INVALID_USER_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
											vinNotAvailabe = true;
										}
									} else if ((ADD_VEHICLE)
											.equalsIgnoreCase(validateDataWrapper
													.getValidateData()
													.getRequestedservice())) {

										vinMappedToUser = vehicleLocal
												.validateUserVehicle(
														validateDataWrapper,
														validUser, brand);
										if (("notAvailable")
												.equalsIgnoreCase(vinMappedToUser)) {
											success = true;
										} else if (("otherUser")
												.equalsIgnoreCase(vinMappedToUser)) {
											jsonObj.accumulate(CODE,
													VEHICLE_MISMATCH_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_MISMATCH_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_MISMATCH_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
										} else if (("sameUser")
												.equalsIgnoreCase(vinMappedToUser)) {
											jsonObj.accumulate(CODE,
													DUPLICATE_VIN_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_DUPLICATE_USER_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_DUPLICATE_USER_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
										}
									}

									else if ((UPLOAD_DOCUMENT)
											.equalsIgnoreCase(validateDataWrapper
													.getValidateData()
													.getRequestedservice())) {
										vinValidation = vehicleLocal
												.validVehicle(
														validateDataWrapper
														.getValidateData()
														.getVin(),
														validUser
														.getUserProfileId());
										if (("success")
												.equalsIgnoreCase(vinValidation)) {
											success = true;
										} else if (("failure")
												.equalsIgnoreCase(vinValidation)) {
											jsonObj.accumulate(CODE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);

										}
									}

								}
								// x875352
							}

						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
						return Response.ok().status(400).entity(jsonFinalObj.toString())
								.build();
					}
				} else {

					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
					return Response.ok().status(400).entity(jsonFinalObj.toString())
							.build();
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			if (success) {
				LOG.info("Inside success for vehcile validate Data");
				if ((ADD_VEHICLE).equalsIgnoreCase(validateDataWrapper
						.getValidateData().getRequestedservice())) {
					LOG.info("Validate data for add vehicle JSON success response for the user = "
							+ userName + " and vin = " + vin);

					jsonlastObj = setAddVehicleValidateDataResponse(jsonObj,
							validUser, ownerPortalUserPhone, state,
							validateDataWrapper, brand);

				} else if ((GET_VEHICLE).equalsIgnoreCase(validateDataWrapper
						.getValidateData().getRequestedservice())
						|| validateDataWrapper.getValidateData()
						.getRequestedservice()
						.equalsIgnoreCase(VEHICLE_SPECIFICATION)
						|| validateDataWrapper.getValidateData()
						.getRequestedservice()
						.equalsIgnoreCase(SERVICE_HISTORY)) {

					LOG.info("Validate data for getvehicle JSON success response for the user = "
							+ userName + " and vin =" + vin);
					jsonlastObj = setGetVehicleValidateDataResponse(jsonObj,
							validUser, ownerPortalUserPhone, state,
							validateDataWrapper, brand, userVehicleInfo,
							vehicleInfo);

				} else if (validateDataWrapper.getValidateData()
						.getRequestedservice().equalsIgnoreCase(UPDATE_VEHICLE)) {

					LOG.info("Validate data for update vehicle JSON success response for the user = "
							+ userName + " and vin =" + vin);
					jsonlastObj = setUpdateVehicleValidateDataResponse(jsonObj,
							validUser, ownerPortalUserPhone, state,
							validateDataWrapper, brand, userVehicleInfo,
							vehicleInfo,
							// Added to accommodate the Fuse requirement for
							
							telematicsInfo);

				} else if (validateDataWrapper.getValidateData()
						.getRequestedservice().equalsIgnoreCase(DELETE_VEHICLE)) {

					LOG.info("Validate data for delete vehicle JSON success response for the user = "
							+ userName + " and vin =" + vin);
					jsonlastObj = setDeleteVehicleValidateDataResponse(jsonObj,
							validUser, ownerPortalUserPhone, state,
							validateDataWrapper, brand, userVehicleInfo,
							vehicleInfo, driverVehicleInfo, vehicleCarwings,
							telematicsInfo);

			} else if (validateDataWrapper.getValidateData()





					.getRequestedservice().equalsIgnoreCase(CONNECTED_SERVICES)) {
				LOG.info("Validate data for connected service JSON success response for the user = "
						+ userName + "and vin=" + vin);
				jsonlastObj = setConnectedServiceValidateDataResponse(jsonObj,
						validUser, vin, vehicleInfo, telematicsInfo, brand);

			} else if (validateDataWrapper.getValidateData()
					.getRequestedservice()
					.equalsIgnoreCase(CONNECTED_SERVICES_DETAIL)) {
					
				// Changes added for connected service detail by X497432 Balaji
				// O
				LOG.info("Validate data for connected service JSON success response for the user = "
						+ userName + "and vin=" + vin);
				jsonlastObj = setConnectedServiceDetailsValidateDataResponse(
						jsonObj, validUser, vin, vehicleInfo, brand, id);

			} else if (validateDataWrapper.getValidateData()
					.getRequestedservice().equalsIgnoreCase(MESSAGE_DETAILS)) {
				// Changes added for Message detail by X497432 Balaji O
				LOG.info("Validate data for connected service JSON success response for the user = "
						+ userName + "and vin=" + vin);
				jsonlastObj = setMessageDetailsValidateDataResponse(jsonObj,
						validUser, vin, brand, id, action);
			} else if (validateDataWrapper.getValidateData()
					.getRequestedservice()
					.equalsIgnoreCase(MESSAGE_USER_ACTION)) {
				// Changes added for Meaasge User Action by X497432 Balaji O
				LOG.info("Validate data for connected service JSON success response for the user = "
						+ userName + "and vin=" + vin);
				jsonlastObj = setMessageUserActionValidateDataResponse(jsonObj,
						validUser, vin, brand, id, action,state,mobileProvider);
			} else if (validateDataWrapper.getValidateData()
					.getRequestedservice().equalsIgnoreCase(SERVICE_CONTRACT)) {
				LOG.info("Validate data for service contract JSON success response for the user = "
						+ userName + "and vin=" + vin);
				jsonlastObj = setServiceContractValidateDataResponse(jsonObj,
						validUser, vin, vehicleInfo, brand);
			} else if (validateDataWrapper.getValidateData()
					.getRequestedservice().equalsIgnoreCase(RECALL_SERVICE)) {
				LOG.info("Validate data for service recall JSON success response for the user = "
						+ userName + "and vin=" + vin);
				jsonlastObj = setRecallValidateDataResponse(jsonObj, validUser,
						vin, vehicleInfo, brand);

			} else if (validateDataWrapper.getValidateData()
					.getRequestedservice().equalsIgnoreCase(UPLOAD_DOCUMENT)) {

				LOG.info("Validate data for upload document JSON success response for the user = "
						+ userName + " and vin =" + vin);
				jsonlastObj = setUploadDocumentValidateDataResponse(jsonObj,
						validUser, vin, validateDataWrapper, brand);

			}

			LOG.info("Validate data JSON success response for the user = "
					+ userName + " and vin = " + vin );
			return Response.ok().status(200).entity(jsonlastObj.toString())
					.build();

			} else if (!isValidVin || !isValidBrand
					|| ("sameUser").equalsIgnoreCase(vinMappedToUser)
					|| ("otherUser").equalsIgnoreCase(vinMappedToSameUser)
					|| ("failure").equalsIgnoreCase(vinMappedToSameUser)
					|| ("otherUser").equalsIgnoreCase(vinMappedToUser)
					|| !isValidMileage || !isValidNickName || !isValidRequestService ||!invalidjwt || !isvalidvinandbrand) {
				LOG.info("Validate data JSON failed response for the user = "
						+ userName + " and vin = " + vin + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (vinNotAvailabe) {
				LOG.info("Validate data JSON failed response for the user = "
						+ userName + " and vin =" + vin + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (generalError) {
				LOG.info("Validate data JSON failed response for the user = "
						+ userName + " and vin =" + vin + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (("failure").equalsIgnoreCase(vinValidation)) {
				LOG.info("Validate data JSON failed response for the user = "
						+ userName + " and vin =" + vin + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();

		} else if (!isValidVin || !isValidBrand || !isValidFileName
				|| !isValidContent || !isValidContentType) {

			LOG.info("Validate data JSON failed response for the vin =" + vin
					+ jsonFinalObj.toString());

			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		}
		// x566325 - Brand Segregation - Email is not available in database case
		else if (emailNotAvailable) {

			LOG.info("Validate data JSON failed response for the vin =" + vin
					+ jsonFinalObj.toString());

			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidId || !idNotAvailable) {

			LOG.info("ID is not available [" + id + "] for the vin =" + vin);

			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidAction) {

			LOG.info("action is  [" + action + "]  for the vin =" + vin);

			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidUser){ 
			LOG.info("Vin number  [" + vin + "]  is not associated to the user ");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidMessageId){ 
			LOG.info("Invalid Message ID provided [" + id + "]  is not associated to the user ");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		}	else {


				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.info("Inside Validate dataJSON failed response for the user = "
						+ userName + " and vin =" + vin + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			
		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside Catch block- JSONException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside Catch block-JsonParseException");
			
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside Catch block-JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the user =" + userName
					+ " and vin =" + vin + " is " + jsonFinalObj.toString()
					+ " IOException during ValidateData : ", e);
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the user =" + userName
					+ " and vin =" + vin + " is " + jsonFinalObj.toString()
					+ " General exception during ValidateData : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}
	
	/**
	 * @author x430955
	 * @use Validate data for vehicle disposal
	 * @param brand
	 * @param vin
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/vehicleDisposal")
	public Response vehicleDisposalvalidateData(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		
		

		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonlastObj = new JSONObject();
		ValidateDataWrapper validateDataWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidVin = true;
		boolean success = false;
		boolean isValidBrand = true;
		boolean isvalidvinandbrand = true;
		boolean isValidNickName = true;
		boolean isValidContentType = true;
		boolean isValidFileName = true;
		boolean isValidContent = true;
		boolean vinNotAvailabe = false;
		boolean isValidRequestService = true;
		boolean isValidId = true;
		boolean invalidjwt = true;
		boolean idNotAvailable = true;
		boolean isValidAction = true;
		boolean isValidUser=true;
		boolean isValidMessageId = true;
		String userName = "";
		byte[] content = null;
		OwnerPortalUser validUser = null;
		List<OwnerPortalUserPhone> ownerPortalUserPhone = null;
		VehicleCarwings vehicleCarwings = null;
		OwnerPortalUser driverVehicleInfo = null;
		OwnerPortalUser isUserEmailValid = null;
		OwnerPortalVehicle vehicleInfo = null;
		OwnerPortalVehicle vehicleInfoCheck = null;
		OwnerPortalUserVehicle userVehicleInfo = null;
		State state = null;
		MobileProvider mobileProvider = null;
		String vinMappedToSameUser = "";
		String vinMappedToUser = "";
		String vinValidationForUnsubscription = "";
		String vin = "";
		String requestedService = "";
		String personHashId = "";
		String id = "";
		String action = "";
		/*String istestvinavailable="";*/
		String brandcheck="";
		boolean generalError = false;
		boolean emailNotAvailable = false;

		Set<String> telematicsInfo = null;
		Set<String> telematicsInfoCheck = null;
		String vinValidation = "";
		try{
			LOG.info("Inside Try block- VehicleDisposal Validate Data");

			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				LOG.info("Inside Try block- VehicleDisposal Valid Request");

				validateDataWrapper = mapper.readValue(requestJson,
						ValidateDataWrapper.class);

				if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper)) {
					LOG.info("Inside Try block- VehicleDisposal Validate Data Wrapper");

					if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper
							.getValidateData())) {
						LOG.info("Inside Try block- VehicleDisposal Validate Data Request");

						if(Utility.isObjectNotNullorNotEmpty(validateDataWrapper
								.getValidateData().getVin()) &&  Utility.isObjectNotNullorNotEmpty(validateDataWrapper.getValidateData().getPersonHashId()) && Utility.isObjectNotNullorNotEmpty(validateDataWrapper
										.getValidateData().getRequestedservice())){
							LOG.info("Inside Try block- VehicleDisposal Validate Data JSON VIN = " + vin);
							LOG.info("Inside Try block- VehicleDisposal Validate Data JSON requestedService = " + requestedService);
							LOG.info("Inside Try block- VehicleDisposal Validate Data JSON personHashId = " + personHashId);
							vin = validateDataWrapper.validateData.getVin();
							requestedService = validateDataWrapper.validateData.getRequestedservice();
							personHashId = validateDataWrapper.validateData.getPersonHashId();

							isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
							LOG.info("isValidBrand response"+ isValidBrand);
							if (isValidBrand) {
								LOG.info("Inside isValidBrand response"+ brand);
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									LOG.info("Inside isValidBrand "+ brand);
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);
									LOG.info("Inside isValidVin "+ isValidVin);
									if (isValidVin) {
										LOG.info("Inside if isValidVin "+ isValidVin);
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
										LOG.info("Inside isValidVin Response"+ isValidVin);
										if (isValidVin) {
											LOG.info("Inside isValidVin loop "+ isValidVin);
											if (!((validateDataWrapper
													.getValidateData()
													.getRequestedservice()
													.equalsIgnoreCase(VEHICLE_DISPOSAL)))){
												LOG.debug("Request Service is null");

												Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
														GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
														VEHICLE_INVALID_REQUESTEDSERVICE_MESSAGE);
												LOG.info("Invalid json request service");
												isValidRequestService = false;											
											}else{
												LOG.info("Else loop isValidVin "+ isValidVin);
												LOG.info("Brand check "+ brand);
												if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
													validateDataWrapper.getValidateData().setBrand(
															NISSAN);
												} else if (brand.equalsIgnoreCase(BRAND_INFINITI)) {
													validateDataWrapper.getValidateData().setBrand(
															INFINITI);			
												}
												LOG.info("Brand check After setting N/I"+ brand);
												validUser = userLocal.validateUserforVehicalDisposal(personHashId,
														validateDataWrapper.getValidateData()
														.getBrand());
												LOG.info(" Valid user Response " + validUser);
												if (!Utility
														.isObjectNotNullorNotEmpty(validUser)) {
													LOG.info("Not Valid user check ");
													Util.setFaultDataToJSON(
															jsonObj,
															jsonFinalObj,
															VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
															VALIDATION_FAILED_MESSAGE,
															PRSNHASHID_NOT_AVAILABLE_DESCRIPTION);
													emailNotAvailable = true;

												} else if (Utility
														.isObjectNotNullorNotEmpty(validUser)) {
													
													LOG.info("Valid user="
															+ validUser.getEmailId()
															+ "UserprofileId="
															+ validUser.getUserProfileId());
													

													vinMappedToSameUser = vehicleLocal
															.validateUserVehicle(
																	validateDataWrapper,
																	validUser, brand);
													LOG.info(" vinMappedToSameUser " + vinMappedToSameUser);
													if (("sameUser")
															.equalsIgnoreCase(vinMappedToSameUser)) {

														LOG.info("Inside sameUser loop " + vinMappedToSameUser);
														LOG.info("Valid user="
																+ validUser.getEmailId()
																+ "UserprofileId="
																+ validUser.getUserProfileId());

														ownerPortalUserPhone = userLocal
																.getUserPhoneDetails(validUser
																		.getUserProfileId());
														LOG.info(" ownerPortalUserPhone " + ownerPortalUserPhone);
														
														state = userLocal
																.getStateByStateKey(validUser
																		.getStateKey());
														LOG.info(" state " + state);


														vehicleInfo = vehicleLocal
																.getVehicleInfo(validateDataWrapper
																		.getValidateData()
																		.getVin());
														LOG.info("vehicleInfo" + vehicleInfo);
														userVehicleInfo = vehicleLocal
																.getUserVehicleInfo(
																		validUser
																		.getUserProfileId(),
																		vin);
														LOG.info("userVehicleInfo" + userVehicleInfo);
														driverVehicleInfo = vehicleLocal
																.getDriverInfo(vin);
														LOG.info("driverVehicleInfo" + driverVehicleInfo);
														telematicsInfo = vehicleLocal
																.getTelematicsInfo(vehicleInfo);
														LOG.info("telematicsInfo" + telematicsInfo);
														if (vehicleInfo
																.getVehicleModelName()
																.contains("LEAF")
																|| vehicleInfo
																.getVehicleModelName()
																.contains("LEF")) {
															vehicleCarwings = vehicleLocal
																	.getVehicleCarwings(vin);
														}
														LOG.info("Before setting success" + success);
														success = true;
														LOG.info("After setting success" + success);
													}

													if (("notAvailable")
															.equalsIgnoreCase(vinMappedToSameUser)) {
														LOG.info("Inside notAvailable");
														jsonObj.accumulate(CODE,
																VEHICLE_NOTAVAILABLE_VINANDUSER_CODE);
														jsonObj.accumulate(MESSAGE,
																VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
														jsonObj.accumulate(DESCRIPTION,
																VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
														jsonFinalObj.accumulate("fault",
																jsonObj);
														vinNotAvailabe = true;
													} else if (("otherUser")
															.equalsIgnoreCase(vinMappedToSameUser)) {
														LOG.info("Inside otherUser");
														jsonObj.accumulate(CODE,
																VEHICLE_MISMATCH_CODE);
														jsonObj.accumulate(MESSAGE,
																VEHICLE_INVALID_USER_MESSAGE);
														jsonObj.accumulate(DESCRIPTION,
																VEHICLE_INVALID_USER_DESCRIPTION);
														jsonFinalObj.accumulate("fault",
																jsonObj);
														vinNotAvailabe = true;
													}
													
													

												}


											}
										}

									}

								}else{
									LOG.info("Brand is neither Nissan nor Infiniti");
									Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}

							}

						}else{

							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							LOG.info("Invalid json request");
							return Response.ok().status(400).entity(jsonFinalObj.toString())
									.build();

						}

					}else{

						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
						return Response.ok().status(400).entity(jsonFinalObj.toString())
								.build();

					}

				}else{

					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
					return Response.ok().status(400).entity(jsonFinalObj.toString())
							.build();

				}
			}else{

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();

			}
			
			if (success) {
				LOG.info("Inside success for vehicleDisposal validate Data");

				LOG.info("Validate data for delete vehicle JSON success response for the user = "
						+ userName + " and vin =" + vin);
				jsonlastObj = setDeleteVehicleValidateDataResponse(jsonObj,
						validUser, ownerPortalUserPhone, state,
						validateDataWrapper, brand, userVehicleInfo,
						vehicleInfo, driverVehicleInfo, vehicleCarwings,
						telematicsInfo);

				LOG.info("Validate data JSON success response for the user = "
						+ userName + " and vin = " + vin );
				return Response.ok().status(200).entity(jsonlastObj.toString())
						.build();
			}else{
				LOG.info("Validate data JSON failed response for the user = "
						+ userName + " and vin =" + vin + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}

		}catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside Catch block- JSONException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside Catch block-JsonParseException");

			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside Catch block-JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the user =" + userName
					+ " and vin =" + vin + " is " + jsonFinalObj.toString()
					+ " IOException during ValidateData : ", e);
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the user =" + userName
					+ " and vin =" + vin + " is " + jsonFinalObj.toString()
					+ " General exception during ValidateData : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}



	}


	/**
	 * @author x796314
	 * @use To lookup for vin in pipeline
	 * @param brand
	 * @param vin
	 * @return
	 * @throws JSONException
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/vinlookup")
	public Response vinLookup(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("VinLookup JSON request is" + requestJson);
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonFinalVehicleObj = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidVin = true;
		boolean success = false;
		boolean isValidBrand = true;
		String makeCode = "";
		boolean generalError = false;

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				VinLookupWrapper vinLookupWrapper = mapper.readValue(
						requestJson, VinLookupWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(vinLookupWrapper
						.getValidateVin())) {
					if (Utility.isStringNotNullorNotEmpty(vinLookupWrapper
							.getValidateVin().getVin())) {
						LOG.info("vin length for the vin"
								+ vinLookupWrapper.getValidateVin().getVin()
								+ "is"
								+ vinLookupWrapper.getValidateVin().getVin()
								.length());
						String vin = vinLookupWrapper.getValidateVin().getVin();
						isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);

						if (isValidBrand) {

							if (brand.equalsIgnoreCase(BRAND_INFINITI)
									|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

								isValidBrand = true;

								isValidVin = isVinNull(vin, jsonObj,
										jsonFinalObj);

								if (isValidVin) {
									isValidVin = isVinValid(vin, jsonObj,
											jsonFinalObj);
								}
							} else {
								LOG.info("Brand is neither Nissan nor Infiniti");
								isValidBrand = false;
								jsonObj.accumulate(CODE, VALIDATION_FAILED_CODE);
								jsonObj.accumulate(MESSAGE,
										VALIDATION_FAILED_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								jsonFinalObj.accumulate(FAULT, jsonObj);
							}
						}
						if (isValidVin && isValidBrand) {
							LOG.debug("VinLookup JSON valid vin=" + vin
									+ " and valid brand=" + brand);

							ManualVehicleLookup vehicleInformation = vehicleLocal
									.vinLookup(vin);

							if (Utility
									.isObjectNotNullorNotEmpty(vehicleInformation)) {
								LOG.debug("Inside vinLookup JSON success");
								ModelLineMapping modelNameData = vehicleLocal
										.modelNameLookup(vehicleInformation
												.getModelLineCode());
								if (vehicleInformation.getVehicleMakeCode()
										.equalsIgnoreCase(NISSAN)) {
									makeCode = BRAND_NISSAN;
								} else if (vehicleInformation
										.getVehicleMakeCode().equalsIgnoreCase(
												INFINITI)) {
									makeCode = BRAND_INFINITI;
								}

								jsonObj.accumulate("vin",
										vehicleInformation.getVin());
								jsonObj.accumulate("modelCode",
										vehicleInformation.getModelCode());
								if (Utility
										.isObjectNotNullorNotEmpty(modelNameData)) {
									jsonObj.accumulate("modelName",
											modelNameData.getModelName());
								} else {
									jsonObj.accumulate("modelName",
											vehicleInformation
											.getModelLineCode());
								}
								jsonObj.accumulate("trimCode",
										vehicleInformation.getTrimCode());
								jsonObj.accumulate("exteriorColorCode",
										vehicleInformation
										.getExteriorColorCode());
								jsonObj.accumulate("optionCode",
										vehicleInformation.getOptionCode());

								jsonObj.accumulate("make", makeCode);
								jsonObj.accumulate("modelYear",
										vehicleInformation
										.getVehicleModelYear()
										.toString());
								jsonFinalObj.accumulate("vehicle", jsonObj);
								jsonFinalVehicleObj.accumulate("vinLookUp",
										jsonFinalObj);
								success = true;
							} else {
								LOG.info("Vin not available in pipeline table");
								jsonObj.accumulate(CODE, VIN_NOTAVAILABLE_CODE);
								jsonObj.accumulate(MESSAGE,
										VEHICLE_UNAVAILABLE_VIN_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_UNAVAILABLE_VIN_DESCRIPTION);
								jsonFinalObj.accumulate(FAULT, jsonObj);
								LOG.info("Vin not available in pipeline table"
										+ jsonFinalObj.toString());
								return Response.ok().status(404)
										.entity(jsonFinalObj.toString())
										.build();
							}

						}
					} else {

						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());
						generalError = true;
					}
				} else {

					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					generalError = true;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				generalError = true;
			}
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response is=" + jsonFinalObj.toString()
			+ "JsonParseException  during vinlookup : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response is=" + jsonFinalObj.toString()
			+ "JsonMappingException  during vinlookup : ", e);

			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JSONException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("response is =" + jsonFinalObj.toString()
			+ "JSONException  during vinlookup : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("response is =" + jsonFinalObj.toString()
			+ "Exception  during vinlookup : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		}

		if (success) {
			LOG.info("Inside vinlookup JSON success response"
					+ jsonFinalVehicleObj.toString());
			return Response.ok().status(200)
					.entity(jsonFinalVehicleObj.toString()).build();
		} else if (!isValidVin || !isValidBrand) {
			LOG.info("Inside vinlookup  JSON failed response"
					+ jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (generalError) {
			LOG.info("Inside vinlookup  general error and response"
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Inside vinlookup  JSON unexpected failed response"
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}

	/**
	 * @author x796314
	 * @param brand
	 * @use To update vehicle in user account
	 * @param requestJson
	 * @method-POST
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/uploadDocument")
	public Response uploadDocument(@HeaderParam("Brand") String brand,
			String requestJson) throws JsonParseException,
	JsonMappingException, IOException, JSONException {

		LOG.info("Inside upload Document JSON request" + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = false;
		boolean isValidVin = true;
		boolean isValidContentType = true;
		boolean isValidContent = true;
		boolean isValidFileName = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean generalError = false;

		OwnerPortalUser validUser;
		OwnerPortalUserVehicleFailedReference validVin;

		String response = "";
		String vin = "";
		String email = "";
		String result = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				UploadDocumentWrapper uploadDocumentWrapper = mapper.readValue(
						requestJson, UploadDocumentWrapper.class);

				isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);

				if (Utility.isObjectNotNullorNotEmpty(uploadDocumentWrapper)) {

					if (Utility.isObjectNotNullorNotEmpty(uploadDocumentWrapper
							.getUploadDocument())) {

						if (Utility
								.isObjectNotNullorNotEmpty(uploadDocumentWrapper
										.getUploadDocument().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(uploadDocumentWrapper
										.getUploadDocument()
										.getVehicle())) {

							email = uploadDocumentWrapper.getUploadDocument()
									.getPerson().getEmail();
							String personHashId = uploadDocumentWrapper
									.getUploadDocument().getPerson()
									.getPersonHashId();

							vin = uploadDocumentWrapper.getUploadDocument()
									.getVehicle().getVin();
							String fileName = uploadDocumentWrapper
									.getUploadDocument().getVehicle()
									.getFileName();
							String contentType = uploadDocumentWrapper
									.getUploadDocument().getVehicle()
									.getContentType();
							byte[] content = uploadDocumentWrapper
									.getUploadDocument().getVehicle()
									.getContent();

							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}

									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}

									if (isValidVin) {
										isValidContentType = isValidContentType(
												contentType, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										//LOG.info("content" + content);

										isValidContent = isValidContent(
												content, contentType, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidFileName = isValidFileName(
												fileName, jsonObj, jsonFinalObj);
									}

								} else {
									LOG.info("Brand is neither Nissan or Infiniti");
									jsonObj.accumulate(CODE,
											VALIDATION_FAILED_CODE);
									jsonObj.accumulate(MESSAGE,
											VALIDATION_FAILED_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
								}
							} else {
								LOG.info("Brand is neither Nissan or Infiniti");
								jsonObj.accumulate(CODE, VALIDATION_FAILED_CODE);
								jsonObj.accumulate(MESSAGE,
										VALIDATION_FAILED_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								jsonFinalObj.accumulate("fault", jsonObj);
							}

							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidFileName
									&& isValidContentType && isValidContent) {

								// x566325 - Brand Segregation - send brand also

								/*
								 * validUser = userLocal.validateEmail(email,
								 * personHashId);
								 */

								validUser = userLocal.validateEmail(email,
										personHashId, brand);

								validVin = vehicleLocal.validateVin(vin);

								if (Utility
										.isObjectNotNullorNotEmpty(validUser)
										&& Utility
										.isObjectNotNullorNotEmpty(validVin)) {
									LOG.info("Inside before calling load document");

									response = vehicleLocal.loadDocument(
											uploadDocumentWrapper, brand);
								} else {
									jsonObj.accumulate(CODE,
											GENERAL_ERROR_FAULT_CODE);
									jsonObj.accumulate(MESSAGE,
											GENERAL_ERROR_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											GENERAL_ERROR_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
									generalError = true;
								}
							}

						}
					}

					if (Utility.isStringNotNullorNotEmpty(response)
							&& response.equalsIgnoreCase(SUCCESS)) {
						LOG.debug("Inside upload Document JSON success response for the vin"
								+ vin);
						Util.setUploadDocumentResponseDataToJSON(jsonObj,
								GENERAL_CODE, GENERAL_MESSAGE);

						LOG.debug("response is" + jsonObj.toString());

						result = SUCCESS;

					}

				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					generalError = true;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				generalError = true;
			}
		}

		catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + " with email= "
					+ email + "is" + jsonFinalObj.toString()
					+ "JsonParseException  upload Document  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + " with email= "
					+ email + "is" + jsonFinalObj.toString()
					+ "JsonMappingException  upload Document  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + " with email= "
					+ email + "is" + jsonFinalObj.toString()
					+ "Json exception  upload Document  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + " with email= "
					+ email + "is" + jsonFinalObj.toString()
					+ "General exception  upload Document  ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {
			LOG.info("Upload document JSON success response for the vin=" + vin
					+ " user person ID=" + vin + jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();

		} else if (!isValidVin || !isValidBrand
				|| result.equalsIgnoreCase(FAILURE) || !isValidContent
				|| !isValidContentType || !isValidEmail || !isValidFileName) {

			LOG.info("Upload document JSON failed response for the vin=" + vin
					+ " with user person ID=" + vin + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} else if (generalError || !isValidPersonHashId) {
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Upload document JSON unexpected failed response for the vin="
					+ vin
					+ " with user person ID="
					+ vin
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}

	/**
	 * @author x796314
	 * @param brand
	 * @use To update vehicle in user account
	 * @param requestJson
	 * @method-POST
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/updateVehicle")
	public Response updateVehicle(@HeaderParam("Brand") String brand,
			String requestJson) throws JsonParseException,
	JsonMappingException, IOException, JSONException {

		LOG.info("Inside update Vehicle JSON request = " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidMileage = true;
		boolean isValidAverageMileage = true;
		boolean isValidNickName = true;
		String response = "";
		OwnerPortalUser validUser;
		OwnerPortalVehicle vehicleInfo = null;
		OwnerPortalUserVehicle ownerPortalUserVehicle = null;
		String vinMappedToSameUser = "";
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isMakeNull = true;
		boolean isvalidJSON  = true;
		String vin = "";
		String email = "";
		boolean generalError = false;
		String result = "";
		boolean emailNotAvailable = false;

		String modelCode = "";
		String exteriorColorCode = "";
		String factoryOptionCode = "";
		String bodyStyleName = "";
		String modelName = "";
		OwnerPortalVehicle ownerPortalVehicle;	

		String largeImageUrl = "";
		String smallImageUrl = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				UpdateVehicleWrapper updateVehicleWrapper = mapper.readValue(
						requestJson, UpdateVehicleWrapper.class);

				isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
				if (Utility.isObjectNotNullorNotEmpty(updateVehicleWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(updateVehicleWrapper
							.getUpdateVehicle())) {

						if (Utility
								.isObjectNotNullorNotEmpty(updateVehicleWrapper
										.getUpdateVehicle().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(updateVehicleWrapper
										.getUpdateVehicle()
										.getVehicle())) {
							vin = updateVehicleWrapper.getUpdateVehicle()
									.getVehicle().getVin();
							email = updateVehicleWrapper.getUpdateVehicle()
									.getPerson().getEmail();
							String personHashId = updateVehicleWrapper
									.getUpdateVehicle().getPerson()
									.getPersonHashId();
							Integer mileage = updateVehicleWrapper
									.getUpdateVehicle().getVehicle()
									.getMileage();

							Integer averageMileage = updateVehicleWrapper
									.getUpdateVehicle().getVehicle()
									.getAverageMileage();
							String nickName = updateVehicleWrapper
									.getUpdateVehicle().getVehicle()
									.getNickname();
							String make = updateVehicleWrapper
									.getUpdateVehicle().getVehicle().getMake();

							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isMakeNull = isMakeNull(make, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidMileage = isValidMileage(
												mileage, jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidAverageMileage = isValidAverageMileage(
												averageMileage, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidNickName = isValidNickName(
												nickName, jsonObj, jsonFinalObj);
									}

								} else {
									LOG.info("Brand is neither Nissan or Infiniti");
									jsonObj.accumulate(CODE,
											VALIDATION_FAILED_CODE);
									jsonObj.accumulate(MESSAGE,
											VALIDATION_FAILED_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
								}
							} else {
								LOG.info("Brand is neither Nissan or Infiniti");
								jsonObj.accumulate(CODE, VALIDATION_FAILED_CODE);
								jsonObj.accumulate(MESSAGE,
										VALIDATION_FAILED_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								jsonFinalObj.accumulate("fault", jsonObj);
							}

							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidMileage
									&& isValidAverageMileage && isValidNickName
									&& isMakeNull) {

								if (Utility.isStringNotNullorNotEmpty(make)
										&& make.equalsIgnoreCase(BRAND_NISSAN)
										|| make.equalsIgnoreCase(BRAND_INFINITI)) {

									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										updateVehicleWrapper.getUpdateVehicle()
										.setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										updateVehicleWrapper.getUpdateVehicle()
										.setBrand(INFINITI);
									}

									// x566325 - Brand Segregation - send brand
									// also
									/*
									 * validUser =
									 * userLocal.validateEmail(email,
									 * personHashId);
									 */

									validUser = userLocal.validateEmail(email,
											personHashId, brand);

									vehicleInfo = vehicleLocal
											.getVehicleInfo(vin);

									/*
									// x566325 - Update updateVehicle API to
									// include Image URL's in the response
									largeImageUrl = vehicleLocal
											.getVehicleFunctionLargeImage(vehicleInfo);
									smallImageUrl = vehicleLocal
											.getVehicleFunctionSmallImage(vehicleInfo);
									 */


									// X550910 - For silhoutte images in update vehicle Call
									modelCode =  updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelCode();
									exteriorColorCode = updateVehicleWrapper.getUpdateVehicle().getVehicle().getExteriorColorCode();
									factoryOptionCode =  updateVehicleWrapper.getUpdateVehicle().getVehicle().getOptionCode();
									bodyStyleName = updateVehicleWrapper.getUpdateVehicle().getVehicle().getBodyStyleName();
									modelName = updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName();

									// If the URL is not there in GPAS, Display Silhouette Image based on BodyStyleName and ModelName - starts
									if(Utility.isStringNotNullorNotEmpty(modelCode) && Utility.isStringNotNullorNotEmpty(exteriorColorCode) && Utility.isStringNotNullorNotEmpty(factoryOptionCode)) {

										// condition  to check if the vehicle year is >= 2016. if not set silhoutte images 
										if(Integer.parseInt( vehicleInfo.getModelYearNumber()) >= 2016) {

											LOG.info("Values from Request:" +""+ "modelCode" + modelCode + "exteriorColorCode" + exteriorColorCode + "factoryOptionCode" + factoryOptionCode);

											// UpdateVehicle API to
											// include Image URL's in the response
											largeImageUrl = vehicleLocal
													.getVehicleFunctionLargeImage1(modelCode,exteriorColorCode,factoryOptionCode);
											smallImageUrl = vehicleLocal
													.getVehicleFunctionSmallImage1(modelCode,exteriorColorCode,factoryOptionCode);
											LOG.info("Final valid BIDW imageurl " +largeImageUrl + "smallimage " +  smallImageUrl);

										} else {
											LOG.info("Vehicle Model Year is less that 2016 so setting silhoutte images:" + vehicleInfo.getModelYearNumber());
											largeImageUrl = "Not_Available";
											smallImageUrl = "Not_Available";

										}
										//check url check
										if(largeImageUrl.contains("exterior-.png") || largeImageUrl.equalsIgnoreCase("Not_Available")) {
											largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
											LOG.info("final silhoutte largeImageUrl" + largeImageUrl);

										} 
										if(smallImageUrl.contains("exterior-.png") || smallImageUrl.equalsIgnoreCase("Not_Available")) {

											smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
											LOG.info("final silhoutte smallImageUrl" + smallImageUrl);
										}


									}else {

										LOG.info("all the 5 values are null from BIDW are null & vin = " + vin);

										//check the owner portal vehicle table for ModelName
										ownerPortalVehicle = vehicleLocal.getModelNameUsingVin(vin);
										modelName = ownerPortalVehicle.getVehicleModelName();
										LOG.info("all the 5 values are null from BIDW are null & modeLName from DB: " + modelName);

										largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
										LOG.info("final silhoutte largeImageUrl using ModelName" + largeImageUrl);
										smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
										LOG.info("final silhoutte smallImageUrl using ModelName" + smallImageUrl);

									}



									// x566325 - Brand segregation - Email is
									// not available in database case
									if (!Utility
											.isObjectNotNullorNotEmpty(validUser)) {

										Util.setFaultDataToJSON(
												jsonObj,
												jsonFinalObj,
												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
										emailNotAvailable = true;
									}
									// changed below if to else if
									else if (Utility
											.isObjectNotNullorNotEmpty(validUser)
											&& Utility
											.isObjectNotNullorNotEmpty(vehicleInfo)) {

										vinMappedToSameUser = vehicleLocal
												.validateVehicleOwner(validUser
														.getUserProfileId(),
														vin);

										ownerPortalUserVehicle = vehicleLocal
												.getUserVehicleInfo(validUser
														.getUserProfileId(),
														vin);

										if (("sameUser")
												.equalsIgnoreCase(vinMappedToSameUser)) {

											response = vehicleLocal
													.updateVehicle(
															updateVehicleWrapper,
															validUser);

										} else if (("otherUser")
												.equalsIgnoreCase(vinMappedToSameUser)) {
											jsonObj.accumulate(CODE,
													VEHICLE_MISMATCH_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_MISMATCH_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_MISMATCH_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);

										} else {

											LOG.info("User=" + email
													+ " or vin=" + vin
													+ " not available in db");
											jsonObj.accumulate(CODE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
										}
									} else {
										jsonObj.accumulate(CODE,
												GENERAL_ERROR_FAULT_CODE);
										jsonObj.accumulate(MESSAGE,
												GENERAL_ERROR_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												GENERAL_ERROR_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										generalError = true;
									}

								} else {
									jsonObj.accumulate(CODE,
											VALIDATION_FAILED_CODE);
									jsonObj.accumulate(MESSAGE,
											VALIDATION_FAILED_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
								}
							}

							if (Utility.isStringNotNullorNotEmpty(response)
									&& response.equalsIgnoreCase(SUCCESS)) {
								LOG.info("Inside update vehicle JSON success response for the vin = "
										+ vin);
								jsonObj.accumulate("vin", updateVehicleWrapper
										.getUpdateVehicle().getVehicle()
										.getVin());
								if (Utility
										.isStringNotNullorNotEmpty(updateVehicleWrapper
												.getUpdateVehicle()
												.getVehicle().getModelName())) {
									if (updateVehicleWrapper.getUpdateVehicle()
											.getVehicle().getModelName()
											.contains("LEAF")
											|| updateVehicleWrapper
											.getUpdateVehicle()
											.getVehicle()
											.getModelName()
											.contains("LEF")) {
										jsonObj.accumulate("electric", true);
									} else {
										jsonObj.accumulate("electric", false);
									}
								} else {
									if (vehicleInfo.getVehicleModelName()
											.contains("LEAF")
											|| vehicleInfo
											.getVehicleModelName()
											.contains("LEF")) {
										jsonObj.accumulate("electric", true);
									} else {
										jsonObj.accumulate("electric", false);
									}
								}
								if ((updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName() == null)
										
										|| (updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName()
												.equals(""))
										|| (updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName()
												.equals(" "))) {

									LOG.info("vehicleName - vehicleInfo.getVehicleModelName = null "
											+ vehicleInfo.getVehicleModelName());
									jsonObj.accumulate("vehicleName", vehicleInfo.getVehicleModelName());

								} else {
									LOG.info("vehicleName - vehicleInfo.getVehicleModelName ! = null "
											+ updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName());
									jsonObj.accumulate("vehicleName",
											updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName());
								}

								if ((updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName() == null)										
										|| (updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName()
												.equals(""))
										|| (updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName()
												.equals(" "))) {

									LOG.info("detailedVehicleName - vehicleInfo.getVehicleModelName = null "
											+ vehicleInfo.getVehicleModelName());
									jsonObj.accumulate("detailedVehicleName", vehicleInfo.getVehicleModelName());

								} else {
									LOG.info("detailedVehicleName - vehicleInfo.getVehicleModelName ! = null "
											+ updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName());
									jsonObj.accumulate("detailedVehicleName",
											updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelName());
								}


								if (updateVehicleWrapper.getUpdateVehicle()
										.getVehicle().getNickname() != null) {
									jsonObj.accumulate("nickname",
											updateVehicleWrapper
											.getUpdateVehicle()
											.getVehicle().getNickname());
								} else {
									jsonObj.accumulate("nickname",
											ownerPortalUserVehicle
											.getVehicleNickName());
								}
								if (updateVehicleWrapper.getUpdateVehicle()
										.getVehicle().getModelCode() != null) {
									jsonObj.accumulate("modelIdentifier",
											updateVehicleWrapper
											.getUpdateVehicle()
											.getVehicle()
											.getModelCode());
								} else {
									jsonObj.accumulate("modelIdentifier",
											vehicleInfo.getVehicleModelCode());
								}

								if (updateVehicleWrapper.getUpdateVehicle()
										.getVehicle().getExteriorColorName() != null) {
									jsonObj.accumulate("colour",
											updateVehicleWrapper
											.getUpdateVehicle()
											.getVehicle()
											.getExteriorColorName());
								} else {
									jsonObj.accumulate("colour", vehicleInfo
											.getVehicleExteriorColorName());
								}

								if (updateVehicleWrapper.getUpdateVehicle()
										.getVehicle().getMileage() != null) {
									jsonObj.accumulate("mileage",
											updateVehicleWrapper
											.getUpdateVehicle()
											.getVehicle().getMileage());
								} else {
									jsonObj.accumulate("mileage",
											ownerPortalUserVehicle.getMileage());
								}
								if (updateVehicleWrapper.getUpdateVehicle()
										.getVehicle().getAverageMileage() != null) {
									jsonObj.accumulate("averageMileage",
											updateVehicleWrapper
											.getUpdateVehicle()
											.getVehicle()
											.getAverageMileage());
								} else {
									jsonObj.accumulate("averageMileage",
											ownerPortalUserVehicle
											.getAverageMileage());
								}

								if (!(updateVehicleWrapper.getUpdateVehicle()
										.getVehicle().getPreferredDealer() == null || updateVehicleWrapper
										.getUpdateVehicle().getVehicle()
										.getPreferredDealer().equals(""))) {
									//
									//									jsonObj.accumulate(DEALERID, "NNA"+ updateVehicleWrapper
									//													.getUpdateVehicle()
									//													.getVehicle().getDealerId()
									//													.trim());
									jsonObj.accumulate(PREFERRED_DEALER,"NNA"+ updateVehicleWrapper
											.getUpdateVehicle()
									.getVehicle().getPreferredDealer()
									.trim());
								}

								// Added by 455144 for Model year change
								if ((updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelYear() == null)
										|| (updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelYear().equals(""))
										|| (updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelYear().equals(" "))) {						
									jsonObj.accumulate("modelYear", vehicleInfo.getModelYearNumber());
								} else {
									jsonObj.accumulate("modelYear",
											updateVehicleWrapper.getUpdateVehicle().getVehicle().getModelYear());
								}

								// x566325 - Update updateVehicle API to include
								// Image URL's in the response
								jsonObj.accumulate("largeImage", largeImageUrl);
								jsonObj.accumulate("smallImage", smallImageUrl);

								result = SUCCESS;

							} else if (Utility
									.isStringNotNullorNotEmpty(response)
									&& ("otherUser").equalsIgnoreCase(response)) {
								LOG.info("Vehicle is already assigned to another user"
										+ vin);
								jsonObj.accumulate(CODE,
										VEHICLE_INVALID_USER_CODE);
								jsonObj.accumulate(MESSAGE,
										VEHICLE_INVALID_USER_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_INVALID_USER_DESCRIPTION);
								jsonFinalObj.accumulate("fault", jsonObj);
								result = FAILURE;

							}

						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							LOG.info("Invalid json request");
			              	isvalidJSON=false;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
		              	isvalidJSON=false;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
	              	isvalidJSON=false;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
              	isvalidJSON=false;
			}
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("Update vehicle JSON success response for the vin = " + vin
						+ " with email = " + email + " the response = "+jsonObj.toString());
				return Response.ok().status(200).entity(jsonObj.toString()).build();
			}
			// x566325 - Brand Segregation - Added emailNotAvailable condition
			else if (!isValidVin || !isValidBrand
					|| result.equalsIgnoreCase(FAILURE) || !isMakeNull
					|| !isValidEmail || !isValidAverageMileage || emailNotAvailable ||!isvalidJSON) {

				LOG.info("Update vehicle JSON failed response for the vin=" + vin
						+ " with email=" + email + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (generalError || !isValidPersonHashId) {
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {
				LOG.info("Update vehicle JSON unexpected failed response for the vin="
						+ vin + " with email=" + email + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JSONException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + " with email= "
					+ email + "is" + jsonFinalObj.toString()
					+ "General exception  update vehicle  ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		

	}

	/**
	 * @author rs101547
	 * @param brand
	 * @use To get Telematics features and SSO URL for a vin
	 * @param requestJson
	 * @method-POST
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getTelematics")
	public Response getTelematics(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Get telematics request = " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		OwnerPortalUser validUser;
		OwnerPortalVehicle vehicleInfo;
		// String vinMappedToSameUser = "";
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isValidMake = true;
		boolean isvalidJSON = true;
		String result = "";
		// Set<String> telematicsInfo;
		String vin = "";
		String email = "";
		String personHashId = "";
		String make = "";
		String isBlocking = "true";
		String vinStatus = "";
		int trialPeriod = 0;
		List<TelematicsSubscriptionVO> subscription;
		SubscriptionSsoTokenVO subscriptionSsoTokenVO;
		TelematicsVO telematicsVO;
		boolean vinNotAvailable = true;
		boolean emailNotAvailable = false;
		VehicleCarwings vehicleCarwings = null;
		OwnerPortalVehicle vehicleInfoCheck = null;
		Set<String> telematicsInfo = null;

		//x116202
		OwnerPortalVehicleTelematicsCodeMaster telematicsCodeInfo;
		//
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {

				GetTelematicsWrapper getTelematicsWrapper = mapper.readValue(
						requestJson, GetTelematicsWrapper.class);

				isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
				if (Utility.isObjectNotNullorNotEmpty(getTelematicsWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(getTelematicsWrapper
							.getGetTelematics())) {

						if (Utility
								.isObjectNotNullorNotEmpty(getTelematicsWrapper
										.getGetTelematics().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(getTelematicsWrapper
										.getGetTelematics()
										.getVehicle())) {
							vin = getTelematicsWrapper.getGetTelematics()
									.getVehicle().getVin();
							email = getTelematicsWrapper.getGetTelematics()
									.getPerson().getEmail();
							personHashId = getTelematicsWrapper
									.getGetTelematics().getPerson()
									.getPersonHashId();

							make = getTelematicsWrapper.getGetTelematics()
									.getVehicle().getMake();
							vinStatus = getTelematicsWrapper.getGetTelematics()
									.getVehicle().getStatus();
							LOG.info("Get telematics request - vinStatus: " + vinStatus);
							LOG.info("Get telematics request - vin: " + vin);
							vehicleInfoCheck = vehicleLocal.getVehicleInfo(vin);
							//LOG.info("Get telematics request - vehicleInfoCheck telematics code: " + vehicleInfoCheck.getVehicleTelematicsCode());
							//LOG.info("Get telematics request - vehicleInfoCheck model code: " + vehicleInfoCheck.getVehicleModelCode());
							//LOG.info("Get telematics request - vehicleInfoCheck make code: " + vehicleInfoCheck.getVehicleMakeCode());
							telematicsInfo = vehicleLocal.getTelematicsInfo(vehicleInfoCheck);
							LOG.info("Get telematics request - telematicsInfo"+ telematicsInfo);
							if (getTelematicsWrapper.getGetTelematics()
									.getVehicle().getTrialPeriod() != null) {
								if (getTelematicsWrapper.getGetTelematics()
										.getVehicle().getTrialPeriod().length() > 0) {
									trialPeriod = Integer
											.parseInt(getTelematicsWrapper
													.getGetTelematics()
													.getVehicle()
													.getTrialPeriod());
								}
							}
							vinStatus = getTelematicsSubscriptionStatus(
									vinStatus, trialPeriod);
							LOG.info("Get telematics request - getTelematicsSubscriptionStatus - vinStatus: " + vinStatus);
							if (Utility
									.isObjectNotNullorNotEmpty(getTelematicsWrapper
											.getGetTelematics()
											.getSubscription())) {
								LOG.info(" Subscription is not null for vin="
										+ getTelematicsWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								subscription = getTelematicsWrapper
										.getGetTelematics().getSubscription();
							} else {
								LOG.info(" Subscription is  null for vin="
										+ getTelematicsWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								subscription = null;
							}

							if (Utility
									.isObjectNotNullorNotEmpty(getTelematicsWrapper
											.getGetTelematics().getSsoToken())) {
								LOG.info("ssoToken is not null for vin="
										+ getTelematicsWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								subscriptionSsoTokenVO = getTelematicsWrapper
										.getGetTelematics().getSsoToken();
							} else {
								LOG.info("ssoToken is  null for vin="
										+ getTelematicsWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								subscriptionSsoTokenVO = null;
							}
							// x566325 - New VCS SXM SSO Enrollment URL changes
							if (Utility
									.isObjectNotNullorNotEmpty(getTelematicsWrapper
											.getGetTelematics().getTelematics())) {
								LOG.info("telematics is not null for vin="
										+ getTelematicsWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								telematicsVO = getTelematicsWrapper
										.getGetTelematics().getTelematics();
							} else {
								LOG.info("telematics is  null for vin="
										+ getTelematicsWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								telematicsVO = null;
							}

							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidMake = isMakeNull(make, jsonObj,
												jsonFinalObj);
									}

								} else {
									LOG.info("Brand is neither Nissan or Infiniti");

									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand =false;
								}
							}

							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidMake) {

								if (Utility.isStringNotNullorNotEmpty(make)
										&& make.equalsIgnoreCase(BRAND_NISSAN)
										|| make.equalsIgnoreCase(BRAND_INFINITI)) {

									// x566325 - Brand Segregation - send brand
									// also
									/*
									 * validUser =
									 * userLocal.validateEmail(email,
									 * personHashId);
									 */

									validUser = userLocal.validateEmail(email,
											personHashId, brand);
									vehicleInfo = vehicleLocal
											.getVehicleInfo(getTelematicsWrapper
													.getGetTelematics()
													.getVehicle().getVin());
									//x116202 - OS-1742
									telematicsCodeInfo = vehicleLocal.getTelematicsCodeInfo(vehicleInfo);
									//

									// x566325 - Email not available in database
									// case
									if (!Utility
											.isObjectNotNullorNotEmpty(validUser)) {

										Util.setFaultDataToJSON(
												jsonObj,
												jsonFinalObj,
												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
										emailNotAvailable = true;
									} else if (Utility
											.isObjectNotNullorNotEmpty(validUser)
											&& Utility
											.isObjectNotNullorNotEmpty(telematicsVO)) {
										// x566325 - removed below validations

										/*
										 * vinMappedToSameUser = vehicleLocal
										 * .validateVehicleOwner( validUser
										 * .getUserProfileId(),
										 * getTelematicsWrapper
										 * .getGetTelematics() .getVehicle()
										 * .getVin());
										 */

										/*
										 * if (("sameUser")
										 * .equalsIgnoreCase(vinMappedToSameUser
										 * )) { telematicsInfo = vehicleLocal
										 * .getTelematicsInfo(vehicleInfo);
										 */
										// x055765 - included model year for
										// telematics
										if (!Utility
												.isObjectNotNullorNotEmpty(telematicsVO)) {
											LOG.info("============telematicsVO not found case========");
											Util.setFaultDataToJSON(jsonObj,
													jsonFinalObj,
													INVALID_FEATURE_CODE,
													INVALID_FEATURE_MESSAGE,
													INVALID_FEATURE_DESCRIPTION);
											result = "not found";
										} else {
											if (telematicsVO
													.getInfinitiIntouchwithWifiHotspot()
													.equalsIgnoreCase("true")
													|| telematicsVO
													.getNissanConnectwithWifiHotspot()
													.equalsIgnoreCase(
															"true")) {
												LOG.info("calling AT&T wifi services for "
														+ vin);

												List<TermsAndConditionsAgreementSt> termsAndConditionsAgreementSt;
												termsAndConditionsAgreementSt = vehicleLocal
														.getTermsAndConditionsAgreementSt(
																vin,
																validUser
																.getUserProfileId());
												if (termsAndConditionsAgreementSt != null) {
													if (termsAndConditionsAgreementSt
															.size() > 0) {
														for (int i = 0; i < termsAndConditionsAgreementSt
																.size(); i++) {
															if (termsAndConditionsAgreementSt
																	.get(i)
																	.getId()
																	.getTermCndtnSrcCd()
																	.equalsIgnoreCase(
																			"A")) {
																isBlocking = "false";
															}
														}
													} else {
														isBlocking = "true";
													}
												} else {
													isBlocking = "true";
												}
												jsonFinalObj = setGetATTWifiResponse(
														vin, telematicsVO,
														validUser, isBlocking,
														telematicsCodeInfo, make, vinStatus);
												if (jsonFinalObj.length() > 0) {
													result = SUCCESS;
												} else {
													LOG.info("User="
															+ email
															+ " or vin="
															+ vin
															+ " not available in db for null subscription");
													jsonFinalObj
													.accumulate("msg",
															INVALID_CONNECTED_SERVICES_VIN_DESCRIPTION);
													result = SUCCESS;

												}

											} else {
												LOG.info("before calling get telematics response for sub="
														+ subscription);
												LOG.info("before calling get telematics response for sso="
														+ subscriptionSsoTokenVO);
												LOG.info("before calling get telematics response for tel="
														+ telematicsVO);
												if (Utility
														.isObjectNotNullorNotEmpty(subscription)
														|| Utility
														.isObjectNotNullorNotEmpty(subscriptionSsoTokenVO)
														|| Utility
														.isObjectNotNullorNotEmpty(telematicsVO)) {

													if (Utility
															.isObjectNotNullorNotEmpty(subscription)
															&& !(subscription
																	.isEmpty())) {

														// x566325 - New SSO
														// SAML
														// Url changes for I2,
														// I3,
														// I4 added
														if (Utility
																.isObjectNotNullorNotEmpty(subscriptionSsoTokenVO)
																&& Utility
																.isObjectNotNullorNotEmpty(telematicsVO)) {
															LOG.info("before calling get telematics response for vin"
																	+ vin);
															jsonFinalObj = setGetTelematicsResponse(
																	subscription,
																	subscriptionSsoTokenVO,
																	make,
																	vin,
																	telematicsVO,
																	validUser,
																	vehicleInfo,
																	vinStatus,telematicsInfo);
															LOG.info("After calling get telematics response for vin"
																	+ getTelematicsWrapper
																	.getGetTelematics()
																	.getVehicle()
																	.getVin());
															if (jsonFinalObj
																	.length() > 0) {
																result = SUCCESS;
															} else {

																LOG.info("User="
																		+ email
																		+ " or vin="
																		+ vin
																		+ "subcription available and vin does not have any telematic feature");
																Util.setFaultDataToJSON(
																		jsonObj,
																		jsonFinalObj,
																		INVALID_FEATURE_CODE,
																		INVALID_FEATURE_MESSAGE,
																		INVALID_FEATURE_DESCRIPTION);
																// jsonFinalObj.accumulate("msg",
																// INVALID_CONNECTED_SERVICES_VIN_DESCRIPTION);

																result = "not found";

															}

														} else {
															LOG.info("User="
																	+ email
																	+ " or vin="
																	+ vin
																	+ " not available in db for which subscription code is invalid");
															Util.setFaultDataToJSON(
																	jsonObj,
																	jsonFinalObj,
																	GENERAL_ERROR_FAULT_CODE,
																	GENERAL_ERROR_MESSAGE,
																	GENERAL_ERROR_DESCRIPTION);
															isvalidJSON=false;

														}

													} else {
														// x566325 - New SSO
														// SAML
														// Url changes for I2,
														// I3,
														// I4 added
														if (Utility
																.isObjectNotNullorNotEmpty(subscriptionSsoTokenVO)
																&& Utility
																.isObjectNotNullorNotEmpty(telematicsVO)) {
															jsonFinalObj = setGetTelematicsResponseWithoutSubscription(
																	telematicsVO,
																	subscriptionSsoTokenVO,
																	make,
																	vin,
																	validUser,
																	vehicleInfo,
																	vinStatus,telematicsInfo);
															if (jsonFinalObj
																	.length() > 0) {
																result = SUCCESS;

															} else {

																LOG.info("User="
																		+ email
																		+ " or vin="
																		+ vin
																		+ " not available in db for null subscription");
																Util.setFaultDataToJSON(
																		jsonObj,
																		jsonFinalObj,
																		INVALID_FEATURE_CODE,
																		INVALID_FEATURE_MESSAGE,
																		INVALID_FEATURE_DESCRIPTION);
																// jsonFinalObj.accumulate("msg",
																// INVALID_CONNECTED_SERVICES_VIN_DESCRIPTION);

																result = "not found";

															}
														} else {
															LOG.info("User="
																	+ email
																	+ " or vin="
																	+ vin
																	+ " not available in db for sso token null");
															LOG.info("============not found case 2========");
															Util.setFaultDataToJSON(
																	jsonObj,
																	jsonFinalObj,
																	INVALID_FEATURE_CODE,
																	INVALID_FEATURE_MESSAGE,
																	INVALID_FEATURE_DESCRIPTION);

															result = "not found";
														}
													}

												} else {
													LOG.info("User="
															+ email
															+ " or vin="
															+ vin
															+ " not available in db");
													LOG.info("============not found case 3========");
													Util.setFaultDataToJSON(
															jsonObj,
															jsonFinalObj,
															INVALID_FEATURE_CODE,
															INVALID_FEATURE_MESSAGE,
															INVALID_FEATURE_DESCRIPTION);

													result = "not found";

												}
											}
										}
									}
									// x566325 - removed below conditions

									// end of same user
									/*
									 * else if (("otherUser")
									 * .equalsIgnoreCase(vinMappedToSameUser)) {
									 * 
									 * Util.setFaultDataToJSON(jsonObj,
									 * jsonFinalObj, VEHICLE_MISMATCH_CODE,
									 * VEHICLE_MISMATCH_MESSAGE,
									 * VEHICLE_MISMATCH_DESCRIPTION);
									 * 
									 * result = FAILURE;
									 * 
									 * }
									 *//*
									 * else {
									 * 
									 * LOG.info("User=" + email + " or vin="
									 * + vin + " not available in db");
									 * Util.setFaultDataToJSON( jsonObj,
									 * jsonFinalObj,
									 * VEHICLE_NOTAVAILABLE_VINANDUSER_CODE,
									 * VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE
									 * ,
									 * VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION
									 * );
									 * 
									 * vinNotAvailable = true;
									 * 
									 * }
									 */
									else {
										Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
												INVALID_JSON_REQUEST_DESCRIPTION);
										LOG.info("Invalid json request");
									
									     isvalidJSON=false;

									}

								} else {
									Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
											GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
											INVALID_JSON_REQUEST_DESCRIPTION);
									LOG.info("Invalid json request");
								
								     isvalidJSON=false;

								}
							}else{
								isvalidJSON=false;
							}

						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							LOG.info("Invalid json request");
						
						     isvalidJSON=false;

						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
					
					     isvalidJSON=false;


					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
				
				     isvalidJSON=false;

				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
			     isvalidJSON=false;
			}
			
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("getTelematics JSON success response for the vin = " + vin
						+ " with email = " + email + " response = "+ jsonFinalObj.toString());
				return Response.ok().status(200).entity(jsonFinalObj.toString())
						.build();
			}
			// x566325 - Brand Segregation - Added emailNotAvailable condition
			else if (!isValidVin || !isValidBrand
					|| result.equalsIgnoreCase(FAILURE) || !isValidMake
					|| !isValidEmail || emailNotAvailable || !isvalidJSON) {
				LOG.info("valid casesss------------ ");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (!vinNotAvailable || ("not found").equalsIgnoreCase(result)) {
				LOG.info("getTelematics JSON failed response for the vin=" + vin
						+ " =" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
				LOG.info("getTelematics JSON unexpected failed response for the vin="
						+ vin + " =" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			
		} catch (JsonParseException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside IOException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "General exception  getTelematics  ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		

	}

	/**
	 * @author x497432 - Balaji O
	 * @use To get list of message ids related to the provided connected service
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getTelematicsDetails")
	public Response getTelematicsDetails(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Get telematics details request = " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;

		List<OwnerPortalUserVehicle> ownerPortalUserVehicles;
		List<TermsAndConditionsAgreementSt> termsAndConditionsAgreementSt;
		String isBlocking = "true";
		OwnerPortalVehicle vehicleInfo;
		boolean isValidMake = true;
		String status = "";
		String endDate = "";
		String result = "";
		String vin = "";
		String email = "";
		String id = "";
		int trialPeriod = 0;
		boolean vinNotAvailable = true;
		boolean emailNotAvailable = false;
		boolean isvalidJSON = true;

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {

				GetTelematicsDetailWrapper getTelematicsWrapper = mapper
						.readValue(requestJson,
								GetTelematicsDetailWrapper.class);

				isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
				if (Utility.isObjectNotNullorNotEmpty(getTelematicsWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(getTelematicsWrapper
							.getGetTelematicsDetails())) {

						vin = getTelematicsWrapper.getGetTelematicsDetails()
								.getVehicle().getVin();

						id = getTelematicsWrapper.getGetTelematicsDetails()
								.getRequestedService().getId();
						status = getTelematicsWrapper.getGetTelematicsDetails()
								.getVehicle().getStatus();
						if (getTelematicsWrapper.getGetTelematicsDetails()
								.getVehicle().getTrialPeriod() != null) {
							if (getTelematicsWrapper.getGetTelematicsDetails()
									.getVehicle().getTrialPeriod().length() > 0) {
								trialPeriod = Integer
										.parseInt(getTelematicsWrapper
												.getGetTelematicsDetails()
												.getVehicle().getTrialPeriod());
							}
						}
						/*endDate = getTelematicsWrapper
								.getGetTelematicsDetails()
								.getRequestedService().getEndDate();*/
						endDate = "";
						status = getTelematicsSubscriptionStatus(status,
								trialPeriod);
						if (isValidBrand) {
							if (brand.equalsIgnoreCase(BRAND_INFINITI)
									|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
								isValidBrand = true;
								isValidVin = isVinNull(vin, jsonObj,
										jsonFinalObj);

								if (isValidVin) {
									isValidVin = isVinValid(vin, jsonObj,
											jsonFinalObj);
								}

							} else {
								LOG.info("Brand is neither Nissan or Infiniti");

								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_CODE,
										VALIDATION_FAILED_MESSAGE,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								isValidBrand = false;

							}
						}else{
							isValidBrand = false;
						}

						if (isValidBrand && isValidVin) {

							// x566325 - Brand Segregation - send brand
							// also
							/*
							 * validUser = userLocal.validateEmail(email,
							 * personHashId);
							 */

							vehicleInfo = vehicleLocal
									.getVehicleInfo(getTelematicsWrapper
											.getGetTelematicsDetails()
											.getVehicle().getVin());

							if (!Utility.isObjectNotNullorNotEmpty(vehicleInfo)) {

								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
										VALIDATION_FAILED_MESSAGE,
										VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
								emailNotAvailable = true;
							} else if (Utility
									.isObjectNotNullorNotEmpty(vehicleInfo)) {
								System.out.println("vin " + vin);
								ownerPortalUserVehicles = vehicleLocal
										.getOwnerPortalUserVehicles(vin);
								System.out
								.println(" ownerPortalUserVehicles.size() "
										+ ownerPortalUserVehicles
										.size());

								// telematicsInfo =
								// vehicleLocal.getTelematicsInfo(vehicleInfo);

								Set<String> msgIds = new HashSet<String>();

								if (id.equalsIgnoreCase(NISSANCONNECTEV)) {
									termsAndConditionsAgreementSt = vehicleLocal
											.getTermsAndConditionsAgreementSt(
													vin,
													ownerPortalUserVehicles
													.get(0)
													.getOwnerPortalUserVehiclePK()
													.getUserProfileId());
									if (termsAndConditionsAgreementSt != null) {
										if (termsAndConditionsAgreementSt.size() > 0) {

											// ArrayList<String> check = new
											// ArrayList<String>();

											for (int i = 0; i < termsAndConditionsAgreementSt
													.size(); i++) {

												if (termsAndConditionsAgreementSt
														.get(i).getId()
														.getTermCndtnSrcCd()
														.equalsIgnoreCase("A")) {
													isBlocking = "false";
												}
											}
										} else {
											isBlocking = "true";
										}

									} else {
										isBlocking = "true";
									}
									endDate = "";
									VehicleCarwings vehicleCarwings = vehicleLocal
											.getVehicleCarwings(vin);
									if (vehicleCarwings != null) {
										if (vehicleCarwings
												.getCarwingsAgreementStatusCode()
												.equalsIgnoreCase("ACCEPTED")) {
											status = ACTIVE;
										} else {
											status = ELIGIBLE;
											isBlocking = "true";
										}
									} else {
										status = ELIGIBLE;
										isBlocking = "true";
									}
								}
								if((id.equalsIgnoreCase(NISSANCONNECTSERVICES)) || 
										(id.equalsIgnoreCase(INFINFITICONNECTION)) ||
										(id.equalsIgnoreCase(INFINITIINTOUCHSERVICES))) {
									if(status.contentEquals(ACTIVE)) {
										isBlocking = "false";
									}else {
										isBlocking = "true";
									}

								}

								if (isBlocking.equalsIgnoreCase("true")) {
									if (id.equalsIgnoreCase(NISSANCONNECTSERVICES)) {
										msgIds.add(NISSANCONNECTSERVICES_MSG_ID);
										//									} else if (id
										//											.equalsIgnoreCase(NISSANCONNECTNAVIGATION)) {
										//										msgIds.add(NISSANCONNECTNAVIGATION_MSG_ID);
									} else if (id
											.equalsIgnoreCase(INFINFITICONNECTION)) {
										msgIds.add(INFINFITICONNECTION_MSG_ID);
									} else if (id
											.equalsIgnoreCase(INFINITIINTOUCHSERVICES)) {
										msgIds.add(INFINITIINTOUCHSERVICES_MSG_ID);
									} else if (id
											.equalsIgnoreCase(NISSANCONNECTEV)) {
										msgIds.add(NISSANCONNECTEV_MSG_ID);
									}
									msgIds.add(COMMON_MSG_ID);
								}

								System.out.println(" profile id"
										+ ownerPortalUserVehicles.get(0)
										.getOwnerPortalUserVehiclePK()
										.getUserProfileId());
								OwnerPortalUser ownerPortalUser = vehicleLocal
										.getOwnerPortalUser(ownerPortalUserVehicles
												.get(0)
												.getOwnerPortalUserVehiclePK()
												.getUserProfileId());
								jsonFinalObj = setGetTelematicsDetailsResponse(
										id, status, endDate, vin, msgIds,
										ownerPortalUser, vehicleInfo);

								return Response.ok().status(200)
										.entity(jsonFinalObj.toString())
										.build();
							}
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
						
					        isvalidJSON=false;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
					
				        isvalidJSON=false;

				}
			} else {
				
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
				
			        isvalidJSON=false;
			}
			
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("getTelematics details JSON success response for the vin = "
						+ vin + " with email = " + email + " response = " +jsonFinalObj.toString());
				return Response.ok().status(200).entity(jsonFinalObj.toString())
						.build();
			}
			// x566325 - Brand Segregation - Added emailNotAvailable condition
			else if (!isValidVin || !isValidBrand
					|| result.equalsIgnoreCase(FAILURE) || !isValidMake
					|| emailNotAvailable ||!isvalidJSON) {
				LOG.info("valid casesss------------ ");
				LOG.info("getTelematics details JSON failed response for the vin="
						+ vin + " =" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (!vinNotAvailable || ("not found").equalsIgnoreCase(result)) {
				LOG.info("not found case------------ ");
				LOG.info("getTelematics details JSON failed response for the vin="
						+ vin + " =" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {
				LOG.info("general error case------------ ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {


			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {


			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside IOException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "General exception  getTelematics details  ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		

	}

	/**
	 * @author x497432 - Balaji O
	 * @use To accumulate the json response for Message ID
	 * @param status
	 *            of the connected service
	 * @param endDate
	 *            of theconnected service
	 * @param VIN
	 * @param SET
	 *            of Message IDs
	 * @param Owner
	 *            Portel User Object
	 * @param Owner
	 *            Portel Vehicle Object
	 * @return jsonObj
	 */
	private JSONObject setGetTelematicsDetailsResponse(String id,
			String status, String endDate, String vin, Set<String> msgIds,
			OwnerPortalUser ownerPortalUser,
			OwnerPortalVehicle ownerPortalVehicle) throws JSONException,
	InvalidKeyException, UnsupportedEncodingException,
	NoSuchAlgorithmException, NoSuchPaddingException,
	IllegalBlockSizeException, BadPaddingException {

		String encryptedVin = "";
		JSONObject jsonFinalObj = new JSONObject();
		jsonFinalObj.accumulate("id", id);
		jsonFinalObj.accumulate("status", status);
		jsonFinalObj.accumulate("endDate", "");
		LOG.info("New war is deployed>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// to be enabled only if the PACE/UI team asks for the URL in the
		// Connected service details call
		// x497432 Balaji O
		if (id.equals(NISSANCONNECTSERVICES)) {
			encryptedVin = Util.getAESEncryptedVin(vin, NISSAN_ENCRYPTEDKEY);
			//encryptedVin = vin;
			encryptedVin = encryptedVin.trim();
			String encodedVin = URLEncoder.encode(encryptedVin, "UTF-8");

			jsonFinalObj.accumulate("url", NISSANCONNECT_SERVICES_SSO_URL
					+ "?vehicleId="+encodedVin+"&lang=en-us"+"&brand=N"+"&country=us");
		} else if (id.equals(INFINITIINTOUCHSERVICES)) {
			encryptedVin = Util.getAESEncryptedVin(vin, INFINITI_ENCRYPTEDKEY);
			//encryptedVin = vin;
			encryptedVin = encryptedVin.trim();
			String encodedVin = URLEncoder.encode(encryptedVin, "UTF-8");
			jsonFinalObj.accumulate("url", INFINITI_INTOUCH_SERVICES_SSO_URL
					+ "?vehicleId="+encodedVin+"&lang=en-us"+"&brand=I"+"&country=us");
		} else if (id.equals(INFINFITICONNECTION)) {
			encryptedVin = Util.getAESEncryptedVin(vin, INFINITI_ENCRYPTEDKEY);
			//encryptedVin = vin;
			encryptedVin = encryptedVin.trim();
			String encodedVin = URLEncoder.encode(encryptedVin, "UTF-8");
			jsonFinalObj.accumulate("url", INFINITI_CONNECTION_URL
					+ "?vehicleId="+encodedVin+"&lang=en-us"+"&brand=I"+"&country=us");
		} else if (id.equals(NISSANCONNECTEV)) {
			jsonFinalObj.accumulate("url", NISSAN_EV_URL);
		} else if (id.equals(NISSANCONNECTNAVIGATION)) {
			String token = createToken(ownerPortalUser, ownerPortalVehicle);
			if (Utility.isStringNotNullorNotEmpty(token)) {
				jsonFinalObj.accumulate("url", NISSANCONNECT_SSO_URL + "token="
						+ token + "&regionId=NNA&selectedVin=" + vin);
			}
		}
		jsonFinalObj.put("messages", msgIds);
		return jsonFinalObj;
	}

	/**
	 * @author x497432 - Balaji O
	 * @use To get Message Details information
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getMessageDetails")
	public Response getMessageDetails(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Get Message details request is" + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		List<OwnerPortalUserVehicle> ownerPortalUserVehicles;
		List<TermsAndConditionsAgreementSt> termsAndConditionsAgreementSt;
		OwnerPortalVehicle vehicleInfo;
		Set<String> telematicsInfo;

		boolean isValidMake = true;
		String isBlocking = "true";
		String result = "";
		String vin = "";
		String email = "";
		String msgId = "";
		String optionCode = "";
		boolean vinNotAvailable = true;
		boolean emailNotAvailable = false;
		boolean isEVVin = false;
		boolean isNSVin = false;
		boolean isNSEVVin = false;
		boolean isICVin = false;
		boolean isIITVin = false;
		boolean isvalidJSON = true;
		

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {

				GetMessageDetailWrapper getMessageDetailWrapper = mapper
						.readValue(requestJson, GetMessageDetailWrapper.class);

				isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
				if (Utility.isObjectNotNullorNotEmpty(getMessageDetailWrapper)) {
					if (Utility
							.isObjectNotNullorNotEmpty(getMessageDetailWrapper
									.getGetMessageDetails())) {

						vin = getMessageDetailWrapper.getGetMessageDetails()
								.getVehicle().getVin();
						msgId = getMessageDetailWrapper.getGetMessageDetails()
								.getMessageDetails().getMsgId();

						if (isValidBrand) {
							if (brand.equalsIgnoreCase(BRAND_INFINITI)
									|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
								isValidBrand = true;
								isValidVin = isVinNull(vin, jsonObj,
										jsonFinalObj);

								if (isValidVin) {
									isValidVin = isVinValid(vin, jsonObj,
											jsonFinalObj);
								}

							} else {
								LOG.info("Brand is neither Nissan or Infiniti");

								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_CODE,
										VALIDATION_FAILED_MESSAGE,
										VEHICLE_INVALID_BRAND_DESCRIPTION);

							}
						}

						if (isValidBrand && isValidVin) {

							vehicleInfo = vehicleLocal
									.getVehicleInfo(getMessageDetailWrapper
											.getGetMessageDetails()
											.getVehicle().getVin());

							if (!Utility.isObjectNotNullorNotEmpty(vehicleInfo)) {

								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
										VALIDATION_FAILED_MESSAGE,
										VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
								emailNotAvailable = true;
							} else if (Utility
									.isObjectNotNullorNotEmpty(vehicleInfo)) {
								System.out.println("vin " + vin);
								if (msgId.equalsIgnoreCase(COMMON_MSG_ID)) {
									jsonFinalObj = setGetMessageDetailsCommonResponse(
											msgId, COMMON_MESSAGE_BODY);

									return Response.ok().status(200)
											.entity(jsonFinalObj.toString())
											.build();

								} else {
									String titleText = "";
									String bodyText = "";

									if (msgId
											.equalsIgnoreCase(NISSANCONNECTSERVICES_MSG_ID)) {
										titleText = NISSANCONNECTSERVICES_TITLE;
										bodyText = NISSANCONNECTSERVICES_BODY;
									} else if (msgId
											.equalsIgnoreCase(INFINFITICONNECTION_MSG_ID)) {
										titleText = INFINFITICONNECTION_TITLE;
										bodyText = INFINFITICONNECTION_BODY;
										//									} else if (msgId
										//											.equalsIgnoreCase(NISSANCONNECTNAVIGATION_MSG_ID)) {
										//										titleText = NISSANCONNECTNAVIGATION_TITLE;
										//										bodyText = NISSANCONNECTNAVIGATION_BODY;
									} else if (msgId
											.equalsIgnoreCase(NISSANCONNECTEV_MSG_ID)) {
										titleText = NISSANCONNECTEV_TITLE;
										bodyText = NISSANCONNECTEV_BODY;
									} else if (msgId
											.equalsIgnoreCase(INFINITIINTOUCHSERVICES_MSG_ID)) {
										titleText = INFINITIINTOUCHSERVICES_TITLE;
										bodyText = INFINITIINTOUCHSERVICES_BODY;
									}

									ownerPortalUserVehicles = vehicleLocal
											.getOwnerPortalUserVehicles(vin);
									System.out
									.println(" ownerPortalUserVehicles.size() "
											+ ownerPortalUserVehicles
											.size());

									telematicsInfo = vehicleLocal
											.getTelematicsInfo(vehicleInfo);

									LOG.info("telematicsInfo  for the VIN : "
											+ vin + " is :" + telematicsInfo);
									if (telematicsInfo.contains("I2")
											|| telematicsInfo.contains("I3")
											|| telematicsInfo.contains("I4")
											|| telematicsInfo.contains("IC") 
											|| telematicsInfo.contains("M3")
											|| telematicsInfo.contains("M4") 
											|| telematicsInfo.contains("M5")) {
										if (telematicsInfo.contains("IC")) {
											optionCode = "IC";
											isICVin=true;
										} else if(telematicsInfo.contains("I2")){
											optionCode = "IC";
											isIITVin=true;
										}
										else if(telematicsInfo.contains("I3")){
											optionCode = "IC";
											isIITVin=true;
										}
										else if(telematicsInfo.contains("I4")){
											optionCode = "IC";
											isIITVin=true;
										}
										else if(telematicsInfo.contains("M3")){
											optionCode = "IC";
											isIITVin=true;
										}
										else if(telematicsInfo.contains("M4")){
											optionCode = "IC";
											isIITVin=true;
										}
										else if(telematicsInfo.contains("M5")){
											optionCode = "IC";
											isIITVin=true;
										}
									} else if (telematicsInfo.contains("N2")
											|| telematicsInfo.contains("N3")
											|| telematicsInfo.contains("N4")
											|| telematicsInfo.contains("NS")
											|| telematicsInfo.contains("L2")
											|| telematicsInfo.contains("M6")
											|| telematicsInfo.contains("M7")
											|| telematicsInfo.contains("M8")
											|| telematicsInfo.contains("M9") 
											|| telematicsInfo.contains(E1)) {

										if (telematicsInfo.contains("CW")
												|| telematicsInfo
												.contains("L2")) {
											optionCode = "EV,NS";
											isNSEVVin=true;
										} else {
											optionCode = "NS";
											isNSVin=true;
										}
									} else if (telematicsInfo.contains("CW")) {
										optionCode = "EV";
										isEVVin = true;
									}

									LOG.info("optionCode  for the VIN : " + vin
											+ " is :" + optionCode);
									System.out
									.println(" profile id"
											+ ownerPortalUserVehicles
											.get(0)
											.getOwnerPortalUserVehiclePK()
											.getUserProfileId());

									isBlocking = "true";

									TelematicstermsAndConditions telematics;
									String termsandConditionsStr = "";
									if (optionCode.equalsIgnoreCase("EV,NS")) {
										//isEVVin = true;
										isNSEVVin=true;
										titleText = NISSANCONNECTEV_SERVICE_TITLE;
										telematics = vehicleLocal
												.getTelematicstermsAndConditions("NS");
										if (telematics != null) {
											termsandConditionsStr = termsandConditionsStr
													+ telematics
													.getTermCndtnCntntTx();
										}
										termsandConditionsStr = termsandConditionsStr
												+ "<br><hr style=\"height:2px;border:none;background-color:#333;\"><br>";
										telematics = vehicleLocal
												.getTelematicstermsAndConditions("EV");
										if (telematics != null) {
											termsandConditionsStr = termsandConditionsStr
													+ telematics
													.getTermCndtnCntntTx();
										}
									} else {
										telematics = vehicleLocal
												.getTelematicstermsAndConditions(optionCode);
										if (telematics != null) {
											termsandConditionsStr = telematics
													.getTermCndtnCntntTx();
										}
									}

									// OwnerPortalUser ownerPortalUser =
									// vehicleLocal
									// .getOwnerPortalUser(ownerPortalUserVehicles
									// .get(0)
									// .getOwnerPortalUserVehiclePK()
									// .getUserProfileId());
									jsonFinalObj = setGetMessageDetailsResponse(
											isBlocking, vin, msgId, titleText,
											termsandConditionsStr, isEVVin,isNSVin,isNSEVVin,isICVin,isIITVin);

									return Response.ok().status(200)
											.entity(jsonFinalObj.toString())
											.build();

								}
							}
						}

					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
					
					     isvalidJSON=false;

					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
				
				     isvalidJSON=false;

				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
			
			     isvalidJSON=false;

			}
			
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("get Message details JSON success response for the vin="
						+ vin + " with email = " + email +" response = " +jsonFinalObj.toString());
				return Response.ok().status(200).entity(jsonFinalObj.toString())
						.build();
			}
			// x566325 - Brand Segregation - Added emailNotAvailable condition
			else if (!isValidVin || !isValidBrand
					|| result.equalsIgnoreCase(FAILURE) || !isValidMake
					|| emailNotAvailable || !isvalidJSON ) {
				LOG.info("valid casesss------------ ");
				LOG.info("get Message details JSON failed response for the vin="
						+ vin + " =" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (!vinNotAvailable || ("not found").equalsIgnoreCase(result)) {
				LOG.info("not found case------------ ");
				LOG.info("get Message details JSON failed response for the vin="
						+ vin + " =" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside IOException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "General exception  get Message details  ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		

	}

	/**
	 * @author x497432 - Balaji O
	 * @use To accumulate the json response for Message ID
	 * @param Blocking
	 *            state of the message
	 * @param VIN
	 * @param Message
	 *            ID
	 * @param Owner
	 *            Portal User Object
	 * @param Owner
	 *            Portal Vehicle Object
	 * @param Message
	 *            Title text
	 * @param Message
	 *            Body Text
	 * @return jsonObj
	 */
	private JSONObject setGetMessageDetailsResponse(String blocking,
			String vin, String msgId, String titleText, String bodyText, boolean isEVVin,boolean isNSVin,boolean isNSEVVin,boolean isICVin,boolean isIITVin)
					throws JSONException, InvalidKeyException,
					UnsupportedEncodingException, NoSuchAlgorithmException,
					NoSuchPaddingException, IllegalBlockSizeException,
					BadPaddingException {

		JSONObject jsonFinalObj = new JSONObject();

		JSONObject option1 = new JSONObject();

		jsonFinalObj.accumulate("id", msgId);

		if (blocking.equalsIgnoreCase("true")) {
			jsonFinalObj.accumulate("blocking", true);
		} else if (blocking.equalsIgnoreCase("false")) {
			jsonFinalObj.accumulate("blocking", false);
		}

		jsonFinalObj.accumulate("title", titleText);
		jsonFinalObj.accumulate("body", bodyText);
		if(isEVVin){
			jsonFinalObj.accumulate("readConfirmationLabel",
					"I have read and accept "
					//+ "<a href=\"https://owners.nissanusa.com/nowners/doc/documents/NISSAN_LEAF_Telematics_Agreement_and_Consent.pdf\" target=\"_blank\"> NissanConnect<sup>&#174;</sup> EV Terms & Conditions </a>"
					+ "NissanConnect® EV Terms and Conditions");
			//+ "and the "
			//+ "<a href=\"https://owners.nissanusa.com/nowners/doc/documents/NISSAN_LEAF_Telematics_Agreement_and_Consent.pdf\" target=\"_blank\"> </a>"
			//+ "Nissan LEAF� Telematics Subscription Services Agreement and Consent"
		}else if(isNSVin){
			jsonFinalObj.accumulate("readConfirmationLabel",
					"I have read and accept "
							+"NissanConnect® Services Terms and Conditions.");
			//+ "<a href=\"https://owners.nissanusa.com/nowners/doc/documents/NISSAN_LEAF_Telematics_Agreement_and_Consent.pdf\" target=\"_blank\"> NissanConnect<sup>&#174;</sup> Services Terms & Conditions. </a>"
		}
		else if(isNSEVVin){
			jsonFinalObj.accumulate("readConfirmationLabel",
					"I have read and accept "
					//+ "<a href=\"https://owners.nissanusa.com/nowners/doc/documents/NISSAN_LEAF_Telematics_Agreement_and_Consent.pdf\" target=\"_blank\"> NissanConnect<sup>&#174;</sup> EV & Services Terms and Conditions </a>"
					+ "NissanConnect® EV and Service Terms and Conditions.");
			jsonFinalObj.accumulate("readConfirmationLabel1",
					"I have read and accept "
							+ "the "
							+ "<a href=\"https://owners.nissanusa.com/nowners/doc/documents/NISSAN_LEAF_Telematics_Agreement_and_Consent.pdf\" target=\"_blank\"> Nissan LEAF<sup>&#174;</sup> Telematics Subscription Services Agreement and Consent.</a>");
		}
		else if(isICVin){
			jsonFinalObj.accumulate("readConfirmationLabel",
					"I have read and accept "
							+"Infiniti Connection® Terms and Conditions.");
		}
		else if(isIITVin){
			jsonFinalObj.accumulate("readConfirmationLabel",
					"I have read and accept "
							+"Infiniti Intouch® Services Terms and Conditions.");
		}
		else{
			jsonFinalObj.accumulate("readConfirmationLabel",
					"I have read and accept the terms and conditions");
		}

		option1.put("label", "Accept");
		option1.put("action", "accept");
		jsonFinalObj.accumulate("options", option1);

		option1 = new JSONObject();
		option1.put("label", "Decline");
		option1.put("action", "decline");
		jsonFinalObj.accumulate("options", option1);

		return jsonFinalObj;
	}

	/**
	 * @author x497432 - Balaji O
	 * @use To accumulate the json response for Common Message ID
	 * @param Message
	 *            ID
	 * @param Bodt
	 *            Text
	 * @return jsonObj
	 */
	private JSONObject setGetMessageDetailsCommonResponse(String msgId,
			String bodyText) throws JSONException, InvalidKeyException,
	UnsupportedEncodingException, NoSuchAlgorithmException,
	NoSuchPaddingException, IllegalBlockSizeException,
	BadPaddingException {

		JSONObject jsonFinalObj = new JSONObject();

		JSONObject option1 = new JSONObject();

		jsonFinalObj.accumulate("id", msgId);
		jsonFinalObj.accumulate("blocking", false);

		jsonFinalObj.accumulate("body", bodyText);

		option1.put("label", "Continue");
		option1.put("action", "dismiss");

		JSONArray array = new JSONArray();
		array.put(0, option1);

		jsonFinalObj.accumulate("options", array);

		return jsonFinalObj;
	}

	/**
	 * @author x796314
	 * @use To get service contract information
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getServiceContract")
	public Response getServiceContract(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Service Contract request is" + requestJson);

		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		OwnerPortalUser validUser;
		OwnerPortalVehicle vehicleInfo;
		String vinMappedToSameUser = "";
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isValidMake = true;
		Set<String> telematicsInfo;
		String vin = "";
		String email = "";
		String make = "";
		String personHashId = "";
		List<TelematicsSubscriptionVO> subscription;
		String result = "";
		boolean generalError = false;
		boolean emailNotAvailable = false;
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				GetTelematicsWrapper serviceContractWrapper = mapper.readValue(
						requestJson, GetTelematicsWrapper.class);

				isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);
				if (Utility.isObjectNotNullorNotEmpty(serviceContractWrapper)) {
					if (Utility
							.isObjectNotNullorNotEmpty(serviceContractWrapper
									.getGetTelematics())) {

						if (Utility
								.isObjectNotNullorNotEmpty(serviceContractWrapper
										.getGetTelematics().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(serviceContractWrapper
										.getGetTelematics()
										.getVehicle())) {
							vin = serviceContractWrapper.getGetTelematics()
									.getVehicle().getVin();
							email = serviceContractWrapper.getGetTelematics()
									.getPerson().getEmail();
							personHashId = serviceContractWrapper
									.getGetTelematics().getPerson()
									.getPersonHashId();

							make = serviceContractWrapper.getGetTelematics()
									.getVehicle().getMake();
							if (Utility
									.isObjectNotNullorNotEmpty(serviceContractWrapper
											.getGetTelematics()
											.getSubscription())) {
								LOG.info("Subscription is not null for vin="
										+ serviceContractWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								subscription = serviceContractWrapper
										.getGetTelematics().getSubscription();
							} else {
								LOG.info("Subscription is  null for vin="
										+ serviceContractWrapper
										.getGetTelematics()
										.getVehicle().getVin());
								subscription = null;
							}

							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
									isValidBrand = true;
									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidMake = isMakeNull(make, jsonObj,
												jsonFinalObj);
									}

								} else {
									LOG.info("Brand is neither Nissan nor Infiniti");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VALIDATION_FAILED_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}

							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidMake) {

								if ((Utility.isStringNotNullorNotEmpty(make))
										&& make.equalsIgnoreCase(BRAND_NISSAN)
										|| make.equalsIgnoreCase(BRAND_INFINITI)) {

									// x566325 - Brand Segregation - send brand
									// also

									/*
									 * validUser =
									 * userLocal.validateEmail(email,
									 * personHashId);
									 */

									validUser = userLocal.validateEmail(email,
											personHashId, brand);

									vehicleInfo = vehicleLocal
											.getVehicleInfo(serviceContractWrapper
													.getGetTelematics()
													.getVehicle().getVin());
									// x566325 - Email not available in database
									// case
									if (!Utility
											.isObjectNotNullorNotEmpty(validUser)) {

										Util.setFaultDataToJSON(
												jsonObj,
												jsonFinalObj,
												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
										emailNotAvailable = true;

									} else if (Utility
											.isObjectNotNullorNotEmpty(validUser)
											&& Utility
											.isObjectNotNullorNotEmpty(vehicleInfo)) {

										vinMappedToSameUser = vehicleLocal
												.validateVehicleOwner(
														validUser
														.getUserProfileId(),
														serviceContractWrapper
														.getGetTelematics()
														.getVehicle()
														.getVin());

										if (("sameUser")
												.equalsIgnoreCase(vinMappedToSameUser)) {
											telematicsInfo = vehicleLocal
													.getTelematicsInfo(vehicleInfo);
											// x055765 - included model year for
											// telematics
											if (telematicsInfo.isEmpty()) {

												Util.setFaultDataToJSON(
														jsonObj,
														jsonFinalObj,
														INVALID_FEATURE_CODE,
														INVALID_FEATURE_MESSAGE,
														INVALID_FEATURE_DESCRIPTION);
												result = "not found";
											} else {
												if (Utility
														.isObjectNotNullorNotEmpty(subscription)
														|| !(telematicsInfo
																.isEmpty())) {
													if (Utility
															.isObjectNotNullorNotEmpty(subscription)
															&& !(subscription
																	.isEmpty())) {
														if ((telematicsInfo
																.contains(S2) || telematicsInfo
																.contains(NS))
																|| (telematicsInfo
																		.contains(NC)
																		|| telematicsInfo
																		.contains(IC)
																		|| telematicsInfo
																		.contains(EV) || telematicsInfo
																		.contains(CW))) {
															LOG.info("before calling get service contract response for vin"
																	+ vin);
															jsonFinalObj = setGetServiceContractResponse(
																	subscription,
																	make, vin,
																	telematicsInfo);

															LOG.info("After calling get service contract response for vin"
																	+ vin);
															if (jsonFinalObj
																	.length() > 0) {
																result = SUCCESS;

															} else {

																LOG.info("User="
																		+ email
																		+ " or vin="
																		+ vin
																		+ " not available in db");

																Util.setFaultDataToJSON(
																		jsonObj,
																		jsonFinalObj,
																		GENERAL_ERROR_FAULT_CODE,
																		GENERAL_ERROR_MESSAGE,
																		GENERAL_ERROR_DESCRIPTION);

															}

														} else {
															LOG.info("User="
																	+ email
																	+ " or vin="
																	+ vin
																	+ " not available in db");
															Util.setFaultDataToJSON(
																	jsonObj,
																	jsonFinalObj,
																	GENERAL_ERROR_FAULT_CODE,
																	GENERAL_ERROR_MESSAGE,
																	GENERAL_ERROR_DESCRIPTION);

														}

													} else {

														LOG.info("No subsciptions");
														if ((telematicsInfo
																.contains(S2) || telematicsInfo
																.contains(NS))
																|| (telematicsInfo
																		.contains(NC)
																		|| telematicsInfo
																		.contains(IC)
																		|| telematicsInfo
																		.contains(EV) || telematicsInfo
																		.contains(CW))) {
															jsonFinalObj = setSerivceContractResponseWithoutSubscription(
																	telematicsInfo,
																	make, vin);

															result = SUCCESS;
														} else {
															LOG.info("User="
																	+ email
																	+ " or vin="
																	+ vin
																	+ " not available in db");

															Util.setFaultDataToJSON(
																	jsonObj,
																	jsonFinalObj,
																	INVALID_FEATURE_CODE,
																	INVALID_FEATURE_MESSAGE,
																	INVALID_FEATURE_DESCRIPTION);
															result = "not found";

														}
													}

												} else {
													LOG.info("User="
															+ email
															+ " or vin="
															+ vin
															+ " not available in db");
													Util.setFaultDataToJSON(
															jsonObj,
															jsonFinalObj,
															GENERAL_ERROR_FAULT_CODE,
															GENERAL_ERROR_MESSAGE,
															GENERAL_ERROR_DESCRIPTION);

												}
											}
										}// end of same user
										else if (("otherUser")
												.equalsIgnoreCase(vinMappedToSameUser)) {
											Util.setFaultDataToJSON(jsonObj,
													jsonFinalObj,
													VEHICLE_MISMATCH_CODE,
													VEHICLE_MISMATCH_MESSAGE,
													VEHICLE_MISMATCH_DESCRIPTION);

											result = FAILURE;

										} else {

											LOG.info("User=" + email
													+ " or vin=" + vin
													+ " not available in db");
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VEHICLE_NOTAVAILABLE_VINANDUSER_CODE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE,
													VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);

											result = FAILURE;
										}
									} else {
										Util.setFaultDataToJSON(jsonObj,
												jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE,
												GENERAL_ERROR_MESSAGE,
												GENERAL_ERROR_DESCRIPTION);

										generalError = true;
									}

								} else {
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											GENERAL_ERROR_FAULT_CODE,
											GENERAL_ERROR_MESSAGE,
											GENERAL_ERROR_DESCRIPTION);

									generalError = true;
								}
							}

						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							LOG.debug("Request is null and response is"
									+ jsonFinalObj.toString());

							generalError = true;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());

						generalError = true;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());

					generalError = true;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());

				generalError = true;
			}
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "JsonParseException  service contract  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to fuse for the vin" + vin
					+ jsonFinalObj.toString()
					+ "JsonMappingException  service contract   ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "IOException  service contract  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "General exception service contract   ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {

			LOG.info("service contract  JSON success response for the vin="
					+ vin + " with email=" + email + jsonFinalObj.toString());
			return Response.ok().status(200).entity(jsonFinalObj.toString())
					.build();

		}
		// x566325 - Brand Segregation - Added emailNotAvailable condition below
		else if (!isValidVin || !isValidBrand
				|| result.equalsIgnoreCase(FAILURE) || !isValidEmail
				|| emailNotAvailable) {

			LOG.info("service contract  JSON failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (("not found").equalsIgnoreCase(result)) {
			LOG.info("service contract  JSON unexpected failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(404).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidMake || !isValidPersonHashId || generalError) {
			LOG.info("service contract  JSON unexpected failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("service contract  JSON unexpected failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}

	/**
	 * @author x875352
	 * @use To get recall service information
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getRecallService")
	public Response getRecallService(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		LOG.info("Recall Service request is" + requestJson);

		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONArray jsonFinal = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();

		boolean success = false;
		boolean isValidVin = true;
		boolean isValidBrand = false;
		String vin = "";

		List<OEMRecallDataVO> oemRecallDataVOList;

		try {
			isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj); // checking
			// if it
			// is
			// valid
			// brand
			// or
			// not

			if (isValidBrand) {
				if (brand.equalsIgnoreCase(BRAND_INFINITI)
						|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
					isValidBrand = true;
				} else {
					LOG.info("Brand is neither Nissan nor Infiniti");
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							VEHICLE_INVALID_BRAND_CODE,
							VEHICLE_INVALID_BRAND_MESSAGE,
							VEHICLE_INVALID_BRAND_DESCRIPTION);
					isValidBrand = false;
				}
			}
			if (isValidBrand) {

				if (Utility.isStringNotNullorNotEmpty(requestJson)) {

					RecallWrapper recallServiceWrapper = mapper.readValue(
							requestJson, RecallWrapper.class);

					if (Utility.isObjectNotNullorNotEmpty(recallServiceWrapper)) {

						oemRecallDataVOList = recallServiceWrapper
								.getOemRecall().getOemRecallDataVO();

						vin = recallServiceWrapper.getOemRecall().getVin();
						isValidVin = isVinNull(vin, jsonObj, jsonFinalObj);
						if (isValidVin) {

							isValidVin = isVinValid(vin, jsonObj, jsonFinalObj);
						}
						if (isValidVin) {

							if (Utility
									.isObjectNotNullorNotEmpty(oemRecallDataVOList)
									&& !(oemRecallDataVOList.isEmpty())) {
								LOG.info("Inside getRecallService the size of recalls is "
										+ oemRecallDataVOList.size());
								jsonFinal = getRecallServiceResponse(
										oemRecallDataVOList,
										recallServiceWrapper);

								if (jsonFinal.length() > 0) {
									jsonFinalObj = jsonFinalObj.accumulate(
											"recalls", jsonFinal);

								}

								success = true;

							}

						}
					}

				}

			}

		} catch (JsonParseException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "JsonParseException  recall service  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to fuse for the vin" + vin
					+ jsonFinalObj.toString()
					+ "JsonMappingException  recall service   ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to fuse for the vin" + vin + "is ="
							+ jsonFinalObj.toString()
							+ "IOException  recall service  ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Response to fuse for the vin" + vin + "is ="
					+ jsonFinalObj.toString()
					+ "General exception recall service   ", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (success) {

			LOG.info("Recall Service JSON success response for the request:"
					+ requestJson + jsonFinalObj.toString());
			return Response.ok().status(200).entity(jsonFinalObj.toString())
					.build();

		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Recall Service  JSON unexpected failed response for the request:"
					+ requestJson + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	/**
	 * @author x875352
	 * @use To generate response for recall service
	 * @param recallsVO
	 * @param typeDescription
	 * @return
	 */
	private JSONArray getRecallServiceResponse(
			List<OEMRecallDataVO> oemRecallDataVOList,
			RecallWrapper recallServiceWrapper) {
		JSONArray jsonOEMRecallServiceResponseObj = new JSONArray();
		OEMRecallDataVO oemRecallDataVO;

		try {

			for (Iterator<OEMRecallDataVO> iterator = oemRecallDataVOList
					.iterator(); iterator.hasNext();) {
				JSONObject jsonrecallserviceresponseObj = new JSONObject();
				oemRecallDataVO = (OEMRecallDataVO) iterator.next();

				jsonrecallserviceresponseObj.accumulate("vin",
						recallServiceWrapper.getOemRecall().getVin());
				jsonrecallserviceresponseObj
				.accumulate(
						"recallNumber",
						(oemRecallDataVO.getNhtsaRecallId() + "|" + oemRecallDataVO
								.getNnaRecallId()));
				jsonrecallserviceresponseObj.accumulate("title",
						(oemRecallDataVO.getRecallSecondaryDescription()));
				jsonrecallserviceresponseObj.accumulate(
						"description",
						"SUMMARY" + "-" + oemRecallDataVO.getDefectSummary()
						+ "|" + "RISK" + "-"
						+ oemRecallDataVO.getRiskofNotDoingRepair()
						+ "|" + "REMEDY" + "-"
						+ oemRecallDataVO.getRemedyDescription());
				jsonrecallserviceresponseObj.accumulate("type",
						oemRecallDataVO.getRecallTypeCode());
				jsonrecallserviceresponseObj.accumulate("date",
						oemRecallDataVO.getRecallEffectiveDate());
				jsonrecallserviceresponseObj.accumulate("status", Util.STATUS);

				jsonOEMRecallServiceResponseObj = jsonOEMRecallServiceResponseObj
						.put(jsonrecallserviceresponseObj);

			}
		} catch (JSONException e) {

			LOG.error("JSONException in getRecallServiceResponse for the vin"
					+ recallServiceWrapper.getOemRecall().getVin(), e);
		}
		return jsonOEMRecallServiceResponseObj;
	}

	/**
	 * @author x796314
	 * @use To check if nickname in proper format or not
	 * @param nickname
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isValidNickName(String nickName, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean isValidNickName = true;
		try {

			if (Utility.isStringNotNullorNotEmpty(nickName)) {

				LOG.debug("Nickname is not null");
				if ((nickName.length() > 14)
						|| (!(nickName.matches("^[A-Za-z0-9@&-_.,+' ]+$")))) {
					LOG.info("Nickname length is greater than 14"
							+ nickName.length());
					jsonObj.accumulate(CODE, INVALID_NICKNAME_CODE);
					jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
					jsonObj.accumulate(DESCRIPTION, NICKNAME_VALIDATION_FAILED);
					jsonFinalObj.accumulate("fault", jsonObj);
					isValidNickName = false;

				} // commented to avoid issues using chinese language in
				// nickname

			}
		} catch (JSONException e) {

			LOG.error("JSONException in isValidNickName for the nickname"
					+ nickName, e);

		}
		return isValidNickName;
	}

	/**
	 * @author x796314
	 * @use To check if mileage in proper format or not
	 * @param mileage
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isValidMileage(Integer mileage, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean isValidMileage = true;

		try {

			if (mileage != null) {

				if (!(mileage.toString().matches("[0-9]+"))) {

					LOG.info("mileage contains alphabets" + mileage);
					jsonObj.accumulate(CODE, INVALID_MILEAGE_CODE);
					jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
					jsonObj.accumulate(DESCRIPTION,
							INVALID_MILEAGE_FORMAT_DESCRIPTION);
					jsonFinalObj.accumulate("fault", jsonObj);
					isValidMileage = false;
				}

			}
		} catch (JSONException e) {
			LOG.error("JSONException in isValidMileage for the mileage"
					+ mileage, e);
		}

		return isValidMileage;
	}

	/**
	 * @author x796314
	 * @use To check if average mileage in proper format or not
	 * @param mileage
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isValidAverageMileage(Integer averagemileage,
			JSONObject jsonObj, JSONObject jsonFinalObj) {

		boolean isValidAverageMileage = true;

		try {

			if (averagemileage != null) {

				if (!(averagemileage.toString().matches("[0-9]+"))) {

					LOG.info("mileage contains alphabets" + averagemileage);
					jsonObj.accumulate(CODE, INVALID_MILEAGE_CODE);
					jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
					jsonObj.accumulate(DESCRIPTION,
							INVALID_MILEAGE_FORMAT_DESCRIPTION);
					jsonFinalObj.accumulate("fault", jsonObj);
					isValidAverageMileage = false;
				}

			}
		} catch (JSONException e) {
			LOG.error("JSONException in isValidAverageMileage for the mileage"
					+ averagemileage, e);
		}

		return isValidAverageMileage;
	}

	/**
	 * @author x178099
	 * @use To check if brand is null
	 * @param brand
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isBrandNull(String brand, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;
		if (!Utility.isStringNotNullorNotEmpty(brand)) {

			LOG.info("Brand is null");
			try {

				jsonObj.accumulate(CODE, VALIDATION_FAILED_CODE);
				jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
				jsonObj.accumulate(DESCRIPTION,
						VEHICLE_INVALID_BRAND_DESCRIPTION);
				jsonFinalObj.accumulate("fault", jsonObj);
				response = false;
			} catch (JSONException e) {
				LOG.error("JSONException in isBrandNull for the brand" + brand,
						e);
			}
		} else {
			LOG.info("Brand is valid" + brand);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To check if vin is null
	 * @param vin
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isVinNull(String vin, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;
		if (!Utility.isStringNotNullorNotEmpty(vin)) {

			LOG.info("Vin is null");
			try {

				jsonObj.accumulate(CODE, INVALID_VIN_CODE);
				jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
				jsonObj.accumulate(DESCRIPTION, VEHICLE_INVALID_VIN_DESCRIPTION);
				jsonFinalObj.accumulate(FAULT, jsonObj);
				response = false;
			} catch (JSONException e) {
				LOG.error("JSONException in isVinNull for the vin" + vin, e);
			}
		} else {
			LOG.info("Vin is valid" + vin);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To check if vin length is 17 character and contains I,O,Q characters
	 * @param vin
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isVinValid(String vin, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;

		try {
			if (Utility.isStringNotNullorNotEmpty(vin)) {
				LOG.info("Check if vin=" + vin + " is valid");
				if (vin.length() < 17 || vin.length() > 17) {
					LOG.info("Vin length is= " + vin.length());
					jsonObj.accumulate(CODE, INVALID_VIN_CODE);
					jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
					jsonObj.accumulate(DESCRIPTION,
							VEHICLE_INVALID_VINLENGTH_DESCRIPTION);
					jsonFinalObj.accumulate(FAULT, jsonObj);
					response = false;
				} else if (vin.toUpperCase().contains("I")
						|| vin.toUpperCase().contains("O")
						|| vin.toUpperCase().contains("Q")) {
					jsonObj.accumulate(CODE, INVALID_VIN_CODE);
					jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
					jsonObj.accumulate(DESCRIPTION,
							VEHICLE_INVALID_VINCONTENT_DESCRIPTION);
					jsonFinalObj.accumulate(FAULT, jsonObj);
					response = false;
				}

			}
		} catch (JSONException e) {
			LOG.error("JSONException in isValidVin", e);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To check if email is null
	 * @param email
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isEmailNull(String email, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;

		if (!Utility.isStringNotNullorNotEmpty(email)) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_EMAIL_CODE, VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_EMAIL_DESCRIPTION);
			response = false;
		} else {
			LOG.info("Email " + email + "is valid");
		}

		return response;
	}

	/**
	 * @author x875352
	 * @use To check if fileName is null
	 * @param fileName
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isValidFileName(String fileName, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;

		if (!Utility.isStringNotNullorNotEmpty(fileName)) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_FILENAME_CODE, VALIDATION_FAILED_MESSAGE,
					INVALID_FILE_NAME_DESCRIPTION);
			response = false;
		} else {
			LOG.info("File Name " + fileName + "is valid");
		}

		return response;
	}

	/**
	 * @author x875352
	 * @use To check if contentType is null and valid
	 * @param contentType
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */

	private boolean isValidContentType(String contentType, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		LOG.debug("ContentType " + contentType);
		boolean response = true;
		try {
			if (Utility.isStringNotNullorNotEmpty(contentType)) {
				LOG.debug("ContentType is not null");

				if (!(contentType.equals("image/jpeg")
						|| contentType.equals("image/png") || contentType
						.equals("application/pdf"))) {
					jsonObj.accumulate(CODE,
							VALIDATION_FAILED_CONTENT_TYPE_CODE);
					jsonObj.accumulate(MESSAGE,
							VALIDATION_FAILED_CONTENT_TYPE_MESSAGE);
					jsonObj.accumulate(DESCRIPTION,
							INVALID_CONTENT_TYPE_DESCRIPTION);
					jsonFinalObj.accumulate("fault", jsonObj);
					response = false;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_CONTENT_TYPE_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_CONTENTTYPE_DESCRIPTION);
				response = false;
			}
		} catch (JSONException e) {

			LOG.error("JSONException in isValidContentType for the contentType"
					+ contentType, e);

		}
		return response;
	}

	/**
	 * @author x875352
	 * @use To check if content is null and valid
	 * @param content
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */

	private boolean isValidContent(byte[] content, String contentType,
			JSONObject jsonObj, JSONObject jsonFinalObj)
					throws DocumentException, IOException {

		boolean response = true;

		try {
			LOG.info("Content may be null");
			long bArraySize = (long) content.length;
			LOG.info("before if condition in valid content bArraySize1"
					+ bArraySize);

			if (bArraySize != 0) {

				LOG.info("Content is not null");

				if (contentType.equals("application/pdf")) {

					if (bArraySize > 5242880) {
						LOG.debug("pdf is too long");
						jsonObj.accumulate(CODE, VALIDATION_FAILED_CONTENT_CODE);
						jsonObj.accumulate(MESSAGE,
								VALIDATION_FAILED_CONTENT_MESSAGE);
						jsonObj.accumulate(DESCRIPTION,
								VEHICLE_INVALID_CONTENT_DESCRIPTION);
						jsonFinalObj.accumulate("fault", jsonObj);
						response = false;

					}

				} else if ((contentType.equals("image/jpeg") || contentType
						.equals("image/png"))) {

					double byteArraySize = content.length;

					if (byteArraySize > 5242880) {
						LOG.debug("image is too long");
						jsonObj.accumulate(CODE, VALIDATION_FAILED_CONTENT_CODE);
						jsonObj.accumulate(MESSAGE,
								VALIDATION_FAILED_CONTENT_MESSAGE);
						jsonObj.accumulate(DESCRIPTION,
								VEHICLE_INVALID_CONTENT_DESCRIPTION);
						jsonFinalObj.accumulate("fault", jsonObj);
						response = false;
					}
				}
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_CONTENT_ERROR_CODE,
						GENERAL_ERROR_MESSAGE,
						VALIDATION_FAILED_CONTENT_ERROR_DESCRIPTION);
				response = false;

			}
		} catch (JSONException e) {

			LOG.error("JSONException in isValidContent for the contentType"
					+  e);

		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To check if personHashId is null
	 * @param personHashId
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isPersonhashidNull(String personHashId, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;

		LOG.info("Check if personHashId=" + personHashId + " is null");
		try {
			if (!Utility.isStringNotNullorNotEmpty(personHashId)) {
				LOG.info("PersonhashId is null ");
				jsonObj.accumulate(CODE, GENERAL_ERROR_FAULT_CODE);
				jsonObj.accumulate(MESSAGE, GENERAL_ERROR_MESSAGE);
				jsonObj.accumulate(DESCRIPTION, GENERAL_ERROR_DESCRIPTION);
				jsonFinalObj.accumulate("fault", jsonObj);
				response = false;
			} else {
				LOG.info("PersonhashId " + personHashId + " is valid ");
			}

		} catch (JSONException e) {
			LOG.error(
					"JSONException in isPersonhashidNull for the personHashId"
							+ personHashId, e);
		}

		return response;
	}

	private boolean isMakeNull(String make, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;

		try {
			if (!Utility.isStringNotNullorNotEmpty(make)) {
				LOG.info("Make is null");
				jsonObj.accumulate(CODE, GENERAL_ERROR_FAULT_CODE);
				jsonObj.accumulate(MESSAGE, GENERAL_ERROR_MESSAGE);
				jsonObj.accumulate(DESCRIPTION, GENERAL_ERROR_DESCRIPTION);
				jsonFinalObj.accumulate("fault", jsonObj);
				response = false;
			} else {
				LOG.info("Valid Make" + make);
			}

		} catch (JSONException e) {
			LOG.error("JSONException in isMakeNull for the make" + make, e);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To check if CDIID is null
	 * @param cdiid
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean isCDIIDNull(String cdiid, JSONObject jsonObj,
			JSONObject jsonFinalObj) {

		boolean response = true;

		try {
			if (!Utility.isStringNotNullorNotEmpty(cdiid)) {
				LOG.info("cdiid is null ");
				jsonObj.accumulate(CODE, GENERAL_ERROR_FAULT_CODE);
				jsonObj.accumulate(MESSAGE, GENERAL_ERROR_MESSAGE);
				jsonObj.accumulate(DESCRIPTION, GENERAL_ERROR_DESCRIPTION);
				jsonFinalObj.accumulate("fault", jsonObj);
				response = false;
			} else {
				LOG.info("CDIID is valid" + cdiid);
			}

		} catch (JSONException e) {
			LOG.error("JSONException in isCDIIDNull for the cdiid" + cdiid, e);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To accumulate json response. If optional values are provided then
	 *      set them in response.else get the values from helios db and set in
	 *      response.
	 * @param getVehicleWrapper
	 * @param userVehicleInfo
	 * @param vehicleInfo
	 * @param jsonObj
	 * @return
	 */
	private JSONObject setGetVehicleResponse(
			GetVehicleWrapper getVehicleWrapper,
			OwnerPortalUserVehicle userVehicleInfo,
			OwnerPortalVehicle vehicleInfo, JSONObject jsonObj,
			String imageLargeUrl, String imageSmallUrl) {
		JSONObject response = null;
		try {

			jsonObj.accumulate("vin", getVehicleWrapper.getGetVehicle()
					.getVehicle().getVin());
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelName())) {
				if (getVehicleWrapper.getGetVehicle().getVehicle()
						.getModelName().contains("LEAF")
						|| getVehicleWrapper.getGetVehicle().getVehicle()
						.getModelName().contains("LEF")) {
					jsonObj.accumulate("electric", true);
				} else {
					jsonObj.accumulate("electric", false);
				}
			} else {
				if (vehicleInfo.getVehicleModelName().contains("LEAF")
						|| vehicleInfo.getVehicleModelName().contains("LEF")) {
					jsonObj.accumulate("electric", true);
				} else {
					jsonObj.accumulate("electric", false);
				}
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelName())) {
				jsonObj.accumulate("vehicleName", getVehicleWrapper
						.getGetVehicle().getVehicle().getModelName());
			} else {
				jsonObj.accumulate("vehicleName",
						vehicleInfo.getVehicleModelName());
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelName())) {
				jsonObj.accumulate("detailedVehicleName", getVehicleWrapper
						.getGetVehicle().getVehicle().getModelName());
			} else {
				jsonObj.accumulate("detailedVehicleName",
						vehicleInfo.getVehicleModelName());
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelCode())) {
				jsonObj.accumulate("modelIdentifier", getVehicleWrapper
						.getGetVehicle().getVehicle().getModelCode());
			} else {
				jsonObj.accumulate("modelIdentifier",
						vehicleInfo.getVehicleModelCode());
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getNickname())) {
				jsonObj.accumulate("nickname", getVehicleWrapper
						.getGetVehicle().getVehicle().getNickname());
			} else {
				jsonObj.accumulate("nickname",
						userVehicleInfo.getVehicleNickName());
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelCode())) {
				jsonObj.accumulate("colour", getVehicleWrapper.getGetVehicle()
						.getVehicle().getExteriorColorName());
			} else {
				jsonObj.accumulate("colour",
						vehicleInfo.getVehicleExteriorColorName());
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelCode())) {
				jsonObj.accumulate("mileage", getVehicleWrapper.getGetVehicle()
						.getVehicle().getMileage());
			} else {
				jsonObj.accumulate("mileage", userVehicleInfo.getMileage());
			}
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelCode())) {
				jsonObj.accumulate("averageMileage", getVehicleWrapper
						.getGetVehicle().getVehicle().getAverageMileage());
			} else {
				jsonObj.accumulate("averageMileage",
						userVehicleInfo.getAverageMileage());
			}

			// Added by 455144 for model year change
			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getModelYear())) {
				jsonObj.accumulate("modelYear", getVehicleWrapper
						.getGetVehicle().getVehicle().getModelYear());
			} else {
				jsonObj.accumulate("modelYear",
						vehicleInfo.getModelYearNumber());
			}

			if (Utility.isStringNotNullorNotEmpty(getVehicleWrapper
					.getGetVehicle().getVehicle().getPreferredDealer())) {
				//				jsonObj.accumulate(DEALERID, "NNA"
				//						+ getVehicleWrapper.getGetVehicle().getVehicle()
				//								.getDealerId());
				jsonObj.accumulate(PREFERRED_DEALER, "NNA"
						+ getVehicleWrapper.getGetVehicle().getVehicle()
						.getPreferredDealer());
			} else if (Utility.isStringNotNullorNotEmpty(userVehicleInfo
					.getDealerId())) {
				//				jsonObj.accumulate(DEALERID,
				//						"NNA" + userVehicleInfo.getDealerId());
				jsonObj.accumulate(PREFERRED_DEALER,
						"NNA" + userVehicleInfo.getDealerId());
			}

			// x566325 - Update getVehicle API to include Image URL's in the
			// response
			jsonObj.accumulate("largeImage", imageLargeUrl);
			jsonObj.accumulate("smallImage", imageSmallUrl);

			response = jsonObj;
		} catch (JSONException e) {
			LOG.error("JSONException in setGetVehicleResponse for the vin"
					+ getVehicleWrapper.getGetVehicle().getVehicle().getVin()
					+ " and email"
					+ getVehicleWrapper.getGetVehicle().getPerson().getEmail(),
					e);
		} catch (Exception e) {
			LOG.error("Exception in setGetVehicleResponse for the vin"
					+ getVehicleWrapper.getGetVehicle().getVehicle().getVin()
					+ " and email"
					+ getVehicleWrapper.getGetVehicle().getPerson().getEmail(),
					e);
		}
		return response;
	}

	/**
	 * @author x796314 To accumulate the json response for VehicleSpecifications
	 * @param getVehicleWrapper
	 * @param vehicleSpecifications
	 * @param equipmentInfo
	 * @param jsonObj
	 * @return
	 */
	private JSONObject setGetVehicleSpecificationResponse(
			GetVehicleSpecificationWrapper getVehicleWrapper,
			List<VehicleSpecification> vehicleSpecifications,
			List<Equipment> equipmentInfo, JSONObject jsonObj) {

		JSONObject response = null;
		JSONArray jsonArrayObj = new JSONArray();

		JSONObject jsonEquipments = new JSONObject();

		JSONArray jsonArrayCategoriesObj = new JSONArray();

		try {

			jsonObj.accumulate("vin", getVehicleWrapper.getVehicleSpec()
					.getVehicle().getVin());
			jsonObj.accumulate("detailedName", vehicleSpecifications.get(0)
					.getVersion());
			if (Utility.isObjectNotNullorNotEmpty(vehicleSpecifications)) {

				Iterator<VehicleSpecification> iterator = vehicleSpecifications
						.iterator();

				while (iterator.hasNext()) {

					JSONObject jsonOwnedObj = new JSONObject();

					VehicleSpecification vehicleSpecs = iterator.next();

					if (Utility.isObjectNotNullorNotEmpty(vehicleSpecs)) {

						jsonOwnedObj.put("name", vehicleSpecs.getCategory());
						jsonOwnedObj.put("value",
								vehicleSpecs.getEquipmentName());

					}
					jsonArrayObj.put(jsonOwnedObj);

				}
			}

			if (jsonArrayObj.length() > 0) {
				jsonObj.accumulate("basicInformation", jsonArrayObj);
			}

			List<String> nameList = new ArrayList<String>();
			Map<String, List<String>> finalResponseMap = new HashMap<String, List<String>>();

			if (Utility.isObjectNotNullorNotEmpty(equipmentInfo)) {

				for (Iterator<Equipment> equipIterator = equipmentInfo
						.iterator(); equipIterator.hasNext();) {

					Equipment equipments = (Equipment) equipIterator.next();

					if (equipments.getCategory() != null
							&& !("").equals(equipments.getCategory().trim())) {

						if (!(nameList.isEmpty())
								&& nameList.contains(equipments.getCategory()
										.trim())) {
							if (finalResponseMap != null
									&& !(finalResponseMap.isEmpty())) {
								List<String> list = finalResponseMap
										.get(equipments.getCategory().trim());
								list.add(equipments.getEquipmentName());
								finalResponseMap.remove(equipments
										.getCategory().trim());
								finalResponseMap.put(equipments.getCategory()
										.trim(), list);
							}
						} else {
							nameList.add(equipments.getCategory().trim());
							List<String> list = new ArrayList<String>();
							list.add(equipments.getEquipmentName());
							finalResponseMap.put(equipments.getCategory()
									.trim(), list);
						}

					}
				}
			}
			LOG.info(finalResponseMap.size());

			for (String key : finalResponseMap.keySet()) {

				JSONObject jsoncatObj = new JSONObject();

				JSONArray itemsArray = new JSONArray();

				List<String> value = finalResponseMap.get(key);

				jsoncatObj.accumulate("name", key);

				for (int i = 0; i < value.size(); i++) {

					JSONObject itemsObj = new JSONObject();

					itemsObj.put("name", value.get(i));

					itemsObj.put("optional", false);

					itemsArray.put(itemsObj);

				}

				jsoncatObj.accumulate("items", itemsArray);

				jsonArrayCategoriesObj.put(jsoncatObj);

			}

			if (jsonArrayCategoriesObj.length() > 0) {
				jsonEquipments.accumulate("categories", jsonArrayCategoriesObj);
			}

			jsonObj.accumulate("equipment", jsonEquipments);

			response = jsonObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setGetVehicleSpecificationResponse for the vin"
							+ getVehicleWrapper.getVehicleSpec().getVehicle()
							.getVin(), e);
		} catch (Exception e) {
			LOG.error(
					"Exception in setGetVehicleSpecificationResponse for the vin"
							+ getVehicleWrapper.getVehicleSpec().getVehicle()
							.getVin(), e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To accumulate json response for add vehicle validatedata endpoint
	 * @param jsonObj
	 * @param isValidUser
	 * @param state
	 * @param phoneDetails
	 * @param validateDataWrapper
	 * @param brand
	 * @return
	 */
	private JSONObject setAddVehicleValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,

			ValidateDataWrapper validateDataWrapper, String brand) {
		JSONObject response = null;
		try {

			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonAdObj = new JSONObject();
			JSONObject jsonOptObj = new JSONObject();
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			jsonObj.accumulate("cdiid",
					validUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("personHashId", validUser.getPersonalHashId());
			jsonObj.accumulate("email", validUser.getEmailId());
			jsonObj.accumulate("firstName", validUser.getFirstName());
			jsonObj.accumulate("lastName", validUser.getLastName());
			jsonAdObj.accumulate("addressLine1", validUser.getAddressText());
			jsonAdObj.accumulate("city", validUser.getCityName());
			jsonAdObj.accumulate("region", state.getStateCode());
			jsonAdObj.accumulate("country", validUser.getCountryCode());
			jsonAdObj.accumulate("postalCode", validUser.getPostalCode());
			jsonObj.accumulate("address", jsonAdObj);
			/*
			 * for (int i = 0; i < ownerPortalUserPhone.size(); i++) { if
			 * (("work").equalsIgnoreCase(ownerPortalUserPhone.get(i)
			 * .getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("landLineNumber", Utility
			 * .isStringNull(ownerPortalUserPhone.get(i) .getPhoneNumber())); }
			 * else if (("mobile").equalsIgnoreCase(ownerPortalUserPhone
			 * .get(i).getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("mobileNumber", Utility
			 * .isStringNull(ownerPortalUserPhone.get(i) .getPhoneNumber())); }
			 * 
			 * }
			 */
			/* Workaround for Owner portal database remediation */
			if (validUser.getCellPhoneNumber() != null
					&& validUser.getCellPhoneNumber().intValue() > 0)
				jsonObj.accumulate("mobileNumber",
						validUser.getCellPhoneNumber());
			if (validUser.getWorkPhoneNumber() != null
					&& validUser.getWorkPhoneNumber().intValue() > 0)
				jsonObj.accumulate("landLineNumber",
						validUser.getWorkPhoneNumber());

			jsonOptObj.accumulate("allowText", "mobile");
			jsonOptObj.accumulate("allowText", "landLine");
			jsonOptObj.accumulate("allowAutoDial", "mobile");
			jsonOptObj.accumulate("allowAutoDial", "landLine");
			jsonObj.accumulate("optIn", jsonOptObj);
			jsonObj.accumulate("language", validUser.getLanguageCode());
			jsonObj.accumulate("profileChange", FALSE);
			jsonObj.accumulate("source", CUSTOMER);
			jsonVhObj.accumulate("vin", validateDataWrapper.getValidateData()
					.getVin());
			jsonVhObj.accumulate("make", brand);

			jsonVhObj.accumulate("nickname", validateDataWrapper
					.getValidateData().getNickname());
			jsonPrsnObj.accumulate("person", jsonObj);
			jsonPrsnObj.accumulate("vehicle", jsonVhObj);

			jsonlastObj.accumulate("validateData", jsonPrsnObj);

			response = jsonlastObj;
		} catch (JSONException e) {
			LOG.error(
					"JSONException in setAddVehicleValidateResponse for the vin"
							+ validateDataWrapper.getValidateData().getVin(), e);
		} catch (Exception e) {
			LOG.error("Exception in setAddVehicleValidateResponse for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To accumulate json response for update vehicle validatedata endpoint
	 * @param jsonObj
	 * @param isValidUser
	 * @param state
	 * @param phoneDetails
	 * @param validateDataWrapper
	 * @param brand
	 * @param userVehicleInfo
	 * @param vehicleInfo
	 * @return
	 */
	private JSONObject setUpdateVehicleValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,

			ValidateDataWrapper validateDataWrapper, String brand,
			OwnerPortalUserVehicle userVehicleInfo,
			OwnerPortalVehicle vehicleInfo,
			// Added to accommodate the Fuse requirement for Telematics Code
			Set<String> telematicsInfo) {
		JSONObject response = null;
		try {

			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonAdObj = new JSONObject();
			JSONObject jsonOptObj = new JSONObject();
			//JSONObject jsonOptCodeObj = new JSONObject();
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONObject jsonTelematicsObj  = new JSONObject();
			jsonObj.accumulate("cdiid",
					validUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("personHashId", validUser.getPersonalHashId());
			jsonObj.accumulate("email", validUser.getEmailId());
			jsonObj.accumulate("firstName", validUser.getFirstName());
			jsonObj.accumulate("lastName", validUser.getLastName());
			jsonAdObj.accumulate("addressLine1", validUser.getAddressText());
			jsonAdObj.accumulate("region", state.getStateCode());
			jsonAdObj.accumulate("country", validUser.getCountryCode());
			jsonAdObj.accumulate("postalCode", validUser.getPostalCode());
			jsonObj.accumulate("address", jsonAdObj);
			/*
			 * for (int i = 0; i < ownerPortalUserPhone.size(); i++) { if
			 * (("work").equalsIgnoreCase(ownerPortalUserPhone.get(i)
			 * .getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("landLineNumber", ownerPortalUserPhone
			 * .get(i).getPhoneNumber()); } else if
			 * (("mobile").equalsIgnoreCase(ownerPortalUserPhone
			 * .get(i).getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("mobileNumber", ownerPortalUserPhone
			 * .get(i).getPhoneNumber()); }
			 * 
			 * }
			 */
			/* Workaround for Owner portal database remediation */
			if (validUser.getCellPhoneNumber() != null
					&& validUser.getCellPhoneNumber().intValue() > 0)
				jsonObj.accumulate("mobileNumber",
						validUser.getCellPhoneNumber());
			if (validUser.getWorkPhoneNumber() != null
					&& validUser.getWorkPhoneNumber().intValue() > 0)
				jsonObj.accumulate("landLineNumber",
						validUser.getWorkPhoneNumber());

			jsonOptObj.accumulate("allowText", "mobile");
			jsonOptObj.accumulate("allowText", "landLine");
			jsonOptObj.accumulate("allowAutoDial", "mobile");
			jsonOptObj.accumulate("allowAutoDial", "landLine");
			jsonObj.accumulate("optIn", jsonOptObj);
			jsonObj.accumulate("language", validUser.getLanguageCode());
			jsonObj.accumulate("profileChange", FALSE);
			jsonObj.accumulate("source", CUSTOMER);
			jsonVhObj.accumulate("vin", validateDataWrapper.getValidateData()
					.getVin());
			jsonVhObj.accumulate("make", brand);
			if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper
					.getValidateData().getNickname())) {
				jsonVhObj.accumulate("nickname", validateDataWrapper
						.getValidateData().getNickname());
			} else {
				jsonVhObj.accumulate("nickname",
						userVehicleInfo.getVehicleNickName());
			}
			if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper
					.getValidateData().getMileage())) {
				jsonVhObj.accumulate("mileage", validateDataWrapper
						.getValidateData().getMileage());
			} else {
				jsonVhObj.accumulate("mileage", userVehicleInfo.getMileage());
			}

			if (Utility.isObjectNotNullorNotEmpty(validateDataWrapper
					.getValidateData().getAverageMileage())) {
				jsonVhObj.accumulate("averageMileage", validateDataWrapper
						.getValidateData().getAverageMileage());
			} else {
				jsonVhObj.accumulate("averageMileage",
						userVehicleInfo.getAverageMileage());
			}

			// Added by 455144 for modelYear change

			jsonVhObj
			.accumulate("modelCode", vehicleInfo.getVehicleModelCode());
			jsonVhObj
			.accumulate("modelName", vehicleInfo.getVehicleModelName());
			// Commenting as mdl_ln_cd is not available in OP DB
			/*
			 * jsonVhObj.accumulate("modelLineCode",
			 * vehicleInfo.getModelLineCode());
			 */
			jsonVhObj.accumulate("interiorColorCode",
					vehicleInfo.getVehicleInteriorColorCode());
			jsonVhObj.accumulate("interiorColorName",
					vehicleInfo.getVehicleInteriorColorName());
			jsonVhObj.accumulate("exteriorColorCode",
					vehicleInfo.getVehicleExteriorColorCode());
			jsonVhObj.accumulate("exteriorColorName",
					vehicleInfo.getVehicleExteriorColorName());
			jsonVhObj.accumulate("optionCode",
					vehicleInfo.getVehicleOptionCode());
			jsonVhObj.accumulate("modelYear", vehicleInfo.getModelYearNumber());
			jsonVhObj.accumulate("isTestVin", TRUE);
			/*if(istestvinavailable.equalsIgnoreCase("Success")){
				LOG.info("Inside if istestvinavailable"+istestvinavailable);
				jsonVhObj.accumulate("isTestVin", TRUE);
			}else{
				LOG.info("Inside else istestvinavailable"+istestvinavailable);
				jsonVhObj.accumulate("isTestVin", FALSE);
			}*/
			// Added to accommodate the Fuse requirement for Telematics Code -
			// Owners CI Project
          
            LOG.info("TelematicsInfo from OP DB = " + telematicsInfo);//x430955
			
			if (telematicsInfo.contains(N6) || telematicsInfo.contains(N7)
					|| telematicsInfo.contains(I6) || telematicsInfo.contains(I7) || telematicsInfo.contains(I8) || telematicsInfo.contains(I9) 
					|| telematicsInfo.contains(M6) || telematicsInfo.contains(M7) || telematicsInfo.contains(M8) || telematicsInfo.contains(M9)
					|| telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5) || telematicsInfo.contains(E1)) { //x100994 - OS-1742
				LOG.info("OptionTypeCode in Validate data response for Connected Service - N6/N7, I6/I7 ,I8/I9, M3/M4/M5,  M6/M7/M8/M9");
				jsonTelematicsObj.accumulate("nissanConnectEV", false);
				jsonTelematicsObj
				.accumulate("nissanConnectMobileApps", false);
				jsonTelematicsObj.accumulate("infinitiInTouchApps", false);
				jsonTelematicsObj.accumulate("nissanConnect", false);
				jsonTelematicsObj.accumulate("infinitiConnection", false);
				//jsonTelematicsObj.accumulate("nissanConnectServices", false);
				
				
				
				
				
				if (telematicsInfo.contains(N6) || telematicsInfo.contains(N7)) { 
					LOG.info("OptionTypeCode in Validate data response for Connected Service - N6/N7");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", false);
					jsonTelematicsObj.accumulate("nissanConnectServices", false);
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", true);
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", false);
				} else if(telematicsInfo.contains(M6) || telematicsInfo.contains(M7) || telematicsInfo.contains(M8) || telematicsInfo.contains(M9)|| telematicsInfo.contains(E1)){//x100994 - OS-1742
					//M6, M7, M8 and M9 changes
					LOG.info("OptionTypeCode in Validate data response for Connected Service - M6/M7/M8/M9");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", false);
					jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot", true);
					jsonTelematicsObj.accumulate("nissanConnectServices", true);
					jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot", false);
				}
				else if(telematicsInfo.contains(I6) || telematicsInfo.contains(I7) || telematicsInfo.contains(I8) || telematicsInfo.contains(I9)) {
					LOG.info("OptionTypeCode in Validate data response for Connected Service - I6/I7/I8/I9");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", false);
					jsonTelematicsObj.accumulate("nissanConnectServices", false);
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", false);
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", true);
				}else if(telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5)){
					LOG.info("OptionTypeCode in Validate data response for Connected Service - M3/M4/M5");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", true);
					jsonTelematicsObj.accumulate("nissanConnectServices", false);
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", false);
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", true);
				}

			} else {

				if (telematicsInfo.contains(CW) || telematicsInfo.contains(EV)
						|| telematicsInfo.contains(L2) || telematicsInfo.contains(X4)) {
					jsonTelematicsObj.accumulate("nissanConnectEV", true);
				} else {
					jsonTelematicsObj.accumulate("nissanConnectEV", false);
				}// x566325 - Modified the condition for VCS SSO Change
				if (telematicsInfo.contains(S2)
						&& vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
								NISSAN)) {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							true);
					/*jsonApiObj.accumulate("nissanConnectMobileApps",
							NISSANCONNECT_MOBILEAPPS_APIKEY);*/
				} else {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							false);
				}
				if (telematicsInfo.contains(S2)
						&& vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
								INFINITI)) {
					jsonTelematicsObj.accumulate("infinitiInTouchApps", true);
					/*jsonApiObj.accumulate("infinitiInTouchApps",
							INFINITI_INTOUCHAPPS_APIKEY);*/
				} else {
					jsonTelematicsObj
					.accumulate("infinitiInTouchApps", false);
				}
				if (telematicsInfo.contains(IC) || telematicsInfo.contains(X1)) {
					jsonTelematicsObj.accumulate("infinitiConnection", true);

				} else {
					jsonTelematicsObj.accumulate("infinitiConnection", false);
				}
				if (telematicsInfo.contains(NC)) {
					jsonTelematicsObj.accumulate("nissanConnect", true);
				} else {
					jsonTelematicsObj.accumulate("nissanConnect", false);
				}
				// x566325 - New VCS SXM Enrollment Process - For NS
				if (telematicsInfo.contains(NS) || telematicsInfo.contains(L2)
						|| telematicsInfo.contains(N2)
						|| telematicsInfo.contains(N3)
						|| telematicsInfo.contains(N4)
						|| telematicsInfo.contains(X2)
						|| telematicsInfo.contains(X3)) {
					jsonTelematicsObj.accumulate("nissanConnectServices",
							true);
					/*
					 * jsonApiObj.accumulate("nissanConnectServices",
					 * NISSANCONNECT_SERVICES_APIKEY);
					 */
				} else {
					jsonTelematicsObj.accumulate("nissanConnectServices",
							false);
				}
				// x566325 - New VCS SXM Enrollment Process - For I2,I3,I4
				if (telematicsInfo.contains(I2) 
						|| telematicsInfo.contains(I3)
						|| telematicsInfo.contains(I4)
						|| telematicsInfo.contains(X5)
						|| telematicsInfo.contains(X6)
						|| telematicsInfo.contains(X7)) {
					jsonTelematicsObj.accumulate("infinitiInTouchServices",
							true);
				} else {
					jsonTelematicsObj.accumulate("infinitiInTouchServices",
							false);
				}

				// setting default false as no AT&T service is available
				jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot",
						false);
				jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot",
						false);
			}			

			// Iterator<String> iterator = telematicsInfo.iterator();

			// while (iterator.hasNext()) {
			// jsonOptCodeObj.accumulate("telematicsCode", iterator.next());
			// }
			//jsonVhObj.put("telematicsDetails", telematicsInfo);
			
			

			jsonPrsnObj.accumulate("person", jsonObj);
			jsonPrsnObj.accumulate("vehicle", jsonVhObj);
			jsonPrsnObj.accumulate("telematics", jsonTelematicsObj);

			jsonlastObj.accumulate("validateData", jsonPrsnObj);

			response = jsonlastObj;
		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateVehicleResponse for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		} catch (Exception e) {
			LOG.error("Exception in setUpdateVehicleResponsefor the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To set the get vehicle validata data response
	 * @param jsonObj
	 * @param validUser
	 * @param ownerPortalUserPhone
	 * @param state
	 * @param validateDataWrapper
	 * @param brand
	 * @param userVehicleInfo
	 * @param vehicleInfo
	 * @return
	 */
	private JSONObject setGetVehicleValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,

			ValidateDataWrapper validateDataWrapper, String brand,
			OwnerPortalUserVehicle userVehicleInfo,
			OwnerPortalVehicle vehicleInfo) {
		JSONObject response = null;
		try {

			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonAdObj = new JSONObject();
			JSONObject jsonOptObj = new JSONObject();
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			jsonObj.accumulate("cdiid",
					validUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("personHashId", validUser.getPersonalHashId());
			jsonObj.accumulate("email", validUser.getEmailId());
			jsonObj.accumulate("firstName", validUser.getFirstName());
			jsonObj.accumulate("lastName", validUser.getLastName());
			jsonAdObj.accumulate("addressLine1", validUser.getAddressText());
			jsonAdObj.accumulate("region", state.getStateCode());
			jsonAdObj.accumulate("country", validUser.getCountryCode());
			jsonAdObj.accumulate("postalCode", validUser.getPostalCode());
			jsonObj.accumulate("address", jsonAdObj);
			// for (int i = 0; i < ownerPortalUserPhone.size(); i++) {
			// if (("work").equalsIgnoreCase(ownerPortalUserPhone.get(i)
			if (validUser.getCellPhoneNumber() != null
					&& validUser.getCellPhoneNumber().intValue() > 0)
				jsonObj.accumulate("mobileNumber",
						validUser.getCellPhoneNumber());
			if (validUser.getWorkPhoneNumber() != null
					&& validUser.getWorkPhoneNumber().intValue() > 0)
				jsonObj.accumulate("landLineNumber",
						validUser.getWorkPhoneNumber());
			/*
			 * if (("mobile").equalsIgnoreCase(ownerPortalUserPhone
			 * .get(i).getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("mobileNumber", ownerPortalUserPhone
			 * .get(i).getPhoneNumber()); }
			 */
			// }

			jsonOptObj.accumulate("allowText", "mobile");
			jsonOptObj.accumulate("allowText", "landLine");
			jsonOptObj.accumulate("allowAutoDial", "mobile");
			jsonOptObj.accumulate("allowAutoDial", "landLine");
			jsonObj.accumulate("optIn", jsonOptObj);
			jsonObj.accumulate("language", validUser.getLanguageCode());
			jsonObj.accumulate("profileChange", FALSE);
			jsonObj.accumulate("source", CUSTOMER);
			jsonVhObj.accumulate("vin", validateDataWrapper.getValidateData()
					.getVin());
			jsonVhObj.accumulate("make", brand);
			jsonVhObj.accumulate("nickname",
					userVehicleInfo.getVehicleNickName());
			jsonVhObj.accumulate("mileage", userVehicleInfo.getMileage());
			jsonVhObj.accumulate("averageMileage",
					userVehicleInfo.getAverageMileage());
			jsonVhObj
			.accumulate("modelCode", vehicleInfo.getVehicleModelCode());
			jsonVhObj
			.accumulate("modelName", vehicleInfo.getVehicleModelName());
			// Commenting as mdl_ln_cd is not available in OP DB
			jsonVhObj.accumulate("modelLineCode", " ");
			jsonVhObj.accumulate("interiorColorCode",
					vehicleInfo.getVehicleInteriorColorCode());
			jsonVhObj.accumulate("interiorColorName",
					vehicleInfo.getVehicleInteriorColorName());
			jsonVhObj.accumulate("exteriorColorCode",
					vehicleInfo.getVehicleExteriorColorCode());
			jsonVhObj.accumulate("exteriorColorName",
					vehicleInfo.getVehicleExteriorColorName());
			jsonVhObj.accumulate("optionCode",
					vehicleInfo.getVehicleOptionCode());
			jsonVhObj.accumulate("modelYear", vehicleInfo.getModelYearNumber());
			jsonVhObj.accumulate("isTestVin", TRUE);
			jsonPrsnObj.accumulate("person", jsonObj);
			jsonPrsnObj.accumulate("vehicle", jsonVhObj);

			jsonlastObj.accumulate("validateData", jsonPrsnObj);

			response = jsonlastObj;
		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateVehicleResponse for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		} catch (Exception e) {
			LOG.error("Exception in setUpdateVehicleResponsefor the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To accumulate json response for delete vehicle validatedata endpoint
	 * @param jsonObj
	 * @param isValidUser
	 * @param state
	 * @param phoneDetails
	 * @param validateDataWrapper
	 * @param brand
	 * @param userVehicleInfo
	 * @param vehicleInfo
	 * @param driverVehicleInfo
	 * @param vehicleCarwings
	 * @param telematicsInfo
	 * @return
	 */
	private JSONObject setDeleteVehicleValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,

			ValidateDataWrapper validateDataWrapper, String brand,
			OwnerPortalUserVehicle userVehicleInfo,
			OwnerPortalVehicle vehicleInfo, OwnerPortalUser driverVehicleInfo,
			VehicleCarwings vehicleCarwings, Set<String> telematicsInfo) {
		JSONObject response = null;
		try {

			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonAdObj = new JSONObject();
			JSONObject jsonOptObj = new JSONObject();
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONObject jsonDriverObj = new JSONObject();
			JSONObject jsonGDCObj = new JSONObject();
			JSONObject jsonTelematicsObj = new JSONObject();
			JSONObject jsonabqObj = new JSONObject();

			jsonObj.accumulate("cdiid",
					validUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("personHashId", validUser.getPersonalHashId());
			jsonObj.accumulate("email", validUser.getEmailId());
			jsonObj.accumulate("firstName", validUser.getFirstName());
			jsonObj.accumulate("lastName", validUser.getLastName());
			jsonAdObj.accumulate("addressLine1", validUser.getAddressText());
			jsonAdObj.accumulate("region", state.getStateCode());
			jsonAdObj.accumulate("country", validUser.getCountryCode());
			jsonAdObj.accumulate("postalCode", validUser.getPostalCode());
			jsonObj.accumulate("address", jsonAdObj);
			/*
			 * for (int i = 0; i < ownerPortalUserPhone.size(); i++) { if
			 * (("work").equalsIgnoreCase(ownerPortalUserPhone.get(i)
			 * .getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("landLineNumber", ownerPortalUserPhone
			 * .get(i).getPhoneNumber()); } else if
			 * (("mobile").equalsIgnoreCase(ownerPortalUserPhone
			 * .get(i).getOwnerPortalUserPhonePK().getPhoneTypeId())) {
			 * jsonObj.accumulate("mobileNumber", ownerPortalUserPhone
			 * .get(i).getPhoneNumber()); }
			 * 
			 * }
			 */
			/* Workaround for Owner portal database remediation */
			if (validUser.getCellPhoneNumber() != null
					&& validUser.getCellPhoneNumber().intValue() > 0)
				jsonObj.accumulate("mobileNumber",
						validUser.getCellPhoneNumber());
			if (validUser.getWorkPhoneNumber() != null
					&& validUser.getWorkPhoneNumber().intValue() > 0)
				jsonObj.accumulate("landLineNumber",
						validUser.getWorkPhoneNumber());

			jsonOptObj.accumulate("allowText", "mobile");
			jsonOptObj.accumulate("allowText", "landLine");
			jsonOptObj.accumulate("allowAutoDial", "mobile");
			jsonOptObj.accumulate("allowAutoDial", "landLine");
			jsonObj.accumulate("optIn", jsonOptObj);
			jsonObj.accumulate("language", validUser.getLanguageCode());
			jsonObj.accumulate("profileChange", FALSE);
			jsonObj.accumulate("source", CUSTOMER);
			jsonVhObj.accumulate("vin", validateDataWrapper.getValidateData()
					.getVin());
			jsonVhObj.accumulate("make", brand);
			jsonVhObj.accumulate("nickname",
					userVehicleInfo.getVehicleNickName());
			jsonVhObj.accumulate("mileage", userVehicleInfo.getMileage());
			jsonVhObj.accumulate("averageMileage",
					userVehicleInfo.getAverageMileage());
			jsonVhObj
			.accumulate("modelCode", vehicleInfo.getVehicleModelCode());
			jsonVhObj
			.accumulate("modelName", vehicleInfo.getVehicleModelName());
			// jsonVhObj.accumulate("modelLineCode",
			// vehicleInfo.getModelLineCode());
			jsonVhObj.accumulate("interiorColorCode",
					vehicleInfo.getVehicleInteriorColorCode());
			jsonVhObj.accumulate("interiorColorName",
					vehicleInfo.getVehicleInteriorColorName());
			jsonVhObj.accumulate("exteriorColorCode",
					vehicleInfo.getVehicleExteriorColorCode());
			jsonVhObj.accumulate("exteriorColorName",
					vehicleInfo.getVehicleExteriorColorName());
			jsonVhObj.accumulate("optionCode",
					vehicleInfo.getVehicleOptionCode());
			jsonVhObj.accumulate("modelYear", vehicleInfo.getModelYearNumber());
			jsonVhObj.accumulate("isTestVin", TRUE);
			/*if(istestvinavailable.equalsIgnoreCase("Success")){
				LOG.info("Inside if istestvinavailable"+istestvinavailable);
				jsonVhObj.accumulate("isTestVin", TRUE);
			}else{
				LOG.info("Inside else istestvinavailable"+istestvinavailable);
				jsonVhObj.accumulate("isTestVin", FALSE);
			}*/
			

			if (driverVehicleInfo != null) {
				jsonDriverObj.accumulate("personHashId",

						driverVehicleInfo.getPersonalHashId());
				jsonDriverObj.accumulate("cdiid",
						driverVehicleInfo.getCustomerDataIntegrationId());
				jsonDriverObj.accumulate("firstName",
						driverVehicleInfo.getFirstName());
				jsonDriverObj.accumulate("lastName",
						driverVehicleInfo.getLastName());
				jsonDriverObj.accumulate("email",
						driverVehicleInfo.getEmailId());
			}
			if (vehicleCarwings != null) {

				jsonGDCObj.accumulate("pin",
						vehicleCarwings.getOwnerCarwingsUserId());

				jsonGDCObj.accumulate("authToken",
						vehicleCarwings.getAuthToken());
			}
			/*if (telematicsInfo.contains(CW) || telematicsInfo.contains(EV)
					|| telematicsInfo.contains(L2)) {
				jsonTelematicsObj.accumulate("nissanConnectEV", true);
			} else {
				jsonTelematicsObj.accumulate("nissanConnectEV", false);
			}
			if (telematicsInfo.contains(S2)) {
				if (vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(NISSAN)) {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							true);

				} else if (vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
						INFINITI)) {
					jsonTelematicsObj.accumulate("infinitiIntouchApps", true);

				}

			} else {
				if (vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(NISSAN)) {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							false);

				} else if (vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
						INFINITI)) {
					jsonTelematicsObj.accumulate("infinitiIntouchApps", false);

				}

			}
			if (telematicsInfo.contains(IC)) {
				jsonTelematicsObj.accumulate("infinitiConnection", true);
			} else {
				jsonTelematicsObj.accumulate("infinitiConnection", false);
			}
			if (telematicsInfo.contains(NC)) {
				jsonTelematicsObj.accumulate("nissanConnect", true);
			} else {
				jsonTelematicsObj.accumulate("nissanConnect", false);
			}
			if (telematicsInfo.contains(NS) || telematicsInfo.contains(L2)
					|| telematicsInfo.contains(N2)
					|| telematicsInfo.contains(N3)
					|| telematicsInfo.contains(N4)
					|| telematicsInfo.contains(M6)
					|| telematicsInfo.contains(M7)
					|| telematicsInfo.contains(M8)
					|| telematicsInfo.contains(M9)) {
				jsonTelematicsObj.accumulate("nissanConnectServices", true);
			} else {
				jsonTelematicsObj.accumulate("nissanConnectServices", false);
			}

			if (telematicsInfo.contains(I2) || telematicsInfo.contains(I3)
					|| telematicsInfo.contains(I4) || telematicsInfo.contains(I8) || telematicsInfo.contains(I9)
					|| telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5)) {
				jsonTelematicsObj.accumulate("infinitiInTouchServices", true);
			} else {
				jsonTelematicsObj.accumulate("infinitiInTouchServices", false);
			}*/
			
              LOG.info("TelematicsInfo = " + telematicsInfo);//x430955
			
			if (telematicsInfo.contains(N6) || telematicsInfo.contains(N7)
					|| telematicsInfo.contains(I6) || telematicsInfo.contains(I7) || telematicsInfo.contains(I8) || telematicsInfo.contains(I9) 
					|| telematicsInfo.contains(M6) || telematicsInfo.contains(M7) || telematicsInfo.contains(M8) || telematicsInfo.contains(M9)
					|| telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5) || telematicsInfo.contains(E1)) { //x100994 - OS-1742
				LOG.info("OptionTypeCode in Validate data response for Connected Service - N6/N7, I6/I7 ,I8/I9, M3/M4/M5,  M6/M7/M8/M9");
				jsonTelematicsObj.accumulate("nissanConnectEV", false);
				jsonTelematicsObj
				.accumulate("nissanConnectMobileApps", false);
				jsonTelematicsObj.accumulate("infinitiInTouchApps", false);
				jsonTelematicsObj.accumulate("nissanConnect", false);
				jsonTelematicsObj.accumulate("infinitiConnection", false);
				//jsonTelematicsObj.accumulate("nissanConnectServices", false);
				
				
				
				
				
				if (telematicsInfo.contains(N6) || telematicsInfo.contains(N7)) { 
					LOG.info("OptionTypeCode in Validate data response for Connected Service - N6/N7");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", false);
					jsonTelematicsObj.accumulate("nissanConnectServices", false);
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", true);
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", false);
				} else if(telematicsInfo.contains(M6) || telematicsInfo.contains(M7) || telematicsInfo.contains(M8) || telematicsInfo.contains(M9)|| telematicsInfo.contains(E1)){//x100994 - OS-1742
					//M6, M7, M8 and M9 changes
					LOG.info("OptionTypeCode in Validate data response for Connected Service - M6/M7/M8/M9");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", false);
					jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot", true);
					jsonTelematicsObj.accumulate("nissanConnectServices", true);
					jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot", false);
				}
				else if(telematicsInfo.contains(I6) || telematicsInfo.contains(I7) || telematicsInfo.contains(I8) || telematicsInfo.contains(I9)) {
					LOG.info("OptionTypeCode in Validate data response for Connected Service - I6/I7/I8/I9");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", false);
					jsonTelematicsObj.accumulate("nissanConnectServices", false);
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", false);
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", true);
				}else if(telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5)){
					LOG.info("OptionTypeCode in Validate data response for Connected Service - M3/M4/M5");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", true);
					jsonTelematicsObj.accumulate("nissanConnectServices", false);
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", false);
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", true);
				}

			} else {

				if (telematicsInfo.contains(CW) || telematicsInfo.contains(EV)
						|| telematicsInfo.contains(L2) || telematicsInfo.contains(X4)) {
					jsonTelematicsObj.accumulate("nissanConnectEV", true);
				} else {
					jsonTelematicsObj.accumulate("nissanConnectEV", false);
				}// x566325 - Modified the condition for VCS SSO Change
				if (telematicsInfo.contains(S2)
						&& vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
								NISSAN)) {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							true);
					/*jsonApiObj.accumulate("nissanConnectMobileApps",
							NISSANCONNECT_MOBILEAPPS_APIKEY);*/
				} else {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							false);
				}
				if (telematicsInfo.contains(S2)
						&& vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
								INFINITI)) {
					jsonTelematicsObj.accumulate("infinitiInTouchApps", true);
					/*jsonApiObj.accumulate("infinitiInTouchApps",
							INFINITI_INTOUCHAPPS_APIKEY);*/
				} else {
					jsonTelematicsObj
					.accumulate("infinitiInTouchApps", false);
				}
				if (telematicsInfo.contains(IC) || telematicsInfo.contains(X1)) {
					jsonTelematicsObj.accumulate("infinitiConnection", true);

				} else {
					jsonTelematicsObj.accumulate("infinitiConnection", false);
				}
				if (telematicsInfo.contains(NC)) {
					jsonTelematicsObj.accumulate("nissanConnect", true);
				} else {
					jsonTelematicsObj.accumulate("nissanConnect", false);
				}
				// x566325 - New VCS SXM Enrollment Process - For NS
				if (telematicsInfo.contains(NS) || telematicsInfo.contains(L2)
						|| telematicsInfo.contains(N2)
						|| telematicsInfo.contains(N3)
						|| telematicsInfo.contains(N4)
						|| telematicsInfo.contains(X2)
						|| telematicsInfo.contains(X3)
						) {
					jsonTelematicsObj.accumulate("nissanConnectServices",
							true);
					/*
					 * jsonApiObj.accumulate("nissanConnectServices",
					 * NISSANCONNECT_SERVICES_APIKEY);
					 */
				} else {
					jsonTelematicsObj.accumulate("nissanConnectServices",
							false);
				}
				// x566325 - New VCS SXM Enrollment Process - For I2,I3,I4
				if (telematicsInfo.contains(I2) || telematicsInfo.contains(I3)
						|| telematicsInfo.contains(I4) || telematicsInfo.contains(X5)
						|| telematicsInfo.contains(X6) || telematicsInfo.contains(X7)) {
					jsonTelematicsObj.accumulate("infinitiInTouchServices",
							true);
				} else {
					jsonTelematicsObj.accumulate("infinitiInTouchServices",
							false);
				}

				// setting default false as no AT&T service is available
				jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot",
						false);
				jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot",
						false);
			}

			jsonabqObj.accumulate("airbiquityDeleteKey",
					AIRBIQUITY_DELETE_APIKEY);
			jsonPrsnObj.accumulate("person", jsonObj);
			jsonPrsnObj.accumulate("driver", jsonDriverObj);
			jsonPrsnObj.accumulate("vehicle", jsonVhObj);
			jsonPrsnObj.accumulate("telematics", jsonTelematicsObj);
			jsonPrsnObj.accumulate("gdc", jsonGDCObj);
			jsonPrsnObj.accumulate("api", jsonabqObj);
			jsonlastObj.accumulate("validateData", jsonPrsnObj);

			response = jsonlastObj;
		} catch (JSONException e) {
			LOG.error("JSONException in setGetVehicleResponse for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		} catch (Exception e) {
			LOG.error("Exception in setGetVehicleResponse for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use To accumulate json response for connected service validatedata
	 *      endpoint
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param vin
	 * @param vehicleInfo
	 * @param telematicsInfo
	 * @return
	 */
	private JSONObject setConnectedServiceValidateDataResponse(
			JSONObject jsonObj, OwnerPortalUser ownerPortalUser, String vin,
			OwnerPortalVehicle vehicleInfo, Set<String> telematicsInfo,
			String brand) {
		JSONObject response = null;

		try {
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONObject jsonTelematicsObj = new JSONObject();
			JSONObject jsonApiObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("userProfileId",
					ownerPortalUser.getUserProfileId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonVhObj.accumulate("vin", vin);
			jsonVhObj.accumulate("make", brand);
			LOG.info("====telematicsInfo=======" + telematicsInfo);
			if (telematicsInfo.contains(N6) || telematicsInfo.contains(N7)
					|| telematicsInfo.contains(I6) || telematicsInfo.contains(I7) 	|| telematicsInfo.contains(I8) || telematicsInfo.contains(I9) 
					|| telematicsInfo.contains(M6) || telematicsInfo.contains(M7) || telematicsInfo.contains(M8) || telematicsInfo.contains(M9)
					|| telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5) || telematicsInfo.contains(E1)) { //x100994 - OS-1742
				LOG.info("OptionTypeCode in Validate data response for Connected Service - N6/N7, I6/I7,I8/I9,M3/M4/M5,  M6/M7/M8/M9");
				jsonTelematicsObj.accumulate("nissanConnectEV", "false");
				jsonTelematicsObj
				.accumulate("nissanConnectMobileApps", "false");
				jsonTelematicsObj.accumulate("infinitiInTouchApps", "false");
				jsonTelematicsObj.accumulate("nissanConnect", "false");
				jsonTelematicsObj.accumulate("infinitiConnection", "false");
				//jsonTelematicsObj.accumulate("nissanConnectServices", "false");
				
				if (telematicsInfo.contains(N6) || telematicsInfo.contains(N7)) { 
					LOG.info("OptionTypeCode in Validate data response for Connected Service - N6/N7");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", "false");
					jsonTelematicsObj.accumulate("nissanConnectServices", "false");
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", "true");
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", "false");
				} else if(telematicsInfo.contains(M6) || telematicsInfo.contains(M7) || telematicsInfo.contains(M8) || telematicsInfo.contains(M9) || telematicsInfo.contains(E1)){//x100994 - OS-1742
					//M6, M7, M8 and M9 changes
					LOG.info("OptionTypeCode in Validate data response for Connected Service - M6/M7/M8/M9");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", "false");
					jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot", "true");
					jsonTelematicsObj.accumulate("nissanConnectServices", "true");
					jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot", "false");
				}
				else if(telematicsInfo.contains(I6) || telematicsInfo.contains(I7) || telematicsInfo.contains(I8) || telematicsInfo.contains(I9)) {
					LOG.info("OptionTypeCode in Validate data response for Connected Service - I6/I7/I8/I9");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", "false");
					jsonTelematicsObj.accumulate("nissanConnectServices", "false");
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", "false");
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", "true");
				}
				else if(telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5)){
					LOG.info("OptionTypeCode in Validate data response for Connected Service - M3/M4/M5");
					jsonTelematicsObj
					.accumulate("infinitiInTouchServices", "true");
					jsonTelematicsObj.accumulate("nissanConnectServices", "false");
					jsonTelematicsObj.accumulate(
							"nissanConnectwithWifiHotspot", "false");
					jsonTelematicsObj.accumulate(
							"infinitiIntouchwithWifiHotspot", "true");
					
				}

			} else {
				LOG.info("Inside else block validate data connected service");

				if (telematicsInfo.contains(CW) || telematicsInfo.contains(EV)
						|| telematicsInfo.contains(L2) || telematicsInfo.contains(X4)) {
					jsonTelematicsObj.accumulate("nissanConnectEV", "true");
				} else {
					jsonTelematicsObj.accumulate("nissanConnectEV", "false");
				}// x566325 - Modified the condition for VCS SSO Change
				if (telematicsInfo.contains(S2)
						&& vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
								NISSAN)) {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							"true");
					jsonApiObj.accumulate("nissanConnectMobileApps",
							NISSANCONNECT_MOBILEAPPS_APIKEY);
				} else {
					jsonTelematicsObj.accumulate("nissanConnectMobileApps",
							"false");
				}
				if (telematicsInfo.contains(S2)
						&& vehicleInfo.getVehicleMakeCode().equalsIgnoreCase(
								INFINITI)) {
					jsonTelematicsObj.accumulate("infinitiInTouchApps", "true");
					jsonApiObj.accumulate("infinitiInTouchApps",
							INFINITI_INTOUCHAPPS_APIKEY);
				} else {
					jsonTelematicsObj
					.accumulate("infinitiInTouchApps", "false");
				}
				if (telematicsInfo.contains(IC) || telematicsInfo.contains(X1) ) {
					jsonTelematicsObj.accumulate("infinitiConnection", "true");

				} else {
					jsonTelematicsObj.accumulate("infinitiConnection", "false");
				}
				if (telematicsInfo.contains(NC)) {
					jsonTelematicsObj.accumulate("nissanConnect", "true");
				} else {
					jsonTelematicsObj.accumulate("nissanConnect", "false");
				}
				// x566325 - New VCS SXM Enrollment Process - For NS
				if (telematicsInfo.contains(NS) || telematicsInfo.contains(L2)
						|| telematicsInfo.contains(N2)
						|| telematicsInfo.contains(N3)
						|| telematicsInfo.contains(N4) || telematicsInfo.contains(X2) || telematicsInfo.contains(X3)) {
					jsonTelematicsObj.accumulate("nissanConnectServices",
							"true");
					/*
					 * jsonApiObj.accumulate("nissanConnectServices",
					 * NISSANCONNECT_SERVICES_APIKEY);
					 */
				} else {
					jsonTelematicsObj.accumulate("nissanConnectServices",
							"false");
				}
				// x566325 - New VCS SXM Enrollment Process - For I2,I3,I4
				if (telematicsInfo.contains(I2) || telematicsInfo.contains(I3)
						|| telematicsInfo.contains(I4) || telematicsInfo.contains(X5) || telematicsInfo.contains(X6) || telematicsInfo.contains(X7)) {
					jsonTelematicsObj.accumulate("infinitiInTouchServices",
							"true");
				} else {
					jsonTelematicsObj.accumulate("infinitiInTouchServices",
							"false");
				}

				// setting default false as no AT&T service is available
				jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot",
						"false");
				jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot",
						"false");
			}

			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);
			jsonObj.accumulate("telematics", jsonTelematicsObj);
			jsonObj.accumulate("api", jsonApiObj);
			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setConnectedServiceResponse for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in setConnectedServiceResponse for the vin"
					+ vin, e);
		}

		return response;
	}

	/**
	 * New function to build the response json for Connected service validate
	 * data Balaji Onnappan
	 **/

	private boolean validateMessageIdForMessageUserAction(String id, Set<String> telematicsInfo){


		if (telematicsInfo.contains(CW) || telematicsInfo.contains(EV)
				|| telematicsInfo.contains(L2)) {
			// NISSANCONNECTEV_MSG_ID
			if(id.equalsIgnoreCase(NISSANCONNECTEV_MSG_ID) || id.equalsIgnoreCase(NISSANCONNECTSERVICES_MSG_ID)){

				return true;
			}else{
				return false;
			}

		} else if (telematicsInfo.contains(IC)) {
			//INFINFITICONNECTION_MSG_ID
			if(id.equalsIgnoreCase(INFINFITICONNECTION_MSG_ID)){
				return true;
			}else{
				return false;
			}
		} else if (telematicsInfo.contains(NC)) {
			//NISSANCONNECTNAVIGATION_MSG_ID
			if(id.equalsIgnoreCase(NISSANCONNECTNAVIGATION_MSG_ID)){
				return true;
			}else{
				return false;
			}
		} else if (telematicsInfo.contains(NS) || telematicsInfo.contains(L2)
				|| telematicsInfo.contains(N2)
				|| telematicsInfo.contains(N3)
				|| telematicsInfo.contains(N4)
				|| telematicsInfo.contains(M6) //x116202 - OS - 1742
				|| telematicsInfo.contains(M7)
				|| telematicsInfo.contains(M8)
				|| telematicsInfo.contains(M9)
				|| telematicsInfo.contains(E1)) {//
			//NISSANCONNECTSERVICES_MSG_ID
			if(id.equalsIgnoreCase(NISSANCONNECTSERVICES_MSG_ID)){
				return true;
			}else{
				return false;
			}
		} else if (telematicsInfo.contains(I2) || telematicsInfo.contains(I3)
				|| telematicsInfo.contains(I4) || telematicsInfo.contains(M3) || telematicsInfo.contains(M4) || telematicsInfo.contains(M5) ) {
			//INFINITIINTOUCHSERVICES_MSG_ID
			if(id.equalsIgnoreCase(INFINITIINTOUCHSERVICES_MSG_ID)){
				return true;
			}else{
				return false;
			}
		} else {

			return false;
		}
	}

	private JSONObject setConnectedServiceDetailsValidateDataResponse(
			JSONObject jsonObj, OwnerPortalUser ownerPortalUser, String vin,
			OwnerPortalVehicle vehicleInfo, String brand, String telematicsId) {
		JSONObject response = null;

		try {
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONObject jsonTelematicsObj = new JSONObject();
			JSONObject jsonApiObj = new JSONObject();
			JSONObject jsonIdObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("userProfileId",
					ownerPortalUser.getUserProfileId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonVhObj.accumulate("vin", vin);
			jsonVhObj.accumulate("make", brand);

			LOG.info("telematicsId>>>>>>" + new Date() + ">>>>>>>"
					+ telematicsId);

			if (telematicsId.equalsIgnoreCase(NISSANCONNECTEV)) {
				jsonTelematicsObj.accumulate("nissanConnectEV", "true");
			} else {
				jsonTelematicsObj.accumulate("nissanConnectEV", "false");
			}

			if (telematicsId.equalsIgnoreCase(NISSANCONNECTMOBILEAPPS)) {
				jsonTelematicsObj.accumulate("nissanConnectMobileApps", "true");
				jsonApiObj.accumulate("nissanConnectMobileApps",
						NISSANCONNECT_MOBILEAPPS_APIKEY);
			} else {
				jsonTelematicsObj
				.accumulate("nissanConnectMobileApps", "false");
			}

			if (telematicsId.equalsIgnoreCase(INFINFITIINTOUCHAPPS)) {
				jsonTelematicsObj.accumulate("infinitiInTouchApps", "true");
				jsonApiObj.accumulate("infinitiInTouchApps",
						INFINITI_INTOUCHAPPS_APIKEY);
			} else {
				jsonTelematicsObj.accumulate("infinitiInTouchApps", "false");
			}

			if (telematicsId.equalsIgnoreCase(INFINFITICONNECTION)) {
				jsonTelematicsObj.accumulate("infinitiConnection", "true");

			} else {
				jsonTelematicsObj.accumulate("infinitiConnection", "false");
			}

			if (telematicsId.equalsIgnoreCase(NISSANCONNECTNAVIGATION)) {
				jsonTelematicsObj.accumulate("nissanConnect", "true");
			} else {
				jsonTelematicsObj.accumulate("nissanConnect", "false");
			}

			if (telematicsId.equalsIgnoreCase(NISSANCONNECTSERVICES)) {
				jsonTelematicsObj.accumulate("nissanConnectServices", "true");
				jsonApiObj.accumulate("nissanConnectServices",
						NISSANCONNECT_SERVICES_APIKEY);

			} else {
				jsonTelematicsObj.accumulate("nissanConnectServices", "false");
			}

			if (telematicsId.equalsIgnoreCase(INFINITIINTOUCHSERVICES)) {
				jsonTelematicsObj.accumulate("infinitiInTouchServices", "true");
			} else {
				jsonTelematicsObj
				.accumulate("infinitiInTouchServices", "false");
			}

			// setting default false as no AT&T service is available
			jsonTelematicsObj.accumulate("nissanConnectwithWifiHotspot",
					"false");
			jsonTelematicsObj.accumulate("infinitiIntouchwithWifiHotspot",
					"false");

			jsonIdObj.accumulate("id", telematicsId);
			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);
			jsonObj.accumulate("telematics", jsonTelematicsObj);
			jsonObj.accumulate("api", jsonApiObj);
			jsonObj.accumulate("requestedService", jsonIdObj);
			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setConnectedServiceResponse for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in setConnectedServiceResponse for the vin"
					+ vin, e);
		}

		return response;
	}

	/**
	 * @author x497432 - Balaji O
	 * @use To accumulate the json response for Validate Message User Action
	 * @param jsonObj
	 * @param Owner
	 *            Portel User Object
	 * @param VIN
	 * @param brand
	 *            of the vehicle
	 * @param Message
	 *            ID
	 * @param Action
	 *            preformed by the user
	 * @return jsonObj
	 */
	private JSONObject setMessageDetailsValidateDataResponse(
			JSONObject jsonObj, OwnerPortalUser ownerPortalUser, String vin,
			String brand, String msgId, String action) {
		JSONObject response = null;

		try {
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONObject jsonIdObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("userProfileId",
					ownerPortalUser.getUserProfileId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonVhObj.accumulate("vin", vin);
			jsonVhObj.accumulate("make", brand);

			jsonIdObj.accumulate("messageId ", msgId);

			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);

			jsonObj.accumulate("messageDetails", jsonIdObj);
			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setConnectedServiceResponse for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in setConnectedServiceResponse for the vin"
					+ vin, e);
		}

		return response;
	}

	/**
	 * @author x497432 - Balaji O
	 * @use To accumulate the json response for Validate Message User Action
	 * @param jsonObj
	 * @param Owner
	 *            Portel User Object
	 * @param VIN
	 * @param brand
	 *            of the vehicle
	 * @param Message
	 *            ID
	 * @param Action
	 *            preformed by the user
	 * @return jsonObj
	 */
	private JSONObject setMessageUserActionValidateDataResponse(
			JSONObject jsonObj, OwnerPortalUser ownerPortalUser, String vin,
			String brand, String id, String action,State state,MobileProvider mobileProvider) {
		JSONObject response = null;
		String password= userLocal.decryptPassword(ownerPortalUser.getPassword());

		try {
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONObject jsonIdObj = new JSONObject();
			JSONObject jsonAdObj = new JSONObject();
			JSONObject jsonCustomObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("userProfileId",
					ownerPortalUser.getUserProfileId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonVhObj.accumulate("vin", vin);
			jsonVhObj.accumulate("make", brand);

			jsonIdObj.accumulate("id", id);
			jsonIdObj.accumulate("action", action);

			//Added for CS details validate data for terms and condition accepted state

			jsonPrsnObj.accumulate("title", ownerPortalUser.getPrefixName());
			jsonPrsnObj.accumulate("firstName", ownerPortalUser.getFirstName());
			jsonPrsnObj.accumulate("middleName", ownerPortalUser.getMiddleName());
			jsonPrsnObj.accumulate("lastName", ownerPortalUser.getLastName());
			jsonPrsnObj.accumulate("secondLastName", ownerPortalUser.getSuffixName());
			jsonAdObj.accumulate("addressLine1", ownerPortalUser.getAddressText());
			jsonAdObj.accumulate("city", ownerPortalUser.getCityName());
			jsonAdObj.accumulate("region", state.getStateName());
			jsonAdObj.accumulate("postalCode", ownerPortalUser.getPostalCode());
			jsonAdObj.accumulate("country", ownerPortalUser.getCountryCode());

			jsonPrsnObj.accumulate("address", jsonAdObj);
			jsonPrsnObj.accumulate("mobileNumber", ownerPortalUser.getCellPhoneNumber());
			jsonPrsnObj.accumulate("password", password);

			if (Utility.isObjectNotNullorNotEmpty(mobileProvider)) {	        
				jsonPrsnObj.accumulate("mobileNetworkOperator", mobileProvider.getMobileProviderName());
			}
			else{
				jsonPrsnObj.accumulate("mobileNetworkOperator", "");	
			}
			jsonCustomObj.accumulate("accountSource", CUSTOMER);
			jsonPrsnObj.accumulate("customAttributes", jsonCustomObj);


			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);

			jsonObj.accumulate("messageUserAction", jsonIdObj);
			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setConnectedServiceResponse for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in setConnectedServiceResponse for the vin"
					+ vin, e);
		}

		return response;
	}

	/**
	 * @author x796314
	 * @use To accumulate json response for service contract validatedata
	 *      endpoint
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param vin
	 * @param vehicleInfo
	 * @return
	 */
	private JSONObject setServiceContractValidateDataResponse(
			JSONObject jsonObj, OwnerPortalUser ownerPortalUser, String vin,
			OwnerPortalVehicle vehicleInfo, String brand) {

		JSONObject response = null;

		try {
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonVhObj.accumulate("vin", vin);
			jsonVhObj.accumulate("make", brand);
			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);

			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setServiceContractValidateData response for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error(
					"Exception in setServiceContractValidateData response for the vin"
							+ vin, e);
		}

		return response;

	}

	/**
	 * @author dasp2
	 * @use To accumulate json response for service contract validatedata
	 *      endpoint
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param vin
	 * @param vehicleInfo
	 * @return
	 */
	private JSONObject setRecallValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser, String vin,
			OwnerPortalVehicle vehicleInfo, String brand) {

		JSONObject response = null;

		try {
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonVhObj.accumulate("vin", vin);
			jsonVhObj.accumulate("make", brand);
			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);

			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setRecallValidateDataResponse response for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error(
					"Exception in setRecallValidateDataResponse response for the vin"
							+ vin, e);
		}

		return response;

	}

	// ///////UPLOAD/////////

	private JSONObject setUploadDocumentValidateDataResponse(
			JSONObject jsonObj, OwnerPortalUser validUser, String vin,
			ValidateDataWrapper validateDataWrapper, String brand) {

		JSONObject response = null;
		try {

			JSONObject jsonVhObj = new JSONObject();
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					validUser.getPersonalHashId());
			jsonPrsnObj.accumulate("email", validUser.getEmailId());
			jsonVhObj.accumulate("vin", validateDataWrapper.getValidateData()
					.getVin());

			jsonObj.accumulate("person", jsonPrsnObj);
			jsonObj.accumulate("vehicle", jsonVhObj);

			jsonlastObj.accumulate("validateData", jsonObj);

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error(
					"JSONException in setUploadDocumentValidateDataResponse for the vin"
							+ validateDataWrapper.getValidateData().getVin(), e);
		} catch (Exception e) {
			LOG.error(
					"Exception in setUploadDocumentValidateDataResponse for the vin"
							+ validateDataWrapper.getValidateData().getVin(), e);
		}
		return response;

	}

	// /**
	// * @author rs101547
	// * @use To accumulate json response for get telematics endpoint
	// * @param subscription
	// * @param subscriptionSsoTokenVO
	// * @param make
	// * @param vin
	// * @return
	// */
	//
	// private JSONObject setGetTelematicsResponse(
	// List<TelematicsSubscriptionVO> subscription,
	// SubscriptionSsoTokenVO subscriptionSsoTokenVO, String make,
	// String vin, TelematicsVO telematicsVO,
	// OwnerPortalUser ownerPortalUser,
	// OwnerPortalVehicle ownerPortalVehicle) {
	// JSONObject response = null;
	// String status = "";
	// String encodedVin = "";
	//
	// LOG.info("inside get telematics for vin=" + vin);
	//
	// try {
	//
	// JSONArray jsonServicesArrayObj = new JSONArray();
	// JSONObject jsonlastObj = new JSONObject();
	// LOG.info("inside get telematics before iteration for vin=" + vin);
	// LOG.info("inside get telematics before iteration for size="
	// + subscription.size());
	// TelematicsSubscriptionVO telematicsSubscriptionVO = null;
	//
	// if (Utility.isObjectNotNullorNotEmpty(subscriptionSsoTokenVO)) {
	// for (Iterator<TelematicsSubscriptionVO> iterator = subscription
	// .iterator(); iterator.hasNext();) {
	// telematicsSubscriptionVO = (TelematicsSubscriptionVO) iterator
	// .next();
	//
	// List<OwnerPortalSubScriptionProduct> subScriptionProducts = vehicleLocal
	// .getSubScriptionProduct(telematicsSubscriptionVO
	// .getProductCode());
	// LOG.info("checkkkkkkkkkkkkkkk"
	// + telematicsSubscriptionVO.getEndDate());
	// LOG.info("inside get telematics subcription product size="
	// + subScriptionProducts.size());
	// if (Utility
	// .isObjectNotNullorNotEmpty(telematicsSubscriptionVO)) {
	// if (Utility
	// .isStringNotNullorNotEmpty(telematicsSubscriptionVO
	// .getStatus())) {
	// status = getTelematicsSubscriptionStatus(telematicsSubscriptionVO
	// .getStatus());
	// }
	// }
	// if (Utility.isObjectNotNullorNotEmpty(subScriptionProducts)
	// && !(subScriptionProducts.isEmpty())) {
	// // x566325 - New SSO SAML Url changes
	// if (telematicsVO.getNissanConnectMobileApps()
	// .equalsIgnoreCase("true")
	// && subScriptionProducts.get(0)
	// .getVehicleTelematicsOptionCode()
	// .equals(S2)) {
	// LOG.info("inside get telematics subcription product S2 for vin="
	// + vin);
	// JSONObject jsonservicesresponseObj = new JSONObject();
	//
	// LOG.info("inside get telematics subcription product S2 for vin Nissan="
	// + vin);
	// jsonservicesresponseObj.accumulate("id",
	// NISSANCONNECTMOBILEAPPS);
	//
	// jsonservicesresponseObj
	// .accumulate("status", status);
	// jsonservicesresponseObj
	// .accumulate(
	// "endDate",
	// Utility.convertToUTCFormat((Utility
	// .getDateFormatString(telematicsSubscriptionVO
	// .getEndDate()))));
	// jsonservicesresponseObj.accumulate(
	// "supportsMessaging", "false");
	// jsonservicesresponseObj.accumulate("statusChanged",
	// telematicsSubscriptionVO.getEndDate());
	// if (Utility
	// .isStringNotNullorNotEmpty(subscriptionSsoTokenVO
	// .getNissanConnectMobileAppsToken())) {
	// jsonservicesresponseObj
	// .accumulate(
	// "url",
	// NISSANCONNECT_APPS_SSO_URL
	// + subscriptionSsoTokenVO
	// .getNissanConnectMobileAppsToken()
	// + "&brandId=N&countryCode=us&vin="
	// + vin);
	// }
	// // jsonservicesresponseObj.accumulate("blocking",false);
	// // jsonservicesresponseObj.accumulate("message",
	// // "");
	// jsonServicesArrayObj.put(jsonservicesresponseObj);
	//
	// }
	// if (telematicsVO.getInfinitiInTouchApps()
	// .equalsIgnoreCase("true")
	// && subScriptionProducts.get(0)
	// .getVehicleTelematicsOptionCode()
	// .equals(S2)) {
	// LOG.info("inside get telematics subcription product S2 for vin infiniti="
	// + vin);
	// JSONObject jsonservicesresponseObj = new JSONObject();
	// jsonservicesresponseObj.accumulate("id",
	// INFINFITIINTOUCHAPPS);
	// jsonservicesresponseObj
	// .accumulate("status", status);
	// jsonservicesresponseObj
	// .accumulate(
	// "endDate",
	// Utility.convertToUTCFormat((Utility
	// .getDateFormatString(telematicsSubscriptionVO
	// .getEndDate()))));
	// jsonservicesresponseObj.accumulate(
	// "supportsMessaging", "false");
	// jsonservicesresponseObj.accumulate("statusChanged",
	// telematicsSubscriptionVO.getEndDate());
	// if (Utility
	// .isStringNotNullorNotEmpty(subscriptionSsoTokenVO
	// .getInfinitiInTouchAppsToken())) {
	// jsonservicesresponseObj
	// .accumulate(
	// "url",
	// INFINITI_INTOUCH_APPS_SSO_URL
	// + subscriptionSsoTokenVO
	// .getInfinitiInTouchAppsToken()
	// + "&brandId=I&countryCode=us&vin="
	// + vin);
	// }
	// // jsonservicesresponseObj.accumulate("blocking",false);
	// // jsonservicesresponseObj.accumulate("message",
	// // "");
	// jsonServicesArrayObj.put(jsonservicesresponseObj);
	// }
	// if (telematicsVO.getNissanConnectServices()
	// .equalsIgnoreCase("true")
	// && subScriptionProducts.get(0)
	// .getVehicleTelematicsOptionCode()
	// .equals(NS)) {
	//
	// JSONObject jsonservicesresponseObj = new JSONObject();
	// LOG.info("inside get telematics subcription product NS for vin ="
	// + vin);
	// jsonservicesresponseObj.accumulate("id",
	// NISSANCONNECTSERVICES);
	// jsonservicesresponseObj
	// .accumulate("status", status);
	// LOG.info("end date checkkkkkkkkk"
	// + telematicsSubscriptionVO.getEndDate());
	// jsonservicesresponseObj
	// .accumulate(
	// "endDate",
	// Utility.convertToUTCFormat((Utility
	// .getDateFormatString(telematicsSubscriptionVO
	// .getEndDate()))));
	// jsonservicesresponseObj.accumulate(
	// "supportsMessaging", "true");
	// jsonservicesresponseObj.accumulate("statusChanged",
	// telematicsSubscriptionVO.getEndDate());
	// // x566325 - New SSO SAML Url changes for NS
	// /*
	// * if (Utility
	// * .isStringNotNullorNotEmpty(subscriptionSsoTokenVO
	// * .getNissanConnectMobileAppsToken())) {
	// */
	// encodedVin = Util.getAESEncryptedVin(vin,
	// NISSAN_ENCRYPTEDKEY);
	// encodedVin = encodedVin.trim();
	// jsonservicesresponseObj.accumulate("url",
	// NISSANCONNECT_SERVICES_SSO_URL
	// + "&vehicleId=" + encodedVin
	// + "&lang=en-us");
	// // }
	// // jsonservicesresponseObj.accumulate("blocking",false);
	// // jsonservicesresponseObj.accumulate("message",
	// // "");
	// jsonServicesArrayObj.put(jsonservicesresponseObj);
	// }
	// }
	//
	// LOG.info("inside get telematics subcription product Setting array json ="
	// + vin);
	// }
	// }
	//
	// if (Utility.isObjectNotNullorNotEmpty(telematicsVO)) {
	//
	// if (telematicsVO.getNissanConnect().equalsIgnoreCase("true")) {
	// JSONObject jsonservicesresponseObjNC = new JSONObject();
	// LOG.info("inside get telematics subcription product IC for vin ="
	// + vin);
	// jsonservicesresponseObjNC.accumulate("id",
	// NISSANCONNECTNAVIGATION);
	// jsonservicesresponseObjNC.accumulate("status",
	// NOT_APPLICABLE);
	// jsonservicesresponseObjNC
	// .accumulate(
	// "endDate",
	// Utility.convertToUTCFormat((Utility
	// .getDateFormatString(telematicsSubscriptionVO
	// .getEndDate()))));
	// jsonservicesresponseObjNC.accumulate("supportsMessaging",
	// "true");
	// String token = createToken(ownerPortalUser,
	// ownerPortalVehicle);
	// if (Utility.isStringNotNullorNotEmpty(token)) {
	// jsonservicesresponseObjNC.accumulate("url",
	// NISSANCONNECT_SSO_URL + "token=" + token
	// + "&regionId=NNA&selectedVin=" + vin);
	// }
	// jsonServicesArrayObj.put(jsonservicesresponseObjNC);
	// }
	//
	// // x055765 - New SSO SAML Url changes for IC
	// if (telematicsVO.getInfinitiConnection().equalsIgnoreCase(
	// "true")) {
	// JSONObject jsonservicesresponseObjIC = new JSONObject();
	// LOG.info("inside get telematics subcription product IC for vin ="
	// + vin);
	// jsonservicesresponseObjIC.accumulate("id",
	// INFINFITICONNECTION);
	// jsonservicesresponseObjIC.accumulate("status",
	// PENDINGSTATUS);
	// jsonservicesresponseObjIC
	// .accumulate(
	// "endDate",
	// Utility.convertToUTCFormat((Utility
	// .getDateFormatString(telematicsSubscriptionVO
	// .getEndDate()))));
	// jsonservicesresponseObjIC.accumulate("supportsMessaging",
	// "true");
	// // String token = createToken(ownerPortalUser,
	// // ownerPortalVehicle);
	// // if (Utility.isStringNotNullorNotEmpty(token)) {
	// encodedVin = Util.getAESEncryptedVin(vin,
	// INFINITI_ENCRYPTEDKEY);
	// encodedVin = encodedVin.trim();
	// jsonservicesresponseObjIC.accumulate("url",
	// INFINITI_CONNECTION_URL + "&vehicleId="
	// + encodedVin + "&lang=en-us");
	// // }
	// jsonServicesArrayObj.put(jsonservicesresponseObjIC);
	// }
	// // x566325 - New SSO SAML Url changes for I2, I3, I4
	// if (telematicsVO.getInfinitiInTouchServices().equalsIgnoreCase(
	// "true")) {
	// JSONObject jsonservicesresponseObjI2 = new JSONObject();
	// LOG.info("inside get telematics subcription product I2 or I3 or I4 for vin ="
	// + vin);
	// jsonservicesresponseObjI2.accumulate("id",
	// INFINITIINTOUCHSERVICES);
	// jsonservicesresponseObjI2.accumulate("status",
	// PENDINGSTATUS);
	// jsonservicesresponseObjI2
	// .accumulate(
	// "endDate",
	// Utility.convertToUTCFormat((Utility
	// .getDateFormatString(telematicsSubscriptionVO
	// .getEndDate()))));
	// jsonservicesresponseObjI2.accumulate("supportsMessaging",
	// "false");
	// encodedVin = Util.getAESEncryptedVin(vin,
	// INFINITI_ENCRYPTEDKEY);
	// encodedVin = encodedVin.trim();
	// jsonservicesresponseObjI2.accumulate("url",
	// INFINITI_INTOUCH_SERVICES_SSO_URL + "&vehicleId="
	// + encodedVin + "&lang=en-us");
	// jsonServicesArrayObj.put(jsonservicesresponseObjI2);
	// }
	//
	// if (telematicsVO.getNissanConnectEV().equalsIgnoreCase("true")) {
	// LOG.info("==============inside get telematics NissanConnectEV for vin ="
	// + vin);
	// JSONObject jsonservicesresponseObj = new JSONObject();
	//
	// jsonservicesresponseObj.accumulate("id", NISSANCONNECTEV);
	//
	// // The status of the EV is identified from the entry in
	// // VHCL_CRWNGS, if CRWNGS_AGRMT_STS_CD is "Accepted" then
	// // the status is Active else Eligible
	// VehicleCarwings vehicleCarwings = vehicleLocal
	// .getVehicleCarwings(vin);
	// if (vehicleCarwings != null) {
	// if (vehicleCarwings.getCarwingsAgreementStatusCode()
	// .equalsIgnoreCase("ACCEPTED")) {
	// jsonservicesresponseObj
	// .accumulate("status", ACTIVE);
	// } else {
	// jsonservicesresponseObj.accumulate("status",
	// ELEGIBLE);
	// }
	// } else {
	// jsonservicesresponseObj.accumulate("status", ELEGIBLE);
	// }
	//
	// jsonservicesresponseObj.accumulate("endDate", "");
	// // As per Owner portal EV does have end date
	// // jsonservicesresponseObj
	// // .accumulate(
	// // "endDate",
	// // Utility.convertToUTCFormat((Utility
	// // .getDateFormatString(telematicsSubscriptionVO
	// // .getEndDate()))));
	// jsonservicesresponseObj.accumulate("supportsMessaging",
	// "true");
	//
	// jsonservicesresponseObj.accumulate("url",
	// "https://qa1.owners.nissanusa.com/heliosnowners/");
	// jsonServicesArrayObj.put(jsonservicesresponseObj);
	// }
	//
	// }
	//
	// LOG.info("inside get telematics subcription product setting response for vin ="
	// + vin);
	// if (jsonServicesArrayObj.length() > 0) {
	// LOG.info("inside get telematics Json array not null or empty ="
	// + vin);
	// response = jsonlastObj.accumulate("services",
	// jsonServicesArrayObj);
	// } else {
	// LOG.info("inside get telematics Json array  null or empty ="
	// + vin);
	// response = jsonlastObj;
	// }
	// } catch (JSONException e) {
	// LOG.error("JSONException in setGetTelematicsResponse for the vin"
	// + vin, e);
	// } catch (Exception e) {
	// LOG.error(
	// "Exception in setGetTelematicsResponse for the vin" + vin,
	// e);
	// }
	//
	// return response;
	// }

	/**
	 * @author rs101547 - Changes done by x497432
	 * @use To accumulate json response for get telematics endpoint
	 * @param subscription
	 * @param subscriptionSsoTokenVO
	 * @param make
	 * @param vin
	 * @return
	 */

	private JSONObject setGetTelematicsResponse(
			List<TelematicsSubscriptionVO> subscription,
			SubscriptionSsoTokenVO subscriptionSsoTokenVO, String make,
			String vin, TelematicsVO telematicsVO,
			OwnerPortalUser ownerPortalUser,
			OwnerPortalVehicle ownerPortalVehicle, String status,Set<String> telematicsInfo ) {
		JSONObject response = null;
		// String status = "UNDEFINED";
		String encodedVin = "";
		boolean error3Gvin= false;

		LOG.info("inside get telematics for vin=" + vin);
		LOG.info("inside get telematics for vin Status=" + status);
		LOG.info("inside get telematics Response for vin=" + telematicsInfo);
		
		if((telematicsInfo.contains(X1)
				|| telematicsInfo.contains(X2)
				|| telematicsInfo.contains(X3)
				|| telematicsInfo.contains(X4)
				|| telematicsInfo.contains(X5)
				|| telematicsInfo.contains(X6)
				|| telematicsInfo.contains(X7))){
			LOG.info("inside get telematics - telematicsInfo 3G=" + telematicsInfo);
			
			error3Gvin=true;
		}

		try {

			JSONArray jsonServicesArrayObj = new JSONArray();
			JSONObject jsonlastObj = new JSONObject();
			LOG.info("inside get telematics before iteration for vin=" + vin);
			LOG.info("inside get telematics before iteration for size="
					+ subscription.size());
			TelematicsSubscriptionVO telematicsSubscriptionVO = subscription
					.get(0);
			if (Utility.isObjectNotNullorNotEmpty(telematicsVO)) {

				if(error3Gvin){
					LOG.info("Inside error3Gvin get telematics before iteration for telematicsInfo=" + telematicsInfo);
					if (telematicsVO.getNissanConnectMobileApps().equalsIgnoreCase("true")
							&& status.equalsIgnoreCase(ACTIVE) 
							&& (telematicsInfo.contains(X1)
								|| telematicsInfo.contains(X2)
								|| telematicsInfo.contains(X3)
								|| telematicsInfo.contains(X5)
								|| telematicsInfo.contains(X6)
								|| telematicsInfo.contains(X7))) {
						LOG.info("inside get telematics before iteration for getNissanConnectMobileApps=" + vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						LOG.info("inside get telematics subcription product S2 for vin Nissan="
								+ vin);
						jsonservicesresponseObj.accumulate("id",
								NISSANCONNECTMOBILEAPPS);
						jsonservicesresponseObj.accumulate("status", ACTIVE);
						jsonservicesresponseObj.accumulate("endDate", "");
						jsonservicesresponseObj.accumulate("supportsMessaging",
								false);
						if (Utility
								.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
										.getNissanConnectMobileAppsToken())) {
							jsonservicesresponseObj
							.accumulate(
									"url",
									NISSANCONNECT_APPS_SSO_URL
									+ subscriptionSsoTokenVO
									.getNissanConnectMobileAppsToken()
									+ "&brandId=N&countryCode=us&vin="
									+ vin);
						}
						jsonServicesArrayObj.put(jsonservicesresponseObj);

					}
					if (telematicsVO.getInfinitiInTouchApps().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)&& 
							        (telematicsInfo.contains(X1)
									|| telematicsInfo.contains(X2)
									|| telematicsInfo.contains(X3)
									|| telematicsInfo.contains(X5)
									|| telematicsInfo.contains(X6)
									|| telematicsInfo.contains(X7))) {
						LOG.info("inside get telematics subcription product S2 for vin infiniti="
								+ vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						jsonservicesresponseObj.accumulate("id",
								INFINFITIINTOUCHAPPS);
						jsonservicesresponseObj.accumulate("status", ACTIVE);
						jsonservicesresponseObj.accumulate("endDate", "");
						jsonservicesresponseObj.accumulate("supportsMessaging",
								false);
						if (Utility
								.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
										.getInfinitiInTouchAppsToken())) {
							jsonservicesresponseObj.accumulate(
									"url",
									INFINITI_INTOUCH_APPS_SSO_URL
									+ subscriptionSsoTokenVO
									.getInfinitiInTouchAppsToken()
									+ "&brandId=I&countryCode=us&vin="
									+ vin);
						}
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					}
					if (telematicsVO.getNissanConnectServices().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)) {
						LOG.info("inside get telematics before iteration for getNissanConnectServices and ACTIVE=" + vin);

						JSONObject jsonservicesresponseObj = new JSONObject();
						LOG.info("inside get telematics subcription product NS for vin ="
								+ vin);
						jsonservicesresponseObj.accumulate("id",
								NISSANCONNECTSERVICES);
						jsonservicesresponseObj.accumulate("status", status);
						jsonservicesresponseObj
						.accumulate(
								"endDate", "");
						jsonservicesresponseObj.accumulate("supportsMessaging",
								true);
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					}

					if (telematicsVO.getNissanConnect().equalsIgnoreCase("true")) {
						JSONObject jsonservicesresponseObjNC = new JSONObject();
						LOG.info("inside get telematics subcription product IC for vin ="
								+ vin);
						jsonservicesresponseObjNC.accumulate("id",
								NISSANCONNECTNAVIGATION);
						jsonservicesresponseObjNC.accumulate("status", ACTIVE);
						jsonservicesresponseObjNC
						.accumulate(
								"endDate", "");
						jsonservicesresponseObjNC.accumulate("supportsMessaging",
								false);
						jsonServicesArrayObj.put(jsonservicesresponseObjNC);
					}

					if (telematicsVO.getInfinitiConnection().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)) {
						LOG.info("inside get telematics before iteration for getInfinitiConnection and ACTIVE=" + vin);
						JSONObject jsonservicesresponseObjIC = new JSONObject();
						LOG.info("inside get telematics subcription product IC for vin ="
								+ vin);
						jsonservicesresponseObjIC.accumulate("id",
								INFINFITICONNECTION);
						jsonservicesresponseObjIC.accumulate("status", status);
						jsonservicesresponseObjIC
						.accumulate(
								"endDate", "");
						jsonservicesresponseObjIC.accumulate("supportsMessaging",
								true);
						jsonServicesArrayObj.put(jsonservicesresponseObjIC);
					}
					if (telematicsVO.getInfinitiInTouchServices().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)) {
						JSONObject jsonservicesresponseObjI2 = new JSONObject();
						LOG.info("inside get telematics before iteration for getInfinitiInTouchServices and ACTIVE=" + vin);
						
						jsonservicesresponseObjI2.accumulate("id",
								INFINITIINTOUCHSERVICES);
						jsonservicesresponseObjI2.accumulate("status", status);
						jsonservicesresponseObjI2
						.accumulate(
								"endDate", "");
						jsonservicesresponseObjI2.accumulate("supportsMessaging",
								true);
						jsonServicesArrayObj.put(jsonservicesresponseObjI2);
					}

					if (telematicsVO.getNissanConnectEV().equalsIgnoreCase("true")) {
						
						LOG.info("inside getNissanConnectEV with Subscription");
						VehicleCarwings vehicleCarwings = vehicleLocal
								.getVehicleCarwings(vin);
						if (vehicleCarwings != null) {
							LOG.info("inside vehicleCarwings not null");
							if (vehicleCarwings.getCarwingsAgreementStatusCode()
									.equalsIgnoreCase("ACCEPTED")) {
								LOG.info("inside get telematics EV Accepted" + vin);
								JSONObject jsonservicesresponseObj = new JSONObject();
								jsonservicesresponseObj.accumulate("id", NISSANCONNECTEV);
								jsonservicesresponseObj
								.accumulate("status", ACTIVE);
								jsonservicesresponseObj.accumulate("endDate", "");
								jsonservicesresponseObj.accumulate("supportsMessaging",
										true);
								jsonServicesArrayObj.put(jsonservicesresponseObj);
							} 
							if (telematicsVO.getNissanConnectMobileApps().equalsIgnoreCase(
									"true") && vehicleCarwings.getCarwingsAgreementStatusCode()
									.equalsIgnoreCase("ACCEPTED")) {
								LOG.info("inside get telematics EV S2 getNissanConnectMobileApps=" + vin);
								JSONObject jsonservicesresponseObj = new JSONObject();
								LOG.info("inside get telematics subcription product S2 for vin Nissan="
										+ vin);
								jsonservicesresponseObj.accumulate("id",
										NISSANCONNECTMOBILEAPPS);
								jsonservicesresponseObj.accumulate("status", ACTIVE);
								jsonservicesresponseObj.accumulate("endDate", "");
								jsonservicesresponseObj.accumulate("supportsMessaging",
										false);
								if (Utility
										.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
												.getNissanConnectMobileAppsToken())) {
									jsonservicesresponseObj
									.accumulate(
											"url",
											NISSANCONNECT_APPS_SSO_URL
											+ subscriptionSsoTokenVO
											.getNissanConnectMobileAppsToken()
											+ "&brandId=N&countryCode=us&vin="
											+ vin);
								}
								jsonServicesArrayObj.put(jsonservicesresponseObj);
								
							}
						}
					}

				}
				//2g-3g Changes ends
			else  {
				LOG.info("Inside ELSE block- Not 3g/2g vin");
				if (telematicsVO.getNissanConnectMobileApps().equalsIgnoreCase(
						"true")) {
					JSONObject jsonservicesresponseObj = new JSONObject();

					LOG.info("inside get telematics subcription product S2 for vin Nissan="
							+ vin);
					jsonservicesresponseObj.accumulate("id",
							NISSANCONNECTMOBILEAPPS);
					jsonservicesresponseObj.accumulate("status", ACTIVE);
					jsonservicesresponseObj.accumulate("endDate", "");
					jsonservicesresponseObj.accumulate("supportsMessaging",
							false);
					if (Utility
							.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
									.getNissanConnectMobileAppsToken())) {
						jsonservicesresponseObj
						.accumulate(
								"url",
								NISSANCONNECT_APPS_SSO_URL
								+ subscriptionSsoTokenVO
								.getNissanConnectMobileAppsToken()
								+ "&brandId=N&countryCode=us&vin="
								+ vin);
					}
					jsonServicesArrayObj.put(jsonservicesresponseObj);

				}
				if (telematicsVO.getInfinitiInTouchApps().equalsIgnoreCase(
						"true")) {
					LOG.info("inside get telematics subcription product S2 for vin infiniti="
							+ vin);
					JSONObject jsonservicesresponseObj = new JSONObject();
					jsonservicesresponseObj.accumulate("id",
							INFINFITIINTOUCHAPPS);
					jsonservicesresponseObj.accumulate("status", ACTIVE);
					jsonservicesresponseObj.accumulate("endDate", "");
					jsonservicesresponseObj.accumulate("supportsMessaging",
							false);
					if (Utility
							.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
									.getInfinitiInTouchAppsToken())) {
						jsonservicesresponseObj.accumulate(
								"url",
								INFINITI_INTOUCH_APPS_SSO_URL
								+ subscriptionSsoTokenVO
								.getInfinitiInTouchAppsToken()
								+ "&brandId=I&countryCode=us&vin="
								+ vin);
					}
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				}
				if (telematicsVO.getNissanConnectServices().equalsIgnoreCase(
						"true")) {

					JSONObject jsonservicesresponseObj = new JSONObject();
					LOG.info("inside get telematics subcription product NS for vin ="
							+ vin);
					jsonservicesresponseObj.accumulate("id",
							NISSANCONNECTSERVICES);
					jsonservicesresponseObj.accumulate("status", status);
					LOG.info("end date checkkkkkkkkk"
							+ telematicsSubscriptionVO.getEndDate());
					/*jsonservicesresponseObj
							.accumulate(
									"endDate",
									Utility.convertToUTCFormat((Utility
											.getDateFormatString(telematicsSubscriptionVO
													.getEndDate()))));*/
					jsonservicesresponseObj
					.accumulate(
							"endDate", "");
					jsonservicesresponseObj.accumulate("supportsMessaging",
							true);
					// encodedVin =
					// Util.getAESEncryptedVin(vin,NISSAN_ENCRYPTEDKEY);
					// encodedVin = encodedVin.trim();
					// jsonservicesresponseObj.accumulate("url",
					// NISSANCONNECT_SERVICES_SSO_URL
					// + "&vehicleId=" + encodedVin
					// + "&lang=en-us");
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				}

				if (telematicsVO.getNissanConnect().equalsIgnoreCase("true")) {
					JSONObject jsonservicesresponseObjNC = new JSONObject();
					LOG.info("inside get telematics subcription product IC for vin ="
							+ vin);
					jsonservicesresponseObjNC.accumulate("id",
							NISSANCONNECTNAVIGATION);
					jsonservicesresponseObjNC.accumulate("status", ACTIVE);
					/*jsonservicesresponseObjNC
							.accumulate(
									"endDate",
									Utility.convertToUTCFormat((Utility
											.getDateFormatString(telematicsSubscriptionVO
													.getEndDate()))));*/
					jsonservicesresponseObjNC
					.accumulate(
							"endDate", "");
					jsonservicesresponseObjNC.accumulate("supportsMessaging",
							false);
					// String token = createToken(ownerPortalUser,
					// ownerPortalVehicle);
					// if (Utility.isStringNotNullorNotEmpty(token)) {
					// jsonservicesresponseObjNC.accumulate("url",
					// NISSANCONNECT_SSO_URL + "token=" + token
					// + "&regionId=NNA&selectedVin=" + vin);
					// }
					jsonServicesArrayObj.put(jsonservicesresponseObjNC);
				}

				// x055765 - New SSO SAML Url changes for IC
				if (telematicsVO.getInfinitiConnection().equalsIgnoreCase(
						"true")) {
					JSONObject jsonservicesresponseObjIC = new JSONObject();
					LOG.info("inside get telematics subcription product IC for vin ="
							+ vin);
					jsonservicesresponseObjIC.accumulate("id",
							INFINFITICONNECTION);
					jsonservicesresponseObjIC.accumulate("status", status);
					/*jsonservicesresponseObjIC
							.accumulate(
									"endDate",
									Utility.convertToUTCFormat((Utility
											.getDateFormatString(telematicsSubscriptionVO
													.getEndDate()))));*/
					jsonservicesresponseObjIC
					.accumulate(
							"endDate", "");
					jsonservicesresponseObjIC.accumulate("supportsMessaging",
							true);
					// encodedVin = Util.getAESEncryptedVin(vin,
					// INFINITI_ENCRYPTEDKEY);
					// encodedVin = encodedVin.trim();
					// jsonservicesresponseObjIC.accumulate("url",
					// INFINITI_CONNECTION_URL + "&vehicleId="
					// + encodedVin + "&lang=en-us");
					jsonServicesArrayObj.put(jsonservicesresponseObjIC);
				}
				// x566325 - New SSO SAML Url changes for I2, I3, I4
				if (telematicsVO.getInfinitiInTouchServices().equalsIgnoreCase(
						"true")) {
					JSONObject jsonservicesresponseObjI2 = new JSONObject();
					LOG.info("inside get telematics subcription product I2 or I3 or I4 for vin ="
							+ vin);
					jsonservicesresponseObjI2.accumulate("id",
							INFINITIINTOUCHSERVICES);
					jsonservicesresponseObjI2.accumulate("status", status);
					/*jsonservicesresponseObjI2
							.accumulate(
									"endDate",
									Utility.convertToUTCFormat((Utility
											.getDateFormatString(telematicsSubscriptionVO
													.getEndDate()))));*/
					jsonservicesresponseObjI2
					.accumulate(
							"endDate", "");
					jsonservicesresponseObjI2.accumulate("supportsMessaging",
							true);
					// encodedVin =
					// Util.getAESEncryptedVin(vin,INFINITI_ENCRYPTEDKEY);
					// encodedVin = encodedVin.trim();
					// jsonservicesresponseObjI2.accumulate("url",
					// INFINITI_INTOUCH_SERVICES_SSO_URL + "&vehicleId="
					// + encodedVin + "&lang=en-us");
					jsonServicesArrayObj.put(jsonservicesresponseObjI2);
				}

				if (telematicsVO.getNissanConnectEV().equalsIgnoreCase("true")) {
					LOG.info("==============inside get telematics NissanConnectEV for vin ="
							+ vin);
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj.accumulate("id", NISSANCONNECTEV);

					// The status of the EV is identified from the entry in
					// VHCL_CRWNGS, if CRWNGS_AGRMT_STS_CD is "Accepted" then
					// the status is Active else Eligible
					VehicleCarwings vehicleCarwings = vehicleLocal
							.getVehicleCarwings(vin);
					if (vehicleCarwings != null) {
						if (vehicleCarwings.getCarwingsAgreementStatusCode()
								.equalsIgnoreCase("ACCEPTED")) {
							jsonservicesresponseObj
							.accumulate("status", ACTIVE);
						} else {
							jsonservicesresponseObj.accumulate("status",
									ELIGIBLE);
						}
					} else {
						jsonservicesresponseObj.accumulate("status", ELIGIBLE);
					}

					jsonservicesresponseObj.accumulate("endDate", "");
					// As per Owner portal EV does have end date
					// jsonservicesresponseObj
					// .accumulate(
					// "endDate",
					// Utility.convertToUTCFormat((Utility
					// .getDateFormatString(telematicsSubscriptionVO
					// .getEndDate()))));
					jsonservicesresponseObj.accumulate("supportsMessaging",
							true);

					// jsonservicesresponseObj.accumulate("url",NISSAN_EV_URL);
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				}
			}

			}

			LOG.info("inside get telematics subcription product setting response for vin ="
					+ vin);
			if (jsonServicesArrayObj.length() > 0) {
				LOG.info("inside get telematics Json array not null or empty ="
						+ vin);
				response = jsonlastObj.accumulate("services",
						jsonServicesArrayObj);
			} else {
				LOG.info("inside get telematics Json array  null or empty ="
						+ vin);
				response = jsonlastObj;
			}
		} catch (JSONException e) {
			LOG.error("JSONException in setGetTelematicsResponse for the vin"
					+ vin, e);
		} catch (Exception e) {
			LOG.error(
					"Exception in setGetTelematicsResponse for the vin" + vin,
					e);
		}

		return response;
	}

	/**
	 * @author rs101547
	 * @use to accumulate the json response for get telematics endpoint in case
	 *      subscription is not available
	 * @param telematicsInfo
	 * @param make
	 * @param vin
	 * @return
	 */
	private JSONObject setGetTelematicsResponseWithoutSubscription(
			TelematicsVO telematicsVO,
			SubscriptionSsoTokenVO subscriptionSsoTokenVO, String make,
			String vin, OwnerPortalUser ownerPortalUser,
			OwnerPortalVehicle ownerPortalVehicle, String status, Set<String> telematicsInfo ) {

		JSONObject response = null;
		String telematics = "";
		String encodedVin = "";
		boolean error3Gvin= false;

		LOG.info("get telematics response without subscription for vin=" + vin);
		try {

			JSONArray jsonServicesArrayObj = new JSONArray();
			JSONObject jsonlastObj = new JSONObject();
			if((telematicsInfo.contains(X1)
					|| telematicsInfo.contains(X2)
					|| telematicsInfo.contains(X3)
					|| telematicsInfo.contains(X4)
					|| telematicsInfo.contains(X5)
					|| telematicsInfo.contains(X6)
					|| telematicsInfo.contains(X7))){
				LOG.info("inside get telematics - telematicsInfo 3G =" + telematicsInfo);
				
				error3Gvin=true;
			}

			if (Utility.isObjectNotNullorNotEmpty(telematicsVO)) {
				if(error3Gvin){
					LOG.info("Inside error3Gvin get telematics without subscription before iteration for telematicsInfo=" + telematicsInfo);
					if (telematicsVO.getNissanConnectMobileApps().equalsIgnoreCase("true")
							&& status.equalsIgnoreCase(ACTIVE) 
							&& (telematicsInfo.contains(X1)
								|| telematicsInfo.contains(X2)
								|| telematicsInfo.contains(X3)
								|| telematicsInfo.contains(X5)
								|| telematicsInfo.contains(X6)
								|| telematicsInfo.contains(X7))) {
						LOG.info("inside get telematics before iteration for getNissanConnectMobileApps=" + vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						LOG.info("inside get telematics subcription product S2 for vin Nissan="
								+ vin);
						jsonservicesresponseObj.accumulate("id",
								NISSANCONNECTMOBILEAPPS);
						jsonservicesresponseObj.accumulate("status", ACTIVE);
						jsonservicesresponseObj.accumulate("endDate", "");
						jsonservicesresponseObj.accumulate("supportsMessaging",
								false);
						if (Utility
								.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
										.getNissanConnectMobileAppsToken())) {
							jsonservicesresponseObj
							.accumulate(
									"url",
									NISSANCONNECT_APPS_SSO_URL
									+ subscriptionSsoTokenVO
									.getNissanConnectMobileAppsToken()
									+ "&brandId=N&countryCode=us&vin="
									+ vin);
						}
						jsonServicesArrayObj.put(jsonservicesresponseObj);

					}
					if (telematicsVO.getInfinitiInTouchApps().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE) &&
							         (telematicsInfo.contains(X1)
									|| telematicsInfo.contains(X2)
									|| telematicsInfo.contains(X3)
									|| telematicsInfo.contains(X5)
									|| telematicsInfo.contains(X6)
									|| telematicsInfo.contains(X7))) {
						LOG.info("inside get telematics subcription product S2 for vin infiniti="
								+ vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						jsonservicesresponseObj.accumulate("id",
								INFINFITIINTOUCHAPPS);
						jsonservicesresponseObj.accumulate("status", ACTIVE);
						jsonservicesresponseObj.accumulate("endDate", "");
						jsonservicesresponseObj.accumulate("supportsMessaging",
								false);
						if (Utility
								.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
										.getInfinitiInTouchAppsToken())) {
							jsonservicesresponseObj.accumulate(
									"url",
									INFINITI_INTOUCH_APPS_SSO_URL
									+ subscriptionSsoTokenVO
									.getInfinitiInTouchAppsToken()
									+ "&brandId=I&countryCode=us&vin="
									+ vin);
						}
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					}
					if (telematicsVO.getNissanConnectServices().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)) {
						LOG.info("inside get telematics before iteration for getNissanConnectServices and ACTIVE=" + vin);

						JSONObject jsonservicesresponseObj = new JSONObject();
						LOG.info("inside get telematics subcription product NS for vin ="
								+ vin);
						jsonservicesresponseObj.accumulate("id",
								NISSANCONNECTSERVICES);
						jsonservicesresponseObj.accumulate("status", status);
						jsonservicesresponseObj
						.accumulate(
								"endDate", "");
						jsonservicesresponseObj.accumulate("supportsMessaging",
								true);
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					}

					if (telematicsVO.getNissanConnect().equalsIgnoreCase("true")) {
						JSONObject jsonservicesresponseObjNC = new JSONObject();
						LOG.info("inside get telematics subcription product NC for vin ="
								+ vin);
						jsonservicesresponseObjNC.accumulate("id",
								NISSANCONNECTNAVIGATION);
						jsonservicesresponseObjNC.accumulate("status", ACTIVE);
						jsonservicesresponseObjNC
						.accumulate(
								"endDate", "");
						jsonservicesresponseObjNC.accumulate("supportsMessaging",
								false);
						jsonServicesArrayObj.put(jsonservicesresponseObjNC);
					}

					if (telematicsVO.getInfinitiConnection().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)) {
						LOG.info("inside get telematics before iteration for getInfinitiConnection and ACTIVE=" + vin);
						JSONObject jsonservicesresponseObjIC = new JSONObject();
						LOG.info("inside get telematics subcription product IC for vin ="
								+ vin);
						jsonservicesresponseObjIC.accumulate("id",
								INFINFITICONNECTION);
						jsonservicesresponseObjIC.accumulate("status", status);
						jsonservicesresponseObjIC
						.accumulate(
								"endDate", "");
						jsonservicesresponseObjIC.accumulate("supportsMessaging",
								true);
						jsonServicesArrayObj.put(jsonservicesresponseObjIC);
					}
					if (telematicsVO.getInfinitiInTouchServices().equalsIgnoreCase(
							"true") && status.equalsIgnoreCase(ACTIVE)) {
						JSONObject jsonservicesresponseObjI2 = new JSONObject();
						LOG.info("inside get telematics before iteration for getInfinitiInTouchServices and ACTIVE=" + vin);
						
						jsonservicesresponseObjI2.accumulate("id",
								INFINITIINTOUCHSERVICES);
						jsonservicesresponseObjI2.accumulate("status", status);
						jsonservicesresponseObjI2
						.accumulate(
								"endDate", "");
						jsonservicesresponseObjI2.accumulate("supportsMessaging",
								true);
						jsonServicesArrayObj.put(jsonservicesresponseObjI2);
					}

					if (telematicsVO.getNissanConnectEV().equalsIgnoreCase("true")) {
						
						LOG.info("inside getNissanConnectEV without subscription");
						VehicleCarwings vehicleCarwings = vehicleLocal
								.getVehicleCarwings(vin);
						if (vehicleCarwings != null) {
							LOG.info("inside getNissanConnectEV without subscription vehicleCarwings not null");
							
							if (vehicleCarwings.getCarwingsAgreementStatusCode()
									.equalsIgnoreCase("ACCEPTED")) {
								LOG.info("inside get telematics EV Accepted" + vin);
								JSONObject jsonservicesresponseObj = new JSONObject();
								jsonservicesresponseObj.accumulate("id", NISSANCONNECTEV);
								jsonservicesresponseObj
								.accumulate("status", ACTIVE);
								jsonservicesresponseObj.accumulate("endDate", "");
								jsonservicesresponseObj.accumulate("supportsMessaging",
										true);
								jsonServicesArrayObj.put(jsonservicesresponseObj);
							} 
							if (telematicsVO.getNissanConnectMobileApps().equalsIgnoreCase(
									"true") && vehicleCarwings.getCarwingsAgreementStatusCode()
									.equalsIgnoreCase("ACCEPTED")) {
								LOG.info("inside get telematics EV S2 getNissanConnectMobileApps=" + vin);
								JSONObject jsonservicesresponseObj = new JSONObject();
								LOG.info("inside get telematics subcription product S2 for vin Nissan="
										+ vin);
								jsonservicesresponseObj.accumulate("id",
										NISSANCONNECTMOBILEAPPS);
								jsonservicesresponseObj.accumulate("status", ACTIVE);
								jsonservicesresponseObj.accumulate("endDate", "");
								jsonservicesresponseObj.accumulate("supportsMessaging",
										false);
								if (Utility
										.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
												.getNissanConnectMobileAppsToken())) {
									jsonservicesresponseObj
									.accumulate(
											"url",
											NISSANCONNECT_APPS_SSO_URL
											+ subscriptionSsoTokenVO
											.getNissanConnectMobileAppsToken()
											+ "&brandId=N&countryCode=us&vin="
											+ vin);
								}
								jsonServicesArrayObj.put(jsonservicesresponseObj);
								
							}
						}
					}
				}
				else{
					LOG.info("inside get telematics without subcription product telematics code ="
						+ telematics + " for vin" + vin);
				// telematics = (String) iterator.next();
				LOG.info("inside get telematics without subcription product telematics code ="
						+ telematics + " for vin" + vin);
				if (Utility.isObjectNotNullorNotEmpty(subscriptionSsoTokenVO)) {
					// x566325 - New SSO SAML Url changes for NS
					if (telematicsVO.getNissanConnectMobileApps()
							.equalsIgnoreCase("true")) {
						LOG.info("==============inside get telematics NissanConnectMobileApps for vin ="
								+ vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						jsonservicesresponseObj.accumulate("id",
								NISSANCONNECTMOBILEAPPS);
						jsonservicesresponseObj.accumulate("status", ELIGIBLE);
						jsonservicesresponseObj.accumulate("supportsMessaging",
								false);
						if (Utility
								.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
										.getNissanConnectMobileAppsToken())) {
							jsonservicesresponseObj
							.accumulate(
									"url",
									NISSANCONNECT_APPS_SSO_URL
									+ subscriptionSsoTokenVO
									.getNissanConnectMobileAppsToken()
									+ "&brandId=N&countryCode=us&vin="
									+ vin);
						}
						jsonServicesArrayObj.put(jsonservicesresponseObj);

					}
					if (telematicsVO.getInfinitiInTouchApps().equalsIgnoreCase(
							"true")) {
						LOG.info("==============inside get telematics InfinitiInTouchApps for vin ="
								+ vin);
						LOG.info("inside brand infiniti =" + telematics
								+ " for vin" + vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						jsonservicesresponseObj.accumulate("id",
								INFINFITIINTOUCHAPPS);
						jsonservicesresponseObj.accumulate("status", ELIGIBLE);

						jsonservicesresponseObj.accumulate("supportsMessaging",
								false);
						if (Utility
								.isStringNotNullorNotEmpty(subscriptionSsoTokenVO
										.getInfinitiInTouchAppsToken())) {
							jsonservicesresponseObj
							.accumulate(
									"url",
									INFINITI_INTOUCH_APPS_SSO_URL
									+ subscriptionSsoTokenVO
									.getInfinitiInTouchAppsToken()
									+ "&brandId=I&countryCode=us&vin="
									+ vin);
						}
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					}
					// }

					if (telematicsVO.getNissanConnectServices()
							.equalsIgnoreCase("true")) {
						LOG.info("=============inside get telematics NissanConnectServices for vin ="
								+ vin);
						JSONObject jsonservicesresponseObj = new JSONObject();
						jsonservicesresponseObj.accumulate("id",
								NISSANCONNECTSERVICES);
						jsonservicesresponseObj.accumulate("status", status);
						jsonservicesresponseObj.accumulate("supportsMessaging",
								true);
						// x566325 - New SSO SAML Url changes for NS
						/*
						 * if (Utility
						 * .isStringNotNullorNotEmpty(subscriptionSsoTokenVO
						 * .getNissanConnectMobileAppsToken())) {
						 */
						// encodedVin = Util.getAESEncryptedVin(vin,
						// NISSAN_ENCRYPTEDKEY);
						// encodedVin = encodedVin.trim();
						// jsonservicesresponseObj.accumulate("url",
						// NISSANCONNECT_SERVICES_SSO_URL + "&vehicleId="
						// + encodedVin + "&lang=en-us");
						// }
						jsonServicesArrayObj.put(jsonservicesresponseObj);

					}

				}

				// x566325 - New SSO SAML Url changes for IC
				if (telematicsVO.getInfinitiConnection().equalsIgnoreCase(
						"true")) {
					LOG.info("============inside get telematics InfinitiConnection for vin ="
							+ vin);
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj.accumulate("id",
							INFINFITICONNECTION);
					jsonservicesresponseObj.accumulate("status", status);
					jsonservicesresponseObj.accumulate("supportsMessaging",
							true);
					// String token = createToken(ownerPortalUser,
					// ownerPortalVehicle);
					// if (Utility.isStringNotNullorNotEmpty(token)) {
					// encodedVin = Util.getAESEncryptedVin(vin,
					// INFINITI_ENCRYPTEDKEY);
					// encodedVin = encodedVin.trim();
					// jsonservicesresponseObj.accumulate("url",
					// INFINITI_CONNECTION_URL + "&vehicleId="
					// + encodedVin + "&lang=en-us");
					// }
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				}

				if (telematicsVO.getNissanConnect().equalsIgnoreCase("true")) {
					LOG.info("============inside get telematics NissanConnect for vin ="
							+ vin);
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj.accumulate("id",
							NISSANCONNECTNAVIGATION);
					jsonservicesresponseObj.accumulate("status", status);
					jsonservicesresponseObj.accumulate("supportsMessaging",
							true);
					// String token = createToken(ownerPortalUser,
					// ownerPortalVehicle);
					// if (Utility.isStringNotNullorNotEmpty(token)) {
					// jsonservicesresponseObj.accumulate("url",
					// NISSANCONNECT_SSO_URL + "token=" + token
					// + "&regionId=NNA&selectedVin=" + vin);
					//
					// }
					jsonServicesArrayObj.put(jsonservicesresponseObj);

				}// x566325 - New SSO SAML Url changes for I2, I3, I4
				if (telematicsVO.getInfinitiInTouchServices().equalsIgnoreCase(
						"true")) {
					LOG.info("============inside get telematics InfinitiInTouchServices() for vin ="
							+ vin);
					JSONObject jsonservicesresponseObjI2 = new JSONObject();
					LOG.info("inside get telematics subcription product I2 or I3 or I4 for vin ="
							+ vin);
					jsonservicesresponseObjI2.accumulate("id",
							INFINITIINTOUCHSERVICES);
					jsonservicesresponseObjI2.accumulate("status", status);
					jsonservicesresponseObjI2.accumulate("supportsMessaging",
							true);
					/*
					 * if (Utility
					 * .isStringNotNullorNotEmpty(subscriptionSsoTokenVO
					 * .getInfinitiInTouchServicesToken() )) {
					 */
					// encodedVin = Util.getAESEncryptedVin(vin,
					// INFINITI_ENCRYPTEDKEY);
					// encodedVin = encodedVin.trim();
					// jsonservicesresponseObjI2.accumulate("url",
					// INFINITI_INTOUCH_SERVICES_SSO_URL + "&vehicleId="
					// + encodedVin + "&lang=en-us");
					// }
					jsonServicesArrayObj.put(jsonservicesresponseObjI2);

				}
				if (telematicsVO.getNissanConnectEV().equalsIgnoreCase("true")) {
					LOG.info("==============inside get telematics NissanConnectEV for vin ="
							+ vin);
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj.accumulate("id", NISSANCONNECTEV);
					// The status of the EV is identified from the entry in
					// VHCL_CRWNGS, if CRWNGS_AGRMT_STS_CD is "Accepted" then
					// the status is Active else Eligible
					VehicleCarwings vehicleCarwings = vehicleLocal
							.getVehicleCarwings(vin);
					if (vehicleCarwings != null) {
						if (vehicleCarwings.getCarwingsAgreementStatusCode()
								.equalsIgnoreCase("ACCEPTED")) {
							jsonservicesresponseObj
							.accumulate("status", ACTIVE);
						} else {
							jsonservicesresponseObj.accumulate("status",
									ELIGIBLE);
						}
					} else {
						jsonservicesresponseObj.accumulate("status", ELIGIBLE);
					}
					jsonservicesresponseObj.accumulate("endDate", "");
					jsonservicesresponseObj.accumulate("supportsMessaging",
							true);

					// jsonservicesresponseObj.accumulate("url","https://qa1.owners.nissanusa.com/heliosnowners/");

					jsonServicesArrayObj.put(jsonservicesresponseObj);
				}

				LOG.info("inside get telematics subcription product Setting array json ="
						+ vin);

				// }

			}
		}

			if (jsonServicesArrayObj.length() > 0) {

				response = jsonlastObj.accumulate("services",
						jsonServicesArrayObj);
			} else {
				response = jsonlastObj;
			}
		} catch (JSONException e) {
			LOG.error(
					"JSONException in setGetTelematicsResponseWithoutSubscription for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error(
					"Exception in setGetTelematicsResponseWithoutSubscription for the vin"
							+ vin, e);
		}
		return response;
	}

	/**
	 * @author x550910
	 * @use to accumulate the json response for get telematics endpoint if the
	 *      VIN has AT&T service enabled
	 * @param telematicsInfo
	 * @param User
	 *            info
	 * @param vin
	 * @param T
	 *            &C status - isBlocking
	 * @return JSON object
	 */

	private JSONObject setGetATTWifiResponse(String vin,
			TelematicsVO telematicsVO, OwnerPortalUser ownerPortalUser,
			String isBlocking, OwnerPortalVehicleTelematicsCodeMaster telematicsCodeInfo,
			String make, String status ) {

		JSONObject response = null;
		//x116202
		String optionTypCode = telematicsCodeInfo.getOptionTypeCode();
		State state = userLocal.getStateByStateKey(ownerPortalUser.getStateKey());
		String stateCode = state.getStateCode();
		//

		LOG.info("inside setGetATTWifiResponse for vin = " + vin);
		LOG.info("inside setGetATTWifiResponse for optionTypCode = " + optionTypCode);
		LOG.info("inside setGetATTWifiResponse for isBlocking = " + isBlocking);
		LOG.info("inside setGetATTWifiResponse for State Code = " + stateCode);
		LOG.info("inside setGetATTWifiResponse for telematicsCodeInfo.getTcuTypeCode() = " + telematicsCodeInfo.getTcuTypeCode());
		try {
			JSONArray jsonServicesArrayObj = new JSONArray();
			JSONObject jsonlastObj = new JSONObject();
			LOG.info("inside setGetATTWifiResponse before iteration - vin = " + vin);

			if (isBlocking.equalsIgnoreCase("false")) {
				//T&Cs accepted - x116202
				JSONObject jsonservicesresponseObj = new JSONObject();
				if (telematicsVO.getNissanConnectwithWifiHotspot().equalsIgnoreCase("true")
						&& telematicsCodeInfo.getTcuTypeCode().equalsIgnoreCase("A-IVC") || telematicsCodeInfo.getTcuTypeCode().equalsIgnoreCase("A-IVC K")) { // x100994 - OS-1742
					LOG.info("Inside get telematics for AT&T wifi for N6,N7,M6,M7,M8,M9,I8,I9,M3,M4,M5 vehicle terms and condition accepted"
							+ vin);
					jsonservicesresponseObj.accumulate("id",
							NISSANCONNECTWITHWIFIHOTSPOT);
					jsonservicesresponseObj.accumulate("supportsMessaging",
							false);
					jsonservicesresponseObj.accumulate("status", ELIGIBLE);
					//x100994 - OS-1742
					if(optionTypCode.equalsIgnoreCase("N6") || optionTypCode.equalsIgnoreCase("N7")){
						LOG.info("setGetATTWifiResponse - optiontypecode is N6/N7 and isBlocking is false");
						jsonservicesresponseObj.accumulate("url",
								NISSANCONNECTWITHWIFIHOTSPOTURL
								//x550910---NAR 4557
								+ "?"
								+ "&country=US"
								);
					} else if(optionTypCode.equalsIgnoreCase("M6") || optionTypCode.equalsIgnoreCase("M7") || optionTypCode.equalsIgnoreCase("M8") || optionTypCode.contains("M9") || optionTypCode.contains(E1)){
						LOG.info("setGetATTWifiResponse - response optiontypecode is M6/M7/M8/M9 and isBlocking is false");
						jsonservicesresponseObj.accumulate("url",
								NISSANCONNECTWITHWIFIHOTSPOTURL
								//x550910---NAR 4557
								+ "?"
								//x116202---OS-1742
								+ "&vin="+ vin
								+ "&firstName=" + ownerPortalUser.getFirstName()
								+ "&lastName=" + ownerPortalUser.getLastName()
								+ "&street1=" + ownerPortalUser.getAddressText()
								+ "&city=" + ownerPortalUser.getCityName()
								+ "&state="	+ stateCode
								+ "&zip=" + ownerPortalUser.getPostalCode()
								+ "&email=" + ownerPortalUser.getEmailId()
								+ "&country=US"
								+ "&language=" + ownerPortalUser.getLanguageCode()
								+ "&phoneNumber=" + ownerPortalUser.getCellPhoneNumber()
								);
					}

				} else if (telematicsVO.getInfinitiIntouchwithWifiHotspot().equalsIgnoreCase("true")
						&& telematicsCodeInfo.getTcuTypeCode().equalsIgnoreCase("A-IVC") ) {
					LOG.info("Inside get telematics for AT&T wifi for I6,I7,I8,I9 vehicle terms and condition accepted"
							+ vin);
					jsonservicesresponseObj.accumulate("id",
							INFINITIINTOUCHWITHWIFIHOTSPOT);
					jsonservicesresponseObj.accumulate("supportsMessaging",
							false);
					jsonservicesresponseObj.accumulate("status", ELIGIBLE);
					if(optionTypCode.equalsIgnoreCase("I6") || optionTypCode.equalsIgnoreCase("I7") || optionTypCode.equalsIgnoreCase("I8") || optionTypCode.equalsIgnoreCase("I9")){

						LOG.info("setGetATTWifiResponse - optiontypecode is I6/I7/I8/I9 and isBlocking is false");
						jsonservicesresponseObj.accumulate("url",
								INFINITIINTOUCHWITHWIFIHOTSPOTURL
								//x550910---NAR 4557
								+ "?"
								+ "&country=US"
								);
					
						
					}
					else if(optionTypCode.contains("M3") || optionTypCode.contains("M4") || optionTypCode.contains("M5")){

						LOG.info("setGetATTWifiResponse - response optiontypecode is M3/M4/M5 and isBlocking is false");
						jsonservicesresponseObj.accumulate("url",
								INFINITIINTOUCHWITHWIFIHOTSPOTURL
								//x550910---NAR 4557
								+ "?"
								//x116202---OS-1742
								+ "&vin="+ vin
								+ "&firstName=" + ownerPortalUser.getFirstName()
								+ "&lastName=" + ownerPortalUser.getLastName()
								+ "&street1=" + ownerPortalUser.getAddressText()
								+ "&city=" + ownerPortalUser.getCityName()
								+ "&state="	+ stateCode
								+ "&zip=" + ownerPortalUser.getPostalCode()
								+ "&email=" + ownerPortalUser.getEmailId()
								+ "&country=US"
								+ "&language=" + ownerPortalUser.getLanguageCode()
								+ "&phoneNumber=" + ownerPortalUser.getCellPhoneNumber()
								);
					
						
						
					}
				/*	jsonservicesresponseObj.accumulate(
							"url",
							INFINITIINTOUCHWITHWIFIHOTSPOTURL
							);*/
				}
				jsonServicesArrayObj.put(jsonservicesresponseObj);

			} else if (isBlocking.equalsIgnoreCase("true")) {
				//T&Cs not accepted
				JSONObject jsonservicesresponseObj = new JSONObject();

				if (telematicsVO.getNissanConnectwithWifiHotspot().equalsIgnoreCase("true")
						&& telematicsCodeInfo.getTcuTypeCode().equalsIgnoreCase("A-IVC") ) {

					LOG.info("inside get telematics for AT&T wifi for N6,N7,M6,M7,M8,M9 terms and condition not accepted"
							+ vin);
					jsonservicesresponseObj.accumulate("id",
							NISSANCONNECTWITHWIFIHOTSPOT);
					jsonservicesresponseObj.accumulate("supportsMessaging",
							false);
					jsonservicesresponseObj.accumulate("status", ELIGIBLE);
					jsonservicesresponseObj.accumulate(
							"url",
							NISSANCONNECTWITHWIFIHOTSPOTURL
							//x550910---NAR 4557
							+ "?"
							+ "&country=US"
							);	

				} else if (telematicsVO.getInfinitiIntouchwithWifiHotspot().equalsIgnoreCase("true")
						&& telematicsCodeInfo.getTcuTypeCode().equalsIgnoreCase("A-IVC") ) {

					LOG.info("inside get telematics for AT&T wifi I6,I7,I8,I9,M3,M4,M5 terms and condition not accepted"
							+ vin);
					jsonservicesresponseObj.accumulate("id",
							INFINITIINTOUCHWITHWIFIHOTSPOT);
					jsonservicesresponseObj.accumulate("supportsMessaging",
							false);
					jsonservicesresponseObj.accumulate("status", ELIGIBLE);
					jsonservicesresponseObj.accumulate("url",
							INFINITIINTOUCHWITHWIFIHOTSPOTURL
							+ "?"
							+ "&country=US"
							);
				}
				jsonServicesArrayObj.put(jsonservicesresponseObj);
			}
			/////////x116202 - OS-1742
			if(optionTypCode.equalsIgnoreCase("M6") || optionTypCode.equalsIgnoreCase("M7") || optionTypCode.equalsIgnoreCase("M8") || optionTypCode.contains("M9")|| optionTypCode.contains(E1)){
				/*if(make.equalsIgnoreCase(BRAND_NISSAN)){*/
					JSONObject jsonservicesresponseObj = new JSONObject();
					LOG.info("inside get telematics subcription product optionTypCode = M6 M7 M8 M9 ="+ vin);
					jsonservicesresponseObj.accumulate("id",NISSANCONNECTSERVICES);
					jsonservicesresponseObj.accumulate("status", status);
					jsonservicesresponseObj.accumulate("endDate", "");
					jsonservicesresponseObj.accumulate("supportsMessaging",true);
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				/*}
				if(make.equalsIgnoreCase(BRAND_INFINITI)){
					//to be included if M6,M7,M8,M9 stated as infiniti vehicles 
				}*/
			}
			if( optionTypCode.contains("M3") || optionTypCode.contains("M4") || optionTypCode.contains("M5")) {
				JSONObject jsonservicesresponseObj = new JSONObject();
				LOG.info("inside get telematics subcription product optionTypCode :  M3 M4 M5 = "+ vin);
				jsonservicesresponseObj.accumulate("id",INFINITIINTOUCHSERVICES);
				jsonservicesresponseObj.accumulate("status", status);
				jsonservicesresponseObj.accumulate("endDate", "");
				jsonservicesresponseObj.accumulate("supportsMessaging",true);
				jsonServicesArrayObj.put(jsonservicesresponseObj);
			}
			/////////
			LOG.info("inside get at&t wifi subcription product setting response for vin = "
					+ vin);
			if (jsonServicesArrayObj.length() > 0) {
				LOG.info("inside get at&t wifi Json array not null or empty ="
						+ vin);
				response = jsonlastObj.accumulate("services",
						jsonServicesArrayObj);
			} else {
				LOG.info("inside get at&t wifi Json array  null or empty ="
						+ vin);
				response = jsonlastObj;
			}

		} catch (JSONException e) {
			LOG.error("JSONException in  setGetAT&TwifiResponse for the vin"
					+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in  setGetAT&TwifiResponse for the vin" + vin,
					e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To set the telematicsSubscription Status
	 * @param status
	 * @return
	 */
	/*private String getTelematicsSubscriptionStatus(String status) {

		LOG.info("status is = " + status);
		if (Utility.isStringNotNullorNotEmpty(status)) {
			if (status.equalsIgnoreCase(NOT_ENROLLED)
					|| status.equalsIgnoreCase(SOLD)
					|| status.equalsIgnoreCase(ELIGIBLE)
					|| status.equalsIgnoreCase(NON_SUBSCRIBER)) {
				status = ELIGIBLE;
				LOG.info(" status is enrolled or eligible or nonsubscriber - "
						+ status);
			} else if (status.equalsIgnoreCase(ENROLLED)
					|| status.equalsIgnoreCase(ACTIVE)) {
				status = ACTIVE;
				LOG.info(" status is enrolled or active - " + status);
			} else if (status.toUpperCase().contains("EXPIRES ON")) {
				status = ACTIVE;
				LOG.info(" status is expires on  - " + status);
			} else if (status.toUpperCase().contains("EXPIRED")) {
				status = ENDED;
				LOG.info(" status is expired - " + status);
			} else if (status.equalsIgnoreCase("PLEASE TRY AGAIN")
					|| status.equalsIgnoreCase(SHELL)
					|| status.equalsIgnoreCase(SHELL2)) {
				status = PENDINGSTATUS;
				LOG.info(" status is please try again - " + status);
			} else if (status.equalsIgnoreCase(SVL_NON_RECOVERED)
					|| status.equalsIgnoreCase(NO_AGREEMENT)
					|| status.equalsIgnoreCase(TOTAL)) {
				status = ENDED;
				LOG.info(" status is please try again - " + status);
			} else if (status.equalsIgnoreCase(CANCEL)
					|| status.equalsIgnoreCase(NON_RENEWAL)) {
				status = CANCELLED;
				LOG.info(" status is please try again - " + status);
			} else {
				status = PENDINGSTATUS;
			}
		} else { // this is temp fix for empty status
			status = ELIGIBLE;
		}
		LOG.info("status after setting is = " + status);
		return status;
	}
	 */
	/**
	 * @author x497432
	 * @use To set the telematicsSubscription Status based on SXM status and
	 *      trial period
	 * @param status
	 * @param trial
	 *            period
	 * @return
	 */
	private String getTelematicsSubscriptionStatus(String status,
			int trialPeriod) {

		boolean trialStatus = false;
		if (trialPeriod > 0) {
			trialStatus = true;
		}

		LOG.info("status is = " + status);
		if (Utility.isStringNotNullorNotEmpty(status)) {
			if ( status.equalsIgnoreCase(SOLD)) {
				if(trialStatus) {
					status = ELIGIBLE;
				}else {
					status = ENDED;
				}
				LOG.info(" status is enrolled or eligible or nonsubscriber - "
						+ status);
			} else if (status.equalsIgnoreCase(ACTIVE) || status.equalsIgnoreCase(SVL_NON_RECOVERED)) {
				status = ACTIVE;
				LOG.info(" status is enrolled or active - " + status);
			} else if ( status.equalsIgnoreCase(DECLINED) || status.equalsIgnoreCase(NON_RENEWAL)) {
				status = ENDED;
				LOG.info(" status is expired - " + status);
			} else if (status.equalsIgnoreCase(SHELL)
					|| status.equalsIgnoreCase(SHELL2) || status.equalsIgnoreCase(SHELL_2)|| status.equalsIgnoreCase(NO_AGREEMENT) || status.equalsIgnoreCase(NO_AGREEMENT1)) {

				if(trialStatus) {
					status = ELIGIBLE;
				}else {
					status = ENDED;
				}
				LOG.info(" status is please try again - " + status);
			} else if (status.equalsIgnoreCase(TOTAL)|| status.equalsIgnoreCase(TOTALED) ) {
				status = UNDEFINED;
				LOG.info(" status is please try again - " + status);
			} else if (status.equalsIgnoreCase(CANCEL)) {
				status = CANCELLED;
				LOG.info(" status is please try again - " + status);
			} else {
				status = UNDEFINED;
			}
		} else { // this is temp fix for empty status
			status = UNDEFINED;
		}
		LOG.info("status after setting is = " + status);
		return status;
	}

	/**
	 * @author x796314
	 * @use To accumulate json response for get service contract endpoint
	 * @param subscription
	 * @param subscriptionSsoTokenVO
	 * @param make
	 * @param vin
	 * @return
	 */
	private JSONObject setGetServiceContractResponse(
			List<TelematicsSubscriptionVO> subscription, String make,
			String vin, Set<String> telematicsInfo) {
		JSONObject response = null;
		LOG.info("Inside get service contract response for vin=" + vin);
		try {

			JSONArray jsonServicesArrayObj = new JSONArray();
			JSONObject jsonlastObj = new JSONObject();
			int months = 0;
			String startDate = "";
			String endDate = "";
			TelematicsSubscriptionVO telematicsSubscriptionVO;
			LOG.info("Inside get service contract response before iteration for size="
					+ subscription.size());
			for (Iterator<TelematicsSubscriptionVO> iterator = subscription
					.iterator(); iterator.hasNext();) {
				telematicsSubscriptionVO = (TelematicsSubscriptionVO) iterator
						.next();

				List<OwnerPortalSubScriptionProduct> subScriptionProducts = vehicleLocal
						.getSubScriptionProduct(telematicsSubscriptionVO
								.getProductCode());
				LOG.info("Telematics code for the subscription product code("
						+ telematicsSubscriptionVO.getProductCode()
						+ ")"
						+ subScriptionProducts.get(0)
						.getVehicleTelematicsOptionCode());

				if (Utility.isObjectNotNullorNotEmpty(subScriptionProducts)
						&& !(subScriptionProducts.isEmpty())) {

					startDate = telematicsSubscriptionVO.getStartDate();
					endDate = telematicsSubscriptionVO.getEndDate();

					if (Utility.isStringNotNullorNotEmpty(startDate)
							&& Utility.isStringNotNullorNotEmpty(endDate)) {

						startDate = startDate.replace("T", " ");
						endDate = endDate.replace("T", " ");

						LOG.info("startDate after removing T=" + startDate);
						LOG.info("endDate after removing T=" + endDate);
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						Date beginningDate = null;
						Date endingDate = null;

						beginningDate = format.parse(startDate);
						endingDate = format.parse(endDate);

						months = Utility.monthsBetweenDates(beginningDate,
								endingDate);

						LOG.info("beginningDate=" + beginningDate
								+ "endingDate=" + endingDate
								+ "Difference in Months=" + months);
					}

					if (telematicsInfo.contains(S2)) {
						if (subScriptionProducts.get(0)
								.getVehicleTelematicsOptionCode().equals(S2)) {
							JSONObject jsonservicesresponseObj = new JSONObject();
							if (make.equals(BRAND_NISSAN)) {

								jsonservicesresponseObj = getServiceContractResponse(
										NISSANCONNECTMOBILEAPPS, months,
										telematicsSubscriptionVO);

							} else if (make.equals(BRAND_INFINITI)) {

								jsonservicesresponseObj = getServiceContractResponse(
										INFINFITIINTOUCHAPPS, months,
										telematicsSubscriptionVO);

							}
							jsonServicesArrayObj.put(jsonservicesresponseObj);
						}
					} else if (telematicsInfo.contains(NS)) {

						if (subScriptionProducts.get(0)

								.getVehicleTelematicsOptionCode().equals(NS)) {
							JSONObject jsonservicesresponseObj = new JSONObject();
							jsonservicesresponseObj = getServiceContractResponse(
									NISSANCONNECTSERVICES, months,
									telematicsSubscriptionVO);
							jsonServicesArrayObj.put(jsonservicesresponseObj);
						}
					} else if (telematicsInfo.contains(IC)) {

						if (subScriptionProducts.get(0)
								.getVehicleTelematicsOptionCode().equals(IC)) {
							JSONObject jsonservicesresponseObj = new JSONObject();
							jsonservicesresponseObj = getServiceContractResponse(
									INFINFITICONNECTION, months,
									telematicsSubscriptionVO);
							jsonServicesArrayObj.put(jsonservicesresponseObj);
						}

					}

				}
				LOG.info("inside get service contract subcription product Setting array json ="
						+ vin);

			}

			LOG.info("inside get service contract subcription product setting response for vin ="
					+ vin);

			if (jsonServicesArrayObj.length() > 0) {

				response = jsonlastObj.accumulate("contracts",
						jsonServicesArrayObj);
			} else {

				response = jsonlastObj;
			}

		} catch (ParseException e) {
			LOG.error(
					"ParseException in set Get service contractResponse for the vin"
							+ vin, e);
		} catch (JSONException e) {
			LOG.error(
					"JSONException in set Get service contractResponse for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error(
					"Exception in set Getservice contract Response for the vin"
							+ vin, e);
		}

		return response;
	}

	/**
	 * @author x796314
	 * @use to accumulate the json response for service contract endpoint in
	 *      case subscription is not available
	 * @param telematicsInfo
	 * @param make
	 * @param vin
	 * @return
	 */

	private JSONObject setSerivceContractResponseWithoutSubscription(
			Set<String> telematicsInfo, String make, String vin) {
		JSONObject response = null;
		LOG.info("Service contract response without subscription for vin="
				+ vin);
		try {
			JSONArray jsonServicesArrayObj = new JSONArray();
			JSONObject jsonlastObj = new JSONObject();
			String telematics = "";

			for (Iterator<String> iterator = telematicsInfo.iterator(); iterator
					.hasNext();) {

				telematics = (String) iterator.next();

				if (telematics.contains(CW)) {
					JSONObject jsonservicesresponseObj = new JSONObject();
					jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
							NISSANCONNECTEV, NOT_APPLICABLE);// status pending
					// changed to
					// not
					// applicable
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				} else if (telematics.equals(S2)) {
					JSONObject jsonservicesresponseObj = new JSONObject();
					if (make.equals(BRAND_NISSAN)) {

						jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
								NISSANCONNECTMOBILEAPPS, PENDINGSTATUS);
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					} else if (make.equals(BRAND_INFINITI)) {

						jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
								INFINFITIINTOUCHAPPS, PENDINGSTATUS);
						jsonServicesArrayObj.put(jsonservicesresponseObj);
					}
				} else if (telematics.equals(NS)) {
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
							NISSANCONNECTSERVICES, PENDINGSTATUS);
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				} else if (telematics.equals(IC)) {
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
							INFINFITICONNECTION, PENDINGSTATUS);
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				} else if (telematicsInfo.contains(NC)) {
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
							NISSANCONNECTNAVIGATION, NOT_APPLICABLE);
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				} else if (telematicsInfo.contains(EV)) {
					JSONObject jsonservicesresponseObj = new JSONObject();

					jsonservicesresponseObj = getServiceContractResponseWithoutSubscription(
							NISSANCONNECTEV, NOT_APPLICABLE);
					jsonServicesArrayObj.put(jsonservicesresponseObj);
				}

			}
			if (jsonServicesArrayObj.length() > 0) {

				response = jsonlastObj.accumulate("contracts",
						jsonServicesArrayObj);
			} else {

				response = jsonlastObj;
			}

		} catch (JSONException e) {
			LOG.error("JSONException in setServiceContractResponse for the vin"
					+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in setServiceContractResponse for the vin"
					+ vin, e);
		}
		return response;
	}

	String createToken(OwnerPortalUser ownerPortalUser,
			OwnerPortalVehicle ownerPortalVehicle) {
		String success = null;
		try {
			success = vehicleLocal.createToken(ownerPortalUser,
					ownerPortalVehicle);
			LOG.info("inside token0 complete" + success);
		} catch (Exception e) {
			LOG.error("Exception in createtoken WS for the vin" + e);
		}
		return success;
	}

	/**
	 * @author x796314
	 * @use to accumulate the json response commom for all telematics info for
	 *      service contract endpoint in case subscription is available
	 * @param name
	 * @param months
	 * @param telematicsSubscriptionVO
	 * @return
	 */
	private JSONObject getServiceContractResponse(String name, int months,
			TelematicsSubscriptionVO telematicsSubscriptionVO) {

		JSONObject response = null;
		String status = "";
		int trialPeriod = 0;
		try {
			JSONObject jsonservicesresponseObj = new JSONObject();
			JSONObject jsonConditionsOnj = new JSONObject();
			JSONArray jsonArrayObj = new JSONArray();

			//x116202 - taking trial period as 0 to match with method parameter should be changed in future ()
			status = getTelematicsSubscriptionStatus(telematicsSubscriptionVO
					.getStatus(),trialPeriod);
			jsonservicesresponseObj.accumulate("name", name);
			jsonservicesresponseObj.accumulate("durationMonths", months);

			jsonservicesresponseObj.accumulate("startDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatString(telematicsSubscriptionVO
									.getStartDate()))));
			jsonservicesresponseObj.accumulate("endDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatString(telematicsSubscriptionVO
									.getEndDate()))));
			jsonservicesresponseObj.accumulate("status", status);
			jsonConditionsOnj.accumulate("startDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatString(telematicsSubscriptionVO
									.getStartDate()))));
			jsonConditionsOnj.accumulate("endDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatString(telematicsSubscriptionVO
									.getEndDate()))));
			jsonConditionsOnj.accumulate("unlimitedMileage", true);
			jsonArrayObj.put(jsonConditionsOnj);
			jsonservicesresponseObj.accumulate("conditions", jsonArrayObj);
			jsonservicesresponseObj.accumulate("extendable", true);
			response = jsonservicesresponseObj;

		} catch (Exception e) {
			LOG.error(
					"Exception in getServiceContractResponse for TelematicsInfo"
							+ name, e);
		}

		return response;
	}

	/**
	 * @author x796314
	 * @use to accumulate the json response commom for all types of telematics
	 *      info for service contract endpoint in case subscription is not
	 *      available
	 * @param name
	 * @return
	 */
	private JSONObject getServiceContractResponseWithoutSubscription(
			String name, String status) {

		JSONObject response = null;
		try {
			JSONObject jsonservicesresponseObj = new JSONObject();
			JSONObject jsonConditionsOnj = new JSONObject();
			JSONArray jsonArrayObj = new JSONArray();

			jsonservicesresponseObj.accumulate("name", name);
			jsonservicesresponseObj.accumulate("durationMonths", 0);

			jsonservicesresponseObj.accumulate("endDate", Utility
					.convertToUTCFormat(new Timestamp(System
							.currentTimeMillis())));
			jsonservicesresponseObj.accumulate("status", status);

			jsonConditionsOnj.accumulate("endDate", Utility
					.convertToUTCFormat(new Timestamp(System
							.currentTimeMillis())));
			jsonConditionsOnj.accumulate("unlimitedMileage", true);
			jsonArrayObj.put(jsonConditionsOnj);

			jsonservicesresponseObj.accumulate("conditions", jsonArrayObj);
			jsonservicesresponseObj.accumulate("extendable", true);
			response = jsonservicesresponseObj;

		} catch (Exception e) {
			LOG.error(
					"Exception in getServiceContractResponse for TelematicsInfo"
							+ name, e);
		}

		return response;
	}

	/**
	 * @author x293386
	 * @use To get contract service information
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getASContract")
	public Response getASContractService(String requestJson)
			throws JSONException {
		LOG.info("Contract Service request is" + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONArray jsonServicesArrayObj = new JSONArray();
		JSONObject jsonlastObj = new JSONObject();
		JSONObject jsonservicesresponseObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean success = false;
		boolean isValidReq = false;
		boolean isValidVin = true;
		boolean isValidBrand = false;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		String vin = "";
		String email = "";
		String make = "";
		String personHashId = "";
		List<ASContractVO> asContract;
		ASContractVO asContractVO;

		try {

			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				ASServiceContractWrapper asServiceContractWrapper = mapper
						.readValue(requestJson, ASServiceContractWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(asServiceContractWrapper)) {
					if (Utility
							.isObjectNotNullorNotEmpty(asServiceContractWrapper
									.getASServiceContractVO())) {
						if (Utility
								.isObjectNotNullorNotEmpty(asServiceContractWrapper
										.getASServiceContractVO().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(asServiceContractWrapper
										.getASServiceContractVO()
										.getVehicle())) {
							vin = asServiceContractWrapper
									.getASServiceContractVO().getVehicle()
									.getVin();
							email = asServiceContractWrapper
									.getASServiceContractVO().getPerson()
									.getEmail();
							personHashId = asServiceContractWrapper
									.getASServiceContractVO().getPerson()
									.getPersonHashId();
							make = asServiceContractWrapper
									.getASServiceContractVO().getVehicle()
									.getMake();
							asContract = asServiceContractWrapper
									.getASServiceContractVO().getAsContract();

							isValidBrand = isBrandNull(make, jsonObj,
									jsonFinalObj);
							if (make.equalsIgnoreCase(BRAND_INFINITI)
									|| make.equalsIgnoreCase(BRAND_NISSAN)) {
								isValidBrand = true;
							}
							if (isValidBrand) {
								isValidVin = isVinNull(vin, jsonObj,
										jsonFinalObj);
								if (isValidVin) {
									isValidVin = isVinValid(vin, jsonObj,
											jsonFinalObj);
								}
								if (isValidVin) {
									isValidEmail = isEmailNull(email, jsonObj,
											jsonFinalObj);
								}
								if (isValidVin) {
									isValidPersonHashId = isPersonhashidNull(
											personHashId, jsonObj, jsonFinalObj);
								}
							} else {
								LOG.info("Brand is neither Nissan nor Infiniti");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VEHICLE_INVALID_BRAND_CODE,
										VEHICLE_INVALID_BRAND_MESSAGE,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								isValidBrand = false;
							}
							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId) {
								if (Utility
										.isObjectNotNullorNotEmpty(asContract)
										&& !(asContract.isEmpty())) {
									LOG.info("Before calling After Sales get service contract response for vin"
											+ vin);
									for (Iterator<ASContractVO> iterator = asContract
											.iterator(); iterator.hasNext();) {
										asContractVO = (ASContractVO) iterator
												.next();
										List<ASServiceContractCategoryLookup> aSServiceContractCategoryLookup = vehicleLocal
												.getASServiceContractCategoryDetails(asContractVO
														.getProduct());
										String name = "";
										String description = "";
										if (Utility
												.isObjectNotNullorNotEmpty(aSServiceContractCategoryLookup)
												&& !(aSServiceContractCategoryLookup
														.isEmpty())) {
											name = aSServiceContractCategoryLookup
													.get(0)
													.getServiceContractCategoryDescription();
											description = aSServiceContractCategoryLookup
													.get(0)
													.getServiceContractCategoryName();
										} else {
											name = INVALID_AS_SERVICE_CONTRACT_PRODUCT_MESSAGE;
											description = INVALID_AS_SERVICE_CONTRACT_PRODUCT_MESSAGE;
										}
										jsonservicesresponseObj = getASContractResponse(
												asContractVO, name, description);
										jsonServicesArrayObj
										.put(jsonservicesresponseObj);
										LOG.info("inside after sales get service contract setting array json ="
												+ vin);
									}
									if (jsonServicesArrayObj.length() > 0) {
										jsonFinalObj = jsonlastObj.accumulate(
												"contracts",
												jsonServicesArrayObj);
									} else {
										jsonFinalObj = jsonlastObj;
									}
									if (jsonFinalObj.length() > 0) {
										success = true;
									}
								} else {
									LOG.info("No contracts");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj, INVALID_VIN,
											INVALID_CONTRACT_MESSAGE,
											INVALID_CONTRACT_DESCRIPTION);
									success = true;
								}

							} else {
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										REQUEST_NULL_CODE,
										REQUEST_NULL_MESSAGE,
										REQUEST_NULL_DESCRIPTION);
								LOG.debug("Request is null and response is"
										+ jsonFinalObj.toString());
								success = false;
								isValidReq = false;
							}
						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
									REQUEST_NULL_DESCRIPTION);
							LOG.debug("Person and Vehicle in the request is null"
									+ jsonFinalObj.toString());
							success = false;
							isValidReq = false;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
								REQUEST_NULL_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());
						success = false;
						isValidReq = false;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
							REQUEST_NULL_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					success = false;
					isValidReq = false;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
						REQUEST_NULL_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				success = false;
				isValidReq = false;
			}
		} catch (JsonParseException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "JsonParseException  service contract  ", e);
			success = false;
			isValidReq = false;
		} catch (JsonMappingException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "JsonMappingException  service contract  ", e);
			success = false;
			isValidReq = false;
		} catch (IOException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "IOException  service contract  ", e);
			success = false;
			isValidReq = false;
		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "General Exception  service contract  ", e);
			success = false;
			isValidReq = false;
		}
		if (success) {
			LOG.info("service contract  JSON success response for the vin="
					+ vin + " with email=" + email + jsonFinalObj.toString());
			return Response.ok().status(200).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidVin || isValidBrand || !isValidReq || !isValidEmail
				|| !isValidPersonHashId) {
			LOG.info("service contract  JSON failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("service contract  JSON unexpected failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	/**
	 * @author x293386
	 * @param description
	 * @param name
	 * @use to accumulate the json response for after sales service contract
	 *      endpoint in case contract is available
	 * @param name
	 * @param months
	 * @return
	 */
	private JSONObject getASContractResponse(ASContractVO asContractVO,
			String name, String description) {
		JSONObject response = null;
		String status = "";
		String endDate = "";
		try {
			JSONObject jsonservicesresponseObj = new JSONObject();
			JSONObject jsonConditionsOnj = new JSONObject();
			JSONArray jsonArrayObj = new JSONArray();
			jsonservicesresponseObj.accumulate("name", name);
			jsonservicesresponseObj.accumulate("id", asContractVO
					.getPolicyNumber().substring(0, 11));
			jsonservicesresponseObj.accumulate("description", description);
			jsonservicesresponseObj.accumulate("durationMonths",
					asContractVO.getPlanTerm());
			jsonservicesresponseObj.accumulate("startDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatStringWithSeconds(asContractVO
									.getEffectiveDate()))));
			endDate = getEndDate(asContractVO);
			jsonservicesresponseObj.accumulate("endDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatStringWithSeconds(endDate))));
			status = getStatus(asContractVO);
			jsonservicesresponseObj.accumulate("status", status);
			jsonConditionsOnj.accumulate("startDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatStringWithSeconds(asContractVO
									.getEffectiveDate()))));
			jsonConditionsOnj.accumulate("endDate", Utility
					.convertToUTCFormat((Utility
							.getDateFormatStringWithSeconds(endDate))));
			jsonConditionsOnj.accumulate("unlimitedMileage", false);
			jsonConditionsOnj.accumulate("maximumMileage",
					asContractVO.getExpireMileage());
			jsonConditionsOnj.accumulate("mileageUnit", "miles");
			jsonArrayObj.put(jsonConditionsOnj);
			jsonservicesresponseObj.accumulate("conditions", jsonArrayObj);
			jsonservicesresponseObj.accumulate("extendable", true);
			response = jsonservicesresponseObj;
			LOG.info("AS Service contract response" + response);
		} catch (Exception e) {
			LOG.error("Exception in getASContractResponse for Contracts", e);
		}
		return response;
	}

	/**
	 * @author x293386 To get endDate based on ExpireDate , CancelEffectiveDate
	 * @param asContractVO
	 * @return
	 * @throws ParseException
	 */
	public String getEndDate(ASContractVO asContractVO) throws ParseException {
		String endDate = "";
		Date cancelEffectiveDate = null;
		Date expireDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		if (asContractVO.getCancelEffectiveDate() == null
				|| ("").equals(asContractVO.getCancelEffectiveDate().trim())) {
			cancelEffectiveDate = null;
		} else {
			cancelEffectiveDate = format.parse(asContractVO
					.getCancelEffectiveDate());
		}

		if (asContractVO.getExpireDate() == null
				|| ("").equals(asContractVO.getExpireDate().trim())) {
			expireDate = null;
		} else {
			expireDate = format.parse(asContractVO.getExpireDate());
		}

		if (cancelEffectiveDate != null && expireDate != null) {
			if (expireDate.compareTo(cancelEffectiveDate) > 0) {
				endDate = asContractVO.getExpireDate();
			} else if (cancelEffectiveDate.compareTo(expireDate) > 0) {
				endDate = asContractVO.getCancelEffectiveDate();
			} else if (cancelEffectiveDate.compareTo(expireDate) == 0) {
				endDate = asContractVO.getCancelEffectiveDate();
			}
		} else if (cancelEffectiveDate != null && expireDate == null) {
			endDate = asContractVO.getCancelEffectiveDate();
		} else if (cancelEffectiveDate == null && expireDate != null) {
			endDate = asContractVO.getExpireDate();
		}
		return endDate;
	}

	/**
	 * @author x293386 To get contract status based on EffectiveDate, ExpireDate
	 *         , CancelEffectiveDate
	 * @param asContractVO
	 * @return
	 * @throws ParseException
	 */
	public String getStatus(ASContractVO asContractVO) throws ParseException {
		String status = "";
		Date cancelEffectiveDate = null;
		Date effectiveDate = null;
		Date expireDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		if (asContractVO.getExpireDate() == null
				|| ("").equals(asContractVO.getExpireDate().trim())) {
			expireDate = null;
		} else {
			expireDate = format.parse(asContractVO.getExpireDate());
		}
		if (asContractVO.getEffectiveDate() == null
				|| ("").equals(asContractVO.getEffectiveDate().trim())) {
			effectiveDate = null;
		} else {
			effectiveDate = format.parse(asContractVO.getEffectiveDate());
		}
		if (asContractVO.getCancelEffectiveDate() == null
				|| ("").equals(asContractVO.getCancelEffectiveDate().trim())) {
			cancelEffectiveDate = null;
		} else {
			cancelEffectiveDate = format.parse(asContractVO
					.getCancelEffectiveDate());
		}

		if (cancelEffectiveDate != null) {
			if (getCurrentTimestamp().compareTo(cancelEffectiveDate) < 0) {
				if ((getCurrentTimestamp().compareTo(effectiveDate) > 0)
						&& (getCurrentTimestamp().compareTo(expireDate) < 0)) {
					status = "active";
				} else if (getCurrentTimestamp().compareTo(effectiveDate) < 0) {
					status = "pending";
				}
			} else {
				status = "cancelled";
			}
		} else {
			if ((getCurrentTimestamp().compareTo(effectiveDate) > 0)
					&& (getCurrentTimestamp().compareTo(expireDate) < 0)) {
				status = "active";
			} else if (getCurrentTimestamp().compareTo(effectiveDate) < 0) {
				status = "pending";
			} else if (getCurrentTimestamp().compareTo(expireDate) > 0) {
				status = "ended";
			}
		}
		return status;
	}

	/**
	 * @author x293386 To get currentTimestamp
	 * @param
	 * @return
	 */
	private static Timestamp getCurrentTimestamp() {
		Date date = new Date();
		Timestamp currentDateTime = new Timestamp(date.getTime());
		return currentDateTime;
	}

	/**
	 * @author x293386 To get contract status based on EffectiveDate, ExpireDate
	 *         , CancelEffectiveDate
	 * @param asContractVO
	 * @return
	 * @throws ParseException
	 */
	public String getStatus(ApplicableWarrantyDataVO applicableWarrantyDataVO)
			throws ParseException {
		String status = "";

		Date expireDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		if (applicableWarrantyDataVO.getWarrantyExpirationDate() == null
				|| ("").equals(applicableWarrantyDataVO
						.getWarrantyExpirationDate().trim())) {
			expireDate = null;
		} else {
			expireDate = format.parse(applicableWarrantyDataVO
					.getWarrantyExpirationDate());
		}
         
		if(null !=expireDate){
		if ((getCurrentTimestamp().compareTo(expireDate) < 0)) {
			status = "active";
		} else if (getCurrentTimestamp().compareTo(expireDate) > 0) {
			status = "ended";
		}
		}
		return status;
	}

	public boolean getUnlimitedMileage(
			ApplicableWarrantyDataVO applicableWarrantyDataVO) {

		boolean unlimitedMileage;

		if (applicableWarrantyDataVO.getWarrantyExpirationMiles() == 9999999) {
			unlimitedMileage = true;
		} else {
			unlimitedMileage = false;
		}

		return unlimitedMileage;
	}

	private JSONObject getServiceWarrantyResponse(
			ApplicableWarrantyDataVO applicableWarrantyDataVO) {
		JSONObject response = null;
		String status = "";
		boolean unlimitedMileage;
		try {
			JSONObject jsonservicesresponseObj = new JSONObject();
			JSONObject jsonConditionsOnj = new JSONObject();
			JSONArray jsonArrayObj = new JSONArray();
			jsonservicesresponseObj.accumulate("name",
					applicableWarrantyDataVO.getWarrantyDescription());
			jsonservicesresponseObj.accumulate("durationMonths", 72);
			jsonservicesresponseObj.accumulate("endDate",
					applicableWarrantyDataVO.getWarrantyExpirationDate());
			status = getStatus(applicableWarrantyDataVO);
			jsonservicesresponseObj.accumulate("status", status);

			jsonConditionsOnj.accumulate("endDate",
					applicableWarrantyDataVO.getWarrantyExpirationDate());
			unlimitedMileage = getUnlimitedMileage(applicableWarrantyDataVO);
			if (unlimitedMileage == true) {
				jsonConditionsOnj.accumulate("unlimitedMileage",
						unlimitedMileage);
			} else {
				jsonConditionsOnj.accumulate("unlimitedMileage",
						unlimitedMileage);
				jsonConditionsOnj.accumulate("maximumMileage",
						applicableWarrantyDataVO.getWarrantyExpirationMiles());
				jsonConditionsOnj.accumulate("mileageUnit", "miles");
			}
			jsonArrayObj.put(jsonConditionsOnj);
			jsonservicesresponseObj.accumulate("conditions", jsonArrayObj);
			jsonservicesresponseObj.accumulate("extendable", true);
			response = jsonservicesresponseObj;
			LOG.info("Service Warranty response" + response);
		} catch (Exception e) {
			LOG.error("Exception in getServiceWarrantyResponse for Contracts",
					e);
		}
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getServiceWarranty")
	public Response getServiceWarrantyService(String requestJson)
			throws JSONException {
		LOG.info("Service Warranty request is" + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONArray jsonServicesArrayObj = new JSONArray();
		JSONObject jsonlastObj = new JSONObject();
		JSONObject jsonservicesresponseObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean success = false;
		boolean isValidReq=false;
		boolean isValidVin = true;
		String vin = "";
		String email = "";

		List<ApplicableWarrantyDataVO> applicableWarrantyData;
		ApplicableWarrantyDataVO applicableWarrantyDataVO;

		try {

			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				ServiceWarrantyWrapper serviceWarrantyWrapper = mapper
						.readValue(requestJson, ServiceWarrantyWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(serviceWarrantyWrapper)) {
					if (Utility
							.isObjectNotNullorNotEmpty(serviceWarrantyWrapper
									.getServiceWarrantyVO())) {

						vin = serviceWarrantyWrapper.getServiceWarrantyVO()
								.getVin();

						applicableWarrantyData = serviceWarrantyWrapper
								.getServiceWarrantyVO()
								.getApplicableWarrantyData();

						isValidVin = isVinNull(vin, jsonObj, jsonFinalObj);
						if (isValidVin) {
							isValidVin = isVinValid(vin, jsonObj, jsonFinalObj);
						}

						if (isValidVin) {
							if (Utility
									.isObjectNotNullorNotEmpty(applicableWarrantyData)
									&& !(applicableWarrantyData.isEmpty())) {
								LOG.info("Before calling After Sales get service warranty response for vin"
										+ vin);
								for (Iterator<ApplicableWarrantyDataVO> iterator = applicableWarrantyData
										.iterator(); iterator.hasNext();) {
									applicableWarrantyDataVO = (ApplicableWarrantyDataVO) iterator
											.next();

									jsonservicesresponseObj = getServiceWarrantyResponse(applicableWarrantyDataVO);
									jsonServicesArrayObj
									.put(jsonservicesresponseObj);
									LOG.info("inside after sales get service warranty setting array json ="
											+ vin);
								}
								if (jsonServicesArrayObj.length() > 0) {
									jsonFinalObj = jsonlastObj.accumulate(
											"contracts", jsonServicesArrayObj);
								} else {
									jsonFinalObj = jsonlastObj;
								}
								if (jsonFinalObj.length() > 0) {
									success = true;
								}
							} else {
								LOG.info("No contracts");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										INVALID_VIN, INVALID_CONTRACT_MESSAGE,
										INVALID_CONTRACT_DESCRIPTION);
								success = true;
							}

						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
									REQUEST_NULL_DESCRIPTION);
							LOG.debug("Request is null and response is"
									+ jsonFinalObj.toString());
							success = false;
							 isValidReq=false;
						}

					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
								REQUEST_NULL_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());
						success = false;
						isValidReq=false;
					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
							REQUEST_NULL_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					success = false;
					isValidReq=false;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						REQUEST_NULL_CODE, REQUEST_NULL_MESSAGE,
						REQUEST_NULL_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				success = false;
				isValidReq=false;
			}
		} catch (JsonParseException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "JsonParseException  service warranty  ", e);
			success = false;
			isValidReq=false;
		} catch (JsonMappingException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "JsonMappingException  service warranty  ", e);
			success = false;
			isValidReq=false;
		} catch (IOException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "IOException  service warranty  ", e);
			success = false;
			isValidReq=false;
		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to fuse for the vin" + vin + "is="
					+ jsonFinalObj.toString()
					+ "General Exception  service warranty ", e);
			success = false;
			isValidReq=false;
		}
		if (success) {
			LOG.info("service warranty  JSON success response for the vin="
					+ vin + " with email=" + email + jsonFinalObj.toString());
			return Response.ok().status(200).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidVin || !isValidReq) {
			LOG.info("service warranty  JSON failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("service warranty  JSON unexpected failed response for the vin="
					+ vin + " =" + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	/**
	 * @author x055765 To get history of the services
	 * @param
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/serviceHistory")
	public Response serviceHistory(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		LOG.info("Service History JSON request" + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isValidMake = true;
		String vin = "";
		String email = "";
		String personHashId = "";
		String userProfileId = "";
		String vinMappedToSameUser = "";
		String result = "";
		String modelCode = "";
		OwnerPortalUser validUser;
		boolean generalError = false;
		try {

			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				ServiceHistoryWrapper serviceHistoryWrapper = mapper.readValue(
						requestJson, ServiceHistoryWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(serviceHistoryWrapper)) {

					if (Utility.isObjectNotNullorNotEmpty(serviceHistoryWrapper
							.getServiceHistory())) {
						if (Utility
								.isObjectNotNullorNotEmpty(serviceHistoryWrapper
										.getServiceHistory().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(serviceHistoryWrapper
										.getServiceHistory()
										.getVehicle())
								&& Utility
								.isObjectNotNullorNotEmpty(serviceHistoryWrapper
										.getServiceHistory()
										.getService())
								&& Utility
								.isObjectNotNullorNotEmpty(serviceHistoryWrapper
										.getServiceHistory()
										.getService().getHistory())) {

							// service history for the vin is not
							// available-Error handling
							int historySize = serviceHistoryWrapper
									.getServiceHistory().getService()
									.getHistory().size();
							LOG.info("historySize" + historySize);

							if (historySize > 0) {

								double repairOrderSize = serviceHistoryWrapper
										.getServiceHistory().getService()
										.getHistory().get(0)
										.getReportOrderDetails().size();
								LOG.info("repairOrderSize" + repairOrderSize);
								LOG.info("size of history"
										+ serviceHistoryWrapper
										.getServiceHistory()
										.getService().getHistory()
										.size());

								if (repairOrderSize > 0) {

									isValidBrand = isBrandNull(brand, jsonObj,
											jsonFinalObj);
									vin = serviceHistoryWrapper
											.getServiceHistory().getVehicle()
											.getVin();
									email = serviceHistoryWrapper
											.getServiceHistory().getPerson()
											.getEmail();
									personHashId = serviceHistoryWrapper
											.getServiceHistory().getPerson()
											.getPersonHashId();
									modelCode = serviceHistoryWrapper
											.getServiceHistory().getVehicle()
											.getModelCode();
									LOG.info("modelCode:" + modelCode);

									if (isValidBrand) {
										if (brand
												.equalsIgnoreCase(BRAND_INFINITI)
												|| brand.equalsIgnoreCase(BRAND_NISSAN)) {
											isValidBrand = true;
											isValidVin = isVinNull(vin,
													jsonObj, jsonFinalObj);

											if (isValidVin) {
												isValidVin = isVinValid(vin,
														jsonObj, jsonFinalObj);
											}
											if (isValidVin) {
												isValidEmail = isEmailNull(
														email, jsonObj,
														jsonFinalObj);
											}
											if (isValidVin) {
												isValidPersonHashId = isPersonhashidNull(
														personHashId, jsonObj,
														jsonFinalObj);
											}

										} else {
											LOG.info("Brand is neither Nissan nor Infiniti");
											Util.setFaultDataToJSON(jsonObj,
													jsonFinalObj,
													VALIDATION_FAILED_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_BRAND_DESCRIPTION);
											isValidBrand = false;
										}
									}
									if (isValidBrand && isValidVin
											&& isValidEmail
											&& isValidPersonHashId) {

										if (brand
												.equalsIgnoreCase(BRAND_NISSAN)) {
											serviceHistoryWrapper
											.getServiceHistory()
											.setBrand(NISSAN);
										} else if (brand
												.equalsIgnoreCase(BRAND_INFINITI)) {
											serviceHistoryWrapper
											.getServiceHistory()
											.setBrand(INFINITI);
										}

										// x566325 - Brand Segregation - send
										// brand also

										/*
										 * validUser = userLocal.validateEmail(
										 * email, personHashId);
										 */

										validUser = userLocal.validateEmail(
												email, personHashId, brand);

										if (!Utility
												.isObjectNotNullorNotEmpty(validUser)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
											isValidEmail = false;
										} else if (Utility
												.isObjectNotNullorNotEmpty(validUser)) {

											userProfileId = validUser
													.getUserProfileId();
											vinMappedToSameUser = vehicleLocal
													.validateVehicleOwner(
															userProfileId, vin);
											LOG.info("Status of first vehicle association is "
													+ vinMappedToSameUser
													+ "for the vin =" + vin);

											LOG.info("Status of second vehicle association is "
													+ vinMappedToSameUser
													+ "for the vin =" + vin);
											if (("sameUser")
													.equalsIgnoreCase(vinMappedToSameUser)) {
												LOG.info("vin mapped to the same user in service history service");
												LOG.info("Status of vehicle association is inside same user"

														+ "for the vin =" + vin);

												// set jsonObj for success
												// response

												if (Utility
														.isObjectNotNullorNotEmpty(validUser)
														&& Utility
														.isObjectNotNullorNotEmpty(vinMappedToSameUser)) {
													LOG.info("the user and vin are valid");
													jsonObj = setServiceHistoryResponse(
															serviceHistoryWrapper,
															jsonObj);
													result = SUCCESS;
												} else {

													Util.setFaultDataToJSON(
															jsonObj,
															jsonFinalObj,
															GENERAL_ERROR_FAULT_CODE,
															GENERAL_ERROR_MESSAGE,
															GENERAL_ERROR_DESCRIPTION);
													LOG.debug("Request is null and response is"
															+ jsonFinalObj
															.toString());
													generalError = true;
												}

											} else if (("otherUser")
													.equalsIgnoreCase(vinMappedToSameUser)) {
												LOG.info("Status of vehicle association is inside other user"

														+ "for the vin =" + vin);
												jsonObj.accumulate(CODE,
														VEHICLE_MISMATCH_CODE);
												jsonObj.accumulate(MESSAGE,
														VEHICLE_MISMATCH_MESSAGE);
												jsonObj.accumulate(DESCRIPTION,
														VEHICLE_MISMATCH_DESCRIPTION);
												jsonFinalObj.accumulate(
														"fault", jsonObj);
												result = FAILURE;
											} else if (("notAvailable")
													.equalsIgnoreCase(vinMappedToSameUser)) {
												jsonObj.accumulate(CODE,
														VEHICLE_UNAVAILABLE_VIN_CODE);
												jsonObj.accumulate(MESSAGE,
														VEHICLE_NOTAVAILABLE_VINANDUSER_MESSAGE);
												jsonObj.accumulate(DESCRIPTION,
														VEHICLE_NOTAVAILABLE_VINANDUSER_DESCRIPTION);
												jsonFinalObj.accumulate(
														"fault", jsonObj);
												result = FAILURE;
											}

										}

									}

								} else {
									Util.setFaultDataToJSON(
											jsonObj,
											jsonFinalObj,
											SERVICE_HISTORY_UNAVAILABLE_CODE,
											SERVICE_HISTORY_UNAVAILABLE_MESSAGE,
											SERVICE_HISTORY_NOTAVAILABLE_DESCRIPTION);
									LOG.debug("Request is null and response is"
											+ jsonFinalObj.toString());
									return Response.ok().status(400)
											.entity(jsonFinalObj.toString())
											.build();
								}

							} else {
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										SERVICE_HISTORY_UNAVAILABLE_CODE,
										SERVICE_HISTORY_UNAVAILABLE_MESSAGE,
										SERVICE_HISTORY_NOTAVAILABLE_DESCRIPTION);
								LOG.debug("Request is null and response is"
										+ jsonFinalObj.toString());
								return Response.ok().status(400)
										.entity(jsonFinalObj.toString())
										.build();
							}
						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							LOG.debug("Request is null and response is"
									+ jsonFinalObj.toString());
							generalError = true;
						}

					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null and response is"
								+ jsonFinalObj.toString());
						generalError = true;
					}

				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null and response is"
							+ jsonFinalObj.toString());
					generalError = true;
				}

			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null and response is"
						+ jsonFinalObj.toString());
				generalError = true;
			}
		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "Json exception  during service history : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "JsonParseException during service history : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "JsonMappingException  during service history : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + "with email ="
					+ email + "is" + jsonFinalObj.toString()
					+ "General exception  during  service history: ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {
			LOG.info("Service History JSON success response for the vin = "
					+ vin + jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();
		} else if (generalError || !isValidMake || !isValidPersonHashId) {

			LOG.info("Response to Fuse on service history for the vin =" + vin
					+ "with email =" + email + "is" + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else if (!isValidVin || !isValidBrand || !isValidEmail
				|| result.equalsIgnoreCase(FAILURE)) {
			LOG.info("Response to Fuse on service history for the vin =" + vin
					+ "with email =" + email + "is" + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Service History JSON unexpected failed response for the vin ="
					+ vin + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	/**
	 * @author X055765 To accumulate the json response for ServiceHistory
	 * @param serviceHistoryWrapper
	 * @param jsonObj
	 * @return
	 */

	private boolean validateUser(String vin, String email) {
		LOG.info(" vin "+vin +" email "+email);
		List<OwnerPortalUserVehicle>  userVehicles = vehicleLocal.getOwnerPortalUserVehicles(vin);
		if(userVehicles != null){
		OwnerPortalUser user = vehicleLocal.getOwnerPortalUser(userVehicles.get(0).getOwnerPortalUserVehiclePK().getUserProfileId());
		if (user != null) {
			LOG.info(" email "+email+" -- "+user.getEmailId());

			if (email.equalsIgnoreCase(user.getEmailId())) {

				return true;
			} else {
				return false;
			}
		} else {
			LOG.info("vin not available   ");
			return false;
		}}
		else {
			LOG.info("No data");
			return false;
		}

	}

	private JSONObject  (
			ServiceHistoryWrapper serviceHistoryWrapper, JSONObject jsonObj) {

		JSONObject jsonFinalObj = new JSONObject();

		JSONArray jsonFinalArr = new JSONArray();

		List<HistoryVO> historyVO = serviceHistoryWrapper.getServiceHistory()
				.getService().getHistory();
		List<ReportOrderDetailsVO> reportOrderDetailsVO;

		try {
			JSONObject jsonServiceObject;
			JSONArray itemsArr;
			JSONObject visitObj;

			Set<Integer> serviceYear = new HashSet<Integer>();
			for (HistoryVO history : historyVO) {
				String serviceDate = history.getServiceDate();
				LOG.info("Service Date :: " + serviceDate);
				String[] dateParts = serviceDate.split("-");
				Integer yearPart = Integer.parseInt(dateParts[0]);
				Integer year = yearPart;
				LOG.info("Service Date year :: " + year);
				serviceYear.add(year);

			}

			Set<Integer> serviceMonth = new HashSet<Integer>();
			for (HistoryVO history : historyVO) {
				String serviceDate = history.getServiceDate();
				LOG.info("Service Date :: " + serviceDate);
				String[] dateParts = serviceDate.split("-");
				Integer monthPart = Integer.parseInt(dateParts[1]);
				Integer month = monthPart;

				LOG.info("Service Date month :: " + month);
				serviceMonth.add(month);

			}

			LOG.info("Service History size month:: " + serviceMonth.size());

			for (int y = 0; y < serviceYear.size(); y++) {
				JSONObject jsonFinalYearObj = new JSONObject();
				JSONArray monthArr = new JSONArray();
				LOG.info("size of service year  " + serviceYear.size());
				Object[] myArrYear = serviceYear.toArray();

				LOG.info("my set array year " + myArrYear[y]);

				for (int m = 0; m < serviceMonth.size(); m++) {

					JSONArray visitArr = new JSONArray();
					JSONObject jsonMonthObj = new JSONObject();
					Object[] myArr = serviceMonth.toArray();

					LOG.info("my set array month " + myArr[m]);

					for (int i = 0; i < historyVO.size(); i++) {

						LOG.info("Service History size :: " + historyVO.size());

						JSONArray serviceArr = new JSONArray();
						visitObj = new JSONObject();
						reportOrderDetailsVO = serviceHistoryWrapper
								.getServiceHistory().getService().getHistory()
								.get(i).getReportOrderDetails();
						String historyDate = serviceHistoryWrapper
								.getServiceHistory().getService().getHistory()
								.get(i).getServiceDate().substring(0, 10);
						String[] dateParts = historyDate.split("-");
						Integer monthPart = Integer.parseInt(dateParts[1]);
						Integer month = monthPart;

						Integer yearPart = Integer.parseInt(dateParts[0]);
						Integer year = yearPart;

						LOG.info("my Integer  month " + month);
						LOG.info("my Integer  year " + year);
						if (month.equals(myArr[m]) && year.equals(myArrYear[y])) {

							LOG.info("my set array month " + month
									+ "my set array month" + myArr[m]);
							LOG.info("my set array year " + year
									+ "my set array year" + myArrYear[y]);
							Integer mileage = historyVO.get(i).getMileage();
							String dealerId = historyVO.get(i).getDealerCode();

							for (int j = 0; j < reportOrderDetailsVO.size(); j++) {
								LOG.info("Report Order Details size :: "
										+ reportOrderDetailsVO.size());
								JSONObject jsonItemsObject = new JSONObject();
								jsonServiceObject = new JSONObject();
								itemsArr = new JSONArray();
								Integer totalCost = reportOrderDetailsVO.get(j)
										.getSvcOpPartsAmount()
										+ reportOrderDetailsVO.get(j)
										.getSvcOpLaborAmount()
										+ reportOrderDetailsVO.get(j)
										.getSvcOpMiscAmount();
								LOG.info("Total cost :: " + totalCost);

								// x566325 Service History - null or empty
								// hardcoded for CleanOpDescription - starts
								if ((Utility.isStringNull(reportOrderDetailsVO
										.get(j).getCleanOpDescription())
										.equalsIgnoreCase(EMPTY_STRING))
										|| (reportOrderDetailsVO.get(j)
												.getCleanOpDescription()
												.equalsIgnoreCase(""))) {
									LOG.info("clean op inside null" + j
											+ "value");
									jsonItemsObject.accumulate("name",
											"Service Performed");
								} else {
									jsonItemsObject.accumulate("name",
											reportOrderDetailsVO.get(j)
											.getCleanOpDescription());

								}
								// x566325 Service History - null or empty
								// hardcoded for CleanOpDescription - ends
								jsonItemsObject.accumulate("cost", totalCost);
								itemsArr.put(jsonItemsObject);
								// x566325 Service History - null or empty
								// hardcoded for SvcOpDescription - starts
								if ((Utility.isStringNull(reportOrderDetailsVO
										.get(j).getSvcOpDescription())
										.equalsIgnoreCase(EMPTY_STRING))
										|| (reportOrderDetailsVO.get(j)
												.getSvcOpDescription()
												.equalsIgnoreCase(""))) {
									jsonServiceObject.accumulate("title",
											"Service Performed");
								} else {
									jsonServiceObject.accumulate("title",
											reportOrderDetailsVO.get(j)
											.getSvcOpDescription());
								}
								// x566325 Service History - null or empty
								// hardcoded for SvcOpDescription - ends
								jsonServiceObject.accumulate("items", itemsArr);
								jsonServiceObject
								.accumulate("mileage", mileage);
								// 304026 change to append NNA to the Dealer ID
								if (dealerId == null || dealerId.isEmpty()) {
									// do nothing
								} else if (dealerId.contains("NNA")) {
									// do nothing
								} else {
									dealerId = NNA_DEALER + dealerId.trim();
								}
								//jsonServiceObject.accumulate("dealerId",dealerId);
								jsonServiceObject.accumulate(PREFERRED_DEALER,dealerId);
								LOG.info("Service Object :: "
										+ jsonServiceObject.toString());
								serviceArr.put(jsonServiceObject);

							}
							visitObj.accumulate("date", historyDate);
							visitObj.accumulate("services", serviceArr);
							visitArr.put(visitObj);

						}

					}

					if (!visitArr.isNull(0)) {
						jsonMonthObj.accumulate("month", myArr[m]);
						LOG.info("Service month :: " + myArr[m]);
						jsonMonthObj.accumulate("visits", visitArr);
						monthArr.put(jsonMonthObj);
					}

				}

				if (!monthArr.isNull(0)) {
					jsonFinalYearObj.accumulate("year", myArrYear[y]);
					LOG.info("Service year :: " + myArrYear[y]);
					jsonFinalYearObj.accumulate("months", monthArr);
					jsonFinalArr.put(jsonFinalYearObj);
				}

			}

			jsonFinalObj.accumulate("years", jsonFinalArr);
			LOG.info("jsonFinalObj " + jsonFinalObj.toString());

		} catch (JSONException e) {
			LOG.error("JSONException in ServiceHistory Response", e);
		} catch (Exception e) {
			LOG.error("Exception in ServiceHistory Response", e);
		}

		return jsonFinalObj;

	}

	/**
	 * @author x178099
	 * @use To save vehicle in user account
	 * @method-POST
	 * @header brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/failedVehicle")
	public Response failedVehicle(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("failed Vehicle JSON request is " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = true;
		boolean isValidVin = true;
		boolean isValidEmail = true;
		boolean isValidPersonHashId = true;
		boolean isValidNickName = true;
		String response = "";
		OwnerPortalUser validUser;
		String vin = "";
		String make = "";
		String email = "";
		String personHashId = "";
		String nickName = "";
		boolean generalError = false;
		String result = "";
		OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference = new OwnerPortalUserVehicleFailedReference();
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				FailedVehicleWrapper failedVehicleWrapper = mapper.readValue(
						requestJson, FailedVehicleWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(failedVehicleWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(failedVehicleWrapper
							.getFailedVehicle())) {
						if (Utility
								.isObjectNotNullorNotEmpty(failedVehicleWrapper
										.getFailedVehicle().getPerson())
								&& Utility
								.isObjectNotNullorNotEmpty(failedVehicleWrapper
										.getFailedVehicle()
										.getVehicle())) {
							isValidBrand = isBrandNull(brand, jsonObj,
									jsonFinalObj);
							vin = failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getVin();
							email = failedVehicleWrapper.getFailedVehicle()
									.getPerson().getEmail();
							personHashId = failedVehicleWrapper
									.getFailedVehicle().getPerson()
									.getPersonHashId();
							nickName = failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getNickname();
							make = failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getMake();
							if (isValidBrand) {
								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									isValidVin = isVinNull(vin, jsonObj,
											jsonFinalObj);

									if (isValidVin) {
										isValidVin = isVinValid(vin, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidEmail = isEmailNull(email,
												jsonObj, jsonFinalObj);
									}
									if (isValidVin) {
										isValidPersonHashId = isPersonhashidNull(
												personHashId, jsonObj,
												jsonFinalObj);
									}
									if (isValidVin) {
										isValidNickName = isValidNickName(
												nickName, jsonObj, jsonFinalObj);
									}
								} else {
									LOG.info("Brand is neither Nissan nor Infiniti");
									jsonObj.accumulate(CODE,
											VALIDATION_FAILED_CODE);
									jsonObj.accumulate(MESSAGE,
											VALIDATION_FAILED_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
									isValidBrand = false;
								}
							}
							if (isValidBrand && isValidVin && isValidEmail
									&& isValidPersonHashId && isValidNickName) {

								if (Utility
										.isStringNotNullorNotEmpty(failedVehicleWrapper
												.getFailedVehicle()
												.getVehicle().getMake())
										&& failedVehicleWrapper
										.getFailedVehicle()
										.getVehicle().getMake()
										.equalsIgnoreCase(BRAND_NISSAN)
										|| failedVehicleWrapper
										.getFailedVehicle()
										.getVehicle()
										.getMake()
										.equalsIgnoreCase(
												BRAND_INFINITI)) {
									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										failedVehicleWrapper.getFailedVehicle()
										.setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										failedVehicleWrapper.getFailedVehicle()
										.setBrand(INFINITI);
									}

									// x566325 - Brand Segregation - send brand
									// also

									/*
									 * validUser =
									 * userLocal.validateEmail(email,
									 * personHashId);
									 */

									validUser = userLocal.validateEmail(email,
											personHashId, brand);

									LOG.info("Inside save vehicle JSON success response for the vin"
											+ vin);

									if (!Utility
											.isObjectNotNullorNotEmpty(validUser)) {
										Util.setFaultDataToJSON(
												jsonObj,
												jsonFinalObj,
												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);
										isValidEmail = false;
									}
									// x566325 - Brand segregation - changed
									// below if to else if
									else if (!(brand.contains(BRAND_NISSAN) && "NISSAN"
											.equalsIgnoreCase(make))
											|| (brand.contains(BRAND_INFINITI) && "INFINITI"
													.equalsIgnoreCase(make))) {

										jsonObj.accumulate(CODE,
												VALIDATION_FAILED_MISMATCH_CODE);
										jsonObj.accumulate(MESSAGE,
												VALIDATION_FAILED_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												VEHICLE_INVALID_VIN_DESCRIPTION);
										jsonFinalObj.accumulate("fault",
												jsonObj);
										result = "failure";
									} else {

										if (Utility
												.isObjectNotNullorNotEmpty(validUser)) {
											LOG.info("Inside valid user JSON success response for the vin"
													+ vin);

											response = vehicleLocal
													.saveFailedVehicle(
															failedVehicleWrapper,
															validUser,
															ownerPortalUserVehicleFailedReference,
															brand);
										}
										LOG.info("Response for Failed Vehicle for the vin "
												+ vin + " is" + response);
										if (Utility
												.isStringNotNullorNotEmpty(response)
												&& response
												.equalsIgnoreCase(SUCCESS)) {
											LOG.info("Inside save failed vehicle JSON success response for the vin"
													+ vin);
											jsonObj.accumulate(CODE,
													VEHICLE_MISMATCH_CODE);
											jsonObj.accumulate(MESSAGE,
													VEHICLE_INVALID_USER_MESSAGE);
											jsonObj.accumulate(DESCRIPTION,
													VEHICLE_INVALID_USER_DESCRIPTION);
											jsonFinalObj.accumulate("fault",
													jsonObj);
											result = "success";

										} else {
											Util.setFaultDataToJSON(jsonObj,
													jsonFinalObj,
													GENERAL_ERROR_FAULT_CODE,
													GENERAL_ERROR_MESSAGE,
													GENERAL_ERROR_DESCRIPTION);
											LOG.debug("Request is null");
											generalError = true;
										}
									}

								}
							}
						} else {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							LOG.debug("Request is null");
							generalError = true;
						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null");
						generalError = true;
					}
				}
			}
		} catch (JSONException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse for the vin =" + vin + " with email ="
					+ email + "is " + jsonFinalObj.toString()
					+ "Json exception  during failed vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to Fuse in JsonParseException for the vin ="
							+ vin + " with email =" + email + "is "
							+ jsonFinalObj.toString()
							+ "JsonParseException  during failed vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					"Response to Fuse in JsonMappingException  for the vin ="
							+ vin + " with email =" + email + "is "
							+ jsonFinalObj.toString()
							+ "JsonMappingException  during update vehicle : ",
							e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse in IOException for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "IOException  during update vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("Response to Fuse in Exception for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString()
					+ "General exception  during update vehicle : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {
			LOG.info("Failed vehicle JSON success response for the vin =" + vin
					+ " with email =" + email + "is " + jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();
		} else if (!isValidVin || !isValidBrand
				|| result.equalsIgnoreCase(FAILURE) || !isValidNickName
				|| !isValidEmail) {

			LOG.info("Failed vehicle JSON failed response for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else if (generalError || !isValidPersonHashId) {
			LOG.info("Failed vehicle JSON failed response for the vin =" + vin
					+ " with email =" + email + "is " + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} else {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Failed vehicle JSON unexpected failed response for the vin ="
					+ vin
					+ " with email ="
					+ email
					+ "is "
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/messageUserAction")
	public Response messageUserAction(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("MessageUserAction JSON request = " + requestJson);
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonFinalVehicleObj = new JSONObject();

		JSONObject jsonObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidVin = true;
		boolean success = false;
		boolean isValidBrand = true;
		String makeCode = "";
		boolean generalError = false;
		boolean isValidId = true;
		boolean idNotAvailable = true;
		boolean isValidAction = true;
		boolean isvalidJSON = true;

		String id = "";
		try {
			jsonFinalVehicleObj.accumulate("message",
					"User message action selection has been updated.");
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				MessageUserActionWrapper messageUserActionWrapper = mapper
						.readValue(requestJson, MessageUserActionWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(messageUserActionWrapper
						.getMessageUserAction().getVin())) {
					if (Utility
							.isStringNotNullorNotEmpty(messageUserActionWrapper
									.getMessageUserAction().getVin())) {
						LOG.info("vin length for the vin"
								+ messageUserActionWrapper
								.getMessageUserAction().getVin()
								+ "is"
								+ messageUserActionWrapper
								.getMessageUserAction().getVin()
								.length());
						String vin = messageUserActionWrapper
								.getMessageUserAction().getVin();
						id = messageUserActionWrapper.getMessageUserAction()
								.getId();
						String action = messageUserActionWrapper
								.getMessageUserAction().getAction();
						isValidBrand = isBrandNull(brand, jsonObj, jsonFinalObj);

						if (isValidBrand) {

							if (brand.equalsIgnoreCase(BRAND_INFINITI)
									|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

								isValidBrand = true;

								isValidVin = isVinNull(vin, jsonObj,
										jsonFinalObj);

								if (isValidVin) {
									isValidVin = isVinValid(vin, jsonObj,
											jsonFinalObj);
								}
							} else {
								LOG.info("Brand is neither Nissan nor Infiniti");
								isValidBrand = false;
								jsonObj.accumulate(CODE, VALIDATION_FAILED_CODE);
								jsonObj.accumulate(MESSAGE,
										VALIDATION_FAILED_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										VEHICLE_INVALID_BRAND_DESCRIPTION);
								jsonFinalObj.accumulate(FAULT, jsonObj);
								
							}
						}
						if (isValidVin && isValidBrand) {
							LOG.debug("MessageUserAction JSON valid vin=" + vin
									+ " and valid brand=" + brand);

							if (Utility.isObjectNotNullorNotEmpty(id)) {

								List<String> messageList = new ArrayList<String>();
								messageList.add(NISSANCONNECTSERVICES_MSG_ID);

								messageList.add(INFINFITICONNECTION_MSG_ID);
								messageList.add(NISSANCONNECTNAVIGATION_MSG_ID);
								messageList.add(NISSANCONNECTEV_MSG_ID);
								messageList.add(INFINITIINTOUCHSERVICES_MSG_ID);
								messageList.add(COMMON_MSG_ID);

								if (!messageList.contains(id)) {
									LOG.info("Message user action - ID is not available ["
											+ id + "]");
									jsonObj.accumulate(CODE,
											MESSAGE_USER_ACTION_ERROR_CODE);
									jsonObj.accumulate(MESSAGE,
											INVALID_MESSAGE_ID_ERROR_MESSAGE);
									jsonObj.accumulate(DESCRIPTION,
											INVALID_MESSAGE_ID_ERROR_DESCRIPTION);
									jsonFinalObj.accumulate("fault", jsonObj);
									idNotAvailable = false;
								} else {
									if (!action.equalsIgnoreCase("accept")
											&& !action
											.equalsIgnoreCase("decline")
											&& !action
											.equalsIgnoreCase("dismiss")) {

										LOG.info("Message user action is not available ["
												+ id + "]");
										jsonObj.accumulate(CODE,
												MESSAGE_USER_ACTION_ERROR_CODE);
										jsonObj.accumulate(MESSAGE,
												INVALID_ACTION_ERROR_MESSAGE);
										jsonObj.accumulate(DESCRIPTION,
												INVALID_ACTION_ERROR_DESCRIPTION
												+ " " + action);
										jsonFinalObj.accumulate("fault",
												jsonObj);

										isValidAction = false;
									} else {
										if (action.equalsIgnoreCase("dismiss")) {
											return Response
													.ok()
													.status(200)
													.entity(jsonFinalVehicleObj
															.toString())
													.build();
										} else {
											Timestamp timestamp = new Timestamp(
													System.currentTimeMillis());
											List<OwnerPortalUserVehicle> ownerPortalUserVehicles = vehicleLocal
													.getOwnerPortalUserVehicles(vin);
											String userProfileId = ownerPortalUserVehicles
													.get(0)
													.getOwnerPortalUserVehiclePK()
													.getUserProfileId();

											List<TermsAndConditionsAgreementSt> agreementSts = vehicleLocal
													.getTermsAndConditionsAgreementStUsingSorcecode(
															vin, userProfileId,
															"A");
											List<TermsAndConditionsAgreementSt> agreementSts2 = vehicleLocal
													.getTermsAndConditionsAgreementStUsingSorcecode(
															vin, userProfileId,
															"D");

											if (action
													.equalsIgnoreCase("accept")
													&& agreementSts != null) {
												return Response
														.ok()
														.status(200)
														.entity(jsonFinalVehicleObj
																.toString())
														.build();

											} else if (action
													.equalsIgnoreCase("decline")
													&& agreementSts2 != null) {

												TermsAndConditionsAgreementSt agreementSt = agreementSts2
														.get(0);
												agreementSt
												.setUpdtTs(timestamp);
												vehicleLocal
												.updateTermsAndConditionsAgreementSt(agreementSt);
												return Response
														.ok()
														.status(200)
														.entity(jsonFinalVehicleObj
																.toString())
														.build();
											} else {

												TermsAndConditionsAgreementSt agreementSt = new TermsAndConditionsAgreementSt();
												TermsAndConditionsAgreementStPK agreementStPK = new TermsAndConditionsAgreementStPK();
												if (action
														.equalsIgnoreCase("accept")) {
													agreementStPK
													.setTermCndtnSrcCd("A");
												} else if (action
														.equalsIgnoreCase("decline")) {
													agreementStPK
													.setTermCndtnSrcCd("D");
												}
												agreementStPK
												.setUsrPrflId(userProfileId);
												agreementStPK.setVin(vin);

												agreementSt
												.setCrteTs(timestamp);
												agreementSt
												.setTermCndtnAgrmtTs(timestamp);
												agreementSt
												.setUpdtTs(timestamp);
												// agreementSt.setVin(vin);

												agreementSt
												.setId(agreementStPK);
												agreementSt
												.setCrteUsrId("NNACOP");
												agreementSt
												.setUpdtUsrId("NNACOP");
												vehicleLocal
												.insertTermsAndConditionsAgreementSt(agreementSt);
												success = true;
											}
										}
									}
								}
							} else {
								jsonObj.accumulate(CODE,
										CONNECTED_SERVICE_DETAIL_ERROR_CODE);
								jsonObj.accumulate(MESSAGE,
										INVALID_ID_ERROR_MESSAGE);
								jsonObj.accumulate(DESCRIPTION,
										INVALID_ID_ERROR_DESCRIPTION);
								jsonFinalObj.accumulate("fault", jsonObj);
								isValidId = false;
							}

						} else {
							LOG.info("Vin not available in pipeline table");
							jsonObj.accumulate(CODE, VIN_NOTAVAILABLE_CODE);
							jsonObj.accumulate(MESSAGE,
									VEHICLE_UNAVAILABLE_VIN_MESSAGE);
							jsonObj.accumulate(DESCRIPTION,
									VEHICLE_UNAVAILABLE_VIN_DESCRIPTION);
							jsonFinalObj.accumulate(FAULT, jsonObj);
							LOG.info("Vin not available in pipeline table"
									+ jsonFinalObj.toString());
							isValidVin =false;
						}

					} else {

						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						LOG.info("Invalid json request");
					
					       isvalidJSON=false;
					}
				} else {

					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					LOG.info("Invalid json request");
				
				       isvalidJSON=false;
				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Invalid json request");
			
			       isvalidJSON=false;
			}
			
			if (success) {
				LOG.info("MessageUserAction JSON success response = "
						+ jsonFinalVehicleObj.toString());
				return Response.ok().status(200)
						.entity(jsonFinalVehicleObj.toString()).build();
			} else if (!isValidVin || !isValidBrand || !idNotAvailable
					|| !isValidId || !isValidAction ||!isvalidJSON) {
				LOG.info("Inside MessageUserAction  JSON failed response"
						+ jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else if (generalError) {
				LOG.info("Inside MessageUserAction  general error and response"
						+ jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Inside else json request");
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonParseException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JsonMappingException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JsonMappingException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (JSONException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Inside JSONException");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();

		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error("response is =" + jsonFinalObj.toString()
			+ "Exception  during MessageUserAction : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();

		}

		

	}
	
	
	/*
	 * @Author X522443
	 * 
	 * @Method-Get
	 * 
	 * @param input fields
	 * 
	 * @return
	 * 
	 * @throws JSONException vin=****&size=****&type
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getImageUrl")
	public Response getImageUrl(String requestJson) throws JSONException {
		LOG.info("Action class =vehicleWS ::: Method= getImageUrl Starts");
		LOG.info("Action class =vehicleWS ::: Method= getImageUrl :::Get ImageUrl request body = " + requestJson);

		String vin = null;
		String brand = "Nissan";
		String size = "D";
		String res = "HR";
		String width = "1950";
		String height = "975";
	    String view = "E01";
	    String background = "transparent";
	    String shadow = "ON";
	    String type = "png";
		
			JSONObject reqJson = new JSONObject(requestJson);
			vin = reqJson.getString("vin");
			size = reqJson.getString("size");
			res = reqJson.getString("res");
			width = reqJson.getString("width");
			height = reqJson.getString("height");
			view = reqJson.getString("view");
			background = reqJson.getString("background");
			shadow = reqJson.getString("shadow");
			type = reqJson.getString("type");
			
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		
		boolean isValidVin = true;
		String imageUrl = "";
		String modelCode = null;
		String exteriorColorCode = null;
		String factoryOptionCode = null;
		String make = null;
		String modelName = null;
		String bodyStyleName = null;
		String modelYear = null;
		String eimCode = null;
		try {
			if (Utility.isStringNotNullorNotEmpty(vin)) {
				isValidVin = isVinNull(vin, jsonObj, jsonFinalObj);
				if (isValidVin) {
					isValidVin = isVinValid(vin, jsonObj, jsonFinalObj);
				}
				if (isValidVin) {
					LOG.info("Action class =vehicleWS ::: Method= getImageUrl :::isValidVin = " + isValidVin);

					ManualVehicleLookup vehinfo = null;
					OwnerPortalVehicle imageownerportalVehicleInfo = null;

					JSONObject vehInfoResponse = new JSONObject();
					vehInfoResponse = getVehicleDetails(vin);

					if (vehInfoResponse.get("status").toString().equalsIgnoreCase("success")) {
						modelCode = vehInfoResponse.get("VehicleNMCModelCode").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("VehicleNMCModelCode").toString();  
						exteriorColorCode = vehInfoResponse.get("ExteriorColorCode").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("ExteriorColorCode").toString();
						factoryOptionCode = vehInfoResponse.get("FactoryOptionsText").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("FactoryOptionsText").toString();
						make = vehInfoResponse.get("VehicleMakeCode").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("VehicleMakeCode").toString();
						modelName = vehInfoResponse.get("NMCModelName").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("NMCModelName").toString();
						bodyStyleName = vehInfoResponse.get("BodyStyleName").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("BodyStyleName").toString();
						modelYear = vehInfoResponse.get("VehicleYearNumber").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("VehicleYearNumber").toString();
						eimCode = vehInfoResponse.get("EIMCode").toString().equalsIgnoreCase("null") ? "" : vehInfoResponse.get("EIMCode").toString();
					}

					LOG.info(" Action class =vehicleWS ::: Method= getImageUrl ::: modelCode = " + modelCode + "::exteriorColorCode = " + exteriorColorCode
							+ "factoryOptionCode = " + factoryOptionCode + "make= " + make + "modelName= "
							+ modelName + "bodyStyleName= " + bodyStyleName + "ModelYear= " + modelYear);

					if(modelYear != null && (Integer.parseInt(modelYear) >= 2020 && Integer.parseInt(modelYear) <= 2022)) {
						imageUrl = Utility.getMackImageUrl(modelYear, exteriorColorCode, eimCode, modelName,
								width, height, view, background, shadow, type);
					} else {
						// BIDW service call end and its response
						String sizeType = EMPTY_STRING;
						String makeCode = EMPTY_STRING;
						String modlname = EMPTY_STRING;
						String bodystylenme = EMPTY_STRING;
						// include Image URL's in the response
						if ((modelCode != null && modelCode.length() > 0)
								&& (factoryOptionCode != null && factoryOptionCode.length() > 0)
								&& (exteriorColorCode != null && exteriorColorCode.length() > 0)) {
							//
							if ((size != null && size.length() > 0)
									&& (size.equalsIgnoreCase("D") || size.equalsIgnoreCase("DESKTOP"))) {
								size = SIZE_DESKTOP;
							} else if ((size != null && size.length() > 0)
									&& (size.equalsIgnoreCase("M") || size.equalsIgnoreCase("MOBILE"))) {
								size = SIZE_MOBILE;
							} else if ((size != null && size.length() > 0)
									&& (size.equalsIgnoreCase("T") || size.equalsIgnoreCase("TABLET"))) {
								size = SIZE_TABLET;
							} else {
								size = SIZE_DESKTOP;
							}
							// Standard(SD) or High Resolution(HR)
							if ((res != null && res.length() > 0)
									&& (res.equalsIgnoreCase("SD") || res.equalsIgnoreCase("STANDARD"))) {
								res = STANDARD;
							} else if ((res != null && res.length() > 0)
									&& (res.equalsIgnoreCase("HR") || res.equalsIgnoreCase("HIGH_RESOLUTION"))) {
								res = HIGH_RESOLUTION;

							} else {
								res = STANDARD;
							}
							//
							if ((size != null && size.length() > 0) && (res != null && res.length() > 0))
								sizeType = size + "_" + res;

							LOG.info(
									"Action class =vehicleWS ::: Method= getImageUrl :::Before getVehicleFunctionImage() the ModelYear= "
											+ modelYear);

							if (modelYear != null && Integer.parseInt(modelYear) >= 2016) {
								LOG.info("Action class =vehicleWS ::: Method= getImageUrl:: Modelyear is 2016");
								imageUrl = vehicleLocal.getVehicleFunctionImage(modelCode, exteriorColorCode,
										factoryOptionCode, sizeType);
							}

							if (!imageUrl.equals("") && imageUrl.length() > 0
									&& imageUrl.equalsIgnoreCase("Not_Available")
									|| (imageUrl.contains("exterior-.png"))) {
								LOG.info(
										"Action class =vehicleWS ::: Method= getImageUrl :::After getVehicleFunctionImage() ::: ImageUrl = "
												+ imageUrl);
								LOG.info(
										"Action class =vehicleWS ::: Method= getImageUrl :::If Image URL out put is Not_Available hitting SilhouetteImageURL::make= "
												+ make + "::bodyStyleName = " + bodyStyleName);
								if (make != null && (modelName != null || bodyStyleName != null)) {
									if (make.equalsIgnoreCase(NISSAN)) {
										makeCode = BRAND_NISSAN;
									} else if (make.equalsIgnoreCase(INFINITI)) {
										makeCode = BRAND_INFINITI;
									}
									imageUrl = Utility.getLargeSilhouetteImageURL(makeCode, bodyStyleName, modelName);
									LOG.info(
											"Action class =vehicleWS ::: Method= getImageUrl ::: SilhouetteImageURL = "
													+ imageUrl);
								}
							}
						}
						if ((make != null && (modelName != null || bodyStyleName != null)) && (imageUrl == null)
								|| (imageUrl.equalsIgnoreCase("Not_Available"))
								|| (imageUrl.contains("exterior-.png"))) {
							LOG.info(
									"Action class =vehicleWS ::: Method= getImageUrl inside  if block::: BIDW service response primary 3 fields not available then Modelname , bodyStyleName fields are available");
							if (make.equalsIgnoreCase(NISSAN)) {
								makeCode = BRAND_NISSAN;
							} else if (make.equalsIgnoreCase(INFINITI)) {
								makeCode = BRAND_INFINITI;
							}
							imageUrl = Utility.getLargeSilhouetteImageURL(makeCode, bodyStyleName, modelName);
							LOG.info(
									"Action class =vehicleWS ::: Method= getImageUrl inside if block::: SilhouetteImageURL response = "
											+ imageUrl);
						}
						if ((imageUrl.equals("")) || (imageUrl.equalsIgnoreCase("Not_Available"))
								|| (imageUrl.length() <= 2) || (imageUrl.contains("exterior-.png"))) {
							LOG.info(
									"Action class =vehicleWS ::: Method= getImageUrl inside block::: Vehicle information fetching from OP DB");
							vehinfo = vehicleLocal.vinLookup(vin);
							imageownerportalVehicleInfo = vehicleLocal.getVehicleInfo(vin);
							if (Utility.isObjectNotNullorNotEmpty(vehinfo) || vehinfo != null) {
								if (vehinfo.getVehicleMakeCode().equalsIgnoreCase("N")) {
									makeCode = BRAND_NISSAN;
								} else if (vehinfo.getVehicleMakeCode().equalsIgnoreCase("I")) {
									makeCode = BRAND_INFINITI;
								}

								modlname = vehinfo.getModelLineCode();
								LOG.info(
										"Action class =vehicleWS ::: Method= getImageUrl inside else block::: Vehicle load:::makeCode = "
												+ makeCode + "::bodystylenme = " + bodystylenme + ":::modelname = "
												+ modlname);
								imageUrl = Utility.getLargeSilhouetteImageURL(makeCode, bodystylenme, modlname);
							} else if (Utility.isObjectNotNullorNotEmpty(imageownerportalVehicleInfo)
									|| imageownerportalVehicleInfo != null) {
								if (imageownerportalVehicleInfo.getVehicleMakeCode().equalsIgnoreCase("N")) {
									makeCode = BRAND_NISSAN;
								} else if (imageownerportalVehicleInfo.getVehicleMakeCode().equalsIgnoreCase("I")) {
									makeCode = BRAND_INFINITI;
								}

								modlname = imageownerportalVehicleInfo.getVehicleModelName();
								LOG.info(
										"Action class =vehicleWS ::: Method= getImageUrl inside else block::: ownerportal Vehicle:::makeCode = "
												+ makeCode + "::bodystylenme = " + bodystylenme + ":::modelname = "
												+ modlname);
								imageUrl = Utility.getLargeSilhouetteImageURL(makeCode, bodystylenme, modlname);
							}

							else {
								LOG.info("Action class =vehicleWS ::: Method= getImageUrl final IF:::ImageUrl = "
										+ imageUrl);
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VIN_UNAVAILABLE_IN_BIDW_AND_OP_DB_ERROR_CODE, VEHICLE_UNAVAILABLE_VIN_MESSAGE,
										VIN_UNAVAILABLE_IN_BIDW_AND_OP_DB_DESCRIPTION);
								return Response.ok().status(404).entity(jsonFinalObj.toString()).build();
							}
							LOG.info(
									"Action class =vehicleWS ::: Method= getImageUrl inside else if block::: SilhouetteImageURL response = "
											+ imageUrl);

						}
					}
					JSONObject response = null;
					jsonObj.accumulate("imageUrl", imageUrl);
					if ((!imageUrl.equals("") && imageUrl.length() > 2)
							|| (imageUrl.equals("") && vehinfo!=null || imageownerportalVehicleInfo!=null)) {
						return Response.ok().status(200).entity(jsonObj.toString()).build();
					} else {
						return Response.ok().status(500).entity(jsonFinalObj.toString()).build();
					}


				}
			} else {
				LOG.info("Action class = vehicleWS ::: Method= getImageUrl VIN is empty");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj, VALIDATION_FAILED_CODE, VALIDATION_FAILED_MESSAGE,
						VEHICLE_INVALID_VIN_DESCRIPTION1);
				return Response.ok().status(400).entity(jsonFinalObj.toString()).build();
			}

		} catch (Exception e) {
			LOG.error("Action class = vehicleWS ::: Method= getImageUrl Exception :", e);
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj, GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(400).entity(jsonFinalObj.toString()).build();
		}
		LOG.info("Action class =vehicleWS ::: Method= getImageUrl Ends");
		return Response.ok().status(400).entity(jsonFinalObj.toString()).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getOVCaseStatus")
	public Response getOVCaseStatus(@HeaderParam("Brand") String brand, @HeaderParam("X-JWT-Assertion") String jwt)
			throws JSONException {
		Date invokedDate = new Date();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonObject = new JSONObject();

		String largeImageUrl = "";
		String smallImageUrl = "";
		String modelCode = "";
		String exteriorColorCode = "";
		String factoryOptionCode = "";
		String bodyStyleName = "";
		String modelName = "";
		String trimLevelDescription = "";
		String exteriorColorName = "";
		String interiorcolorName = "";
		String modelLineCode = "";
		String interiorColorCode = "";
		String eimcode = "";
		OwnerPortalVehicle vehicleInfo = null;
		OwnerPortalVehicle ownerPortalVehicle = null;

		try {
			LOG.info("Get Ownership Verification case status. Process start time = " + invokedDate.toString());
			if (brand != null && brand != ""
					&& (brand.equalsIgnoreCase(BRAND_NISSAN) || brand.equalsIgnoreCase(BRAND_INFINITI))) {
				LOG.info("Brand obtained from header: " + brand);
				brand = Utility.getBrand(brand);
			} else {
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_CODE,
						VALIDATION_FAILED_MESSAGE, VEHICLE_INVALID_BRAND_DESCRIPTION)).build();
			}

			String email = Utility.getUsernameFromJWT(jwt);
			if (email.isEmpty()) {
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_CODE,
						VEHICLE_INVALID_JWT_MESSAGE, VEHICLE_INVALID_JWT_DESCRIPTION)).build();
			}

			OwnerPortalUser user = userLocal.validateUser(email, brand);
			String profileId = user.getUserProfileId().toString();
			List<OwnerPortalUserVehicleFailedReference> pendingVehicles = vehicleLocal
					.getOVCaseVehiclesByStatus(profileId);
			List<String> vehicles = vehicleLocal.getOwnerVehicles(profileId);
			// Remove any duplicate vehicle records from failed vehicles list
			if (pendingVehicles.isEmpty() || pendingVehicles.size() < 1) {
				jsonFinalObj.put("ovPendingVehicles", jsonArray);
				return Response.ok().status(200).entity(jsonFinalObj.toString()).build();
			}
			List<OwnerPortalUserVehicleFailedReference> uniquePendingVehicles = pendingVehicles.stream()
					.collect(collectingAndThen(toCollection(
							() -> new TreeSet<>(Comparator.comparing(OwnerPortalUserVehicleFailedReference::getVin))),
							ArrayList::new));
			if (vehicles.size() > 0 && uniquePendingVehicles.size() > 0) {
				// Removing active vehicles from pending failed vehicles list if its available
				for (String vin : vehicles) {
					uniquePendingVehicles.removeIf(v -> v.getVin().toLowerCase().equalsIgnoreCase(vin));
				}
			}
			
			// Calling OV status for the list of pending vehicles
			for (OwnerPortalUserVehicleFailedReference failedVehicles : uniquePendingVehicles) {
				LOG.info("Vin : " + failedVehicles.getVin());
				LOG.info("CRM Case ID : " + failedVehicles.getCrmCaseId());
				JSONObject jsonObj = new JSONObject();
				//String caseStatus = vehicleLocal.getCurrentOVStatusFromSFDC(failedVehicles.getCrmCaseId());
				Map<String,String> map = vehicleLocal.getCurrentOVStatusFromSFDC(failedVehicles.getCrmCaseId());
				LOG.info("Final map = "+map); 
				LOG.info("Final map.get(error)"+map.get("error")); 
				if (!map.isEmpty() && map.get("apiresponse").equalsIgnoreCase("success")){
					jsonObject = getVehicleDetails(failedVehicles.getVin());
					jsonObj.put("vin", failedVehicles.getVin());
					OwnerPortalUserVehicle vehicleData = vehicleLocal.getUserVehicleInfo(profileId, failedVehicles.getVin());
					LOG.info("Final SFDC CaseStatus  = "+map.get("CaseNumber"));
					LOG.info("Final SFDC CaseNumber = "+map.get("CaseNumber"));
					jsonObj.put("caseNumber", map.get("CaseNumber"));
					if (vehicleData != null) {
                        jsonObj.put("caseStatus", "Complete");
						jsonObj.put("vehicleEnrollmentDate", vehicleData.getCreateTimestamp());
					} else {
						jsonObj.put("caseStatus", map.get("CaseStatus"));
						jsonObj.put("vehicleEnrollmentDate", "");
					}
					if (jsonObject.get("status") == "success") {
						modelCode = jsonObject.get("VehicleNMCModelCode").toString();
						factoryOptionCode = jsonObject.get("FactoryOptionsText").toString();
						exteriorColorCode = jsonObject.get("ExteriorColorCode").toString();
						bodyStyleName = jsonObject.get("BodyStyleName").toString();
						trimLevelDescription = jsonObject.get("TrimLevelDescription").toString();
						exteriorColorCode = jsonObject.get("ExteriorColorCode").toString();
						exteriorColorName = jsonObject.get("ExteriorColorName").toString();
						interiorColorCode = jsonObject.get("TrimCode").toString();
						interiorcolorName = jsonObject.get("TrimCodeName").toString();
						modelLineCode = jsonObject.get("VehicleLineCode").toString();
						modelName = jsonObject.get("VehicleLineName").toString();
						eimcode = jsonObject.get("EIMCode").toString();

						LOG.info("eimcode from BIDW : " + eimcode);
						LOG.info("modelCode from BIDW : " + modelCode);
						LOG.info("exteriorColorCode from BIDW : " + exteriorColorCode);
						LOG.info("factoryOptionCode from BIDW : " + factoryOptionCode);

						vehicleInfo = vehicleLocal.fetchVehicleInfoFromVehicleList(failedVehicles.getVin());

						jsonObj.put("colour", vehicleInfo.getVehicleExteriorColorName());
						jsonObj.put("detailedVehicleName", vehicleInfo.getVehicleModelName());
						jsonObj.put("vehicleName", vehicleInfo.getVehicleModelName());
						if (vehicleInfo.getVehicleModelName().contains("LEAF")
								|| vehicleInfo.getVehicleModelName().contains("LEF")) {
							jsonObj.put("electric", IS_TRUE);
						} else {
							jsonObj.put("electric", IS_FALSE);
						}
						jsonObj.put("modelYear", vehicleInfo.getModelYearNumber());
						jsonObj.put("modelIdentifier", vehicleInfo.getVehicleModelCode());

						if (Utility.isStringNotNullorNotEmpty(modelCode)
								&& Utility.isStringNotNullorNotEmpty(exteriorColorCode)
								&& Utility.isStringNotNullorNotEmpty(factoryOptionCode)) {
							// condition to check if the vehicle year is >= 2016. if not set sillhoutte
							// images
							if (Integer.parseInt(vehicleInfo.getModelYearNumber()) >= 2016) {

								largeImageUrl = vehicleLocal.getVehicleFunctionLargeImage1(modelCode, exteriorColorCode,
										factoryOptionCode);
								smallImageUrl = vehicleLocal.getVehicleFunctionSmallImage1(modelCode, exteriorColorCode,
										factoryOptionCode);
							} else {
								LOG.info("Vehicle Model Year is less that 2016 so setting silhoutte images:"
										+ vehicleInfo.getModelYearNumber());
								largeImageUrl = "Not_Available";
								smallImageUrl = "Not_Available";
							}

							LOG.info("Final valid BIDW imageurl " + largeImageUrl + "smallimage " + smallImageUrl);
							if (largeImageUrl.contains("exterior-.png")
									|| largeImageUrl.equalsIgnoreCase("Not_Available")) {
								largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
								LOG.info("final silhoutte largeImageUrl" + largeImageUrl);

							}
							if (smallImageUrl.contains("exterior-.png")
									|| smallImageUrl.equalsIgnoreCase("Not_Available")) {

								smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
								LOG.info("final silhoutte smallImageUrl" + smallImageUrl);
							}

						} else {
							LOG.info("all the 5 values are null from BIDW & vin " + vehicleInfo.getVin());

							// check the owner portal vehicle table for ModelName
							ownerPortalVehicle = vehicleLocal.getModelNameUsingVin(vehicleInfo.getVin());
							modelName = ownerPortalVehicle.getVehicleModelName();
							LOG.info("all the 5 values are null from BIDW & modeLName from DB: " + modelName);
							// x055765 - Show silhouette images if the url is not in GPAS - starts
							largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
							LOG.info("final silhoutte largeImageUrl using ModelName" + largeImageUrl);
							smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
							LOG.info("final silhoutte smallImageUrl using ModelName" + smallImageUrl);

						}
						jsonObj.put("largeImage", largeImageUrl);
						jsonObj.put("smallImage", smallImageUrl);
						jsonObj.put("trimLevelDescription", trimLevelDescription);
						jsonObj.put("eimCode", eimcode);
						jsonObj.put("exteriorColorCode", exteriorColorCode);
						jsonObj.put("exteriorColorName", exteriorColorName);
						jsonObj.put("interiorColorCode", interiorColorCode);
						jsonObj.put("interiorColorName", interiorcolorName);
						jsonObj.put("modelLineCode", modelLineCode);
					} else {
						jsonObj.put("colour", "");
						jsonObj.put("detailedVehicleName", "");
						jsonObj.put("vehicleName", "");
						jsonObj.put("electric", "");
						jsonObj.put("modelYear", "");
						jsonObj.put("modelIdentifier", "");
						jsonObj.put("largeImage", "");
						jsonObj.put("smallImage", "");
						jsonObj.put("trimLevelDescription", "");
						jsonObj.put("eimCode", "");
						jsonObj.put("exteriorColorCode", "");
						jsonObj.put("exteriorColorName", "");
						jsonObj.put("interiorColorCode", "");
						jsonObj.put("interiorColorName", "");
						jsonObj.put("modelLineCode", "");
					}
					
					
					jsonArray.put(jsonObj);
				}
			}
			jsonFinalObj.put("ovPendingVehicles", jsonArray);
			LOG.info("Processing Time for Get Ownership Verification case status, duration = "
					+ Utility.showTimeDiff(invokedDate) + "ms");
			return Response.ok().status(200).entity(jsonFinalObj.toString()).build();
		} catch (Exception e) {
			LOG.error("Action class = vehicleWS ::: Method= getImageUrl Exception :", e);
			JSONObject jsonObj = new JSONObject();
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj, GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(400).entity(jsonFinalObj.toString()).build();
		}
	}

	
	public JSONObject getVehicleDetails(String vin) {

		JSONObject jsonObject = new JSONObject();
		LOG.info("Getting vehicle details from BIDW for VIN for SFDC API:: " + vin);
		String requestURL = BIDW_WEBSERVICE_URL+vin;
		String authorizationKey = BIDW_AUTHORIZATION_KEY;
		LOG.info("BIDW RequestURL " + requestURL);
		try{
			HttpClient hc = new DefaultHttpClient();
			HttpGet hGet = new HttpGet(requestURL);
			// add request header
			hGet.addHeader("Authorization", "Basic "+ authorizationKey);
			hGet.addHeader("Content-Type", "application/json");
			HttpResponse hresp = hc.execute(hGet);
			LOG.info ("Response Code : " + hresp.getStatusLine().getStatusCode());
			if (hresp.getStatusLine().getStatusCode() !=200){
				jsonObject.accumulate("status", "failure");
			}else{
				BufferedReader rd = new BufferedReader(new InputStreamReader(hresp.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				jsonObject = new JSONObject(result.toString());
				jsonObject.accumulate("status", "success");
				LOG.info("Final JSON Object from BIDW for SFDC API = "+jsonObject);

			}
			}catch(Exception ex){
				LOG.error("Exception in getVehicleDetails calling web service for BIDW DB: " +ex);
				return null;
			}
			return jsonObject;
		}
	

}
