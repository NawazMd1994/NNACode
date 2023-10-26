package com.nissanusa.helios.ownerservice.facade;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.nissanusa.helios.ownerservice.entity.ASServiceContractCategoryLookup;
import com.nissanusa.helios.ownerservice.entity.Equipment;
import com.nissanusa.helios.ownerservice.entity.ManualVehicleLookup;
import com.nissanusa.helios.ownerservice.entity.ModelLineMapping;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalSubScriptionProduct;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicleFailedReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicleTelematicsCodeMaster;
import com.nissanusa.helios.ownerservice.entity.TelematicstermsAndConditions;
import com.nissanusa.helios.ownerservice.entity.TermsAndConditionsAgreementSt;
import com.nissanusa.helios.ownerservice.entity.TermsAndConditionsAgreementStPK;
import com.nissanusa.helios.ownerservice.entity.VehdsplMaritzDlyLndgPrcngEntity;
import com.nissanusa.helios.ownerservice.entity.VehicleCarwings;
import com.nissanusa.helios.ownerservice.entity.VehicleSpecification;
import com.nissanusa.helios.ownerservice.service.UserService;
import com.nissanusa.helios.ownerservice.service.VehicleService;
import com.nissanusa.helios.ownerservice.vo.FailedVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UpdateVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UploadDocumentWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateDataWrapper;

/**
 * 
 * @author x178099
 * @use Facade Implementation for vehicleLocal
 *
 */
@Stateless
public class VehicleFacade implements VehicleLocal {
    @Inject
    EntityManager osEm;

    // X787640 -- Injecting AfterSales DataSource
    @Inject
    public EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("persistence-unit-HeliosASOracleDS");
        return factory.createEntityManager();
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to Lookup vin in manualvehicleLoad
     *      table
     * @param vin
     * @return vinLookup method object of vehicle service class
     */
    @Override
    public ManualVehicleLookup vinLookup(String vin) {

        return new VehicleService().vinLookup(vin, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to saveVehicle
     * @param ownerPortalUser
     *            ,saveVehicleWrapper
     * @return saveVehicle method object of vehicle service class
     */

    @Override
    public String saveVehicle(SaveVehicleWrapper saveVehicleWrapper,
            OwnerPortalUser ownerPortalUser) {

        return new VehicleService().saveVehicle(saveVehicleWrapper,
                ownerPortalUser, osEm);
    }
   
    /**
     * @author x796314
     * @use overridden method for userLocal to saveVehicle
     * @param ownerPortalUser
     *            ,saveVehicleWrapper
     * @return saveVehicle method object of vehicle service class
     */

    @Override
    public String saveFailedVehicle(FailedVehicleWrapper failedVehicleWrapper,
            OwnerPortalUser validUser,OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference,String brand) {

        return new VehicleService().saveFailedVehicle(failedVehicleWrapper,
        		validUser,ownerPortalUserVehicleFailedReference,brand, osEm);
    }
    
    @Override
    public String loadDocument(UploadDocumentWrapper uploadDocumentWrapper,String brand
           ) {

        return new VehicleService().loadDocument(uploadDocumentWrapper,brand,osEm);
               
    }
    /**
     * @author x796314
     * @use overridden method for vehicleLocal to validateUserVehicle
     * @param validateDataWrapper
     *            ,ownerPortalUser
     * @return validateUserVehicle method object of vehicle service class
     */

    @Override
    public String validateUserVehicle(ValidateDataWrapper validateDataWrapper,
            OwnerPortalUser validUser,String brand) {

        return new VehicleService().validateUserVehicle(validateDataWrapper,
        		validUser,brand, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to updateVehicle
     * @param updateVehicleWrapper
     *            ,OwnerPortalUser
     * @return updateVehicle method object of vehicle service class
     */

    @Override
    public String updateVehicle(UpdateVehicleWrapper updateVehicleWrapper,
            OwnerPortalUser ownerPortalUser) {

        return new VehicleService().updateVehicle(updateVehicleWrapper,
                ownerPortalUser, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to validateVehicleOwner
     * @param vin
     *            ,userProfileId
     * @return validateVehicleOwner method object of vehicle service class
     */
    @Override
    public String validateVehicleOwner(String userProfileId, String vin) {

        return new VehicleService().validateVehicleOwner(userProfileId, vin,
                osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getVehicleInfo
     * @param vin
     * @return getVehicleInfo method object of vehicle service class
     */
    @Override
    public OwnerPortalVehicle getVehicleInfo(String vin) {

        return new VehicleService().getVehicleInfo(vin, osEm);
    }

    /**
     * @author x116202
     * @use overridden method for userLocal to getTelematicsCodeInfo
     * @param vehicleInfo
     * @return getTelematicsCodeInfo method object of vehicle service class
     */
    @Override
    public OwnerPortalVehicleTelematicsCodeMaster getTelematicsCodeInfo(OwnerPortalVehicle vehicleInfo) {

        return new VehicleService().getTelematicsCodeInfo(vehicleInfo, osEm);
    }
    
    /**
     * @author x796314
     * @use overridden method for userLocal to getUserVehicleInfo
     * @param vin
     *            ,userProfileId
     * @return getUserVehicleInfo method object of vehicle service class
     */
    @Override
    public OwnerPortalUserVehicle getUserVehicleInfo(String userProfileId,
            String vin) {

        return new VehicleService()
                .getUserVehicleInfo(userProfileId, vin, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getDriverInfo
     * @param vin
     * @return getDriverInfo method object of vehicle service class
     */
    @Override
    public OwnerPortalUser getDriverInfo(String vin) {

        return new VehicleService().getDriverInfo(vin, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getVehicleCarwings
     * @param vin
     * @return getVehicleCarwings method object of vehicle service class
     */
    @Override
    public VehicleCarwings getVehicleCarwings(String vin) {

        return new VehicleService().getVehicleCarwings(vin, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getTelematicsInfo
     * @param vehicleInfo
     * @return getTelematicsInfo method object of vehicle service class
     */
    @Override
    public Set<String> getTelematicsInfo(OwnerPortalVehicle vehicleInfo) {

        return new VehicleService().getTelematicsInfo(vehicleInfo, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getDriverVehicleInfo
     * @param vin
     * @return getDriverVehicleInfo method object of vehicle service class
     */
    @Override
    public List<OwnerPortalUserVehicle> getDriverVehicleInfo(String vin) {

        return new VehicleService().getDriverVehicleInfo(vin, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to deleteVehicle
     * @param vin
     *            ,ownerPortalUserVehicle,vehicleinfo
     * @return deleteVehicle method object of vehicle service class
     */
    
    @Override
    public String deleteVehicle(String vin,
            OwnerPortalUserVehicle ownerPortalUserVehicle,
            OwnerPortalVehicle vehicleinfo) {

        return new VehicleService().deleteVehicle(vin, ownerPortalUserVehicle,
                vehicleinfo, osEm);
    }

    /**
     * @author x796314
     * @use overridden method for userLocal to getOwnerVehicleDetails
     * @param userProfileId
     * @return getOwnerVehicleDetails method object of vehicle service class
     */
    @Override
    public List<OwnerPortalUserVehicle> getOwnerVehicleDetails(
            String userProfileId) {

        return new VehicleService().getOwnerVehicleDetails(userProfileId, osEm);
    }

    /**
     * @author x178099
     * @use overridden method for userLocal to fetchVehicleInfoFromVehicleList
     * @param userVehicleInfo
     * @return
     */
    @Override
    public OwnerPortalVehicle fetchVehicleInfoFromVehicleList(String vin) {

        return new VehicleService().fetchVehicleInfoFromVehicleList(vin, osEm);
    }

    
    /**
     * @author x055765
     * @use overridden method for userLocal to fetchNissanVins
     * @param userVehicleInfo
     * @return
     */
    @Override
    public List<OwnerPortalVehicle> fetchOnlyNissanVinsAndVin(String vehicleMakeCode,String vin) {

        return new VehicleService().fetchOnlyNissanVinsAndVin(vehicleMakeCode,vin, osEm);
    }
    

    /**
     * @author rs101547
     * @use overridden method to get subscription product code
     * @param subscriptionProductCode
     * @return
     */
    @Override
    public List<OwnerPortalSubScriptionProduct> getSubScriptionProduct(
            String subscriptionProductCode) {

        return new VehicleService().getSubScriptionProduct(
                subscriptionProductCode, osEm);
    }

    @Override
    public String createToken(OwnerPortalUser ownerPortalUser,
            OwnerPortalVehicle ownerPortalVehicle) {

        return new VehicleService().createToken(ownerPortalUser,
                ownerPortalVehicle, osEm);
    }

    @Override
    public ModelLineMapping modelNameLookup(String modelCode) {

        return new VehicleService().modelNameLookup(modelCode, osEm);
    }

    // x787640 --> Code added to get Recall Type and Service Contract Product
    // Details -- AfterSales -- Start
    @Override
    public String recallTypeDescription(String typeCode) {
        return new VehicleService().recallTypeDescription(typeCode,
                getEntityManager());
    }

    @Override
    public List<ASServiceContractCategoryLookup> getASServiceContractCategoryDetails(
            String asServiceContractCategoryCode) {
        return new VehicleService().getASServiceContractCategoryDetails(
                asServiceContractCategoryCode, getEntityManager());
    }

    // x787640 --> Code added to get Recall Type and Service Contract Product
    // Details -- AfterSales -- End

    @Override
    public List<VehicleSpecification> getVehicleSpecifications(String modelCode) {

        return new VehicleService().getVehicleSpecifications(modelCode, osEm);
    }

    @Override
    public List<Equipment> getEquipmentInfo(String modelCode) {

        return new VehicleService().getEquipmentInfo(modelCode, osEm);
    }
    
    
    /**
     * @author x875352
     * @use overridden method for vehicleLocal to validateVehicle
     * @param validateDataWrapper
     *            ,ownerPortalUser
     * @return validateVehicle method object of vehicle service class
     */

    @Override
    public String validVehicle(String vin,String userProfileId) {

        return new VehicleService().validVehicle(vin,userProfileId, osEm);
    }

    @Override
    public OwnerPortalUserVehicleFailedReference validateVin(String vin) {

        return new VehicleService().validateVin(vin,osEm);
    }
	
    /**
	 * @author x566325
	 * @use To retrieve the LargeImageURL from the getVehicle DB Function
	 * @param Model Code, Exterior Color Code
	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
	 * @return LargeImageURL String
	 */
    @Override
    public String getVehicleFunctionLargeImage1(String modelCode,String exteriorColorCode,String factoryOptionCode) {
        return new VehicleService().getVehicleFunctionLargeImage1(modelCode, exteriorColorCode, factoryOptionCode, osEm);
        
    }
    
    
    /**
	 * @author x566325
	 * @use To retrieve the SmallImageURL from the getVehicle DB Function
	 * @param Model Code, Exterior Color Code
	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
	 * @return SmallImageURL String
	 */
    @Override
    public String getVehicleFunctionSmallImage1(String modelCode,String exteriorColorCode,String factoryOptionCode) {
        return new VehicleService().getVehicleFunctionSmallImage1(modelCode, exteriorColorCode, factoryOptionCode, osEm);
    }
    
    /**
   	 * @author x566325
   	 * @use To retrieve the LargeImageURL from the getVehicle DB Function
   	 * @param Model Code, Exterior Color Code
   	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
   	 * @return LargeImageURL String
   	 */
       @Override
       public String getVehicleFunctionLargeImage(OwnerPortalVehicle vehicleInfo) {
           return new VehicleService().getVehicleFunctionLargeImage(vehicleInfo, osEm);
           
       }
       
       
       /**
   	 * @author x566325
   	 * @use To retrieve the SmallImageURL from the getVehicle DB Function
   	 * @param Model Code, Exterior Color Code
   	 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
   	 * @return SmallImageURL String
   	 */
       @Override
       public String getVehicleFunctionSmallImage(OwnerPortalVehicle vehicleInfo) {
           return new VehicleService().getVehicleFunctionSmallImage( vehicleInfo,osEm);
       }

	@Override
	public List<TermsAndConditionsAgreementSt> getTermsAndConditionsAgreementSt(
			String vin,String userId) {
		// TODO Auto-generated method stub
		return new VehicleService().getTermsAndConditionsAgreementSt(vin,userId,osEm);
	}

	@Override
	public TelematicstermsAndConditions getTelematicstermsAndConditions(
			String vehicleTelematicsCode) {
		
		return new VehicleService().getTelematicstermsAndConditions(vehicleTelematicsCode,osEm);
	}

	@Override
	public List<OwnerPortalUserVehicle> getOwnerPortalUserVehicles(String vin) {
		// TODO Auto-generated method stub
		return new VehicleService().getOwnerPortalUserVehicles(vin,osEm);
	}

	@Override
	public OwnerPortalUser getOwnerPortalUser(String userProfileId) {
		
		return new VehicleService().getOwnerPortalUser(userProfileId, osEm);
	}

	@Override
	public void insertTermsAndConditionsAgreementSt(
			TermsAndConditionsAgreementSt agreementSt) {
		
		new VehicleService().insertTermsAndConditionsAgreementSt(agreementSt,osEm);
	}

	@Override
	public List<TermsAndConditionsAgreementSt> getTermsAndConditionsAgreementStUsingSorcecode(
			String vin, String userId,String agreement) {
		
		
		return new VehicleService().getTermsAndConditionsAgreementStUsingSorcecode(vin, userId,agreement, osEm);
	}

	@Override
	public void updateTermsAndConditionsAgreementSt(
			TermsAndConditionsAgreementSt agreementSt) {
		new VehicleService().updateTermsAndConditionsAgreementSt(agreementSt, osEm);
		
	}

	 @Override
	    public ManualVehicleLookup getVehicleInfoForTestVin(String vin) {

	        return new VehicleService()
	                .getVehicleInfoForTestVin(vin, osEm);
	    }
	 
	 @Override
	    public OwnerPortalVehicle getModelNameUsingVin(String vin) {

	        return new VehicleService()
	                .getModelNameUsingVin(vin, osEm);
	    }
	 
	 /**
		 * @author X522443
		 * @use To retrieve the ImageURL from the getVehicle DB Function
		 * @param Model Code, Exterior Color Code
		 * @param Package Accessory Code List (Option Code), Image Type (in the same order)
		 * @return ImageURL String
		 */
	 @Override
	    public String getVehicleFunctionImage(String modelCode,String exteriorColorCode,String factoryOptionCode, String typeSize) {
	        return new VehicleService().getVehicleFunctionImage(modelCode, exteriorColorCode, factoryOptionCode, osEm, typeSize); 
	        
	    }
	    
	 
	   
	    @Override
	    public VehdsplMaritzDlyLndgPrcngEntity checkVin(String vin) {

	        return new VehicleService().checkVin(vin, osEm);
	    }
	    
	    @Override
	    public void updateVehdsplMaritzDlyLndgPrcngEntity(VehdsplMaritzDlyLndgPrcngEntity vehdsplMaritzDlyLndgPrcngEntity){
	    	new VehicleService().updateVehdsplMaritzDlyLndgPrcngEntity(vehdsplMaritzDlyLndgPrcngEntity, osEm);
	    }
	@Override
	public List<OwnerPortalUserVehicleFailedReference> getOVCaseVehiclesByStatus(String userProfileId) {
		// TODO Auto-generated method stub
		return new VehicleService().getOVCaseVehiclesByStatus(userProfileId, osEm);
	}
	
	@Override
	public Map<String,String> getCurrentOVStatusFromSFDC(String caseId) {
		return new VehicleService().getCurrentOVStatusFromSFDC(caseId);
	}
	
	@Override
	public List<String> getOwnerVehicles(String userProfileId) {
		// TODO Auto-generated method stub
		return new VehicleService().getOwnerVehicles(userProfileId, osEm);
	}
}
