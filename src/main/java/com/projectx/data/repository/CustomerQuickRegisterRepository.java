package com.projectx.data.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.CustomerQuickRegisterEntity;

@Repository
@Profile(value = { "Test", "Prod" })

public interface CustomerQuickRegisterRepository extends
		CrudRepository<CustomerQuickRegisterEntity,Long > {
	
		 @Override
		 CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity  customer);
		 
		 @Override
		 List<CustomerQuickRegisterEntity> findAll();
	
		 CustomerQuickRegisterEntity findByCustomerId(Long customerId);
		
		 Integer countByEmail(String email);
		 
		 Integer countByMobile(Long mobile);

		 
		 @Transactional
		 @Modifying
		 @Query(value="update customer_quick_register_entity set STATUS=:status,LASTSTATUSCHANGETIME=:lastStatusChangeTime,MOBILEVERIFICATIONATTEMPTS=:mobileVerificationAttempts where CUSTOMERID=:customerId",nativeQuery = true)
		 Integer updateStatusAndMobileVerificationAttemptsByCustomerId(@Param("customerId") Long customerId,@Param("status") String status,
				 @Param("lastStatusChangeTime") Date lastStatusChangeTime,@Param("mobileVerificationAttempts")Integer mobileVerificationAttempts );
		 
		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set MOBILEPIN=:mobilePin ,MOBILEPINSENTTIME=:mobilePinSentTime,MOBILEVERIFICATIONATTEMPTS=0 where CUSTOMERID=:customerId",nativeQuery = true)
		 Integer updateMobilePin(@Param("customerId") Long customerId, @Param("mobilePin") Integer mobilePin,@Param ("mobilePinSentTime") Date mobilePinSentTime);
		 
		 		
		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set EMAILHASH=:emailHash, EMAILHASHSENTTIME=:emailHashSentTime where CUSTOMERID=:customerId",nativeQuery = true)
		Integer updateEmailHash(@Param("customerId") Long customerId, @Param("emailHash") String emailHash,@Param("emailHashSentTime") Date emailHashSentTime);

		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set PASSWORD=:password, PASSWORDTYPE=:passwordType where CUSTOMERID=:customerId",nativeQuery=true)
		 Integer updatePassword(@Param("customerId") Long customerId,@Param("password") String password,@Param("passwordType")String passwordType);
		
		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set EMAILHASHSENTTIME=:emailHashSentTime, MOBILEPINSENTTIME=:mobilePinSentTime where CUSTOMERID=:customerId",nativeQuery=true)
		 Integer updateEmailHashAndMobilePinSentTime(@Param("customerId") Long customerId,@Param("emailHashSentTime") Date emailHashSentTime,@Param("mobilePinSentTime")Date mobilePinSentTime);
		
		
		
		@Transactional
		@Modifying
		@Query(value="truncate table customer_quick_register_entity",nativeQuery = true)
		void clearTestData();

		
		
//      Integer countByCustomerIdAndMobilePin(Long customerId,Integer mobilePin);
//			
//		 Integer countByCustomerIdAndEmailHash(Long customerId,String emailHash);

		 
//		 @Query(value="select status from customer_quick_register_entity where CUSTOMERID=:customerId",nativeQuery = true)
//		 String fetchStatusByCustomerId(@Param("customerId") Long customerId);

		
//		@Query(value="select MOBILEVERIFICATIONATTEMPTS from customer_quick_register_entity where CUSTOMERID=:customerId",nativeQuery=true)
//		Integer getMobileVerificationAttempts(@Param("customerId")Long customerId);

//		@Transactional
//		@Modifying
//		@Query(value="update customer_quick_register_entity set MOBILEVERIFICATIONATTEMPTS=MOBILEVERIFICATIONATTEMPTS+1 where CUSTOMERID=:customerId ",nativeQuery=true)
//		Integer incrementMobileVerificationAttempts(@Param("customerId")Long customerId);
		
		
}
