package com.projectx.data.fixtures;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;

import com.google.gson.Gson;
import com.projectx.data.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerIdMobileDTO;
import com.projectx.rest.domain.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;

public class CustomerMobileVericationDetailsFixtures {

	
	public static String CUST_MOBILE_TYPE_PRIMARY="PRIMARY";
	public static String CUST_MOBILE_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;
	public static Integer CUST_MOBILE_VERIFICATION_ATTEMPTS=0;
	
	static Gson gson=new Gson();
	
	public static CustomerMobileVerificationDetails standardCustomerMobileVerificationDetails()
	{
		return new CustomerMobileVerificationDetails(CUST_ID, CUST_MOBILE, CUST_MOBILE_TYPE_PRIMARY, 101010, CUST_MOBILEPIN_VERIFICATION_ATTEMPTS, CUST_RESEND_COUNT);
	}
	
	
	public static UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO standardUpdateMobilePinAndMobileVerificationAttemptsDTO()
	{
		return new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(CUST_ID, CUST_MOBILE, CUST_MOBILEPIN_UPDATED, CUST_MOBILE_VERIFICATION_ATTEMPTS+1,CUST_RESEND_COUNT+1);
	}
	

	
	public static CustomerIdMobileDTO standardCustomerIdMobileDTO()
	{
		return new CustomerIdMobileDTO(CUST_ID, CUST_MOBILE);
	}
	
	public static String standardJsonCustomerMobileVerificationDetails()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardCustomerMobileVerificationDetails().getCustomerId()+",");
		jsonBuilder.append("\"mobile\":"+standardCustomerMobileVerificationDetails().getMobile()+",");
		jsonBuilder.append("\"mobileType\":\""+standardCustomerMobileVerificationDetails().getMobileType()+"\",");
		jsonBuilder.append("\"mobilePin\":"+standardCustomerMobileVerificationDetails().getMobilePin()+",");
		jsonBuilder.append("\"lastUnsucessfulAttempts\":"+standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()+",");
		jsonBuilder.append("\"resendCount\":"+standardCustomerMobileVerificationDetails().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		System.out.println(gson.toJson(standardCustomerMobileVerificationDetails()));
		
		return gson.toJson(standardCustomerMobileVerificationDetails());
		//return jsonBuilder.toString();
	}
	
	public static String standardJsonUpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId()+",");
		jsonBuilder.append("{\"mobile\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile()+",");
		
		jsonBuilder.append("{\"mobilePin\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin()+",");
		jsonBuilder.append("{\"lastUnsucessfulAttempts\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts()+",");
		jsonBuilder.append("{\"resendCount\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		return gson.toJson(standardUpdateMobilePinAndMobileVerificationAttemptsDTO());
		//return jsonBuilder.toString();
		
	}
	
	public static String standardJsonCustomerIdMobile()
	{
		return "{\"customerId\":"+standardCustomerIdMobileDTO().getCustomerId()+","
				+ "\"mobile\":"+standardCustomerIdMobileDTO().getMobile()+"}";
	}
	
	
}
