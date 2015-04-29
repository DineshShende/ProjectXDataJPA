package com.projectx.data.fixtures.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailPassword;
import com.projectx.rest.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;


public class AuthenticationDetailsDataFixtures {
	
	
	public static Date CUST_DATE=new Date();
	public static Integer CUST_UPDATED_BY=1;
	
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
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, 
				CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithError()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null,null, CUST_PASSWORD_DEFAULT, 
				CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsVendor()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKeyVendor(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, 
				CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static AuthenticationDetails standardCustomerEmailAuthenticationDetails()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,null, null, CUST_PASSWORD_TYPE_DEFAULT,
				CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}

	public static AuthenticationDetails standardCustomerEmailAuthenticationDetailsDuplicate()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKeyVendor(), CUST_EMAIL,null, null, CUST_PASSWORD_TYPE_DEFAULT,
				CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static AuthenticationDetails standardCustomerMobileAuthenticationDetails()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null,CUST_MOBILE, CUST_PASSWORD_DEFAULT,
				CUST_PASSWORD_TYPE_DEFAULT, null, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}

	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_CHANGED,
				CUST_PASSWORD_TYPE_CHANGED, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static AuthenticationDetails standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,null, CUST_PASSWORD_CHANGED, 
				CUST_PASSWORD_TYPE_CHANGED, CUST_EMAILHASH, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}

	public static AuthenticationDetails standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null,CUST_MOBILE, CUST_PASSWORD_CHANGED, 
				CUST_PASSWORD_TYPE_CHANGED, null, CUST_COUNT_ZERO, CUST_COUNT_ZERO,CUST_DATE,CUST_DATE,
				 ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}

	
	
	
	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordTypeWithEmailPass()
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,CUST_EMAILHASH_UPDATED ,CUST_PASSWORD_TYPE_CHANGED,CUST_UPDATED_BY,CUST_ID);
	}

	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordTypeWithEmailPass(Long customerId)
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(customerId,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,CUST_EMAILHASH_UPDATED ,CUST_PASSWORD_TYPE_CHANGED,CUST_UPDATED_BY,customerId);
	}
	
	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordTypeWithPass()
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,null ,CUST_PASSWORD_TYPE_CHANGED,CUST_UPDATED_BY,CUST_ID);
	}
	
	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordTypeWithPass(Long customerId)
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(customerId,CUST_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,null ,CUST_PASSWORD_TYPE_CHANGED,CUST_UPDATED_BY,customerId);
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
	
	public static CustomerIdTypeUpdatedByDTO standardCustomerIdTypeUpdatedByDTO()
	{
		return new CustomerIdTypeUpdatedByDTO(CUST_ID, CUST_TYPE_CUSTOMER,CUST_UPDATED_BY,CUST_ID);
	}
	
	public static CustomerIdTypeUpdatedByDTO standardCustomerIdTypeUpdatedByDTO(Long customerId)
	{
		return new CustomerIdTypeUpdatedByDTO(customerId, CUST_TYPE_CUSTOMER,CUST_UPDATED_BY,customerId);
	}
	
	public static String standardJsonCustomerAuthenticationDetails(AuthenticationDetails standardCustomer)
	{
		
		return gson.toJson(standardCustomer);
		
	}
	
	public static String standardJsonCustomerIdType(CustomerIdTypeDTO idTypeDTO)
	{
		return gson.toJson(idTypeDTO);
	}
	
	public static String standardJsonCustomerIdTypeUpdatedBy(CustomerIdTypeUpdatedByDTO idTypeDTO)
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
	
		
	public static String standardJsonCustomerIdForLoginDetails(Long customerId) {
		
		System.out.println("{\"customerId\":"+customerId+"}");
		
		return "{\"customerId\":"+customerId+"}";
	}
	
}
