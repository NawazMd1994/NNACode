package com.nissanusa.helios.ownerservice.facade;

/*
 *last modified date 27-05-2016 by x178099

 */
import java.util.List;

import javax.ejb.Local;

import com.nissanusa.helios.ownerservice.entity.FAQDetails;
import com.nissanusa.helios.ownerservice.entity.MobileProvider;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalLinkReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalPhoneDisclaimer;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhone;
import com.nissanusa.helios.ownerservice.entity.State;
import com.nissanusa.helios.ownerservice.vo.AccountVO;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailRevokeWrapper;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveUserWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateCdiidWrapper;

/**
 * 
 * @author x178099
 * @use Local Implementation for user services
 *
 */
@Local
public interface UserLocal {

    String validateCdiid(ValidateCdiidWrapper validatecdiidwrapper);

    OwnerPortalUser updateEmail(ChangeEmailWrapper changeEmailWrapper,
            OwnerPortalUser ownerPortalUser);
    
    String updateUnlockAccountStatus(OwnerPortalUser ownerPortalUser);

    OwnerPortalUser revokeEmail(
            ChangeEmailRevokeWrapper changeEmailRevokeWrapper,
            OwnerPortalUser ownerPortalUser);

    String decryptPassword(String password);

    String createLinkReferenceId(String userProfileId);

	OwnerPortalUser validateEmail(String email, String personHashId,
			String brand);
    
    OwnerPortalPhoneDisclaimer getPhoneDisclaimer(String userProfileId);

    OwnerPortalUser getUserDetails(String userProfileId);
    
    State getStateByStateKey(String state);

    State getStateCode(String state);

    OwnerPortalUser validateUser(String email, String brand);
    
    OwnerPortalUser validateUserforVehicalDisposal(String personHashId, String brand);

    State getStateByStateName(String stateName);

    MobileProvider getProviderByProviderName(String mobileCarrierName);

    MobileProvider getProviderByProviderCode(OwnerPortalUser validUser,String mobileCarrierCode);

    OwnerPortalUser updateUser(SaveUserWrapper saveUserWrapper,
            OwnerPortalUser ownerPortalUser,
            List<OwnerPortalUserPhone> ownerPortalUserPhone, State stateName,
            MobileProvider mobileProvider);
    
    // x497432 Added to update Mobile and landline number from Maritz during get account
    OwnerPortalUser updateUser(OwnerPortalUser ownerPortalUser);

    List<OwnerPortalUserPhone> getUserPhoneDetails(String userProfileId);

    boolean createUser(AccountVO accountVO);

    OwnerPortalLinkReference getOwnerPortalLinkReferenceDetails(
            String linkReferenceId);

    OwnerPortalLinkReference getLinkReferenceDetails(String linkReferenceId,
            String userProfileId);

    void updateLinkReferenceHashIdStatusByUserProfileId(
            String userProfileId);
    
    void updateLinkReferenceHashIdStatus(
            OwnerPortalLinkReference ownerPortalLinkReference);
    
    String saveLnkRefrncCodeFromWso2(String notificationCode, String userProfileId,String brand);
    
    List<FAQDetails> getFaqs(String brand, String appName, Integer status);
    
    String getFaqsHeader(String headerCode, Integer status);

}
