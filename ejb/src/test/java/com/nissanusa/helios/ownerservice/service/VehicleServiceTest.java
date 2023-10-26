package com.nissanusa.helios.ownerservice.service;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.BasicConfigurator;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nissanusa.helios.ownerservice.entity.Equipment;
import com.nissanusa.helios.ownerservice.entity.EventNotification;
import com.nissanusa.helios.ownerservice.entity.ManualVehicleLookup;
import com.nissanusa.helios.ownerservice.entity.ModelLineMapping;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalSubScriptionProduct;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehicleFailedReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserVehiclePK;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicle;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicleTelematicsCodeMaster;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicleTelematicsCodeMasterPK;
import com.nissanusa.helios.ownerservice.entity.VehicleCarwings;
import com.nissanusa.helios.ownerservice.entity.VehicleSpecification;
import com.nissanusa.helios.ownerservice.service.VehicleService;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.OwnerQueryConstants;
import com.nissanusa.helios.ownerservice.vo.AccountVO;
import com.nissanusa.helios.ownerservice.vo.AddressVO;
import com.nissanusa.helios.ownerservice.vo.PersonVehicleVO;
import com.nissanusa.helios.ownerservice.vo.SaveVehicleWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateDataVO;
import com.nissanusa.helios.ownerservice.vo.ValidateDataWrapper;
import com.nissanusa.helios.ownerservice.vo.VehicleVO;



@RunWith(PowerMockRunner.class)
@PrepareForTest(VehicleService.class)
public class VehicleServiceTest implements OwnerConstants,OwnerQueryConstants{
	
	/*VehicleService vehicleService;
	
	private static final Logger LOG = Logger.getLogger(UserServiceTest.class);
	
	@Mock
	EntityManager osEm;
	
	@Mock
	Query query;
	
	@Mock
	TypedQuery<ManualVehicleLookup> vinLookUpQuery;
	
	@Mock
	TypedQuery<OwnerPortalVehicle> ownerPortalVehicleQuery;
	
	@Mock
	TypedQuery<OwnerPortalUserVehicle> ownerPortalUserVehicleQuery;
	
	@Mock
	TypedQuery<OwnerPortalUser> ownerPortalUserQuery;
	
	@Mock
	TypedQuery<OwnerPortalUserVehicleFailedReference> ownerPortalUserVehicleFailedReferenceQuery;
	
	@Mock
	TypedQuery<Equipment> equipmentQuery;
	
	@Mock
	TypedQuery<EventNotification> eventNotificationQuery;
	
	@Mock
	TypedQuery<VehicleCarwings> vehicleCarwingsQuery;
	
	@Mock
	TypedQuery<VehicleSpecification> vehicleSpecificationQuery;
	
	@Mock
	TypedQuery<ModelLineMapping> modelLineMappingQuery;
	
	@Mock
	TypedQuery<OwnerPortalSubScriptionProduct> ownerPortalSubScriptionProductQuery;
	
	@Mock
	TypedQuery<OwnerPortalVehicleTelematicsCodeMaster> ownerPortalVehicleTelematicsCodeMasterQuery;
	
	@Mock
	TypedQuery<OwnerPortalVehicleTelematicsCodeMasterPK> ownerPortalVehicleTelematicsCodeMasterPKQuery;
	
	@Before
	public void setUp() throws Exception {
		vehicleService = PowerMockito.spy(new VehicleService());
		BasicConfigurator.configure();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	  
	//savevehicle
//@Test
//@Ignore
public void saveVehicleTest() throws Exception{
			LOG.info("inside saveVehicleTest success case");
				
			String vin = "1N3CH51DCTC10360T";
			String userProfileId = "1542";
				
			SaveVehicleWrapper saveVehicleWrapper = new SaveVehicleWrapper();
			PersonVehicleVO saveVehicle = new PersonVehicleVO();
			VehicleVO vehicle = new VehicleVO();
			vehicle.setVin(vin);
			saveVehicle.setVehicle(vehicle);
			saveVehicleWrapper.setSaveVehicle(saveVehicle);
				
			OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
			ownerPortalUserVehicle.setMileage(25);
			ownerPortalUserVehicle.setVehicleNickName("Altima");
	        List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
			ownerPortalUserVehicleList.add(ownerPortalUserVehicle);
				
			when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByVin",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.setParameter("vin", saveVehicleWrapper.getSaveVehicle().getVehicle().getVin())).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
				
			OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
			ownerPortalUser.setUserProfileId(userProfileId);
				
			when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByUserProfileIdAndVin",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.setParameter("userProfileId", ownerPortalUser.getUserProfileId())).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.setParameter("vin", saveVehicleWrapper.getSaveVehicle().getVehicle().getVin())).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
				
			OwnerPortalVehicle ownerPortalVehicle = new OwnerPortalVehicle();
			ownerPortalVehicle.setModelYearNumber("2006");
			ownerPortalVehicle.setVehicleMakeCode("N");
			List<OwnerPortalVehicle> ownerPortalVehicleList = new ArrayList<OwnerPortalVehicle>();
			ownerPortalVehicleList.add(ownerPortalVehicle);
			when(osEm.createNamedQuery("OwnerPortalVehicle.findByVin",OwnerPortalVehicle.class)).thenReturn(ownerPortalVehicleQuery);
			when(ownerPortalVehicleQuery.setParameter("vin", saveVehicleWrapper.getSaveVehicle().getVehicle().getVin())).thenReturn(ownerPortalVehicleQuery);
			when(ownerPortalVehicleQuery.getResultList()).thenReturn(ownerPortalVehicleList);
				
				
			String saveVehicleTestReponse =  vehicleService.saveVehicle(saveVehicleWrapper, ownerPortalUser, osEm);
		        		
		    LOG.info("SaveVehicleTestReponse :" + saveVehicleTestReponse);
		       
		    Assert.assertEquals("success",saveVehicleTestReponse);
		 
} 
	
	
	
	//save vehicle info
	
	//save failed vehicle
	

//pending
//@Test
//@Ignore
public void saveFailedVehicleInfo1Test() throws Exception{
			LOG.info("inside saveFailedVehicleInfo1Test success case");
			
			OwnerPortalUser validUser = new OwnerPortalUser();
			validUser.setUserProfileId("1564");
			
			OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference = new OwnerPortalUserVehicleFailedReference();
			
			String brand = "NISSAN";
			
			String vin = "1N3CH51DCTC10360T";
			ValidateDataWrapper validateDataWrapper = new ValidateDataWrapper();
			ValidateDataVO validateData = new ValidateDataVO();
			validateData.setVin(vin);
			validateDataWrapper.setValidateData(validateData);
			
			OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
			ownerPortalUserVehicle.setMileage(25);
			ownerPortalUserVehicle.setVehicleNickName("Altima");

			List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
			ownerPortalUserVehicleList.add(ownerPortalUserVehicle);
			
			when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByVin",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.setParameter("vin", validateDataWrapper.getValidateData().getVin())).thenReturn(ownerPortalUserVehicleQuery);
			when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
			
			doNothing().when(osEm).persist(Matchers.any(OwnerPortalUserVehicleFailedReference.class));

			long saveFailedVehicleInfo1Reponse =  vehicleService.saveFailedVehicleInfo1(validateDataWrapper, validUser, ownerPortalUserVehicleFailedReference, brand, osEm);
	        		
	        LOG.info("SaveFailedVehicleInfo1Reponse :" + saveFailedVehicleInfo1Reponse);
	       
	        Assert.assertEquals("success",saveFailedVehicleInfo1Reponse);
	 
} 
	

//@Test
//@Ignore
public void saveUserVehicleInfoTest() throws Exception{
		LOG.info("inside saveUserVehicleInfoTest success case");
		
		// Setup test input
		OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
		ownerPortalUser.setUserProfileId("1564");
		
		String nickName = "Test";
		
		VehicleVO vehicle = new VehicleVO();
		vehicle.setVin("1N3CH51DCTC10360T");
		vehicle.setMileage(500);
		vehicle.setAverageMileage(1000);
		vehicle.setDealerId("7001");
		
		PersonVehicleVO saveVehicle = new PersonVehicleVO();
		saveVehicle.setVehicle(vehicle);
		
		SaveVehicleWrapper saveVehicleWrapper = new SaveVehicleWrapper();
		saveVehicleWrapper.setSaveVehicle(saveVehicle);
		
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setVehicleNickName("My Altima");
		ownerPortalUserVehicle.setMileage(saveVehicleWrapper.getSaveVehicle().getVehicle().getMileage());
		ownerPortalUserVehicle.setAverageMileage(saveVehicleWrapper.getSaveVehicle().getVehicle().getAverageMileage());
		ownerPortalUserVehicle.setDealerId(saveVehicleWrapper.getSaveVehicle().getVehicle().getDealerId());
		ownerPortalUserVehicle.setVehicleRelationCode(RELATION_CODE);
		ownerPortalUserVehicle.setCreateUserId(USERID);
		ownerPortalUserVehicle.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
		ownerPortalUserVehicle.setUpdateUserId(USERID);
		ownerPortalUserVehicle.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
		ownerPortalUserVehicle.setNotificationOptIn(NOTIFICATION_OPT);
		
		OwnerPortalUserVehiclePK ownerPortalUserVehiclePK = new OwnerPortalUserVehiclePK();
		ownerPortalUserVehiclePK.setVin(saveVehicleWrapper.getSaveVehicle().getVehicle().getVin());
		
		ownerPortalUserVehicle.setOwnerPortalUserVehiclePK(ownerPortalUserVehiclePK);
		
	    doNothing().when(osEm).persist(Matchers.any(OwnerPortalUserVehicle.class));

        String saveUserVehicleInfoReponse =  vehicleService.saveUserVehicleInfo(saveVehicleWrapper, ownerPortalUser, nickName, osEm);
        		
        LOG.info("SaveUserVehicleInfoReponse :" + saveUserVehicleInfoReponse.toString());
       
        Assert.assertEquals("success",saveUserVehicleInfoReponse);

}
	
	
//@Test
//@Ignore
public void vinLookupTest() throws Exception {
		LOG.info("inside vinLookupTest test success case");
		
		// Setup test input
		String vin = "1N3CH51DYTC10284T";
	 
		ManualVehicleLookup manualVehicleLookup = new ManualVehicleLookup();
		manualVehicleLookup.setVehicleModelYear("2016");
		manualVehicleLookup.setVehicleMakeCode("N");

		List<ManualVehicleLookup> manualVehicleLookupownerPortalUserList = new ArrayList<ManualVehicleLookup>();
		manualVehicleLookupownerPortalUserList.add(manualVehicleLookup);

		when(osEm.createNamedQuery("ManualVehicleLookup.findByVin",ManualVehicleLookup.class)).thenReturn(vinLookUpQuery);
		when(vinLookUpQuery.setParameter("vin", vin)).thenReturn(vinLookUpQuery);
		when(vinLookUpQuery.getResultList()).thenReturn(manualVehicleLookupownerPortalUserList);
		
		ManualVehicleLookup response =  vehicleService.vinLookup(vin, osEm);

		LOG.info("response" + response.getVehicleMakeCode());

		Assert.assertEquals("2016",response.getVehicleModelYear());
			
		Assert.assertEquals("N",response.getVehicleMakeCode());

}
	
	
//@Test
//@Ignore
public void getVehicleInfoTest() throws Exception {
		LOG.info("inside getVehicleInfoTest test success case");

		// Setup test input
		String vin = "1N3CH51DYTC10284T";
		 
		OwnerPortalVehicle ownerPortalVehicle = new OwnerPortalVehicle();
		ownerPortalVehicle.setVehicleModelCode("ALT");
		ownerPortalVehicle.setVehicleMakeCode("N");

		List<OwnerPortalVehicle> ownerPortalVehicleList = new ArrayList<OwnerPortalVehicle>();
		ownerPortalVehicleList.add(ownerPortalVehicle);

		when(osEm.createNamedQuery("OwnerPortalVehicle.findByVin",OwnerPortalVehicle.class)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.getResultList()).thenReturn(ownerPortalVehicleList);
				
		OwnerPortalVehicle response =  vehicleService.getVehicleInfo(vin, osEm);

		LOG.info("response" + response.getVehicleMakeCode());

		Assert.assertEquals("ALT",response.getVehicleModelCode());
				
		Assert.assertEquals("N",response.getVehicleMakeCode());

}
	
//validate user vehicle


//@Test
//@Ignore
public void validateVehicleOwnerTest() throws Exception {
		LOG.info("inside validateVehicleOwnerTest success case");

		// Setup test input
		String vin = "1N5CH31DYTC100678";
		String userProfileId = "1137";
		
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setMileage(20);
		ownerPortalUserVehicle.setVehicleNickName("My First Car");
	 
	    List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
        ownerPortalUserVehicleList.add(ownerPortalUserVehicle);

		when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByVin",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleQuery);
	    when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
	    
	    when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByUserProfileIdAndVin",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
	    when(ownerPortalUserVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleQuery);
	    when(ownerPortalUserVehicleQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserVehicleQuery);
	    when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
        
	    String validateVehicleOwnerResponse =  vehicleService.validateVehicleOwner(userProfileId, vin, osEm);

		LOG.info("ValidateVehicleOwnerResponse" + validateVehicleOwnerResponse);

		Assert.assertEquals("sameUser",validateVehicleOwnerResponse);

}


//@Test
//@Ignore
public void getUserVehicleInfoTest() throws Exception {
		LOG.info("inside getUserVehicleInfoTest success case");

		// Setup test input
		String vin = "1N5CH31DYTC100678";
		String userProfileId = "1137";
		
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setMileage(20);
		ownerPortalUserVehicle.setVehicleNickName("My Nissan");
	 
	    List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
        ownerPortalUserVehicleList.add(ownerPortalUserVehicle);

		when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByUserProfileIdAndVin",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleQuery);
	    when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
        
	    OwnerPortalUserVehicle getUserVehicleInfoResponse =  vehicleService.getUserVehicleInfo(userProfileId, vin, osEm);

		LOG.info("GetUserVehicleInfoResponse" + getUserVehicleInfoResponse.getMileage());

		Assert.assertEquals("My Nissan",getUserVehicleInfoResponse.getVehicleNickName());

}
	

//update vehicle

//update vehicle info


//update user vehicle info


//@Test
//@Ignore
public void getDriverInfoTest() throws Exception {
		LOG.info("inside getDriverInfoTest success case");

		// Setup test input
		String vin = "1N5CH31DYTC100678";
		char vehicleRelationCode = DRIVER;
		
		OwnerPortalUserVehiclePK ownerPortalUserVehiclePK = new OwnerPortalUserVehiclePK();
	    ownerPortalUserVehiclePK.setUserProfileId("1127");
	        
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setAverageMileage(30);
		ownerPortalUserVehicle.setVehicleNickName("Testvin");
		ownerPortalUserVehicle.setOwnerPortalUserVehiclePK(ownerPortalUserVehiclePK);
		
	    List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
        ownerPortalUserVehicleList.add(ownerPortalUserVehicle);

		when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByVinAndvehicleRelationCode",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vehicleRelationCode", vehicleRelationCode)).thenReturn(ownerPortalUserVehicleQuery);
        when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
        
        OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
        ownerPortalUser.setEmailId("david.ben@test2.com");
        ownerPortalUser.setCountryCode("US");
		
	    List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
	    ownerPortalUserList.add(ownerPortalUser);
        
        when(osEm.createNamedQuery("OwnerPortalUser.findByuserProfileId",OwnerPortalUser.class)).thenReturn(ownerPortalUserQuery);
		when(ownerPortalUserQuery.setParameter("userProfileId", ownerPortalUserVehicle.getOwnerPortalUserVehiclePK().getUserProfileId())).thenReturn(ownerPortalUserQuery);
	    when(ownerPortalUserQuery.getResultList()).thenReturn(ownerPortalUserList);
		
        OwnerPortalUser getDriverInfoResponse =  vehicleService.getDriverInfo(vin, osEm);

		LOG.info("GetDriverInfoResponse" + getDriverInfoResponse.getCountryCode());

		Assert.assertEquals("david.ben@test2.com",getDriverInfoResponse.getEmailId());
	
}


//@Test
//@Ignore
public void getDriverVehicleInfoTest() throws Exception {
		LOG.info("inside getDriverVehicleInfoTest success case");

		// Setup test input
		String vin = "1N5CH31DYTC100678";
		char vehicleRelationCode = DRIVER;
				
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setAverageMileage(30);
		ownerPortalUserVehicle.setVehicleNickName("Testvin");
				
		List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
		ownerPortalUserVehicleList.add(ownerPortalUserVehicle);

		when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByVinAndvehicleRelationCode",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vehicleRelationCode", vehicleRelationCode)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
				
		List<OwnerPortalUserVehicle> getDriverVehicleInfoResponse =  vehicleService.getDriverVehicleInfo(vin, osEm);

		LOG.info("GetDriverVehicleInfoResponse" + getDriverVehicleInfoResponse.get(0).getAverageMileage());

		Assert.assertEquals("Testvin",getDriverVehicleInfoResponse.get(0).getVehicleNickName());

}
	
//@Test
//@Ignore
public void getVehicleCarwingsTest() throws Exception {
		LOG.info("inside getVehicleCarwingsTest success case");

		// Setup test input
		String vin = "JNKPV2248VT528993";
			
		VehicleCarwings vehicleCarwings = new VehicleCarwings();
		vehicleCarwings.setOwnerCarwingsUserId("1253");
		vehicleCarwings.setChargingStatusCode("NORMAL_CHARGING");
			
	    List<VehicleCarwings> vehicleCarwingsList = new ArrayList<VehicleCarwings>();
	    vehicleCarwingsList.add(vehicleCarwings);

		when(osEm.createNamedQuery("VehicleCarwings.findByVin",VehicleCarwings.class)).thenReturn(vehicleCarwingsQuery);
		when(vehicleCarwingsQuery.setParameter("vin", vin)).thenReturn(vehicleCarwingsQuery);
	    when(vehicleCarwingsQuery.getResultList()).thenReturn(vehicleCarwingsList);
			
	    VehicleCarwings vehicleCarwingsResponse =  vehicleService.getVehicleCarwings(vin, osEm);

	    LOG.info("VehicleCarwingsResponse" + vehicleCarwingsResponse.getOwnerCarwingsUserId());

		Assert.assertEquals("NORMAL_CHARGING",vehicleCarwingsResponse.getChargingStatusCode());
	
}


//@Test
//@Ignore
public void getEventNotificationTest() throws Exception {
	   LOG.info("inside getEventNotificationTest success case");

		// Setup test input
		String vin = "1N3CH31DYTC101434";
		
		EventNotification eventNotification = new EventNotification();
		eventNotification.setUserProfileId("1121");
		eventNotification.setEventNotificationCode("PLUG_IN_REMINDER");
		
        List<EventNotification> eventNotificationList = new ArrayList<EventNotification>();
        eventNotificationList.add(eventNotification);

		when(osEm.createQuery("select o from EventNotification o where o.vin=:vin",EventNotification.class)).thenReturn(eventNotificationQuery);
		when(eventNotificationQuery.setParameter("vin", vin)).thenReturn(eventNotificationQuery);
        when(eventNotificationQuery.getResultList()).thenReturn(eventNotificationList);
		
        List<EventNotification> eventNotificationResponse =  vehicleService.getEventNotification(vin, osEm);

		LOG.info("EventNotificationResponse" + eventNotificationResponse.size());

		Assert.assertEquals("1121",eventNotificationResponse.get(0).getUserProfileId());
	
}
	

//@Test
//@Ignore
public void updateOwnerAccountStatusTest() throws Exception{
		LOG.info("inside updateOwnerAccountStatusTest success case");
																		
		//Input data
		String accountStatusCode = "ACL";
		String userProfileId = "1564";
	
		OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
		ownerPortalUser.setEmailId("durga@test.com");
		ownerPortalUser.setCustomerDataIntegrationId("143762991");
													
		when(osEm.createQuery("UPDATE OwnerPortalUser AS o SET o.accountStatusCode=:accountStatusCode WHERE o.userProfileId=:userProfileId")).thenReturn(ownerPortalUserQuery);
		when(ownerPortalUserQuery.setParameter("accountStatusCode", accountStatusCode)).thenReturn(ownerPortalUserQuery);
		when(ownerPortalUserQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserQuery);
	
		when(ownerPortalUserQuery.executeUpdate()).thenReturn(1);
																	
		String response = vehicleService.updateOwnerAccountStatus(userProfileId, osEm);
																	
		Assert.assertEquals("success",response);
																		
}


//@Test
//@Ignore
public void deleteDriverTest() throws Exception {
		LOG.info("inside deleteDriverTest success case");

		//Input data
		String vin="JN8AZ08T27W260294";
		String userProfileId = "1564";
	
		OwnerPortalUserVehicle ownerPortalUserVehicleObj = new OwnerPortalUserVehicle();
		ownerPortalUserVehicleObj.setMileage(25);
		ownerPortalUserVehicleObj.setVehicleNickName("My Car");
	
		List<OwnerPortalUserVehicle> ownerPortalUserVehicle = new ArrayList<OwnerPortalUserVehicle>();
		ownerPortalUserVehicle.add(ownerPortalUserVehicleObj);
	
		when(osEm.createQuery("delete from OwnerPortalUserVehicle o where o.vin=:vin and o.OwnerPortalUserVehiclePK.userProfileId = :userProfileId",OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicle);
		
		String response =  vehicleService.deleteDriver(vin, userProfileId, osEm);

		//LOG.info("GetTelematicsInfoResponse" + getTelematicsInfoResponse.size());

		Assert.assertEquals("success", response);
															
}


//@Test
//@Ignore
public void getTelematicsInfoTest() throws Exception {
		LOG.info("inside getTelematicsInfoTest success case");

		//Input data
		OwnerPortalVehicle vehicleInfo = new OwnerPortalVehicle();
		vehicleInfo.setVehicleModelCode("38217");
		vehicleInfo.setModelYearNumber("2016");
   
		OwnerPortalVehicleTelematicsCodeMasterPK ownerPortalVehicleTelematicsCodeMasterPK = new OwnerPortalVehicleTelematicsCodeMasterPK();
		ownerPortalVehicleTelematicsCodeMasterPK.setVehicleTelematicsOptionTypeCode("S2");
	
		List<OwnerPortalVehicleTelematicsCodeMasterPK> ownerPortalVehicleTelematicsCodeMasterPKList = new ArrayList<OwnerPortalVehicleTelematicsCodeMasterPK>();
		ownerPortalVehicleTelematicsCodeMasterPKList.add(ownerPortalVehicleTelematicsCodeMasterPK);
	
		when(osEm.createNamedQuery("OwnerPortalVehicleTelematicsCodeMaster.findByVehicleModelCodeAndModelYear", OwnerPortalVehicleTelematicsCodeMaster.class)).thenReturn(ownerPortalVehicleTelematicsCodeMasterQuery);
		when(ownerPortalVehicleTelematicsCodeMasterQuery.setParameter("vehicleModelCode", vehicleInfo.getVehicleModelCode())).thenReturn(ownerPortalVehicleTelematicsCodeMasterQuery);
		when(ownerPortalVehicleTelematicsCodeMasterQuery.setParameter("modelYear", vehicleInfo.getModelYearNumber())).thenReturn(ownerPortalVehicleTelematicsCodeMasterQuery);
		when(ownerPortalVehicleTelematicsCodeMasterPKQuery.getResultList()).thenReturn(ownerPortalVehicleTelematicsCodeMasterPKList);
																
		Set<String> getTelematicsInfoResponse =  vehicleService.getTelematicsInfo(vehicleInfo, osEm);

		LOG.info("GetTelematicsInfoResponse" + getTelematicsInfoResponse.size());

		Assert.assertEquals(0,getTelematicsInfoResponse.size());
															
}	

//@Test
//@Ignore
public void checkUserVehicleTest() throws Exception {
		LOG.info("inside checkUserVehicleTest success case");

		// Setup test input
		String userProfileId = "1564";
		 
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setMileage(25);
		ownerPortalUserVehicle.setVehicleNickName("My Car");
		 
		List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
		ownerPortalUserVehicleList.add(ownerPortalUserVehicle);
      
        when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByUserProfileId", OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
		
		List<OwnerPortalUserVehicle> ownerPortalUserVehicleResponse =  vehicleService.checkUserVehicle(userProfileId, osEm);

		LOG.info("CheckUserVehicleResponse" + ownerPortalUserVehicleResponse.size());

		Assert.assertEquals("My Car",ownerPortalUserVehicleResponse.get(0).getVehicleNickName());
	
}


//DELETE VEHICLE


//@Test
//@Ignore
public void getOwnerVehicleDetailsTest() throws Exception {
		LOG.info("inside getOwnerVehicleDetailsTest success case");

		// Setup test input
		String userProfileId = "1564";
		 
		OwnerPortalUserVehicle ownerPortalUserVehicle = new OwnerPortalUserVehicle();
		ownerPortalUserVehicle.setMileage(25);
		ownerPortalUserVehicle.setVehicleNickName("My Car");
		 
		List<OwnerPortalUserVehicle> ownerPortalUserVehicleList = new ArrayList<OwnerPortalUserVehicle>();
		ownerPortalUserVehicleList.add(ownerPortalUserVehicle);
      
        when(osEm.createNamedQuery("OwnerPortalUserVehicle.findByUserProfileId", OwnerPortalUserVehicle.class)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserVehicleQuery);
		when(ownerPortalUserVehicleQuery.getResultList()).thenReturn(ownerPortalUserVehicleList);
		
		List<OwnerPortalUserVehicle> ownerPortalUserVehicleResponse =  vehicleService.getOwnerVehicleDetails(userProfileId, osEm);

		LOG.info("GetOwnerDetailsResponse" + ownerPortalUserVehicleResponse.size());

		Assert.assertEquals("My Car",ownerPortalUserVehicleResponse.get(0).getVehicleNickName());
	
}


//@Test
//@Ignore
public void fetchVehicleInfoFromVehicleListTest() throws Exception {
		LOG.info("inside fetchVehicleInfoFromVehicleListTest success case");

		// Setup test input
		String vin = "1N3CH31DYTC101434";
		 
	    OwnerPortalVehicle ownerPortalVehicle = new OwnerPortalVehicle();
		ownerPortalVehicle.setModelYearNumber("2016");
//		ownerPortalVehicle.setModelLineCode("ALT");
		 
        when(osEm.createNamedQuery("OwnerPortalVehicle.findByVin", OwnerPortalVehicle.class)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.getSingleResult()).thenReturn(ownerPortalVehicle);
		
		OwnerPortalVehicle ownerPortalVehicleResponse =  vehicleService.fetchVehicleInfoFromVehicleList(vin, osEm);

//		LOG.info("FetchVehicleInfoFromVehicleListTestResponse" + ownerPortalVehicleResponse.getModelLineCode());

//		Assert.assertEquals("ALT",ownerPortalVehicleResponse.getModelLineCode());
	
}

//@Test
//@Ignore
public void fetchOnlyNissanVinsAndVinTest() throws Exception {
		LOG.info("inside fetchOnlyNissanVinsAndVinTest success case");

		// Setup test input
		String vin = "1N3CH31DYTC101434";
		String vehicleMakeCode = "N";
	    
		OwnerPortalVehicle ownerPortalVehicle = new OwnerPortalVehicle();
		ownerPortalVehicle.setModelYearNumber("2016");
//		ownerPortalVehicle.setModelLineCode("ALT");
		
        List<OwnerPortalVehicle> ownerPortalVehicleList = new ArrayList<OwnerPortalVehicle>();
        ownerPortalVehicleList.add(ownerPortalVehicle);

		when(osEm.createNamedQuery("OwnerPortalVehicle.findByVehicleMakeCodeAndVin", OwnerPortalVehicle.class)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.setParameter("vehicleMakeCode", vehicleMakeCode)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.getResultList()).thenReturn(ownerPortalVehicleList);
		
		List<OwnerPortalVehicle> ownerPortalVehicleResponse =  vehicleService.fetchOnlyNissanVinsAndVin(vehicleMakeCode, vin, osEm);

		LOG.info("FetchOnlyNissanVins" + ownerPortalVehicleResponse.size());

//		Assert.assertEquals("ALT",ownerPortalVehicleResponse.get(0).getModelLineCode());
	
}

//@Test
//@Ignore
public void fetchOnlyInfinitiVinsAndVinTest() throws Exception {
		LOG.info("inside fetchOnlyInfinitiVinsAndVinTest success case");

		// Setup test input
		String vin = "1N3CH31DYTC101434";
		String vehicleMakeCode = "I";
	    
		OwnerPortalVehicle ownerPortalVehicle = new OwnerPortalVehicle();
		ownerPortalVehicle.setModelYearNumber("2016");
//		ownerPortalVehicle.setModelLineCode("ALT");
		
        List<OwnerPortalVehicle> ownerPortalVehicleList = new ArrayList<OwnerPortalVehicle>();
        ownerPortalVehicleList.add(ownerPortalVehicle);

		when(osEm.createNamedQuery("OwnerPortalVehicle.findByVehicleMakeCodeAndVin", OwnerPortalVehicle.class)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.setParameter("vehicleMakeCode", vehicleMakeCode)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.setParameter("vin", vin)).thenReturn(ownerPortalVehicleQuery);
		when(ownerPortalVehicleQuery.getResultList()).thenReturn(ownerPortalVehicleList);
		
		List<OwnerPortalVehicle> ownerPortalVehicleResponse =  vehicleService.fetchOnlyInfinitiVinsAndVin(vehicleMakeCode, vin, osEm);

		LOG.info("FetchOnlyInfinitiVins" + ownerPortalVehicleResponse.size());

//		Assert.assertEquals("ALT",ownerPortalVehicleResponse.get(0).getModelLineCode());
	
}

//@Test
//@Ignore
public void getSubScriptionProductTest() throws Exception {
		LOG.info("inside getSubScriptionProductTest success case");

		// Setup test input
		String subscriptionProductCode = "BASIC_1";
	    
		OwnerPortalSubScriptionProduct ownerPortalSubScriptionProduct = new OwnerPortalSubScriptionProduct();
		ownerPortalSubScriptionProduct.setSubscriptionProductDescription("Mobile Apps Subscription");
		ownerPortalSubScriptionProduct.setVehicleTelematicsOptionCode("S2");
		
        List<OwnerPortalSubScriptionProduct> ownerPortalSubScriptionProductList = new ArrayList<OwnerPortalSubScriptionProduct>();
        ownerPortalSubScriptionProductList.add(ownerPortalSubScriptionProduct);

		when(osEm.createNamedQuery("OwnerPortalSubScriptionProduct.findBySubscriptionProductCode", OwnerPortalSubScriptionProduct.class)).thenReturn(ownerPortalSubScriptionProductQuery);
		when(ownerPortalSubScriptionProductQuery.setParameter("subscriptionProductCode", subscriptionProductCode)).thenReturn(ownerPortalSubScriptionProductQuery);
		when(ownerPortalSubScriptionProductQuery.getResultList()).thenReturn(ownerPortalSubScriptionProductList);
		
		List<OwnerPortalSubScriptionProduct> ownerPortalSubsProductResponse =  vehicleService.getSubScriptionProduct(subscriptionProductCode, osEm);

		LOG.info("OwnerPortalSubsProductResponse" + ownerPortalSubsProductResponse.size());

		Assert.assertEquals("S2",ownerPortalSubsProductResponse.get(0).getVehicleTelematicsOptionCode());
	
}

//create token


//@Test
//@Ignore
public void modelNameLookupTest() throws Exception {
		LOG.info("inside modelNameLookupTest success case");

		// Setup test input
		String modelCode = "ALT";
	    
		ModelLineMapping modelLineMapping = new ModelLineMapping();
		modelLineMapping.setMakeCode("N");
		modelLineMapping.setModelName("ALTIMA");
		
        List<ModelLineMapping> modelLineMappingList = new ArrayList<ModelLineMapping>();
        modelLineMappingList.add(modelLineMapping);

		when(osEm.createNamedQuery("ModelLineMapping.findByModelCode",ModelLineMapping.class)).thenReturn(modelLineMappingQuery);
		when(modelLineMappingQuery.setParameter("modelCode", modelCode)).thenReturn(modelLineMappingQuery);
		when(modelLineMappingQuery.getResultList()).thenReturn(modelLineMappingList);
		
		ModelLineMapping modelLineMappingResponse =  vehicleService.modelNameLookup(modelCode, osEm);

		LOG.info("ModelLineMappingResponse" + modelLineMappingResponse.getMakeCode());

		Assert.assertEquals("ALTIMA",modelLineMappingResponse.getModelName());

}

//update existing vehicle info

//@Test
//@Ignore
public void getVehicleSpecificationsTest() throws Exception {
		LOG.info("inside getVehicleSpecificationsTest success case");

		// Setup test input
		String modelCode = "91116";
	    
		VehicleSpecification vehicleSpecification = new VehicleSpecification();
		vehicleSpecification.setMake("Infiniti");
		vehicleSpecification.setVersion("Q50 2.0t");
		
        List<VehicleSpecification> vehicleSpecificationList = new ArrayList<VehicleSpecification>();
        vehicleSpecificationList.add(vehicleSpecification);

		when(osEm.createNamedQuery("VehicleSpecification.modelCode", VehicleSpecification.class)).thenReturn(vehicleSpecificationQuery);
		when(vehicleSpecificationQuery.setParameter("modelCode", modelCode)).thenReturn(vehicleSpecificationQuery);
		when(vehicleSpecificationQuery.getResultList()).thenReturn(vehicleSpecificationList);
		
		List<VehicleSpecification> vehicleSpecResponse =  vehicleService.getVehicleSpecifications(modelCode, osEm);

		LOG.info("VehicleSpecResponse" + vehicleSpecResponse.size());

		Assert.assertEquals("Infiniti",vehicleSpecResponse.get(0).getMake());
		
		Assert.assertEquals("Q50 2.0t",vehicleSpecResponse.get(0).getVersion());

}


//@Test
//@Ignore
public void getEquipmentInfoTest() throws Exception {
		LOG.info("inside getEquipmentInfoTest success case");

		// Setup test input
		String modelCode = "91116";
		 

		Equipment equipment = new Equipment();
		equipment.setMake("Infiniti");
		equipment.setVersion("Q50 2.0t");
		equipment.setEquipmentName("Automatic on/off LED headlights with Daytime Running Lights");

		List<Equipment> equipmentList = new ArrayList<Equipment>();
		equipmentList.add(equipment);

		when(osEm.createNamedQuery("Equipment.modelCode", Equipment.class)).thenReturn(equipmentQuery);
		when(equipmentQuery.setParameter("modelCode", modelCode)).thenReturn(equipmentQuery);
		when(equipmentQuery.getResultList()).thenReturn(equipmentList);
		
		List<Equipment> equipmentResponse =  vehicleService.getEquipmentInfo(modelCode, osEm);

		LOG.info("EquipmentResponse" + equipmentResponse.size());

		Assert.assertEquals("Infiniti",equipmentResponse.get(0).getMake());
		
		Assert.assertEquals("Q50 2.0t",equipmentResponse.get(0).getVersion());

}


//@Test
//@Ignore
public void validVehicleTest() throws Exception {
		LOG.info("inside validVehicleTest success case");

		// Setup test input
		String vin = "1N3CH51DYTC10284T";
		String userProfileId = "1564";

		OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference = new OwnerPortalUserVehicleFailedReference();
		ownerPortalUserVehicleFailedReference.setVehicleNickName("Alti");
		ownerPortalUserVehicleFailedReference.setVehicleMakeCode("N");

		List<OwnerPortalUserVehicleFailedReference> ownerPortalUserVehicleFailedReferenceList = new ArrayList<OwnerPortalUserVehicleFailedReference>();
		ownerPortalUserVehicleFailedReferenceList.add(ownerPortalUserVehicleFailedReference);

		when(osEm.createNamedQuery("OwnerPortalUserVehicleFailedReference.findByuserProfileIdAndvin",OwnerPortalUserVehicleFailedReference.class)).thenReturn(ownerPortalUserVehicleFailedReferenceQuery);
		when(ownerPortalUserVehicleFailedReferenceQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleFailedReferenceQuery);
		when(ownerPortalUserVehicleFailedReferenceQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserVehicleFailedReferenceQuery);
        when(ownerPortalUserVehicleFailedReferenceQuery.getResultList()).thenReturn(ownerPortalUserVehicleFailedReferenceList);
		
        String response =  vehicleService.validVehicle(vin, userProfileId, osEm);

		LOG.info("response" + response.toString());

		Assert.assertEquals(true,response.contains("success"));

}


//@Test
//@Ignore
public void validateVinTest() throws Exception {
		LOG.info("inside validateVinTest success case");

		// Setup test input
		String vin = "1N3CH51DYTC10284T";

		OwnerPortalUserVehicleFailedReference ownerPortalUserVehicleFailedReference = new OwnerPortalUserVehicleFailedReference();
		ownerPortalUserVehicleFailedReference.setVehicleNickName("Alti");
		ownerPortalUserVehicleFailedReference.setVehicleMakeCode("N");

		List<OwnerPortalUserVehicleFailedReference> ownerPortalUserVehicleFailedReferenceList = new ArrayList<OwnerPortalUserVehicleFailedReference>();
		ownerPortalUserVehicleFailedReferenceList.add(ownerPortalUserVehicleFailedReference);

		when(osEm.createNamedQuery("OwnerPortalUserVehicleFailedReference.findByvin",OwnerPortalUserVehicleFailedReference.class)).thenReturn(ownerPortalUserVehicleFailedReferenceQuery);
		when(ownerPortalUserVehicleFailedReferenceQuery.setParameter("vin", vin)).thenReturn(ownerPortalUserVehicleFailedReferenceQuery);
        when(ownerPortalUserVehicleFailedReferenceQuery.getResultList()).thenReturn(ownerPortalUserVehicleFailedReferenceList);
		
        OwnerPortalUserVehicleFailedReference response =  vehicleService.validateVin(vin, osEm);

		LOG.info("response" + response.getVehicleMakeCode());

		Assert.assertEquals("Alti",response.getVehicleNickName());
		
		Assert.assertEquals("N",response.getVehicleMakeCode());

}



//load document

//generate failed vehicle reference key

//@Test
//@Ignore
public void generateFailedRefKeyFromSequenceTest() throws Exception {
		LOG.info("inside generateFailedRefKeyFromSequenceTest success case");

		// Setup test input
		List failedRefKey = (List) new BigDecimal(4423);
		//List<BigDecimal> failedRefKey = new ArrayList<BigDecimal>();
		//failedRefKey.add(new BigDecimal(1234));	
		LOG.info("failedRefKey in test" + failedRefKey);
		when(osEm.createNativeQuery(SELECT_NEXTVAL_SQL)).thenReturn(query);
		when(query.getResultList()).thenReturn(failedRefKey);
		LOG.info("response");
		//Assert.assertEquals("Alti",response.getVehicleNickName());
		//Assert.assertEquals("N",response.getVehicleMakeCode());

}*/

 

}
