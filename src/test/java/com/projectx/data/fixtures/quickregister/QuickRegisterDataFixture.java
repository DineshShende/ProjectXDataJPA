package com.projectx.data.fixtures.quickregister;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;



public class QuickRegisterDataFixture {

	
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
	
	public static Integer CUST_TYPE_CUSTOMER=1;
	public static Integer CUST_TYPE_VENDER=2;
	
	public static Integer CUST_COUNT_ZERO=0;
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";

	
	public static Boolean CUST_ISMOBILE_VERIFIED=false;
	public static Boolean CUST_ISEMAIL_VERIFIED=false;
	public static Date CUST_INSERT_TIME=new Date();
	public static Date CUST_UPDATE_TIME=new Date();
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	

	static Gson gson=new Gson();
	
	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public static QuickRegisterEntity standardEmailMobileCustomer()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER ,CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}
	
	public static QuickRegisterEntity standardEmailMobileCustomerWithErrors()
	{
		return new QuickRegisterEntity(CUST_ID, null, CUST_LASTNAME, null, null, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}
	
	public static QuickRegisterEntity standardMobileCustomer()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN, null, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailCustomer()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN, CUST_ISEMAIL_VERIFIED, null,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}
	
	
	
	public static QuickRegisterEntity standardEmailCustomerOther()
	{
		
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL_OTHER, null, CUST_PIN, CUST_ISEMAIL_VERIFIED, null,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
					CUST_UPDATE_TIME, CUST_UPDATED_BY);
			
	}
	

	public static String standardJsonQuickRegisterCustomer(QuickRegisterEntity customer)
	{

		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":");
		jsonBuilder.append(customer.getCustomerId());
		
		jsonBuilder.append(",\"firstName\":");
				
		if(customer.getFirstName()!=null)
				jsonBuilder.append("\""+customer.getFirstName()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"lastName\":");
		
		if(customer.getLastName()!=null)
			jsonBuilder.append("\""+customer.getLastName()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"email\":");
		if(customer.getEmail()!=null)
			jsonBuilder.append("\""+customer.getEmail()+"\"");
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"mobile\":");
		
		jsonBuilder.append(customer.getMobile());
		jsonBuilder.append(",\"pincode\":");
		jsonBuilder.append(customer.getPincode());
		jsonBuilder.append(",\"isEmailVerified\":");
		jsonBuilder.append(customer.getIsEmailVerified());
		
		jsonBuilder.append(",\"isMobileVerified\":");
		jsonBuilder.append(customer.getIsMobileVerified());
		
		jsonBuilder.append(",\"insertTime\":");
		if(customer.getInsertTime()!=null)
			jsonBuilder.append(customer.getInsertTime().getTime());
		else
			jsonBuilder.append("null");
		
		jsonBuilder.append(",\"updateTime\":");
		if(customer.getUpdateTime()!=null)
			jsonBuilder.append(customer.getUpdateTime().getTime());
		else
			jsonBuilder.append("null");
		
		
		jsonBuilder.append(",\"updatedBy\":");
		if(customer.getUpdatedBy()!=null)
			jsonBuilder.append("\""+customer.getUpdatedBy()+"\"");
		else
			jsonBuilder.append("null");
		
				
		jsonBuilder.append("}");
		
		System.out.println(jsonBuilder.toString());
		
		
		return jsonBuilder.toString();

//		System.out.println(gson.toJson(customer));
//		
//		return gson.toJson(customer);

	}

	
	public static CustomerIdDTO standardCustomerId()
	{
		return new CustomerIdDTO(CUST_ID);
	}
	
	
	public static CustomerIdTypeDTO standardCustomerIdTypeDTO()
	{
		return new CustomerIdTypeDTO(CUST_ID, CUST_TYPE_CUSTOMER);
	}
	
	public static EmailDTO standardGetByEmailDTO()
	{
		return new EmailDTO(CUST_EMAIL);
	}
	
	public static MobileDTO standardGetByMobile()
	{
		return new MobileDTO(CUST_MOBILE);
	}
	
	
	public static UpdateEmailMobileVerificationStatus standardUpdateEmailMobileVerificationStatus()
	{
		return new UpdateEmailMobileVerificationStatus(CUST_ID, true, new Date(), CUST_UPDATED_BY);
	}
	
	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID,CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME);
	}
	/*
	public static UpdatePasswordAndPasswordTypeDTO standardUpdatePasswordDTO()
	{
		return new UpdatePasswordAndPasswordTypeDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}
	*/
	
	public static String standardJsonUpdateEmailMobileVerificationStatus()
	{
		System.out.println("{\"customerId\":"+standardUpdateEmailMobileVerificationStatus().getCustomerId()+","
				+ "\"status\":"+standardUpdateEmailMobileVerificationStatus().getStatus()+","
			+ "\"updateTime\":"+standardUpdateEmailMobileVerificationStatus().getUpdateTime().getTime()+","
			+ "\"updatedBy\":\""+standardUpdateEmailMobileVerificationStatus().getUpdatedBy()+"\"}");
		
		return "{\"customerId\":"+standardUpdateEmailMobileVerificationStatus().getCustomerId()+","
				+ "\"status\":"+standardUpdateEmailMobileVerificationStatus().getStatus()+","
			+ "\"updateTime\":"+standardUpdateEmailMobileVerificationStatus().getUpdateTime().getTime()+","
			+ "\"updatedBy\":\""+standardUpdateEmailMobileVerificationStatus().getUpdatedBy()+"\"}";
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
	
	/*
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
	*/
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
	/*
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
	*/
	
}
