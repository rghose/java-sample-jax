package com.flywheel.cashiering.api;

import java.util.logging.Logger;

import com.flywheel.trips.TripsProto.TripRequest;
import com.flywheel.trips.TripsProto.TripsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.protobuf.ProtoConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class AbeBackend {

	public interface AbeBackendService {
		@Headers({"Accept: application/x-protobuf", "Content-Type: application/x-protobuf"})
		@POST("/trips/proto")
		Call<TripsResponse> getTrips(@Body TripRequest trip_request);
	}

	private static final Logger LOGGER = Logger.getLogger( "InfoLogging" );
	
	private String backendUrl;
	
	public AbeBackend(String url) {
		this.backendUrl = url;
	}

	public void getTripsFromAbe() throws Exception {

		TripRequest.Builder request = TripRequest.newBuilder();
		request.setUserId("aritra@flywheel.com");
		request.setPassword("aritra");
		request.setFleetId(1);
		long startTimestamp = (System.currentTimeMillis() / 1000L) - 28*24*60*60;
		long endTimestamp = System.currentTimeMillis() / 1000L;
		request.setStartDate(startTimestamp);
		request.setEndDate(endTimestamp);

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(backendUrl)
				.addConverterFactory(ProtoConverterFactory.create())
				.build();
		AbeBackendService a = retrofit.create(AbeBackendService.class);
		Call<TripsResponse> t = a.getTrips(request.build());
		TripsResponse trips = t.execute().body();

		LOGGER.info("Total number of trips: " + trips.getCount());
	}
}
