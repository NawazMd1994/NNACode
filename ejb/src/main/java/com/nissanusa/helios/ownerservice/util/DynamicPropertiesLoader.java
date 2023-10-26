package com.nissanusa.helios.ownerservice.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

public class DynamicPropertiesLoader implements OwnerConstants {

	private static final Logger LOG = Logger
			.getLogger(DynamicPropertiesLoader.class);
	private static DynamicProperties dProperties;

	public DynamicPropertiesLoader() {
		try {
			PropertiesLoader.getLog4j();

		} catch (Exception e) {
			LOG.error("In the PropertiesLoaderException Exception Utility class: "
					+ e.getMessage());

		}
	}

	public static DynamicProperties getDynaProp() throws Exception {
		try {
			if (dProperties == null) {
				dProperties = DynamicProperties.getInstance();

				dProperties.loadProperties(System
						.getProperty(PROPERTIES_FILE_PATH)
						+ File.separator
						+ OWNERSERVICES_PROPERTIES_FILE);
			} else {
				LOG.info(">>> else part");
			}

		} catch (FileNotFoundException e) {
			LOG.info(">>> Inside PropertiesLoader FileNotFoundException catch block"
					+ e);

		} catch (IOException e) {
			LOG.info(">>> Inside PropertiesLoader IOException catch block" + e);

		} catch (Exception e) {
			LOG.info(">>> Inside DynamicPropertiesLoader Exception catch block"
					+ e);

		}
		return dProperties;
	}
}
