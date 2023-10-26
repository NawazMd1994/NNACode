package com.nissanusa.helios.ownerservice.util;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
/*
 *last modified date 31-05-2016 by x178099

 */
import org.jboss.logging.Logger;

import com.nissanusa.helios.ownerservice.entity.OwnerPortalVehicle;

/**
 * 
 * @author MM00334899
 * @use Util class to hold the common methods
 * 
 */
public class Utility implements OwnerConstants {

	private static final Logger LOG = Logger.getLogger(Utility.class);

	public Utility() {
		try {
			PropertiesLoader.getLog4j();

		} catch (Exception e) {
			LOG.info("In the PropertiesLoaderException Exception Utility class: "
					+ e.getMessage());

		}
	}

	/**
	 * @author x796314 To check whether the given input string is null
	 * @param value
	 * @return
	 */
	public static String isStringNull(String input) {

		String response = input;

		if (input == null || ("").equals(input.trim())) {
			LOG.info("inside is string null");
			response = EMPTY_STRING;
		}
		return response;
	}

	/**
	 * @author x796314 To check whether the given input string is not null
	 * @param value
	 * @return
	 */
	public static boolean isStringNotNullorNotEmpty(String value) {

		boolean isNotNullorNotEmpty = false;

		if (value != null) {
			if (!("").equals(value)) {
				isNotNullorNotEmpty = true;
			}
		}
		return isNotNullorNotEmpty;

	}

	/**
	 * @author x796314 To check whether the given input object is not null
	 * @param object
	 * @return
	 */
	public static boolean isObjectNotNullorNotEmpty(Object object) {
		boolean isObjectNotNullorNotEmpty = false;
		if (object != null) {
			isObjectNotNullorNotEmpty = true;
		}
		return isObjectNotNullorNotEmpty;
	}

	/**
	 * @author x796314
	 * @use To calculate months between two dates
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int monthsBetweenDates(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return 0;
		}

		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(startDate);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(endDate);

		int months = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR))
				* 12
				+ ((cal2.get(Calendar.MONTH)) - (cal1.get(Calendar.MONTH)))
				+ (cal2.get(Calendar.DAY_OF_MONTH) >= cal1
				.get(Calendar.DAY_OF_MONTH) ? 0 : -1);

		LOG.info("Start Year=" + cal1.get(Calendar.YEAR));
		LOG.info("End Year=" + cal2.get(Calendar.YEAR));
		LOG.info("Start Month=" + cal1.get(Calendar.MONTH));
		LOG.info("End Month=" + cal2.get(Calendar.MONTH));
		LOG.info("Start Day=" + cal1.get(Calendar.DAY_OF_MONTH));
		LOG.info("End Day=" + cal2.get(Calendar.DAY_OF_MONTH));

		LOG.info("Diff in months  is.." + months);

		return months;

	}

	/**
	 * @author x796314
	 * @use To convert the date to UTC format
	 * @param date
	 * @return
	 */
	public static String convertToUTCFormat(Date date) {
		String nowAsISO;

		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted
		// ‘Z’
		// to
		// indicate
		// UTC,
		// no
		// timezone
		// offset
		df.setTimeZone(tz);

		nowAsISO = df.format(date);

		LOG.info("UTC Format date=" + nowAsISO);
		return nowAsISO;

	}

	/**
	 * @author x796314
	 * @use To convert the String date to Util date
	 * @param date
	 * @return
	 */
	public static Date getDateFormatString(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

		Date dateParse = null;
		try {
			dateParse = sdf.parse(date);
		} catch (ParseException e) {

			Date dateWithoutSeconds = getDateFormatStringWthoutSeconds(date);
			return dateWithoutSeconds;

		}
		return dateParse;
	}

	/**
	 * @author x796314
	 * @use To convert the string date to util date without seconds
	 * @param date
	 * @return
	 */
	private static Date getDateFormatStringWthoutSeconds(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date dateParse = null;
		try {
			dateParse = sdf.parse(date);
		} catch (ParseException e) {

			LOG.info("insisde exception in getDateFormatStringWthoutSeconds"
					+ e);
			return new Timestamp(System.currentTimeMillis());

		}
		return dateParse;
	}

	/**
	 * @author x293386
	 * @use To convert the String date to Util date
	 * @param date
	 * @return
	 */
	public static Date getDateFormatStringWithSeconds(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date dateParse = null;
		try {
			dateParse = sdf.parse(date);
		} catch (ParseException e) {
			Date dateWithoutSeconds = getDateFormatStringWthoutSeconds(date);
			return dateWithoutSeconds;
		}
		return dateParse;
	}


	/** x055765 - get the default silhouette large image url based on bodyStyleName and ModelName
	 * @param largeImageUrl
	 * @return response
	 */
	public static String getLargeSilhouetteImageURL(String brand, String bodyStyleName, String modelName) {
		LOG.info("inside large image url bodyStyleName = " + bodyStyleName + "modelName = " + modelName + "brand = " + brand);

		String largeImageUrl = "";

		try{

			if(brand.equalsIgnoreCase("Nissan")) {

				if (Utility.isStringNotNullorNotEmpty(bodyStyleName)) {

					if(bodyStyleName.equalsIgnoreCase(OwnerConstants.CREW_CAB) || bodyStyleName.equalsIgnoreCase(OwnerConstants.KING_CAB)) {
						largeImageUrl = OwnerConstants.NISSAN_TRUCK_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.HATCHBACK)) {
						largeImageUrl = OwnerConstants.NISSAN_LEAF_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.COUPE)) {
						largeImageUrl = OwnerConstants.NISSAN_SMALL_SEDAN_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.WAGON_4_DOOR)) {
						largeImageUrl = OwnerConstants.NISSAN_CROSSOVER_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.MID_SUV) || bodyStyleName.equalsIgnoreCase(OwnerConstants.LARGE_SUV)) {
						largeImageUrl = OwnerConstants.NISSAN_SUV_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.NV_VEHICLES)) {
						largeImageUrl = OwnerConstants.NISSAN_VAN_LARGE;
					}else {
						largeImageUrl = "";
					}
				} else if (Utility.isStringNotNullorNotEmpty(modelName)) {
					if(modelName.equalsIgnoreCase(OwnerConstants.VERSA_NOTE) || modelName.equalsIgnoreCase(OwnerConstants.VERSA_SEDAN) || modelName.equalsIgnoreCase(OwnerConstants.ROADSTER_370z) ||
							modelName.equalsIgnoreCase(OwnerConstants.COUPE_370z) || modelName.equalsIgnoreCase(OwnerConstants.GT_R) || modelName.equalsIgnoreCase(OwnerConstants.COUPE_350z) ||
							modelName.equalsIgnoreCase(OwnerConstants.ROADSTER_350z) || modelName.equalsIgnoreCase(OwnerConstants.VERSA_HB) || modelName.equalsIgnoreCase(OwnerConstants.VERSA_SD)
							|| modelName.equalsIgnoreCase(OwnerConstants.SENTRA)) {
						largeImageUrl = OwnerConstants.NISSAN_SMALL_SEDAN_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.ALTIMA) || modelName.equalsIgnoreCase(OwnerConstants.MAXIMA) ||
							modelName.equalsIgnoreCase(OwnerConstants.ALTIMA_COUPE) || modelName.equalsIgnoreCase(OwnerConstants.ALTIMA_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.ALTIMA_SEDAN) 
							||modelName.toLowerCase().contains(OwnerConstants.ALTIMA.toLowerCase())) {
						largeImageUrl = OwnerConstants.NISSAN_SEDAN_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.LEAF)) {
						largeImageUrl = OwnerConstants.NISSAN_LEAF_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.KICKS) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE_SPORT) || 
							modelName.equalsIgnoreCase(OwnerConstants.MURANO) || modelName.equalsIgnoreCase(OwnerConstants.JUKE) || modelName.equalsIgnoreCase(OwnerConstants.MURANO_HYBRID) 
							|| modelName.equalsIgnoreCase(OwnerConstants.MURANO_CROSSCABRIOLET) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE_SELECT) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE_HYBRID) ||
							modelName.equalsIgnoreCase(OwnerConstants.ROGUE_SPORT)) {
						largeImageUrl = OwnerConstants.NISSAN_CROSSOVER_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.ARMADA) || modelName.equalsIgnoreCase(OwnerConstants.CUBE) 
							|| modelName.equalsIgnoreCase(OwnerConstants.XTERRA) || modelName.equalsIgnoreCase(OwnerConstants.PATHFINDER_HYBRID)) {
						largeImageUrl = OwnerConstants.NISSAN_SUV_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.PATHFINDER) || modelName.equalsIgnoreCase(OwnerConstants.FRONTIER) || modelName.equalsIgnoreCase(OwnerConstants.TITAN) || modelName.equalsIgnoreCase(OwnerConstants.TITAN_XD)) {
						largeImageUrl = OwnerConstants.NISSAN_TRUCK_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.NV_CARGO) || modelName.equalsIgnoreCase(OwnerConstants.NV_PASSENGER) || modelName.equalsIgnoreCase(OwnerConstants.NV200_COMPACT_CARGO)
							|| modelName.equalsIgnoreCase(OwnerConstants.NV_TAXI) || modelName.equalsIgnoreCase(OwnerConstants.NV1500) || modelName.equalsIgnoreCase(OwnerConstants.NV2500_HD) ||
							modelName.equalsIgnoreCase(OwnerConstants.NV3500_HD) || modelName.equalsIgnoreCase(OwnerConstants.NV200) || modelName.equalsIgnoreCase(OwnerConstants.QUEST) ||
							modelName.equalsIgnoreCase(OwnerConstants.TAXI)) {
						largeImageUrl = OwnerConstants.NISSAN_VAN_LARGE;
					}else {
						largeImageUrl = "";
					}
				}else {
					largeImageUrl = "";
				}

			}else if(brand.equalsIgnoreCase("Infiniti")) {

				if (Utility.isStringNotNullorNotEmpty(bodyStyleName)) {
					if(bodyStyleName.equalsIgnoreCase(OwnerConstants.HATCHBACK)) {
						largeImageUrl = OwnerConstants.INFINITI_SMALL_SEDAN_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.COUPE) || bodyStyleName.equalsIgnoreCase(OwnerConstants.SEDAN)) {
						largeImageUrl = OwnerConstants.INFINITI_SEDAN_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.MID_SUV)) {
						largeImageUrl = OwnerConstants.INFINITI_CROSSOVER_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.LARGE_SUV)) {
						largeImageUrl = OwnerConstants.INFINITI_SUV_LARGE;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.CONVERTIBLE)) {
						largeImageUrl = OwnerConstants.INFINITI_CONVERTIBLE_LARGE;
					}else {
						largeImageUrl = "";
					}
				} else if (Utility.isStringNotNullorNotEmpty(modelName)) {

					if(modelName.equalsIgnoreCase(OwnerConstants.Q50) || modelName.equalsIgnoreCase(OwnerConstants.Q60) || modelName.equalsIgnoreCase(OwnerConstants.Q70) ||
							modelName.equalsIgnoreCase(OwnerConstants.Q70L) || modelName.equalsIgnoreCase(OwnerConstants.G35_COUPE) || modelName.equalsIgnoreCase(OwnerConstants.G35_SEDAN) 
							|| modelName.equalsIgnoreCase(OwnerConstants.G37_SEDAN) || modelName.equalsIgnoreCase(OwnerConstants.G25_SEDAN) ||
							modelName.equalsIgnoreCase(OwnerConstants.M45) || modelName.equalsIgnoreCase(OwnerConstants.M35) || modelName.equalsIgnoreCase(OwnerConstants.M37) ||
							modelName.equalsIgnoreCase(OwnerConstants.M56) || modelName.equalsIgnoreCase(OwnerConstants.M35_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.Q40) 
							|| modelName.equalsIgnoreCase(OwnerConstants.Q50_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.Q60_COUPE) || modelName.equalsIgnoreCase(OwnerConstants.Q70_HYBRID)
							|| modelName.equalsIgnoreCase(OwnerConstants.G37_COUPE)) {
						largeImageUrl = OwnerConstants.INFINITI_SEDAN_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.QX60) || modelName.equalsIgnoreCase(OwnerConstants.EX35) || modelName.equalsIgnoreCase(OwnerConstants.EX37) ||
							modelName.equalsIgnoreCase(OwnerConstants.FX45) || modelName.equalsIgnoreCase(OwnerConstants.FX35) || modelName.equalsIgnoreCase(OwnerConstants.FX50) || 
							modelName.equalsIgnoreCase(OwnerConstants.FX37) || modelName.equalsIgnoreCase(OwnerConstants.JX35) || modelName.equalsIgnoreCase(OwnerConstants.QX50) || modelName.equalsIgnoreCase(OwnerConstants.QX55)
							|| modelName.equalsIgnoreCase(OwnerConstants.QX60_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.QX70) || modelName.equalsIgnoreCase(OwnerConstants.QX30)) {
						largeImageUrl = OwnerConstants.INFINITI_CROSSOVER_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.QX80) || modelName.equalsIgnoreCase(OwnerConstants.QX56)) {
						largeImageUrl = OwnerConstants.INFINITI_SUV_LARGE;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.G37) || modelName.equalsIgnoreCase(OwnerConstants.CONVERTIBLE) 
							|| modelName.equalsIgnoreCase(OwnerConstants.Q60_CONVERTIBLE)) {
						largeImageUrl = OwnerConstants.INFINITI_CONVERTIBLE_LARGE;
					}else {
						largeImageUrl = "";
					}
				}else {
					largeImageUrl = "";
				}
			}



		}catch(Exception e){
			LOG.info("Exception in getLargeSilhouetteImageURL :", e);

		}
		return largeImageUrl;
	}



	/** x055765 - get the default silhouette small image url based on bodyStyleName and ModelName
	 * @param smallImageUrl
	 * @return response
	 */
	public static String getSmallSilhouetteImageURL(String brand, String bodyStyleName, String modelName) {
		LOG.info("inside small image url bodyStyleName = " + bodyStyleName + "modelName = " + modelName + "brand = " + brand);
		String smallImageUrl = "";

		try{


			if(brand.equalsIgnoreCase("Nissan")) {

				if (Utility.isStringNotNullorNotEmpty(bodyStyleName)) {


					if(bodyStyleName.equalsIgnoreCase(OwnerConstants.CREW_CAB) || bodyStyleName.equalsIgnoreCase(OwnerConstants.KING_CAB)) {
						smallImageUrl = OwnerConstants.NISSAN_TRUCK_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.HATCHBACK)) {
						smallImageUrl = OwnerConstants.NISSAN_LEAF_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.COUPE)) {
						smallImageUrl = OwnerConstants.NISSAN_SMALL_SEDAN_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.WAGON_4_DOOR)) {
						smallImageUrl = OwnerConstants.NISSAN_CROSSOVER_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.MID_SUV) || bodyStyleName.equalsIgnoreCase(OwnerConstants.LARGE_SUV)) {
						smallImageUrl = OwnerConstants.NISSAN_SUV_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.NV_VEHICLES)) {
						smallImageUrl = OwnerConstants.NISSAN_VAN_SMALL;
					}else {
						smallImageUrl = "";
					}
				} else if (Utility.isStringNotNullorNotEmpty(modelName)) {


					if(modelName.equalsIgnoreCase(OwnerConstants.VERSA_NOTE) || modelName.equalsIgnoreCase(OwnerConstants.VERSA_SEDAN) || modelName.equalsIgnoreCase(OwnerConstants.ROADSTER_370z) ||
							modelName.equalsIgnoreCase(OwnerConstants.COUPE_370z) || modelName.equalsIgnoreCase(OwnerConstants.GT_R) || modelName.equalsIgnoreCase(OwnerConstants.COUPE_350z) ||
							modelName.equalsIgnoreCase(OwnerConstants.ROADSTER_350z) || modelName.equalsIgnoreCase(OwnerConstants.VERSA_HB) || modelName.equalsIgnoreCase(OwnerConstants.VERSA_SD)
							|| modelName.equalsIgnoreCase(OwnerConstants.SENTRA)) {
						smallImageUrl = OwnerConstants.NISSAN_SMALL_SEDAN_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.ALTIMA) || modelName.equalsIgnoreCase(OwnerConstants.MAXIMA) ||
							modelName.equalsIgnoreCase(OwnerConstants.ALTIMA_COUPE) || modelName.equalsIgnoreCase(OwnerConstants.ALTIMA_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.ALTIMA_SEDAN) 
							||modelName.toLowerCase().contains(OwnerConstants.ALTIMA.toLowerCase())) {
						smallImageUrl = OwnerConstants.NISSAN_SEDAN_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.LEAF)) {
						smallImageUrl = OwnerConstants.NISSAN_LEAF_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.KICKS) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE_SPORT) || 
							modelName.equalsIgnoreCase(OwnerConstants.MURANO) || modelName.equalsIgnoreCase(OwnerConstants.JUKE) || modelName.equalsIgnoreCase(OwnerConstants.MURANO_HYBRID) 
							|| modelName.equalsIgnoreCase(OwnerConstants.MURANO_CROSSCABRIOLET) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE_SELECT) || modelName.equalsIgnoreCase(OwnerConstants.ROGUE_HYBRID) ||
							modelName.equalsIgnoreCase(OwnerConstants.ROGUE_SPORT)) {
						smallImageUrl = OwnerConstants.NISSAN_CROSSOVER_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.ARMADA) || modelName.equalsIgnoreCase(OwnerConstants.CUBE) 
							|| modelName.equalsIgnoreCase(OwnerConstants.XTERRA) || modelName.equalsIgnoreCase(OwnerConstants.PATHFINDER_HYBRID)) {
						smallImageUrl = OwnerConstants.NISSAN_SUV_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.PATHFINDER) || modelName.equalsIgnoreCase(OwnerConstants.FRONTIER) || modelName.equalsIgnoreCase(OwnerConstants.TITAN) 
							|| modelName.equalsIgnoreCase(OwnerConstants.TITAN_XD)) {
						smallImageUrl = OwnerConstants.NISSAN_TRUCK_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.NV_CARGO) || modelName.equalsIgnoreCase(OwnerConstants.NV_PASSENGER) || modelName.equalsIgnoreCase(OwnerConstants.NV200_COMPACT_CARGO)
							|| modelName.equalsIgnoreCase(OwnerConstants.NV_TAXI) || modelName.equalsIgnoreCase(OwnerConstants.NV1500) || modelName.equalsIgnoreCase(OwnerConstants.NV2500_HD) ||
							modelName.equalsIgnoreCase(OwnerConstants.NV3500_HD) || modelName.equalsIgnoreCase(OwnerConstants.NV200) || modelName.equalsIgnoreCase(OwnerConstants.QUEST) ||
							modelName.equalsIgnoreCase(OwnerConstants.TAXI)) {
						smallImageUrl = OwnerConstants.NISSAN_VAN_SMALL;
					}else {
						smallImageUrl = "";
					}
				}else {
					smallImageUrl = "";
				}
			}else if(brand.equalsIgnoreCase("Infiniti")) {

				if (Utility.isStringNotNullorNotEmpty(bodyStyleName)) {

					if(bodyStyleName.equalsIgnoreCase(OwnerConstants.HATCHBACK)) {
						smallImageUrl = OwnerConstants.INFINITI_SMALL_SEDAN_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.COUPE) || bodyStyleName.equalsIgnoreCase(OwnerConstants.SEDAN)) {
						smallImageUrl = OwnerConstants.INFINITI_SEDAN_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.MID_SUV)) {
						smallImageUrl = OwnerConstants.INFINITI_CROSSOVER_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.LARGE_SUV)) {
						smallImageUrl = OwnerConstants.INFINITI_SUV_SMALL;
					}else if(bodyStyleName.equalsIgnoreCase(OwnerConstants.CONVERTIBLE)) {
						smallImageUrl = OwnerConstants.INFINITI_CONVERTIBLE_SMALL;
					}else {
						smallImageUrl = "";
					}
				} 
				else if (Utility.isStringNotNullorNotEmpty(modelName)){

					if(modelName.equalsIgnoreCase(OwnerConstants.Q50) || modelName.equalsIgnoreCase(OwnerConstants.Q60) || modelName.equalsIgnoreCase(OwnerConstants.Q70) ||
							modelName.equalsIgnoreCase(OwnerConstants.Q70L) || modelName.equalsIgnoreCase(OwnerConstants.G35_COUPE) || modelName.equalsIgnoreCase(OwnerConstants.G35_SEDAN) 
							|| modelName.equalsIgnoreCase(OwnerConstants.G37_SEDAN) || modelName.equalsIgnoreCase(OwnerConstants.G25_SEDAN) ||
							modelName.equalsIgnoreCase(OwnerConstants.M45) || modelName.equalsIgnoreCase(OwnerConstants.M35) || modelName.equalsIgnoreCase(OwnerConstants.M37) ||
							modelName.equalsIgnoreCase(OwnerConstants.M56) || modelName.equalsIgnoreCase(OwnerConstants.M35_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.Q40) 
							|| modelName.equalsIgnoreCase(OwnerConstants.Q50_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.Q60_COUPE) || modelName.equalsIgnoreCase(OwnerConstants.Q70_HYBRID)
							|| modelName.equalsIgnoreCase(OwnerConstants.G37_COUPE)) {
						smallImageUrl = OwnerConstants.INFINITI_SEDAN_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.QX60) || modelName.equalsIgnoreCase(OwnerConstants.EX35) || modelName.equalsIgnoreCase(OwnerConstants.EX37) ||
							modelName.equalsIgnoreCase(OwnerConstants.FX45) || modelName.equalsIgnoreCase(OwnerConstants.FX35) || modelName.equalsIgnoreCase(OwnerConstants.FX50) || 
							modelName.equalsIgnoreCase(OwnerConstants.FX37) || modelName.equalsIgnoreCase(OwnerConstants.JX35) || modelName.equalsIgnoreCase(OwnerConstants.QX50)
							|| modelName.equalsIgnoreCase(OwnerConstants.QX60_HYBRID) || modelName.equalsIgnoreCase(OwnerConstants.QX70) || modelName.equalsIgnoreCase(OwnerConstants.QX30)) {
						smallImageUrl = OwnerConstants.INFINITI_CROSSOVER_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.QX80) || modelName.equalsIgnoreCase(OwnerConstants.QX56)) {
						smallImageUrl = OwnerConstants.INFINITI_SUV_SMALL;
					}else if(modelName.equalsIgnoreCase(OwnerConstants.G37) || modelName.equalsIgnoreCase(OwnerConstants.CONVERTIBLE) 
							|| modelName.equalsIgnoreCase(OwnerConstants.Q60_CONVERTIBLE)) {
						smallImageUrl = OwnerConstants.INFINITI_CONVERTIBLE_SMALL;
					}else {
						smallImageUrl = "";
					}
				}else {
					smallImageUrl = "";
				}
			}



		}catch(Exception e){
			LOG.info("Exception in getSmallSilhouetteImageURL :" ,e);

		}
		return smallImageUrl;
	}

	public static String getBrand(String brand) {
		if(brand.equalsIgnoreCase(BRAND_NISSAN)){
			brand = NISSAN;
		} else if(brand.equalsIgnoreCase(BRAND_INFINITI)){
			brand = INFINITI;
		} else {
			brand = "undefined";
		}
		return brand;
	}

	public static String getUsernameFromJWT(String jwt) {
		String userName = "";
		try {
			String[] base64UrlEncodedSegments = jwt.split("\\.");
			Base64.Decoder decoder = Base64.getDecoder();
			String claims = new String(decoder.decode(base64UrlEncodedSegments[1]));
			if (claims != null) {
				JSONObject obj = new JSONObject(claims);
				LOG.info(obj.toString());
				userName = obj.getString("http://wso2.org/claims/enduser");
				LOG.info("Inside JWT header: " + userName);
				userName = userName.toLowerCase();
				LOG.info("UserName after converting into lowercase : "+userName);
				if (userName.endsWith("@carbon.super")) {
					userName = userName.replace("@carbon.super", "");
					userName = userName.toLowerCase();
					LOG.info("UserName after replacing is = " + userName);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.info("ArrayIndexOutOfBoundsException: ", e);
		} catch (JSONException e) {
			LOG.info("JSONException: ", e);
		}
		return userName;
	}

	public static String faultResponse(String code, String message, String description) 
			throws JSONException{
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFinalObj = new JSONObject();
		jsonObj.accumulate(CODE,
				code);
		jsonObj.accumulate(MESSAGE,
				message);
		jsonObj.accumulate(DESCRIPTION,
				description);
		return jsonFinalObj.accumulate("fault", jsonObj).toString();
	}

	public static String showTimeDiff(Date invokedDate) {
		Date currentDate = new Date();
		long timeDifference = currentDate.getTime() - invokedDate.getTime();
		String timeDiffInString = String.valueOf(timeDifference);
		return timeDiffInString;
	}

	public static String getMackImageUrl(String modelYear, String exteriorColorCode, String eimCode, 
			String modelName, String width, String height, String view, String background, String shadow, String type) {
		String url = "";
		String baseUrl = "https://ms-prd.use.mediaserver.heliosnissan.net/iris/iris";
		String brand = "nisnna";
		try {
			JSONObject j = new JSONObject(getModelImageValues(modelName.toLowerCase(), modelYear.toLowerCase(), view));
			String[] saOptions = eimCode.split("");
			url = baseUrl + "?pov=" + view.toUpperCase() + "&resp=" + type.toLowerCase() + "&bkgnd=" + background.toLowerCase() + "&w=" + j.getString("w") + "&h=" + j.getString("h") + "&x=" + j.getString("x") + "&y=" + j.getString("y") + "&height=" + height + "&width=" + width + "&vehicle=8_" + saOptions[7] + saOptions[8] + saOptions[9] + "&paint=" + exteriorColorCode + "&brand="+ brand + "&sa=";
			for (int i=0; i<saOptions.length; i++) {
				int urlIndex = i + 1;
				switch(i) {
				case 2:
					// 2nd and 3rd position denotes the Engine
					url += urlIndex + "_" + saOptions[i] + saOptions[2] + ",";
					break;
				case 3:
				case 8:
				case 9:
				case 10:
					// skip 3rd position (see case 2)
					// skip positions 8, 9, and 10 (the Model code)
					break;
				default:
					if (!saOptions[i].equalsIgnoreCase("-")) {
						url += urlIndex + "_" + saOptions[i] + ",";
					}
					break;
				}
			}
			url += "SHADOW_" + shadow.toUpperCase() + ",PI_ON,PE_ON" + "," + modelYear;
			LOG.info("Mackevision URL :::::::::::::: " +url);
		} catch (Exception e) {
			url = "";
			LOG.info("Exception: " +e);
		}

		return url;
	}

	public static String getModelImageValues(String modelName, String modelYear, String view) {
		String modelValues = "";
		//Nissan
		//Crossovers & SUVs
		if(!view.equalsIgnoreCase("E01")) {
			return modelValues = "{\"w\": \"7667\",\"h\": \"7667\",\"x\": \"950\",\"y\": \"0\"}";
		}
		if (modelName.contains("rogue spt")) {
			return modelValues = "{\"w\": \"5800\",\"h\": \"2900\",\"x\": \"1800\",\"y\": \"2350\"}";
		}
		else if (modelName.contains("murano")) {
			return modelValues = "{\"w\": \"5400\",\"h\": \"2700\",\"x\": \"2075\",\"y\": \"2725\"}";
		}
		else if (modelName.contains("pathfinder")||modelName.contains("rogue")||modelName.contains("kicks")||modelName.contains("armada")) {
			return modelValues = "{\"w\": \"5400\",\"h\": \"2700\",\"x\": \"2000\",\"y\": \"2550\"}";
		}

		//Cars
		else if (modelName.contains("sentra") ) {
			return modelValues = "{\"w\": \"5500\",\"h\": \"2750\",\"x\": \"1900\",\"y\": \"2450\"}";
		}

		else if (modelName.contains("versa") || modelName.contains("leaf")) {
			return modelValues = "{\"w\": \"5300\",\"h\": \"2650\",\"x\": \"1950\",\"y\": \"2750\"}";
		}
		
		else if (modelName.contains("alt") && modelYear.equals("2020")) {
			return modelValues = "{\"w\": \"5500\",\"h\": \"2750\",\"x\": \"1900\",\"y\": \"3000\"}";
		}

		else if (modelName.contains("alt") || modelName.contains("maxima")) {
			return modelValues = "{\"w\": \"5500\",\"h\": \"2750\",\"x\": \"1900\",\"y\": \"2350\"}";
		}

		// Sports Cars
		else if (modelYear.equals("2021") && (modelName.contains("gt-r")) ) {
			return modelValues = "{\"w\": \"5500\",\"h\": \"2750\",\"x\": \"1900\",\"y\": \"2500\"}";
		}
		else if (modelYear.equals("2020") && (modelName.contains("gt-r")) ) {
			return modelValues = "{\"w\": \"5500\",\"h\": \"2750\",\"x\": \"1900\",\"y\": \"3000\"}";
		}
		else if (modelName.contains("z") ) {
			return modelValues = "{\"w\": \"5000\",\"h\": \"2500\",\"x\": \"2225\",\"y\": \"3075\"}";
		}

		//Trucks
		else if (modelName.contains("ttn") || modelName.contains("txd") ) {
			return modelValues = "{\"w\": \"6000\",\"h\": \"3000\",\"x\": \"1900\",\"y\": \"2200\"}";
		}

		else if (modelName.contains("frt") ) {
			return modelValues = "{\"w\": \"5600\",\"h\": \"2800\",\"x\": \"1900\",\"y\": \"2450\"}";
		}

		//Vans
		else if (modelName.contains("nv 1500") || modelName.contains("nv pass") ) {
			return modelValues = "{\"w\": \"5000\",\"h\": \"2500\",\"x\": \"2350\",\"y\": \"2850\"}";
		}

		else if (modelName.contains("nv") ) {
			return modelValues = "{\"w\": \"5800\",\"h\": \"2900\",\"x\": \"2000\",\"y\": \"2275\"}";
		}

		else if (modelName.contains("nv200") ) {
			return modelValues = "{\"w\": \"5600\",\"h\": \"2800\",\"x\": \"2000\",\"y\": \"2400\"}";
		}

		//INFINITI
		//Sedans & Coupes
		else if (modelName.contains("q50") || modelName.contains("q60") ) {
			return modelValues = "{\"w\": \"5300\",\"h\": \"2650\",\"x\": \"2050\",\"y\": \"2750\"}";
		}

		//Crossovers & SUVs
		else if (modelName.contains("qx50") || modelName.contains("qx80") ) {
			return modelValues = "{\"w\": \"5400\",\"h\": \"2700\",\"x\": \"2000\",\"y\": \"2650\"}";
		}

		else if (modelName.contains("qx55") ) {
			return modelValues = "{\"w\": \"5400\",\"h\": \"2700\",\"x\": \"2000\",\"y\": \"3100\"}";
		}

		else {
			modelValues = "{\"w\": \"7667\",\"h\": \"7667\",\"x\": \"950\",\"y\": \"0\"}";
		}
		return modelValues;
	}
}
