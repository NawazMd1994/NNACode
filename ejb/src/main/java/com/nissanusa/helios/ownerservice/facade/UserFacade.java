package com.nissanusa.helios.ownerservice.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nissanusa.helios.ownerservice.entity.FAQDetails;
import com.nissanusa.helios.ownerservice.entity.MobileProvider;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalLinkReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalPhoneDisclaimer;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhone;
import com.nissanusa.helios.ownerservice.entity.State;
import com.nissanusa.helios.ownerservice.service.UserService;
import com.nissanusa.helios.ownerservice.vo.AccountVO;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailRevokeWrapper;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveUserWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateCdiidWrapper;

@Stateless
/**
 * 
 * @author x178099
 * @use Facade Implementation for userLocal

 */
public class UserFacade implements UserLocal {

    @Inject
    EntityManager osEm;

    /**
     * @author x796314
     * @use overridden method for userLocal to validateCdiid
     * @param validatecdiidwrapper
     * @return validateCdiid method object of user service class
     */

    @Override
    public String validateCdiid(ValidateCdiidWrapper validatecdiidwrapper) {

        return new UserService().validateCdiid(validatecdiidwrapper, osEm);
    }
   /**
    * @author x178099
    * @use to update account status code as unlock
    */

    @Override
    public String updateUnlockAccountStatus(OwnerPortalUser ownerPortalUser) {

        return new UserService().updateUnlockAccountStatus(ownerPortalUser,osEm);
    }

    /**
     * @author x178099
     * @use overridden method for userLocal to update email
     * @param changeEmailWrapper
     *            ,ownerPortalUser
     * @return changeEmail method object of user service class
     */

    @Override
    public OwnerPortalUser updateEmail(ChangeEmailWrapper changeEmailWrapper, OwnerPortalUser ownerPortalUser) {

        return new UserService().updateEmail(changeEmailWrapper, ownerPortalUser, osEm);
    }
    /**
     * @author x178099
     * @use overridden method for userLocal to revoke email
     * @param changeEmailRevokeWrapper
     *            ,ownerPortalUser
     * @return changeEmail method object of user service class
     */

    @Override
    public OwnerPortalUser revokeEmail(ChangeEmailRevokeWrapper changeEmailRevokeWrapper, OwnerPortalUser ownerPortalUser) {

        return new UserService().revokeEmail(changeEmailRevokeWrapper, ownerPortalUser, osEm);
    }
    /**
     * @author x178099
     * @use overridden method for userLocal to generate token
     * @param userProfileId
     * @return changeEmail method object of user service class
     */

    @Override
    public String createLinkReferenceId(String userProfileId) {

        return new UserService().createLinkReferenceId(userProfileId, osEm);
    }

	/**
	 * @author x055765
	 * @useoverridden method for userLocal to validateEmail
	 * @param email
	 *            ,personHashId, brand
	 * @return validateEmail method object of user service class
	 */

	@Override
	public OwnerPortalUser validateEmail(String email, String personHashId,
			String brand) {

		return new UserService()
				.validateEmail(email, personHashId, brand, osEm);
	}
    
    /**
     * @author x796314
     * @use overridden method for userLocal to getPhoneDisclaimer
     * @param userProfileId
     * @return getPhoneDisclaimer method object of user service class
     */

    @Override
    public OwnerPortalPhoneDisclaimer getPhoneDisclaimer(String userProfileId) {

        return new UserService().getPhoneDisclaimer(userProfileId, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getState
     * @param state
     * @return getState method object of user service class
     */

    @Override
    public State getStateByStateKey(String state) {

        return new UserService().getStateByStateKey(state, osEm);
    }

    @Override
    public State getStateCode(String state) {

        return new UserService().getStateCode(state, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to validateUser
     * @param email
     *            ,brand
     * @return validateUser method object of user service class
     */

    @Override
    public OwnerPortalUser validateUser(String email, String brand) {

        return new UserService().validateUser(email, brand, osEm);
    }

    @Override
    public OwnerPortalUser validateUserforVehicalDisposal(String personHashId, String brand) {

        return new UserService().validateUserforVehicalDisposal(personHashId, brand, osEm);
    }

    
    /**
     * @author x796314
     * @use overridden method for userLocal to getStateByStateName
     * @param stateName
     * @return getStateByStateName method object of user service class
     */

    @Override
    public State getStateByStateName(String stateName) {

        return new UserService().getStateByStateName(stateName, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to updateUser
     * @param saveUserWrapper
     *            ,ownerPortalUser,stateName
     * @return updateUser method object of user service class
     */

    @Override
    public OwnerPortalUser updateUser(SaveUserWrapper saveUserWrapper,
            OwnerPortalUser ownerPortalUser,
            List<OwnerPortalUserPhone> ownerPortalUserPhone, State stateName,
            MobileProvider mobileProvider) {

        return new UserService().updateUser(saveUserWrapper, ownerPortalUser,
                ownerPortalUserPhone, stateName, mobileProvider, osEm);
    }
    
    /**
     * @author x497432
     * @use overridden method for userLocal to updateUser
     * @param ownerPortalUser
     * @return updateUser method object of user service class
     */

    @Override
    public OwnerPortalUser updateUser(OwnerPortalUser ownerPortalUser) {

        return new UserService().updateUser(ownerPortalUser,osEm);
    }

    /**
     * @author x796314
     * @use To get the userphone details
     * @param userProfileId
     * @param osEm
     * @return
     */
    @Override
    public List<OwnerPortalUserPhone> getUserPhoneDetails(String userProfileId) {

        return new UserService().getUserPhoneDetails(userProfileId, osEm);
    }

    /**
     * @author x178099
     * @use To create User account
     * @param requestJson
     * @param osEm
     * @return
     */
    @Override
    public boolean createUser(AccountVO accountVO) {
        return new UserService().createUser(accountVO, osEm);
    }

    /**
     * @author x178099
     * @use To retrieve mobile provider details based on carrier name
     * @param requestJson
     * @param osEm
     * @return
     */
    @Override
    public MobileProvider getProviderByProviderName(String mobileCarrierName) {

        return new UserService().getProviderByProviderName(mobileCarrierName,
                osEm);
    }

    /**
     * @author x178099
     * @use To retrieve mobile provider details based on carrier code
     * @param requestJson
     * @param osEm
     * @return
     */
    @Override
    public MobileProvider getProviderByProviderCode(OwnerPortalUser validUser,String mobileCarrierCode) {

        return new UserService().getProviderByProviderCode(validUser,mobileCarrierCode,
                osEm);
    }

    @Override
    public OwnerPortalLinkReference getOwnerPortalLinkReferenceDetails(
            String linkReferenceId) {

        return new UserService().getOwnerPortalLinkReferenceDetails(
                linkReferenceId, osEm);
    }

    @Override
    public OwnerPortalLinkReference getLinkReferenceDetails(
            String linkReferenceId, String userProfileID) {

        return new UserService().getLinkReferenceDetails(
                linkReferenceId,userProfileID, osEm);
    }
    
    @Override
    public void updateLinkReferenceHashIdStatusByUserProfileId(
             String userProfileID) {

         new UserService().updateLinkReferenceHashIdStatusByUserProfileId(
                userProfileID, osEm);
    }
    @Override
    public OwnerPortalUser getUserDetails(String userProfileId) {

        return new UserService().getUserDetails(userProfileId, osEm);
    }

   
    
    @Override
    public void updateLinkReferenceHashIdStatus(
            OwnerPortalLinkReference ownerPortalLinkReference) {
        new UserService().updateLinkReferenceHashIdStatus(
                ownerPortalLinkReference, osEm);

    }

    /**
     * @author x178099
     * @use To retrieve decrypted password
     * @param password
     * @param osEm
     * @return
     */
    @Override
    public String decryptPassword(String password) {
        return new UserService().decryptPassword(password, osEm);
    }
    
    /**
     * @author x796314
     * @useoverridden method for userLocal to validateEmail
     * @param email
     *            ,personHashId
     * @return validateEmail method object of user service class
     */

   
    
    @Override
    public String saveLnkRefrncCodeFromWso2(String notificationCode, String userProfileId,String brand) {

        return new UserService().saveLnkRefrncCodeFromWso2(notificationCode,userProfileId,brand, osEm);
    }

   
    @Override
    public List<FAQDetails> getFaqs(String brand, String appName, Integer status) {

        return new UserService().getFaqs(brand, appName, status, osEm);
    }
    
    @Override
    public String getFaqsHeader(String headerCode, Integer status) {

        return new UserService().getFaqsHeader(headerCode, status, osEm);
    }

}
