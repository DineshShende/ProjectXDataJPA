package com.projectx.data.repository;



import javax.persistence.NamedNativeQuery;
import javax.transaction.Transactional;

import com.projectx.data.domain.CustomerAuthenticationDetails;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
@Profile(value={"Prod","Test"})
public interface CustomerAuthenticationDetailsRepository extends
		CrudRepository<CustomerAuthenticationDetails, Long> {

	@Override
	CustomerAuthenticationDetails save(CustomerAuthenticationDetails authenticationDetails);
	
	CustomerAuthenticationDetails findByCustomerId(Long customerId);
	
	CustomerAuthenticationDetails findByEmail(String email);
	
	CustomerAuthenticationDetails findByMobile(Long mobile);
	
	//
	@Transactional
	@Modifying

	@Query(value="update customer_authentication_details set PASSWORD=:password,PASSWORDTYPE=:passwordType,"
			+ "RESENDCOUNT=:resendCount,LASTUNSUCESSFULLATTEMPTS=:lastUnsucessfullAttempts where CUSTOMERID=:customerId",nativeQuery = true)
	Integer updatePasswordAndPasswordTypeAndCounts(@Param("customerId")Long customerId,@Param("password")String password,
			@Param("passwordType")String passwordType,@Param("resendCount")Integer resendCount,@Param("lastUnsucessfullAttempts")Integer lastUnsucessfullAttempts);
	
	
	@Transactional
	@Modifying
	@Query(value="update customer_authentication_details set EMAILPASSWORD=:emailPassword,PASSWORDTYPE=:passwordType,"
			+ "RESENDCOUNT=:resendCount,LASTUNSUCESSFULLATTEMPTS=:lastUnsucessfullAttempts where CUSTOMERID=:customerId",nativeQuery = true)
	Integer updateEmailPasswordAndPasswordTypeAndCounts(@Param("customerId")Long customerId,@Param("emailPassword")String emailPassword,
			@Param("passwordType")String passwordType,@Param("resendCount")Integer resendCount,@Param("lastUnsucessfullAttempts")Integer lastUnsucessfullAttempts);
	
	
	@Transactional
	@Modifying
	@Query(value="update customer_authentication_details set RESENDCOUNT=RESENDCOUNT+1 where CUSTOMERID=:customerId",nativeQuery=true)
	Integer incrementResendCount(@Param("customerId") Long customerId);

	
	@Transactional
	@Modifying
	@Query(value="update customer_authentication_details set LASTUNSUCESSFULLATTEMPTS=LASTUNSUCESSFULLATTEMPTS+1 where CUSTOMERID=:customerId",nativeQuery=true)
	Integer incrementLastUnsucessfullAttempts(@Param("customerId") Long customerId);

	
	
	@Transactional
	@Modifying
	@Query(value="truncate table customer_authentication_details",nativeQuery = true)
	void clearTestData();

//	CustomerAuthenticationDetails findByCustomerId();
	
	/*
	@Query(value="select * from customer_authentication_details where (EMAIL=:email or MOBILE=:mobile) and PASSWORD=:password",nativeQuery = true)
	CustomerAuthenticationDetails loginVerification(@Param("email") String email,@Param("mobile") Long mobile,@Param("password") String password);
	*/
	
}
