package com.projectx.data.fixtures.completeregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.VendorDetails;

import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;

public class VendorDetailsDataFixture {

	public static Long VENDOR_ID=213L;
	
	public static String VENDER_FIRSTNAME="Ted";
	
	public static String VENDER_MIDDLENAME="A.";
	
	public static String VENDER_LASTNAME="Mosby";
	
	public static String VENDOR_FIRMNAME="ABC Corp";
	
	public static Date VENDOR_DATE=new Date();
	
	public static Address VENDOR_ADDRESS=standardAddress();
	
	public static Long VENDOR_MOBILE=8625867370L;
	
	public static Long VENDOR_SEC_MOBILE=8025867370L;
	
	public static Long VENDOR_PHONENUMBER=2034564323L;
	
	public static String VENDOR_EMAIL="tedmosby@gmail.com";
	
	public static Boolean VENDOR_STATUS_TRUE=true;
	
	public static Boolean VENDOR_STATUS_FALSE=false;
	
	public static String VENDOR_LANGUAGE="English";
	
	public static String VENDOR_UPDATEDBY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	public static VendorDetails standardVendor()
	{
		return new VendorDetails(VENDOR_ID, VENDER_FIRSTNAME,VENDER_MIDDLENAME, VENDER_LASTNAME, VENDOR_DATE,VENDOR_FIRMNAME,VENDOR_ADDRESS, VENDOR_ADDRESS, VENDOR_MOBILE,
				VENDOR_PHONENUMBER,VENDOR_STATUS_FALSE,VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE, VENDOR_STATUS_FALSE,VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetails standardVendorError()
	{
		return new VendorDetails(VENDOR_ID, null,VENDER_MIDDLENAME, VENDER_LASTNAME, VENDOR_DATE,VENDOR_FIRMNAME,VENDOR_ADDRESS, VENDOR_ADDRESS, VENDOR_MOBILE,VENDOR_PHONENUMBER,
				VENDOR_STATUS_FALSE,VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE, VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetails standardVendorUpdatedFirstLastName()
	{
		return new VendorDetails(VENDOR_ID, "Updated",VENDER_MIDDLENAME, "Updated", VENDOR_DATE,VENDOR_FIRMNAME,VENDOR_ADDRESS, VENDOR_ADDRESS, VENDOR_MOBILE,VENDOR_PHONENUMBER,
				VENDOR_STATUS_FALSE,VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE, VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static UpdateMobileVerificationStatusUpdatedByDTO standardUpdateMobileVerificationStatusDTO()
	{
		return new UpdateMobileVerificationStatusUpdatedByDTO(VENDOR_ID,VENDOR_MOBILE, VENDOR_STATUS_TRUE,VENDOR_UPDATEDBY);
	}
	
	public static UpdateEmailVerificationStatusUpdatedByDTO standardEmailUpdateVerificationStatusDTO()
	{
		return new UpdateEmailVerificationStatusUpdatedByDTO(VENDOR_ID,VENDOR_EMAIL, VENDOR_STATUS_TRUE,VENDOR_UPDATEDBY);
	}
	
	public static String standardJsonVendor(VendorDetails vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
	
	public static String standardJsonUpdateMobileVerificationStatus(UpdateMobileVerificationStatusUpdatedByDTO vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
	
	public static String standardJsonUpdateEmailVerificationStatus(UpdateEmailVerificationStatusUpdatedByDTO vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
}
