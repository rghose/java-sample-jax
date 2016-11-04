package com.flywheel.cashiering.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jws.WebService;


//Service Implementation
@WebService(endpointInterface = "com.flywheel.cashiering.api.ApiCashiering")
public class ApiCashieringImpl implements ApiCashiering {
	private static final Logger LOGGER = Logger.getLogger( "InfoLogging" );
	private String getConfigurationOptions() {
		Properties properties = new Properties();
		File configFile = new File( "/mnt/disk/test.prop");
	    try {
	       InputStream is = new FileInputStream(configFile);
	       properties.load(is);
	    } catch(IOException ex) {
	      System.out.println("exception loading properties file");
	    }
	    return properties.getProperty("url_get_trips");
	}

	@Override
	public String getHelloWorldAsString(String name) {
		String retVal = getConfigurationOptions();
		LOGGER.info("Logging an INFO-level message: " +  retVal);
		return "Nomoshkar Prithibi JAX-WS " + name + " URL: " + retVal;
	}
}