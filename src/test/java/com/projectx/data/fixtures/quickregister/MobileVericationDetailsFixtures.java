package com.projectx.data.fixtures.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;

public class MobileVericationDetailsFixtures {

	
	public static String CUST_MOBILE_TYPE_PRIMARY="PRIMARY";
	public static String CUST_MOBILE_TYPE_SECONDARY="SECONDARY";
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
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"key\":{\"customerId\":"+standardMobileVerificationKey().getCustomerId()+",\"customerType\":"+standardMobileVerificationKey().getCustomerType()+",\"mobile\":"+standardMobileVerificationKey().getMobile()+"},");
		jsonBuilder.append("\"mobileType\":\""+standardCustomerMobileVerificationDetails().getMobileType()+"\",");
		jsonBuilder.append("\"mobilePin\":"+standardCustomerMobileVerificationDetails().getMobilePin()+",");
		jsonBuilder.append("\"mobileVerificationAttempts\":"+standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()+",");
		jsonBuilder.append("\"resendCount\":"+standardCustomerMobileVerificationDetails().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		//System.out.println(gson.toJson(standardCustomerMobileVerificationDetails()));
		
		return jsonBuilder.toString();
		//return jsonBuilder.toString();
	}
	
	public static String standardJsonUpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId()+",");
		jsonBuilder.append("\"customerType\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType()+",");
		jsonBuilder.append("\"mobile\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile()+",");
		
		jsonBuilder.append("\"mobilePin\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin()+",");
		jsonBuilder.append("\"mobileVerificationAttempts\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts()+",");
		jsonBuilder.append("\"resendCount\":"+standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		//return gson.toJson(standardUpdateMobilePinAndMobileVerificationAttemptsDTO());
		return jsonBuilder.toString();
		
	}
	
	public static String standardJsonCustomerIdTypeMobile()
	{
		return "{\"customerId\":"+standardCustomerIdMobileDTO().getCustomerId()+","
				+"\"customerType\":"+standardCustomerIdMobileDTO().getCustomerType()+","
				+ "\"mobile\":"+standardCustomerIdMobileDTO().getMobile()+"}";
	}
	
	
}
