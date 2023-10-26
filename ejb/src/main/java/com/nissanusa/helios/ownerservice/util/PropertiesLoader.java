package com.nissanusa.helios.ownerservice.util;

import java.io.File;
import org.apache.log4j.Logger;


import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author x796314
 * @use Class will hold the services for retrieving the properties from owner
 *      property file and log file
 *
 */
public class PropertiesLoader implements OwnerConstants {

	private static String log4jInitialized = null;
	static final String PROPERTIES_FILEPATH = "/data02/applicationConfig/helios/ownerService";
	static final String LOG4J_PROPERTIES = "Log4j_OwnerService.properties";
	private static final Logger LOG = Logger.getLogger(OwnerConstants.HELIOS_OWNERSERVICES_LOG);

	public static void getLog4j() {
		String sLog4jPath = null;
		if (log4jInitialized == null ) {
			try {

				sLog4jPath = PROPERTIES_FILEPATH;
				PropertyConfigurator.configure(sLog4jPath + File.separator
						+ LOG4J_PROPERTIES);

				log4jInitialized = "YES";
			} catch (Exception e) {
				LOG.error("Exception in Properties Loader",e);
			}

		}

	}

}
