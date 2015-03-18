package com.projectx.data.fixtures.request;

import static com.projectx.data.fixtures.completeregister.VehicleDetailsDataFixtures.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.VehicleDetails;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.request.UpdateReservationStatus;

public class FreightRequestByVendorDataFixtures {
	
	
public static VehicleDetails REQ_VEHICLE=standardVehicleDetails();
	
	public static Integer REQ_SOURCE=411045;
	
	public static Integer REQ_DESTINATION=413102;
	
	public static Integer REQ_DESTINATION_UPDATED=413133;
	
	public static Long REQ_DRIVER=213L;
	
	public  static Date REQ_AVAIL_DATE=new Date();
	
	public static String REQ_AVAIL_TIME="6:00PM";
	
	public static String REQ_AVAIL_TIME_UPDATED="8:00PM";
	
	public static Integer REQ_PICK_UP_RANGE=10;
	
	public static Integer REQ_PICK_UP_RANGE_UPDATED=0;
	
	public static Date REQ_DATE=new Date();
	
	public static String REQ_UPDATED_BY="CUST_ONLINE";
	
	public static Long REQ_VENDOR_ID=213L;
	
	public static String REQ_STATUS="NEW";
	
	private static Gson gson=new Gson();
	
	public static FreightRequestByVendorDTO standardFreightRequestByVendorDTO()
	{
		return new FreightRequestByVendorDTO(1L, standardVehicleDetails().getRegistrationNumber(),
				REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
	}
	
	public static FreightRequestByVendor standardTestRequest()
	{
		
		
		return new FreightRequestByVendor(1L,standardVehicleDetails(), REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static FreightRequestByVendor standardTestRequestOpen()
	{
		
		
		return new FreightRequestByVendor(100L,standardVehicleDetailsOpen(), REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static FreightRequestByVendor standardTestRequestOpen307()
	{
		
		
		return new FreightRequestByVendor(100L,standardVehicleDetailsOpen307(), REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static FreightRequestByVendor standardTestRequestClosed()
	{
		
		
		return new FreightRequestByVendor(120L,standardVehicleDetailsClosed(), REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static FreightRequestByVendor standardTestRequestFlexible()
	{
		
		
		return new FreightRequestByVendor(110L,standardVehicleDetailsFlexible(), REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static FreightRequestByVendor standardTestRequestUpdated()
	{
		return new FreightRequestByVendor(1L,standardVehicleDetails(), REQ_SOURCE, REQ_DESTINATION_UPDATED, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME_UPDATED,
				REQ_PICK_UP_RANGE_UPDATED,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static String stanardJsonTestRequest(FreightRequestByVendor freightRequestByVendor)
	{
		System.out.println(gson.toJson(freightRequestByVendor));
		
		return gson.toJson(freightRequestByVendor);
	}

	
	
	public static String stanardJsonFreightRequestByVendorDTO(FreightRequestByVendorDTO freightRequestByVendor)
	{
		System.out.println(gson.toJson(freightRequestByVendor));
		
		return gson.toJson(freightRequestByVendor);
	}
	
	public static UpdateReservationStatus standardUpdateReservationStatus(Long requestId)
	{
		return new UpdateReservationStatus(requestId, "NEW", "BLOCKED", 212L);
	}
	
	public static String standardJsonUpdateReservationStatus(UpdateReservationStatus updateReservationStatus)
	{
		return gson.toJson(updateReservationStatus);
	}

}
