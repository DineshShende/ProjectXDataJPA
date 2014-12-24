package com.projectx.data.fixtures.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;

public class MobileVericationDetailsFixtures {

	
	public static Integer CUST_MOBILE_TYPE_PRIMARY=1;
	public static Integer CUST_MOBILE_TYPE_SECONDARY=2;
	public static Integer CUST_RESEND_COUNT=0;
	public static Integer CUST_MOBILE_VERIFICATION_ATTEMPTS=0;
	
	static Gson gson=new Gson();
	
	public static Integer CUST_TYPE_CUSTOMER=1;
	public static Integer CUST_TYPE_VENDER=2;
	
	public static Date CUST_DATE=new Date();
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	
	
	
	public static MobileVerificationKey standardMobileVerificationKey()
	{
		return new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_MOBILE);
	}
	
	public static MobileVerificationDetails standardCustomerMobileVerificationDetails()
	{
		return new MobileVerificationDetails(standardMobileVerificationKey(), CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILEPIN, CUST_MOBILE_VERIFICATION_ATTEMPTS, CUST_MOBILE_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	public static MobileVerificationDetails standardCustomerMobileVerificationDetailsOther()
	{
		return new MobileVerificationDetails(standardMobileVerificationKey(), CUST_MOBILE_TYPE_SECONDARY, CUST_MOBILEPIN_UPDATED, CUST_MOBILE_VERIFICATION_ATTEMPTS, CUST_MOBILE_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	
	public static UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO standardUpdateMobilePinAndMobileVerificationAttemptsDTO()
	{
		return new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_MOBILE, CUST_MOBILEPIN_UPDATED, CUST_MOBILE_VERIFICATION_ATTEMPTS+1,CUST_RESEND_COUNT+1);
	}
	

	
	public static CustomerIdTypeMobileDTO standardCustomerIdMobileDTO()
	{
		return new CustomerIdTypeMobileDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_MOBILE);
	}
	
	public static String standardJsonCustomerMobileVerificationDetails()
	{
		System.out.println(gson.toJson(standardCustomerMobileVerificationDetails()));
				
		return gson.toJson(standardCustomerMobileVerificationDetails());
	}
	
	public static String standardJsonUpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO()
	{
		
		System.out.println(gson.toJson(standardUpdateMobilePinAndMobileVerificationAttemptsDTO()));
		return gson.toJson(standardUpdateMobilePinAndMobileVerificationAttemptsDTO());
		
		
	}
	
	public static String standardJsonCustomerIdTypeMobile()
	{
		System.out.println(gson.toJson(standardCustomerIdMobileDTO()));
		return gson.toJson(standardCustomerIdMobileDTO());
	}
	
	
	public static String standardJsonMobileKey()
	{
		System.out.println(gson.toJson(standardMobileVerificationKey()));
		
		return gson.toJson(standardMobileVerificationKey());
	}
	
}
