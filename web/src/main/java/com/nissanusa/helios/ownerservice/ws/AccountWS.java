package com.nissanusa.helios.ownerservice.ws;

/*
 *last modified date 09-10-2016 by x178099

 */
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.logging.Logger;

import com.nissanusa.helios.ownerservice.vo.ChangeEmailRevokeWrapper;
import com.nissanusa.helios.ownerservice.vo.AccountUnlockWrapper;
import com.nissanusa.helios.ownerservice.entity.ManualVehicleLookup;
import com.nissanusa.helios.ownerservice.entity.MobileProvider;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalLinkReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhone;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicle;
import com.nissanusa.helios.ownerservice.entity.State;
import com.nissanusa.helios.ownerservice.facade.UserLocal;
import com.nissanusa.helios.ownerservice.facade.VehicleLocal;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.PropertiesLoader;
import com.nissanusa.helios.ownerservice.util.Utility;
import com.nissanusa.helios.ownerservice.vo.GetUserWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveUserWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateDataAccountWrapper;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailWrapper;

@Path("/account")
/**
 * @author x178099
 * @use WS class will hold the rest services involving update account services
 *
 */
public class AccountWS implements OwnerConstants {

	private static final Logger LOG = Logger.getLogger(AccountWS.class);

	@Inject
	UserLocal userLocal;
	@Inject
	VehicleLocal vehicleLocal;

	public AccountWS() {
		try {
			PropertiesLoader.getLog4j();

		} catch (Exception e) {
			LOG.info("PropertiesLoaderException Exception AccountWS: "
					+ e.getMessage());

		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/accountunlock")
	public Response accountUnlock(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		LOG.info("account unlock JSON request is " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		AccountUnlockWrapper accountUnlockWrapper = null;
		String result = "";
		boolean isValidBrand = false;
		OwnerPortalUser validUser = null;

		try {

			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				accountUnlockWrapper = mapper.readValue(requestJson,
						AccountUnlockWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(accountUnlockWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(accountUnlockWrapper
							.getAccountUnlock())) {
						if (Utility
								.isObjectNotNullorNotEmpty(accountUnlockWrapper
										.getAccountUnlock().getPerson())) {
							isValidBrand = Util.isBrandNull(brand, jsonObj,
									jsonFinalObj);

							if (isValidBrand) {

								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									String emailId = accountUnlockWrapper
											.getAccountUnlock().getPerson()
											.getEmail();
									String personHashId = accountUnlockWrapper
											.getAccountUnlock().getPerson()
											.getPersonHashId();

									//x055765 - Brand Segregation - send brand also
									validUser = userLocal.validateEmail(
											emailId, personHashId,brand);
									if (!Utility
											.isObjectNotNullorNotEmpty(validUser)) {
										Util.setFaultDataToJSON(
												jsonObj,
												jsonFinalObj,
												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

										result = FAILURE;
									} else {
										if (Utility
												.isObjectNotNullorNotEmpty(validUser)) {

											userLocal
													.updateLinkReferenceHashIdStatusByUserProfileId(validUser
															.getUserProfileId());
											result = userLocal
													.updateUnlockAccountStatus(validUser);
											if (result.equals(SUCCESS)) {
												result = SUCCESS;
											}
										}
									}

								} else {
									LOG.info("Brand is neither N nor I");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VEHICLE_INVALID_BRAND_CODE,
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
							LOG.debug("Request is null");

						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null");

					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null");

				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null");

			}

		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					" Response to Fuse for the user is"
							+ jsonFinalObj.toString()
							+ "JsonParseException during account unlock  : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					" Response to Fuse for the user is"
							+ jsonFinalObj.toString()
							+ "JsonMappingException during account unlock : ",
					e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(
					" Response to Fuse for the user is"
							+ jsonFinalObj.toString()
							+ "General Exception during account unlock : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		if (result.equalsIgnoreCase(SUCCESS)) {

			jsonObj = setAccountUnlockResponse();

			LOG.info("account unlock  JSON success response for the user is"
					+ jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();

		} else if (!isValidBrand || result.equalsIgnoreCase(FAILURE)) {
			LOG.info("Inside Update User JSON failed response for the useris"
					+ jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("account unlock JSON failed response for the user is"
					+ jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}

	/**
	 * @author x178099
	 * @use To revoke user email change request
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/changeemailrevoke")
	public Response changeEmailRevoke(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		LOG.info("Change email revoke JSON request is " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonPrsnObj = new JSONObject();
		ChangeEmailRevokeWrapper changeEmailRevokeWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = false;
		boolean isValidRequest = true;
		OwnerPortalUser validUser = null;
		String userName = "";
		String result = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				changeEmailRevokeWrapper = mapper.readValue(requestJson,
						ChangeEmailRevokeWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(changeEmailRevokeWrapper)) {
					if (Utility
							.isObjectNotNullorNotEmpty(changeEmailRevokeWrapper
									.getChangeEmailRevoke())) {
						if (Utility
								.isObjectNotNullorNotEmpty(changeEmailRevokeWrapper
										.getChangeEmailRevoke().getPerson())) {
							isValidBrand = Util.isBrandNull(brand, jsonObj,
									jsonFinalObj);

							if (isValidBrand) {

								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										changeEmailRevokeWrapper
												.getChangeEmailRevoke()
												.getPerson().setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										changeEmailRevokeWrapper
												.getChangeEmailRevoke()
												.getPerson().setBrand(INFINITI);
									}

									String emailId = changeEmailRevokeWrapper
											.getChangeEmailRevoke().getPerson()
											.getEmail();
									String personHashId = changeEmailRevokeWrapper
											.getChangeEmailRevoke().getPerson()
											.getPersonHashId();

									String oldEmail = changeEmailRevokeWrapper
											.getChangeEmailRevoke().getPerson()
											.getOldEmail();

									isValidRequest = validateChangeEmailRevokeRequest(
											emailId, oldEmail, personHashId,
											jsonObj, jsonFinalObj);

									if (isValidRequest) {
										LOG.info("Request is valid");

										//x055765 - Brand segregation - send brand also
										validUser = userLocal.validateEmail(
												emailId, personHashId,brand);
										if (!Utility
												.isObjectNotNullorNotEmpty(validUser)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

											result = FAILURE;
										} else {
											if (Utility
													.isObjectNotNullorNotEmpty(validUser)) {

												validUser = userLocal
														.revokeEmail(
																changeEmailRevokeWrapper,
																validUser);

												result = SUCCESS;
											}
										}
									}

								} else {
									LOG.info("Brand is neither N nor I");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VEHICLE_INVALID_BRAND_CODE,
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
							LOG.debug("Request is null");

						}
					} else {
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE,
								GENERAL_ERROR_MESSAGE,
								GENERAL_ERROR_DESCRIPTION);
						LOG.debug("Request is null");

					}
				} else {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
							GENERAL_ERROR_DESCRIPTION);
					LOG.debug("Request is null");

				}
			} else {
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.debug("Request is null");

			}
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the user" + userName + "is"
					+ jsonFinalObj.toString()
					+ "JsonParseException during Update User : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the user" + userName + "is"
					+ jsonFinalObj.toString()
					+ "JsonMappingException during Update User : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the user" + userName + "is"
					+ jsonFinalObj.toString()
					+ "General Exception during Update User : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
		if (result.equalsIgnoreCase(SUCCESS)) {

			jsonPrsnObj = setChangeEmailRevokeResponse(jsonObj, validUser,
					changeEmailRevokeWrapper);
			LOG.info("jsonPrsnObj" + jsonPrsnObj.toString());
			LOG.info("Update User of update account JSON success response for the user"
					+ userName + "is" + jsonObj.toString());
			return Response.ok().status(200).entity(jsonObj.toString()).build();

		} else if (!isValidBrand || result.equalsIgnoreCase(FAILURE)) {
			LOG.info("Inside Update User JSON failed response for the user"
					+ userName + "is" + jsonFinalObj.toString());
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} else {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.info("Inside Update User JSON failed response for the user"
					+ userName + "is" + jsonFinalObj.toString());
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

	}

	/**
	 * @author x178099
	 * @use To change user email
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/changeemail")
	public Response changeEmail(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {

		
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		ChangeEmailWrapper changeEmailWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = false;
		boolean isValidRequest = true;
		boolean invalidjson = true;
		OwnerPortalUser validUser = null;
		String userName = "";
		String result = "";
		String token = "";
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				changeEmailWrapper = mapper.readValue(requestJson,
						ChangeEmailWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(changeEmailWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(changeEmailWrapper
							.getChangeEmail())) {
						if (Utility
								.isObjectNotNullorNotEmpty(changeEmailWrapper
										.getChangeEmail().getPerson())) {
							isValidBrand = Util.isBrandNull(brand, jsonObj,
									jsonFinalObj);

							if (isValidBrand) {

								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										changeEmailWrapper.getChangeEmail()
												.getPerson().setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										changeEmailWrapper.getChangeEmail()
												.getPerson().setBrand(INFINITI);
									}
									String firstName = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getFirstName();
									String lastName = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getLastName();
									String addressLine1 = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getAddress().getAddressLine1();
									String emailId = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getEmail();
									String personHashId = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getPersonHashId();
									String postalCode = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getAddress().getPostalCode();
									String mobileNumber = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getMobileNumber();
									String landLineNumber = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getLandlineNumber();
									String newEmail = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getNewEmail();
									// x055765 include regioncode
									String regionCode = changeEmailWrapper
											.getChangeEmail().getPerson()
											.getAddress().getRegionCode();

									isValidRequest = validateRequest(firstName,
											lastName, addressLine1, emailId,
											personHashId, postalCode,
											mobileNumber, landLineNumber,
											jsonObj, jsonFinalObj, regionCode);
									if (newEmail == null
											|| ("").equals(newEmail)) {
										LOG.info("New Email is null ");
										Util.setFaultDataToJSON(jsonObj,
												jsonFinalObj,
												VALIDATION_FAILED_EMAIL_CODE,
												VALIDATION_FAILED_MESSAGE,
												VEHICLE_INVALID_EMAIL_DESCRIPTION);

										isValidRequest = false;
									}
									if (isValidRequest) {
										LOG.info("Request is valid");

										userName = changeEmailWrapper
												.getChangeEmail().getPerson()
												.getEmail();

										//x055765 - Brand Segregation - send brand also
										validUser = userLocal.validateEmail(
												userName, personHashId,brand);
										if (!Utility
												.isObjectNotNullorNotEmpty(validUser)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

											result = FAILURE;
										} else {
											if (Utility
													.isObjectNotNullorNotEmpty(validUser)) {

												validUser = userLocal
														.updateEmail(
																changeEmailWrapper,
																validUser);
												String userProfileId = validUser
														.getUserProfileId();
												token = userLocal
														.createLinkReferenceId(userProfileId);
												LOG.info("token" + token);
												result = SUCCESS;
											}
										}
									}

								} else {
									LOG.info("Brand is neither N nor I");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VEHICLE_INVALID_BRAND_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}
						} else {
							LOG.info("Request is invalid");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							invalidjson=false;

						}
					} else {
						LOG.info("Request is invalid");
						Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
								GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
								INVALID_JSON_REQUEST_DESCRIPTION);
						invalidjson=false;

					}
				} else {
					LOG.info("Request is invalid");
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					invalidjson=false;

				}
			} else {
				LOG.info("Request is invalid");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				invalidjson=false;
			

			}
			
			
			if (result.equalsIgnoreCase(SUCCESS)) {

				// 104026 commented the below setChangeEmailResponse and added new
				// with same name

				Util.setSuccessDataToJSON(jsonFinalObj, CHANGE_EMAIL_SUCCESS_CODE,
						CHANGE_EMAIL_SUCCESS_MESSAGE);

				LOG.info("Change email JSON success response for the user = "
						+ userName);
				return Response.ok().status(200).entity(jsonFinalObj.toString())
						.build();

			} else if (!isValidBrand || !isValidRequest || result.equalsIgnoreCase(FAILURE) || !invalidjson) {
				LOG.info("Inside Change email JSON failed response for the user"
						+ userName + "is" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
						INVALID_JSON_REQUEST_DESCRIPTION);
				LOG.info("Inside Change email JSON failed response for the user"
						+ userName + "is" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}
			
			
		} catch (JsonParseException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.error(" Response to Fuse for the user = " + userName + " response = "
					+ jsonFinalObj.toString()
					+ "JsonParseException during Update User : ", e);
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid json request");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		
		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the user" + userName + "is"
					+ jsonFinalObj.toString()
					+ "General Exception during Update User : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
		

	}

	@POST
	@Path("/changeemail/confirmation")
	public Response changeEmailAddressConfirmation(
			@HeaderParam("Brand") String brand, String requestJson)
			throws JSONException {
		LOG.info("Received Request for Change Email Confirmation Stub endpoint");
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("code", 1040003);
		jsonObj.accumulate("message", "Email updated and account unlocked");
		return Response.ok().status(200).entity(jsonObj.toString()).build();
	}

	/**
	 * @author x178099
	 * @use To validate data (verify if user is valid) for update account
	 *      service
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/validatedata")
	public Response validateData(@HeaderParam("Brand") String brand,
			//@HeaderParam("UsrName") String usrrName,
			String requestJson) throws JSONException {

		LOG.info("Account validate data JSON request " );
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonlastObj = new JSONObject();
		ValidateDataAccountWrapper accountValidateDataWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = false;
		boolean isValidRequest = false;
		boolean isvalidJSON= true;
		OwnerPortalUser ownerPortalUser = null;
		OwnerPortalLinkReference ownerPortalLinkReference = null;
		List<OwnerPortalUserPhone> ownerPortalUserPhone = null;
		State state = null;
		MobileProvider mobileProvider = null;
		String userName = "";
		String jwt = "";
		String result = "";
		String mobileCarrierCode = "";
		String usrPrflId = "";
		List<OwnerPortalUserVehicle> userVehicleInfo = null;
		OwnerPortalVehicle vehicleInfo = null;
		String vin = "";
		List<OwnerPortalVehicle> vehicleInfor = new ArrayList<OwnerPortalVehicle>();
		String vehicleMakeCode = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				accountValidateDataWrapper = mapper.readValue(requestJson,
						ValidateDataAccountWrapper.class);
				if (Utility
						.isObjectNotNullorNotEmpty(accountValidateDataWrapper)) {
					if (Utility
							.isObjectNotNullorNotEmpty(accountValidateDataWrapper
									.getValidateData())) {

						isValidBrand = Util.isBrandNull(brand, jsonObj,
								jsonFinalObj);

						if (isValidBrand) {

							if (brand.equalsIgnoreCase(BRAND_INFINITI)
									|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

								if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
									accountValidateDataWrapper
											.getValidateData().setBrand(NISSAN);
								} else if (brand
										.equalsIgnoreCase(BRAND_INFINITI)) {
									accountValidateDataWrapper
											.getValidateData().setBrand(
													INFINITI);
								}

								isValidRequest = ValidateUserRequest(
										accountValidateDataWrapper, jsonObj,
										jsonFinalObj);

								if (isValidRequest) {

									if (accountValidateDataWrapper
											.getValidateData()
											.getRequestedService()
											.equalsIgnoreCase(MULTI_CHANNEL)) {

										ownerPortalLinkReference = userLocal
												.getOwnerPortalLinkReferenceDetails(accountValidateDataWrapper
														.getValidateData()
														.getLinkReferenceId());
										LOG.info("Before if");
										if (!Utility
												.isObjectNotNullorNotEmpty(ownerPortalLinkReference)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													INVALID_LINK_REFERENCE_CODE,
													VALIDATION_FAILED_MESSAGE,
													LINK_REFERENCEID_INVALID_DESCRIPTION);
											result = FAILURE;
										} else if (Utility
												.isObjectNotNullorNotEmpty(ownerPortalLinkReference)) {
											LOG.info("Inside else if ownerPortalLinkReference");
											ownerPortalUser = userLocal
													.getUserDetails(ownerPortalLinkReference
															.getUserProfileId());
											if (!Utility
													.isObjectNotNullorNotEmpty(ownerPortalUser)) {
												Util.setFaultDataToJSON(
														jsonObj,
														jsonFinalObj,
														VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
														VALIDATION_FAILED_MESSAGE,
														VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

												result = FAILURE;
											} 
											
											
											
											else if (Utility
													.isObjectNotNullorNotEmpty(ownerPortalUser)) {
												LOG.info("User available in database for userprofileId="
														+ ownerPortalLinkReference
																.getUserProfileId());
												userLocal
														.updateLinkReferenceHashIdStatus(ownerPortalLinkReference);

												result = SUCCESS;
											}
										}
									} else {
										LOG.info("Inside else after validations");

										jwt = accountValidateDataWrapper
												.getValidateData().getJwt();
										LOG.info("JWT = " + jwt);
										if (accountValidateDataWrapper
												.getValidateData()
												.getRequestedService()
												.equalsIgnoreCase(
														CHANGE_PASSWORD)
												|| accountValidateDataWrapper
														.getValidateData()
														.getRequestedService()
														.equalsIgnoreCase(
																CHANGE_EMAIL_CONFIRMATION)) {
											if (Utility.isStringNotNullorNotEmpty(accountValidateDataWrapper
															.getValidateData()
															.getEmail())) {
												userName = accountValidateDataWrapper
														.getValidateData()
														.getEmail();
												LOG.info("Email value is =");
											}
										} else {
											userName = Util.getUsernameFromJWT(jwt);
											LOG.info("Email value from JWT = " +userName);
											
										}
										if (Utility
												.isStringNotNullorNotEmpty(userName)) {

											brand = accountValidateDataWrapper
													.getValidateData()
													.getBrand();

											ownerPortalUser = userLocal.validateUser(userName, brand);
											if(!Utility
													.isObjectNotNullorNotEmpty(ownerPortalUser)){
												Util.setFaultDataToJSON(
														jsonObj,
														jsonFinalObj,
														VALIDATION_INVALID_USER_CODE,
														USER_UNAVAILABLE_MESSAGE,
														VEHICLE_INVALID_EMAIL_FOR_BRAND);
												
											/*	VEHICLE_INVALID_EMAIL_FOR_BRAND*/
												result = FAILURE;
												
											}else{
											
											
											//x055765 - OS - 1218 Code change to get the vin from DB  and display in validate data response to call BIDW through fuse
											//code starts here
											usrPrflId = ownerPortalUser.getUserProfileId();
											
											userVehicleInfo = vehicleLocal
													.getOwnerVehicleDetails(usrPrflId);

											if (Utility
													.isObjectNotNullorNotEmpty(userVehicleInfo)) {

												Iterator<OwnerPortalUserVehicle> iterator = userVehicleInfo
														.iterator();
												OwnerPortalUserVehicle userVehicle;
												while (iterator.hasNext()) {
													userVehicle = iterator
															.next();
													if (Utility
															.isObjectNotNullorNotEmpty(userVehicle)) {
														LOG.info("ValidateData : vehicle available in user account is "
																+ userVehicle
																		.getOwnerPortalUserVehiclePK()
																		.getVin());
														vin = userVehicle
																.getOwnerPortalUserVehiclePK()
																.getVin();
														vehicleInfo = vehicleLocal
																.fetchVehicleInfoFromVehicleList(vin);

														// x055765 -
														// change-if the
														// brand is
														// nissan/infiniti
														// it should display
														// nissan/infiniti
														// vins alone
														vehicleMakeCode = vehicleInfo
																.getVehicleMakeCode();
													
															List<OwnerPortalVehicle> nissanAndInfinitiVins = vehicleLocal
																	.fetchOnlyNissanVinsAndVin(
																			vehicleMakeCode,
																			vin);
															
															vehicleInfor
																	.addAll(nissanAndInfinitiVins);

													}
												}
											}
											
											//code ends here
											

											if (!Utility
													.isObjectNotNullorNotEmpty(ownerPortalUser)) {
												Util.setFaultDataToJSON(
														jsonObj,
														jsonFinalObj,
														VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
														VALIDATION_FAILED_MESSAGE,
														VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

												result = FAILURE;

											} else if(Utility
											.isObjectNotNullorNotEmpty(ownerPortalUser) && (ownerPortalUser.getAddressText() == null
											|| ownerPortalUser.getCityName() == null
											|| ownerPortalUser.getStateKey() == null
											|| ownerPortalUser.getCellPhoneNumber() == null
											)){
											//x222914 Modify MobileCarrierName field as not mandatory
										LOG.info("Mandatory parameter is missing while process the request for AAS USERS");
										  /**
									     * @author x061538 -- AAS complete profile solution**/
										LOG.info("Brand is :::::"+brand);
										Util.setFaultDataToJSONComProfile(
												jsonObj,
												jsonFinalObj,
												VALIDATION_FAILED_MANDATORY_PARAMETER_CODE,
												VALIDATION_FAILED_MESSAGE,
												VALIDATION_FAILED_MADATORY_PARAMETER,brand);
										 /**
									     * @author x061538 -- AAS complete profile solution**/
										result = FAILURE;
									
										
									}
											else if (Utility
													.isObjectNotNullorNotEmpty(ownerPortalUser)) {
												String decryptedPassword = userLocal
														.decryptPassword(ownerPortalUser
																.getPassword());

												if (Utility
														.isStringNotNullorNotEmpty(accountValidateDataWrapper
																.getValidateData()
																.getCurrentPassword())) {
												
													if (!decryptedPassword
															.trim()
															.equals(accountValidateDataWrapper
																	.getValidateData()
																	.getCurrentPassword()
																	.trim())) {
														LOG.info("Current user details did not match DB authentication");
														Util.setFaultDataToJSON(
																jsonObj,
																jsonFinalObj,
																REQUEST_NULL_CODE,
																AUTH_MISMATCH_MESSAGE,
																INVALID_CURRENT_AUTH_DESCRIPTION);

														isValidRequest = false;
													} else {
														isValidRequest = true;
													}
												}
												if (Utility
														.isStringNotNullorNotEmpty(accountValidateDataWrapper
																.getValidateData()
																.getCode())) {
													ownerPortalLinkReference = userLocal
															.getLinkReferenceDetails(
																	accountValidateDataWrapper
																			.getValidateData()
																			.getCode(),
																	ownerPortalUser
																			.getUserProfileId());
													if (!Utility
															.isObjectNotNullorNotEmpty(ownerPortalLinkReference)) {
														Util.setFaultDataToJSON(
																jsonObj,
																jsonFinalObj,
																VALIDATION_FAILED_MISMATCH_CODE,
																VALIDATION_FAILED_MESSAGE,
																CODE_MISMATCH_DESCRIPTION);
														isValidRequest = false;
													} else {
														isValidRequest = true;
													}
												}

												if (isValidRequest) {
													ownerPortalUserPhone = userLocal
															.getUserPhoneDetails(ownerPortalUser
																	.getUserProfileId());
													LOG.info("Valid user::"
															+ userName);

													state = userLocal
															.getStateByStateKey(ownerPortalUser
																	.getStateKey());
													
													mobileCarrierCode = ownerPortalUser.getMobileCarrNm();
													
													
													if (Utility.isStringNotNullorNotEmpty(mobileCarrierCode)) {
															mobileProvider = userLocal
																	.getProviderByProviderCode(ownerPortalUser, mobileCarrierCode);
													}

													result = SUCCESS;
												}

											}

										}

									}
								}

							} 
						}else{
							LOG.info("Brand is neither N nor I");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VEHICLE_INVALID_BRAND_CODE,
									VALIDATION_FAILED_MESSAGE,
									VEHICLE_INVALID_BRAND_DESCRIPTION);
							isValidBrand = false;
							
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
			}
			
			if (result.equalsIgnoreCase(SUCCESS)) {
				LOG.info("inside success");

				if (accountValidateDataWrapper.getValidateData()
						.getRequestedService().equalsIgnoreCase(UPDATE_ACCOUNT)) {

					jsonlastObj = setUpdateAccountValidateDataResponse(jsonObj,
							ownerPortalUser, ownerPortalUserPhone, state,
							accountValidateDataWrapper, mobileProvider);

				} else if (accountValidateDataWrapper.getValidateData()
						.getRequestedService().equalsIgnoreCase(CHANGE_EMAIL)
						|| accountValidateDataWrapper.getValidateData()
								.getRequestedService()
								.equalsIgnoreCase(CHANGE_EMAIL_CONFIRMATION)) {
					jsonlastObj = setChangeEmailValidateDataResponse(jsonObj,
							ownerPortalUser, ownerPortalUserPhone, state,
							accountValidateDataWrapper, mobileProvider);
				} else if (accountValidateDataWrapper.getValidateData()
						.getRequestedService().equalsIgnoreCase(VIEW_ACCOUNT)) {
					jsonlastObj = setViewAccountValidateDataResponse(jsonObj,
							ownerPortalUser,vehicleInfor,state);
				} else if (accountValidateDataWrapper.getValidateData()
						.getRequestedService().equalsIgnoreCase(MULTI_CHANNEL)) {
					jsonlastObj = setMultiChannelValidateDataResponse(jsonObj,
							ownerPortalUser, accountValidateDataWrapper);
				} else if (accountValidateDataWrapper.getValidateData()
						.getRequestedService().equalsIgnoreCase(CHANGE_PASSWORD)) {
					jsonlastObj = setChangePasswordValidateDataResponse();
				}
				LOG.info("Validate data JSON success response for the username = "+ userName);

				return Response.ok().status(200).entity(jsonlastObj.toString())
						.build();

			} else if (!isValidRequest || result.equalsIgnoreCase(FAILURE)|| !isvalidJSON || !isValidBrand) {
				LOG.info("Validate data JSON failed response for the username ="
						+ userName + "is" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			} else {

				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
						GENERAL_ERROR_DESCRIPTION);
				LOG.info("Account Validate data JSON failed response for the username ="
						+ userName + "is" + jsonFinalObj.toString());
				return Response.ok().status(400).entity(jsonFinalObj.toString())
						.build();
			}

		} catch (JsonParseException e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JSON request");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid JSON request");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the username =" + userName + "is"
					+ jsonFinalObj.toString()
					+ "IOException during ValidateData : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the username =" + userName + "is"
					+ jsonFinalObj.toString()
					+ "General Exception during ValidateData : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		
	}

	/**
	 * @author x178099
	 * @use To get the user information
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/getUser")
	public Response getUser(@HeaderParam("Brand") String brand,
			//@HeaderParam("UsrName") String usrName,
			String requestJson) throws JSONException {
		
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonPrsnObj = new JSONObject();
		GetUserWrapper getUserWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = false;
		boolean isValidRequest = true;
		boolean isValidState = true;
		boolean isvalidJSON = true;
		OwnerPortalUser validUser = null;
		//Set<OwnerPortalVehicle> vehicleInformation = new HashSet<OwnerPortalVehicle>();
		List<OwnerPortalVehicle> vehicleInfor = new ArrayList<OwnerPortalVehicle>();
		List<OwnerPortalUserVehicle> userVehicleInfo = null;
		OwnerPortalVehicle vehicleInfo = null;
		String vehicleMakeCode = null;
		List<OwnerPortalUserPhone> ownerPortalUserPhone = null;
		State stateName = null;
		String userName = "";
		String vin = "";
		boolean updateCheck = false;

		String result = "";
		MobileProvider mobileProvider = null;
		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				getUserWrapper = mapper.readValue(requestJson,
						GetUserWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(getUserWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(getUserWrapper
							.getGetUser())) {
						if (Utility.isObjectNotNullorNotEmpty(getUserWrapper
								.getGetUser().getPerson())) {

							isValidBrand = Util.isBrandNull(brand, jsonObj,
									jsonFinalObj);
							if (isValidBrand) {

								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										getUserWrapper.getGetUser().getPerson()
												.setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										getUserWrapper.getGetUser().getPerson()
												.setBrand(INFINITI);
									}

									String firstName = getUserWrapper
											.getGetUser().getPerson()
											.getFirstName();
									String lastName = getUserWrapper
											.getGetUser().getPerson()
											.getLastName();
									String addressLine1 = getUserWrapper
											.getGetUser().getPerson()
											.getAddress().getAddressLine1();
									String emailId = getUserWrapper
											.getGetUser().getPerson()
											.getEmail();
									String personHashId = getUserWrapper
											.getGetUser().getPerson()
											.getPersonHashId();
									String postalCode = getUserWrapper
											.getGetUser().getPerson()
											.getAddress().getPostalCode();
									String mobileNumber = getUserWrapper
											.getGetUser().getPerson()
											.getMobileNumber();
									String landLineNumber = getUserWrapper
											.getGetUser().getPerson()
											.getLandlineNumber();

									// x055765 include regioncode
									String regionCode = getUserWrapper
											.getGetUser().getPerson()
											.getAddress().getRegionCode();

									// 104026 added this if condition to handle
									// below case.
									// if Maritz didn't return Address, then we
									// should not throw error to Customer,
									// instead we should provide the data from
									// DB
									int addressCheck = 0;
									if (addressLine1 == null
											|| "".equals(addressLine1)) {
										LOG.info("Address is null from Maritz for the Request Json : "
												+ requestJson);
										addressLine1 = "Invalid Address";
										addressCheck = 1;
									}

									// Commented as we need not validate the data coming from Maritz
									//x497432
									// setting default as true
//									isValidRequest = validateRequest(firstName,
//											lastName, addressLine1, emailId,
//											personHashId, postalCode,
//											mobileNumber, landLineNumber,
//											jsonObj, jsonFinalObj, regionCode);
									isValidRequest = true;
									

									// 104026 making address back to null if
									// above if condition is executed
									if (addressCheck == 1) {
										addressLine1 = null;
									}

									if (isValidRequest) {
										LOG.info("Request is valid");

										userName = getUserWrapper.getGetUser()
												.getPerson().getEmail();
										//x055765 - Brand Segregation - send brand also
										validUser = userLocal.validateEmail(
												userName, personHashId,brand);
										
										//X497432
										//Mobile and Landline number checked for length and number
										//on success we update the user with the new number from Maritx
										if(landLineNumber != null){
											if(landLineNumber.length()== 10 &&
													landLineNumber.matches("[0-9]+")){
												BigDecimal ln = new BigDecimal(landLineNumber.trim());
												if(!(ln.intValue() == 0)){								
													validUser.setWorkPhoneNumber(ln);
													updateCheck = true;
												}
											}
										}
										if(mobileNumber != null){
											if(mobileNumber.length()== 10 &&
													mobileNumber.matches("[0-9]+")){
												BigDecimal mn = new BigDecimal(mobileNumber.trim());
												if(!(mn.intValue() == 0)){								
													validUser.setCellPhoneNumber(mn);
													updateCheck = true;
												}
											}
										}
										//on success we update the user with the new number from Maritx
										if(updateCheck){
											validUser = userLocal.updateUser(validUser);
										}
										
										if (!Utility
												.isObjectNotNullorNotEmpty(validUser)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

											result = FAILURE;
										} else {

											// x055765 change region to
											// regioncode
											if (Utility
													.isStringNotNullorNotEmpty(getUserWrapper
															.getGetUser()
															.getPerson()
															.getAddress()
															.getRegionCode())) {
												String userStateCode = getUserWrapper
														.getGetUser()
														.getPerson()
														.getAddress()
														.getRegionCode();
												String userStateName = getUserWrapper
														.getGetUser()
														.getPerson()
														.getAddress()
														.getRegion();
												LOG.info("regionCode"
														+ userStateCode);
												if (userStateCode.length() == 2) {
													stateName = userLocal
															.getStateCode(userStateCode);
													LOG.info("statekey"
															+ stateName
																	.getStateName());

													if (!Utility
															.isObjectNotNullorNotEmpty(stateName)) {
														Util.setFaultDataToJSON(
																jsonObj,
																jsonFinalObj,
																VALIDATION_FAILED_CODE,
																VALIDATION_FAILED_MESSAGE,
																INVALID_STATE_DESCRIPTION);
														isValidState = false;
													}
												} else {

													stateName = userLocal
															.getStateByStateName(userStateName);
													LOG.info("StateName= "
															+ stateName
																	.getStateName());
													if (!Utility
															.isObjectNotNullorNotEmpty(stateName)) {
														Util.setFaultDataToJSON(
																jsonObj,
																jsonFinalObj,
																VALIDATION_FAILED_CODE,
																VALIDATION_FAILED_MESSAGE,
																INVALID_STATE_DESCRIPTION);
														isValidState = false;
													}
												}
											} else if (!Utility
													.isStringNotNullorNotEmpty(getUserWrapper
															.getGetUser()
															.getPerson()
															.getAddress()
															.getRegion())) {
												stateName = userLocal
														.getStateByStateKey(validUser
																.getStateKey());
											}

											if (isValidState) {
														
														if (validUser.getMobileCarrNm() != null) {

															mobileProvider = userLocal
																	.getProviderByProviderCode(validUser, validUser.getMobileCarrNm());
														}
												String userProfileId = validUser
														.getUserProfileId();
												//Owners CI project - added logic to retrieve user phone disclaimer details
												ownerPortalUserPhone = userLocal
														.getUserPhoneDetails(userProfileId); 
												
												//x055765 - Show only 6 vehicles in garage page - OS 1216-Owners2.0
												userVehicleInfo = vehicleLocal
														.getOwnerVehicleDetails(userProfileId);

												if (Utility
														.isObjectNotNullorNotEmpty(userVehicleInfo)) {

													Iterator<OwnerPortalUserVehicle> iterator = userVehicleInfo
															.iterator();
													OwnerPortalUserVehicle userVehicle;
													while (iterator.hasNext()) {
														userVehicle = iterator
																.next();
														if (Utility
																.isObjectNotNullorNotEmpty(userVehicle)) {
															LOG.info("vehicle available in user account is "
																	+ userVehicle
																			.getOwnerPortalUserVehiclePK()
																			.getVin());
															vin = userVehicle
																	.getOwnerPortalUserVehiclePK()
																	.getVin();
															vehicleInfo = vehicleLocal
																	.fetchVehicleInfoFromVehicleList(vin);

															
															vehicleMakeCode = vehicleInfo
																	.getVehicleMakeCode();
														
																List<OwnerPortalVehicle> nissanAndInfinitiVins = vehicleLocal
																		.fetchOnlyNissanVinsAndVin(
																				vehicleMakeCode,
																				vin);
																
																vehicleInfor
																		.addAll(nissanAndInfinitiVins);

														
														}
													}
												}
												result = SUCCESS;
											}
										}
									}

								} else {
									LOG.info("Brand is neither N nor I");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VEHICLE_INVALID_BRAND_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
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
			
			
			
			if (result.equalsIgnoreCase(SUCCESS) && isValidRequest) {

				if (Utility.isStringNotNullorNotEmpty(getUserWrapper.getGetUser()
						.getPerson().getRequestedService())) {

					if (getUserWrapper.getGetUser().getPerson()
							.getRequestedService().equalsIgnoreCase(MULTI_CHANNEL)) {

						jsonPrsnObj = setViewAccountMultiChannelResponse(jsonObj,
								validUser, ownerPortalUserPhone, stateName,
								getUserWrapper, mobileProvider);
						LOG.info("get User of Multi Channel JSON success response for the user"
								+ userName + "is" + jsonPrsnObj.toString());
						return Response.ok().status(200)
								.entity(jsonPrsnObj.toString()).build();
					}
				} else {

					jsonPrsnObj = setViewAccountGetUserResponse(jsonObj, validUser,
							ownerPortalUserPhone, stateName, getUserWrapper, brand,
							vehicleInfor, mobileProvider);
				}

				LOG.info("Get User JSON success response for the user = "
						+ userName );
				return Response.ok().status(200).entity(jsonPrsnObj.toString())
						.build();

			} else if (isValidBrand || result.equalsIgnoreCase(FAILURE)
					|| !isValidRequest || !isValidState || !isvalidJSON) {
				LOG.info("get User of view account JSON failure response for the user"
						+ userName + "is" + jsonFinalObj.toString());
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
			LOG.error(" Response to Fuse for the username =" + userName + "is"
					+ jsonFinalObj.toString()
					+ "JsonParseException during GetUser : ", e);
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.error(" Response to Fuse for the username =" + userName + "is"
					+ jsonFinalObj.toString()
					+ "JsonMappingException during GetUser : ", e);
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.error(
					" Response to Fuse for the username =" + userName + "is"
							+ jsonFinalObj.toString()
							+ "IOException during GetUser : ", e);
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the username =" + userName + "is"
					+ jsonFinalObj.toString()
					+ " General Exception during GetUser : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}

		
	}

	/**
	 * @author x178099
	 * @use To update user details
	 * @param brand
	 * @param requestJson
	 * @return
	 * @throws JSONException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/1.0/updateUser")
	public Response updateUser(@HeaderParam("Brand") String brand,
			String requestJson) throws JSONException {
		LOG.info("Update account updateUser JSON request = " + requestJson);
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		JSONObject jsonPrsnObj = new JSONObject();
		SaveUserWrapper saveUserWrapper = null;

		ObjectMapper mapper = new ObjectMapper();
		boolean isValidBrand = false;
		boolean isValidRequest = true;
		boolean isValidState = true;
		boolean isvalidJSON=true;
		OwnerPortalUser validUser = null;
		List<OwnerPortalUserVehicle> userVehicleInfo = null;
		//Set<OwnerPortalVehicle> vehicleInformation = new HashSet<OwnerPortalVehicle>();
		List<OwnerPortalVehicle> vehicleInfor = new ArrayList<OwnerPortalVehicle>();
		OwnerPortalVehicle vehicleInfo = null;
		List<OwnerPortalUserPhone> ownerPortalUserPhone = null;
		String vehicleMakeCode = null;
		String vin = "";
		State stateName = null;
		MobileProvider mobileProvider = null;
		String userName = "";
		String mobileCarrierName = "";
		String result = "";

		try {
			if (Utility.isStringNotNullorNotEmpty(requestJson)) {
				saveUserWrapper = mapper.readValue(requestJson,
						SaveUserWrapper.class);
				if (Utility.isObjectNotNullorNotEmpty(saveUserWrapper)) {
					if (Utility.isObjectNotNullorNotEmpty(saveUserWrapper
							.getSaveUser())) {
						if (Utility.isObjectNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson())) {
							isValidBrand = Util.isBrandNull(brand, jsonObj,
									jsonFinalObj);

							if (isValidBrand) {

								if (brand.equalsIgnoreCase(BRAND_INFINITI)
										|| brand.equalsIgnoreCase(BRAND_NISSAN)) {

									if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
										saveUserWrapper.getSaveUser()
												.getPerson().setBrand(NISSAN);
									} else if (brand
											.equalsIgnoreCase(BRAND_INFINITI)) {
										saveUserWrapper.getSaveUser()
												.getPerson().setBrand(INFINITI);
									}
									String firstName = saveUserWrapper
											.getSaveUser().getPerson()
											.getFirstName();
									String lastName = saveUserWrapper
											.getSaveUser().getPerson()
											.getLastName();
									String addressLine1 = saveUserWrapper
											.getSaveUser().getPerson()
											.getAddress().getAddressLine1();
									String emailId = saveUserWrapper
											.getSaveUser().getPerson()
											.getEmail();
									String personHashId = saveUserWrapper
											.getSaveUser().getPerson()
											.getPersonHashId();
									String postalCode = saveUserWrapper
											.getSaveUser().getPerson()
											.getAddress().getPostalCode();
									String mobileNumber = saveUserWrapper
											.getSaveUser().getPerson()
											.getMobileNumber();
									String landLineNumber = saveUserWrapper
											.getSaveUser().getPerson()
											.getLandlineNumber();
									// x055765 include regioncode
									String regionCode = saveUserWrapper
											.getSaveUser().getPerson()
											.getAddress().getRegionCode();

									isValidRequest = validateRequest(firstName,
											lastName, addressLine1, emailId,
											personHashId, postalCode,
											mobileNumber, landLineNumber,
											jsonObj, jsonFinalObj, regionCode);

									if (isValidRequest) {
										LOG.info("Request is valid");

										userName = saveUserWrapper
												.getSaveUser().getPerson()
												.getEmail();
										
										//x055765 - brand segregation - so sending brand also
										validUser = userLocal.validateEmail(
												userName, personHashId,brand);
										if (!Utility
												.isObjectNotNullorNotEmpty(validUser)) {
											Util.setFaultDataToJSON(
													jsonObj,
													jsonFinalObj,
													VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
													VALIDATION_FAILED_MESSAGE,
													VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

											result = FAILURE;
										} else {
											// x055765 region and region code
											// change
											if (Utility
													.isStringNotNullorNotEmpty(saveUserWrapper
															.getSaveUser()
															.getPerson()
															.getAddress()
															.getRegionCode())) {
												String userStateCode = saveUserWrapper
														.getSaveUser()
														.getPerson()
														.getAddress()
														.getRegionCode();
												String userStateName = saveUserWrapper
														.getSaveUser()
														.getPerson()
														.getAddress()
														.getRegion();
												LOG.info("regionCode is  :"
														+ userStateCode);
												if (userStateCode.length() == 2) {
													// find state by state code
													stateName = userLocal
															.getStateCode(userStateCode);
													LOG.info("state::::"
															+ stateName
																	.getStateKey());

													if (!Utility
															.isObjectNotNullorNotEmpty(stateName)) {
														Util.setFaultDataToJSON(
																jsonObj,
																jsonFinalObj,
																VALIDATION_FAILED_CODE,
																VALIDATION_FAILED_MESSAGE,
																INVALID_STATE_DESCRIPTION);
														isValidState = false;
													}
												} else {
													LOG.info("RegionCode is null");
													stateName = userLocal
															.getStateByStateName(userStateName);
													if (!Utility
															.isObjectNotNullorNotEmpty(stateName)) {
														Util.setFaultDataToJSON(
																jsonObj,
																jsonFinalObj,
																VALIDATION_FAILED_CODE,
																VALIDATION_FAILED_MESSAGE,
																INVALID_STATE_DESCRIPTION);
														isValidState = false;
													}
												}

											} else if (!Utility
													.isStringNotNullorNotEmpty(saveUserWrapper
															.getSaveUser()
															.getPerson()
															.getAddress()
															.getRegionCode())) {
												stateName = userLocal
														.getStateByStateKey(validUser
																.getStateKey());
												LOG.info("state  ::::"
														+ stateName
																.getStateKey());
											}

											if (isValidState) {

												ownerPortalUserPhone = userLocal
														.getUserPhoneDetails(validUser
																.getUserProfileId());
												mobileCarrierName = saveUserWrapper
														.getSaveUser()
														.getPerson()
														.getMobileNetworkOperator();
												
											
												
												if (Utility
														.isStringNotNullorNotEmpty(mobileCarrierName)) {
													mobileProvider = userLocal
															.getProviderByProviderName(mobileCarrierName);
													LOG.info("Mobile Carrier Name is"
															+ mobileProvider
																	.getMobileProviderName());
												} else {
													/*LOG.info("User Phone is::"
															+ validUser.getMobileCarrNm());*/
													if (validUser.getMobileCarrNm() != null) {
														LOG.info("Inside get Mobile carrier Name");
														mobileProvider = userLocal
																.getProviderByProviderCode(validUser,validUser.getMobileCarrNm());
														LOG.info("After get Mobile carrier Name");
													}
												}
											
												
												validUser = userLocal
														.updateUser(
																saveUserWrapper,
																validUser,
																ownerPortalUserPhone,
																stateName,
																mobileProvider);
												LOG.info("After Mobile Carrier Name is");
												String userProfileId = validUser
														.getUserProfileId();
												//x055765 - Update and Show only 6 vehicles in garage page - OS 1216-Owners2.0
												userVehicleInfo = vehicleLocal
														.getOwnerVehicleDetails(userProfileId);
												if (Utility
														.isObjectNotNullorNotEmpty(userVehicleInfo)) {
													Iterator<OwnerPortalUserVehicle> iterator = userVehicleInfo
															.iterator();
													OwnerPortalUserVehicle userVehicle;

													while (iterator.hasNext()) {
														userVehicle = iterator
																.next();
														if (Utility
																.isObjectNotNullorNotEmpty(userVehicle)) {
															LOG.info("vehicle available in user account");
															vin = userVehicle
																	.getOwnerPortalUserVehiclePK()
																	.getVin();
															vehicleInfo = vehicleLocal
																	.fetchVehicleInfoFromVehicleList(vin);
															
															// x055765 -
															// change-if the
															// brand is
															// nissan/infiniti
															// it should display
															// nissan/infiniti
															// vins alone
															vehicleMakeCode = vehicleInfo
																	.getVehicleMakeCode();
														
																List<OwnerPortalVehicle> nissanAndInfinitiVins = vehicleLocal
																		.fetchOnlyNissanVinsAndVin(
																				vehicleMakeCode,
																				vin);
																
																vehicleInfor
																		.addAll(nissanAndInfinitiVins);
																
															/*vehicleInformation
																	.addAll(vehicleInfor);*/
														}
													}

												}
												result = SUCCESS;
											}
										}
									}

								} else {

									LOG.info("Brand is neither N nor I");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											VEHICLE_INVALID_BRAND_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_BRAND_DESCRIPTION);
									isValidBrand = false;
								}
							}else{
								LOG.info("Brand is neither N nor I");
								isValidBrand = false;
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

				jsonPrsnObj = setUpdateAccountSaveUserResponse(jsonObj, validUser,
						ownerPortalUserPhone, stateName, saveUserWrapper,
						vehicleInfor, mobileProvider);

				LOG.info("Update User of update account JSON success response for the user"
						+ userName );
				return Response.ok().status(200).entity(jsonPrsnObj.toString())
						.build();

			} else if (!isValidBrand || result.equalsIgnoreCase(FAILURE) ||!isValidState || !isvalidJSON) {
				LOG.info("Inside Update User JSON failed response for the user"
						+ userName + "is" + jsonFinalObj.toString());
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
			LOG.info("Invalid json request");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (JsonMappingException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid json request");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (IOException e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
					INVALID_JSON_REQUEST_DESCRIPTION);
			LOG.info("Invalid json request");
			return Response.ok().status(400).entity(jsonFinalObj.toString())
					.build();
		} catch (Exception e) {

			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			LOG.error(" Response to Fuse for the user" + userName + "is"
					+ jsonFinalObj.toString()
					+ "General Exception during Update User : ", e);
			return Response.ok().status(500).entity(jsonFinalObj.toString())
					.build();
		}
		
	}

	/**
	 * @author x178099
	 * @use To validate user request for update user
	 * @param accountValidateDataWrapper
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean ValidateUserRequest(
			ValidateDataAccountWrapper accountValidateDataWrapper,
			JSONObject jsonObj, JSONObject jsonFinalObj) {

		boolean response = true;
		OwnerPortalUser emailValid = null;

		if (Utility.isObjectNotNullorNotEmpty(accountValidateDataWrapper)) {
			if (Utility.isObjectNotNullorNotEmpty(accountValidateDataWrapper
					.getValidateData())) {
				if (!Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getRequestedService())) {
					LOG.info("Requested Service is null ");

					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					response = false;
				} else if (!(accountValidateDataWrapper.getValidateData()
						.getRequestedService().equalsIgnoreCase(UPDATE_ACCOUNT)
						|| accountValidateDataWrapper.getValidateData()
								.getRequestedService()
								.equalsIgnoreCase(VIEW_ACCOUNT)
						|| accountValidateDataWrapper.getValidateData()
								.getRequestedService()
								.equalsIgnoreCase(MULTI_CHANNEL)
						|| accountValidateDataWrapper.getValidateData()
								.getRequestedService()
								.equalsIgnoreCase(CHANGE_EMAIL)
						|| accountValidateDataWrapper.getValidateData()
								.getRequestedService()
								.equalsIgnoreCase(CHANGE_PASSWORD)
						|| accountValidateDataWrapper.getValidateData()
								.getRequestedService()
								.equalsIgnoreCase(CHANGE_EMAIL_CONFIRMATION) || accountValidateDataWrapper
						.getValidateData().getRequestedService()
						.equalsIgnoreCase(PROOF_OF_OWNERSHIP))) {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
							INVALID_JSON_REQUEST_DESCRIPTION);
					response = false;
				} else {
					if (accountValidateDataWrapper.getValidateData()

					.getRequestedService().contains(UPDATE_ACCOUNT)) {
						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getFirstName())) {
							LOG.info("First Name is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_FIRSTNAME_CODE,
									VALIDATION_FAILED_MESSAGE,
									INVALID_FIRST_NAME_DESCRIPTION);

							response = false;
						} else if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getLastName())) {
							LOG.info("Last Name is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_LASTNAME_CODE,
									VALIDATION_FAILED_MESSAGE,
									INVALID_LAST_NAME_DESCRIPTION);
							response = false;
						} else if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getAddress()
										.getAddressLine1())) {
							LOG.info("Address Line1 is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_ADDRESSLINE1_CODE,
									VALIDATION_FAILED_MESSAGE,
									INVALID_ADDRESSLINE1_DESCRIPTION);

							response = false;
						}
						//commented by x116202 - Removing special char check - to keep inline with legacy OP flow
						/*if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getFirstName())) {
							if (!accountValidateDataWrapper.getValidateData()
									.getFirstName().matches("^[a-zA-Z]+$")) {
								LOG.info("Invalid First Name");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_FIRSTNAME_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_FIRSTNAME_FORMAT_DESCRIPTION);

								response = false;
							}
						}
						if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getMiddleName())) {
							if (!accountValidateDataWrapper.getValidateData()
									.getMiddleName().matches("^[a-zA-Z]+$")) {
								LOG.info("Invalid Middle Name");

								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_MIDDLENAME_FORMAT_DESCRIPTION);

								response = false;
							}
						}

						if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getLastName())) {
							if (!accountValidateDataWrapper.getValidateData()
									.getLastName().matches("^[a-zA-Z]+$")) {
								LOG.info("Invalid Last Name");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_LASTNAME_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_LASTNAME_FORMAT_DESCRIPTION);

								response = false;
							}
						}
						if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getSecondLastName())) {
							if (!accountValidateDataWrapper.getValidateData()
									.getSecondLastName().matches("^[a-zA-Z]+$")) {
								LOG.info("Invalid SecondLastName");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_SECONDLASTNAME_FORMAT_DESCRIPTION);

								response = false;
							}
						}*/
						if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getAddress()
										.getPostalCode())) {
							if (accountValidateDataWrapper.getValidateData()
									.getAddress().getPostalCode().length() < 5
									|| accountValidateDataWrapper
											.getValidateData().getAddress()
											.getPostalCode().length() > 5) {
								LOG.info("Invalid Postal Code length ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_POSTALCODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_POSTAL_CODE_LENGTH_DESCRIPTION);
								response = false;
							} else if (!accountValidateDataWrapper
									.getValidateData().getAddress()
									.getPostalCode().matches("[0-9]+")) {
								LOG.info("Invalid Postal Code format ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_POSTALCODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_POSTAL_CODE_FORMAT_DESCRIPTION);
								response = false;
							}
						}
						if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getLandlineNumber())) {
							if (accountValidateDataWrapper.getValidateData()
									.getLandlineNumber().length() < 10
									|| accountValidateDataWrapper
											.getValidateData()
											.getLandlineNumber().length() > 10) {
								LOG.info("Invalid landline number length ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_LANDLINE_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_LANDLINE_LENGTH_DESCRIPTION);
								response = false;
							} else if (!accountValidateDataWrapper
									.getValidateData().getLandlineNumber()
									.matches("[0-9]+")) {
								LOG.info("Invalid landline number format ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_LANDLINE_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_LANDLINE_NUMBER_FORMAT_DESCRIPTION);
								response = false;
							}
						}
						if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getMobileNumber())) {
							LOG.info("inside mobilenumber");
							if (accountValidateDataWrapper.getValidateData()
									.getMobileNumber().length() < 10
									|| accountValidateDataWrapper
											.getValidateData()
											.getMobileNumber().length() > 10) {
								LOG.info("Invalid mobile number length ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_MOBILENUMBER_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_MOBILE_NUMBER_LENGTH_DESCRIPTION);
								response = false;
							} else if (!accountValidateDataWrapper
									.getValidateData().getMobileNumber()
									.matches("[0-9]+")) {
								LOG.info("Invalid mobile number format ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_MOBILENUMBER_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_MOBILE_NUMBER_FORMAT_DESCRIPTION);
								response = false;
							}
						}

					} // x055765 - includes all preferences
						// Disclaimer Preferences
					if (Utility
							.isObjectNotNullorNotEmpty(accountValidateDataWrapper
									.getValidateData()
									.getDisclaimerPreferences())) {
						LOG.info("check1");
						if (!Utility
								.isObjectNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData()
										.getDisclaimerPreferences()
										.getWorkPhone())
								|| !Utility
										.isObjectNotNullorNotEmpty(accountValidateDataWrapper
												.getValidateData()
												.getDisclaimerPreferences()
												.getMobilePhone())) {
							LOG.info("Invalid Disclaimer Preferences format ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_DISCLAIMER_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							response = false;

						} else if (Utility
								.isObjectNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData()
										.getDisclaimerPreferences()
										.getWorkPhone())) {
							int wSize = accountValidateDataWrapper
									.getValidateData()
									.getDisclaimerPreferences().getWorkPhone()
									.size();
							LOG.info("workphonesize" + wSize);
							if (!(wSize == 0)) {
								LOG.info("wSize");
								LOG.info("wSize" + wSize);
								if (wSize == 2) {
									if ((!accountValidateDataWrapper
											.getValidateData()
											.getDisclaimerPreferences()
											.getWorkPhone()
											.contains("autodial"))
											|| (!accountValidateDataWrapper
													.getValidateData()
													.getDisclaimerPreferences()
													.getWorkPhone()
													.contains("sms"))) {
										LOG.info("Invalid Disclaimer Preferences format WorkSize 2");
										Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE, INVALID_DISCLAIMER_REQUEST_ERROR_MSG,
												INVALID_JSON_REQUEST_DESCRIPTION);
										response = false;
									}
								}
								if (wSize == 1) {
									if ((!accountValidateDataWrapper
											.getValidateData()
											.getDisclaimerPreferences()
											.getWorkPhone()
											.contains("autodial"))
											&& (!accountValidateDataWrapper
													.getValidateData()
													.getDisclaimerPreferences()
													.getWorkPhone()
													.contains("sms"))) {
										LOG.info("Invalid Disclaimer Preferences format Worksize 1");
										Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE, INVALID_DISCLAIMER_REQUEST_ERROR_MSG,
												INVALID_JSON_REQUEST_DESCRIPTION);
										response = false;
									}
								}

							}
						} else if (Utility
								.isObjectNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData()
										.getDisclaimerPreferences()
										.getMobilePhone())) {
							int mSize = accountValidateDataWrapper
									.getValidateData()
									.getDisclaimerPreferences()
									.getMobilePhone().size();
							LOG.info("workphonesize" + mSize);
							if (!(mSize == 0)) {
								LOG.info("mSize is not zero");
								LOG.info("mSize" + mSize);
								if (mSize == 2) {
									if ((!accountValidateDataWrapper
											.getValidateData()
											.getDisclaimerPreferences()
											.getMobilePhone()
											.contains("autodial"))
											|| (!accountValidateDataWrapper
													.getValidateData()
													.getDisclaimerPreferences()
													.getMobilePhone()
													.contains("sms"))) {
										LOG.info("Invalid Disclaimer Preferences format MobileSize 2");
										Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE, INVALID_DISCLAIMER_REQUEST_ERROR_MSG,
												INVALID_JSON_REQUEST_DESCRIPTION);
										response = false;
									}
								}
								if (mSize == 1) {
									if ((!accountValidateDataWrapper
											.getValidateData()
											.getDisclaimerPreferences()
											.getMobilePhone()
											.contains("autodial"))
											&& (!accountValidateDataWrapper
													.getValidateData()
													.getDisclaimerPreferences()
													.getMobilePhone()
													.contains("sms"))) {
										LOG.info("Invalid Disclaimer Preferences format MobileSize 1");
										Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
												GENERAL_ERROR_FAULT_CODE, INVALID_DISCLAIMER_REQUEST_ERROR_MSG,
												INVALID_JSON_REQUEST_DESCRIPTION);
										response = false;
									}
								}

							}
						}
					}

					// Marketing Preferences
					if (Utility
							.isObjectNotNullorNotEmpty(accountValidateDataWrapper
									.getValidateData().getMarketingPreference())) {
						if (!(Utility
								.isObjectNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData()
										.getMarketingPreference()
										.getNewsletter())
								|| Utility
										.isObjectNotNullorNotEmpty(accountValidateDataWrapper
												.getValidateData()
												.getMarketingPreference()
												.getProductOffers())
								|| Utility
										.isObjectNotNullorNotEmpty(accountValidateDataWrapper
												.getValidateData()
												.getMarketingPreference()
												.getServiceOffers())
								|| Utility
										.isObjectNotNullorNotEmpty(accountValidateDataWrapper
												.getValidateData()
												.getMarketingPreference()
												.getScheduledMaintenance()) || Utility
									.isObjectNotNullorNotEmpty(accountValidateDataWrapper
											.getValidateData()
											.getMarketingPreference()
											.getFeedback()))) {
							LOG.info("Invalid Marketing Preferences format ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE, INVALID_MARKETING_REQUEST_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);
							response = false;
						}
						int nSize = accountValidateDataWrapper
								.getValidateData().getMarketingPreference()
								.getNewsletter().size();
						int pSize = accountValidateDataWrapper
								.getValidateData().getMarketingPreference()
								.getProductOffers().size();
						int sSize = accountValidateDataWrapper
								.getValidateData().getMarketingPreference()
								.getServiceOffers().size();
						int schSize = accountValidateDataWrapper
								.getValidateData().getMarketingPreference()
								.getScheduledMaintenance().size();
						int fSize = accountValidateDataWrapper
								.getValidateData().getMarketingPreference()
								.getFeedback().size();
						if ((nSize > 5) || (pSize > 5) || (sSize > 5)
								|| (schSize > 5) || (fSize > 5)) {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);
							response = false;
						}
					} else if (accountValidateDataWrapper.getValidateData()

					.getRequestedService().contains(CHANGE_PASSWORD)) {

						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getNewPassword())) {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_AUTH_CODE,
									VALIDATION_FAILED_MESSAGE,
									INVALID_AUTH_DESCRIPTION);
							response = false;
						} else if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getNewPassword())) {
							// 104026 added usernamePasswordMatch to verify
							// whether 3 or more consecutive letters of email in
							// password
							if (!checkPasswordPolicy(
									accountValidateDataWrapper
									.getValidateData()
									.getNewPassword())
									
									/*(!accountValidateDataWrapper
									.getValidateData()
									.getNewPassword()
									.matches(
											"^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*])).{0,100}$"))*/
									
									|| (usernamePasswordMatch(
											accountValidateDataWrapper
													.getValidateData()
													.getEmail(),
											accountValidateDataWrapper
													.getValidateData()
													.getNewPassword()))) {
								
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_AUTH_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_AUTH_POLICY_DESCRIPTION);
								response = false;
							}
						}

						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getCurrentPassword())) {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_AUTH_CODE,
									VALIDATION_FAILED_MESSAGE,
									INVALID_AUTH_DESCRIPTION);
							response = false;
						} else if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getEmail())) {
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_EMAIL_CODE,
									VALIDATION_FAILED_MESSAGE,
									VEHICLE_INVALID_EMAIL_DESCRIPTION);
							response = false;
						}
					} else if (accountValidateDataWrapper.getValidateData()

					.getRequestedService().equals(CHANGE_EMAIL)) {

						// Update email changes done by x178099

						if (!(Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getNewEmail()))) {

							LOG.info("New Email is null");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_EMAIL_CODE,
									VALIDATION_FAILED_MESSAGE,
									VEHICLE_INVALID_EMAIL_DESCRIPTION);

							response = false;

						} else if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getNewEmail())) {
							if (!(accountValidateDataWrapper.getValidateData()
									.getNewEmail().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}$"))){
									//.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"))){
								LOG.info("Invalid Email Format ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_EMAIL_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_EMAIL_FORMAT_DESCRIPTION);
								response = false;
							} else {
								emailValid = userLocal.validateUser(
										accountValidateDataWrapper
												.getValidateData()
												.getNewEmail(),
										accountValidateDataWrapper
												.getValidateData().getBrand());
								if (Utility
										.isObjectNotNullorNotEmpty(emailValid)) {
									LOG.info("Email provided for update already exist in system");
									Util.setFaultDataToJSON(jsonObj,
											jsonFinalObj,
											EMAIL_ALREADY_EXIST_CODE,
											EMAIL_ALREADY_EXIST_MESSAGE,
											EMAIL_ALREADY_EXIST_DESCRIPTION);

									response = false;
								}
							}

						}

					} else if (accountValidateDataWrapper.getValidateData()

					.getRequestedService().equals(CHANGE_EMAIL_CONFIRMATION)) {

						// change email confirmation done by x178099

						if (!(Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getEmail()))) {

							LOG.info(" Email is null");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_EMAIL_CODE,
									VALIDATION_FAILED_MESSAGE,
									VEHICLE_INVALID_EMAIL_DESCRIPTION);

							response = false;

						} else if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getEmail())) {
							if (!(accountValidateDataWrapper.getValidateData()
									.getEmail().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}$"))){
									//.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))) {
								LOG.info("Invalid Email Format ");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										VALIDATION_FAILED_EMAIL_CODE,
										VALIDATION_FAILED_MESSAGE,
										INVALID_EMAIL_FORMAT_DESCRIPTION);
								response = false;
							} else {
								emailValid = userLocal.validateUser(
										accountValidateDataWrapper
												.getValidateData().getEmail(),
										accountValidateDataWrapper
												.getValidateData().getBrand());
								if (!Utility
										.isObjectNotNullorNotEmpty(emailValid)) {
									LOG.info("Email provided for update does not exist in system");
									Util.setFaultDataToJSON(
											jsonObj,
											jsonFinalObj,
											VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
											VALIDATION_FAILED_MESSAGE,
											VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

									response = false;
								}
							}

						}
						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getJwt())) {
							LOG.info("JWT is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									GENERAL_ERROR_MESSAGE,
									GENERAL_ERROR_DESCRIPTION);

							response = false;
						}
						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getCode())) {
							LOG.info("confirmation code is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									VALIDATION_FAILED_INVALID_CODE,
									VALIDATION_FAILED_MESSAGE,
									CONFIRMATION_CODE_VALIDATION_FAILED);
							response = false;
						}

					} else if (accountValidateDataWrapper.getValidateData()

					.getRequestedService().contains(MULTI_CHANNEL)) {

						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getLinkReferenceId())) {
							LOG.info("Link reference id  is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									INVALID_LINK_REFERENCE_CODE,
									VALIDATION_FAILED_MESSAGE,
									LINK_REFERENCEID_NULL_DESCRIPTION);
							response = false;
						}

					}
					if (accountValidateDataWrapper.getValidateData()
							.getRequestedService()
							.equalsIgnoreCase(UPDATE_ACCOUNT)
							|| accountValidateDataWrapper.getValidateData()
									.getRequestedService()
									.equalsIgnoreCase(VIEW_ACCOUNT)) {
						if (!Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getJwt())) {
							LOG.info("JWT is null ");
							Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
									GENERAL_ERROR_FAULT_CODE,
									INVALID_JWT_ERROR_MSG,
									INVALID_JSON_REQUEST_DESCRIPTION);

							response = false;
						} else if (Utility
								.isStringNotNullorNotEmpty(accountValidateDataWrapper
										.getValidateData().getJwt())) {

							int counter = 0;
							for (int i = 0; i < accountValidateDataWrapper
									.getValidateData().getJwt().length(); i++) {
								if (accountValidateDataWrapper
										.getValidateData().getJwt().charAt(i) == '.') {
									counter++;
								}
							}

							LOG.info("Count of '.' in JWT" + counter);
							if (counter == 2) {

								LOG.info("Valid JWT");

							} else {
								LOG.info("Invalid JWT format");
								Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
										GENERAL_ERROR_FAULT_CODE,
										INVALID_JWT_ERROR_MSG,
										INVALID_JSON_REQUEST_DESCRIPTION);
								response = false;
							}
						}
					}

				}
			}
		}

		return response;

	}

	/**
	 * @author x178099
	 * @use To accumulate json response for change email validatedata endpoint
	 * @param jsonObj
	 * @param isValidUser
	 * @param state
	 * @param phoneDetails
	 * @param mobileProvider
	 * @param validateDataWrapper
	 * @return
	 */
	private JSONObject setChangeEmailValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,

			ValidateDataAccountWrapper accountValidateDataWrapper,
			MobileProvider mobileProvider) {
		String landLineNumber = "";
		String mobileNumber = "";
		String autoDialWork = "";
		String smsWork = "";
		String autoDialMobile = "";
		String smsMobile = "";
		String mobileCarrierCode="";
		String autoDial = "autodial";
		String sms = "sms";
		int autoDialAlwdWork = 0;
		int smsAlwdWork = 0;
		int autoDialAlwdMobile = 0;
		int smsAlwdMobile = 0;
		JSONObject response = null;
		try {

			 landLineNumber = validUser.getWorkPhoneNumber()+"";
			 mobileNumber = validUser.getCellPhoneNumber()+"";
			 mobileCarrierCode = validUser.getMobileCarrNm();
			
			if(ownerPortalUserPhone!=null && ownerPortalUserPhone.size()>0){
				autoDialWork = ownerPortalUserPhone.get(0).getWrkPhnAutoDilrOptIn();
				smsWork = ownerPortalUserPhone.get(0).getWrkPhnSmsOptIn();
				autoDialMobile = ownerPortalUserPhone.get(0).getMblPhnAutoDilrOptIn();
				smsMobile = ownerPortalUserPhone.get(0).getMblPhnSmsOptIn();
				autoDialAlwdWork = Integer.parseInt(autoDialWork);
				smsAlwdWork = Integer.parseInt(smsWork);
				autoDialAlwdMobile = Integer.parseInt(autoDialMobile);
				smsAlwdMobile = Integer.parseInt(smsMobile);
			}
			
			LOG.info("Success mobileCarrierCode: " + mobileCarrierCode);

			JSONObject jsonAdObj = new JSONObject();
			JSONObject jsonOptObj = new JSONObject();
			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			JSONArray jsonDisclPrefObjWork = new JSONArray();
			JSONArray jsonDisclPrefObjMobile = new JSONArray();
			jsonObj.accumulate("cdiid",
					validUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("personHashId",
					validUser.getPersonalHashId());
			jsonObj.accumulate("email", validUser.getEmailId());
			if (Utility
					.isStringNotNullorNotEmpty(accountValidateDataWrapper
							.getValidateData().getNewEmail())) {
				jsonObj.accumulate("newEmail", accountValidateDataWrapper
						.getValidateData().getNewEmail());
			}
			jsonObj.accumulate("firstName", validUser.getFirstName());
			jsonObj.accumulate("lastName", validUser.getLastName());
			jsonAdObj
					.accumulate("addressLine1", validUser.getAddressText());
			// x055765 region and regioncode change
			jsonAdObj.accumulate("region", state.getStateName());
			jsonAdObj.accumulate("regionCode", state.getStateCode());
			jsonAdObj.accumulate("country", validUser.getCountryCode());
			jsonAdObj.accumulate("postalCode", validUser.getPostalCode());
			jsonAdObj.accumulate("city", validUser.getCityName());
			jsonObj.accumulate("address", jsonAdObj);
			jsonObj.accumulate("landlineNumber", landLineNumber);
			jsonObj.accumulate("mobileNumber", mobileNumber);
			if (Utility.isObjectNotNullorNotEmpty(mobileProvider)) {
				jsonObj.accumulate("mobileNetworkOperator",
						mobileProvider.getMobileProviderName());
			}
			

		
			if (autoDialAlwdWork == 1) {
				jsonDisclPrefObjWork.put(autoDial);
			}
			if (smsAlwdWork == 1) {
				jsonDisclPrefObjWork.put(sms);
			}
			if (autoDialAlwdMobile == 1) {
				jsonDisclPrefObjMobile.put(autoDial);
			}
			if (smsAlwdMobile == 1) {
				jsonDisclPrefObjMobile.put(sms);
			}

			jsonOptObj.put("workPhone", jsonDisclPrefObjWork);
			jsonOptObj.put("mobilePhone", jsonDisclPrefObjMobile);
			jsonObj.accumulate("disclaimerPreferences", jsonOptObj);
		
			
			jsonObj.accumulate("language", validUser.getLanguageCode());
			jsonObj.accumulate("profileChange", TRUE);
			jsonObj.accumulate("source", CUSTOMER);
			jsonPrsnObj.accumulate("person", jsonObj);
			jsonlastObj.accumulate("validateData", jsonPrsnObj);

			response = jsonlastObj;
			

		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateAccountValidateDataResponse",
					e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To accumulate json response for update account validatedata endpoint
	 * @param jsonObj
	 * @param isValidUser
	 * @param state
	 * @param phoneDetails
	 * @param validateDataWrapper
	 * @return
	 */
	private JSONObject setUpdateAccountValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,

			ValidateDataAccountWrapper accountValidateDataWrapper,
			MobileProvider mobileProvider) {
		JSONObject response = null;
		try {

			String landLineNumber = validUser.getWorkPhoneNumber()+"";
			String mobileNumber = validUser.getCellPhoneNumber()+"";
			String mobileCarrierCode = validUser.getMobileCarrNm();
			
				JSONObject jsonAdObj = new JSONObject();
				// x566325 - includes all preferences
				JSONObject jsonDisclPrefObj = new JSONObject();
				JSONObject jsonMarkPrefObj = new JSONObject();
				JSONObject jsonPrsnObj = new JSONObject();
				JSONObject jsonlastObj = new JSONObject();
				jsonObj.accumulate("cdiid",
						validUser.getCustomerDataIntegrationId());
				jsonObj.accumulate("personHashId",
						validUser.getPersonalHashId());
				jsonObj.accumulate("email", validUser.getEmailId());

				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getFirstName())) {
					jsonObj.accumulate("firstName", accountValidateDataWrapper
							.getValidateData().getFirstName());
				} else {
					jsonObj.accumulate("firstName", validUser.getFirstName());
				}
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getLastName())) {
					jsonObj.accumulate("lastName", accountValidateDataWrapper
							.getValidateData().getLastName());
				} else {
					jsonObj.accumulate("lastName", validUser.getLastName());
				}
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getAddress()
								.getAddressLine1())) {
					jsonAdObj.accumulate("addressLine1",
							accountValidateDataWrapper.getValidateData()
									.getAddress().getAddressLine1());
				} else {
					jsonAdObj.accumulate("addressLine1",
							validUser.getAddressText());
				}
				// x055765-changed region and region code
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getAddress().getRegion())) {

					state = userLocal
							.getStateByStateName(accountValidateDataWrapper
									.getValidateData().getAddress().getRegion());

					jsonAdObj.accumulate("region", state.getStateName());
					jsonAdObj.accumulate("regionCode", state.getStateCode());

				} else {
					jsonAdObj.accumulate("region", state.getStateName());
					jsonAdObj.accumulate("regionCode", state.getStateCode());
				}

				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getAddress().getCountry())) {
					jsonAdObj.accumulate("country", accountValidateDataWrapper
							.getValidateData().getAddress().getCountry());
				} else {
					jsonAdObj.accumulate("country", validUser.getCountryCode());
				}
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getAddress().getPostalCode())) {
					jsonAdObj.accumulate("postalCode",
							accountValidateDataWrapper.getValidateData()
									.getAddress().getPostalCode());
				} else {
					jsonAdObj.accumulate("postalCode",
							validUser.getPostalCode());
				}
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getAddress().getCity())) {
					jsonAdObj.accumulate("city", accountValidateDataWrapper
							.getValidateData().getAddress().getCity());
				} else {
					jsonAdObj.accumulate("city", validUser.getCityName());
				}

				jsonObj.accumulate("address", jsonAdObj);
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getLandlineNumber())) {
					jsonObj.accumulate("landlineNumber",
							accountValidateDataWrapper.getValidateData()
									.getLandlineNumber());
				} else {

					jsonObj.accumulate("landlineNumber", landLineNumber);

				}

				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getMobileNumber())) {
					jsonObj.accumulate("mobileNumber",
							accountValidateDataWrapper.getValidateData()
									.getMobileNumber());
				} else {

					jsonObj.accumulate("mobileNumber", mobileNumber);

				}
				if (Utility
						.isStringNotNullorNotEmpty(accountValidateDataWrapper
								.getValidateData().getMobileNetworkOperator())) {
					jsonObj.accumulate("mobileNetworkOperator",
							accountValidateDataWrapper.getValidateData()
									.getMobileNetworkOperator());
				} else {
					if (Utility.isObjectNotNullorNotEmpty(mobileProvider)) {
						jsonObj.accumulate("mobileNetworkOperator",
								mobileProvider.getMobileProviderName());
					}else{
						jsonObj.accumulate("mobileNetworkOperator", "");
					}
				}

				jsonDisclPrefObj.put("workPhone", accountValidateDataWrapper
						.getValidateData().getDisclaimerPreferences()
						.getWorkPhone());
				jsonDisclPrefObj.put("mobilePhone", accountValidateDataWrapper
						.getValidateData().getDisclaimerPreferences()
						.getMobilePhone());
				jsonObj.accumulate("disclaimerPreferences", jsonDisclPrefObj);

				LOG.info("------jsonDisclPrefObj--------" + jsonDisclPrefObj);
				
				//x566325 - Marketting Preference fields changed into optional
				if(Utility.isObjectNotNullorNotEmpty(accountValidateDataWrapper
						.getValidateData().getMarketingPreference())){
				jsonMarkPrefObj.put("newsletter", accountValidateDataWrapper
						.getValidateData().getMarketingPreference()
						.getNewsletter());
				jsonMarkPrefObj.put("productOffers", accountValidateDataWrapper
						.getValidateData().getMarketingPreference()
						.getProductOffers());
				jsonMarkPrefObj.put("serviceOffers", accountValidateDataWrapper
						.getValidateData().getMarketingPreference()
						.getServiceOffers());
				jsonMarkPrefObj.put("scheduledMaintenance",
						accountValidateDataWrapper.getValidateData()
								.getMarketingPreference()
								.getScheduledMaintenance());
				jsonMarkPrefObj.put("feedback", accountValidateDataWrapper
						.getValidateData().getMarketingPreference()
						.getFeedback());

				LOG.info("------jsonMarkPrefObj--------" + jsonMarkPrefObj);

				jsonObj.accumulate("marketingPreferences", jsonMarkPrefObj);
				}

				jsonObj.accumulate("language", validUser.getLanguageCode());
				jsonObj.accumulate("profileChange", TRUE);
				jsonObj.accumulate("source", CUSTOMER);
				jsonPrsnObj.accumulate("person", jsonObj);
				jsonlastObj.accumulate("validateData", jsonPrsnObj);

				response = jsonlastObj;
			
		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateAccountValidateDataResponse",
					e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param changeEmailWrapper
	 * @return
	 */
	private JSONObject setChangeEmailResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser,
			ChangeEmailWrapper changeEmailWrapper, String token) {

		JSONObject response = null;

		try {

			JSONObject jsonPrsnObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			/*
			 * jsonPrsnObj.accumulate("oldEmail", "null");
			 */
			jsonPrsnObj.accumulate("oldEmail",
					ownerPortalUser.getPreviousEmailId());
			/* jsonPrsnObj.accumulate("oldEmail","null"); */
			jsonPrsnObj.accumulate("token", token);
			jsonObj.accumulate("changeEmail", jsonPrsnObj);

			response = jsonObj;

		} catch (JSONException e) {
			LOG.error("JSONException in setMultiChannelValidateDataResponse", e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param changeEmailWrapper
	 * @return
	 */
	private JSONObject setChangeEmailRevokeResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser,
			ChangeEmailRevokeWrapper changeEmailRevokeWrapper) {

		JSONObject response = null;

		try {

			JSONObject jsonPrsnObj = new JSONObject();

			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonPrsnObj.accumulate("success", TRUE);

			jsonObj.accumulate("changeEmailRevoke", jsonPrsnObj);

			response = jsonObj;

		} catch (JSONException e) {
			LOG.error("JSONException in setMultiChannelValidateDataResponse", e);
		}
		return response;
	}

	/**
	 * 
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param accountValidateDataWrapper
	 * @return
	 */
	private JSONObject setMultiChannelValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser,
			ValidateDataAccountWrapper accountValidateDataWrapper) {

		JSONObject response = null;

		try {

			JSONObject jsonPrsnObj = new JSONObject();
			JSONObject jsonlastObj = new JSONObject();
			jsonPrsnObj.accumulate("personHashId",
					ownerPortalUser.getPersonalHashId());
			jsonPrsnObj.accumulate("cdiid",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonPrsnObj.accumulate("email", ownerPortalUser.getEmailId());
			jsonPrsnObj.accumulate("linkReferenceId",
					accountValidateDataWrapper.getValidateData()
							.getLinkReferenceId());
			jsonObj.accumulate("person", jsonPrsnObj);
			jsonlastObj.accumulate("validateData", jsonObj);
			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error("JSONException in setMultiChannelValidateDataResponse", e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @return
	 */
	private JSONObject setChangePasswordValidateDataResponse() {

		JSONObject response = null;

		try {

			JSONObject jsonlastObj = new JSONObject();

			jsonlastObj.accumulate("success", true);
			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error("JSONException in setChangePasswordValidateDataResponse",
					e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To set account unlock response
	 * @return
	 */
	private JSONObject setAccountUnlockResponse() {

		JSONObject response = null;

		try {

			JSONObject jsonlastObj = new JSONObject();

			jsonlastObj.accumulate("code", 1040004);
			jsonlastObj.accumulate("message", "Account unlocked");

			response = jsonlastObj;

		} catch (JSONException e) {
			LOG.error("JSONException in setAccountUnlockResponse", e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To set response for view account validate data endpoint
	 * @param jsonObj
	 * @param isValidUser
	 * @return
	 */
	private JSONObject setViewAccountValidateDataResponse(JSONObject jsonObj,
			OwnerPortalUser validUser,List<OwnerPortalVehicle> vehicleInfor,State state) {

		JSONObject jsonlastObj = new JSONObject();
		JSONObject jsonPrsnObj = new JSONObject();
		//JSONObject jsonVinObj  = new JSONObject(); 
		JSONObject jsonArrayVin = new JSONObject();
		JSONObject jsonAdObj = new JSONObject();
		MobileProvider mobileProvider = null;
		try {

			//List vehicleList = vehicleInfor.stream().limit(6).collect(Collectors.toList());
			//x116202 - Show 100 vehicles in garage page - OS 1704-Owners2.0
 //           List vehicleList = vehicleInfor.stream().limit(2000).collect(Collectors.toList());
			LOG.info("Printing the recent vins size in viewAccountValidateDataResponse" + vehicleInfor.size());
			List<String> listOfVins= new ArrayList<String>();
			
			for(OwnerPortalVehicle vehicle :(List<OwnerPortalVehicle>)vehicleInfor){
				LOG.info("vin" + vehicle.getVin());
				String vin = vehicle.getVin();
				listOfVins.add(vin);
				
			}
			//x430955 - for Login component error requested by fuse
			jsonArrayVin.put("vin", listOfVins);
			
			jsonObj.accumulate("firstName",validUser.getFirstName());
			jsonObj.accumulate("lastName",validUser.getLastName());
			jsonObj.accumulate("cdiid",
					validUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("personHashId", validUser.getPersonalHashId());
			jsonObj.accumulate("email", validUser.getEmailId());
			jsonAdObj.accumulate("addressLine1",validUser.getAddressText());
			jsonAdObj.accumulate("city",validUser.getCityName());
			jsonAdObj.accumulate("regionCode", state.getStateCode());
			jsonAdObj.accumulate("postalCode",validUser.getPostalCode());
			jsonObj.accumulate("address", jsonAdObj);
			jsonObj.accumulate("mobileNumber", validUser.getCellPhoneNumber());
			if (Utility.isStringNotNullorNotEmpty(validUser.getMobileCarrNm())) {
				mobileProvider = userLocal
						.getProviderByProviderCode(validUser,validUser.getMobileCarrNm());
				jsonObj.accumulate("mobileNetworkOperator", mobileProvider.getMobileProviderName());
			}else{
				jsonObj.accumulate("mobileNetworkOperator", "");
			}
			jsonObj.accumulate("landlineNumber", validUser.getWorkPhoneNumber());
			jsonPrsnObj.accumulate("person", jsonObj);
		
			jsonPrsnObj.accumulate("vehicles", jsonArrayVin);
			jsonlastObj.accumulate("validateData", jsonPrsnObj);

			
		} catch (JSONException e) {
			LOG.error(
					"JSON Exception in setViewAccountValidateDataResponse for the user ="
							+ validUser.getEmailId(), e);
		}
		return jsonlastObj;
	}

	/**
	 * @author x796314
	 * @use to validate the user request for getUser and update user endpoints
	 * @param firstName
	 * @param lastName
	 * @param addressLine1
	 * @param emailId
	 * @param personHashId
	 * @param postalCode
	 * @param mobileNumber
	 * @param landLineNumber
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */

	private boolean validateRequest(String firstName, String lastName,
			String addressLine1, String emailId, String personHashId,
			String postalCode, String mobileNumber, String landLineNumber,
			JSONObject jsonObj, JSONObject jsonFinalObj, String regionCode) {

		boolean response = true;

		LOG.info("get User is not null ");
		if (firstName == null || firstName.equals("")) {
			LOG.info("firstName is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_FIRSTNAME_CODE,
					VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_FIRSTNAME_DESCRIPTION);

			response = false;
		} else if (lastName == null || lastName.equals("")) {
			LOG.info("lastName is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_LASTNAME_CODE, VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_LASTNAME_DESCRIPTION);

			response = false;
		} else if (emailId == null || emailId.equals("")) {
			LOG.info("Email is null");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_EMAIL_CODE, VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_EMAIL_DESCRIPTION);

			response = false;
		} else if (personHashId == null || personHashId.isEmpty()) {
			LOG.info("Person Hash Id is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);

			response = false;
		} else if (addressLine1 == null || addressLine1.equals("")) {
			LOG.info("addressLine1 is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_ADDRESSLINE1_CODE,
					VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_ADDRESSLINE1_DESCRIPTION);

			response = false;
		}
		/*if (Utility.isStringNotNullorNotEmpty(firstName)) {
			if (!firstName.matches("^[a-zA-Z]+$")) {
				LOG.info("Invalid First Name");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_FIRSTNAME_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_FIRSTNAME_FORMAT_DESCRIPTION);

				response = false;
			}
		}
		if (Utility.isStringNotNullorNotEmpty(lastName)) {
			if (!lastName.matches("^[a-zA-Z]+$")) {
				LOG.info("Invalid Last Name");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_LASTNAME_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_LASTNAME_FORMAT_DESCRIPTION);

				response = false;
			}
		}*/
		if (Utility.isStringNotNullorNotEmpty(postalCode)) {
			if (postalCode.length() < 5 || postalCode.length() > 5) {
				LOG.info("Invalid Postal Code length ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_POSTALCODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_POSTAL_CODE_LENGTH_DESCRIPTION);
				response = false;
			} else if (!postalCode.matches("[0-9]+")) {
				LOG.info("Invalid Postal Code format ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_POSTALCODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_POSTAL_CODE_FORMAT_DESCRIPTION);
				response = false;
			}
		}
		if (Utility.isStringNotNullorNotEmpty(landLineNumber)) {
			if (landLineNumber.length() < 10 || landLineNumber.length() > 10) {
				LOG.info("Invalid landline number length ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_LANDLINE_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_LANDLINE_LENGTH_DESCRIPTION);
				response = false;
			} else if (!landLineNumber.matches("[0-9]+")) {
				LOG.info("Invalid landline number format ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_LANDLINE_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_LANDLINE_NUMBER_FORMAT_DESCRIPTION);
				response = false;
			}
		}
		if (Utility.isStringNotNullorNotEmpty(mobileNumber)) {
			if (mobileNumber.length() < 10 || mobileNumber.length() > 10) {
				LOG.info("Invalid mobile number length ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_MOBILENUMBER_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_MOBILE_NUMBER_LENGTH_DESCRIPTION);
				response = false;
			} else if (!mobileNumber.matches("[0-9]+")) {
				LOG.info("Invalid mobile number format ");
				Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
						VALIDATION_FAILED_MOBILENUMBER_CODE,
						VALIDATION_FAILED_MESSAGE,
						INVALID_MOBILE_NUMBER_FORMAT_DESCRIPTION);
				response = false;
			}
		}
		if (Utility.isStringNotNullorNotEmpty(regionCode)) {
			if (regionCode.length() < 2 || regionCode.length() > 2) {
				if (!Utility.isObjectNotNullorNotEmpty(regionCode)) {
					Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
							VALIDATION_FAILED_CODE, VALIDATION_FAILED_MESSAGE,
							INVALID_STATE_DESCRIPTION);
					response = false;
				}
			}
		}
		return response;

	}

	/**
	 * @author x178099
	 * @param emailId
	 * @param oldEmail
	 * @param personHashId
	 * @param jsonObj
	 * @param jsonFinalObj
	 * @return
	 */
	private boolean validateChangeEmailRevokeRequest(String emailId,
			String oldEmail, String personHashId,

			JSONObject jsonObj, JSONObject jsonFinalObj) {

		boolean response = true;

		LOG.info("get User is not null ");

		if (emailId == null || emailId.equals("")) {
			LOG.info("Email is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_EMAIL_CODE, VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_EMAIL_DESCRIPTION);

			response = false;
		} else if (personHashId == null || personHashId.isEmpty()) {
			LOG.info("Person Hash Id is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);

			response = false;
		} else if (oldEmail == null || oldEmail.equals("")) {
			LOG.info("Old Email is null ");
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
					VALIDATION_FAILED_EMAIL_CODE, VALIDATION_FAILED_MESSAGE,
					VEHICLE_INVALID_EMAIL_DESCRIPTION);

			response = false;
		}

		return response;

	}

	/**
	 * @author x178099
	 * @use To set JSON object for update user details in save User -Update
	 *      account
	 * @param jsonObj
	 * @param isValidUser
	 * @param state
	 * @param phoneDetails
	 * @param saveUserWrapper
	 * @param vehicleInformation
	 * @return
	 */
	private JSONObject setUpdateAccountSaveUserResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,
			SaveUserWrapper saveUserWrapper,

			//Set<OwnerPortalVehicle> vehicleInformation,
			List<OwnerPortalVehicle> vehicleInfor,
			MobileProvider mobileProvider) {
		LOG.info("Method: Update Account");
		JSONObject response = null;
		try {
			String landLineNumber = "";
			String mobileNumber = "";
			String mobileCarrierCode = "";
			String autoDialWork = "";
			String smsWork = "";
			String autoDialMobile = "";
			String smsMobile = "";
			int autoDialAlwdWork = 0;
			int smsAlwdWork = 0;
			int autoDialAlwdMobile = 0;
			int smsAlwdMobile = 0;
			OwnerPortalUserVehicle ownerPortalUserVehicle = null;
			String largeImageUrl = "";
			String smallImageUrl = "";
			
//			for (int i = 0; i < ownerPortalUserPhone.size(); i++) {
//				if (("work").equalsIgnoreCase(ownerPortalUserPhone.get(i)
//						.getOwnerPortalUserPhonePK().getPhoneTypeId())) {
//					landLineNumber = ownerPortalUserPhone.get(i)
//							.getPhoneNumber();
//					autoDialWork = ownerPortalUserPhone.get(i)
//							.getAutoDialerAllowedIn();
//					smsWork = ownerPortalUserPhone.get(i).getSmsAllowedIn();
//
//				} else if (("mobile").equalsIgnoreCase(ownerPortalUserPhone
//						.get(i).getOwnerPortalUserPhonePK().getPhoneTypeId())) {
//					mobileNumber = ownerPortalUserPhone.get(i).getPhoneNumber();
//					mobileCarrierCode = ownerPortalUserPhone.get(i)
//							.getMobileCarrierCode();
//					autoDialMobile = ownerPortalUserPhone.get(i)
//							.getAutoDialerAllowedIn();
//					smsMobile = ownerPortalUserPhone.get(i).getSmsAllowedIn();
//					LOG.info("mobileCarrierCode:" + mobileCarrierCode);
//				}
//			}
			if(ownerPortalUserPhone.size()>0){
				landLineNumber = ownerPortalUser.getWorkPhoneNumber()+"";
				autoDialWork = ownerPortalUserPhone.get(0).getWrkPhnAutoDilrOptIn();
				smsWork = ownerPortalUserPhone.get(0).getWrkPhnSmsOptIn();
				mobileNumber = ownerPortalUser.getCellPhoneNumber()+"";
				mobileCarrierCode = ownerPortalUser.getMobileCarrNm();
				autoDialMobile = ownerPortalUserPhone.get(0).getMblPhnAutoDilrOptIn();
				smsMobile = ownerPortalUserPhone.get(0).getMblPhnSmsOptIn();
			}
			LOG.info("Update User Final Response landLineNumber: not null" + landLineNumber);

			autoDialAlwdWork = Integer.parseInt(autoDialWork);
			smsAlwdWork = Integer.parseInt(smsWork);
			autoDialAlwdMobile = Integer.parseInt(autoDialMobile);
			smsAlwdMobile = Integer.parseInt(smsMobile);
			JSONObject jsonAdObj = new JSONObject();
			// x055765 - includes all preferences
			JSONArray jsonDisclPrefObjWork = new JSONArray();
			JSONArray jsonDisclPrefObjMobile = new JSONArray();
			JSONObject jsonDisclPrefObj = new JSONObject();
			JSONObject jsonMarkPrefObj = new JSONObject();
			JSONObject jsonOwnObj = new JSONObject();
			JSONArray jsonArrayObj = new JSONArray();
			// changes made for marketing preference

			jsonObj.accumulate("id",
					(ownerPortalUser.getCustomerDataIntegrationId()));
			jsonObj.accumulate("status", "UPDATED");
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getTitle())) {
				jsonObj.accumulate("title", saveUserWrapper.getSaveUser()
						.getPerson().getTitle());
			} else {
				jsonObj.accumulate("title", ownerPortalUser.getPrefixName());
			}

			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getEmail())) {
				jsonObj.accumulate("email", saveUserWrapper.getSaveUser()
						.getPerson().getEmail());
			} else {
				jsonObj.accumulate("email", ownerPortalUser.getEmailId());
			}

			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getFirstName())) {
				jsonObj.accumulate("firstName", saveUserWrapper.getSaveUser()
						.getPerson().getFirstName());
			} else {
				jsonObj.accumulate("firstName", ownerPortalUser.getFirstName());
			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getLastName())) {
				jsonObj.accumulate("lastName", saveUserWrapper.getSaveUser()
						.getPerson().getLastName());
			} else {
				jsonObj.accumulate("lastName", ownerPortalUser.getLastName());
			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getMiddleName())) {
				jsonObj.accumulate("middleName", saveUserWrapper.getSaveUser()
						.getPerson().getMiddleName());
			} else {
				jsonObj.accumulate("middleName",
						ownerPortalUser.getMiddleName());
			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getSecondLastName())) {
				jsonObj.accumulate("secondLastName", saveUserWrapper
						.getSaveUser().getPerson().getSecondLastName());
			} else {
				jsonObj.accumulate("secondLastName",
						ownerPortalUser.getSuffixName());
			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getAddress().getAddressLine1())) {
				jsonAdObj.accumulate("addressLine1", saveUserWrapper
						.getSaveUser().getPerson().getAddress()
						.getAddressLine1());
			} else {
				jsonAdObj.accumulate("addressLine1",
						ownerPortalUser.getAddressText());
			}

			jsonAdObj.accumulate("region", state.getStateName());

			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getAddress().getCountry())) {
				jsonAdObj.accumulate("country", saveUserWrapper.getSaveUser()
						.getPerson().getAddress().getCountry());
			} else {
				jsonAdObj.accumulate("country",
						ownerPortalUser.getCountryCode());
			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getAddress().getPostalCode())) {
				jsonAdObj
						.accumulate("postalCode", saveUserWrapper.getSaveUser()
								.getPerson().getAddress().getPostalCode());
			} else {
				jsonAdObj.accumulate("postalCode",
						ownerPortalUser.getPostalCode());
			}

			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getAddress().getCity())) {
				jsonAdObj.accumulate("city", saveUserWrapper.getSaveUser()
						.getPerson().getAddress().getCity());
			} else {
				jsonAdObj.accumulate("city", ownerPortalUser.getCityName());
			}

			jsonObj.accumulate("address", jsonAdObj);
			if(saveUserWrapper.getSaveUser()
					.getPerson().getLandlineNumber() == null){
				LOG.info("Update User Final Response landLineNumber: is null" + landLineNumber);
				jsonObj.accumulate("landlineNumber", landLineNumber);
			}  
			else if(("").equals(saveUserWrapper.getSaveUser()
					.getPerson().getLandlineNumber())){
				LOG.info("Update User Final Response landLineNumber: is empty ");
				jsonObj.accumulate("landlineNumber",  "");
			}
			else if(Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getLandlineNumber())){
				LOG.info("Update User Final Response landLineNumber: not null" + saveUserWrapper
						.getSaveUser().getPerson().getLandlineNumber());
				jsonObj.accumulate("landlineNumber", saveUserWrapper
						.getSaveUser().getPerson().getLandlineNumber());
			}

			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getMobileNumber())) {
				LOG.info("Update User Final Response MobileNumber: not null" + saveUserWrapper
						.getSaveUser().getPerson().getMobileNumber());
				jsonObj.accumulate("mobileNumber", saveUserWrapper
						.getSaveUser().getPerson().getMobileNumber());
			} else {
				LOG.info("Update User Final Response MobileNumber: not null" + mobileNumber);
				//jsonObj.accumulate("mobileNumber", "");
				jsonObj.accumulate("mobileNumber", mobileNumber);

			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getMobileNetworkOperator())) {
				/*jsonObj.accumulate("mobileNetworkOperator", saveUserWrapper
						.getSaveUser().getPerson().getMobileNetworkOperator());*/
				jsonObj.accumulate("mobileNetworkOperator",
						mobileProvider.getMobileProviderName());
			} else {
				if (Utility.isObjectNotNullorNotEmpty(mobileProvider)) {
					jsonObj.accumulate("mobileNetworkOperator",
							mobileProvider.getMobileProviderName());
				}else{
					jsonObj.accumulate("mobileNetworkOperator", "");
				}
			}
			if (Utility.isStringNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getPassword())) {
				jsonObj.accumulate("password", saveUserWrapper.getSaveUser()
						.getPerson().getPassword());
			}

			String autoDial = "autodial";
			String sms = "sms";
			if (autoDialAlwdWork == 1) {
				jsonDisclPrefObjWork.put(autoDial);
			}
			if (smsAlwdWork == 1) {
				jsonDisclPrefObjWork.put(sms);
			}
			if (autoDialAlwdMobile == 1) {
				jsonDisclPrefObjMobile.put(autoDial);
			}
			if (smsAlwdMobile == 1) {
				jsonDisclPrefObjMobile.put(sms);
			}

			jsonDisclPrefObj.put("workPhone", jsonDisclPrefObjWork);
			jsonDisclPrefObj.put("mobilePhone", jsonDisclPrefObjMobile);
			jsonObj.accumulate("disclaimerPreferences", jsonDisclPrefObj);

			LOG.info("------jsonDisclPrefObj--------" + jsonDisclPrefObj);
            
			//x566325 - Marketting Preference fields changed into optional
			if(Utility.isObjectNotNullorNotEmpty(saveUserWrapper.getSaveUser()
					.getPerson().getMarketingPreference())){
			jsonMarkPrefObj.put("newsletter", saveUserWrapper.getSaveUser()
					.getPerson().getMarketingPreference().getNewsletter());
			jsonMarkPrefObj.put("productOffers", saveUserWrapper.getSaveUser()
					.getPerson().getMarketingPreference().getProductOffers());
			jsonMarkPrefObj.put("serviceOffers", saveUserWrapper.getSaveUser()
					.getPerson().getMarketingPreference().getServiceOffers());
			jsonMarkPrefObj.put("scheduledMaintenance", saveUserWrapper
					.getSaveUser().getPerson().getMarketingPreference()
					.getScheduledMaintenance());
			jsonMarkPrefObj.put("feedback", saveUserWrapper.getSaveUser()
					.getPerson().getMarketingPreference().getFeedback());

			LOG.info("Method: UpdateAccount------jsonMarkPrefObj--------" + jsonMarkPrefObj.length());

			jsonObj.accumulate("marketingPreferences", jsonMarkPrefObj);
			
			}

			//x055765 - Show only 6 vehicles in garage page - OS 1216-Owners2.0
			//List vehicleList = vehicleInfor.stream().limit(6).collect(Collectors.toList());
			//x116202 - Show 100 vehicles in garage page - OS 1704-Owners2.0
            //List vehicleList = vehicleInfor.stream().limit(2000).collect(Collectors.toList());
			LOG.info("Printing the recent vins size in UpdateUser" + vehicleInfor.size());
			
			
		/*	Iterator<OwnerPortalVehicle> iterator = vehicleInformation
					.iterator();
			OwnerPortalVehicle vehicle;*/

			// X055765 - if there is no vehicle for that account,it should not
			// display empty array
			//LOG.info("iteartor:" + iterator.hasNext());
			//if (iterator.hasNext() == true) {
				//while (iterator.hasNext()) {

					/*vehicle = iterator.next();
					LOG.info("vehicle list in iterator in set response::"
							+ vehicle);*/
			
			for(OwnerPortalVehicle vehicle :(List<OwnerPortalVehicle>)vehicleInfor){
					if (Utility.isObjectNotNullorNotEmpty(vehicle)) {
						LOG.info("vehicle list in iterator1" + vehicle);

						ownerPortalUserVehicle = vehicleLocal
								.getUserVehicleInfo(
										ownerPortalUser.getUserProfileId(),
										vehicle.getVin());
						//x566325 - Update updateUser API to include Image URL's in the response 
						largeImageUrl = vehicleLocal.getVehicleFunctionLargeImage(vehicle);
						smallImageUrl = vehicleLocal.getVehicleFunctionSmallImage(vehicle);
						
						if (Utility
								.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)) {
							JSONObject jsonOwnedObj = new JSONObject();
							if (vehicle.getVehicleModelName().contains("LEAF")
									|| vehicle.getVehicleModelName().contains(
											"LEF")) {
								jsonOwnedObj.put("electric", IS_TRUE);
							} else {
								jsonOwnedObj.put("electric", IS_FALSE);
							}
							jsonOwnedObj.put("modelIdentifier",
									vehicle.getVehicleModelCode());
							jsonOwnedObj.put("vehicleName",
									vehicle.getVehicleModelName());
							jsonOwnedObj.put("detailedVehicleName",
									vehicle.getVehicleModelName());
							jsonOwnedObj.put("vin", vehicle.getVin());
							jsonOwnedObj.put("colour",
									vehicle.getVehicleExteriorColorName());

							jsonOwnedObj.put("mileage",
									ownerPortalUserVehicle.getMileage());
							jsonOwnedObj.put("averageMileage",
									ownerPortalUserVehicle.getAverageMileage());

							// Added by 455144 for model year change
							jsonOwnedObj.put("modelYear",
									vehicle.getModelYearNumber());

							// Added by x566325 for vehicle list sort change
							jsonOwnedObj
									.put("date", ownerPortalUserVehicle
											.getCreateTimestamp());
							
							//x566325 - Update updateUser API to include Image URL's in the response
							jsonOwnedObj.put("largeImage", largeImageUrl);
							jsonOwnedObj.put("smallImage", smallImageUrl);
							
							jsonArrayObj.put(jsonOwnedObj);

						}
					}
			}

				//}
				// Added by x566325 for vehicle list sort change - Calling Sort
				// Util method
				jsonArrayObj = Util.sort(jsonArrayObj);
				LOG.info("jsonArrayObj" + jsonArrayObj.toString());

				if (Utility.isObjectNotNullorNotEmpty(jsonArrayObj)) {
					jsonOwnObj.accumulate("owned", jsonArrayObj);
				}

				if (Utility.isObjectNotNullorNotEmpty(jsonOwnObj)) {
					jsonObj.accumulate("vehicles", jsonOwnObj);
				}
			/*} else {
				LOG.info("update user - Inside else");
			}*/

			response = jsonObj;
		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateAccountSaveUserResponse", e);
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use to get the account details
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param ownerPortalUserPhone
	 * @param state
	 * @param getUserWrapper
	 * @param brand
	 * @param vehicleInformation
	 * @return
	 */

	private JSONObject setViewAccountGetUserResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,
			GetUserWrapper getUserWrapper, String brand,

			//Set<OwnerPortalVehicle> vehicleInformation,
			List<OwnerPortalVehicle> vehicleInfor,
			MobileProvider mobileProvider) {
		LOG.info("Method: getAccount" );
	
	
		JSONObject response = null;
		try {
			String landLineNumber = "";
			String mobileNumber = "";
			String autoDialWork = "";
			String smsWork = "";
			String autoDialMobile = "";
			String smsMobile = "";
			int autoDialAlwdWork = 0;
			int smsAlwdWork = 0;
			int autoDialAlwdMobile = 0;
			int smsAlwdMobile = 0;
			OwnerPortalUserVehicle ownerPortalUserVehicle = null;
			String largeImageUrl = "";
			String smallImageUrl = "";
			String modelCode = "";
			String exteriorColorCode = "";
			String factoryOptionCode = "";
			ManualVehicleLookup manualVehicleLookup = null;
			String bodyStyleName = "";
			String modelName = "";
			OwnerPortalVehicle ownerPortalVehicle = null;

	
			landLineNumber = ownerPortalUser.getWorkPhoneNumber()+"";
			
			
			mobileNumber = ownerPortalUser.getCellPhoneNumber()+"";

			if(ownerPortalUserPhone!=null && ownerPortalUserPhone.size()>0){
				autoDialWork = ownerPortalUserPhone.get(0).getWrkPhnAutoDilrOptIn();
				smsWork = ownerPortalUserPhone.get(0).getWrkPhnSmsOptIn();
				autoDialMobile = ownerPortalUserPhone.get(0).getMblPhnAutoDilrOptIn();
				smsMobile = ownerPortalUserPhone.get(0).getMblPhnSmsOptIn();
				autoDialAlwdWork = Integer.parseInt(autoDialWork);
				smsAlwdWork = Integer.parseInt(smsWork);
				autoDialAlwdMobile = Integer.parseInt(autoDialMobile);
				smsAlwdMobile = Integer.parseInt(smsMobile);
			}
			
			JSONObject jsonAdObj = new JSONObject();
			// x055765 - includes all preferences
			JSONArray jsonDisclPrefObjWork = new JSONArray();
			JSONArray jsonDisclPrefObjMobile = new JSONArray();
			JSONObject jsonDisclPrefObj = new JSONObject();
			JSONObject jsonMarkPrefObj = new JSONObject();

			JSONObject jsonOwnObj = new JSONObject();
			JSONArray jsonArrayObj = new JSONArray();

			jsonObj.accumulate("id",
					ownerPortalUser.getCustomerDataIntegrationId());
			jsonObj.accumulate("title", ownerPortalUser.getPrefixName());

			jsonObj.accumulate("email", ownerPortalUser.getEmailId());

			jsonObj.accumulate("firstName", ownerPortalUser.getFirstName());

			jsonObj.accumulate("lastName", ownerPortalUser.getLastName());

			//x566325 - User Story : 4386 | Add middleName and secondLastName to GetProfile response
			jsonObj.accumulate("secondLastName",
					ownerPortalUser.getSuffixName());
			
			jsonObj.accumulate("middleName", ownerPortalUser.getMiddleName());

			jsonAdObj.accumulate("addressLine1",
					ownerPortalUser.getAddressText());

			jsonAdObj.accumulate("city", ownerPortalUser.getCityName());

			jsonAdObj.accumulate("region", state.getStateName());

			jsonAdObj.accumulate("country", ownerPortalUser.getCountryCode());

			jsonAdObj.accumulate("postalCode", ownerPortalUser.getPostalCode());

			jsonObj.accumulate("address", jsonAdObj);
			
			//x055765 - fix for Srinath's(21-06-19) mail - If the value is null from DB, then we should send "". If the value is there, send the value.- Owners2.0
             if(landLineNumber == null || landLineNumber.equalsIgnoreCase("null")){
				LOG.info("LandLinenumber is null" + landLineNumber);
				jsonObj.accumulate("landlineNumber", "");
			}else{
				LOG.info("LandLinenumber is not null" + landLineNumber);
				jsonObj.accumulate("landlineNumber", landLineNumber);

			}

			jsonObj.accumulate("mobileNumber", mobileNumber);

			if (Utility.isObjectNotNullorNotEmpty(mobileProvider)) {
				jsonObj.accumulate("mobileNetworkOperator",
						mobileProvider.getMobileProviderName());
			}else{
				//x222914 - set mobileNetworkOperator is null in getProfile Json Response
				jsonObj.accumulate("mobileNetworkOperator", "");
				
			}

			String autoDial = "autodial";
			String sms = "sms";
			if (autoDialAlwdWork == 1) {
				jsonDisclPrefObjWork.put(autoDial);
			}
			if (smsAlwdWork == 1) {
				jsonDisclPrefObjWork.put(sms);
			}
			if (autoDialAlwdMobile == 1) {
				jsonDisclPrefObjMobile.put(autoDial);
			}
			if (smsAlwdMobile == 1) {
				jsonDisclPrefObjMobile.put(sms);
			}

			jsonDisclPrefObj.put("workPhone", jsonDisclPrefObjWork);
			jsonDisclPrefObj.put("mobilePhone", jsonDisclPrefObjMobile);
			jsonObj.accumulate("disclaimerPreferences", jsonDisclPrefObj);

			jsonMarkPrefObj.put("newsletter", getUserWrapper.getGetUser()
					.getPerson().getMarketingPreference().getNewsletter());
			jsonMarkPrefObj.put("productOffers", getUserWrapper.getGetUser()
					.getPerson().getMarketingPreference().getProductOffers());
			jsonMarkPrefObj.put("serviceOffers", getUserWrapper.getGetUser()
					.getPerson().getMarketingPreference().getServiceOffers());
			jsonMarkPrefObj.put("scheduledMaintenance", getUserWrapper
					.getGetUser().getPerson().getMarketingPreference()
					.getScheduledMaintenance());
			jsonMarkPrefObj.put("feedback", getUserWrapper.getGetUser()
					.getPerson().getMarketingPreference().getFeedback());

			
			LOG.info("Method: getAccount------jsonMarkPrefObj--------" + jsonMarkPrefObj.length());
	

			jsonObj.accumulate("marketingPreferences", jsonMarkPrefObj);

			//x055765 - Show only 6 vehicles in garage page - OS 1216-Owners2.0
            //List vehicleList = vehicleInfor.stream().limit(6).collect(Collectors.toList());
            //x116202 - Show 100 vehicles in garage page - OS 1704-Owners2.0
           // List vehicleList = vehicleInfor.stream().limit(2000).collect(Collectors.toList());
			LOG.info("Printing the recent vins size in getUser" + vehicleInfor.size());
			

			/*Iterator<OwnerPortalVehicle> iterator = vehicleInformation
					.iterator();

			while (iterator.hasNext()) {
				OwnerPortalVehicle vehicle = iterator.next();*/
			
			//x055765 - OS - 1218 - CAlL BIDW and get the ModeCode, ExteriorColourCode and OptionTypeCode and call GPAS func to form the largeimage and smallimage url
			//OS - 1218 starts
			int vehList = getUserWrapper.getGetUser().getVehicles().getOwned().size();
		    LOG.info("Vehicles List in the get User Request "+ vehList);
			
		    for(OwnerPortalVehicle vehicle :(List<OwnerPortalVehicle>)vehicleInfor){
	
			   
				for(int i = 0; i < vehList; i++) {

					if(getUserWrapper.getGetUser().getVehicles().getOwned().get(i).getVin().equalsIgnoreCase(vehicle.getVin())) {
						
						modelCode =  getUserWrapper.getGetUser().getVehicles().getOwned().get(i).getModelCode();
						factoryOptionCode =  getUserWrapper.getGetUser().getVehicles().getOwned().get(i).getFactoryOptionCode();
						exteriorColorCode = getUserWrapper.getGetUser().getVehicles().getOwned().get(i).getExteriorColorCode();
						bodyStyleName = getUserWrapper.getGetUser().getVehicles().getOwned().get(i).getBodyStyleName();
                        modelName = getUserWrapper.getGetUser().getVehicles().getOwned().get(i).getModelName();

						
						LOG.info("values from request modelcode:" + modelCode);
						LOG.info("values from request factoryOptionCode:" + factoryOptionCode);
						LOG.info("values from request exteriorColorCode:" + exteriorColorCode);
						LOG.info("values from request bodyStyleName:" + bodyStyleName);
						LOG.info("values from request modelName:" + modelName);
						
						//this code is not needed
						/*
						 * if (modelCode.equals("") || exteriorColorCode.equals("") ||
						 * factoryOptionCode.equals("") ){
						 * 
						 * //OS - 1218 - if not in BIDW, then call the mnl_vhcl_load table and get the
						 * values for this 3 fields and pass it to GPAS func to form the largeimage and
						 * smallimage url manualVehicleLookup =
						 * vehicleLocal.getVehicleInfoForTestVin(vehicle.getVin());
						 * if(Utility.isObjectNotNullorNotEmpty(manualVehicleLookup)){ modelCode =
						 * manualVehicleLookup.getModelCode(); factoryOptionCode =
						 * manualVehicleLookup.getOptionCode(); exteriorColorCode =
						 * manualVehicleLookup.getExteriorColorCode(); } LOG.info("vin" +
						 * vehicle.getVin() + " "+ "modelCode" + modelCode + "exteriorColorCode" +
						 * exteriorColorCode + "factoryOptionCode" + factoryOptionCode);
						 * 
						 * }
						 */
					}
				}
			
				
				LOG.info("vehicle list in iterator  in getuser response"
						+ vehicle.getVin());
				ownerPortalUserVehicle = vehicleLocal.getUserVehicleInfo(
						ownerPortalUser.getUserProfileId(), vehicle.getVin());
				
			
				//x566325 - Update getUser API to include Image URL's in the response 
				//x055765 - If the URL is not there in GPAS, Display Silhouette Image based on BodyStyleName and ModelName - starts

		if(Utility.isStringNotNullorNotEmpty(modelCode) && Utility.isStringNotNullorNotEmpty(exteriorColorCode) && Utility.isStringNotNullorNotEmpty(factoryOptionCode)) {
				// condition  to check if the vehicle year is >= 2016. if not set silhoutte images
				if(Integer.parseInt( vehicle.getModelYearNumber()) >= 2016) {
				
					largeImageUrl = vehicleLocal.getVehicleFunctionLargeImage1(modelCode,exteriorColorCode,factoryOptionCode);
					smallImageUrl = vehicleLocal.getVehicleFunctionSmallImage1(modelCode,exteriorColorCode,factoryOptionCode);
				
					if(null != largeImageUrl && !largeImageUrl.equals("") && !largeImageUrl.isEmpty()){
	                      try {
	                          LOG.info("Inside not null largeImageUrl"+largeImageUrl);
	                     long startTime = System.currentTimeMillis();
	                     URL u = new URL (largeImageUrl);
	                     HttpURLConnection huc = (HttpURLConnection)u.openConnection();
	                     /*huc.setRequestMethod("GET");
	                     huc.connect();*/
	                    int code = huc.getResponseCode() ;
	                    LOG.info("Status code for largeImageUrl from GPAS = "+code);
	                    long endTime = System.currentTimeMillis();
                        LOG.info("Time taken to process the GPAS function for largeImageUrl to check status code = " +(endTime - startTime) + " milliseconds");
	                    if(code != 200){
                          LOG.info("Error GPAS largeImageUrl :  ImageURL = " +largeImageUrl + " and Response code from GPAS : ResponseCode = " + code + " and VIN : VIN = " 
	                    			+ vehicle.getVin());
                           largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
                           LOG.info("Final silhoutte largeImageUrl when GPAS function image url is not proper = " + largeImageUrl);
	                       
	                       }
	                   
	                      }
	                      catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                }
					
					if(null != smallImageUrl && !smallImageUrl.equals("") && !smallImageUrl.isEmpty()){
	                      try {
	                          LOG.info("Inside not null smallImageUrl"+smallImageUrl);
	                   long startTime1 = System.currentTimeMillis();
	                    URL u1 = new URL (smallImageUrl);
	                     HttpURLConnection huc1 = (HttpURLConnection)u1.openConnection();
	                     /*huc1.setRequestMethod("GET");
	                     huc1.connect();*/
	                    int code1 = huc1.getResponseCode() ;
	                    LOG.info("Status code for smallImageUrl from GPAS = "+code1);
						long endTime1 = System.currentTimeMillis();
                        LOG.info("Time taken to process the GPAS function for smallImageUrl to check status code = " +(endTime1 - startTime1) + " milliseconds");
	                    if(code1 != 200){
	                    	LOG.info("Error GPAS smallImageUrl :  ImageURL = " + smallImageUrl + " and Response code from GPAS : ResponseCode = " + code1 + " and VIN : VIN = " 
	                    			+ vehicle.getVin());
                          smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
                          LOG.info("Final silhoutte smallImageUrl when GPAS function image url is not proper = " + smallImageUrl);
	                    }
	                    
	                      }
	                      catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                }
				
				} else {
					LOG.info("Vehicle Model Year is less that 2016 so setting silhoutte images:" + vehicle.getModelYearNumber());
					largeImageUrl = "Not_Available";
					smallImageUrl = "Not_Available";		
				}
				
				LOG.info("Final valid BIDW imageurl = " +largeImageUrl + " smallimage = " +  smallImageUrl);
				//check url check
				 if(largeImageUrl.contains("exterior-.png") || largeImageUrl.equalsIgnoreCase("Not_Available")) {
					   largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
				       LOG.info("final silhoutte largeImageUrl" + largeImageUrl);
					  
				 } 
				 if(smallImageUrl.contains("exterior-.png") || smallImageUrl.equalsIgnoreCase("Not_Available")) {
					   
					   smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
				       LOG.info("final silhoutte smallImageUrl" + smallImageUrl);
				 }

				
		} else {
					LOG.info("all the 5 values are null from BIDW & vin " + vehicle.getVin());

					//check the owner portal vehicle table for ModelName
					 ownerPortalVehicle = vehicleLocal.getModelNameUsingVin(vehicle.getVin());
					 modelName = ownerPortalVehicle.getVehicleModelName();
						LOG.info("all the 5 values are null from BIDW & modeLName from DB: " + modelName);
						//x055765 - Show silhouette images if the url is not in GPAS - starts
						 
					       largeImageUrl = Utility.getLargeSilhouetteImageURL(brand, bodyStyleName, modelName);
					       LOG.info("final silhoutte largeImageUrl using ModelName" + largeImageUrl);
						   smallImageUrl = Utility.getSmallSilhouetteImageURL(brand, bodyStyleName, modelName);
					       LOG.info("final silhoutte smallImageUrl using ModelName" + smallImageUrl);
					       
						
						

			}
		//x055765 - If the URL is not there in GPAS, Display Silhouette Image based on BodyStyleName and ModelName - ends

				//OS - 1218 ends
				
				
			
			
			
				if (Utility.isObjectNotNullorNotEmpty(vehicle)) {

					if (Utility
							.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)) {
						JSONObject jsonOwnedObj = new JSONObject();
						if (vehicle.getVehicleModelName().contains("LEAF")
								|| vehicle.getVehicleModelName()
										.contains("LEF")) {
							jsonOwnedObj.put("electric", IS_TRUE);
						} else {
							jsonOwnedObj.put("electric", IS_FALSE);
						}
						jsonOwnedObj.put("modelIdentifier",
								vehicle.getVehicleModelCode());
						jsonOwnedObj.put("vehicleName",
								vehicle.getVehicleModelName());
						jsonOwnedObj.put("detailedVehicleName",
								vehicle.getVehicleModelName());
						jsonOwnedObj.put("vin", vehicle.getVin());
						jsonOwnedObj.put("colour",
								vehicle.getVehicleExteriorColorName());

						jsonOwnedObj.put("mileage",
								ownerPortalUserVehicle.getMileage());

						jsonOwnedObj.put("averageMileage",
								ownerPortalUserVehicle.getAverageMileage());

						jsonOwnedObj.put("modelYear",
								vehicle.getModelYearNumber());
						jsonOwnedObj.put("nickname",
								ownerPortalUserVehicle.getVehicleNickName());
						// Added by x566325 for vehicle list sort change
						jsonOwnedObj.put("date",
								ownerPortalUserVehicle.getCreateTimestamp());
						
						//x566325 - Update getUser API to include Image URL's in the response
						jsonOwnedObj.put("largeImage",largeImageUrl);
						jsonOwnedObj.put("smallImage",smallImageUrl);
						
						jsonArrayObj.put(jsonOwnedObj);
					}

				}

			}
			// Added by x566325 for vehicle list sort change - Calling Sort Util
			// method
			jsonArrayObj = Util.sort(jsonArrayObj);

			if (jsonArrayObj.length() > 0) {
				jsonOwnObj.accumulate("owned", jsonArrayObj);
			}

			if (jsonOwnObj.length() > 0) {
				jsonObj.accumulate("vehicles", jsonOwnObj);
			}

			response = jsonObj;
		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateAccountSaveUserResponse", e);
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use to get the user data for multi channel dealer access service
	 * @param jsonObj
	 * @param ownerPortalUser
	 * @param ownerPortalUserPhone
	 * @param state
	 * @param getUserWrapper
	 * @return
	 */

	private JSONObject setViewAccountMultiChannelResponse(JSONObject jsonObj,
			OwnerPortalUser ownerPortalUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State state,
			GetUserWrapper getUserWrapper, MobileProvider mobileProvider) {
		JSONObject response = null;
		JSONObject jsonCustomObj = new JSONObject();
		JSONObject jsonAdObj = new JSONObject();
		JSONObject jsonOptObj = new JSONObject();

		try {
			String landLineNumber = ownerPortalUser.getWorkPhoneNumber()+"";
			String mobileNumber = ownerPortalUser.getCellPhoneNumber()+"";

			jsonObj.accumulate("title", ownerPortalUser.getPrefixName());

			jsonObj.accumulate("firstName", ownerPortalUser.getFirstName());
			jsonObj.accumulate("middleName", ownerPortalUser.getMiddleName());
			jsonObj.accumulate("lastName", ownerPortalUser.getLastName());
			jsonObj.accumulate("secondLastName",
					ownerPortalUser.getSuffixName());

			jsonAdObj.accumulate("addressLine1",
					ownerPortalUser.getAddressText());
			jsonAdObj.accumulate("city", ownerPortalUser.getCityName());
			jsonAdObj.accumulate("region", state.getStateName());
			jsonAdObj.accumulate("postalCode", ownerPortalUser.getPostalCode());
			jsonAdObj.accumulate("country", ownerPortalUser.getCountryCode());

			jsonObj.accumulate("address", jsonAdObj);

			jsonObj.accumulate("landlineNumber", landLineNumber);

			jsonObj.accumulate("mobileNumber", mobileNumber);
			jsonObj.accumulate("email", ownerPortalUser.getEmailId());

			jsonOptObj.accumulate("optIn", false);
			jsonObj.accumulate("marketingPreferences", jsonOptObj);
			if (Utility.isObjectNotNullorNotEmpty(mobileProvider)) {
				jsonObj.accumulate("mobileNetworkOperator",
						mobileProvider.getMobileProviderName());
			}else{
				jsonObj.accumulate("mobileNetworkOperator", "");
			}

			jsonCustomObj.accumulate("accountSource", DEALER);

			jsonObj.accumulate("customAttributes", jsonCustomObj);

			response = jsonObj;

		} catch (JSONException e) {
			LOG.error("JSONException in setUpdateAccountSaveUserResponse", e);
		}
		return response;
	}

	/**
	 * @author 104026
	 * @use to validate whether 3 or more consecutive letters of password match
	 *      with Email
	 * @param Email
	 * @param New
	 *            Password
	 * @return boolean
	 */

	public boolean usernamePasswordMatch(String email, String password) {
		

		return false;
	}

		//x055765 - get PersonHashId from DB for Forgot Password API - Fuse will call MAritz RegisterPersonActivities
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/1.0/userDetailsForForgotPassword")
    public Response userDetailsForForgotPassword(@HeaderParam("Brand") String brand,String requestJson) throws JSONException {
          LOG.info("getPersonHashID for ForgotPassword API & brand = " + brand );
          
          GetUserWrapper getUserWrapper = null;
          ObjectMapper mapper = new ObjectMapper();
          JSONObject jsonObj = new JSONObject();
          JSONObject jsonFinalObj = new JSONObject();
          JSONObject jsonPrsnObj = new JSONObject();
         // JSONObject jsonPrsnFinalObj = new JSONObject();
          OwnerPortalUser validUser = null;
          String lnkRfrnc = null;
          String response;
          String result = "";
          String usrPrflIdResp;
          boolean isValidBrand = true;
          boolean isvalidJSON = true;
          boolean invaliduser = true;

          
          try{
        	  if (Utility.isStringNotNullorNotEmpty(requestJson)) {
        	   getUserWrapper = mapper.readValue(requestJson,
                          GetUserWrapper.class);
        	   isValidBrand = Util.isBrandNull(brand, jsonObj,
        	                              jsonFinalObj);
        	  if(isValidBrand){
        	    if (brand.equalsIgnoreCase(BRAND_INFINITI)
        	                              || brand.equalsIgnoreCase(BRAND_NISSAN)) {
        	  							
        	  							 if (brand.equalsIgnoreCase(BRAND_NISSAN)) {
        	                              brand = "N";
        	                        } else if (brand
        	                                    .equalsIgnoreCase(BRAND_INFINITI)) {
        	                              brand = "I";
        	                        }
        	                        
        	                  String emailId = getUserWrapper.getGetUser().getPerson().getEmail();
        	                  LOG.info("emailId in ForgotPassword API " + emailId);
        	                  LOG.info("brand in ForgotPassword API " + brand);
        	  				
        	  				validUser = userLocal.validateUser(emailId, brand);
        	  				
        	  				if(Utility
									.isObjectNotNullorNotEmpty(validUser)){
        	  				
        	  				response = validUser.getPersonalHashId();
        	                  LOG.info("Final PersonHashId in the userDetailsForForgotPassword API is " + response );
        	                  
        	                 usrPrflIdResp = validUser.getUserProfileId();
        	                 LOG.info("UserProfileId in the userDetailsForForgotPassword API is " + usrPrflIdResp );
        	                  String notificationCode = getUserWrapper.getGetUser().getPerson().getNotificationCode();
        	                  LOG.info("notificationCode in ForgotPassword API " + notificationCode);
        	                  lnkRfrnc = userLocal.saveLnkRefrncCodeFromWso2(notificationCode,usrPrflIdResp,brand);
        	                  LOG.info("after saving the notification code in DB : " + lnkRfrnc);

        	                  
        	                  jsonPrsnObj.accumulate("personHashId", response);
        	                  //jsonPrsnObj.accumulate("resetURL", "http://qa2.owners.nissanusa.com:80/nowners/user/passwordResetForm");
        	              if (brand == "N") {
        	                    LOG.info("Inside nissan brand to send the URL");
        	                        jsonPrsnObj.accumulate("resetURL", NISSAN_WSO2_RESET_LINK);
        	                        LOG.info("inside brand Nissan : reset URL : " + jsonPrsnObj);
        	               } else if(brand == "I") {
        	                          LOG.info("Inside infiniti brand to send the URL");
        	                        jsonPrsnObj.accumulate("resetURL", INFINITI_WSO2_RESET_LINK);
        	                        LOG.info("Inside brand Infiniti : reset URL : " + jsonPrsnObj);
        	                }
        	  				result = SUCCESS;
        	  				
        	  				}else{
        	  				
        	  										Util.setFaultDataToJSON(
        	  												jsonObj,
        	  												jsonFinalObj,
        	  												VALIDATION_INVALID_EMAIL_ADDRESS_CODE,
        	  												VALIDATION_FAILED_MESSAGE,
        	  												VEHICLE_INVALID_EMAIL_ADDRESS_DESCRIPTION);

        	  										invaliduser = false;
        	  									
        	  				
        	  				}
        	  							
        	  							
        	  							
        	  							}else{
        	  							LOG.info("Brand is neither N nor I");
        	                        Util.setFaultDataToJSON(jsonObj,
        	                                    jsonFinalObj,
        	                                    VEHICLE_INVALID_BRAND_CODE,
        	                                    VALIDATION_FAILED_MESSAGE,
        	                                    VEHICLE_INVALID_BRAND_DESCRIPTION);
        	                        isValidBrand = false;
        	  							}
        	  							}else{
        	  							 LOG.info("Brand is neither N nor I");
        	                        Util.setFaultDataToJSON(jsonObj,
        	                                    jsonFinalObj,
        	                                    VEHICLE_INVALID_BRAND_CODE,
        	                                    VALIDATION_FAILED_MESSAGE,
        	                                    VEHICLE_INVALID_BRAND_DESCRIPTION);
        	                        isValidBrand = false;
        	  							}
        	  }else{
        	  Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
        	  					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
        	  					INVALID_JSON_REQUEST_DESCRIPTION);
        	  					isvalidJSON=false;
        	  }
        	  if(result.equalsIgnoreCase(SUCCESS)){

        	  return Response.ok().status(200).entity(jsonPrsnObj.toString()).build();
        	  }
        	  else if(!isvalidJSON ||!isValidBrand ||!invaliduser){
        	  return Response.ok().status(400).entity(jsonFinalObj.toString())
        	  					.build();

        	  }else{
        	  Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
        	  					GENERAL_ERROR_FAULT_CODE, INVALID_JSON_REQUEST_ERROR_MSG,
        	  					INVALID_JSON_REQUEST_DESCRIPTION);
        	  			
        	  			return Response.ok().status(400).entity(jsonFinalObj.toString())
        	  					.build();
        	  }

        	  }
          catch (RuntimeException e) {
        	    throw e;
        	}
        	  catch (Exception e) {
        	  			Util.setFaultDataToJSON(jsonObj, jsonFinalObj,
        	  					GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
        	  					GENERAL_ERROR_DESCRIPTION);
        	  			
        	  			return Response.ok().status(500).entity(jsonFinalObj.toString())
        	  					.build();
        	  		}
               
                     

    }
	/**
	x116202 - Check for password policy - should pass any of the 3 conditions
	* @param New Password
	* @return boolean
	*/
	private boolean checkPasswordPolicy(String newPassword) {
		boolean numberMatch  = false;
		boolean lowerCaseMatch = false;
		boolean upperCaseMatch = false;
		boolean specialCharMatch = false;
		try{
			if(newPassword.matches(
					"^((?=.*\\d)).{0,100}$")){
				numberMatch = true;
			}
			if(newPassword.matches(
					"^((?=.*[a-z])).{0,100}$")){
				lowerCaseMatch = true;
			}
			if(newPassword.matches(
					"^((?=.*[A-Z])).{0,100}$") ){
				upperCaseMatch = true;
			}
			if(newPassword.matches(
					"^((?=.*[!@#$%&*])).{0,100}$") ){
				specialCharMatch = true;
			}
			if((numberMatch?1:0)+(lowerCaseMatch?1:0)+(upperCaseMatch?1:0)+(specialCharMatch?1:0)>=3){
				return true;
			}
			return false;
		}
		catch(Exception e){
			LOG.info("Exception in checkPasswordPolicy");
			return false;
		}
		
	}
	
	
	
	
	
	
}
