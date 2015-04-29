package com.projectx.data.repository.quickregister;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;

public interface MobileVerificationDetailsRepository extends
		CrudRepository<MobileVerificationDetails, MobileVerificationKey> {
	
		
	
	
	@Override
	MobileVerificationDetails  findOne(MobileVerificationKey id);
	
	@Query(value="select * from mobileverificationdetails where mobile=:mobile",nativeQuery=true)
	MobileVerificationDetails findByMobile(@Param("mobile")Long mobile);
	
	
	@Transactional
	@Modifying
	@Query(value="update mobileverificationdetails set MOBILEPIN=:mobilePin,"
			+ "MOBILEVERIFICATIONATTEMPTS=:mobileVerificationAttempts,RESENDCOUNT=:resendCount,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById"
			+ " where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and MOBILETYPE=:mobileType",nativeQuery=true)
	Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("mobileType")Integer mobileType,
			@Param("mobilePin")Integer mobilePin,@Param("mobileVerificationAttempts")Integer mobileVerificationAttempts,@Param("resendCount")Integer resendCount,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	@Transactional
	@Modifying
	@Query(value="update mobileverificationdetails set MOBILE=:mobile,MOBILEPIN=:mobilePin,"
			+ "MOBILEVERIFICATIONATTEMPTS=:mobileVerificationAttempts,RESENDCOUNT=:resendCount,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById"
			+ " where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and MOBILETYPE=:mobileType",nativeQuery=true)
	Integer updateMobile(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("mobileType")Integer mobileType,@Param("mobile")Long mobile,
			@Param("mobilePin")Integer mobilePin,@Param("mobileVerificationAttempts")Integer mobileVerificationAttempts,@Param("resendCount")Integer resendCount,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	
	
	@Transactional
	@Modifying
	@Query(value="update mobileverificationdetails set MOBILEVERIFICATIONATTEMPTS=MOBILEVERIFICATIONATTEMPTS+1,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById"
			+ " where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and MOBILETYPE=:mobileType",nativeQuery=true)
	Integer incrementMobileVerificationAttempts(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("mobileType")Integer mobileType,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	@Transactional
	@Modifying
	@Query(value="update mobileverificationdetails set RESENDCOUNT=RESENDCOUNT+1,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById "
			+ "where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and MOBILETYPE=:mobileType",nativeQuery=true)
	Integer incrementResendCount(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("mobileType")Integer mobileType,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	
	@Override
	public void delete(MobileVerificationKey id);

}
