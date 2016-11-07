package com.flywheel.cashiering.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.flywheel.trips.TripsProto.TripRequest;
import com.flywheel.trips.TripsProto.TripsResponse;

public class AbeBackend {
	private static final Logger LOGGER = Logger.getLogger( "InfoLogging" );
	
	private String backendUrl;
	
	public AbeBackend(String url) {
		this.backendUrl = url;
	}

	@SuppressWarnings("unused")
	public void getTripsFromAbe() throws Exception {
		String url = backendUrl;

		URL obj = new URL(url);
		LOGGER.info(obj.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-protobuf");

		con.setDoOutput(true);
		TripRequest.Builder request = TripRequest.newBuilder();
		request.setUserId("rahul@flywheel.com");
		request.setPassword("passw0rd");
		request.setFleetId(1);
		long startTimestamp = (System.currentTimeMillis() / 1000L) - 28*24*60*60;
		long endTimestamp = System.currentTimeMillis() / 1000L;
		request.setStartDate(startTimestamp);
		request.setEndDate(endTimestamp);

		OutputStream output = con.getOutputStream();
		request.build().writeTo(output);
		output.close();
		con.connect();

		int responseCode = con.getResponseCode();
		String msg = con.getResponseMessage();
		
		LOGGER.info("\nSending 'POST' request to URL : " + url);
		LOGGER.info("Response Code : " + responseCode + "\n" + msg);

		TripsResponse trips = TripsResponse.parseFrom(con.getInputStream());

		//print result
		LOGGER.info("Total number of trips: " + trips.getCount());
	}
}
