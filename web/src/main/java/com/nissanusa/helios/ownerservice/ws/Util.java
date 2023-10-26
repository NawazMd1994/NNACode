package com.nissanusa.helios.ownerservice.ws;

/*
 *last modified date 01-06-2016 by x178099

 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.jboss.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.nissanusa.helios.ownerservice.util.OwnerConstants;
import com.nissanusa.helios.ownerservice.util.PropertiesLoader;
import com.nissanusa.helios.ownerservice.util.Utility;

/**
 * 
 * @author x178099
 * @use will hold the common methods used in WS class
 *
 */
public class Util implements OwnerConstants {
	private static Cipher cipher;
    private static final Logger LOG = Logger.getLogger(Util.class);

    public Util() {
        try {
            PropertiesLoader.getLog4j();

        } catch (Exception e) {
            LOG.info("In the PropertiesLoaderException Exception Util: "
                    + e.getMessage());

        }
    }

    /**
     * @author x178099
     * @use To check if brand is null
     * @param brand
     * @param jsonObj
     * @param jsonFinalObj
     * @return
     */
    public static boolean isBrandNull(String brand, JSONObject jsonObj,
            JSONObject jsonFinalObj) {
        LOG.info("Inside brand check for  null");
        boolean response = true;
        if (brand == null) {

            LOG.info("Inside brand is null");
            try {

                jsonObj.accumulate(CODE, VEHICLE_INVALID_BRAND_CODE);
                jsonObj.accumulate(MESSAGE, VALIDATION_FAILED_MESSAGE);
                jsonObj.accumulate(DESCRIPTION,
                        VEHICLE_INVALID_BRAND_DESCRIPTION);
                jsonFinalObj.accumulate("fault", jsonObj);
                response = false;
            } catch (JSONException e) {
                LOG.info("JSONException in isBrandNull", e);
            }
        }

        return response;
    }

    /**
     * @author x178099
     * @use
     * @param jwt
     * @return username
     */
    public static String getUsernameFromJWT(String jwt) {
       // String header = "";
        String claims = "";
        String userName = "";
        try {
            String[] base64UrlEncodedSegments = jwt.split("\\.");
           

            String base64UrlEncodedHeader = base64UrlEncodedSegments[0];
            String base64UrlEncodedClaims = base64UrlEncodedSegments[1];

            byte[] decodeHeader = DatatypeConverter
                    .parseBase64Binary(base64UrlEncodedHeader);
            byte[] decodeClaims = DatatypeConverter
                    .parseBase64Binary(base64UrlEncodedClaims);

           // header = new String(decodeHeader, "UTF-8");

            claims = new String(decodeClaims, "UTF-8");
            

            if (Utility.isStringNotNullorNotEmpty(claims)) {

                JSONObject obj = new JSONObject(claims);
                
                userName = obj.getString("http://wso2.org/claims/enduser");
                
                if (userName.endsWith("@carbon.super")) {
                    userName = userName.replace("@carbon.super", "");
                    
                }

            }
        } catch (UnsupportedEncodingException e) {
            LOG.info("UnsupportedEncodingException: ", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.info("ArrayIndexOutOfBoundsException: ", e);
        } catch (JSONException e) {
            LOG.info("JSONException: ", e);
        }
        LOG.info("UserName from JWT = " + userName);
        return userName;
    }

    /**
     * @author x178099
     * @use To set general error code,message and description on JSON object
     * @param jsonObj
     * @param jsonFinalObj
     * @return
     */
    public static String setFaultDataToJSON(JSONObject jsonObj,
            JSONObject jsonFinalObj, String code, String message,
            String description) {
        try {

            jsonObj.accumulate(CODE, code);
            jsonObj.accumulate(MESSAGE, message);
            jsonObj.accumulate(DESCRIPTION, description);
            jsonFinalObj.accumulate("fault", jsonObj);
        } catch (JSONException e) {
            LOG.error("JSONException setFaultDataToJSON ", e);
        }

        return jsonFinalObj.toString();
    }
    
    /**
     * @author x061538 -- AAS complete profile solution
     * @use To set general error code,message and description on JSON object
     * @param jsonObj
     * @param jsonFinalObj
     * @return
     */
    public static String setFaultDataToJSONComProfile(JSONObject jsonObj,
            JSONObject jsonFinalObj, String code, String message,
            String description, String brand) {
        try {
        	LOG.info("Inside setFaultDataToJSONComProfile page"+brand);
            jsonObj.accumulate(CODE, code);
            jsonObj.accumulate(MESSAGE, message);
            jsonObj.accumulate(DESCRIPTION, description);
            
            if(brand!=null && brand.equalsIgnoreCase("N")){
            	jsonObj.accumulate(LINK, COMPLETE_PROFILE_NISSAN);
            }else if(brand!=null && brand.equalsIgnoreCase("I")){
            	jsonObj.accumulate(LINK, COMPLETE_PROFILE_INFINITI);
            }else{
            	jsonObj.accumulate(LINK, "");
            }
            
            
            jsonFinalObj.accumulate("fault", jsonObj);
            LOG.info("FINAL OBJECT :::"+jsonFinalObj);
        } catch (JSONException e) {
            LOG.error("JSONException setFaultDataToJSON ", e);
        }

        return jsonFinalObj.toString();
    }


    
    public static String setUploadDocumentResponseDataToJSON(JSONObject jsonObj,
             int code, String message
            ) {
        try {

            jsonObj.accumulate(CODE, code);
            jsonObj.accumulate(MESSAGE, message);
            
        } catch (JSONException e) {
            LOG.error("JSONException setDataToJSON ", e);
        }

        return jsonObj.toString();
    }


    /**
     * @author 104026
     * @use To set success response code and message on JSON object
     * @param jsonObj
     * @param jsonFinalObj
     * @return
     */
    public static String setSuccessDataToJSON(JSONObject jsonFinalObj, String code, String message){
        try {

        	jsonFinalObj.accumulate(CODE, code);
        	jsonFinalObj.accumulate(MESSAGE, message);
        } catch (JSONException e) {
            LOG.error("JSONException setFaultDataToJSON ", e);
        }

        return jsonFinalObj.toString();
    }
    
	/**
	 * @author x566325
	 * @use to sort the vehicles list in the JSON response for getUser and Update user
	 *   in descending order by the vehicle created time stamp
	 * @param JSONArray - Vehicles Owned JSON Array object
	 * @return JSONArray
	 */
	
	public static JSONArray sort(JSONArray jsonArr) throws JSONException {
		JSONArray sortedJsonArray = new JSONArray();
		JSONArray sortedJsonArrayFinal = new JSONArray();

		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < jsonArr.length(); i++) {
			jsonValues.add(jsonArr.getJSONObject(i));
		}
		Collections.sort(jsonValues, new Comparator<JSONObject>() {

			private static final String KEY_NAME = "date";

			@Override
			public int compare(JSONObject a, JSONObject b) {
				Date valA = new Date();
				Date valB = new Date();

				try {
					valA = (Date) a.get(KEY_NAME);
					valB = (Date) b.get(KEY_NAME);
				} catch (JSONException e) {
					LOG.error("JSONException in sort", e);
				}
				return valB.compareTo(valA);
			}
		});

		for (int i = 0; i < jsonArr.length(); i++) {

			sortedJsonArray.put(jsonValues.get(i));
		}
		JSONObject objects = new JSONObject();

		LOG.info("------sortedJsonArray length--------"
				+ sortedJsonArray.length());
		for (int i = 0; i < sortedJsonArray.length(); i++) {
			objects = sortedJsonArray.getJSONObject(i);
			objects.remove("date");
			sortedJsonArrayFinal.put(objects);

		}
		LOG.info("------sortedJsonArray length--------"
				+ sortedJsonArrayFinal.length());
		return sortedJsonArray;
	}
	
	/**
	 * @author x566325
	 * @use encoding the vin for sso redirection
	 * @param String vin, String encryptedkey
	 * @return String encryptedText
	 */
	public static String getAESEncryptedVin(String vin, String encryptedKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		String encryptedText = "";
		byte[] key = (encryptedKey).getBytes("UTF-8");
		key = Arrays.copyOf(key, 32);
		String s = new String(key);
		LOG.info("Validating the byte : " + s.toString());
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		cipher = Cipher.getInstance("AES");

		
		encryptedText = encrypt(vin, secretKey);		
		encryptedText = encryptedText.replaceAll("(\\r|\\n)", ""); 
		String decryptedText = decrypt(encryptedText, secretKey);
		LOG.info("decryptedText: " + decryptedText);
		return encryptedText;
	}
	
	/**
	 * @author x566325
	 * @use encoding the vin for sso redirection
	 * @param String vin, String AES secretKey
	 * @return String encryptedText
	 */
	 public static String encrypt(String plainText, SecretKeySpec secretKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		String encryptedText = "";
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		encryptedText = Base64.encodeBase64String(encryptedByte);
		LOG.info("encryptedText: " + encryptedText);
		return encryptedText;
	}
	
	 /**
		 * @author x566325
		 * @use decoding the vin for sso redirection
		 * @param String encryptedText, String AES secretKey
		 * @return String decryptedText
		 */ 
	public static String decrypt(String encryptedText,SecretKeySpec secretKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{		
		byte[] encryptedTextByte = Base64.decodeBase64(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		LOG.info("decryptedText: " + decryptedText);
		return decryptedText;
	}

}
