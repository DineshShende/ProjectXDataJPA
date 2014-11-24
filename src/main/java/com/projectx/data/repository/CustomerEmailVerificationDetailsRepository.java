package com.projectx.data.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.CustomerEmailVerificationDetails;

@Repository
@Profile(value={"Test","Prod"})
public interface CustomerEmailVerificationDetailsRepository extends CrudRepository<CustomerEmailVerificationDetails, Long> {
	
	CustomerEmailVerificationDetails save(CustomerEmailVerificationDetails increment);
	
	
	CustomerEmailVerificationDetails findByCustomerIdAndEmail(Long customerId,String email);
	
	@Transactional
	@Modifying
	@Query(value="update customer_email_verification_details set EMAILHASH=:emailHash,EMAILHASHSENTTIME=:emailHashSentTime,"
			+ "RESENDCOUNT=:resendCount where CUSTOMERID=:customerId and EMAIL=:email",nativeQuery=true)
	Integer resetEmailHashAndEmailHashSentTime(@Param("customerId")Long customerId,@Param("email")String email,
				@Param("emailHash")String emailHash, @Param("emailHashSentTime")Date emailHashSentTime,@Param("resendCount")Integer resendCount);
	
	
	@Transactional
	@Modifying
	@Query(value="update customer_email_verification_details set RESENDCOUNT=RESENDCOUNT+1 where CUSTOMERID=:customerId and EMAIL=:email",nativeQuery=true)
	Integer incrementResendCountByCustomerIdAndEmail(@Param ("customerId")Long customerId,@Param("email")String email);

	@Transactional
	@Modifying
	@Query(value="truncate table customer_email_verification_details",nativeQuery = true)
	void clearTestData();

	
}
