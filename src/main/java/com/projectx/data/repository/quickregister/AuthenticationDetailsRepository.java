package com.projectx.data.repository.quickregister;



import java.util.Optional;


import javax.transaction.Transactional;

import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
@Profile(value={"Prod","Test"})
public interface AuthenticationDetailsRepository extends
		CrudRepository<AuthenticationDetails, AuthenticationDetailsKey> {

	@Override
	AuthenticationDetails save(AuthenticationDetails authenticationDetails);
	
	@Override
	public AuthenticationDetails findOne(AuthenticationDetailsKey id);
	
	Optional<AuthenticationDetails> findByEmail(String email);
	
	Optional<AuthenticationDetails> findByMobile(Long mobile);
	
	//
	@Transactional
	//@Lock
	@Modifying

	@Query(value="update authenticationdetails set PASSWORD=:password,PASSWORDTYPE=:passwordType,"
			+ "RESENDCOUNT=:resendCount,LASTUNSUCESSFULLATTEMPTS=:lastUnsucessfullAttempts where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType" ,nativeQuery = true)
	Integer updatePasswordAndPasswordTypeAndCounts(@Param("customerId")Long customerId,@Param("customerType")Integer customerType,@Param("password")String password,
			@Param("passwordType")String passwordType,@Param("resendCount")Integer resendCount,@Param("lastUnsucessfullAttempts")Integer lastUnsucessfullAttempts);
	
	
	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set EMAILPASSWORD=:emailPassword,PASSWORDTYPE=:passwordType,"
			+ "RESENDCOUNT=:resendCount,LASTUNSUCESSFULLATTEMPTS=:lastUnsucessfullAttempts where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType",nativeQuery = true)
	Integer updateEmailPasswordAndPasswordTypeAndCounts(@Param("customerId")Long customerId,@Param("customerType")Integer customerType,@Param("emailPassword")String emailPassword,
			@Param("passwordType")String passwordType,@Param("resendCount")Integer resendCount,@Param("lastUnsucessfullAttempts")Integer lastUnsucessfullAttempts);
	
	
	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set RESENDCOUNT=RESENDCOUNT+1 where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType",nativeQuery=true)
	Integer incrementResendCount(@Param("customerId") Long customerId,@Param("customerType")Integer customerType);

	
	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set LASTUNSUCESSFULLATTEMPTS=LASTUNSUCESSFULLATTEMPTS+1 where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType",nativeQuery=true)
	Integer incrementLastUnsucessfullAttempts(@Param("customerId") Long customerId,@Param("customerType")Integer customerType);


	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set EMAIL=:email where CUSTOMERID=:customerId and CUSTOMERTYPE=:entityType",nativeQuery=true)
	Integer updateEmail(@Param("customerId") Long customerId,@Param("entityType") Integer entityType,@Param("email")String email);

	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set MOBILE=:mobile where CUSTOMERID=:customerId and CUSTOMERTYPE=:entityType",nativeQuery=true)
	Integer updateMobile(@Param("customerId") Long customerId,@Param("entityType") Integer entityType,@Param("mobile")Long mobile);


//	CustomerAuthenticationDetails findByCustomerId();
	
	/*
	@Query(value="select * from customer_authentication_details where (EMAIL=:email or MOBILE=:mobile) and PASSWORD=:password",nativeQuery = true)
	CustomerAuthenticationDetails loginVerification(@Param("email") String email,@Param("mobile") Long mobile,@Param("password") String password);
	*/
	
}
