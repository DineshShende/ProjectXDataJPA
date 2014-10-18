package com.projectx.data.fixtures;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.GetByEmailDTO;
import com.projectx.rest.domain.GetByMobileDTO;
import com.projectx.rest.domain.UpdateEmailHashAndMobilePinSentTimeDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.UpdatePasswordDTO;
import com.projectx.rest.domain.UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO;


public class CustomerQuickRegisterDataFixture {

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static String CUST_EMAIL_OTHER="shendedinesh@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static String CUST_EMAILHASH="02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a";
	public static String CUST_EMAILHASH_UPDATED="277f7fb2ede95f935b08c63273471c9077ace61f1cbb1f376b2983032fda5321";
	public static Integer CUST_MOBILEPIN_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	public static Date CUST_LAST_STATUS_CHANGED_TIME=new Date();
	public static String CUST_PASSWORD_DEFAULT="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	public static String CUST_PASSWORD_CHANGED="654321";
	public static String CUST_PASSWORD_TYPE_CHANGED="Changed";
	
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerWithPassword()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,CUST_PASSWORD_DEFAULT,CUST_PASSWORD_TYPE_DEFAULT);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,null,null);
		
	}
	

	public static String standardJsonEmailMobileCustomer()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(standardEmailMobileCustomer().getCustomerId());
		jsonBuilder.append(",\"firstName\":\"");
		jsonBuilder.append(standardEmailMobileCustomer().getFirstName());
		jsonBuilder.append("\",\"lastName\":\"");
		
		jsonBuilder.append(standardEmailMobileCustomer().getLastName());
		
		jsonBuilder.append("\",\"email\":");
		if(standardEmailMobileCustomer().getEmail()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomer().getEmail()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(standardEmailMobileCustomer().getMobile());
		jsonBuilder.append(",\"pin\":");
		jsonBuilder.append(standardEmailMobileCustomer().getPin());
		jsonBuilder.append(",\"status\":\"");
		jsonBuilder.append(standardEmailMobileCustomer().getStatus());
		jsonBuilder.append("\",\"mobilePin\":");
		jsonBuilder.append(standardEmailMobileCustomer().getMobilePin());
		
		jsonBuilder.append(",\"emailHash\":");
		if(standardEmailMobileCustomer().getEmailHash()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomer().getEmailHash()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobileVerificationAttempts\":");
		jsonBuilder.append(standardEmailMobileCustomer().getMobileVerificationAttempts());
		
		jsonBuilder.append(",\"mobilePinSentTime\":");
		if(standardEmailMobileCustomer().getMobilePinSentTime()!=null)
			jsonBuilder.append(standardEmailMobileCustomer().getMobilePinSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"emailHashSentTime\":");
		if(standardEmailMobileCustomer().getEmailHashSentTime()!=null)
			jsonBuilder.append(standardEmailMobileCustomer().getEmailHashSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"lastStatusChangedTime\":");
		if(standardEmailMobileCustomer().getLastStatusChangedTime()!=null)
			jsonBuilder.append(standardEmailMobileCustomer().getLastStatusChangedTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"password\":");
		if(standardEmailMobileCustomer().getPassword()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomer().getPassword()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"passwordType\":");
		if(standardEmailMobileCustomer().getPasswordType()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomer().getPasswordType()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append("}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();

	}

	
	public static String standardJsonEmailMobileCustomerWithPassword()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getCustomerId());
		jsonBuilder.append(",\"firstName\":\"");
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getFirstName());
		jsonBuilder.append("\",\"lastName\":\"");
		
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getLastName());
		
		jsonBuilder.append("\",\"email\":");
		if(standardEmailMobileCustomerWithPassword().getEmail()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomerWithPassword().getEmail()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getMobile());
		jsonBuilder.append(",\"pin\":");
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getPin());
		jsonBuilder.append(",\"status\":\"");
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getStatus());
		jsonBuilder.append("\",\"mobilePin\":");
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getMobilePin());
		
		jsonBuilder.append(",\"emailHash\":");
		if(standardEmailMobileCustomerWithPassword().getEmailHash()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomerWithPassword().getEmailHash()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobileVerificationAttempts\":");
		jsonBuilder.append(standardEmailMobileCustomerWithPassword().getMobileVerificationAttempts());
		
		jsonBuilder.append(",\"mobilePinSentTime\":");
		if(standardEmailMobileCustomerWithPassword().getMobilePinSentTime()!=null)
			jsonBuilder.append(standardEmailMobileCustomerWithPassword().getMobilePinSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"emailHashSentTime\":");
		if(standardEmailMobileCustomerWithPassword().getEmailHashSentTime()!=null)
			jsonBuilder.append(standardEmailMobileCustomerWithPassword().getEmailHashSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"lastStatusChangedTime\":");
		if(standardEmailMobileCustomerWithPassword().getLastStatusChangedTime()!=null)
			jsonBuilder.append(standardEmailMobileCustomerWithPassword().getLastStatusChangedTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"password\":");
		if(standardEmailMobileCustomerWithPassword().getPassword()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomerWithPassword().getPassword()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"passwordType\":");
		if(standardEmailMobileCustomerWithPassword().getPasswordType()!=null)
			jsonBuilder.append("\""+standardEmailMobileCustomerWithPassword().getPasswordType()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append("}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();

	}

	
	public static String standardJsonEmailCustomer()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(standardEmailCustomer().getCustomerId());
		jsonBuilder.append(",\"firstName\":\"");
		jsonBuilder.append(standardEmailCustomer().getFirstName());
		jsonBuilder.append("\",\"lastName\":\"");
		
		jsonBuilder.append(standardEmailCustomer().getLastName());
		
		jsonBuilder.append("\",\"email\":");
		if(standardEmailCustomer().getEmail()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getEmail()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(standardEmailCustomer().getMobile());
		jsonBuilder.append(",\"pin\":");
		jsonBuilder.append(standardEmailCustomer().getPin());
		jsonBuilder.append(",\"status\":\"");
		jsonBuilder.append(standardEmailCustomer().getStatus());
		jsonBuilder.append("\",\"mobilePin\":");
		jsonBuilder.append(standardEmailCustomer().getMobilePin());
		
		jsonBuilder.append(",\"emailHash\":");
		if(standardEmailCustomer().getEmailHash()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getEmailHash()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobileVerificationAttempts\":");
		jsonBuilder.append(standardEmailCustomer().getMobileVerificationAttempts());
		
		jsonBuilder.append(",\"mobilePinSentTime\":");
		if(standardEmailCustomer().getMobilePinSentTime()!=null)
			jsonBuilder.append(standardEmailCustomer().getMobilePinSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"emailHashSentTime\":");
		if(standardEmailCustomer().getEmailHashSentTime()!=null)
			jsonBuilder.append(standardEmailCustomer().getEmailHashSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"lastStatusChangedTime\":");
		if(standardEmailCustomer().getLastStatusChangedTime()!=null)
			jsonBuilder.append(standardEmailCustomer().getLastStatusChangedTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"password\":");
		if(standardEmailCustomer().getPassword()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getPassword()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"passwordType\":");
		if(standardEmailCustomer().getPasswordType()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getPasswordType()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append("}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}

	public static String standardJsonMobileCustomer()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(standardMobileCustomer().getCustomerId());
		jsonBuilder.append(",\"firstName\":\"");
		jsonBuilder.append(standardMobileCustomer().getFirstName());
		jsonBuilder.append("\",\"lastName\":\"");
		
		jsonBuilder.append(standardMobileCustomer().getLastName());
		
		jsonBuilder.append("\",\"email\":");
		if(standardMobileCustomer().getEmail()!=null)
			jsonBuilder.append("\""+standardMobileCustomer().getEmail()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(standardMobileCustomer().getMobile());
		jsonBuilder.append(",\"pin\":");
		jsonBuilder.append(standardMobileCustomer().getPin());
		jsonBuilder.append(",\"status\":\"");
		jsonBuilder.append(standardMobileCustomer().getStatus());
		jsonBuilder.append("\",\"mobilePin\":");
		jsonBuilder.append(standardMobileCustomer().getMobilePin());
		
		jsonBuilder.append(",\"emailHash\":");
		if(standardMobileCustomer().getEmailHash()!=null)
			jsonBuilder.append("\""+standardMobileCustomer().getEmailHash()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobileVerificationAttempts\":");
		jsonBuilder.append(standardMobileCustomer().getMobileVerificationAttempts());
		
		jsonBuilder.append(",\"mobilePinSentTime\":");
		if(standardMobileCustomer().getMobilePinSentTime()!=null)
			jsonBuilder.append(standardMobileCustomer().getMobilePinSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"emailHashSentTime\":");
		if(standardMobileCustomer().getEmailHashSentTime()!=null)
			jsonBuilder.append(standardMobileCustomer().getEmailHashSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"lastStatusChangedTime\":");
		if(standardMobileCustomer().getLastStatusChangedTime()!=null)
			jsonBuilder.append(standardMobileCustomer().getLastStatusChangedTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"password\":");
		if(standardMobileCustomer().getPassword()!=null)
			jsonBuilder.append("\""+standardMobileCustomer().getPassword()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"passwordType\":");
		if(standardMobileCustomer().getPasswordType()!=null)
			jsonBuilder.append("\""+standardMobileCustomer().getPasswordType()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append("}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}

	public static String standardJsonEmailCustomerOther()
	{
StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(standardEmailCustomer().getCustomerId());
		jsonBuilder.append(",\"firstName\":\"");
		jsonBuilder.append(standardEmailCustomer().getFirstName());
		jsonBuilder.append("\",\"lastName\":\"");
		
		jsonBuilder.append(standardEmailCustomer().getLastName());
		
		jsonBuilder.append("\",\"email\":");
		if(standardEmailCustomer().getEmail()!=null)
			jsonBuilder.append("\""+CUST_EMAIL_OTHER+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(standardEmailCustomer().getMobile());
		jsonBuilder.append(",\"pin\":");
		jsonBuilder.append(standardEmailCustomer().getPin());
		jsonBuilder.append(",\"status\":\"");
		jsonBuilder.append(standardEmailCustomer().getStatus());
		jsonBuilder.append("\",\"mobilePin\":");
		jsonBuilder.append(standardEmailCustomer().getMobilePin());
		
		jsonBuilder.append(",\"emailHash\":");
		if(standardEmailCustomer().getEmailHash()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getEmailHash()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobileVerificationAttempts\":");
		jsonBuilder.append(standardEmailCustomer().getMobileVerificationAttempts());
		
		jsonBuilder.append(",\"mobilePinSentTime\":");
		if(standardEmailCustomer().getMobilePinSentTime()!=null)
			jsonBuilder.append(standardEmailCustomer().getMobilePinSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"emailHashSentTime\":");
		if(standardEmailCustomer().getEmailHashSentTime()!=null)
			jsonBuilder.append(standardEmailCustomer().getEmailHashSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"lastStatusChangedTime\":");
		if(standardEmailCustomer().getLastStatusChangedTime()!=null)
			jsonBuilder.append(standardEmailCustomer().getLastStatusChangedTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"password\":");
		if(standardEmailCustomer().getPassword()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getPassword()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"passwordType\":");
		if(standardEmailCustomer().getPasswordType()!=null)
			jsonBuilder.append("\""+standardEmailCustomer().getPasswordType()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append("}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();

	}
	

	
	public static CustomerIdDTO standardCustomerId()
	{
		return new CustomerIdDTO(CUST_ID);
	}
	
	public static GetByEmailDTO standardGetByEmailDTO()
	{
		return new GetByEmailDTO(CUST_EMAIL);
	}
	
	public static GetByMobileDTO standardGetByMobile()
	{
		return new GetByMobileDTO(CUST_MOBILE);
	}
	
	public static UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId()
	{
		return new UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO(CUST_ID,STATUS_EMAIL_MOBILE_VERIFIED,CUST_LAST_STATUS_CHANGED_TIME,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS);
	}
	
	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID,CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME);
	}
	
	public static UpdateMobilePinDTO standardUpdateMobilePinDTO()
	{
		return new UpdateMobilePinDTO(CUST_ID,CUST_MOBILEPIN_UPDATED,CUST_MOBILE_PIN_SENT_TIME);
	}

	public static UpdatePasswordDTO standardUpdatePasswordDTO()
	{
		return new UpdatePasswordDTO(CUST_ID, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}
	
	public static UpdateEmailHashAndMobilePinSentTimeDTO standardUpdateEmailHashAndMobilePinSentTimeDTO()
	{
		return new UpdateEmailHashAndMobilePinSentTimeDTO(CUST_ID, CUST_EMAIL_HASH_SENT_TIME, CUST_MOBILE_PIN_SENT_TIME);
	}

	public static String standardJsonCustomerId()
	{
		return "{\"customerId\":212}";
	}
	
	public static String standardJsonEmailDTO()
	{
		return "{\"email\":\"dineshshe@gmail.com\"}";
	}
	
	public static String standardJsonMobileDTO()
	{
		return "{\"mobile\":9960821869}";
	}
	
	public static String standardJsonUpdateStatusAndMobileVerficationAttemptsByCustomerIdDTO()
	{
		//String formattedDate = dateFormat.format(standardUpdateStatusWithCustomerId().getStatusChangeTime());
		
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":212,\"status\":\"EmailMobileVerified\",\"statusChangeTime\":\"");
		jsonBuilder.append(standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId().getStatusChangeTime().getTime());
		jsonBuilder.append("\",\"mobileVerificationAttempts\":0}");
		
		return jsonBuilder.toString();
	}
	
	
	public static String standardJsonUpdateMobilePinDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":212,\"mobilePin\":");
		jsonBuilder.append(CUST_MOBILEPIN_UPDATED);
		jsonBuilder.append(",\"updateTime\":\"");
		jsonBuilder.append(standardUpdateMobilePinDTO().getUpdateTime().getTime());
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	public static String standardJsonUpdateEmailHashDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":212,\"emailHash\":\"");
		jsonBuilder.append(CUST_EMAILHASH_UPDATED);
		jsonBuilder.append("\",\"updateTime\":\"");
		jsonBuilder.append(standardUpdateEmailHashDTO().getUpdateTime().getTime());
		jsonBuilder.append("\"}");
				
		return jsonBuilder.toString();
		
	}
	
	public static String standardJsonUpdatePasswordDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":212,\"password\":\"");
		jsonBuilder.append(CUST_PASSWORD_CHANGED);
		jsonBuilder.append("\",\"passwordType\":\"");
		jsonBuilder.append(CUST_PASSWORD_TYPE_CHANGED);
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	public static String standardJsonUpdateEmailHashAndMobilePinSentTimeDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(CUST_ID);
		jsonBuilder.append(",\"emailSentTime\":\"");
		jsonBuilder.append(standardUpdateEmailHashAndMobilePinSentTimeDTO().getEmailSentTime().getTime());
		jsonBuilder.append("\",\"mobilePinSentTime\":\"");
		jsonBuilder.append(standardUpdateEmailHashAndMobilePinSentTimeDTO().getMobilePinSentTime().getTime());
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	/*
	public static String standardJsonVerifyMobileDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":101010}";
		       
	}
	
	public static String standardJsonVerifyEmailDTO()
	{
		return "{\"customerId\":212,\"emailHash\":\"02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a\"}";
		       
	}
*/
	
	/*
	public static VerifyMobileDTO standardVerifyMobilePinDTO()
	{
		return new VerifyMobileDTO(CUST_ID, CUST_MOBILEPIN);
	}
	
	public static VerifyEmailDTO standardVerifyEmailHashDTO()
	{
		return new VerifyEmailDTO(CUST_ID,CUST_EMAILHASH);
	}
	*/
}
