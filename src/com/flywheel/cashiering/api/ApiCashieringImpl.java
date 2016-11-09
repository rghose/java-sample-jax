package com.flywheel.cashiering.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jws.WebService;

import com.flywheel.cashiering.api.AbeBackend;

//Service Implementation
@WebService(endpointInterface = "com.flywheel.cashiering.api.ApiCashiering")
public class ApiCashieringImpl implements ApiCashiering  {
	
	private static final Logger LOGGER = Logger.getLogger( "InfoLogging" );

	private String getConfigurationOptions() {
		LOGGER.info("starting getConfig...");

		Properties properties = new Properties();
		try {
			File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
			File propertyFile = new File( catalinaBase, "conf/configs.prop" );
			InputStream inputStream = new FileInputStream( propertyFile );
			properties.load(inputStream);
		} catch(IOException ex) {
	      LOGGER.warning("exception loading properties file: " + ex.getMessage());
	    }
	    return properties.getProperty("url_get_trips");
	}

	@Override
	public String getFlywheelTrips(String name) {
		String retVal = getConfigurationOptions();
		LOGGER.info("Logging an INFO-level message: " +  retVal);
		AbeBackend a = new AbeBackend(retVal);
		try {
			a.getTripsFromAbe();
		} catch(Exception ex) {
			LOGGER.warning("exception in fetching trips from backend: " + ex.getMessage());
		}
		return "Nomoshkar Prithibi JAX-WS " + name + " URL: " + retVal;
	}
}
