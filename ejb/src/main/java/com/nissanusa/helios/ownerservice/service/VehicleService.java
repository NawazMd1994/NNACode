package com.nissanusa.helios.ownerservice.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

/*
 *last modified date 31-05-2016 by x178099

 */

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.logging.Logger;

import com.nissanusa.helios.ownerservice.entity.ASServiceContractCategoryLookup;
import com.nissanusa.helios.ownerservice.entity.Decryption;
import com.nissanusa.helios.ownerservice.entity.Equipment;
import com.nissanusa.helios.ownerservice.entity.EventNotification;
import com.nissanusa.helios.ownerservice.entity.ManualVehicleLookup;
import com.nissanusa.helios.ownerservice.entity.ModelLineMapping;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalSubScriptionProduct;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalToken;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicleFailedReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehiclePK;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicleTelematicsCodeMaster;
import com.nissanusa.helios.ownerservice.entity.TelematicsSvcLg;
import com.nissanusa.helios.ownerservice.entity.TelematicstermsAndConditions;
import com.nissanusa.helios.ownerservice.entity.TermsAndConditionsAgreementSt;
import com.nissanusa.helios.ownerservice.entity.TermsAndConditionsAgreementStPK;
import com.nissanusa.helios.ownerservice.entity.VehdsplMaritzDlyLndgPrcngEntity;
import com.nissanusa.helios.ownerservice.entity.VehicleCarwings;
import com.nissanusa.helios.ownerservice.entity.VehicleSpecification;
import com.nissanusa.helios.ownerservice.util.DynamicProperties;
import com.nissanusa.helios.ownerservice.util.DynamicPropertiesLoader;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.OwnerQueryConstants;
import com.nissanusa.helios.ownerservice.util.PropertiesLoader;
import com.nissanusa.helios.ownerservice.util.Utility;
import com.nissanusa.helios.ownerservice.vo.FailedVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UpdateVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UploadDocumentWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateDataWrapper;
import org.apache.http.client.methods.HttpPost;

/**
 * 
 * @author x178099
 * @use Service Implementation of vehicle services
 * 
 */

public class VehicleService implements OwnerConstants, OwnerQueryConstants {

	private static final Logger LOG = Logger.getLogger(VehicleService.class);
	private DynamicProperties dProperties;

	String saveResponse;

	public VehicleService() {
		try {
			PropertiesLoader.getLog4j();
			dProperties = DynamicPropertiesLoader.getDynaProp();
			LOG.info("dProperties " + dProperties);
		} catch (Exception e) {
			LOG.error("PropertiesLoaderException Exception VehicleService ", e);

		}
	}

	/**
	 * @author x178099
	 * @use To save vehicle details check for duplicate vin exist in
	 *      OwnerPortalUserVhcl mapped for same user or other user. check if vin
	 *      is availble in ownrportlvhcl table,if not save the vehicle details
	 *      in ownrportlvhcl and OwnerPortalUserVhcl table
	 * @param saveVehicleWrapper
	 *            ,user
	 * @return
	 */
	public String saveVehicle(SaveVehicleWrapper saveVehicleWrapper,
			OwnerPortalUser ownerPortalUser, EntityManager osEm) {
		String response = "failure";
		String nickName = "";
		OwnerPortalVehicle existingOwnerPortalVehicle;
		try {

			List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = osEm
					.createNamedQuery("OwnerPortalUserVehicle.findByVin",
							OwnerPortalUserVehicle.class)
					.setParameter(
							"vin",
							saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin()).getResultList();

			if (!ownerPortalUserVehicleList.isEmpty()) {

				LOG.info("Add owner existing and check for same user for the vin"
						+ saveVehicleWrapper.getSaveVehicle().getVehicle()
								.getVin());
				List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
						.createNamedQuery(
								"OwnerPortalUserVehicle.findByUserProfileIdAndVin",
								OwnerPortalUserVehicle.class)
						.setParameter("userProfileId",
								ownerPortalUser.getUserProfileId())
						.setParameter(
								"vin",
								saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getVin()).getResultList();

				if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
						&& !(ownerPortalUserVehicle.isEmpty())) {
					LOG.info("VIN = "
							+ saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin() + " is  mapped to same user");
					response = "sameUser";
				} else {
					LOG.info("VIN = "
							+ saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin() + " is not mapped to same user");
					response = "otherUser";
				}

			} else {
				LOG.info("VIN="
						+ saveVehicleWrapper.getSaveVehicle().getVehicle()
								.getVin());

				List<OwnerPortalVehicle> ownerPortalVehicle = osEm
						.createNamedQuery("OwnerPortalVehicle.findByVin",
								OwnerPortalVehicle.class)
						.setParameter(
								"vin",
								saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getVin().trim())
						.getResultList();

				if (ownerPortalVehicle.isEmpty()) {

					String insertVehicle = saveVehicleInfo(saveVehicleWrapper,
							osEm);

					if (insertVehicle.equalsIgnoreCase(SUCCESS)) {

						List<OwnerPortalVehicle> vehicledetails = osEm
								.createNamedQuery(
										"OwnerPortalVehicle.findByVin",
										OwnerPortalVehicle.class)
								.setParameter(
										"vin",
										saveVehicleWrapper.getSaveVehicle()
												.getVehicle().getVin().trim())
								.getResultList();
						if (saveVehicleWrapper.getSaveVehicle().getVehicle()
								.getNickname() == null
								|| saveVehicleWrapper.getSaveVehicle()
										.getVehicle().getNickname().isEmpty()) {
							LOG.info("nickName not in db for vehicle is"
									+ nickName);
							nickName = vehicledetails.get(0)
									.getVehicleModelName();
						}
						String insertUserVehicle = saveUserVehicleInfo(
								saveVehicleWrapper, ownerPortalUser, nickName,
								osEm);
						if (insertUserVehicle.equalsIgnoreCase(SUCCESS)) {
							response = "success";
						}
					}

				} else {
					existingOwnerPortalVehicle = new OwnerPortalVehicle();
					existingOwnerPortalVehicle = ownerPortalVehicle.get(0);
					if (saveVehicleWrapper.getSaveVehicle().getVehicle()
							.getNickname() == null
							|| saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getNickname().isEmpty()) {
						nickName = existingOwnerPortalVehicle
								.getVehicleModelName();
						LOG.info("nickName in already data exist is" + nickName);
					}
					String insertVehicle = updateExistingVehicleInfo(
							existingOwnerPortalVehicle, saveVehicleWrapper,
							osEm);
					if (insertVehicle.equalsIgnoreCase(SUCCESS)) {

						String insertUserVehicle = saveUserVehicleInfo(
								saveVehicleWrapper, ownerPortalUser, nickName,
								osEm);
						if (insertUserVehicle.equalsIgnoreCase(SUCCESS)) {
							response = "success";

						}
					}
				}
			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in save vehicle service for the vin ="
							+ saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin(), e);

		} catch (Exception e) {
			LOG.error(
					"Exception in save vehicle service for the vin = "
							+ saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin(), e);

		}
		return response;
	}

	/**
	 * @author x796314
	 * @use To save vehicle details in OwnrPortlVhcl table
	 * @param saveVehicleWrapper
	 * @param osEm
	 * @return
	 */
	public String saveVehicleInfo(SaveVehicleWrapper saveVehicleWrapper,
			EntityManager osEm) {
		String response = "failure";
		ManualVehicleLookup vehicleLookUp = null;
		ModelLineMapping modelNameLookupList = null;

		try {
			if (Utility.isStringNotNullorNotEmpty(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getVin())) {

				vehicleLookUp = vinLookup(saveVehicleWrapper.getSaveVehicle()
						.getVehicle().getVin(), osEm);

				if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
					if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
							.getModelLineCode()))
						modelNameLookupList = modelNameLookup(
								vehicleLookUp.getModelLineCode(), osEm);
					/*LOG.info("inside vinlookup modelName======="
							+ modelNameLookupList.getModelName());*/

				} else {
					modelNameLookupList = modelNameLookup(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelLineCode(),
							osEm);
					LOG.info("inside not a vinlookup modelName======="
							+ modelNameLookupList.getModelName());
				}
			}

			OwnerPortalVehicle ownerPortalVehicle = new OwnerPortalVehicle();

			ownerPortalVehicle.setVin(saveVehicleWrapper.getSaveVehicle()
					.getVehicle().getVin());

			ownerPortalVehicle.setCreateUserId(USERID);
			ownerPortalVehicle.setCreateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			ownerPortalVehicle.setUpdateUserId(USERID);
			ownerPortalVehicle.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			if (Utility.isStringNotNullorNotEmpty(saveVehicleWrapper
					.getSaveVehicle().getBrand())
					|| !("").equals(saveVehicleWrapper.getSaveVehicle()
							.getBrand())) {
				if (saveVehicleWrapper.getSaveVehicle().getBrand()
						.equalsIgnoreCase(INFINITI)) {

					ownerPortalVehicle.setVehicleMakeCode(INFINITI);

				} else if (saveVehicleWrapper.getSaveVehicle().getBrand()
						.equalsIgnoreCase(NISSAN)) {

					ownerPortalVehicle.setVehicleMakeCode(NISSAN);

				}
			}
			// Setting model Line mapping model name if available
			if (Utility.isObjectNotNullorNotEmpty(modelNameLookupList)) {
				if (Utility.isStringNotNullorNotEmpty(modelNameLookupList
						.getModelName())) {
					LOG.info("model name==="
							+ modelNameLookupList.getModelName());
					ownerPortalVehicle.setVehicleModelName(modelNameLookupList
							.getModelName());
				} else {
					ownerPortalVehicle.setVehicleModelName(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelName());
				}
			} else {
				ownerPortalVehicle.setVehicleModelName(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelName());
			}
			// Setting vehicle look up mapping model code if available
			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				LOG.info("model code===" + vehicleLookUp.getModelCode());
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getModelCode())) {
					ownerPortalVehicle.setVehicleModelCode(vehicleLookUp
							.getModelCode());
				} else {
					ownerPortalVehicle.setVehicleModelCode(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelCode());
				}
			} else {
				ownerPortalVehicle.setVehicleModelCode(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelCode());
			}
			// Setting vehicle look up mapping interior color code if available
			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getInteriorColorCode())) {
					LOG.info("color code==="
							+ vehicleLookUp.getInteriorColorCode());
					ownerPortalVehicle
							.setVehicleInteriorColorCode(vehicleLookUp
									.getInteriorColorCode());
				} else {
					ownerPortalVehicle
							.setVehicleInteriorColorCode(saveVehicleWrapper
									.getSaveVehicle().getVehicle()
									.getInteriorColorCode());
				}
			} else {
				ownerPortalVehicle
						.setVehicleInteriorColorCode(saveVehicleWrapper
								.getSaveVehicle().getVehicle()
								.getInteriorColorCode());
			}
			// Setting vehicle look up mapping model line code if available
			// Commenting as mdl_ln_cd is not available in OP DB
			/*if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getModelLineCode())) {
					LOG.info("line code===" + vehicleLookUp.getModelLineCode());
					ownerPortalVehicle.setModelLineCode(vehicleLookUp
							.getModelLineCode());
				} else {
					ownerPortalVehicle.setModelLineCode(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelLineCode());
				}
			} else {
				ownerPortalVehicle.setModelLineCode(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelLineCode());
			}
			ownerPortalVehicle.setVehicleInteriorColorName(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getInteriorColorName());
			ownerPortalVehicle.setVehicleExteriorColorCode(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getExteriorColorCode());
			ownerPortalVehicle.setVehicleExteriorColorName(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getExteriorColorName());
			ownerPortalVehicle.setVehicleOptionCode(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getOptionCode());*/
			// Setting vehicle look up mapping model year if available

			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getVehicleModelYear()+"")) {
					ownerPortalVehicle.setModelYearNumber(vehicleLookUp
							.getVehicleModelYear()+"");
					LOG.info("year code==="
							+ vehicleLookUp.getVehicleModelYear());
				} else {
					ownerPortalVehicle.setModelYearNumber(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelYear());
				}
			} else {
				ownerPortalVehicle.setModelYearNumber(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelYear());
			}
			ownerPortalVehicle.setLevelCharger("N");
			osEm.persist(ownerPortalVehicle);
			LOG.info("Save Vehicle Information success for the vin"
					+ saveVehicleWrapper.getSaveVehicle().getVehicle().getVin());
			response = "success";
		}

		catch (PersistenceException e) {
			LOG.error("PersistenceException in save vehicle info ", e);

		} catch (Exception e) {
			LOG.error("Exception in save vehicle info", e);

		}
		return response;
	}

	/* *//**
	 * @author x796314
	 * @use To save user and vehicle details in OwnerPortalUserVhcl table
	 * @param saveVehicleWrapper
	 * @param OwnerPortalUser
	 * @param osEm
	 * @return
	 */
	public String saveFailedVehicle(
			FailedVehicleWrapper failedVehicleWrapper,
			OwnerPortalUser validUser,
			OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference,
			String brand, EntityManager osEm) {
		LOG.info("Inside save failed Vehicle  ");
		String make = "N";
		String response = "failure";
		Long vehicleFailedReferenceKey;
		try {

			LOG.info("Inside  saveFailedVehicleInfo try");
			ownerPortalUserVehicleFailedReference = new OwnerPortalUserVehicleFailedReference();
			BigDecimal b = generateFailedRefKeyFromSequence(osEm);
			LOG.info("FailedRefKey " + b);
			if (b != null) {
				vehicleFailedReferenceKey = b.longValue();
				LOG.info("FailedRefKey " + vehicleFailedReferenceKey);
			} else {
				vehicleFailedReferenceKey = 0L;
			}

			ownerPortalUserVehicleFailedReference
					.setVehicleFailedReferenceKey(vehicleFailedReferenceKey);
			ownerPortalUserVehicleFailedReference.setUserProfileId(validUser
					.getUserProfileId());
			ownerPortalUserVehicleFailedReference.setVin(failedVehicleWrapper
					.getFailedVehicle().getVehicle().getVin());

			if ("NISSAN".equalsIgnoreCase(failedVehicleWrapper
					.getFailedVehicle().getVehicle().getMake())) {
				make = "N";

			} else if ("INFINITI".equalsIgnoreCase(failedVehicleWrapper
					.getFailedVehicle().getVehicle().getMake())) {
				make = "I";

			} else {
				make = "N";

			}

//			ownerPortalUserVehicleFailedReference.setVehicleMakeCode(make);
			if (failedVehicleWrapper.getFailedVehicle().getVehicle()
					.getNickname() == null
					|| failedVehicleWrapper.getFailedVehicle().getVehicle()
							.getNickname().isEmpty()) {

				ownerPortalUserVehicleFailedReference
						.setVehicleNickName(failedVehicleWrapper
								.getFailedVehicle().getVehicle().getMake());
			} else {
				ownerPortalUserVehicleFailedReference
						.setVehicleNickName(failedVehicleWrapper
								.getFailedVehicle().getVehicle().getNickname());
			}

			ownerPortalUserVehicleFailedReference
					.setMileage(failedVehicleWrapper.getFailedVehicle()
							.getVehicle().getMileage() == null ? 0
							: failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getMileage());
			ownerPortalUserVehicleFailedReference
					.setAverageMileage(failedVehicleWrapper.getFailedVehicle()
							.getVehicle().getAverageMileage() == null ? 0
							: failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getAverageMileage());
			ownerPortalUserVehicleFailedReference.setLeaseIn("N");
			ownerPortalUserVehicleFailedReference
					.setPreownerStatusCode("P");
			ownerPortalUserVehicleFailedReference.setValidateStatusCode("INP");
			ownerPortalUserVehicleFailedReference
					.setLeaseTerm(failedVehicleWrapper.getFailedVehicle()
							.getVehicle().getLeaseTerm() == null ? 0
							: failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getLeaseTerm());
			ownerPortalUserVehicleFailedReference.setCreateUserId(USERID);
			ownerPortalUserVehicleFailedReference
					.setCreateTimestamp(new Timestamp(System
							.currentTimeMillis()));
			ownerPortalUserVehicleFailedReference.setUpdateUserId(USERID);
			ownerPortalUserVehicleFailedReference
					.setUpdateTimestamp(new Timestamp(System
							.currentTimeMillis()));

			osEm.persist(ownerPortalUserVehicleFailedReference);
			response = "success";

		} catch (PersistenceException e) {
			LOG.error(
					"PersistenceException in saveFailedVehicleInfo for the vin ="
							+ failedVehicleWrapper.getFailedVehicle()
									.getVehicle().getVin(), e);
			response = "failure";
		} catch (Exception e) {
			LOG.error("Exception in saveFailedVehicleInfo for the vin = "
					+ failedVehicleWrapper.getFailedVehicle().getVehicle()
							.getVin(), e);
			response = "failure";
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use To save user and vehicle details in OwnerPortalUserVhcl table
	 * @param saveVehicleWrapper
	 * @param OwnerPortalUser
	 * @param osEm
	 * @return
	 */
	
	public long saveFailedVehicleInfo1( ValidateDataWrapper validateDataWrapper,
			OwnerPortalUser validUser, OwnerPortalUserVehicleFailedReference
			ownerPortalUserVehicleFailedReference, String brand, EntityManager osEm) {
		LOG.info("Inside save failed Vehicle information "); Long
		vehicleFailedReferenceKey = 0L; String makeCode = ""; try {

			LOG.info("Inside  saveFailedVehicleInfo try");
			ownerPortalUserVehicleFailedReference = new
					OwnerPortalUserVehicleFailedReference(); BigDecimal b =
					generateFailedRefKeyFromSequence(osEm); LOG.info("FailedRefKey " + b); if (b
							!= null) { vehicleFailedReferenceKey = b.longValue();
							LOG.info("FailedRefKey " + vehicleFailedReferenceKey); } else {
								vehicleFailedReferenceKey = 0L; }

					ownerPortalUserVehicleFailedReference
					.setVehicleFailedReferenceKey(vehicleFailedReferenceKey);

					ownerPortalUserVehicleFailedReference.setUserProfileId(validUser
							.getUserProfileId());
					ownerPortalUserVehicleFailedReference.setVin(validateDataWrapper
							.getValidateData().getVin()); if ("NISSAN".equalsIgnoreCase(brand)) {
								makeCode = "N";

							} else if ("INFINITI".equalsIgnoreCase(brand)) { makeCode = "I";

							} else { makeCode = "N"; }

//							ownerPortalUserVehicleFailedReference.setVehicleMakeCode(makeCode);

							if (validateDataWrapper.getValidateData().getNickname() == null ||
									validateDataWrapper.getValidateData().getNickname().isEmpty()) {

								ownerPortalUserVehicleFailedReference.setVehicleNickName(brand); } else {
									ownerPortalUserVehicleFailedReference .setVehicleNickName(validateDataWrapper
											.getValidateData().getNickname());

								}

							List<OwnerPortalUserVehicle> ownerPortaluserVehicle = osEm
									.createNamedQuery("OwnerPortalUserVehicle.findByVin",
											OwnerPortalUserVehicle.class) .setParameter("vin",
													validateDataWrapper.getValidateData().getVin()) .getResultList();

							Integer mileage = ownerPortaluserVehicle.get(0).getMileage(); Integer
							averageMileage = ownerPortaluserVehicle.get(0) .getAverageMileage();

							ownerPortalUserVehicleFailedReference .setMileage(mileage == null ? 0 :
								mileage); ownerPortalUserVehicleFailedReference
								.setAverageMileage(averageMileage == null ? 0 : averageMileage);

								ownerPortalUserVehicleFailedReference.setLeaseIn("N");
								ownerPortalUserVehicleFailedReference .setPreownerStatusCode("P");
								ownerPortalUserVehicleFailedReference
								.setLeaseTerm(validateDataWrapper.getValidateData() .getLeaseTerm() == null ?
										0 : validateDataWrapper .getValidateData().getLeaseTerm());

								ownerPortalUserVehicleFailedReference.setValidateStatusCode("INP");
								ownerPortalUserVehicleFailedReference.setCreateUserId(USERID);
								ownerPortalUserVehicleFailedReference .setCreateTimestamp(new
										Timestamp(System .currentTimeMillis()));
								ownerPortalUserVehicleFailedReference.setUpdateUserId(USERID);
								ownerPortalUserVehicleFailedReference .setUpdateTimestamp(new
										Timestamp(System .currentTimeMillis()));

								osEm.persist(ownerPortalUserVehicleFailedReference);

		} catch (PersistenceException e) { LOG.error(
				"PersistenceException in saveFailedVehicleInfo for the vin =" +
						validateDataWrapper.getValidateData().getVin(), e);

		} catch (Exception e) {
			LOG.error("Exception in saveFailedVehicleInfo for the vin = " +
					validateDataWrapper.getValidateData().getVin(), e); 
			return 0; 
		} 
		return vehicleFailedReferenceKey; 
	}
	 
	/**
	 * @author x796314
	 * @use To save user and vehicle details in OwnerPortalUserVhcl table
	 * @param saveVehicleWrapper
	 * @param OwnerPortalUser
	 * @param osEm
	 * @return
	 */
	public String saveUserVehicleInfo(SaveVehicleWrapper saveVehicleWrapper,
			OwnerPortalUser ownerPortalUser, String nickName, EntityManager osEm) {
		LOG.info("Inside save user vehicle information ");
		String response = "failure";
		try {
			OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
			OwnerPortalUserVehiclePK ownerPortalUserVehiclePK = new OwnerPortalUserVehiclePK();

			ownerPortalUserVehiclePK.setUserProfileId(ownerPortalUser
					.getUserProfileId());
			if (saveVehicleWrapper.getSaveVehicle().getVehicle().getNickname() == null
					|| saveVehicleWrapper.getSaveVehicle().getVehicle()
							.getNickname().isEmpty()) {
				LOG.info("inside nickname null and nickname value is set to"
						+ nickName);
				ownerPortalUserVehicle.setVehicleNickName(nickName);
			} else {
				LOG.info("inside nickname holds value");
				ownerPortalUserVehicle.setVehicleNickName(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getNickname());
			}
			ownerPortalUserVehiclePK.setVin(saveVehicleWrapper.getSaveVehicle()
					.getVehicle().getVin());
			ownerPortalUserVehicle.setMileage(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getMileage());
			ownerPortalUserVehicle.setAverageMileage(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getAverageMileage());
			ownerPortalUserVehicle.setDealerId(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getPreferredDealer());

			ownerPortalUserVehicle.setVehicleRelationCode(RELATION_CODE);
			ownerPortalUserVehicle.setCreateUserId(USERID);
			ownerPortalUserVehicle.setCreateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			ownerPortalUserVehicle.setUpdateUserId(USERID);
			ownerPortalUserVehicle.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			ownerPortalUserVehicle.setNotificationOptIn(NOTIFICATION_OPT);
			ownerPortalUserVehicle
					.setOwnerPortalUserVehiclePK(ownerPortalUserVehiclePK);
			osEm.persist(ownerPortalUserVehicle);
			response = "success";

		} catch (PersistenceException e) {
			LOG.error(
					"PersistenceException in saveUservehicleInfo for the vin ="
							+ saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin(), e);
			response = "failure";
		} catch (Exception e) {
			LOG.error(
					"Exception in saveUservehicleInfo for the vin = "
							+ saveVehicleWrapper.getSaveVehicle().getVehicle()
									.getVin(), e);
			response = "failure";
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use To check for test vin in pipeline table
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public ManualVehicleLookup vinLookup(String vin, EntityManager osEm) {

		ManualVehicleLookup result = null;
		try {

			List<ManualVehicleLookup> manualVehicleLoadList = osEm
					.createNamedQuery("ManualVehicleLookup.findByVin",
							ManualVehicleLookup.class).setParameter("vin", vin)
					.getResultList();

			if (Utility.isObjectNotNullorNotEmpty(manualVehicleLoadList)
					&& !(manualVehicleLoadList.isEmpty())) {

				result = manualVehicleLoadList.get(0);

			} else {
				LOG.info("Vehicle not available in pipeline for the vin = "
						+ vin);

			}
		} catch (NoResultException e) {
			LOG.error("VinLookup NoResultException for the vin =" + vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("Vinlookup ArrayIndexOutOfBoundsException for the vin ="
					+ vin, e);

		} catch (Exception e) {
			LOG.error("VinLookup General exception for the vin =" + vin, e);

		}

		return result;
	}

	/**
	 * @author x796314
	 * @use To retrieve vehicle information from OwnrPortlVhcl table.
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public OwnerPortalVehicle getVehicleInfo(String vin, EntityManager osEm) {

		OwnerPortalVehicle response = null;

		try {

			List<OwnerPortalVehicle> ownerPortalVehicle = osEm
					.createNamedQuery("OwnerPortalVehicle.findByVin",
							OwnerPortalVehicle.class).setParameter("vin", vin)
					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalVehicle)
					&& !(ownerPortalVehicle.isEmpty())) {
				response = ownerPortalVehicle.get(0);
			}
		} catch (NoResultException e) {
			LOG.error("getVehicleInfo NoResultException for the vin" + vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getVehicleInfo ArrayIndexOutOfBoundsException for the vin"
							+ vin, e);

		} catch (Exception e) {
			LOG.error("getVehicleInfo Exceptionfor the vin" + vin, e);

		}

		return response;
	}
	
	/**
	 * @author x116202
	 * @use To retrieve VehicleOptionTypecode from table.
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public OwnerPortalVehicleTelematicsCodeMaster getTelematicsCodeInfo(OwnerPortalVehicle vehicleInfo,
			EntityManager osEm) {

		OwnerPortalVehicleTelematicsCodeMaster response = null;
		
		try {
			
			List<OwnerPortalVehicleTelematicsCodeMaster> ownerPortalVehicleTelematicsCodeMaster = osEm
					.createNamedQuery(
							"OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYear",
							OwnerPortalVehicleTelematicsCodeMaster.class)
					.setParameter("vehicleModelCode",
							vehicleInfo.getVehicleModelCode())
					.setParameter("modelYear", vehicleInfo.getModelYearNumber())
					//.setParameter("vocList", vocList)
					.getResultList();
			
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalVehicleTelematicsCodeMaster)
					&& !(ownerPortalVehicleTelematicsCodeMaster.isEmpty())) {
				response = ownerPortalVehicleTelematicsCodeMaster.get(0);
			}
		}catch (NoResultException e) {
				LOG.error(
						"NoResultException in getTelematicsInfo for the model code ="
								+ vehicleInfo, e);
		}catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getVehicleInfo ArrayIndexOutOfBoundsException for the vin"
							+ vehicleInfo, e);
		} catch (Exception e) {
			LOG.error("getVehicleInfo Exceptionfor the vin" + vehicleInfo, e);
		}
		
		return response;
	}

	/**
	 * @author x796314
	 * @use To verify vehicle and owner association--removed
	 * @param validateDataWrapper
	 * @param opUser
	 * @param osEm
	 * @return
	 */
	public String validateUserVehicle(ValidateDataWrapper validateDataWrapper,
			OwnerPortalUser validUser, String brand, EntityManager osEm) {

		String response = "failure";

		try {

			List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = osEm
					.createNamedQuery("OwnerPortalUserVehicle.findByVin",
							OwnerPortalUserVehicle.class)
					.setParameter("vin",
							validateDataWrapper.getValidateData().getVin())
					.getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicleList)
					&& !(ownerPortalUserVehicleList.isEmpty())) {

				LOG.info("VIN is available in DB and check if mapped to same user ="
						+ validateDataWrapper.getValidateData().getVin());

				LOG.info("UserProfileId =" + validUser.getUserProfileId());

				List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
						.createNamedQuery(
								"OwnerPortalUserVehicle.findByUserProfileIdAndVin",
								OwnerPortalUserVehicle.class)
						.setParameter("userProfileId",
								validUser.getUserProfileId())
						.setParameter("vin",
								validateDataWrapper.getValidateData().getVin())
						.getResultList();

				if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
						&& !(ownerPortalUserVehicle.isEmpty())) {
					LOG.info("VIN is  mapped to same user ="
							+ validateDataWrapper.getValidateData().getVin());
					response = "sameUser";
				} else {
					LOG.info("VIN is not mapped to same user ="
							+ validateDataWrapper.getValidateData().getVin());
					response = "otherUser";

					/*OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference = new OwnerPortalUserVehicleFailedReference();
					long isInsertFailedRecord = saveFailedVehicleInfo1(
							validateDataWrapper, validUser,
							ownerPortalUserVehicleFailedReference, brand, osEm);

					if (isInsertFailedRecord != 0) {
						response = "otherUser";
					}*/

				}
			} else {
				LOG.info("vin not mapped to any user"
						+ validateDataWrapper.getValidateData().getVin());
				response = "notAvailable";
			}
		} catch (NoResultException e) {
			LOG.error(" validateUser NoResultException for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					" validateUser ArrayIndexOutOfBoundsException for the vin"
							+ validateDataWrapper.getValidateData().getVin(), e);
		} catch (Exception e) {

			LOG.error(" validateUser Exception for the vin"
					+ validateDataWrapper.getValidateData().getVin(), e);

		}
		LOG.info("Response from validateUserVehicle"+response);

		return response;
	}

	/**
	 * @author x178099
	 * @use to verify owner vehicle association
	 * @param usrPrflId
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public String validateVehicleOwner(String userProfileId, String vin,
			EntityManager osEm) {

		String response = "failure";

		try {

			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createNamedQuery("OwnerPortalUserVehicle.findByVin",
							OwnerPortalUserVehicle.class)
					.setParameter("vin", vin).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {

				List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = osEm
						.createNamedQuery(
								"OwnerPortalUserVehicle.findByUserProfileIdAndVin",
								OwnerPortalUserVehicle.class)
						.setParameter("userProfileId", userProfileId)
						.setParameter("vin", vin).getResultList();

				if (Utility
						.isObjectNotNullorNotEmpty(ownerPortalUserVehicleList)
						&& !(ownerPortalUserVehicleList.isEmpty())) {
					LOG.info("VIN " + vin + "is  mapped to same user");
					response = "sameUser";
				} else {
					LOG.info("VIN " + vin + "is  mapped to other user");
					response = "otherUser";
				}
			} else {
				LOG.info("Vin " + vin + " is not mapped to any user");
				response = "notAvailable";
			}

		} catch (NoResultException e) {
			LOG.error("validateVehicleOwner NoResultException for the vin"
					+ vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"validateVehicleOwner ArrayIndexOutOfBoundsException for the vin"
							+ vin, e);
		} catch (Exception e) {

			LOG.error("validateVehicleOwner Exception for the vin" + vin, e);

		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To fetch the user and vehicle information
	 * @param usrPrflId
	 * @param vin
	 * @return
	 */
	public OwnerPortalUserVehicle getUserVehicleInfo(String userProfileId,
			String vin, EntityManager osEm) {
		OwnerPortalUserVehicle response = null;

		try {
			LOG.info("Before fetching user and vehicle details for the vin ="
					+ vin + " mapped to the user profile id =" + userProfileId);

			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicle.findByUserProfileIdAndVin",
							OwnerPortalUserVehicle.class)
					.setParameter("userProfileId", userProfileId)
					.setParameter("vin", vin).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {
				LOG.info("VIN =" + vin + " is  mapped to same user"
						+ userProfileId);
				response = ownerPortalUserVehicle.get(0);
			} else {
				LOG.info("VIN =" + vin + "is not mapped to current user"
						+ userProfileId);

			}

		} catch (NoResultException e) {
			LOG.error(
					"Inside validateVehicleOwner NoResultException for the vin= "
							+ vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"Inside validateVehicleOwner ArrayIndexOutOfBoundsException for the vin= "
							+ vin, e);

		} catch (Exception e) {
			LOG.error("Inside validateVehicleOwner Exceptionfor the vin= "
					+ vin, e);

		}

		return response;
	}

	/**
	 * @author x796314
	 * @use method to update the vehicle details
	 * @param updateVehicleWrapper
	 * @param user
	 * @param osEm
	 * @return
	 */
	public String updateVehicle(UpdateVehicleWrapper updateVehicleWrapper,
			OwnerPortalUser ownerPortalUser, EntityManager osEm) {

		String response = "failure";

		try {

			List<OwnerPortalVehicle> ownerPortalVehicle = osEm
					.createNamedQuery("OwnerPortalVehicle.findByVin",
							OwnerPortalVehicle.class)
					.setParameter(
							"vin",
							updateVehicleWrapper.getUpdateVehicle()
									.getVehicle().getVin().trim())
					.getResultList();

			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createNamedQuery("OwnerPortalUserVehicle.findByVin",
							OwnerPortalUserVehicle.class)
					.setParameter(
							"vin",
							updateVehicleWrapper.getUpdateVehicle()
									.getVehicle().getVin().trim())
					.getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalVehicle)
					&& !(ownerPortalVehicle.isEmpty())) {
				LOG.info("updateVehicle vehicle table not null for the vin ="
						+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
								.getVin());

				if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
						&& !(ownerPortalUserVehicle.isEmpty())) {

					String insertUserVehicle = updateUserVehicleInfo(
							updateVehicleWrapper,
							ownerPortalUserVehicle.get(0), ownerPortalUser,
							osEm);
					if (insertUserVehicle.equalsIgnoreCase(SUCCESS)) {
						response = "success";
					}
				}

			} else {
				LOG.info("vin "
						+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
								.getVin() + "not available");
			}

		} catch (NoResultException e) {
			LOG.error(
					"Exception in update vehicle NoResultException for the vin"
							+ updateVehicleWrapper.getUpdateVehicle()
									.getVehicle().getVin(), e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"update vehicle ArrayIndexOutOfBoundsException for the vin"
							+ updateVehicleWrapper.getUpdateVehicle()
									.getVehicle().getVin(), e);

		} catch (Exception e) {
			LOG.error("Exception in update vehicle service for the vin"
					+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin(), e);

		}
		return response;

	}

	/**
	 * @author x796314
	 * @use method to update the vehicle details in vehicle table
	 */
	public String updateVehicleInfo(UpdateVehicleWrapper updateVehicleWrapper,
			OwnerPortalVehicle ownerPortalVehicle, EntityManager osEm) {
		String response = "failure";

		try {

			ownerPortalVehicle.setVin(updateVehicleWrapper.getUpdateVehicle()
					.getVehicle().getVin());

			ownerPortalVehicle.setUpdateUserId(USERID);
			ownerPortalVehicle.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			if (Utility.isStringNotNullorNotEmpty(updateVehicleWrapper
					.getUpdateVehicle().getBrand())) {

				if (updateVehicleWrapper.getUpdateVehicle().getBrand()
						.equalsIgnoreCase(INFINITI)) {

					ownerPortalVehicle.setVehicleMakeCode("I");
					ownerPortalVehicle.setVehicleModelName(updateVehicleWrapper
							.getUpdateVehicle().getVehicle().getModelName());
				} else if (updateVehicleWrapper.getUpdateVehicle().getBrand()
						.equalsIgnoreCase(NISSAN)) {

					ownerPortalVehicle.setVehicleMakeCode("N");
					ownerPortalVehicle.setVehicleModelName(updateVehicleWrapper
							.getUpdateVehicle().getVehicle().getModelName());
				}
			}

			ownerPortalVehicle.setVehicleModelCode(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getModelCode());
			ownerPortalVehicle.setVehicleInteriorColorCode(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getInteriorColorCode());
			ownerPortalVehicle.setVehicleInteriorColorName(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getInteriorColorName());
			ownerPortalVehicle.setVehicleExteriorColorCode(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getExteriorColorCode());
			ownerPortalVehicle.setVehicleExteriorColorName(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getExteriorColorName());
			ownerPortalVehicle.setVehicleOptionCode(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getOptionCode());
			ownerPortalVehicle.setModelYearNumber(updateVehicleWrapper
					.getUpdateVehicle().getVehicle().getModelYear());

			osEm.merge(ownerPortalVehicle);
			LOG.info("Update Vehicle Details success for the vin "
					+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin());
			response = "success";
		}

		catch (PersistenceException e) {
			LOG.error("update vehicle Info PersistenceException for the vin ="
					+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin(), e);
			response = "failure";
		} catch (Exception e) {
			LOG.error("update vehicle Info Exception for the vin ="
					+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin(), e);
			response = "failure";
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use method to update the vehicle details in user vehicle table
	 */
	public String updateUserVehicleInfo(
			UpdateVehicleWrapper updateVehicleWrapper,
			OwnerPortalUserVehicle ownerPortalUserVehicle,
			OwnerPortalUser ownerPortalUser, EntityManager osEm) {

		String response = "failure";

		try {

			ownerPortalUserVehicle.getOwnerPortalUserVehiclePK().setVin(
					updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin());
			if (Utility.isStringNotNullorNotEmpty(updateVehicleWrapper
					.getUpdateVehicle().getBrand())) {
				if (updateVehicleWrapper.getUpdateVehicle().getBrand()
						.equalsIgnoreCase(INFINITI)) {

					ownerPortalUserVehicle.getOwnerPortalUserVehiclePK()
							.setUserProfileId(
									ownerPortalUser.getUserProfileId());
					if (updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getNickname() != null) {
						ownerPortalUserVehicle
								.setVehicleNickName(updateVehicleWrapper
										.getUpdateVehicle().getVehicle()
										.getNickname());
					}

				} else if (updateVehicleWrapper.getUpdateVehicle().getBrand()
						.equalsIgnoreCase(NISSAN)) {

					ownerPortalUserVehicle.getOwnerPortalUserVehiclePK()
							.setUserProfileId(
									ownerPortalUser.getUserProfileId());
					if (updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getNickname() != null) {
						ownerPortalUserVehicle
								.setVehicleNickName(updateVehicleWrapper
										.getUpdateVehicle().getVehicle()
										.getNickname());
					}

				}
			}
			if (updateVehicleWrapper.getUpdateVehicle().getVehicle()
					.getMileage() != null) {
				ownerPortalUserVehicle.setMileage(updateVehicleWrapper
						.getUpdateVehicle().getVehicle().getMileage());
			}

			if (updateVehicleWrapper.getUpdateVehicle().getVehicle()
					.getAverageMileage() != null) {
				ownerPortalUserVehicle.setAverageMileage(updateVehicleWrapper
						.getUpdateVehicle().getVehicle().getAverageMileage());
			}

			if (updateVehicleWrapper.getUpdateVehicle().getVehicle()
					.getPreferredDealer() != null) {
				ownerPortalUserVehicle.setDealerId(updateVehicleWrapper
						.getUpdateVehicle().getVehicle().getPreferredDealer());
			}

			ownerPortalUserVehicle.setUpdateUserId(USERID);
			ownerPortalUserVehicle.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));

			osEm.merge(ownerPortalUserVehicle);
			response = "success";

		} catch (PersistenceException e) {
			LOG.error("updateUserVehicleInfo PersistenceException for the vin="
					+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin(), e);
			response = "failure";
		} catch (Exception e) {
			LOG.error("updateUserVehicleInfo General Exception for the vin ="
					+ updateVehicleWrapper.getUpdateVehicle().getVehicle()
							.getVin(), e);
			response = "failure";
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To retrieve driver details. check if vin is mapped to users with
	 *      vehicle relation code 'D'.Fetch the driver details using user
	 *      profile id.
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public OwnerPortalUser getDriverInfo(String vin, EntityManager osEm) {

		OwnerPortalUser response = null;
		List<OwnerPortalUserVehicle> ownerPortalUserVehicle = null;
		try {
			ownerPortalUserVehicle = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicle.findByVinAndvehicleRelationCode",
							OwnerPortalUserVehicle.class)
					.setParameter("vin", vin)
					.setParameter("vehicleRelationCode", DRIVER)
					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {
				OwnerPortalUserVehicle driverVehicles = ownerPortalUserVehicle
						.get(0);
				LOG.info("driver vehicle details exist for the vin = " + vin);

				List<OwnerPortalUser> ownerPortalUser = osEm
						.createNamedQuery(
								"OwnerPortalUser.findByuserProfileId",
								OwnerPortalUser.class)
						.setParameter(
								"userProfileId",
								driverVehicles.getOwnerPortalUserVehiclePK()
										.getUserProfileId()).getResultList();
				response = ownerPortalUser.get(0);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getDriverInfo for the vin ="
							+ vin, e);
		} catch (NoResultException e) {
			LOG.error("NoResultException in getDriverInfo for the vin =" + vin,
					e);
		} catch (Exception e) {
			LOG.error("Exception in getDriverInfo for the vin =" + vin, e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To retrieve driver vehicle details. check if vin is mapped to users
	 *      with vehicle relation code 'D'.Fetch the driver vehicle details
	 *      using user profile id.
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public List<OwnerPortalUserVehicle> getDriverVehicleInfo(String vin,
			EntityManager osEm) {

		List<OwnerPortalUserVehicle> response = null;

		try {
			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicle.findByVinAndvehicleRelationCode",
							OwnerPortalUserVehicle.class)
					.setParameter("vin", vin)
					.setParameter("vehicleRelationCode", DRIVER)
					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {

				response = ownerPortalUserVehicle;
			}
		} catch (NoResultException e) {
			LOG.error("NoResultException in getDriverVehicleInfo for the vin"
					+ vin, e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getDriverVehicleInfo for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in getDriverVehicleInfo for the vin" + vin, e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To retrieve vehicle carwings details.
	 * @param vin
	 * @return
	 */
	public VehicleCarwings getVehicleCarwings(String vin, EntityManager osEm) {
		List<VehicleCarwings> vehicleCarwings = null;
		VehicleCarwings response = null;

		try {
			vehicleCarwings = osEm
					.createNamedQuery("VehicleCarwings.findByVin",
							VehicleCarwings.class).setParameter("vin", vin)
					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(vehicleCarwings)
					&& !(vehicleCarwings.isEmpty())) {
				response = vehicleCarwings.get(0);
			}

		} catch (NoResultException e) {
			LOG.error("NoResultException in getVehicleCarwings for the vin"
					+ vin, e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getVehicleCarwings ArrayIndexOutOfBoundsException for the vin"
							+ vin, e);

		} catch (Exception e) {
			LOG.error("Exception in getVehicleCarwings for the vin" + vin, e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To retrieve event notification details.
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public List<EventNotification> getEventNotification(String vin,
			EntityManager osEm) {
		List<EventNotification> response = null;
		try {
			List<EventNotification> eventNotification = osEm
					.createQuery(
							"select o from EventNotification o where o.vin=:vin",
							EventNotification.class).setParameter("vin", vin)
					.getResultList();

			if (Utility.isObjectNotNullorNotEmpty(eventNotification)
					&& !(eventNotification.isEmpty())) {

				LOG.info("Event Notification exist for the vin =" + vin);
				response = eventNotification;

			}
		} catch (NoResultException e) {
			LOG.error("NoResultException in getEventNotification for the vin ="
					+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in getEventNotification for the vin = " + vin,
					e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To update the owner account status as ACL if no vehicle available in
	 *      user account.
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */
	public String updateOwnerAccountStatus(String userProfileId,
			EntityManager osEm) {

		String response = "failure";
		try {
			Query query = osEm
					.createQuery("UPDATE OwnerPortalUser AS o SET o.accountStatusCode=:accountStatusCode WHERE o.userProfileId=:userProfileId");
			query.setParameter("accountStatusCode", "ACL");
			query.setParameter("userProfileId", userProfileId);

			int noOfUpdatedRows = query.executeUpdate();

			if (noOfUpdatedRows > 0) {

				LOG.info("Updated rows" + noOfUpdatedRows);
			}

			response = "success";

		} catch (Exception e) {
			LOG.info("Exception in updateOwnerAccountStatus", e);
		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To delete driver details.
	 * @param vin
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */
	public String deleteDriver(String vin, String userProfileId,
			EntityManager osEm) {
		String response = "failure";
		try {
			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createQuery(
							"delete from OwnerPortalUserVehicle o where o.vin=:vin and o.OwnerPortalUserVehiclePK.userProfileId = :userProfileId",
							OwnerPortalUserVehicle.class)
					.setParameter("vin", vin)
					.setParameter("userProfileId", userProfileId)
					.getResultList();

			LOG.info("Driver deleted successfully for the vin" + vin
					+ " and user" + userProfileId);
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {
				response = "success";
			}
		} catch (NoResultException e) {
			LOG.error("deleteDriver NoResultException for the vin" + vin
					+ " and user" + userProfileId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("deleteDriver ArrayIndexOutOfBoundsException for the vin"
					+ vin + " and user" + userProfileId, e);

		} catch (Exception e) {
			LOG.error("Exception in deleteDriver for the vin" + vin
					+ " and user" + userProfileId, e);
		}
		return response;
	}

	public Set<String> getTelematicsInfo(OwnerPortalVehicle vehicleInfo,
			EntityManager osEm) {
		String response = "";
		Set<String> status = new HashSet<String>();
		//List<String> vocList = new ArrayList<String>();
		//if(vehicleInfo.getVehicleOptionCode() != null){
//			if(vehicleInfo.getVehicleOptionCode().length()>3){
//				String []voc = vehicleInfo.getVehicleOptionCode().split("(?<=\\G...)");
//				for(String st : voc){
//					vocList.add(st);
//				}
//			}else{
//				vocList.add(vehicleInfo.getVehicleOptionCode());
//			}
			
			try {
				
	//			List<OwnerPortalVehicleTelematicsCodeMaster> ownerPortalVehicleTelematicsCodeMaster = osEm
	//					.createNamedQuery(
	//							"OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYearandOptionCode",
	//							OwnerPortalVehicleTelematicsCodeMaster.class)
	//					.setParameter("vehicleModelCode",
	//							vehicleInfo.getVehicleModelCode())
	//					.setParameter("modelYear", vehicleInfo.getModelYearNumber())
	//					.setParameter("vehicleOptionCode", vehicleInfo.getVehicleOptionCode())
	//					.getResultList();
				
				List<OwnerPortalVehicleTelematicsCodeMaster> ownerPortalVehicleTelematicsCodeMaster = osEm
						.createNamedQuery(
								"OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYear",
								OwnerPortalVehicleTelematicsCodeMaster.class)
						.setParameter("vehicleModelCode",
								vehicleInfo.getVehicleModelCode())
						.setParameter("modelYear", vehicleInfo.getModelYearNumber())
						//.setParameter("vocList", vocList)
						.getResultList();
	
				if (Utility
						.isObjectNotNullorNotEmpty(ownerPortalVehicleTelematicsCodeMaster)
						&& !(ownerPortalVehicleTelematicsCodeMaster.isEmpty())) {
	
					Iterator<OwnerPortalVehicleTelematicsCodeMaster> iterator = ownerPortalVehicleTelematicsCodeMaster
							.iterator();
	
					OwnerPortalVehicleTelematicsCodeMaster codeMaster;
	
					while (iterator.hasNext()) {
	
						codeMaster = iterator.next();
	
						if (codeMaster != null) {
							String optionCode = codeMaster
									.getOwnerPortalVehicleTelematicsCodeMasterPK()
									.getVehicleTelematicsOptionCode();
							if(optionCode.equalsIgnoreCase("BSK")){
								response = codeMaster
										.getOwnerPortalVehicleTelematicsCodeMasterPK()
										.getVehicleTelematicsOptionTypeCode();
								LOG.info("Telematics Option Code (Model"
										+ vehicleInfo.getVehicleModelCode()
										+ ") from the  Telematics Code master Table "
										+ response);
								status.add(response);
							}else{
								if(vehicleInfo.getVehicleOptionCode().contains(optionCode)){
									response = codeMaster
											.getOwnerPortalVehicleTelematicsCodeMasterPK()
											.getVehicleTelematicsOptionTypeCode();
									LOG.info("Telematics Option Code (Model"
											+ vehicleInfo.getVehicleModelCode()
											+ ") from the  Telematics Code master Table "
											+ response);
									status.add(response);
								}
							}
						} else {
							LOG.info("Telematics option code for that vin is not available in DB");
							return status;
						}
	
					}
				}
	
			} catch (NoResultException e) {
				LOG.error(
						"NoResultException in getTelematicsInfo for the model code ="
								+ vehicleInfo.getVehicleModelCode(), e);
			} catch (ArrayIndexOutOfBoundsException e) {
				LOG.error(
						"getTelematicsInfo ArrayIndexOutOfBoundsException for the model code="
								+ vehicleInfo.getVehicleModelCode(), e);
	
			} catch (Exception e) {
				LOG.error("Exception in getTelematicsInfo for the model code ="
						+ vehicleInfo.getVehicleModelCode(), e);
			}
		return status;
	}

	/**
	 * @author x178099
	 * @use To retrieve user vehicle details.
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */

	public List<OwnerPortalUserVehicle> checkUserVehicle(String userProfileId,
			EntityManager osEm) {

		List<OwnerPortalUserVehicle> response = null;
		List<OwnerPortalUserVehicle> ownerPortalUserVehicle;
		try {
			ownerPortalUserVehicle = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicle.findByUserProfileId",
							OwnerPortalUserVehicle.class)
					.setParameter("userProfileId", userProfileId)
					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {
				LOG.info("user vehicle exist for the profile id ="
						+ userProfileId);
				response = ownerPortalUserVehicle;
			}
		} catch (NoResultException e) {
			LOG.error(
					"getDriverVehicleInfo NoResultExceptionfor the profile id ="
							+ userProfileId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getDriverVehicleInfo ArrayIndexOutOfBoundsException for the profile id ="
							+ userProfileId, e);

		} catch (Exception e) {
			LOG.error("Exception in getDriverVehicleInfo for the profile id ="
					+ userProfileId, e);
		}
		return response;
	}

	/**
	 * @author x796314
	 * @use To delete vehicle info from vehicle user,event notification,vehicle
	 *      carwings table. check for drivers mapped to vin.remove association
	 *      from vin. If no vehicles found for driver,update account status code
	 *      as ACL (Lite). Delete event notification,carwings data if any for
	 *      LEAF vehicles.
	 * @param vin
	 * @return
	 */

	public String deleteVehicle(String vin,
			OwnerPortalUserVehicle ownerPortalUserVehicle,
			OwnerPortalVehicle vehicleInfo, EntityManager osEm) {
		VehicleCarwings vehicleCarwings = null;
		List<EventNotification> eventNotification = null;
		List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = null;
		List<TermsAndConditionsAgreementSt> TermsAndConditionsAgreementStList=null;//Added for delete vehicle by aniket
		List<TelematicsSvcLg> TelematicsSvcLgList=null;//Added for delete vehicle
		String response = "failure";
		
		try {
			if (Utility.isObjectNotNullorNotEmpty(vehicleInfo)) {
			
			//Added terms and condition for delete vehicle by aniket	
			String ownerProfileId = ownerPortalUserVehicle.getOwnerPortalUserVehiclePK().getUserProfileId();
			
			
			LOG.info("Inside if condition for the delete vehicle" + vin+"------------"+ownerProfileId);
			
			TermsAndConditionsAgreementStList= getTermsAndConditionsAgreementSt(vin, ownerProfileId, osEm);
			if(TermsAndConditionsAgreementStList != null && TermsAndConditionsAgreementStList.size()>0){
				LOG.info("Inside terms and condition for the delete vehicle");
				for(TermsAndConditionsAgreementSt tac : TermsAndConditionsAgreementStList){
					osEm.remove(tac);
					LOG.info("vin is deleted from terms and condition "+vin);					
				}
				LOG.info("After terms and condition table vin deletion");
				
			}
			LOG.info("Going for EV vehicle vin deletion");
			//Delete for event Notification and vehicle carwings
			if (("LEAF")
					.equalsIgnoreCase(vehicleInfo.getVehicleModelName())
					|| ("LEF").equalsIgnoreCase(vehicleInfo
							.getVehicleModelName())) {

				eventNotification = getEventNotification(vin, osEm);

				if (Utility.isObjectNotNullorNotEmpty(eventNotification)) {

					LOG.info("Event Notification exist for the vin" + vin);
					Query query = osEm
							.createQuery("DELETE FROM EventNotification AS o WHERE o.vin=:vin");

					query.setParameter("vin", vin);

					int noOfDeletedRows = query.executeUpdate();

					if (noOfDeletedRows > 0) {

						LOG.info("Total no.of events Deleted for the vin="
								+ vin + "is=" + noOfDeletedRows);
					}

				}
				vehicleCarwings = getVehicleCarwings(vin, osEm);
				if (Utility.isObjectNotNullorNotEmpty(vehicleCarwings)) {

					osEm.remove(vehicleCarwings);
					LOG.info("Carwings Details Deleted for the vin" + vin);

				}
			}
			LOG.info("Going for telematics SVC_LG vin deletion");
			////TLMTCS_SVC_LG for delete vehicle by aniket
			TelematicsSvcLgList = getTelematicsSvcLg(vin, osEm);
			LOG.info("Inside telematics SVC_LG vin deletion "+vin);
			if(TelematicsSvcLgList !=null && TelematicsSvcLgList.size()>0){
				LOG.info("Inside telematics SVC_LG vin deletion");
				for(TelematicsSvcLg tsl : TelematicsSvcLgList){
					osEm.remove(tsl);
				}
				LOG.info("vin deleted for telematics service log"+vin);
			}
			LOG.info("After telematics SVC_LG vin deletion");
			
			LOG.info("Going for Owner portal user vehicle vin deletion"+vin);
			
			ownerPortalUserVehicle= osEm.merge(ownerPortalUserVehicle);//merging and then removing
			osEm.remove(ownerPortalUserVehicle);
			LOG.info("User vehicle details deleted from owner portal user vehicle for the vin" + vin
					+ "mapped to the owner" + ownerProfileId);
			
			}
			response = "success";

		} catch (NoResultException e) {
			LOG.error("NoResultException in deleteVehicle for the vin" + vin, e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"Inside deleteVehicle ArrayIndexOutOfBoundsException for the vin"
							+ vin, e);

		} catch (Exception e) {
			LOG.error("Inside deleteVehicle General Exception for the vin"
					+ vin, e);

		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To fetch the user and vehicle information
	 * @param usrPrflId
	 * @param vin
	 * @param osEm
	 * @return
	 */
	public List<OwnerPortalUserVehicle> getOwnerVehicleDetails(
			String userProfileId, EntityManager osEm) {

		List<OwnerPortalUserVehicle> response = null;

		try {
			LOG.info("Inside getOwnerVehicleDetails");
			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicle.findByUserProfileId",
							OwnerPortalUserVehicle.class)
					.setParameter("userProfileId", userProfileId)
					.getResultList();
			LOG.info("After getOwnerVehicleDetails" + ownerPortalUserVehicle);
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {
				LOG.info("VIN exist in user profile for the user ="
						+ userProfileId);
				response = ownerPortalUserVehicle;
			} else {
				LOG.info("VIN does not exist for the user =" + userProfileId);

			}

		} catch (Exception e) {
			LOG.error("Inside validateVehicleOwner Exception for the user ="
					+ userProfileId, e);

		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To fetch the vehicle object from the user vehicle list
	 * @param userVehicleInfo
	 * @param osEm
	 * @return
	 */
	public OwnerPortalVehicle fetchVehicleInfoFromVehicleList(String vin,
			EntityManager osEm) {
		OwnerPortalVehicle vehicle = null;
		LOG.info("vehicle is" + vin);

		OwnerPortalVehicle ownerPortalVehicle = osEm
				.createNamedQuery("OwnerPortalVehicle.findByVin",
						OwnerPortalVehicle.class).setParameter("vin", vin)
				.getSingleResult();
		if (Utility.isObjectNotNullorNotEmpty(ownerPortalVehicle)) {
			vehicle = ownerPortalVehicle;
		}

		LOG.info("vehicle in result is" + vehicle);
		return vehicle;
	}

	/**
	 * @author x055765
	 * @use To fetch the nissan vins
	 * @param userVehicleInfo
	 * @param osEm
	 * @return
	 */
	public List<OwnerPortalVehicle> fetchOnlyNissanVinsAndVin(
			String vehicleMakeCode, String vin, EntityManager osEm) {
		List<OwnerPortalVehicle> vehicle = null;
		LOG.info("vehiclemakecode is" + vehicleMakeCode);

		List<OwnerPortalVehicle> ownerPortalVehicle = osEm
				.createNamedQuery(
						"OwnerPortalVehicle.findByVehicleMakeCodeAndVin",
						OwnerPortalVehicle.class)
				.setParameter("vehicleMakeCode", vehicleMakeCode)
				.setParameter("vin", vin).getResultList();
		LOG.info("nissanVins in vehicleservice"
				+ ownerPortalVehicle.get(0).getVehicleMakeCode() + "&& vin"
				+ ownerPortalVehicle.get(0).getVin());
		if (Utility.isObjectNotNullorNotEmpty(ownerPortalVehicle)) {
			vehicle = ownerPortalVehicle;
		}

		return vehicle;
	}


	/**
	 * @author rs101547
	 * @use overridden method to get subscription product code
	 * @param subscriptionProductCode
	 * @return
	 */
	public List<OwnerPortalSubScriptionProduct> getSubScriptionProduct(
			String subscriptionProductCode, EntityManager osEm) {

		List<OwnerPortalSubScriptionProduct> response = null;
		try {

			List<OwnerPortalSubScriptionProduct> ownerPortalSubScriptionProduct = osEm
					.createNamedQuery(
							"OwnerPortalSubScriptionProduct.findBySubscriptionProductCode",
							OwnerPortalSubScriptionProduct.class)
					.setParameter("subscriptionProductCode",
							subscriptionProductCode).getResultList();

			if (Utility
					.isObjectNotNullorNotEmpty(ownerPortalSubScriptionProduct)
					&& !(ownerPortalSubScriptionProduct.isEmpty())) {
				LOG.info("subscription available for product code ="
						+ subscriptionProductCode);
				response = ownerPortalSubScriptionProduct;
			} else {
				LOG.info("subscription product code does not exist ="
						+ subscriptionProductCode);

			}

		} catch (Exception e) {

			LOG.error(" get susbscription product code exception ="
					+ subscriptionProductCode, e);

		}
		LOG.info("getSubScriptionProduct complete");
		return response;

	}

	public String createToken(OwnerPortalUser ownerPortalUser,
			OwnerPortalVehicle ownerPortalVehicle, EntityManager osEm) {
		LOG.info("inside token0");

		String token = "";
		try {
			token = "WEB_" + UUID.randomUUID().toString();
			OwnerPortalToken ownerPortalToken = new OwnerPortalToken();

			ownerPortalToken.setOwnerToken(token);
			ownerPortalToken.setSelectedVin(ownerPortalVehicle.getVin());
			ownerPortalToken.setTokenStatusCode("A");
			ownerPortalToken.setUserprofileId(ownerPortalUser
					.getUserProfileId());
			ownerPortalToken.setCreatedate(new Timestamp(System
					.currentTimeMillis()));
			ownerPortalToken.setCreateUserId(USERID);
			ownerPortalToken.setEnrollmentTypeCode(CUSTOMERCODE);

			osEm.persist(ownerPortalToken);
			LOG.info("inside token0 after persisit");

		} catch (Exception e) {
			LOG.error("Inside create token exception =" + e);

		}
		LOG.info("inside token0 complete" + token);

		return token;

	}

	public ModelLineMapping modelNameLookup(String modelCode, EntityManager osEm) {

		ModelLineMapping result = null;
		try {

			List<ModelLineMapping> modelLineMappingList = osEm
					.createNamedQuery("ModelLineMapping.findByModelCode",
							ModelLineMapping.class)
					.setParameter("modelCode", modelCode).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(modelLineMappingList)
					&& !(modelLineMappingList.isEmpty())) {

				result = modelLineMappingList.get(0);

			} else {
				LOG.info("model not available in mapping for code = "
						+ modelCode);

			}
		} catch (NoResultException e) {
			LOG.error("model lookup NoResultException for the modelCode ="
					+ modelCode, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"model lookup ArrayIndexOutOfBoundsException for the modelCode ="
							+ modelCode, e);

		} catch (Exception e) {
			LOG.error("model lookup General exception for the modelCode ="
					+ modelCode, e);

		}

		return result;

	}

	/**
	 * @author rs101547
	 * @use To update vehicle details in OwnrPortlVhcl table if record exists
	 * @param ownerPortalVehicle
	 * @param saveVehicleWrapper
	 * @param osEm
	 * @return
	 */
	public String updateExistingVehicleInfo(
			OwnerPortalVehicle ownerPortalVehicle,
			SaveVehicleWrapper saveVehicleWrapper, EntityManager osEm) {
		String response = "failure";
		ManualVehicleLookup vehicleLookUp = null;
		ModelLineMapping modelNameLookupList = null;
		try {
			if (Utility.isStringNotNullorNotEmpty(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getVin())) {
				vehicleLookUp = vinLookup(saveVehicleWrapper.getSaveVehicle()
						.getVehicle().getVin(), osEm);
				if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
					if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
							.getModelLineCode()))
						modelNameLookupList = modelNameLookup(
								vehicleLookUp.getModelLineCode(), osEm);
				}
			}

			ownerPortalVehicle.setVin(saveVehicleWrapper.getSaveVehicle()
					.getVehicle().getVin());
			ownerPortalVehicle.setCreateUserId(USERID);
			ownerPortalVehicle.setCreateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			ownerPortalVehicle.setUpdateUserId(USERID);
			ownerPortalVehicle.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			if (Utility.isStringNotNullorNotEmpty(saveVehicleWrapper
					.getSaveVehicle().getBrand())
					|| !("").equalsIgnoreCase(saveVehicleWrapper
							.getSaveVehicle().getBrand())) {
				if (saveVehicleWrapper.getSaveVehicle().getBrand()
						.equalsIgnoreCase(INFINITI)) {
					ownerPortalVehicle.setVehicleMakeCode(INFINITI);
				} else if (saveVehicleWrapper.getSaveVehicle().getBrand()
						.equalsIgnoreCase(NISSAN)) {
					ownerPortalVehicle.setVehicleMakeCode(NISSAN);
				}
			}
			// Setting model Line mapping model name if available
			if (Utility.isObjectNotNullorNotEmpty(modelNameLookupList)) {
				if (Utility.isStringNotNullorNotEmpty(modelNameLookupList
						.getModelName())) {
					LOG.info("model name==="
							+ modelNameLookupList.getModelName());
					ownerPortalVehicle.setVehicleModelName(modelNameLookupList
							.getModelName());
				} else {
					ownerPortalVehicle.setVehicleModelName(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelName());
				}
			} else {
				ownerPortalVehicle.setVehicleModelName(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelName());
			}
			// Setting vehicle look up mapping model code if available
			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				LOG.info("model code===" + vehicleLookUp.getModelCode());
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getModelCode())) {
					ownerPortalVehicle.setVehicleModelCode(vehicleLookUp
							.getModelCode());
				} else {
					ownerPortalVehicle.setVehicleModelCode(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelCode());
				}
			} else {
				ownerPortalVehicle.setVehicleModelCode(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelCode());
			}
			// Setting vehicle look up mapping interior color code if available
			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getInteriorColorCode())) {
					LOG.info("color code==="
							+ vehicleLookUp.getInteriorColorCode());
					ownerPortalVehicle
							.setVehicleInteriorColorCode(vehicleLookUp
									.getInteriorColorCode());
				} else {
					ownerPortalVehicle
							.setVehicleInteriorColorCode(saveVehicleWrapper
									.getSaveVehicle().getVehicle()
									.getInteriorColorCode());
				}
			} else {
				ownerPortalVehicle
						.setVehicleInteriorColorCode(saveVehicleWrapper
								.getSaveVehicle().getVehicle()
								.getInteriorColorCode());
			}
			// Setting vehicle look up mapping model line code if available
//			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
//				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
//						.getModelLineCode())) {
//					LOG.info("line code===" + vehicleLookUp.getModelLineCode());
//					ownerPortalVehicle.setModelLineCode(vehicleLookUp
//							.getModelLineCode());
//				} else {
//					ownerPortalVehicle.setModelLineCode(saveVehicleWrapper
//							.getSaveVehicle().getVehicle().getModelLineCode());
//				}
//			} else {
//				ownerPortalVehicle.setModelLineCode(saveVehicleWrapper
//						.getSaveVehicle().getVehicle().getModelLineCode());
//			}
			ownerPortalVehicle.setVehicleInteriorColorName(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getInteriorColorName());
			ownerPortalVehicle.setVehicleExteriorColorCode(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getExteriorColorCode());
			ownerPortalVehicle.setVehicleExteriorColorName(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getExteriorColorName());
			ownerPortalVehicle.setVehicleOptionCode(saveVehicleWrapper
					.getSaveVehicle().getVehicle().getOptionCode());
			// Setting vehicle look up mapping model year if available
	
			if (Utility.isObjectNotNullorNotEmpty(vehicleLookUp)) {
				if (Utility.isStringNotNullorNotEmpty(vehicleLookUp
						.getVehicleModelYear() + "")) {
					ownerPortalVehicle.setModelYearNumber(vehicleLookUp
							.getVehicleModelYear() + "");
					LOG.info("year code==="
							+ vehicleLookUp.getVehicleModelYear());
				} else {
					ownerPortalVehicle.setModelYearNumber(saveVehicleWrapper
							.getSaveVehicle().getVehicle().getModelYear());
				}
			} else {
				ownerPortalVehicle.setModelYearNumber(saveVehicleWrapper
						.getSaveVehicle().getVehicle().getModelYear());
			}
			ownerPortalVehicle.setLevelCharger("N");
			osEm.merge(ownerPortalVehicle);
			LOG.info("Save Vehicle Information success for the vin"
					+ saveVehicleWrapper.getSaveVehicle().getVehicle().getVin());
			response = "success";
		} catch (PersistenceException e) {
			LOG.error("PersistenceException in save vehicle info ", e);
		} catch (Exception e) {
			LOG.error("Exception in save vehicle info", e);
		}
		return response;
	}

	/**
	 * @author x787640
	 * @use To get the description for the recall Type Code
	 * @param typeCode
	 * @param asEm
	 * @return
	 */
	public String recallTypeDescription(String typeCode, EntityManager asEm) {
		String recallTypeDescription = "";
		try {
			recallTypeDescription = (String) asEm
					.createQuery(
							"SELECT r.recallTypeDescription FROM RecallTypeLookup r WHERE r.recallTypeCode LIKE :custValue")
					.setParameter("custValue", typeCode).getSingleResult();
			LOG.info("Recall type description for the type code: " + typeCode
					+ " is " + recallTypeDescription);

		} catch (Exception e) {
			LOG.info("Recall type code is not valid", e);
			recallTypeDescription = INVALID_RECALL_TYPE_MESSAGE;
		}
		return recallTypeDescription;
	}

	/**
	 * @author x787640
	 * @use To get the AS Service Contract Product details based on the Product
	 *      Code
	 * @param typeCode
	 * @param asEm
	 * @return
	 */

	public List<ASServiceContractCategoryLookup> getASServiceContractCategoryDetails(
			String asServiceContractCategoryCode, EntityManager asEm) {
		List<ASServiceContractCategoryLookup> asServiceContractCategoryList = null;
		try {
			asServiceContractCategoryList = asEm
					.createNamedQuery(
							"ASServiceContractCategoryLookup.findByServiceContractCategoryCode",
							ASServiceContractCategoryLookup.class)
					.setParameter("serviceContractCategoryCode",
							asServiceContractCategoryCode).getResultList();
		} catch (Exception e) {
			LOG.info("Invalid product code", e);
		}

		return asServiceContractCategoryList;
	}

	public List<VehicleSpecification> getVehicleSpecifications(
			String modelCode, EntityManager osEm) {

		List<VehicleSpecification> response = null;
		try {

			List<VehicleSpecification> vehicleSpecification = osEm
					.createNamedQuery("VehicleSpecification.modelCode",
							VehicleSpecification.class)
					.setParameter("modelCode", modelCode).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(vehicleSpecification)
					&& !(vehicleSpecification.isEmpty())) {
				LOG.info("Specifications available for the code =" + modelCode);
				response = vehicleSpecification;
			} else {
				LOG.info("Specifications not available for the code  ="
						+ modelCode);

			}

		} catch (Exception e) {

			LOG.error(
					" get VehicleSpecifications code exception =" + modelCode,
					e);

		}

		LOG.info("getVehicleSpecifications complete");
		return response;
	}

	public List<Equipment> getEquipmentInfo(String modelCode, EntityManager osEm) {

		List<Equipment> response = null;
		try {

			List<Equipment> equipment = osEm
					.createNamedQuery("Equipment.modelCode", Equipment.class)
					.setParameter("modelCode", modelCode).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(equipment)
					&& !(equipment.isEmpty())) {
				LOG.info("Equipment Info  available for the code =" + modelCode);
				response = equipment;
			} else {
				LOG.info("Equipment Info not available for the code  ="
						+ modelCode);

			}

		} catch (Exception e) {

			LOG.error("getEquipmentInfo code exception =" + modelCode, e);

		}

		LOG.info("getEquipmentInfo complete");
		return response;

	}

	public String validVehicle(String vin, String userProfileId,
			EntityManager osEm) {

		String response = "failure";

		try {

			List<OwnerPortalUserVehicleFailedReference> ownerPortalUserVehicleFailedReference = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicleFailedReference.findByuserProfileIdAndvin",
							OwnerPortalUserVehicleFailedReference.class)
					.setParameter("userProfileId", userProfileId)
					.setParameter("vin", vin).getResultList();

			if (Utility
					.isObjectNotNullorNotEmpty(ownerPortalUserVehicleFailedReference)
					&& !(ownerPortalUserVehicleFailedReference.isEmpty())) {

				LOG.info("VIN "
						+ vin
						+ "is available in failed reference table for the particular user");
				response = "success";

			} else {
				LOG.info("Vin "
						+ vin
						+ " is not available in failed reference table for the particular user");
				response = "failure";
			}

		} catch (NoResultException e) {
			LOG.error("validVehicle NoResultException for the vin" + vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("validVehicle ArrayIndexOutOfBoundsException for the vin"
					+ vin, e);
		} catch (Exception e) {

			LOG.error("validVehicle Exception for the vin" + vin, e);

		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To validate if user is available in helios db for the provided email
	 *      and personhashid
	 * @param email
	 * @param prsnHashId
	 * @param osEm
	 * @return
	 */
	public OwnerPortalUserVehicleFailedReference validateVin(String vin,
			EntityManager osEm) {

		OwnerPortalUserVehicleFailedReference response = null;

		try {
			LOG.info("Before Validate vin = " + vin);

			List<OwnerPortalUserVehicleFailedReference> ownerPortalUserVehicleFailedReference = osEm
					.createNamedQuery(
							"OwnerPortalUserVehicleFailedReference.findByvin",
							OwnerPortalUserVehicleFailedReference.class)
					.setParameter("vin", vin).getResultList();

			if (Utility
					.isObjectNotNullorNotEmpty(ownerPortalUserVehicleFailedReference)
					&& !(ownerPortalUserVehicleFailedReference.isEmpty())) {

				LOG.info("Vin = " + vin
						+ " is present in failedreference table");
				response = ownerPortalUserVehicleFailedReference.get(0);
			} else {
				LOG.info("Vin =" + vin
						+ " not available in failedreference table");
			}
		} catch (NoResultException e) {
			LOG.error("NoResultException in validateVin for the vin " + vin
					+ " and exception is", e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in validateVin for the vin "
							+ vin + " and exception is", e);

		} catch (Exception e) {
			LOG.error("General Exception in validateVin for the vin " + vin
					+ " and exception is", e);
		}
		return response;
	}

	public String loadDocument(UploadDocumentWrapper uploadDocumentWrapper,
			String brand, EntityManager osEm) throws ClassCastException {
		String response = "failure";
		long vehicleFailedReferenceKey;
		String userProfileId = null;
		LOG.info("in load document before loadDocument");
		List<OwnerPortalUser> userProfileIdList = null;

		try {
			String vin = uploadDocumentWrapper.getUploadDocument().getVehicle()
					.getVin();

			String contentType = uploadDocumentWrapper.getUploadDocument()
					.getVehicle().getContentType();
			LOG.info("in load document after try ::: contentType = "
					+ contentType);

			String email = uploadDocumentWrapper.getUploadDocument()
					.getPerson().getEmail();

			byte[] content = uploadDocumentWrapper.getUploadDocument()
					.getVehicle().getContent();

			String fileName = uploadDocumentWrapper.getUploadDocument()
					.getVehicle().getFileName();
			String personalHashId = uploadDocumentWrapper.getUploadDocument()
					.getPerson().getPersonHashId();

			LOG.info("in load document after try ::: personalHashId = "
					+ personalHashId);
			LOG.info("in load document after try ::: email = " + email);
			//x566325 - Brand Segregation
			//Setting OwnerPortalType in the query
			
			if(brand.equalsIgnoreCase("Nissan")){
				 
				String ownerPortalTypeCode = "N";
				userProfileIdList = osEm
					.createNamedQuery(
							"OwnerPortalUser.findByemailIdandpersonalHashIdandownerPortalTypeCode",
							OwnerPortalUser.class)

					.setParameter("emailId", email.toLowerCase())
					.setParameter("personalHashId", personalHashId)
					.setParameter("ownerPortalTypeCode", ownerPortalTypeCode)
					.getResultList();
			}else if(brand.equalsIgnoreCase("Infiniti")){
				String ownerPortalTypeCode = "I";
				userProfileIdList = osEm
						.createNamedQuery(
								"OwnerPortalUser.findByemailIdandpersonalHashIdandownerPortalTypeCode",
								OwnerPortalUser.class)

						.setParameter("emailId", email.toLowerCase())
						.setParameter("personalHashId", personalHashId)
						.setParameter("ownerPortalTypeCode", ownerPortalTypeCode)
						.getResultList();
			}
			
			/*List<OwnerPortalUser> userProfileIdList = osEm
					.createNamedQuery(
							"OwnerPortalUser.findByemailIdandpersonalHashId",
							OwnerPortalUser.class)
					.setParameter("emailId", email.toLowerCase())
					.setParameter("personalHashId", personalHashId)
					.getResultList();*/
			if(null !=userProfileIdList){
			userProfileId = userProfileIdList.get(0).getUserProfileId();
			}
			LOG.info("userProfileId" + userProfileId);
			List<OwnerPortalUserVehicleFailedReference> vehicleFailedRefKeyList = new ArrayList<OwnerPortalUserVehicleFailedReference>(
					osEm.createNamedQuery(
							"OwnerPortalUserVehicleFailedReference.findByuserProfileIdAndvin",
							OwnerPortalUserVehicleFailedReference.class)
							.setParameter("userProfileId", userProfileId)
							.setParameter("vin", vin).getResultList());

			Collections.sort(vehicleFailedRefKeyList,
					Collections.reverseOrder());

			vehicleFailedReferenceKey = vehicleFailedRefKeyList.get(0)
					.getVehicleFailedReferenceKey();
			LOG.info("vehicleFailedRefKey" + vehicleFailedReferenceKey);

			String uploadfileName = userProfileId + "_"
					+ vehicleFailedReferenceKey + "_" + fileName;

			String pdfFile = "/" + uploadfileName + ".pdf";
			String jpegFile = "/" + uploadfileName + ".jpeg";
			String pngFile = "/" + uploadfileName + ".png";

			byte[] bArray = uploadDocumentWrapper.getUploadDocument()
					.getVehicle().getContent();

			//LOG.info("in load document after try ::: bArray = " + bArray);

			if (("application/pdf").equalsIgnoreCase(contentType)) {

				Query query = osEm
						.createQuery("UPDATE OwnerPortalUserVehicleFailedReference AS o SET o.preferedDocumentPath=:preferedDocumentPath,o.updateTimestamp=:updateTimestamp WHERE o.vehicleFailedReferenceKey=:vehicleFailedReferenceKey");
				query.setParameter("preferedDocumentPath", pdfFile);
				query.setParameter("vehicleFailedReferenceKey",
						vehicleFailedReferenceKey);
				query.setParameter("updateTimestamp",
						new Timestamp(System.currentTimeMillis()));

				int m = query.executeUpdate();

				if (m > 0) {

					LOG.info("After Update  updateDocumentPathWithUploadfileName"
							+ m);
				}

			} else if (("image/jpeg").equalsIgnoreCase(contentType)) {

				Query query = osEm
						.createQuery("UPDATE OwnerPortalUserVehicleFailedReference AS o SET o.preferedDocumentPath=:preferedDocumentPath,o.updateTimestamp=:updateTimestamp WHERE o.vehicleFailedReferenceKey=:vehicleFailedReferenceKey");
				query.setParameter("preferedDocumentPath", jpegFile);
				query.setParameter("vehicleFailedReferenceKey",
						vehicleFailedReferenceKey);
				query.setParameter("updateTimestamp",
						new Timestamp(System.currentTimeMillis()));

				int l = query.executeUpdate();

				if (l > 0) {

					LOG.info("After Update  updateDocumentPathWithUploadfileName"
							+ l);
				}

			} else if (("image/png").equalsIgnoreCase(contentType)) {

				Query query = osEm
						.createQuery("UPDATE OwnerPortalUserVehicleFailedReference AS o SET o.preferedDocumentPath=:preferedDocumentPath,o.updateTimestamp=:updateTimestamp WHERE o.vehicleFailedReferenceKey=:vehicleFailedReferenceKey");
				query.setParameter("preferedDocumentPath", pngFile);
				query.setParameter("vehicleFailedReferenceKey",
						vehicleFailedReferenceKey);
				query.setParameter("updateTimestamp",
						new Timestamp(System.currentTimeMillis()));

				int k = query.executeUpdate();

				if (k > 0) {

					LOG.info("After Update  updateDocumentPathWithUploadfileName"
							+ k);
				}

			}

			Query query = osEm
					.createQuery("UPDATE OwnerPortalUserVehicleFailedReference AS o SET o.ownershipProof=:ownershipProof,o.updateTimestamp=:updateTimestamp WHERE o.vehicleFailedReferenceKey=:vehicleFailedReferenceKey");
			query.setParameter("ownershipProof", content);
			query.setParameter("vehicleFailedReferenceKey",
					vehicleFailedReferenceKey);
			query.setParameter("updateTimestamp",
					new Timestamp(System.currentTimeMillis()));
			int j = query.executeUpdate();

			if (j > 0) {

				LOG.info("After Update  ownershipProof" + j);
			}

			response = "success";

		} catch (Exception e) {
			LOG.error("Exception in load document method for the vin = "
					+ uploadDocumentWrapper.getUploadDocument().getVehicle()
							.getVin(), e);
		}
		return response;
	}

	private BigDecimal generateFailedRefKeyFromSequence(EntityManager osEm) {

		LOG.info("Inside generateFailedRefKey From Sequence method");
		BigDecimal failedRefKey;
		try {
			@SuppressWarnings("unchecked")
			List<BigDecimal> list = osEm.createNativeQuery(SELECT_NEXTVAL_SQL)
					.getResultList();
			LOG.info("List size:" + list.size());

			failedRefKey = (BigDecimal) list.get(0);
		} catch (Exception e) {
			LOG.error("Inside Generate FailedRefKey Exception", e);
			return null;
		}
		return failedRefKey;

	}
	
	/**
	 * @author x566325
	 * @use To retrieve the LargeImageURL from the getVehicle DB Function
	 * @param Model Code, Exterior Color Code
	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
	 * @return LargeImageURL String
	 */
	public String getVehicleFunctionLargeImage1(String modelCode, String exteriorColorCode, String factoryOptionCode, EntityManager osEm) {
		String imageTypeLarge = "Desktop_Standard";
		String imageLarge = ""; 
		LOG.info("getVehicleFunctionLargeImage starts");
		try {
			imageLarge = (String) osEm
					.createNativeQuery(
							"select MNS_B2C_GPAS.FN_GET_VHCL_EXTR_IMG_URL(?1, ?2, ?3, ?4) as img_url from dual")
							.setParameter(1, modelCode)
						    .setParameter(2, exteriorColorCode)
						    .setParameter(3, factoryOptionCode)
						    .setParameter(4, imageTypeLarge).getSingleResult();
			
			LOG.info("getVehicleFunctionLargeImage" + imageLarge);

		} catch (Exception e) {
			LOG.info("getVehicleFunctionLargeImage failed to fetch the URL", e);
		}
		return imageLarge;

	}
	
	/**
	 * @author x566325
	 * @use To retrieve the SmallImageURL from the getVehicle DB Function
	 * @param Model Code, Exterior Color Code
	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
	 * @return SmallImageURL String
	 */
	public String getVehicleFunctionSmallImage(OwnerPortalVehicle ownerPortalVehicle, EntityManager osEm) {
		String imageTypeSmall = "Mobile_Standard";
		String imageSmall = "";
		LOG.info("getVehicleFunctionSmallImage starts");
		try {
			imageSmall = (String) osEm
					.createNativeQuery(
							"select MNS_B2C_GPAS.FN_GET_VHCL_EXTR_IMG_URL(?1, ?2, ?3, ?4) as img_url from dual")
							.setParameter(1, ownerPortalVehicle.getVehicleModelCode())
						    .setParameter(2, ownerPortalVehicle.getVehicleExteriorColorCode())
						    .setParameter(3, ownerPortalVehicle.getVehicleOptionCode())
						    .setParameter(4, imageTypeSmall).getSingleResult();
			
			LOG.info("getVehicleFunctionSmallImage" + imageSmall);

		} catch (Exception e) {
			LOG.info("getVehicleFunctionSmallImage failed to fetch the URL", e);
		}
		return imageSmall;

	}
	
	//Commented by x116202
		public String getVehicleFunctionLargeImage(OwnerPortalVehicle ownerPortalVehicle, EntityManager osEm) {
			String imageTypeSmall = "Desktop_Standard";
			String imageSmall = "";
			LOG.info("getVehicleFunctionSmallImage starts");
			try {
				imageSmall = (String) osEm
						.createNativeQuery(
								"select MNS_B2C_GPAS.FN_GET_VHCL_EXTR_IMG_URL(?1, ?2, ?3, ?4) as img_url from dual")
								.setParameter(1, ownerPortalVehicle.getVehicleModelCode())
							    .setParameter(2, ownerPortalVehicle.getVehicleExteriorColorCode())
							    .setParameter(3, ownerPortalVehicle.getVehicleOptionCode())
							    .setParameter(4, imageTypeSmall).getSingleResult();
				
				LOG.info("getVehicleFunctionSmallImage" + imageSmall);

			} catch (Exception e) {
				LOG.info("getVehicleFunctionSmallImage failed to fetch the URL", e);
			}
			return imageSmall;

		}
		
		
	public String getVehicleFunctionSmallImage1(String modelCode,String exteriorColorCode,String factoryOptionCode, EntityManager osEm) {
		String imageTypeSmall = "Mobile_Standard";
		String imageSmall = "";
		LOG.info("getVehicleFunctionSmallImage starts");
		try {
			imageSmall = (String) osEm
					.createNativeQuery(
							"select MNS_B2C_GPAS.FN_GET_VHCL_EXTR_IMG_URL(?1, ?2, ?3, ?4) as img_url from dual")
							.setParameter(1, modelCode)
						    .setParameter(2, exteriorColorCode)
						    .setParameter(3, factoryOptionCode)
						    .setParameter(4, imageTypeSmall).getSingleResult();
			
			LOG.info("getVehicleFunctionSmallImage" + imageSmall);

		} catch (Exception e) {
			LOG.info("getVehicleFunctionSmallImage failed to fetch the URL", e);
		}
		return imageSmall;

	}

	public List<TermsAndConditionsAgreementSt> getTermsAndConditionsAgreementSt(
			String vin, String userId, EntityManager osEm) {
		List<TermsAndConditionsAgreementSt> response = null;

		try {

			List<TermsAndConditionsAgreementSt> termsAndConditionsAgreementSts = osEm
					.createNamedQuery(
							"TermsAndConditionsAgreementSt.getUsingByVin",
							TermsAndConditionsAgreementSt.class)
					.setParameter("vin", vin).setParameter("usrPrflId", userId)
					.getResultList();
			if (Utility
					.isObjectNotNullorNotEmpty(termsAndConditionsAgreementSts)
					&& !(termsAndConditionsAgreementSts.isEmpty())) {
				response = termsAndConditionsAgreementSts;
			}
		} catch (NoResultException e) {
			LOG.error(
					"getTermsAndConditionsAgreementSt NoResultException for the vin"
							+ vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getTermsAndConditionsAgreementSt ArrayIndexOutOfBoundsException for the vin"
							+ vin, e);

		} catch (Exception e) {
			LOG.error("getTermsAndConditionsAgreementSt Exceptionfor the vin"
					+ vin, e);

		}
		return response;
	}
	
	//Added for delete vehicle by aniket
	public List<TelematicsSvcLg> getTelematicsSvcLg(String vin,EntityManager osEm){
		
		List<TelematicsSvcLg> telematicsSvcLg = null;
		
		try {
			
			telematicsSvcLg = osEm.createNamedQuery("TelematicsSvcLg.findvin",TelematicsSvcLg.class)
					.setParameter("vin", vin).getResultList();
			
		} catch (NoResultException e) {
			
			LOG.error("gettelematicsSvcLg No Result Exception for Vin "+ vin);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("gettelematicsSvcLg Array Index out of bound exception for vin"+vin);
		}
		catch (Exception e) {
			LOG.error("gettelematicsSvcLg Exception for vin"+vin);
		}
		return telematicsSvcLg;
		
	}

	public TelematicstermsAndConditions getTelematicstermsAndConditions(
			String vehicleTelematicsCode, EntityManager osEm) {
		TelematicstermsAndConditions response = null;
		try {

			List<TelematicstermsAndConditions> telematicstermsAndConditions = osEm
					.createNamedQuery(
							"TelematicstermsAndConditions.findByvhclTlmtcsCd",
							TelematicstermsAndConditions.class)
					.setParameter("vhclTlmtcsCd", vehicleTelematicsCode)
					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(telematicstermsAndConditions)
					&& !(telematicstermsAndConditions.isEmpty())) {
				response = telematicstermsAndConditions.get(0);
			}
		} catch (NoResultException e) {
			LOG.error("getTelematicstermsAndConditions NoResultException for the vehicleTelematicsCode"
					+ vehicleTelematicsCode);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("getTelematicstermsAndConditions ArrayIndexOutOfBoundsException for the vehicleTelematicsCode"
					+ vehicleTelematicsCode);

		} catch (Exception e) {
			LOG.error("getTelematicstermsAndConditions Exceptionfor the vehicleTelematicsCode"
					+ vehicleTelematicsCode);

		}
		return response;

	}

	public List<OwnerPortalUserVehicle> getOwnerPortalUserVehicles(String vin,
			EntityManager osEm) {
		List<OwnerPortalUserVehicle> response = null;

		try {
			List<OwnerPortalUserVehicle> ownerPortalUserVehicle = osEm
					.createNamedQuery("OwnerPortalUserVehicle.findByVin",
							OwnerPortalUserVehicle.class)
					.setParameter("vin", vin).getResultList();
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserVehicle)
					&& !(ownerPortalUserVehicle.isEmpty())) {

				response = ownerPortalUserVehicle;
			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getOwnerPortalUserVehicles for the vin"
							+ vin, e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getOwnerPortalUserVehicles for the vin"
							+ vin, e);
		} catch (Exception e) {
			LOG.error("Exception in getOwnerPortalUserVehicles for the vin"
					+ vin, e);
		}
		return response;
	}

	public OwnerPortalUser getOwnerPortalUser(String userProfileId,
			EntityManager osEm) {
		OwnerPortalUser response = null;
		List<OwnerPortalUser> ownerPortalUser = null;
		try {
			ownerPortalUser = osEm
					.createNamedQuery("OwnerPortalUser.findByuserProfileId",
							OwnerPortalUser.class)
					.setParameter("userProfileId", userProfileId)

					.getResultList();
			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUser)
					&& !(ownerPortalUser.isEmpty())) {

				response = ownerPortalUser.get(0);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getOwnerPortalUser for the userProfileId ="
							+ userProfileId, e);
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getOwnerPortalUser for the userProfileId ="
							+ userProfileId, e);
		} catch (Exception e) {
			LOG.error("Exception in getOwnerPortalUser for the userProfileId ="
					+ userProfileId, e);
		}
		return response;
	}

	public void insertTermsAndConditionsAgreementSt(
			TermsAndConditionsAgreementSt agreementSt, EntityManager osEM) {
		try {
			osEM.persist(agreementSt);
		} catch (Exception e) {
			LOG.error("Exception in updateTermsAndConditionsAgreementSt " + e);
		}
	}
	
	public void updateTermsAndConditionsAgreementSt(
			TermsAndConditionsAgreementSt agreementSt, EntityManager osEM) {
		try {
			osEM.merge(agreementSt);
		} catch (Exception e) {
			LOG.error("Exception in updateTermsAndConditionsAgreementSt " + e);
		}
	}
	
	public List<TermsAndConditionsAgreementSt> getTermsAndConditionsAgreementStUsingSorcecode(
			String vin, String userId,String agreement,EntityManager osEm) {
		
		
		List<TermsAndConditionsAgreementSt> response = null;

		try {

			List<TermsAndConditionsAgreementSt> termsAndConditionsAgreementSts = osEm
					.createNamedQuery(
							"TermsAndConditionsAgreementSt.getUsingByVinandusrPrflIdandtermCndtnSrcCd",
							TermsAndConditionsAgreementSt.class)
					.setParameter("vin", vin).setParameter("usrPrflId", userId).setParameter("termCndtnSrcCd", agreement)
					.getResultList();
			if (Utility
					.isObjectNotNullorNotEmpty(termsAndConditionsAgreementSts)
					&& !(termsAndConditionsAgreementSts.isEmpty())) {
				response = termsAndConditionsAgreementSts;
			}
		} catch (NoResultException e) {
			LOG.error(
					"getTermsAndConditionsAgreementSt NoResultException for the vin"
							+ vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getTermsAndConditionsAgreementSt ArrayIndexOutOfBoundsException for the vin"
							+ vin, e);

		} catch (Exception e) {
			LOG.error("getTermsAndConditionsAgreementSt Exceptionfor the vin"
					+ vin, e);

		}
		return response;
	}
	
	
	/**
	 * @author x055765
	 * @use To fetch the vehicle information for test vin
	 * @param vin
	 * @return
	 */
	public ManualVehicleLookup getVehicleInfoForTestVin(String vin, EntityManager osEm) {
		ManualVehicleLookup response = null;
		List<ManualVehicleLookup> manualVehicleLookup = null;

		try {
			LOG.info("VIN inside Service : "+ vin);
			manualVehicleLookup = osEm
					.createNamedQuery(
							"ManualVehicleLookup.findByVin",
							ManualVehicleLookup.class)
					.setParameter("vin", vin).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(manualVehicleLookup)
					&& !(manualVehicleLookup.isEmpty())) {
			  response = manualVehicleLookup.get(0);
			  LOG.info("Vin is available in the Mnl_vhcl_load table :" + response.getVin() + "" + 
		              "ModelCode :" + response.getModelCode() + "" + "ExteriorColorCode :" + response.getExteriorColorCode()
		              + "" + "OptionTypeCode: " + response.getOptionCode());
			} else {
				LOG.info("Vin is not there in the mnl_vhcl_load table");

			}

		} catch (NoResultException e) {
			LOG.error(
					"Inside getVehicleInfoForTestVin NoResultException for the vin= "
							+ vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"Inside getVehicleInfoForTestVin ArrayIndexOutOfBoundsException for the vin= "
							+ vin, e);

		} catch (Exception e) {
			LOG.error("Inside getVehicleInfoForTestVin Exceptionfor the vin= "
					+ vin, e);

		}

		return response;
	}
	
	
	/**
	 * @author x055765
	 * @use To fetch the model code for vin
	 * @param vin
	 * @return
	 */
	public OwnerPortalVehicle getModelNameUsingVin(String vin, EntityManager osEm) {
		OwnerPortalVehicle response = null;
		List<OwnerPortalVehicle> ownerPortalVehicle = null;

		try {
			LOG.info("VIN inside Service : "+ vin);
			ownerPortalVehicle = osEm
					.createNamedQuery(
							"OwnerPortalVehicle.findByVin",
							OwnerPortalVehicle.class)
					.setParameter("vin", vin).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalVehicle)
					&& !(ownerPortalVehicle.isEmpty())) {
			  response = ownerPortalVehicle.get(0);
			  LOG.info("ModelName for the vin is available in the OwnerPortalVehicle table :" + response.getVehicleModelName());
			} else {
				LOG.info("ModelName for the vin is not available in the OwnerPortalVehicle table");

			}

		} catch (NoResultException e) {
			LOG.error(
					"Inside getModelNameUsingVin NoResultException for the vin= "
							+ vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"Inside getModelNameUsingVin ArrayIndexOutOfBoundsException for the vin= "
							+ vin, e);

		} catch (Exception e) {
			LOG.error("Inside getModelNameUsingVin Exceptionfor the vin= "
					+ vin, e);

		}

		return response;
	}
	
	/**
	 * @author X522443
	 * @use To retrieve the ImageURL from the getVehicle DB Function
	 * @param Model Code, Exterior Color Code
	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
	 * @return ImageURL String
	 */
	public String getVehicleFunctionImage(String modelCode, String exteriorColorCode, String factoryOptionCode, EntityManager osEm, String typeSize) {
		String imageTypeLarge = typeSize;
		String imageLarge = ""; 
		LOG.info("getVehicleFunctionImage starts");
		try {
			imageLarge = (String) osEm
					.createNativeQuery(
							"select MNS_B2C_GPAS.FN_GET_VHCL_EXTR_IMG_URL(?1, ?2, ?3, ?4) as img_url from dual")
							.setParameter(1, modelCode)
						    .setParameter(2, exteriorColorCode)
						    .setParameter(3, factoryOptionCode)
						    .setParameter(4, imageTypeLarge).getSingleResult();
			
			LOG.info("getVehicleFunctionImage" + imageLarge);

		} catch (Exception e) {
			LOG.info("getVehicleFunctionImage failed to fetch the URL", e);
		}
		return imageLarge;

	}
	
	public VehdsplMaritzDlyLndgPrcngEntity checkVin(String vin, EntityManager osEm) {

		VehdsplMaritzDlyLndgPrcngEntity result = null;
		try {

			List<VehdsplMaritzDlyLndgPrcngEntity> vehdsplMaritzDlyLndgPrcngEntityList = osEm
					.createNamedQuery("VehdsplMaritzDlyLndgPrcngEntity.findByVin",
							VehdsplMaritzDlyLndgPrcngEntity.class).setParameter("vin", vin)
					.getResultList();

			LOG.info("Vehicle available in vehdsplMaritzDlyLndgPrcngEntityList Size = " +vehdsplMaritzDlyLndgPrcngEntityList.size());
			
			if (Utility.isObjectNotNullorNotEmpty(vehdsplMaritzDlyLndgPrcngEntityList)
					&& !(vehdsplMaritzDlyLndgPrcngEntityList.isEmpty()) && vehdsplMaritzDlyLndgPrcngEntityList.size()>0) {

				result = vehdsplMaritzDlyLndgPrcngEntityList.get(0);

				
			} else {
				LOG.info("Vehicle not available in VehdsplMaritzDlyLndgPrcngEntity for the vin = "
						+ vin);

			}
		} catch (NoResultException e) {
			LOG.error("NoResultException for the vin =" + vin, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("ArrayIndexOutOfBoundsException for the vin ="
					+ vin, e);

		} catch (Exception e) {
			//LOG.error("chechVin General exception for the vin =" + vin, e);
			LOG.error("could not extract ResultSet in chechVin General exception for the vin =" + vin, e);

		}
		LOG.info("Vehicle available in VehdsplMaritzDlyLndgPrcngEntity the result = " +result);
		return result;
	}

	public void updateVehdsplMaritzDlyLndgPrcngEntity(
			VehdsplMaritzDlyLndgPrcngEntity vehdsplMaritzDlyLndgPrcngEntity, EntityManager osEm) {
		// TODO Auto-generated method stub
		try {
			LOG.info(
					"Action = VehicleService :: Method : updateVehdsplMaritzDlyLndgPrcngEntity :: updating 3rd party responses in DB");
			osEm.merge(vehdsplMaritzDlyLndgPrcngEntity);
		} catch (PersistenceException e) {
			LOG.error("PersistenceException in save VehdsplMaritzDlyLndgPrcngEntity info ", e);
		} catch (Exception e) {
			LOG.error("Exception in save VehdsplMaritzDlyLndgPrcngEntity info", e);
		}
	}
	
	public List<OwnerPortalUserVehicleFailedReference> getOVCaseVehiclesByStatus(String userProfileId, EntityManager osEm) {
		List<OwnerPortalUserVehicleFailedReference> response = null;
		try {
			response = osEm
					.createNamedQuery("OwnerPortalUserVehicleFailedReference.findByuserProfileIdAndCaseStatus",
							OwnerPortalUserVehicleFailedReference.class)
					.setParameter("userProfileId", userProfileId)
					.getResultList();
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getOwnerPortalUserVehicles for the vin"
							+ userProfileId, e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getOwnerPortalUserVehicles for the vin"
							+ userProfileId, e);
		} catch (Exception e) {
			LOG.error("Exception in getOwnerPortalUserVehicles for the vin"
					+ userProfileId, e);
		}
		return response;
	}
	
	public Map<String,String> getCurrentOVStatusFromSFDC (String caseId) {
		String status = "";
		String casenumber = "";
	Map<String,String> map = new HashMap<>();
		try {
			String token = getSFDCToken();
			if (token.isEmpty()) {
				LOG.info("SFDC token endpoint failure");
				map.put("apiresponse", "error");
				return map;
			}
			JSONObject keyJson = getSFDCKey(token);
			if(null == keyJson  || !keyJson.has(SFDC_KEY_ID)) {
				LOG.info("SFDC key endpoint failure");
				map.put("apiresponse", "error");
				return map;
			}
			String keyId = keyJson.getString(SFDC_KEY_ID);
			String encKey = keyJson.getString(SFDC_ENC_KEY);
			String encIv = keyJson.getString(SFDC_ENC_IV);
			
			if(keyId.isEmpty() || encKey.isEmpty() || encIv.isEmpty()) {
				LOG.info("SFDC key endpoint failure");
				map.put("apiresponse", "error");
				return map;
			}
			String encryptedResp = validateSFDCCase(caseId, keyId, token);
			 casenumber = getSFDCCasenumber(caseId, keyId, token);
		    LOG.info(encryptedResp);
			if (encryptedResp.isEmpty() && casenumber.isEmpty()) {
				LOG.info("SFDC validate response message error");
				map.put("apiresponse", "error");
				return map;
			}
			else{
				if(!encryptedResp.isEmpty()){
					JSONObject respObj = decryptSFDCResponse(encryptedResp, encKey, encIv);
					if (respObj != null && respObj.has(SFDC_RESP_STS_KEY)) {
						LOG.info("Final decrypted response = " +respObj);
						status=(String) respObj.get(SFDC_RESP_STS_KEY);
						LOG.info("SFDC status = "+status);
						//return (String) respObj.get(SFDC_RESP_STS_KEY);
						map.put("CaseStatus", status);
					}
				}
			map.put("CaseNumber", casenumber);
			map.put("apiresponse", "success");		
			
			}
			
			LOG.info("SFDC CaseStatus = "+status);
			LOG.info("SFDC CaseNumber = "+casenumber);
			
		} catch (Exception e) {
			LOG.error("Get SFDC case status failure exception ", e);
		}
		return map;
	}
	
	private String getSFDCToken() {
		String sfdcToken = "";
		try {
		    String request = GRANT_KEY+"="+GRANT_VALUE+"&"+CLIENT_ID+"="+SFDC_TOKEN_CLIENT_ID
		    		+"&"+CLIENT_SEC+"="+SFDC_TOKEN_CLIENT_SECRET+"&"+USERNAME_KEY+"="+SFDC_TOKEN_USERNAME
		    		+"&"+PASSWORD_KEY+"="+SFDC_TOKEN_PASSWORD;
		    URL url = new URL(SFDC_TOKEN_URL);
		    HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    conn.getOutputStream().write(request.getBytes());
		    conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode != 200) {
				LOG.info("Response for SFDC token API, code = " +responsecode);
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				LOG.info("Response for SFDC token API, code = " +responsecode);
				BufferedReader br = new BufferedReader(new InputStreamReader(
				        (conn.getInputStream())));
				String responseOutput = br.readLine();
				if (responseOutput != null) {
					JSONObject jsonObject = new JSONObject(responseOutput);
					sfdcToken = (String) jsonObject.get(ACCESS_TOKEN);
				}
			}
		} catch (Exception e) {
			LOG.error("Get SFDC token API failure ", e);
		}
		return sfdcToken;
	}
	
	private JSONObject getSFDCKey(String token) {
		JSONObject jsonObject = null;
		try {
		    URL url = new URL(SFDC_GET_KEY_URL);
		    HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		    conn.setDoOutput(true);
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty(AUTH_HEADER_KEY, AUTH_HEADER_VALUE+token);
		    conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode != 202) {
				LOG.info("Response for SFDC Key API, code = " +responsecode);
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				LOG.info("Response for SFDC Key API, code = " +responsecode);
				BufferedReader br = new BufferedReader(new InputStreamReader(
				        (conn.getInputStream())));
				String responseOutput = br.readLine();
				if (responseOutput != null) {
					jsonObject = new JSONObject(responseOutput);
					String key = conn.getHeaderField(SFDC_KEY_ID);
					jsonObject.accumulate(SFDC_KEY_ID, key);
					LOG.info(jsonObject);
				}
			}
		} catch (Exception e) {
			LOG.error("Get SFDC token API failure ", e);
		}
		return jsonObject;
	}
	
	private String validateSFDCCase(String caseId, String keyId, String token) {
		String encryptedResp = "";
		try {
			URL url = new URL(SFDC_VALIDATE_CASE_URL+caseId);
		    HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		    conn.setDoOutput(true);
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty(AUTH_HEADER_KEY, AUTH_HEADER_VALUE+token);
		    conn.setRequestProperty(SFDC_KEY_ID, keyId);
		    conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 202) {
				LOG.info("Response for SFDC Validate Case API, code = " +responsecode);
				BufferedReader br = new BufferedReader(new InputStreamReader(
				        (conn.getInputStream())));
				String responseOutput = br.readLine();
				if (responseOutput != null) {
					LOG.info("Response output = " +responseOutput);
					JSONObject jsonObject = new JSONObject(responseOutput);
					encryptedResp = (String) jsonObject.get(SFDC_CASE_STS_RESP);
				}
			} else if (responsecode == 400) {
				LOG.info("Response for SFDC Validate Case API, code = " +responsecode);
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				LOG.info("Response for SFDC Validate Case API, code = " +responsecode);
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			}
		} catch (Exception e) {
			LOG.error("Get SFDC Validate Case API failure ", e);
		}
		return encryptedResp;
	}
	
	private String getSFDCCasenumber(String caseId, String keyId, String token) {
		String casenumber = "";
		try {
			URL url = new URL(SFDC_VALIDATE_CASENUMBER_URL+caseId+SFDC_VALIDATE_CASENUMBER_SUFFIX);
			LOG.info("caseId = "+caseId);
		    HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		    conn.setDoOutput(true);
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty(AUTH_HEADER_KEY, AUTH_HEADER_VALUE+token);
		    conn.setRequestProperty(SFDC_KEY_ID, keyId);
		    conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				LOG.info("Response for SFDC Validate Case API, code = " +responsecode);
				BufferedReader br = new BufferedReader(new InputStreamReader(
				        (conn.getInputStream())));
				String responseOutput = br.readLine();
				
				JSONArray jsonarray = new JSONArray(responseOutput);
				LOG.info("Response output = " +jsonarray);
				if (jsonarray != null) {
					LOG.info("Response output = " +jsonarray);
					for(int i=0;i<jsonarray.length();i++){
					JSONObject jsonObject = jsonarray.getJSONObject(i);
					LOG.info("Response output for SFDC getcasenumber API= "+jsonObject.get("CaseNumber"));
					casenumber = jsonObject.get("CaseNumber").toString();
					}
					
				}
			} else if (responsecode == 400) {
				LOG.info("Response for SFDC Validate Case API, code = " +responsecode);
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				LOG.info("Response for SFDC Validate Case API, code = " +responsecode);
			    throw new RuntimeException("HttpResponseCode: " + responsecode);
			}
		} catch (Exception e) {
			LOG.error("Get SFDC Validate Case API failure ", e);
		}
		LOG.info("Return casenumber::"+ casenumber);
		return casenumber;
	}
	
	private JSONObject decryptSFDCResponse (String encryptedResp, String encKey, String encIv) {
		JSONObject jsonObject = null;
		try {
			LOG.info("Decrypt SFDC Case status API response"); 
			byte[] aesKey = Base64.getDecoder().decode(encKey);
			byte[] aesIV = Base64.getDecoder().decode(encIv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(aesIV));
			byte[] base64decodedTokenArr = Base64.getDecoder().decode(encryptedResp.getBytes());
			byte[] decrypted = cipher.doFinal(base64decodedTokenArr);
	        String decrypt = new String(decrypted);
	        jsonObject = new JSONObject(decrypt);
		} catch (Exception e) {
			LOG.error("Decrypt SFDC Case status response failure ", e);
		}
		return jsonObject;
	}
	
	public List<String> getOwnerVehicles(
			String userProfileId, EntityManager osEm) {
		List<String> response = null;
		try {
			LOG.info("Inside get VINs from the user profile Id = " +userProfileId);
			response = osEm
					.createNativeQuery("select vin from ownr_portl_usr_vhcl where usr_prfl_id = :userProfileId and (sysdate - CAST(crte_ts as date)) > 3")
					.setParameter("userProfileId", userProfileId)
					.getResultList();
			LOG.info("After getOwnerVehicleDetails" + response);
		} catch (Exception e) {
			LOG.error("Inside validateVehicleOwner Exception for the user ="
					+ userProfileId, e);
		}
		return response;
	}
}
