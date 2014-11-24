package com.projectx.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.projectx.data.domain.CustomerMobileVerificationDetails;

public interface CustomerMobileVerificationDetailsRepository extends
		CrudRepository<CustomerMobileVerificationDetails, Long> {
	
	
	CustomerMobileVerificationDetails save(CustomerMobileVerificationDetails mobileVerificationDetails);
	
	CustomerMobileVerificationDetails findByCustomerIdAndMobile(Long customerId,Long mobile);
	
	@Transactional
	@Modifying
	@Query(value="update customer_mobile_verification_details set MOBILEPIN=:mobilePin,"
			+ "MOBILEVERIFICATIONATTEMPTS=:mobileVerificationAttempts,RESENDCOUNT=:resendCount"
			+ " where CUSTOMERID=:customerId and MOBILE=:mobile",nativeQuery=true)
	Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(@Param("customerId")Long customerId,@Param("mobile")Long mobile,
			@Param("mobilePin")Integer mobilePin,@Param("mobileVerificationAttempts")Integer mobileVerificationAttempts,@Param("resendCount")Integer resendCount);
	
	
	@Transactional
	@Modifying
	@Query(value="update customer_mobile_verification_details set MOBILEVERIFICATIONATTEMPTS=MOBILEVERIFICATIONATTEMPTS+1"
			+ " where CUSTOMERID=:customerId and MOBILE=:mobile",nativeQuery=true)
	Integer incrementMobileVerificationAttempts(@Param("customerId")Long customerId,@Param("mobile")Long mobile);
	
	@Transactional
	@Modifying
	@Query(value="update customer_mobile_verification_details set RESENDCOUNT=RESENDCOUNT+1 "
			+ "where CUSTOMERID=:customerId and MOBILE=:mobile",nativeQuery=true)
	Integer incrementResendCount(@Param("customerId")Long customerId,@Param("mobile")Long mobile);
	
	
	@Transactional
	@Modifying
	@Query(value="truncate table customer_mobile_verification_details",nativeQuery=true)
	void clearTestData();
	

}
