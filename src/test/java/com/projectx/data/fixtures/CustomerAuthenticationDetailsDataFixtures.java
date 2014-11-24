package com.projectx.data.fixtures;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*; 

import com.projectx.data.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.LoginVerificationDTO;
import com.projectx.rest.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;

import com.projectx.rest.domain.UpdateEmailPassword;
import com.projectx.rest.domain.UpdatePasswordAndPasswordTypeDTO;


public class CustomerAuthenticationDetailsDataFixtures {
	
	
    
	public static CustomerAuthenticationDetails standardCustomerEmailMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
	}
	
	public static CustomerAuthenticationDetails standardCustomerEmailAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, null, null, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
	}

	public static CustomerAuthenticationDetails standardCustomerMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, null, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
	}

	public static CustomerAuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
	}
	
	public static CustomerAuthenticationDetails standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
	}

	public static CustomerAuthenticationDetails standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED, null, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
	}

	
	public static UpdatePasswordAndPasswordTypeDTO standardUpdatePasswordAndPasswordTypeDTO()
	{
		return new UpdatePasswordAndPasswordTypeDTO(CUST_ID, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}

	public static UpdateEmailPassword standardUpdateEmailPassword()
	{
		return new UpdateEmailPassword(CUST_ID, CUST_EMAILHASH_UPDATED);
	}
	
	
	
	public static LoginVerificationDTO standardLoginVerificationWithEmail()
	{
		return new LoginVerificationDTO(CUST_EMAIL, null, CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobile()
	{
		return new LoginVerificationDTO(null, CUST_MOBILE, CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithEmailNewPassword()
	{
		return new LoginVerificationDTO(CUST_EMAIL, null, CUST_PASSWORD_CHANGED);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobileNewPassword()
	{
		return new LoginVerificationDTO(null, CUST_MOBILE, CUST_PASSWORD_CHANGED);
	}
	
	public static String standardJsonCustomerAuthenticationDetails(CustomerAuthenticationDetails standardCustomer)
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardCustomer.getCustomerId()+",");
		
		if(standardCustomer.getEmail()!=null)
			jsonBuilder.append("\"email\":\""+standardCustomer.getEmail()+"\",");
		else
			jsonBuilder.append("\"email\":"+standardCustomer.getEmail()+",");
		
		jsonBuilder.append("\"mobile\":"+standardCustomer.getMobile()+",");
		
		if(standardCustomer.getEmailPassword()!=null)	
			jsonBuilder.append("\"password\":\""+standardCustomer.getPassword()+"\",");
		else
			jsonBuilder.append("\"password\":"+standardCustomer.getPassword()+",");
		
		if(standardCustomer.getEmailPassword()!=null)
			jsonBuilder.append("\"emailPassword\":\""+standardCustomer.getEmailPassword()+"\",");
		else
			jsonBuilder.append("\"emailPassword\":"+standardCustomer.getEmailPassword()+",");
		
		jsonBuilder.append("\"lastUnsucessfullAttempts\":"+standardCustomer.getLastUnsucessfullAttempts()+",");
		
		jsonBuilder.append("\"resendCount\":"+standardCustomer.getResendCount()+",");
		
		jsonBuilder.append("\"passwordType\":\""+standardCustomer.getPasswordType()+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
		//return gson.toJson(standardCustomer);
		
	}
	
	public static String standardJsonLoginVerification(LoginVerificationDTO loginVerificationDTO)
	{
		StringBuilder jsonBuilder=new StringBuilder();

		if(loginVerificationDTO.getEmail()!=null)
			jsonBuilder.append("{\"email\":\""+loginVerificationDTO.getEmail()+"\",");
		else
			jsonBuilder.append("{\"email\":"+loginVerificationDTO.getEmail()+",");

		jsonBuilder.append("\"mobile\":"+loginVerificationDTO.getMobile()+",");
		
		jsonBuilder.append("\"password\":\""+loginVerificationDTO.getPassword()+"\"}");
		
		//System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
		//return gson.toJson(loginVerificationDTO);
	}
	
	
	public static String standardJsonUpdatePasswordAndPasswordType()
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardUpdatePasswordAndPasswordTypeDTO().getCustomerId()+",");
		
		jsonBuilder.append("\"password\":\""+standardUpdatePasswordAndPasswordTypeDTO().getPassword()+"\",");
		jsonBuilder.append("\"passwordType\":\""+standardUpdatePasswordAndPasswordTypeDTO().getPasswordType()+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
		//return gson.toJson(standardUpdatePasswordAndPasswordTypeDTO());
		
	}
	
	public static String standardJsonUpdateEmailPassword()
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardUpdateEmailPassword().getCustomerId()+",");
		
		jsonBuilder.append("\"emailPassword\":\""+standardUpdateEmailPassword().getEmailPassword()+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();

		
	}
	
	
	public static String standardJsonCustomerIdForLoginDetails(Long customerId) {
		
		System.out.println("{\"customerId\":"+customerId+"}");
		
		return "{\"customerId\":"+customerId+"}";
	}
	
}
