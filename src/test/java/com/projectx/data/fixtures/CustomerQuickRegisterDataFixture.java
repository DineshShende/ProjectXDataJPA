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
import com.projectx.rest.domain.UpdatePasswordAndPasswordTypeDTO;
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
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerWithPassword()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerOther()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL_OTHER,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	

	public static String standardJsonQuickRegisterCustomer(CustomerQuickRegisterEntity customer)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(customer.getCustomerId());
		jsonBuilder.append(",\"firstName\":\"");
		jsonBuilder.append(customer.getFirstName());
		jsonBuilder.append("\",\"lastName\":\"");
		
		jsonBuilder.append(customer.getLastName());
		
		jsonBuilder.append("\",\"email\":");
		if(customer.getEmail()!=null)
			jsonBuilder.append("\""+customer.getEmail()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(customer.getMobile());
		jsonBuilder.append(",\"pin\":");
		jsonBuilder.append(customer.getPin());
		jsonBuilder.append(",\"status\":\"");
		jsonBuilder.append(customer.getStatus());
		jsonBuilder.append("\",\"mobilePin\":");
		jsonBuilder.append(customer.getMobilePin());
		
		jsonBuilder.append(",\"emailHash\":");
		if(customer.getEmailHash()!=null)
			jsonBuilder.append("\""+customer.getEmailHash()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobileVerificationAttempts\":");
		jsonBuilder.append(customer.getMobileVerificationAttempts());
		
		jsonBuilder.append(",\"mobilePinSentTime\":");
		if(customer.getMobilePinSentTime()!=null)
			jsonBuilder.append(customer.getMobilePinSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"emailHashSentTime\":");
		if(customer.getEmailHashSentTime()!=null)
			jsonBuilder.append(customer.getEmailHashSentTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"lastStatusChangedTime\":");
		if(customer.getLastStatusChangedTime()!=null)
			jsonBuilder.append(customer.getLastStatusChangedTime().getTime());
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

	public static UpdatePasswordAndPasswordTypeDTO standardUpdatePasswordDTO()
	{
		return new UpdatePasswordAndPasswordTypeDTO(CUST_ID, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}
	
	public static UpdateEmailHashAndMobilePinSentTimeDTO standardUpdateEmailHashAndMobilePinSentTimeDTO()
	{
		return new UpdateEmailHashAndMobilePinSentTimeDTO(CUST_ID, CUST_EMAIL_HASH_SENT_TIME, CUST_MOBILE_PIN_SENT_TIME);
	}

	public static String standardJsonCustomerId(Long customerId)
	{
		return "{\"customerId\":"+customerId+"}";
	}
	
	public static String standardJsonEmailDTO(String email)
	{
		return "{\"email\":\""+email+"\"}";
	}
	
	public static String standardJsonMobileDTO(Long mobile)
	{
		return "{\"mobile\":"+mobile+"}";
	}
	
	public static String standardJsonUpdateStatusAndMobileVerficationAttemptsByCustomerIdDTO(UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO dto)
	{
		//String formattedDate = dateFormat.format(standardUpdateStatusWithCustomerId().getStatusChangeTime());
		
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+dto.getCustomerId()+",\"status\":\""+dto.getStatus()+"\",\"statusChangeTime\":\"");
		jsonBuilder.append(dto.getStatusChangeTime().getTime());
		jsonBuilder.append("\",\"mobileVerificationAttempts\":0}");
		
		return jsonBuilder.toString();
	}
	
	
	public static String standardJsonUpdateMobilePinDTO(UpdateMobilePinDTO dto)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+dto.getCustomerId()+",\"mobilePin\":");
		jsonBuilder.append(dto.getMobilePin());
		jsonBuilder.append(",\"updateTime\":\"");
		jsonBuilder.append(dto.getUpdateTime().getTime());
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	public static String standardJsonUpdateEmailHashDTO(UpdateEmailHashDTO dto)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+dto.getCustomerId()+",\"emailHash\":\"");
		jsonBuilder.append(dto.getEmailHash());
		jsonBuilder.append("\",\"updateTime\":\"");
		jsonBuilder.append(dto.getUpdateTime().getTime());
		jsonBuilder.append("\"}");
				
		return jsonBuilder.toString();
		
	}
	
	public static String standardJsonUpdatePasswordDTO(UpdatePasswordAndPasswordTypeDTO dto)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+dto.getCustomerId()+",\"password\":\"");
		jsonBuilder.append(dto.getPassword());
		jsonBuilder.append("\",\"passwordType\":\"");
		jsonBuilder.append(dto.getPasswordType());
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	public static String standardJsonUpdateEmailHashAndMobilePinSentTimeDTO(UpdateEmailHashAndMobilePinSentTimeDTO dto)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(dto.getCustomerId());
		jsonBuilder.append(",\"emailSentTime\":\"");
		jsonBuilder.append(dto.getEmailSentTime().getTime());
		jsonBuilder.append("\",\"mobilePinSentTime\":\"");
		jsonBuilder.append(dto.getMobilePinSentTime().getTime());
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	
}
