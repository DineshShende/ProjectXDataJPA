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

         Integer countByCustomerIdAndMobilePin(Long customerId,Integer mobilePin);
			
		 Integer countByCustomerIdAndEmailHash(Long customerId,String emailHash);

		 
		 @Query(value="select status from customer_quick_register_entity where CUSTOMERID=:customerId",nativeQuery = true)
		 String fetchStatusByCustomerId(@Param("customerId") Long customerId);
		 
		 @Transactional
		 @Modifying
		 @Query(value="update customer_quick_register_entity set STATUS=:status,LASTSTATUSCHANGETIME=:lastStatusChangeTime where CUSTOMERID=:customerId",nativeQuery = true)
		 Integer updateStatusByCustomerId(@Param("customerId") Long customerId,@Param("status") String status,@Param("lastStatusChangeTime") Date lastStatusChangeTime );
		 
		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set MOBILEPIN=:mobilePin ,MOBILEPINSENTTIME=:mobilePinSentTime,MOBILEVERIFICATIONATTEMPTS=0 where CUSTOMERID=:customerId",nativeQuery = true)
		 Integer updateMobilePin(@Param("customerId") Long customerId, @Param("mobilePin") Integer mobilePin,@Param ("mobilePinSentTime") Date mobilePinSentTime);
		 
		 		
		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set EMAILHASH=:emailHash, EMAILHASHSENTTIME=:emailHashSentTime where CUSTOMERID=:customerId",nativeQuery = true)
		Integer updateEmailHash(@Param("customerId") Long customerId, @Param("emailHash") String emailHash,@Param("emailHashSentTime") Date emailHashSentTime);

		
		@Query(value="select MOBILEVERIFICATIONATTEMPTS from customer_quick_register_entity where CUSTOMERID=:customerId",nativeQuery=true)
		Integer getMobileVerificationAttempts(@Param("customerId")Long customerId);

		@Transactional
		@Modifying
		@Query(value="update customer_quick_register_entity set MOBILEVERIFICATIONATTEMPTS=MOBILEVERIFICATIONATTEMPTS+1 where CUSTOMERID=:customerId ",nativeQuery=true)
		Integer incrementMobileVerificationAttempts(@Param("customerId")Long customerId);
		
		
		@Transactional
		@Modifying
		@Query(value="truncate table customer_quick_register_entity",nativeQuery = true)
		void clearTestData();

		
		
		//@Query(value="select * from customer_quick_register_entity where mobile=:mobile and MOBILEPIN=:mobilePin",nativeQuery = true)
		//Boolean verifyMobilePin(@Param ("mobile")Long mobile,@Param("mobilePin") Integer mobilePin);
				
		//@Query(value="select * from customer_quick_register_entity where mobile=:mobile and MOBILEPIN=:mobilePin",nativeQuery = true)
		//Boolean verifyMobile(@Param ("mobile")Long mobile,@Param("mobilePin") Integer mobilePin);
		
		
		// Long deleteByEmail(String email);
		 
		// Long deleteByMobile(Long mobile);
			
		// CustomerQuickRegisterEntity findByEmail(String email);
		
		// CustomerQuickRegisterEntity findByMobile(Long mobile);
		
		 
		 //@Query(value="select status from customer_quick_register_entity where email=:email",nativeQuery = true)
		 //String fetchStatusByEmail(@Param("email") String email);
		
		 
		 //@Query(value="select status from customer_quick_register_entity where mobile=:mobile",nativeQuery = true)
		 //String fetchStatusByMobile(@Param(value="mobile") Long mobile);
		
		//@Modifying
		//@Query(value="update customer_quick_register_entity set status=:status where email=:email",nativeQuery = true)
		// Integer updateStatusByEmail(@Param("email") String email, @Param("status") String status);
				
		//@Modifying
		//@Query(value="update customer_quick_register_entity set status=:status where mobile=:mobile",nativeQuery = true)
		// Integer updateStatusByMobile(@Param("mobile") Long mobile, @Param("status") String status);
		
		//@Modifying
		//@Query(value="update customer_quick_register_entity set MOBILEPIN=:mobilePin where mobile=:mobile",nativeQuery = true)
		//Integer updateMobilePin(@Param("mobile") Long mobile, @Param("mobilePin") Integer mobilePin);

//		@Modifying
//		@Query(value="update customer_quick_register_entity set EMAILHASH=:emailHash where email=:email",nativeQuery = true)
//		Integer updateEmailHash(@Param("email") String email, @Param("emailHash") Long emailHash);

}
