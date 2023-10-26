package com.nissanusa.helios.ownerservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class DynamicProperties {

	private static final Logger LOG = Logger.getLogger(Utility.class);
	
	private static DynamicProperties dPropObj = null;
	// Holds the Property Object
	private Properties propObj = null;
	// Variable for last modified time
	long lModified = 0;
	// Variable for current modified time
	long cModified = 0;
	
	//private static DynamicProperties dProperties = null;
	
	
	
	/**
	 * Public method to create instance of DynaProperties
	 */
	public static DynamicProperties getInstance() {
		if (dPropObj == null) {
			return dPropObj = new DynamicProperties();
		} else {
			return dPropObj;
		}
	}

	/**
	 * Public Method to load properties file. It will return true, if the
	 * properties file is actually loaded.
	 * 
	 * @param strPropertyPath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean loadProperties(String strPropertyPath)
			throws FileNotFoundException, IOException {
		boolean bModificationFlag = false;
		FileInputStream fisPropFile = null;
		try {
			LOG.info("Property file path "+strPropertyPath);
			File fPropFile = new File(strPropertyPath);
			fisPropFile = new FileInputStream(fPropFile);
			cModified = fPropFile.lastModified();
			if (cModified != lModified) {
				// Intantiate the Property Object
				propObj = new Properties();
				// Load the Property File
				propObj.load(fisPropFile);
				// Set last Modified Time To Current Modified Time
				lModified = cModified;
				LOG.info(">>> Property File Freshly Loaded");
				bModificationFlag = true;
			}
		} finally {
			if (!(null==fisPropFile)){
			fisPropFile.close();
		}
		}
		return bModificationFlag;
	}

	/**
	 * Public Method to get value of property
	 * @param strPropKey
	 * @return
	 */
	public String getPropertyValue(String strPropKey) {
		LOG.info("Properties Key is "+strPropKey);
		String strPropValue = propObj.getProperty(strPropKey);
		if (strPropValue != null) {
			return strPropValue.trim();
		} else {
			LOG.info(">>> There is no property for the key : "
					+ strPropKey);
			return "";
		}
	}
}
