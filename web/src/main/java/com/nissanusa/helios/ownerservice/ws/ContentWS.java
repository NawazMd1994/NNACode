package com.nissanusa.helios.ownerservice.ws;

/*
 *last modified date 24-10-2016 by x178099

 */

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.logging.Logger;
import com.nissanusa.helios.ownerservice.entity.FAQDetails;
import com.nissanusa.helios.ownerservice.entity.TelematicstermsAndConditions;
import com.nissanusa.helios.ownerservice.facade.UserLocal;
import com.nissanusa.helios.ownerservice.facade.VehicleLocal;
import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.PropertiesLoader;
import com.nissanusa.helios.ownerservice.util.Utility;


@Path("/content")
/**
 * 
 * @author x178099
 * @use WS class will hold the rest services involving add,view,get and delete
 * vehicle services.
 *
 */
public class ContentWS implements OwnerConstants {

	private static final Logger LOG = Logger.getLogger(ContentWS.class);

	@Inject
	VehicleLocal vehicleLocal;
	@Inject
	UserLocal userLocal;

	public ContentWS() {
		try {
			PropertiesLoader.getLog4j();

		} catch (Exception e) {
			LOG.info("PropertiesLoaderException Exception VehicleWS: "
					+ e.getMessage());

		}
	}


	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getTermsAndConditions")
	public Response getTermsAndConditions(@HeaderParam("Brand") String brand, @HeaderParam("Application") String appname) throws JSONException {

		JSONObject jsonFinalObj = new JSONObject();
		TelematicstermsAndConditions telematics;
		String termsandConditionsStr = "";
		String tc ="";

		try {
			LOG.info("Action class = contentWS ::: Method= getTermsConditions :: ApplicationName = "+appname);
			if (brand != null && brand != ""
					&& (brand.equalsIgnoreCase(BRAND_NISSAN) || brand.equalsIgnoreCase(BRAND_INFINITI))) {
				LOG.info("Brand obtained from header: " + brand);
			} else {
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_CODE, VALIDATION_FAILED_MESSAGE,
						VEHICLE_INVALID_BRAND_DESCRIPTION)).build();
			}

			if((appname != null && appname != "") && (appname.equalsIgnoreCase("mynissan") || appname.equalsIgnoreCase("myinfiniti"))){
				
				if((appname.equalsIgnoreCase("mynissan") && brand.equalsIgnoreCase(BRAND_NISSAN)) || (appname.equalsIgnoreCase("myinfiniti") && brand.equalsIgnoreCase(BRAND_INFINITI))){
				if(appname.equalsIgnoreCase("mynissan")){
					tc ="MN";
				}else if(appname.equalsIgnoreCase("myinfiniti")){
					tc ="MI";
				}
				LOG.info("Content to be fetched from db = "+tc);
				telematics = vehicleLocal.getTelematicstermsAndConditions(tc);
				if (telematics != null) {
					LOG.info("Telematics not null"+ telematics.getTermCndtnCntntTx());
					termsandConditionsStr = telematics.getTermCndtnCntntTx();
				}

				jsonFinalObj.accumulate("termsandconditions", termsandConditionsStr);
				LOG.info("Response jsonFinalObj = "+jsonFinalObj);
				return Response.ok().status(200).entity(jsonFinalObj.toString()).build();
			}else{
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_CODE, VALIDATION_FAILED_MESSAGE, VEHICLE_ERROR_FAQCONTENT_DESCRIPTION)).build();
				}
			}
			else{
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_CODE, VALIDATION_FAILED_MESSAGE, VEHICLE_ERROR_CONTENT_DESCRIPTION)).build();
			}
		}
		catch (Exception e) {
			LOG.error("Action class = contentWS ::: Method= getTermsConditions Exception :", e);
			JSONObject jsonObj = new JSONObject();
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj, GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(400).entity(jsonFinalObj.toString()).build();
		}
	}
	
	

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getFAQs")
	public Response getFAQs(@HeaderParam("Brand") String brand, @HeaderParam("Application") String app)
			throws JSONException {
		Date invokedDate = new Date();
		JSONArray jsonCategoryArray = new JSONArray();
		JSONObject jsonFinalObj = new JSONObject();
		try {
			LOG.info("Get FAQs. Process start time = " + invokedDate.toString());

			if (brand != null && brand != ""
					&& (brand.equalsIgnoreCase(BRAND_NISSAN) || brand.equalsIgnoreCase(BRAND_INFINITI))) {
				LOG.info("Brand obtained from header: " + brand);
			} else {
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_CODE, VALIDATION_FAILED_MESSAGE,
						VEHICLE_INVALID_BRAND_DESCRIPTION)).build();
			}

			if (app != null && app != ""
					&& (app.equalsIgnoreCase(APP_NISSAN) || app.equalsIgnoreCase(APP_INFINITI))) {
				LOG.info("App name obtained from header: " + app);
			} else {
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_FIRSTNAME_CODE,
						VALIDATION_FAILED_MESSAGE, APP_NAME_VALIDATION_ERROR_MESSAGE)).build();
			}
			
			if((app.equalsIgnoreCase("mynissan") && brand.equalsIgnoreCase(BRAND_NISSAN)) || (app.equalsIgnoreCase("myinfiniti") && brand.equalsIgnoreCase(BRAND_INFINITI))){
				
			}else{
				return Response.ok().status(400).entity(Utility.faultResponse(VALIDATION_FAILED_FIRSTNAME_CODE, VALIDATION_FAILED_MESSAGE, VEHICLE_ERROR_FAQCONTENT_DESCRIPTION)).build();
			}

			List<FAQDetails> faq = userLocal.getFaqs(brand, app, 1);
			if (!faq.isEmpty() && faq.size() != 0) {
				Map<String, List<FAQDetails>> map = faq.stream()
						.collect(Collectors.groupingBy(FAQDetails::getFaqHeadingCode));
				Map<String, List<FAQDetails>> sortedMap = map.entrySet().stream()
						.sorted(Map.Entry.<String, List<FAQDetails>>comparingByKey()).collect(Collectors
								.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
				sortedMap.forEach((k, v) -> {
					JSONObject jsonCategoryObject = new JSONObject();
					JSONArray jsonDataArray = new JSONArray();
					try {
						String headerName = userLocal.getFaqsHeader(k, 1);
						jsonCategoryObject.accumulate("category", headerName);
						v.forEach((x) -> {
							JSONObject jsonDataObject = new JSONObject();
							try {
								jsonDataObject.accumulate("question", x.getFaqQuestion());
								jsonDataObject.accumulate("answer", x.getFaqAnswer());
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jsonDataArray.put(jsonDataObject);
						});
						jsonCategoryObject.accumulate("data", jsonDataArray);
						jsonCategoryArray.put(jsonCategoryObject);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				});
			} else {
				return Response.ok().status(400).entity(
						Utility.faultResponse(INVALID_USERANDVIN_CODE, FAQ_ERROR_MESSAGE, FAQ_NOT_FOUND_ERROR_MESSAGE))
						.build();
			}
			jsonFinalObj.put("faq", jsonCategoryArray);
			LOG.info("Processing Time for Get FAQs, duration = " + Utility.showTimeDiff(invokedDate) + "ms");
			return Response.ok().status(200).entity(jsonFinalObj.toString()).build();
		} catch (Exception e) {
			LOG.error("Action class = vehicleWS ::: Method= getFAQs Exception :", e);
			JSONObject jsonObj = new JSONObject();
			Util.setFaultDataToJSON(jsonObj, jsonFinalObj, GENERAL_ERROR_FAULT_CODE, GENERAL_ERROR_MESSAGE,
					GENERAL_ERROR_DESCRIPTION);
			return Response.ok().status(400).entity(jsonFinalObj.toString()).build();
		}
	}


}
