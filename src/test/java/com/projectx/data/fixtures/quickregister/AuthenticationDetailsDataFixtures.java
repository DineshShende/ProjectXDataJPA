package com.projectx.data.fixtures.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailPassword;
import com.projectx.rest.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;


public class AuthenticationDetailsDataFixtures {
	
	
	public static Date CUST_DATE=new Date();
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	
	public static AuthenticationDetailsKey standardAuthenticationDetailsKey()
	{
		return new AuthenticationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER);
	}
	
	public static AuthenticationDetailsKey standardAuthenticationDetailsKeyVendor()
	{
		return new AuthenticationDetailsKey(213L, CUST_TYPE_VENDER);
	}
	
    
	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetails()
	{
		//return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsVendor()
	{
		//return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO);
		return new AuthenticationDetails(standardAuthenticationDetailsKeyVendor(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	public static AuthenticationDetails standardCustomerEmailAuthenticationDetails()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,null, null, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	public static AuthenticationDetails standardCustomerMobileAuthenticationDetails()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, null, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	public static AuthenticationDetails standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	public static AuthenticationDetails standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null,CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED, null, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	
	
	
	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordTypeWithEmailPass()
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,CUST_EMAILHASH_UPDATED ,CUST_PASSWORD_TYPE_CHANGED);
	}

	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordTypeWithPass()
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,null ,CUST_PASSWORD_TYPE_CHANGED);
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
	
	
	public static CustomerIdTypeDTO standardCustomerIdTypeDTO()
	{
		return new CustomerIdTypeDTO(CUST_ID, CUST_TYPE_CUSTOMER);
	}
	
	public static String standardJsonCustomerAuthenticationDetails(AuthenticationDetails standardCustomer)
	{
		
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"key\":{\"customerId\":"+standardCustomer.getKey().getCustomerId()+",\"customerType\":"+standardCustomer.getKey().getCustomerType()+"},");
		
		//{"customerId":212,"customerType":1}
		
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
		
		//jsonBuilder.append("\"insertTime\":"+standardCustomer.getInsertTime().getTime()+",");
		
		//jsonBuilder.append("\"updateTime\":"+standardCustomer.getUpdateTime().getTime()+",");
		
		//jsonBuilder.append("\"updatedBy\":\""+standardCustomer.getUpdatedBy()+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		System.out.println(gson.toJson(standardCustomer));
		
		//System.out.println(gson.toJson(standardCustomer));
		
		return jsonBuilder.toString();
			
		//return gson.toJson(standardCustomer);
		
	}
	
	public static String standardJsonCustomerIdType(CustomerIdTypeDTO idTypeDTO)
	{
		return gson.toJson(idTypeDTO);
	}
	
	
	public static String standardJsonLoginVerification(LoginVerificationDTO loginVerificationDTO)
	{
		System.out.println(loginVerificationDTO);
		
		return gson.toJson(loginVerificationDTO);
	}
	
	
	public static String standardJsonUpdatePasswordEmailPasswordAndPasswordType(UpdatePasswordEmailPasswordAndPasswordTypeDTO dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
		
	}
	
	/*
	public static String standardJsonUpdateEmailPassword()
	{
		System.out.println(standardUpdateEmailPassword());
		
		return gson.toJson(standardUpdateEmailPassword());
	}
	*/
	
	public static String standardJsonCustomerIdForLoginDetails(Long customerId) {
		
		System.out.println("{\"customerId\":"+customerId+"}");
		
		return "{\"customerId\":"+customerId+"}";
	}
	
}
