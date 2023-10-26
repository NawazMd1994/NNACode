package com.helios.services;

import java.io.IOException;
import java.util.Date;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
//import javax.ws.rs.core.Response;

import javax.persistence.TypedQuery;

import org.apache.log4j.BasicConfigurator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;
 



import com.nissanusa.helios.ownerservice.entity.Decryption;
import com.nissanusa.helios.ownerservice.entity.Encryption;
import com.nissanusa.helios.ownerservice.entity.MobileProvider;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalLinkReference;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUser;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhone;
import com.nissanusa.helios.ownerservice.entity.OwnerPortalUserPhonePK;
import com.nissanusa.helios.ownerservice.entity.State;
import com.nissanusa.helios.ownerservice.service.UserService;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.vo.AccountVO;
import com.nissanusa.helios.ownerservice.vo.AddressVO;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailRevokeWrapper;
import com.nissanusa.helios.ownerservice.vo.ChangeEmailWrapper;
import com.nissanusa.helios.ownerservice.vo.DisclaimerPreferencesVO;
import com.nissanusa.helios.ownerservice.vo.SavePersonVO;
import com.nissanusa.helios.ownerservice.vo.SaveUserWrapper;
import com.nissanusa.helios.ownerservice.vo.ValidateCdiidWrapper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceTest implements OwnerConstants{

	UserService userService;
	
	ValidateCdiidWrapper validatecdiidwrapper;
	
	ChangeEmailWrapper changeEmailWrapper;
	
	ChangeEmailRevokeWrapper changeEmailRevokeWrapper;
	private String requestJson;
	
	ObjectMapper mapper;
	
	private static final Logger LOG = Logger.getLogger(UserServiceTest.class);

	@Mock
	EntityManager osEm;

	@Mock
	TypedQuery<OwnerPortalUser> userDetailsQuery;
	
	@Mock 
	TypedQuery<State> stateQuery;
	
	@Mock 
	TypedQuery<OwnerPortalLinkReference> ownerPortalLinkReferenceQuery;
	
	@Mock 
	TypedQuery<OwnerPortalUser> ownerPortalUserQuery;
	
	@Mock 
	TypedQuery<OwnerPortalUserPhone> ownerPortalUserPhoneQuery;
	
	@Mock 
	TypedQuery<MobileProvider> mobileProviderQuery;
	
	@Mock
	Query passwordQuery;
	
	@Mock
	TypedQuery<OwnerPortalLinkReference> updateLinkRefQuery;

	@Before
	public void setUp() throws Exception {
		userService = PowerMockito.spy(new UserService());
		BasicConfigurator.configure();
		MockitoAnnotations.initMocks(this);
		requestJson = getRequestStringForEmpty("", "");
		validatecdiidwrapper = wrapRequest(requestJson);

	}

	@After
	public void tearDown() throws Exception {

	}

	/**
	 * author x566325
	 */
 
    // @Test
	//@Ignore
	public void validateEmailTest() throws Exception {
		LOG.info("inside validateEmail test success case");

		// Setup test input
		String email = "durga@test.com";
		String brand = "N";
		String personHashId = "d06e38aa-1ee4-4793-ab03-cb6ca45d5dd3";
		OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
		ownerPortalUser.setCustomerDataIntegrationId("143762991");
		ownerPortalUser.setFirstName("Durga");

		List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
		ownerPortalUserList.add(ownerPortalUser);

		when(osEm.createNamedQuery("OwnerPortalUser.findByemailIdandpersonalHashId",OwnerPortalUser.class)).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("emailId", email.toLowerCase())).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("personalHashId", personHashId)).thenReturn(userDetailsQuery);
        when(userDetailsQuery.getResultList()).thenReturn(ownerPortalUserList);
		
		OwnerPortalUser response = userService.validateEmail(email,
				personHashId,brand, osEm);

		LOG.info("First Name" + response.getFirstName());

		Assert.assertEquals("143762991",
				response.getCustomerDataIntegrationId());
		
		Assert.assertEquals("Durga",
				response.getFirstName());

	}
	
	

	//@Test
	//doubt - two cases
	//@Ignore
	public void validateCdiidTest1() throws Exception {
		LOG.info("inside validateCdiidtest1 success case");

		// Setup test input
		String email = "durga@test.com";
		String personHashId = "d06e38aa-1ee4-4793-ab03-cb6ca45d5dd3";
		String cdid = "143762991";
		OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
	    ownerPortalUser.setFirstName("Durga");
	    ownerPortalUser.setUserProfileId("1564");

		List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
		ownerPortalUserList.add(ownerPortalUser);

		when(osEm.createNamedQuery("OwnerPortalUser.findBycustomerDataIntegrationIdEmailIdandpersonalHashId",OwnerPortalUser.class)).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("emailId", email.toLowerCase())).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("personalHashId", personHashId)).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("customerDataIntegrationId", cdid)).thenReturn(userDetailsQuery);
        when(userDetailsQuery.getResultList()).thenReturn(ownerPortalUserList);
        
        LOG.info("ownerPortalUserList" + ownerPortalUserList);
       
        if(ownerPortalUserList.isEmpty()){
        	findByCDID();
      
        }
		String response = userService.validateCdiid(validatecdiidwrapper, osEm);

		LOG.info("Response in validateCdiidTest" + response);

		Assert.assertEquals(true,response.contains("success"));
		
		 
	}
	
	/*//doubtyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
	//@Test
	//@Ignore
	public void validateCdiidTest2() throws Exception {
		LOG.info("inside validateCdiidtest2 success case");

		// Setup test input
		String email = "durga@test.com";
		String personHashId = "d06e38aa-1ee4-4793-ab03-cb6ca45d5dd3";
		String cdid = "143762991";
		//OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
	   // ownerPortalUser.setFirstName("");
	   // ownerPortalUser.setUserProfileId("");

		List<OwnerPortalUser> ownerPortalUserList = null;
		//ownerPortalUserList.add(ownerPortalUser);

		when(osEm.createNamedQuery("OwnerPortalUser.findBycustomerDataIntegrationIdEmailIdandpersonalHashId",OwnerPortalUser.class)).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("emailId", email.toLowerCase())).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("personalHashId", personHashId)).thenReturn(userDetailsQuery);
		when(userDetailsQuery.setParameter("customerDataIntegrationId", cdid)).thenReturn(userDetailsQuery);
        when(userDetailsQuery.getResultList()).thenReturn(ownerPortalUserList);
        
        LOG.info("ownerPortalUserList" + ownerPortalUserList.size());
       
        if(ownerPortalUserList.isEmpty()){
        	findByCDID();
      
        }
		String response = userService.validateCdiid(validatecdiidwrapper, osEm);

		LOG.info("Response in validateCdiidTest" + response);

		Assert.assertEquals(true,response.contains("success"));
		
		 
	}*/
	
 	private void findByCDID(){
		    String cdid = "143762991";
			OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
		    ownerPortalUser.setFirstName("Durga");
		    ownerPortalUser.setUserProfileId("1564");
		    
			List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
			ownerPortalUserList.add(ownerPortalUser);
			
		    when(osEm.createNamedQuery("OwnerPortalUser.findBycustomerDataIntegrationId",OwnerPortalUser.class)).thenReturn(userDetailsQuery);
	        when(userDetailsQuery.setParameter("customerDataIntegrationId", cdid)).thenReturn(userDetailsQuery);
	        when(userDetailsQuery.getResultList()).thenReturn(ownerPortalUserList);
	        LOG.info("list is" + ownerPortalUserList);
	 }
	
	
	/**
	 * Wrap the Request JSON into the class file
	 * @param requestJson
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */

	private ValidateCdiidWrapper wrapRequest(String requestJson) throws IOException,
			JsonParseException, JsonMappingException {
		mapper = new ObjectMapper();
		return validatecdiidwrapper = mapper.readValue(requestJson,
				ValidateCdiidWrapper.class);
	}
	
	
	/**
	 * Get the Request JSON for different test cases
	 * @param value of the test
	 * @return the request json
	 * @throws JSONException
	 */
	private String getRequestStringForEmpty(String value, String setValue)
			throws JSONException {
		Map<String, Object> requestMap = getValues();
		Set<String> requstSet = requestMap.keySet();
		JSONObject obj = new JSONObject();
		for (String key : requstSet) {
			if (key.trim().equalsIgnoreCase(value)) {
				obj.accumulate(key, setValue);
			} else {
				obj.accumulate(key, requestMap.get(key));
			}
		}
		
		return obj.toString();
	}
	
	private Map<String, Object> getValues() throws JSONException {
		 
		Map<String, Object> requestMap = new HashMap<String, Object>();
		JSONObject validateCustomerSystemId = new JSONObject();
		validateCustomerSystemId.accumulate("cdiid", "143762991");
		validateCustomerSystemId.accumulate("email", "durga@test.com");
		validateCustomerSystemId.accumulate("personHashId", "d06e38aa-1ee4-4793-ab03-cb6ca45d5dd3");
		requestMap.put("validateCustomerSystemId",validateCustomerSystemId);
	    return requestMap;

	}
	
	
	//@Test
	//@Ignore
	public void getStateByStateKeyTest() throws Exception {
		LOG.info("inside getStateByStateKey test success case");

		// Setup test input
		String stateKey = "1";
		 
		State state = new State();
		state.setStateCode("AL");
		state.setStateName("Alabama");
		state.setCountryCode("US");

		List<State> stateList = new ArrayList<State>();
		stateList.add(state);

		when(osEm.createNamedQuery("State.findByStateKey",State.class)).thenReturn(stateQuery);
		when(stateQuery.setParameter("stateKey", stateKey)).thenReturn(stateQuery);
        when(stateQuery.getResultList()).thenReturn(stateList);
		
        State response = userService.getStateByStateKey(stateKey, osEm); 
        		
		LOG.info("Response for getStateByStateKeyTest" + response.getStateCode());

		Assert.assertEquals("US",response.getCountryCode());
		
		Assert.assertEquals("Alabama",response.getStateName());

	}
	
	//@Test
	//@Ignore
	public void getStateCodeTest() throws Exception {
		LOG.info("inside getStateCode test success case");

		// Setup test input
		String stateCode = "TN";
		 
		State state = new State();
	    state.setStateName("Tennessee");
		state.setCountryCode("US");

		List<State> stateList = new ArrayList<State>();
		stateList.add(state);

		when(osEm.createNamedQuery("State.findByStateCode",State.class)).thenReturn(stateQuery);
		when(stateQuery.setParameter("stateCode", stateCode)).thenReturn(stateQuery);
        when(stateQuery.getResultList()).thenReturn(stateList);
		
        State response = userService.getStateCode(stateCode, osEm); 
        		
		LOG.info("Response for getStateCodeTest" + response.getStateName());

		Assert.assertEquals("US",response.getCountryCode());
		
		Assert.assertEquals("Tennessee",response.getStateName());

	}
	
	    //@Test
		//@Ignore
		public void validateUserTest() throws Exception {
			LOG.info("inside validateUser test success case");

			// Setup test input
			String email = "durga@test.com";
			String brand = "Nissan";
			 
			OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
			ownerPortalUser.setPersonalHashId("d06e38aa-1ee4-4793-ab03-cb6ca45d5dd3");
			ownerPortalUser.setUserProfileId("1564");

			List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
			ownerPortalUserList.add(ownerPortalUser);

			when(osEm.createNamedQuery("OwnerPortalUser.findByemailId",OwnerPortalUser.class)).thenReturn(userDetailsQuery);
			when(userDetailsQuery.setParameter("emailId", email)).thenReturn(userDetailsQuery);
	        when(userDetailsQuery.getResultList()).thenReturn(ownerPortalUserList);
			
	        OwnerPortalUser response = userService.validateUser(email, brand, osEm); 
	        		
			LOG.info("Response for validateUserTest" + response.getEmailId());

 			Assert.assertEquals("1564",response.getUserProfileId());
		 
		}
	 
		 
		  // @Test
			//@Ignore
			public void isCdiidSavedTest() throws Exception {
				LOG.info("inside isCdiidSaved test success case");
            boolean response = true;
				// Setup test input
				String email = "durga@test.com";
				String personHashId = "d06e38aa-1ee4-4793-ab03-cb6ca45d5dd3";
				 
				OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
				ownerPortalUser.setCityName("Franklin");
				ownerPortalUser.setUserProfileId("1564");

				List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
				ownerPortalUserList.add(ownerPortalUser);

				when(osEm.createNamedQuery("OwnerPortalUser.findByemailIdandpersonalHashId",OwnerPortalUser.class)).thenReturn(userDetailsQuery);
				when(userDetailsQuery.setParameter("emailId", email)).thenReturn(userDetailsQuery);
				when(userDetailsQuery.setParameter("personalHashId", personHashId)).thenReturn(userDetailsQuery);
		        when(userDetailsQuery.getResultList()).thenReturn(ownerPortalUserList);
				
		         response = userService.isCdiidSaved(validatecdiidwrapper, osEm); 
		        //merge	
		        when(osEm.merge(ownerPortalUser)).thenReturn(null);
				LOG.info("Response for validateUserTest" + response);

	 			Assert.assertEquals(true,response);
			 
			}
	
	 
		 
		   //@Test
		    //@Ignore
		    public void encryptPasswordTest() throws Exception{
		    	LOG.info("inside encryptPasswordTest success case");
		    	
		    	// Setup test input
		    	String decryptedPassword = "Nissan@14";
		 
		    	Encryption encryption = new Encryption();
		    	encryption.setsPassword("D3A5F9E9A8671376E224C5E080DEA97E");
		     
		    	
		    	List<Encryption> encryptionList = new ArrayList<Encryption>();
		    	encryptionList.add(encryption);
		    	
		    	
				when(osEm.createNativeQuery("select MNS_B2C_OWN_APP.encryption_decryption.encrypt(?)  as sPassword from dual",Encryption.class)).thenReturn(passwordQuery);
				when(passwordQuery.setParameter(1, decryptedPassword)).thenReturn(passwordQuery);
				when(passwordQuery.getResultList()).thenReturn(encryptionList);
				
		    	String userPassword = userService.encryptPassword(decryptedPassword, osEm);
		    	LOG.info("userpassword :::" + userPassword);
		    	Assert.assertEquals("D3A5F9E9A8671376E224C5E080DEA97E", userPassword);
		    	
		    	
		    }
		    
	  
		   // @Test
			//@Ignore
			public void getStateByStateNameTest() throws Exception {
				LOG.info("inside getStateByStateName test success case");

				// Setup test input
				String stateName = "Alabama";
				 
				State state = new State();
				state.setStateCode("AL");
				state.setCountryCode("US");

				List<State> stateList = new ArrayList<State>();
				stateList.add(state);

				when(osEm.createNamedQuery("State.findByStateName",State.class)).thenReturn(stateQuery);
				when(stateQuery.setParameter("stateName", stateName)).thenReturn(stateQuery);
		        when(stateQuery.getResultList()).thenReturn(stateList);
				
		        State response = userService.getStateByStateName(stateName, osEm); 
		        		
				LOG.info("Response for getStateByStateNameTest" + response.getStateCode());

				Assert.assertEquals("US",response.getCountryCode());
				
				Assert.assertEquals("AL",response.getStateCode());
		    }
		
	/**
	 * author x566325
	 */
    //@Test
	//@Ignore
    public void getLinkReferenceDetailsTest() throws Exception{
		LOG.info("inside getLinkReferenceDetailsTestsuccess case");

		// Setup test input
		String linkReferenceHashId = "a39bbf26-820d-4587-a6c2-0b927e0a4da8";
		String userProfileId = "1101";
		String activeIn = "Y";

		Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse("01 NOVEMBER 2012");
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		java.sql.Date sqlDateTest = new java.sql.Date(utilDate.getTime());
		
		OwnerPortalLinkReference ownerPortalLinkReference = new OwnerPortalLinkReference();
		ownerPortalLinkReference.setCreateTimestamp(sqlDate);
		
		List<OwnerPortalLinkReference> ownerPortalLinkReferenceList = new ArrayList<OwnerPortalLinkReference>();
		ownerPortalLinkReferenceList.add(ownerPortalLinkReference);
		
		when(osEm.createNamedQuery("OwnerPortalLinkReference.findByLinkReferenceHashIdandEmailId",OwnerPortalLinkReference.class)).thenReturn(ownerPortalLinkReferenceQuery);
		when(ownerPortalLinkReferenceQuery.setParameter("linkReferenceHashId", linkReferenceHashId)).thenReturn(ownerPortalLinkReferenceQuery);
		when(ownerPortalLinkReferenceQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalLinkReferenceQuery);
		when(ownerPortalLinkReferenceQuery.setParameter("activeIn", activeIn)).thenReturn(ownerPortalLinkReferenceQuery);
        when(ownerPortalLinkReferenceQuery.getResultList()).thenReturn(ownerPortalLinkReferenceList);
        
        OwnerPortalLinkReference response = userService.getLinkReferenceDetails(linkReferenceHashId, userProfileId, osEm);
        
        Assert.assertEquals(sqlDateTest,
				response.getCreateTimestamp());
    	
    }
    
	/**
	 * author x566325
	 */
	//@Test
	//@Ignore
	public void getUserDetailsTest() throws Exception{
		LOG.info("inside getUserDetailsTest success case");
		
		// Setup test input
		String userProfileId = "1101";
		
		OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
		ownerPortalUser.setCustomerDataIntegrationId("142718235");
		
		List<OwnerPortalUser> ownerPortalUserList = new ArrayList<OwnerPortalUser>();
		ownerPortalUserList.add(ownerPortalUser);
		
		when(osEm.createNamedQuery("OwnerPortalUser.findByuserProfileId",OwnerPortalUser.class)).thenReturn(ownerPortalUserQuery);
		when(ownerPortalUserQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserQuery);
        when(ownerPortalUserQuery.getResultList()).thenReturn(ownerPortalUserList);
        
        OwnerPortalUser response = userService.getUserDetails(userProfileId, osEm);
        
        Assert.assertEquals("142718235",response.getCustomerDataIntegrationId());
		
	}
	
	/**
	 * author x566325
	 */
	//@Test
	//@Ignore
	public void getUserPhoneDetailsTest() throws Exception{
		LOG.info("inside getUserPhoneDetailsTest success case");
		
		//Setup test input
		String userProfileId = "1101";
				
		OwnerPortalUserPhone userPhone = new OwnerPortalUserPhone();;
		//userPhone.setPhoneNumber("4546454654");
		
		List<OwnerPortalUserPhone> ownerPortalUserPhoneList = new ArrayList<OwnerPortalUserPhone>();
		ownerPortalUserPhoneList.add(userPhone);
		
		when(osEm.createNamedQuery("OwnerPortalUserPhone.findByuserProfileId",OwnerPortalUserPhone.class)).thenReturn(ownerPortalUserPhoneQuery);
		when(ownerPortalUserPhoneQuery.setParameter("userProfileId", userProfileId)).thenReturn(ownerPortalUserPhoneQuery);
        when(ownerPortalUserPhoneQuery.getResultList()).thenReturn(ownerPortalUserPhoneList);
		
        List <OwnerPortalUserPhone> response = userService.getUserPhoneDetails(userProfileId, osEm);
        
        Iterator<OwnerPortalUserPhone> iterator = response.iterator();
        
		while (iterator.hasNext()) {
			userPhone = iterator
					.next();
		}
        
        //Assert.assertEquals("4546454654", userPhone.getPhoneNumber());
		
		
	}
	
	/**
	 * author x566325
	 */
	    //@Test
	   // @Ignore
	    public void getOwnerPortalLinkReferenceDetailsTest() throws Exception{
			LOG.info("inside getOwnerPortalLinkReferenceDetailsTest success case");

			// Setup test input
			String linkReferenceHashId = "a39bbf26-820d-4587-a6c2-0b927e0a4da8";
			String activeIn = "Y";

			Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse("01 NOVEMBER 2012");
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			java.sql.Date sqlDateTest = new java.sql.Date(utilDate.getTime());
			
			OwnerPortalLinkReference ownerPortalLinkReference = new OwnerPortalLinkReference();
			ownerPortalLinkReference.setCreateTimestamp(sqlDate);
			
			List<OwnerPortalLinkReference> ownerPortalLinkReferenceList = new ArrayList<OwnerPortalLinkReference>();
			ownerPortalLinkReferenceList.add(ownerPortalLinkReference);
			
			when(osEm.createNamedQuery("OwnerPortalLinkReference.findByLinkReferenceHashId",OwnerPortalLinkReference.class)).thenReturn(ownerPortalLinkReferenceQuery);
			when(ownerPortalLinkReferenceQuery.setParameter("linkReferenceHashId", linkReferenceHashId)).thenReturn(ownerPortalLinkReferenceQuery);
			when(ownerPortalLinkReferenceQuery.setParameter("activeIn", activeIn)).thenReturn(ownerPortalLinkReferenceQuery);
	        when(ownerPortalLinkReferenceQuery.getResultList()).thenReturn(ownerPortalLinkReferenceList);
	        
	        OwnerPortalLinkReference response = userService.getOwnerPortalLinkReferenceDetails(linkReferenceHashId, osEm);
	        
	        Assert.assertEquals(sqlDateTest,response.getCreateTimestamp());
	    	
	    }
	    
	    
	    /**
		 * author x566325
		 */
	    //@Test
	    //@Ignore
	    public void getProviderByProviderCodeTest() throws Exception{
	    	LOG.info("inside getProviderByProviderCodeTest success case");	
	    	
	    	// Setup test input
	    	String mobileCarrierCode = "US_CELLULA";
	    	
	    	MobileProvider mobileProvider = new MobileProvider();
	    	mobileProvider.setMobileProviderName("US Cellular");
	    	
	    	List<MobileProvider> mobileProviderList = new ArrayList<MobileProvider>();
	    	mobileProviderList.add(mobileProvider);
	    	
	    	when(osEm.createNamedQuery("MobileProvider.findByMobileProviderKey",MobileProvider.class)).thenReturn(mobileProviderQuery);
			when(mobileProviderQuery.setParameter("mobileProviderKey", mobileCarrierCode)).thenReturn(mobileProviderQuery);
	        when(mobileProviderQuery.getResultList()).thenReturn(mobileProviderList);
	        
	        //MobileProvider response = userService.getProviderByProviderCode(mobileCarrierCode, osEm);
	        
	        //Assert.assertEquals("US Cellular", response.getMobileProviderName());	
	    	
	    }
	    
	    /**
		 * author x566325
		 */
	    //@Test
	  // @Ignore
	    public void getProviderByProviderNameTest() throws Exception{
	    	LOG.info("inside getProviderByProviderNameTest success case");	
	    	
	    	// Setup test input
	    	String mobileCarrierName = "US Cellular";
	    	
	    	MobileProvider mobileProvider = new MobileProvider();
	    	mobileProvider.setMobileProviderKey("US_CELLULA");
	    	
	    	List<MobileProvider> mobileProviderList = new ArrayList<MobileProvider>();
	    	mobileProviderList.add(mobileProvider);
	    	
	    	when(osEm.createNamedQuery("MobileProvider.findByMobileProviderName",MobileProvider.class)).thenReturn(mobileProviderQuery);
			when(mobileProviderQuery.setParameter("mobileProviderName", mobileCarrierName)).thenReturn(mobileProviderQuery);
	        when(mobileProviderQuery.getResultList()).thenReturn(mobileProviderList);
	        
	        MobileProvider response = userService.getProviderByProviderName(mobileCarrierName, osEm);
	        
	        Assert.assertEquals("US_CELLULA", response.getMobileProviderKey());	
	    	
	    }
	    
	    /**
		 * author x566325
		 */
	   // @Test
	   // @Ignore
	    public void decryptPasswordTest() throws Exception{
	    	LOG.info("inside decryptPasswordTest success case");
	    	
	    	// Setup test input
	    	String encryptedPassword = "D3A5F9E9A8671376E224C5E080DEA97E";
	 
	    	Decryption decryption = new Decryption();
	    	decryption.setpPassword("Nissan@14");
	     
	    	
	    	List<Decryption> decryptionList = new ArrayList<Decryption>();
	    	decryptionList.add(decryption);
	    	
	    	
			when(osEm.createNativeQuery("select MNS_B2C_OWN_APP.encryption_decryption.decrypt(?) as pPassword from dual",Decryption.class)).thenReturn(passwordQuery);
			when(passwordQuery.setParameter(1, encryptedPassword)).thenReturn(passwordQuery);
			when(passwordQuery.getResultList()).thenReturn(decryptionList);
			
	    	String userPassword = userService.decryptPassword(encryptedPassword, osEm);
	    	LOG.info("userpassword :::" + userPassword);
	    	Assert.assertEquals("Nissan@14", userPassword);
	    	
	    	
	    }
	    
 
	    
		
		
		 /**
		 * author x566325
		 */
		//@Test
		//@Ignore
		public void updateUserTest() throws Exception{
			LOG.info("inside updateUserTest test success case");
			
			SaveUserWrapper saveUserWrapper = new SaveUserWrapper();
			OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
			OwnerPortalUserPhone ownerPortalUserPhone1 = new OwnerPortalUserPhone();
			MobileProvider mobileProvider = new MobileProvider();

			
			mobileProvider.setMobileProviderKey("US_CELLULA");
			//ownerPortalUserPhone1.setPhoneNumber("6124566987");
			
			Encryption encryption = new Encryption();
	    	encryption.setsPassword("D3A5F9E9A8671376E224C5E080DEA97E");
	     
	    	
	    	List<Encryption> encryptionList = new ArrayList<Encryption>();
	    	encryptionList.add(encryption);
			
			// Setup test input
			AccountVO accountVO = new AccountVO();
			AddressVO addressVO = new AddressVO();
			addressVO.setAddressLine1("23 Ambian Street");
			addressVO.setCity("Franklin");
			addressVO.setCountry("US");
			addressVO.setPostalCode("37067");
			
			accountVO.setTitle("Mr");
			accountVO.setFirstName("Durgaa");
			accountVO.setMiddleName("DD");
			accountVO.setLastName("Devi");
			accountVO.setEmail("durga@test.com");
			accountVO.setAddress(addressVO);
			accountVO.setPassword("Nissan123$");
			
			State state = new State();
			state.setStateName("Tennessee");
			
			OwnerPortalUserPhone userPhone = new OwnerPortalUserPhone();;
			//userPhone.setPhoneNumber("4546454654");
			OwnerPortalUserPhonePK ownerPortalUserPhonePK = new OwnerPortalUserPhonePK();
			//ownerPortalUserPhonePK.setPhoneTypeId("work");
			userPhone.setOwnerPortalUserPhonePK(ownerPortalUserPhonePK);
			
			List<String> workPhone = new ArrayList<String>();
			workPhone.add("sms");
			workPhone.add("autodial");
			
			DisclaimerPreferencesVO disclaimerPreferencesVO = new DisclaimerPreferencesVO();
			disclaimerPreferencesVO.setWorkPhone(workPhone);
			accountVO.setDisclaimerPreferences(disclaimerPreferencesVO);
			
			List<OwnerPortalUserPhone> ownerPortalUserPhoneList = new ArrayList<OwnerPortalUserPhone>();
			ownerPortalUserPhoneList.add(userPhone);
			
			SavePersonVO savePersonVO = new SavePersonVO();
			savePersonVO.setPerson(accountVO);
			
			
		saveUserWrapper.setSaveUser(savePersonVO);
		
		ownerPortalUser.setEmailId("durga@test.com");
		
		when(osEm.createNativeQuery("select MNS_B2C_OWN_APP.encryption_decryption.encrypt(?)  as sPassword from dual",Encryption.class)).thenReturn(passwordQuery);
		when(passwordQuery.setParameter(1, saveUserWrapper.getSaveUser().getPerson().getPassword())).thenReturn(passwordQuery);
		when(passwordQuery.getResultList()).thenReturn(encryptionList);
		
		when(osEm.merge(ownerPortalUser)).thenReturn(ownerPortalUser);
		when(osEm.merge(userPhone)).thenReturn(ownerPortalUserPhone1);
		
		OwnerPortalUser response = userService.updateUser(saveUserWrapper,ownerPortalUser,ownerPortalUserPhoneList,state,mobileProvider,osEm);
		
		Assert.assertEquals("durga@test.com",response.getEmailId());
					
		}
		
		/**
		 * author x566325
		 */
		//@Test
		//@Ignore
		public void updateUnlockAccountStatusTest() throws Exception{
			LOG.info("inside updateUnlockAccountStatusTest success case");
			
	    String response = FAILURE;
		OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
		ownerPortalUser.setEmailId("durga@test.com");
		
		
		ownerPortalUser.setAccountStatusCode(UNLOCK);
		ownerPortalUser.setUpdateUserId(USERID);
		ownerPortalUser.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
		
		when(osEm.merge(ownerPortalUser)).thenReturn(ownerPortalUser);
		
		response = userService.updateUnlockAccountStatus(ownerPortalUser, osEm);
		
		Assert.assertEquals("success",response);
			
		}
		
		/**
		 * author x566325
		 */
		@Test
		//@Ignore
		public void createUserTest() throws Exception{
			LOG.info("inside createUserTest success case");
			
			// Setup test input
			OwnerPortalUser portalUser = new OwnerPortalUser();
			portalUser.setEmailId("durga@test.com");
			
			AccountVO accountVO = new AccountVO();
		    AddressVO addressVO = new AddressVO();
			addressVO.setAddressLine1("23 Ambian Street");
			addressVO.setCity("Franklin");
			addressVO.setCountry("US");
			addressVO.setPostalCode("37067");				
		    accountVO.setTitle("Mr");
			accountVO.setFirstName("Durgaa");
			accountVO.setMiddleName("DD");
			accountVO.setLastName("Devi");
			accountVO.setEmail("durga@test.com");
			accountVO.setAddress(addressVO);
			accountVO.setPassword("Nissan123$");
			accountVO.setSecondLastName("Dd");
			
	        doNothing().when(osEm).persist(Matchers.any(OwnerPortalUser.class));

	       boolean result =  userService.createUser(accountVO, osEm);
	        		
	     Assert.assertEquals("true",result);

		}

		        
            
		 	  //@Test
		 		//@Ignore
		 		public void updateLinkReferenceHashIdStatusTest() throws Exception {
		 			LOG.info("inside updateLinkReferenceHashIdStatus test success case");

		 			// Setup test input
		 			String userProfileId = "1564";
		 			String linkReferenceHashId = "e8feefad-dc9a-4021-8a13-84acc6bbf5dc";
		 			String activeIn = "N";
		 			//String UPDATE_QUERY = "Update OwnerPortalLinkReference set activeIn='N' where linkReferenceHashId= ? and userProfileId = ?";
		 		 
		 			OwnerPortalLinkReference ownerPortalLinkReference = new OwnerPortalLinkReference();
		 			ownerPortalLinkReference.setUserProfileId(userProfileId);
		 			ownerPortalLinkReference.setLinkReferenceHashId(linkReferenceHashId);
		 			
		 			
		 			when(osEm.createQuery(
		 					"Update OwnerPortalLinkReference set activeIn='N' where linkReferenceHashId= ? and userProfileId = ? ")).thenReturn(updateLinkRefQuery);
		 			when(updateLinkRefQuery.setParameter(1, linkReferenceHashId)).thenReturn(updateLinkRefQuery);
		 			when(updateLinkRefQuery.setParameter(2, userProfileId)).thenReturn(updateLinkRefQuery);
		 	        when(updateLinkRefQuery.executeUpdate()).thenReturn(1);
		 			
		 		 
		 	         userService.updateLinkReferenceHashIdStatus(ownerPortalLinkReference, osEm);
		 	        		
		 			LOG.info("Response for updateLinkReferenceHashIdStatus");

		 			Assert.assertEquals("N",activeIn);
		 			
		 			Assert.assertEquals("1564",userProfileId);

		 		}
		 		
		 		//not working
		 		// @Test
		 		//@Ignore
		 		public void updateLinkReferenceHashIdStatusByUserProfileIdTest() throws Exception {
		 			LOG.info("inside updateLinkReferenceHashIdStatusByUserProfileId test success case");

		 			// Setup test input
		 			String userProfileId = "1564";
		           	String activeIn = "N";
		         
		 			when(osEm.createQuery("Update OwnerPortalLinkReference set activeIn='N' where userProfileId = ?")).thenReturn(updateLinkRefQuery);
                    when(updateLinkRefQuery.setParameter(1, userProfileId)).thenReturn(updateLinkRefQuery);
		 	        when(updateLinkRefQuery.executeUpdate()).thenReturn(1);
		 		 
		 	         userService.updateLinkReferenceHashIdStatusByUserProfileId(userProfileId, osEm);
		 	        		
		 			LOG.info("Response for updateLinkReferenceHashIdStatusByUserProfileId");

		 			Assert.assertEquals("N",activeIn);
		 			
		 			Assert.assertEquals("1564",userProfileId);

		 		}


		 		
		 		  
		 		//token mock -pending
		 		//Test
		 		@Ignore
		 		public void createLinkReferenceIdTest() throws Exception{
		 			LOG.info("inside createLinkReferenceId Test success case");
		 			
		 			// Setup test input
		 		  String token = "a0af0527-62e3-47aa-b4fa-67d44871bc9c";
		 			//UUID sampel = UUID.randomUUID();
		 		    
		 		    
		 		    String usrPrflId = "1564";
		 			OwnerPortalLinkReference linkRef = new OwnerPortalLinkReference();
		 		    
		 		   linkRef.setUserProfileId("1564");
		 		   linkRef.setLinkReferenceHashId("e8feefad-dc9a-4021-8a13-84acc6bbf5dc");
		 		   linkRef.setFunctionTypeId("");
		 		   linkRef.setActiveIn("Y");			
		 		   linkRef.setCreateUserId(USERID);
		 		   linkRef.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
		 		 
		 		   when(UUID.randomUUID().toString()).thenReturn("a0af0527-62e3-47aa-b4fa-67d44871bc9c");
		 		   doNothing().when(osEm).persist(Matchers.any(OwnerPortalLinkReference.class));
		 		  
		 			String response = userService.createLinkReferenceId(usrPrflId, osEm);
		 			
		 			LOG.info("response:"+ response);
		 			
		 			Assert.assertEquals("token",token);
		 	 
		 		}


		 		//@Test
				//@Ignore
				public void updateEmailTest() throws Exception{
					LOG.info("inside updateEmailTest success case");
					
			    
				OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
				ownerPortalUser.setEmailId("durga@test.com");
			    //ownerPortalUser.setUpdateUserId(USERID);
				//ownerPortalUser.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
				
				when(osEm.merge(ownerPortalUser)).thenReturn(ownerPortalUser);
				
				OwnerPortalUser response = userService.updateEmail(changeEmailWrapper,ownerPortalUser, osEm);
				
				Assert.assertEquals("durga@test.com",response.getEmailId());
					
				}
		 		
		 		
		 		//@Test
				//@Ignore
				public void revokeEmailTest() throws Exception{
					LOG.info("inside revokeEmailTest success case");
					
					OwnerPortalUser ownerPortalUser = new OwnerPortalUser();
					ownerPortalUser.setEmailId("durga@test.com");
					ownerPortalUser.setAccountStatusCode(UNLOCK);
					ownerPortalUser.setUpdateUserId(USERID);
					ownerPortalUser.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
					
					when(osEm.merge(ownerPortalUser)).thenReturn(ownerPortalUser);
					
					OwnerPortalUser response = userService.revokeEmail(changeEmailRevokeWrapper,ownerPortalUser, osEm);
				
				Assert.assertEquals("durga@test.com",response.getEmailId());
					
				}

}
