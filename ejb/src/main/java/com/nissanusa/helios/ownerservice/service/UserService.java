package com.nissanusa.helios.ownerservice.service;

/*
 *last modified date 27-05-2016 by x796314

 */

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.nissanusa.helios.ownerservice.entity.Decryption;
import com.nissanusa.helios.ownerservice.entity.Encryption;
import com.nissanusa.helios.ownerservice.entity.FAQDetails;
import com.nissanusa.helios.ownerservice.entity.FAQHeaderLookup;
import com.nissanusa.helios.ownerservice.entity.MobileProvider;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalLinkReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalPhoneDisclaimer;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhone;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhonePK;
import com.nissanusa.helios.ownerservice.entity.State;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.PropertiesLoader;
import com.nissanusa.helios.ownerservice.util.Utility;
import com.nissanusa.helios.ownerservice.vo.AccountVO;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailRevokeWrapper;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveUserWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateCdiidWrapper;

/**
 * 
 * @author x178099
 * @use Service Implementation of user services
 * 
 */
@Stateless
public class UserService implements OwnerConstants {

	private static final Logger LOG = Logger.getLogger(UserService.class);

	public UserService() {
		try {
			PropertiesLoader.getLog4j();

		} catch (Exception e) {
			LOG.info("In the PropertiesLoaderException Exception UserService"
					+ e.getMessage());

		}
	}

	/**
	 * @author x178099
	 * @use To validate if cdiid is available in Helios DB for the provided
	 *      email. If not mapped to same user,check for duplicate user. If not
	 *      mapped to any other user,save cdiid for the requested email and
	 *      personhashid.
	 * @param validatecdiidwrapper
	 * @return
	 */

	public String validateCdiid(ValidateCdiidWrapper validatecdiidwrapper,
			EntityManager osEm) {
		String response = "failure";

		try {

			LOG.info("CDIID to be validated = "
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getCdiid());

			List<OwnerPortalUser> ownerPortalUser = osEm
					.createNamedQuery(
							"OwnerPortalUser.findBycustomerDataIntegrationIdEmailIdandpersonalHashId",
							OwnerPortalUser.class)
					.setParameter(
							"customerDataIntegrationId",
							validatecdiidwrapper.getValidateCustomerSystemId()
									.getCdiid())
					.setParameter(
							"personalHashId",
							validatecdiidwrapper.getValidateCustomerSystemId()
									.getPersonHashId())
					.setParameter(
							"emailId",
							validatecdiidwrapper.getValidateCustomerSystemId()
									.getEmail()).getResultList();
			LOG.info("Count of users with CDIID "
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getCdiid() + " is " + ownerPortalUser.size());

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUser)
					&& !(ownerPortalUser.isEmpty())) {
				response = "success";
				LOG.info("CDIID "
						+ validatecdiidwrapper.getValidateCustomerSystemId()
								.getCdiid() + " exist and mapped to same user");
			} else {
				LOG.info("CDIID "
						+ validatecdiidwrapper.getValidateCustomerSystemId()
								.getCdiid()
						+ " not mapped to same user checking for duplicate");

				List<OwnerPortalUser> ownerPortalUserList = osEm
						.createNamedQuery(
								"OwnerPortalUser.findBycustomerDataIntegrationId",
								OwnerPortalUser.class)
						.setParameter(
								"customerDataIntegrationId",
								validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getCdiid()).getResultList();
				if (Utility.isObjectNotNullorNotEmpty(ownerPortalUserList)
						&& !(ownerPortalUserList.isEmpty())) {
					response = "failure";
					LOG.info("CDIID "
							+ validatecdiidwrapper
									.getValidateCustomerSystemId().getCdiid()
							+ " mapped to other user");
				} else {

					boolean isCdiidSaved = isCdiidSaved(validatecdiidwrapper,
							osEm);
					if (isCdiidSaved) {
						response = "success";
						LOG.info("CDIID "
								+ validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getCdiid() + " saved to same user");
					} else {
						response = "available";
						LOG.info("CDIID "
								+ validatecdiidwrapper
										.getValidateCustomerSystemId()
										.getCdiid() + "already available");
					}
				}

			}

		} catch (NoResultException e) {
			LOG.error("NoResultException in validateCdiid for the cdiid "
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getCdiid(), e);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in validateCdiid for the cdiid "
							+ validatecdiidwrapper
									.getValidateCustomerSystemId().getCdiid(),
					e);

		} catch (Exception e) {
			LOG.error("ValidateCdiid General Exception for the cdiid "
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getCdiid(), e);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To retrieve state code from ST table
	 * @param state
	 * @param osEm
	 * @return
	 */
	public State getStateByStateKey(String state, EntityManager osEm) {

		State response = null;

		try {

			List<State> stateList = osEm
					.createNamedQuery("State.findByStateKey", State.class)
					.setParameter("stateKey", state).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(stateList)
					&& !(stateList.isEmpty())) {

				response = stateList.get(0);

			} else {
				LOG.info("state " + state + " is not available");

			}
		} catch (NoResultException e) {
			LOG.error("NoResultException in getState for the state" + state, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getState for the state"
							+ state, e);

		} catch (Exception e) {
			LOG.error("General Exception in getState" + state, e);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @param state
	 * @param osEm
	 * @return
	 */
	public State getStateCode(String state, EntityManager osEm) {

		State response = null;

		try {

			List<State> stateList = osEm
					.createNamedQuery("State.findByStateCode", State.class)
					.setParameter("stateCode", state).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(stateList)
					&& !(stateList.isEmpty())) {

				response = stateList.get(0);

			} else {
				LOG.info("state " + state + " is not available");

			}
		} catch (NoResultException e) {
			LOG.error("NoResultException in getState for the state" + state, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getState for the state"
							+ state, e);

		} catch (Exception e) {
			LOG.error("General Exception in getState" + state, e);
		}

		return response;
	}
	
	//x430955- vehical disposal validate data changes
	
	public OwnerPortalUser validateUserforVehicalDisposal(String personHashId, String brand,
			EntityManager osEm) {
		OwnerPortalUser portalUser = null;
       LOG.info("validateUserforVehicalDisposal personHashId " + personHashId + "brand" + brand);
		List<OwnerPortalUser> ownerPortalUser = null;
		try {
			// x055765 - brand segregation - included OwnerPortalTypeCode
            if(brand.equalsIgnoreCase("N")){
            	LOG.info( "inside N brand" + brand);

			ownerPortalUser = osEm
					.createNamedQuery("OwnerPortalUser.findBypersonalHashIdandChannelCode",
							OwnerPortalUser.class)
					.setParameter("personalHashId", personHashId)
					.setParameter("ownerPortalTypeCode", brand).getResultList();
            }else if(brand.equalsIgnoreCase("I")){
            	ownerPortalUser = osEm
    					.createNamedQuery("OwnerPortalUser.findBypersonalHashIdandChannelCode",
    							OwnerPortalUser.class)
    					.setParameter("personalHashId", personHashId)
    					.setParameter("ownerPortalTypeCode", brand).getResultList();
            }

            if (Utility.isObjectNotNullorNotEmpty(ownerPortalUser)
					&& !(ownerPortalUser.isEmpty())) {

				LOG.info("Valid User");
				portalUser = ownerPortalUser.get(0);
				LOG.info("Valid User"+ portalUser.getEmailId()) ;
			} else {
				LOG.info("User not available");
			}

		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in validateUserVD for the personalHashId" + personHashId,
					e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in validateUserVD for the personHashId"
							+ personHashId, e);

		} catch (Exception e) {
			LOG.error(
					"General Exception in validateUserVD for the personHashId" + personHashId,
					e);
		}
		return portalUser;
	}

	/**
	 * @author x178099
	 * @use To validate user in OwnerPortalUser table
	 * @param email
	 * @param brand
	 * @param osEm
	 * @return
	 */
	public OwnerPortalUser validateUser(String email, String brand,
			EntityManager osEm) {
		OwnerPortalUser portalUser = null;
LOG.info("validateUser emailId " + email + "brand" + brand);
		List<OwnerPortalUser> ownerPortalUser = null;
		String emailId = email.toLowerCase();
		try {
			// x055765 - brand segregation - included OwnerPortalTypeCode
            if(brand.equalsIgnoreCase("N")){
            	LOG.info( "inside N brand" + brand);

			ownerPortalUser = osEm
					.createNamedQuery("OwnerPortalUser.findByemailIdandownerPortalTypeCode",
							OwnerPortalUser.class)
					.setParameter("emailId", emailId)
					.setParameter("ownerPortalTypeCode", brand).getResultList();
            }else if(brand.equalsIgnoreCase("I")){
            	ownerPortalUser = osEm
    					.createNamedQuery("OwnerPortalUser.findByemailIdandownerPortalTypeCode",
    							OwnerPortalUser.class)
    					.setParameter("emailId", emailId)
    					.setParameter("ownerPortalTypeCode", brand).getResultList();
            }

            if (Utility.isObjectNotNullorNotEmpty(ownerPortalUser)
					&& !(ownerPortalUser.isEmpty())) {

				LOG.info("Valid User");
				portalUser = ownerPortalUser.get(0);

			} else {
				LOG.info("User not available");
			}

		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in validateUser for the email" + email,
					e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in validateUser for the email"
							+ email, e);

		} catch (Exception e) {
			LOG.error(
					"General Exception in validateUser for the email" + email,
					e);
		}
		return portalUser;
	}

	/**
	 * @author x178099
	 * @use To get phone disclaimer options opted by user for auto dial and
	 *      allow text in work phone and cell phone.
	 * @param usfPrflId
	 * @param osEm
	 * @return
	 */
	public OwnerPortalPhoneDisclaimer getPhoneDisclaimer(String userProfileId,
			EntityManager osEm) {

		OwnerPortalPhoneDisclaimer response = null;
		try {

			List<OwnerPortalPhoneDisclaimer> ownerPortalPhoneDisclaimer = osEm
					.createNamedQuery(
							"OwnerPortalPhoneDisclaimer.findByUserProfileId",
							OwnerPortalPhoneDisclaimer.class)
					.setParameter("userProfileId", userProfileId)
					.getResultList();

			if (!ownerPortalPhoneDisclaimer.isEmpty()) {

				LOG.info("Phone Disclaimer values exist for the user "
						+ userProfileId);
				response = ownerPortalPhoneDisclaimer.get(0);

			} else {
				LOG.info("Inside disclaimer values null for the user"
						+ userProfileId);

			}
		} catch (NoResultException e) {
			LOG.error(
					"getPhoneDisclaimer No Result Exception for the userprofileid"
							+ userProfileId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getPhoneDisclaimer ArrayIndexOutOfBoundsException for the userprofileid"
							+ userProfileId, e);

		} catch (Exception e) {
			LOG.error(
					"getPhoneDisclaimer General Exception for the userprofileid"
							+ userProfileId, e);
		}
		return response;
	}

	/**
	 * @author x055765 - brand segregation
	 * @use To validate if user is available in helios db for the provided email
	 *      and personhashid
	 * @param email
	 * @param prsnHashId
	 * @param ownerPortalTypeCode
	 * @param osEm
	 * @return
	 */
	public OwnerPortalUser validateEmail(String email, String personHashId,
			String brand, EntityManager osEm) {

		OwnerPortalUser portalUser = null;

		List<OwnerPortalUser> ownerPortalUser = null;


		try {
			LOG.info("Before Validate email = " + email
					+ "with personhashid = " + personHashId);
			// x055765 - brand segregation - included OwnerPortalTypeCode
			if (brand.equalsIgnoreCase("Nissan")) {

				String ownerPortalTypeCode = "N";
				ownerPortalUser = osEm
					.createNamedQuery(
							"OwnerPortalUser.findByemailIdandpersonalHashIdandownerPortalTypeCode",
							OwnerPortalUser.class)

					.setParameter("emailId", email.toLowerCase())
					.setParameter("personalHashId", personHashId)
						.setParameter("ownerPortalTypeCode",
								ownerPortalTypeCode).getResultList();
			} else if (brand.equalsIgnoreCase("Infiniti")) {
				String ownerPortalTypeCode = "I";
				ownerPortalUser = osEm
						.createNamedQuery(
								"OwnerPortalUser.findByemailIdandpersonalHashIdandownerPortalTypeCode",
								OwnerPortalUser.class)

						.setParameter("emailId", email.toLowerCase())
						.setParameter("personalHashId", personHashId)
						.setParameter("ownerPortalTypeCode",
								ownerPortalTypeCode).getResultList();
			}

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalUser)
					&& !(ownerPortalUser.isEmpty())) {

				LOG.info("Email = " + email
						+ " is valid for the person hash id =" + personHashId);
				portalUser = ownerPortalUser.get(0);
			} else {
				LOG.info("Email =" + email
						+ " not available in database for the person hashid ="
						+ personHashId);
			}
		} catch (NoResultException e) {
			LOG.error("NoResultException in validateEmail for the email "
					+ email + " and exception is", e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in validate email for the email "
							+ email + " and exception is", e);

		} catch (Exception e) {
			LOG.error("General Exception in validateEmail for the email "
					+ email + " and exception is", e);
		}
		return portalUser;
	}

	/**
	 * @author x178099
	 * @use To save cdiid in Helios DB for the provided email and
	 *      personhashid,if null previously
	 * @param validatecdiidwrapper
	 * @param brand
	 * @return
	 */
	public boolean isCdiidSaved(ValidateCdiidWrapper validatecdiidwrapper,
			EntityManager osEm) {
		boolean response = false;
		OwnerPortalUser ownerPortalUser;

		try {

			List<OwnerPortalUser> ownerPortalUserList = osEm
					.createNamedQuery(
							"OwnerPortalUser.findByemailIdandpersonalHashId",
							OwnerPortalUser.class)
					.setParameter(
							"emailId",
							validatecdiidwrapper.getValidateCustomerSystemId()
									.getEmail())
					.setParameter(
							"personalHashId",
							validatecdiidwrapper.getValidateCustomerSystemId()
									.getPersonHashId()).getResultList();

			if (!ownerPortalUserList.isEmpty()) {
				ownerPortalUser = ownerPortalUserList.get(0);
				if (ownerPortalUser.getCustomerDataIntegrationId() == null
						|| ("").equals(ownerPortalUser
								.getCustomerDataIntegrationId())) {
					ownerPortalUser
							.setCustomerDataIntegrationId(validatecdiidwrapper
									.getValidateCustomerSystemId().getCdiid());
					osEm.merge(ownerPortalUser);
					response = true;
				}
			}

		} catch (NoResultException e) {
			LOG.error("NoResultException in save cdiid for the email"
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getEmail(), e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("save cdiid ArrayIndexOutOfBoundsException for the email"
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getEmail(), e);

		} catch (PersistenceException e) {
			LOG.error("save cdiid Persistence Exception for the email"
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getEmail(), e);
		} catch (Exception e) {
			LOG.error("save cdiid General Exception for the email"
					+ validatecdiidwrapper.getValidateCustomerSystemId()
							.getEmail(), e);
		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To retrieve state code from ST table
	 * @param st
	 * @param osEm
	 * @return
	 */
	public State getStateByStateName(String stateName, EntityManager osEm) {

		State response = null;

		try {

			List<State> state = osEm
					.createNamedQuery("State.findByStateName", State.class)
					.setParameter("stateName", stateName).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(state) && !(state.isEmpty())) {

				LOG.info("state key =" + state.get(0).getStateKey());
				response = state.get(0);
			} else {
				LOG.info("state is not available for stateName" + stateName);

			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getStateByStateName for the stateName"
							+ stateName, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getStateByStateName for the stateName"
							+ stateName, e);

		} catch (Exception e) {
			LOG.error("Exception in getStateByStateName for the stateName"
					+ stateName, e);

		}

		return response;
	}

	/**
	 * @author x178099
	 * @use To update user details
	 * @param SaveUserWrapper
	 * @param stateName
	 * @param user
	 * @return
	 */
	public OwnerPortalUser updateUser(SaveUserWrapper saveUserWrapper,
			OwnerPortalUser ownerPortalUser,
			List<OwnerPortalUserPhone> ownerPortalUserPhone, State stateName,
			MobileProvider mobileProvider, EntityManager osEm) {

		try {
			if (Utility.isObjectNotNullorNotEmpty(saveUserWrapper)) {

				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getTitle())) {
					ownerPortalUser.setPrefixName(saveUserWrapper.getSaveUser()
							.getPerson().getTitle());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getFirstName())) {
					ownerPortalUser.setFirstName(saveUserWrapper.getSaveUser()
							.getPerson().getFirstName());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getMiddleName())) {
					ownerPortalUser.setMiddleName(saveUserWrapper.getSaveUser()
							.getPerson().getMiddleName());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getLastName())) {
					ownerPortalUser.setLastName(saveUserWrapper.getSaveUser()
							.getPerson().getLastName());
				}
				//x566325 - Persisting the secondLastName in OwnrPortlUsr table
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getSecondLastName())) {
					ownerPortalUser.setSuffixName(saveUserWrapper.getSaveUser()
							.getPerson().getSecondLastName());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getEmail())) {
					ownerPortalUser.setEmailId(saveUserWrapper.getSaveUser()
							.getPerson().getEmail());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getAddress()
						.getAddressLine1())) {
					ownerPortalUser.setAddressText(saveUserWrapper
							.getSaveUser().getPerson().getAddress()
							.getAddressLine1());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getPassword())) {
					String encryptPassword = encryptPassword(saveUserWrapper
							.getSaveUser().getPerson().getPassword(), osEm);
					ownerPortalUser.setPassword(encryptPassword);
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getAddress().getCountry())) {
					ownerPortalUser.setCountryCode(saveUserWrapper
							.getSaveUser().getPerson().getAddress()
							.getCountry());
				}
				if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
						.getSaveUser().getPerson().getAddress().getCity())) {
					ownerPortalUser.setCityName(saveUserWrapper.getSaveUser()
							.getPerson().getAddress().getCity());
				}
				if (Utility.isStringNotNullorNotEmpty(stateName.getStateKey())) {
					ownerPortalUser.setStateKey(stateName.getStateKey());
				}

				if (Utility
						.isStringNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson().getAddress()
								.getPostalCode())) {
					ownerPortalUser.setPostalCode(saveUserWrapper.getSaveUser()
							.getPerson().getAddress().getPostalCode());
				}
				if (Utility
						.isStringNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson().getMobileNumber())) {
					ownerPortalUser.setCellPhoneNumber(new BigDecimal(saveUserWrapper.getSaveUser()
							.getPerson().getMobileNumber()));
				}
				
				
				if (("").equals(saveUserWrapper.getSaveUser()
					.getPerson().getLandlineNumber())){
					ownerPortalUser.setWorkPhoneNumber(null);
				}
				
				else if (Utility
						.isStringNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson().getLandlineNumber())) {
					ownerPortalUser.setWorkPhoneNumber(new BigDecimal(saveUserWrapper.getSaveUser()
							.getPerson().getLandlineNumber()));
				}
				
				if (Utility
						.isStringNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson().getMobileNetworkOperator())) {/*
					ownerPortalUser.setMobileCarrNm(saveUserWrapper.getSaveUser()
							.getPerson().getMobileNetworkOperator());
							
				*/LOG.info("Final save for mobile Provider in Database");
					if(mobileProvider != null){
				ownerPortalUser.setMobileCarrNm(mobileProvider.getMobileProviderKey());
					}
				
				}
				

				ownerPortalUser.setUpdateUserId(USERID);
				ownerPortalUser.setUpdateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				osEm.merge(ownerPortalUser);
				osEm.flush();
				Iterator<OwnerPortalUserPhone> iterator = ownerPortalUserPhone
						.iterator();

				while (iterator.hasNext()) {
					OwnerPortalUserPhone userPhone;
					userPhone = iterator.next();
					
					
						LOG.info("inside workphone/home phone");
						if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson().getLandlineNumber())) {
							userPhone.setUpdateTimestamp((new Timestamp(System
									.currentTimeMillis())));
							userPhone.setUpdateUserId(USERID);
							if (saveUserWrapper.getSaveUser().getPerson()
									.getDisclaimerPreferences().getWorkPhone()
									.contains("autodial")) {
								userPhone.setWrkPhnAutoDilrOptIn("1");
							} else {
								userPhone.setWrkPhnAutoDilrOptIn("0");
							}
							if (saveUserWrapper.getSaveUser().getPerson()
									.getDisclaimerPreferences().getWorkPhone()
									.contains("sms")) {
								userPhone.setWrkPhnSmsOptIn("1");
							} else {
								userPhone.setWrkPhnSmsOptIn("0");
							}

						}

						if (Utility.isStringNotNullorNotEmpty(saveUserWrapper
								.getSaveUser().getPerson().getMobileNumber())) {
							LOG.info("inside mobilephone");
							userPhone.setUpdateTimestamp(new Timestamp(System
									.currentTimeMillis()));
							userPhone.setUpdateUserId(USERID);
							if (saveUserWrapper.getSaveUser().getPerson()
									.getDisclaimerPreferences()
									.getMobilePhone().contains("autodial")) {
								userPhone.setMblPhnAutoDilrOptIn("1");
							} else {
								userPhone.setMblPhnAutoDilrOptIn("0");
							}
							if (saveUserWrapper.getSaveUser().getPerson()
									.getDisclaimerPreferences()
									.getMobilePhone().contains("sms")) {
								userPhone.setMblPhnSmsOptIn("1");
							} else {
								userPhone.setMblPhnSmsOptIn("0");
							}
						osEm.merge(userPhone);
						osEm.flush();
					}

				}

			}

		} catch (PersistenceException e) {

			LOG.error("PersistenceException in updateUser for the user = "
					+ saveUserWrapper.getSaveUser().getPerson().getEmail(), e);

		}

		catch (Exception e) {
			LOG.error("Exception in updateUser for the user"
					+ saveUserWrapper.getSaveUser().getPerson().getEmail(), e);

		}

		return ownerPortalUser;
	}

	/**
	 * @author x178099
	 * @use To update user mobile and landline number from Maritz
	 * @param ownerPortalUser
	 * @return ownerPortalUser
	 */
	public OwnerPortalUser updateUser(OwnerPortalUser ownerPortalUser, EntityManager osEm) {

		try {
			ownerPortalUser.setUpdateUserId(USERID);
			ownerPortalUser.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			osEm.merge(ownerPortalUser);
			osEm.flush();
		} catch (PersistenceException e) {

			LOG.error("PersistenceException in updateUser for the user = "
					+ ownerPortalUser.getEmailId(), e);

		}

		catch (Exception e) {
			LOG.error("Exception in updateUser for the user"
					+ ownerPortalUser.getEmailId(), e);

		}

		return ownerPortalUser;
	}

	/**
	 * @author x178099
	 * @use To update user email
	 * @param SaveUserWrapper
	 * @param stateName
	 * @param user
	 * @return
	 */
	public OwnerPortalUser updateEmail(ChangeEmailWrapper changeEmailWrapper,
			OwnerPortalUser ownerPortalUser, EntityManager osEm) {

		try {
			if (Utility.isObjectNotNullorNotEmpty(changeEmailWrapper)) {

				if (Utility.isStringNotNullorNotEmpty(changeEmailWrapper
						.getChangeEmail().getPerson().getEmail())) {
					ownerPortalUser.setPreviousEmailId(changeEmailWrapper
							.getChangeEmail().getPerson().getEmail());
				}
				if (Utility.isStringNotNullorNotEmpty(changeEmailWrapper
						.getChangeEmail().getPerson().getNewEmail())) {
					ownerPortalUser.setEmailId(changeEmailWrapper
							.getChangeEmail().getPerson().getNewEmail());
				}
				// 104026 commented the setAccountStatusCode(LOCK) line because
				// Nissan doesn't want account to be locked
				ownerPortalUser.setUpdateUserId(USERID);
				ownerPortalUser.setUpdateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				osEm.merge(ownerPortalUser);
				osEm.flush();

			}

		} catch (PersistenceException e) {

			LOG.error("PersistenceException in updateUser for the user = "
					+ changeEmailWrapper.getChangeEmail().getPerson()
							.getEmail(), e);

		}

		catch (Exception e) {
			LOG.error("Exception in updateUser for the user"
					+ changeEmailWrapper.getChangeEmail().getPerson()
							.getEmail(), e);

		}

		return ownerPortalUser;
	}

	/**
	 * @author x178099
	 * @use To revoke user email
	 * @param changeEmailRevokeWrapper
	 *            ,ownerPortalUser
	 * 
	 * @return
	 */
	public OwnerPortalUser revokeEmail(
			ChangeEmailRevokeWrapper changeEmailRevokeWrapper,
			OwnerPortalUser ownerPortalUser, EntityManager osEm) {

		try {
			if (Utility.isObjectNotNullorNotEmpty(changeEmailRevokeWrapper)) {

				if (Utility.isStringNotNullorNotEmpty(changeEmailRevokeWrapper
						.getChangeEmailRevoke().getPerson().getEmail())) {
				ownerPortalUser.setPreviousEmailId(changeEmailRevokeWrapper
						.getChangeEmailRevoke().getPerson().getEmail());
				}
				if (Utility.isStringNotNullorNotEmpty(changeEmailRevokeWrapper
						.getChangeEmailRevoke().getPerson().getOldEmail())) {
					ownerPortalUser.setEmailId(changeEmailRevokeWrapper
							.getChangeEmailRevoke().getPerson().getOldEmail());
				}
				ownerPortalUser.setAccountStatusCode(UNLOCK);
				ownerPortalUser.setUpdateUserId(USERID);
				ownerPortalUser.setUpdateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				osEm.merge(ownerPortalUser);
				osEm.flush();

			}

		} catch (PersistenceException e) {

			LOG.error("PersistenceException in revokeEmail for the user = "
					+ changeEmailRevokeWrapper.getChangeEmailRevoke()
							.getPerson().getEmail(), e);

		}

		catch (Exception e) {
			LOG.error("Exception in revokeEmail for the user"
					+ changeEmailRevokeWrapper.getChangeEmailRevoke()
							.getPerson().getEmail(), e);

		}

		return ownerPortalUser;
	}

	/**
	 * @author x178099
	 * @use To encrypt password of user
	 * @param Password
	 * @param osEm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String encryptPassword(String password, EntityManager osEm) {

		String encrypted = "";
		Query encrypt = (Query) osEm
				.createNativeQuery(
						"select MNS_B2C_OWN_APP.encryption_decryption.encrypt(?)  as sPassword from dual",
						Encryption.class).setParameter(1, password);
		List<Encryption> values = encrypt.getResultList();
		encrypted = ((Encryption) values.get(0)).getsPassword();
		
		return encrypted;

	}
	
	

	/**
	 * @author x178099
	 * @use to update account status code as unlock
	 * @param ownerPortalUser
	 * @param osEm
	 * @return
	 */

	public String updateUnlockAccountStatus(OwnerPortalUser ownerPortalUser,
			EntityManager osEm) {
		String response = FAILURE;
		try {
			ownerPortalUser.setAccountStatusCode(UNLOCK);
			ownerPortalUser.setUpdateUserId(USERID);
			ownerPortalUser.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			osEm.merge(ownerPortalUser);
			osEm.flush();
			response = SUCCESS;
			LOG.info("Account unlocked for the user"
					+ ownerPortalUser.getEmailId());
		} catch (PersistenceException e) {

			LOG.error(
					"PersistenceException in updateUnlockAccountStatus for the user = "
							+ ownerPortalUser.getEmailId(), e);

		}

		catch (Exception e) {
			LOG.error("Exception in updateUnlockAccountStatus for the user"
					+ ownerPortalUser.getEmailId(), e);

		}
		return response;
	}

	/**
	 * @author x178099
	 * @use To decrypt password of user
	 * @param Password
	 * @param osEm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String decryptPassword(String password, EntityManager osEm) {
		String decrypted = "";
		try {
			
			Query decrypt = (Query) osEm
					.createNativeQuery(
							"select MNS_B2C_OWN_APP.encryption_decryption.decrypt(?) as pPassword from dual",
							Decryption.class).setParameter(1, password);
			List<Decryption> values = decrypt.getResultList();

			decrypted = ((Decryption) values.get(0)).getpPassword();

			

		} catch (Exception e) {
			LOG.info("Password failed to decrypt.", e);
		}
		return decrypted;

	}

	/**
	 * @author x796314
	 * @use To get the userphone details
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */

	public List<OwnerPortalUserPhone> getUserPhoneDetails(String userProfileId,
			EntityManager osEm) {

		LOG.info("Inside validate user vehicle service ");
		List<OwnerPortalUserPhone> response = null;

		try {

			List<OwnerPortalUserPhone> ownerPortalUserPhone = osEm
					.createNamedQuery(
							"OwnerPortalUserPhone.findByuserProfileId",
							OwnerPortalUserPhone.class)
					.setParameter("userProfileId", userProfileId)
					.getResultList();

			if (!(ownerPortalUserPhone.isEmpty())) {
				LOG.info("Phone details" + ownerPortalUserPhone);
				response = ownerPortalUserPhone;
			} else {
				LOG.info("User is not availble in the db");

			}

		} catch (NoResultException e) {
			LOG.error("Inside getUserPhoneDetails NoResultException", e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"Inside getUserPhoneDetails ArrayIndexOutOfBoundsException",
					e);

		} catch (Exception e) {
			LOG.error("Inside getUserPhoneDetails Exception", e);

		}

		return response;

	}

	/**
	 * @author x178099
	 * @use To create user account and persist data in Helios DB
	 * @param requestJson
	 * @param osEm
	 * @return
	 */

	public boolean createUser(AccountVO accountVO, EntityManager osEm) {
		LOG.info("Create User Service layer for request ");
		List<OwnerPortalUser> ownerPortalUser = null;

		OwnerPortalUser portalUser = new OwnerPortalUser();
		OwnerPortalUserPhone workPhone = new OwnerPortalUserPhone();
		OwnerPortalUserPhone mobilePhone = new OwnerPortalUserPhone();
		String email = "";
		State stateName = null;
		MobileProvider mobileProviderKey = null;
		boolean response = false;
		if (Utility.isStringNotNullorNotEmpty(accountVO.getEmail())) {
			email = accountVO.getEmail().trim();
			// Check whether email id already exists in DB
		}
		ownerPortalUser = osEm
				.createNamedQuery("OwnerPortalUser.findByemailId",
						OwnerPortalUser.class)
				.setParameter("emailId", email.toLowerCase()).getResultList();
		LOG.info("User Exists for the email " + email);
		if (Utility.isObjectNotNullorNotEmpty(ownerPortalUser)
				&& !(ownerPortalUser.isEmpty())) {

			LOG.info("User Exists");

		} else {
			LOG.info("User does not exist in database. proceed for create account");
			Query dbSequence = osEm
					.createNativeQuery("SELECT USR_PRFL_ID_SEQ.NEXTVAL FROM DUAL");
			List userProfileList = dbSequence.getResultList();
			String personHashId = UUID.randomUUID().toString();
			LOG.info("db sequence list is= " + userProfileList);
			LOG.info("db sequence is= " + userProfileList.get(0));
			String userProfileId = String.valueOf(userProfileList.get(0));
			LOG.info("userProfileId is= " + userProfileId);

			if (Utility.isStringNotNullorNotEmpty(accountVO.getAddress()
					.getRegion())) {
				String userStateName = accountVO.getAddress().getRegion();

				stateName = getStateByStateName(userStateName, osEm);
			}

			if (accountVO.getMobileNetworkOperator() != null) {
				mobileProviderKey = getProviderByProviderName(
						accountVO.getMobileNetworkOperator(), osEm);
			}

			portalUser.setUserProfileId(userProfileId);
			portalUser.setCustomerDataIntegrationId(userProfileId);
			portalUser.setFirstName(accountVO.getFirstName());
			portalUser.setMiddleName(accountVO.getMiddleName());
			portalUser.setLastName(accountVO.getLastName());
			portalUser.setSuffixName(accountVO.getSecondLastName());
			portalUser.setEmailId(email);
			portalUser.setAccountStatusCode("ACT");
			portalUser.setAddressText(accountVO.getAddress().getAddressLine1());
			portalUser.setPersonalHashId(personHashId);
			portalUser.setPrefixName(accountVO.getTitle());
			if(null !=stateName){
			portalUser.setStateKey(stateName.getStateKey());
			}
			portalUser.setCityName(accountVO.getAddress().getCity());
			portalUser.setCountryCode(accountVO.getAddress().getCountry());
			portalUser.setPostalCode(accountVO.getAddress().getPostalCode());
			portalUser.setPassword(accountVO.getPassword());
			portalUser.setLanguageCode("EN");
			portalUser.setCreateUserId(USERID);
			portalUser.setCreateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			portalUser.setUpdateUserId(USERID);
			portalUser.setUpdateTimestamp(new Timestamp(System
					.currentTimeMillis()));
			portalUser.setLocalUtilityOptOutCode(new BigDecimal("9"));
			portalUser.setCellPhoneNumber(new BigDecimal(accountVO.getMobileNumber()));
			portalUser.setWorkPhoneNumber(new BigDecimal(accountVO.getLandlineNumber()));
			portalUser.setMobileCarrNm(accountVO.getMobileNetworkOperator());

			osEm.persist(portalUser);

			if (accountVO.getLandlineNumber() != null) {
				workPhone.setCreateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				workPhone.setCreateUserId(USERID);
				OwnerPortalUserPhonePK ownerPortalUserPhonePK = new OwnerPortalUserPhonePK();
				ownerPortalUserPhonePK.setUserProfileId(userProfileId);
//				ownerPortalUserPhonePK.setPhoneTypeId("work");
				workPhone.setOwnerPortalUserPhonePK(ownerPortalUserPhonePK);
//				workPhone.setPriorityNumber("2");
				workPhone.setUpdateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				workPhone.setUpdateUserId(USERID);
				workPhone.setWrkPhnAutoDilrOptIn("2");
				workPhone.setWrkPhnSmsOptIn("2");
//				workPhone.setPhoneNumber(accountVO.getLandlineNumber());
				osEm.persist(workPhone);

			}

			if (accountVO.getMobileNumber() != null) {
				mobilePhone.setCreateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				mobilePhone.setCreateUserId(USERID);
				OwnerPortalUserPhonePK ownerPortalUserPhonePK = new OwnerPortalUserPhonePK();
				ownerPortalUserPhonePK.setUserProfileId(userProfileId);
//				ownerPortalUserPhonePK.setPhoneTypeId("mobile");
				mobilePhone.setOwnerPortalUserPhonePK(ownerPortalUserPhonePK);
//				mobilePhone.setPriorityNumber("1");
				mobilePhone.setUpdateTimestamp(new Timestamp(System
						.currentTimeMillis()));
				mobilePhone.setUpdateUserId(USERID);
				mobilePhone.setMblPhnAutoDilrOptIn("2");
				mobilePhone.setMblPhnSmsOptIn("2");
//				mobilePhone.setPhoneNumber(accountVO.getMobileNumber());
//				mobilePhone.setMobileCarrierCode(mobileProviderKey.getMobileProviderKey());
				osEm.persist(mobilePhone);

			}

			response = true;
		}

		return response;
	}

	public MobileProvider getProviderByProviderName(String mobileCarrierName,
			EntityManager osEm) {
		List<MobileProvider> mobileProvider = null;
		MobileProvider carrier = null;

		try {
			LOG.info("Inside try  getProviderByProviderName"+mobileCarrierName);
			
		
			if(mobileCarrierName.startsWith("US_")){
				LOG.info("Inside if getProviderByProviderName "+mobileCarrierName);
				mobileProvider = osEm
						.createNamedQuery("MobileProvider.findByMobileProviderKey",
								MobileProvider.class)
						.setParameter("mobileProviderKey", mobileCarrierName)
						.getResultList();
			}else{
			mobileProvider = osEm
					.createNamedQuery(
							"MobileProvider.findByMobileProviderName",
							MobileProvider.class)
					.setParameter("mobileProviderName", mobileCarrierName)
					.getResultList();

			}
			
			if (Utility.isObjectNotNullorNotEmpty(mobileProvider)
					&& !(mobileProvider.isEmpty())) {				
				LOG.info("Mobile Provider key =" + mobileProvider.get(0));
				carrier = mobileProvider.get(0);
			} else {
				LOG.info("Mobile Provider is not available for mobileCarrierName"
						+ mobileCarrierName);

			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getProviderByProviderName for the mobileCarrierName"
							+ mobileCarrierName, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getProviderByProviderName for the stateName"
							+ mobileCarrierName, e);

		} catch (Exception e) {
			LOG.error(
					"Exception in getProviderByProviderName for the stateName"
							+ mobileCarrierName, e);

		}

		LOG.info("Final getProviderByProviderName  Mobile Provider key =" + carrier);
		return carrier;

	}

	/**
	 * @author x178099
	 * @use To fetch the mobile carrier data based on carrier code
	 * @param mobileCarrierCode
	 * @param osEm
	 * @return
	 */
	public MobileProvider getProviderByProviderCode(OwnerPortalUser validUser,String mobileCarrierCode,
			EntityManager osEm) {
		List<MobileProvider> mobileProvider = null;
		MobileProvider carrier = null;

		try {
if(mobileCarrierCode.startsWith("US_")){
	LOG.info("inside if "+mobileCarrierCode);
	mobileProvider = osEm
			.createNamedQuery("MobileProvider.findByMobileProviderKey",
					MobileProvider.class)
			.setParameter("mobileProviderKey", mobileCarrierCode)
			.getResultList();
}else{
	LOG.info("inside else "+mobileCarrierCode);
	mobileProvider = osEm
			.createNamedQuery("MobileProvider.findByMobileProviderName",
					MobileProvider.class)
			.setParameter("mobileProviderName", mobileCarrierCode)
			.getResultList();
	if (Utility.isObjectNotNullorNotEmpty(mobileProvider)
			&& !(mobileProvider.isEmpty())) {
		LOG.info("Mobile Provider key = " + mobileProvider.get(0));
		validUser.setMobileCarrNm(mobileProvider.get(0).getMobileProviderKey());
		osEm.merge(validUser);
	}
	
}
			
			
			

			if (Utility.isObjectNotNullorNotEmpty(mobileProvider)
					&& !(mobileProvider.isEmpty())) {
				LOG.info("Mobile Provider key = " + mobileProvider.get(0));
				carrier = mobileProvider.get(0);
			} else {
				LOG.info("Mobile Provider is not available for mobileCarrierCode"
						+ mobileCarrierCode);

			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getProviderByProviderCode for the mobileCarrierCode"
							+ mobileCarrierCode, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getProviderByProviderCode for the stateName"
							+ mobileCarrierCode, e);

		} catch (Exception e) {
			LOG.error(
					"Exception in getProviderByProviderCode for the stateName"
							+ mobileCarrierCode, e);

		}
		LOG.info("Final getProviderByProvidercodee  Mobile Provider key =" + carrier);
		return carrier;

	}

	public OwnerPortalLinkReference getOwnerPortalLinkReferenceDetails(
			String linkReferenceId, EntityManager osEm) {

		List<OwnerPortalLinkReference> ownerPortalLinkReferenceList = null;
		OwnerPortalLinkReference response = null;
		String activeIn = "Y";
		try {

			ownerPortalLinkReferenceList = osEm
					.createNamedQuery(
							"OwnerPortalLinkReference.findByLinkReferenceHashId",
							OwnerPortalLinkReference.class)
					.setParameter("linkReferenceHashId", linkReferenceId)
					.setParameter("activeIn", activeIn).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalLinkReferenceList)
					&& !(ownerPortalLinkReferenceList.isEmpty())) {

				LOG.info("linkReferenceHashId ="
						+ ownerPortalLinkReferenceList.get(0)
								.getLinkReferenceHashId());
				response = ownerPortalLinkReferenceList.get(0);
			} else {
				LOG.info("Link reference details are not available  for linkReferenceId="
						+ linkReferenceId);

			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ linkReferenceId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ linkReferenceId, e);

		} catch (Exception e) {
			LOG.error(
					"Exception in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ linkReferenceId, e);

		}

		return response;

	}

	/**
	 * @author x178099
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */

	public String createLinkReferenceId(String userProfileId, EntityManager osEm) {

		OwnerPortalLinkReference linkRef = new OwnerPortalLinkReference();
		String token = "";

		try {

			token = UUID.randomUUID().toString();

			linkRef.setUserProfileId(userProfileId);
			linkRef.setLinkReferenceHashId(token);
			linkRef.setFunctionTypeId("");
			linkRef.setActiveIn("Y");
			linkRef.setCreateUserId(USERID);
			linkRef.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));

			osEm.persist(linkRef);

		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ userProfileId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ userProfileId, e);

		} catch (Exception e) {
			LOG.error(
					"Exception in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ userProfileId, e);

		}

		return token;

	}

	/**
	 * 
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */
	public OwnerPortalUser getUserDetails(String userProfileId,
			EntityManager osEm) {

		OwnerPortalUser response = null;
		try {

			List<OwnerPortalUser> ownerPortalUser = osEm
					.createNamedQuery("OwnerPortalUser.findByuserProfileId",
							OwnerPortalUser.class)
					.setParameter("userProfileId", userProfileId)
					.getResultList();

			if (!ownerPortalUser.isEmpty()) {

				LOG.info("Phone Disclaimer values exist for the user "
						+ userProfileId);
				response = ownerPortalUser.get(0);

			} else {
				LOG.info("Inside disclaimer values null for the user"
						+ userProfileId);

			}
		} catch (NoResultException e) {
			LOG.error(
					"getUserDetails No Result Exception for the userprofileid"
							+ userProfileId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"getUserDetails ArrayIndexOutOfBoundsException for the userprofileid"
							+ userProfileId, e);

		} catch (Exception e) {
			LOG.error("getUserDetails General Exception for the userprofileid"
					+ userProfileId, e);
		}
		return response;
	}
	
	
	

	/**
	 * @author x796314
	 * @use To update the status of the status of link reference id
	 * @param ownerPortalLinkReference
	 */
	public void updateLinkReferenceHashIdStatus(
			OwnerPortalLinkReference ownerPortalLinkReference,
			EntityManager osEm) {
		LOG.info("Inside service");
		if (Utility.isObjectNotNullorNotEmpty(ownerPortalLinkReference)) {

			int query = osEm
					.createQuery(
							"Update OwnerPortalLinkReference set activeIn='N' where linkReferenceHashId= ?1 and userProfileId = ?2 ")
					.setParameter(1,
							ownerPortalLinkReference.getLinkReferenceHashId())
					.setParameter(2,
							ownerPortalLinkReference.getUserProfileId())
					.executeUpdate();
			LOG.info("number of rows updated=" + query);

		}

	}

	/**
	 * @author x178099
	 * @param linkReferenceId
	 * @param osEm
	 * @return
	 */
	public OwnerPortalLinkReference getLinkReferenceDetails(
			String linkReferenceId, String userProfileID, EntityManager osEm) {

		List<OwnerPortalLinkReference> ownerPortalLinkReferenceList = null;
		OwnerPortalLinkReference response = null;
		String activeIn = "Y";
		try {

			ownerPortalLinkReferenceList = osEm
					.createNamedQuery(
							"OwnerPortalLinkReference.findByLinkReferenceHashIdandEmailId",
							OwnerPortalLinkReference.class)
					.setParameter("linkReferenceHashId", linkReferenceId)
					.setParameter("userProfileId", userProfileID)
					.setParameter("activeIn", activeIn).getResultList();

			if (Utility.isObjectNotNullorNotEmpty(ownerPortalLinkReferenceList)
					&& !(ownerPortalLinkReferenceList.isEmpty())) {

				LOG.info("linkReferenceHashId ="
						+ ownerPortalLinkReferenceList.get(0)
								.getLinkReferenceHashId());
				response = ownerPortalLinkReferenceList.get(0);
			} else {
				LOG.info("Link reference details are not available  for linkReferenceId="
						+ linkReferenceId);

			}
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ linkReferenceId, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ linkReferenceId, e);

		} catch (Exception e) {
			LOG.error(
					"Exception in getOwnerPortalLinkReferenceDetails for the linkReferenceId="
							+ linkReferenceId, e);

		}

		return response;

	}

	/**
	 * @author x178099
	 * @use To update linkreferenceid using userprofile id
	 * @param linkReferenceId
	 * @param userProfileID
	 * @param osEm
	 * @return
	 */
	public void updateLinkReferenceHashIdStatusByUserProfileId(
			String userProfileID, EntityManager osEm) {

		if (Utility.isStringNotNullorNotEmpty(userProfileID)) {

			int query = osEm
					.createQuery(
							"Update OwnerPortalLinkReference set activeIn='N' where userProfileId = ? ")

					.setParameter(1, userProfileID).executeUpdate();
			LOG.info("number of rows updated=" + query);

		}

	}
	
	
	/**
	 * @author x055765
	 * @param userProfileId
	 * @param notificationCode
	 * @param osEm
	 * @return
	 */

	public String saveLnkRefrncCodeFromWso2(String notificationCode, String userProfileId,String brand,EntityManager osEm) {
		
		LOG.info("UserService: saveLnkRefrncCodeFromWso2 & notificationCode to save in DB : " + notificationCode);

		OwnerPortalLinkReference linkRef = new OwnerPortalLinkReference();
		String response = null;

		try {
		
				linkRef.setUserProfileId(userProfileId);
				linkRef.setLinkReferenceHashId(notificationCode);
				if(brand.equalsIgnoreCase("N")){
					linkRef.setFunctionTypeId("N");
				}else{
					linkRef.setFunctionTypeId("I");
				}
			
				linkRef.setActiveIn("Y");
				linkRef.setCreateUserId(USERID);
				linkRef.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));

				osEm.persist(linkRef);

				response = "success";
		
		} catch (NoResultException e) {
			LOG.error(
					"NoResultException in saveLnkRefrncCodeFromWso2 for the linkReferenceId="
							+ userProfileId, e);
			response = "failure";


		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"ArrayIndexOutOfBoundsException in saveLnkRefrncCodeFromWso2 for the linkReferenceId="
							+ userProfileId, e);
			response = "failure";


		} catch (Exception e) {
			LOG.error(
					"Exception in saveLnkRefrncCodeFromWso2 for the linkReferenceId="
							+ userProfileId, e);
			response = "failure";


		}

         return response;
	}
	
	/**
	 * 
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */
	public List<FAQDetails> getFaqs(String brand, String appName, Integer status,
			EntityManager osEm) {

		List<FAQDetails> response = null;
		try {

			List<FAQDetails> faqDetails = osEm
					.createNamedQuery("FAQDetails.findByBrandAndAppName",
							FAQDetails.class)
					.setParameter("brandName", brand.toLowerCase())
					.setParameter("appName", appName.toLowerCase())
					.setParameter("faqStatus", status)
					.getResultList();

			if (!faqDetails.isEmpty()) {

				LOG.info("FAQ data exists for the Brand : "
						+ brand + " and app : " + appName);
				response = faqDetails;

			} else {
				LOG.info("FAQ data does not exists for the Brand : "
						+ brand + " and app : " + appName);

			}
		} catch (NoResultException e) {
			LOG.error(
					"GetFAQs No Result Exception for the Brand : "
							+ brand + " and app : " + appName, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"GetFAQs ArrayIndexOutOfBoundsException for the Brand : "
							+ brand + " and app : " + appName, e);

		} catch (Exception e) {
			LOG.error("GetFAQs General Exception for the Brand : "
					+ brand + " and app : " + appName, e);
		}
		return response;
	}
	
	/**
	 * 
	 * @param userProfileId
	 * @param osEm
	 * @return
	 */
	public String getFaqsHeader(String headerCode, Integer status,
			EntityManager osEm) {

		String response = "";
		try {

			List<FAQHeaderLookup> faqHeaderData = osEm
					.createNamedQuery("FAQHeaderLookup.findByHeadingCode",
							FAQHeaderLookup.class)
					.setParameter("faqHeadingCode", headerCode.toLowerCase())
					.setParameter("faqHeadingStatus", status)
					.getResultList();


			if (!faqHeaderData.isEmpty()) {
				LOG.info("FAQ header name exists for the header code : "
						+ headerCode);
				response = faqHeaderData.get(0).getFaqHeadingName();

			} else {
				LOG.info("FAQ header name does not exists for the header code : "
						+ headerCode);

			}
		} catch (NoResultException e) {
			LOG.error(
					"GetFAQs Header No Result Exception for the header code : "
							+ headerCode, e);

		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error(
					"GetFAQs Header ArrayIndexOutOfBoundsException for the header code : "
							+ headerCode, e);

		} catch (Exception e) {
			LOG.error("GetFAQs Header General Exception for the header code : "
					+ headerCode, e);
		}
		return response;
	}
	
}
