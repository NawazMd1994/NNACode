package com.nissanusa.helios.ownerservice.facade;

/*
 *last modified date 06-06-2016 by x796314

 */
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

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
import com.nissanusa.helios.ownerservice.vo.FailedVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.SaveVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UpdateVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.UploadDocumentWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateDataWrapper;

/**
 * 
 * @author x178099
 * @use Vehicle Local contains the vehicle and user vehicle related services
 *
 */
@Local
public interface VehicleLocal {

	ManualVehicleLookup vinLookup(String vin);

	String saveVehicle(SaveVehicleWrapper saveVehicleWrapper, OwnerPortalUser ownerPortalUser);

	String saveFailedVehicle(FailedVehicleWrapper failedVehicleWrapper, OwnerPortalUser validUser,
			OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference, String brand);

	String loadDocument(UploadDocumentWrapper uploadDocumentWrapper, String brand);

	String validateUserVehicle(ValidateDataWrapper validateDataWrapper, OwnerPortalUser validUser, String brand);

	String updateVehicle(UpdateVehicleWrapper updateVehicleWrapper, OwnerPortalUser ownerPortalUser);

	String validateVehicleOwner(String userProfileId, String vin);

	OwnerPortalVehicle getVehicleInfo(String vin);

	OwnerPortalVehicleTelematicsCodeMaster getTelematicsCodeInfo(OwnerPortalVehicle vehicleInfo);

	OwnerPortalUserVehicle getUserVehicleInfo(String userProfileId, String vin);

	OwnerPortalUser getDriverInfo(String vin);

	List<OwnerPortalUserVehicle> getDriverVehicleInfo(String vin);

	VehicleCarwings getVehicleCarwings(String vin);

	Set<String> getTelematicsInfo(OwnerPortalVehicle vehicleInfo);

	String deleteVehicle(String vin, OwnerPortalUserVehicle ownerPortalUserVehicle, OwnerPortalVehicle vehicleInfo);

	List<OwnerPortalUserVehicle> getOwnerVehicleDetails(String userProfileId);

	OwnerPortalVehicle fetchVehicleInfoFromVehicleList(String vin);

	List<OwnerPortalVehicle> fetchOnlyNissanVinsAndVin(String vehicleMakeCode, String vin);

	List<OwnerPortalSubScriptionProduct> getSubScriptionProduct(String subscriptionProductCode);

	String createToken(OwnerPortalUser ownerPortalUser, OwnerPortalVehicle ownerPortalVehicle);

	ModelLineMapping modelNameLookup(String modelCode);

	// x787640 --> Code added to get Recall Type and Service Contract Product
	// Details -- AfterSales -- Start
	String recallTypeDescription(String typeCode);

	List<ASServiceContractCategoryLookup> getASServiceContractCategoryDetails(String asServiceContractCategoryCode);

	// x787640 --> Code added to get Recall Type and Service Contract Product
	// Details -- AfterSales -- End

	List<VehicleSpecification> getVehicleSpecifications(String modelCode);

	List<Equipment> getEquipmentInfo(String modelCode);

	String validVehicle(String vin, String userProfileId);

	OwnerPortalUserVehicleFailedReference validateVin(String vin);

	// x566325 - Update getVehicle,saveVehicle, updateVehicle API to include
	// Image URL's in the response
	String getVehicleFunctionLargeImage1(String modelCode, String exteriorColorCode, String factoryOptionCode);

	String getVehicleFunctionSmallImage1(String modelCode, String exteriorColorCode, String factoryOptionCode);

	String getVehicleFunctionLargeImage(OwnerPortalVehicle vehicleInfo);

	String getVehicleFunctionSmallImage(OwnerPortalVehicle vehicleInfo);

	List<TermsAndConditionsAgreementSt> getTermsAndConditionsAgreementSt(String vin, String userId);

	TelematicstermsAndConditions getTelematicstermsAndConditions(String vehicleTelematicsCode);

	List<OwnerPortalUserVehicle> getOwnerPortalUserVehicles(String vin);

	OwnerPortalUser getOwnerPortalUser(String userProfileId);

	void insertTermsAndConditionsAgreementSt(TermsAndConditionsAgreementSt agreementSt);

	void updateTermsAndConditionsAgreementSt(TermsAndConditionsAgreementSt agreementSt);

	List<TermsAndConditionsAgreementSt> getTermsAndConditionsAgreementStUsingSorcecode(String vin, String userId,
			String agreement);

	ManualVehicleLookup getVehicleInfoForTestVin(String vin);

	OwnerPortalVehicle getModelNameUsingVin(String vin);

	String getVehicleFunctionImage(String modelCode, String exteriorColorCode, String factoryOptionCode,
			String typeSize);

	VehdsplMaritzDlyLndgPrcngEntity checkVin(String vin);

	public void updateVehdsplMaritzDlyLndgPrcngEntity(VehdsplMaritzDlyLndgPrcngEntity vehdsplMaritzDlyLndgPrcngEntity);

   List<OwnerPortalUserVehicleFailedReference> getOVCaseVehiclesByStatus(String userProfileId);
   Map<String,String> getCurrentOVStatusFromSFDC(String caseId);
   List<String> getOwnerVehicles(String userProfileId);
}
