package com.projectx.data.fixtures.async;

import static com.projectx.data.fixtures.request.FreightRequestByVendorDataFixtures.*;

import com.google.gson.Gson;
import com.projectx.data.domain.async.RetriggerDetails;

public class RetriggerDetailsDataFixtures {

	private static Gson gson=new Gson();
	
	
	public static RetriggerDetails standardRetriggerDetails()
	{
		return new RetriggerDetails("vendorRequest", gson.toJson(standardFreightRequestByVendorDTO()));
	}
	
	public static String standardJsonRetrigger(RetriggerDetails retriggerDetails)
	{
		return gson.toJson(standardRetriggerDetails());
	}
	
	
	
}
