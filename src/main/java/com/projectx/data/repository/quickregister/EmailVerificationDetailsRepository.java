package com.projectx.data.repository.quickregister;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;

@Repository
@Profile(value={"Test","Prod"})
public interface EmailVerificationDetailsRepository extends CrudRepository<EmailVerificationDetails, EmailVerificationKey> {
	
	EmailVerificationDetails save(EmailVerificationDetails increment);
	
	@Override
	EmailVerificationDetails findOne(EmailVerificationKey id);
	
	//Optional<EmailVerificationDetails> findByCustomerIdAndEmail(Long customerId,String email);
	
	@Transactional
	@Modifying
	@Query(value="update emailverificationdetails set EMAILHASH=:emailHash,EMAILHASHSENTTIME=:emailHashSentTime,"
			+ "RESENDCOUNT=:resendCount where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and EMAIL=:email",nativeQuery=true)
	Integer resetEmailHashAndEmailHashSentTime(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("email")String email,
				@Param("emailHash")String emailHash, @Param("emailHashSentTime")Date emailHashSentTime,@Param("resendCount")Integer resendCount);
	
	
	@Transactional
	@Modifying
	@Query(value="update emailverificationdetails set RESENDCOUNT=RESENDCOUNT+1 where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and EMAIL=:email",nativeQuery=true)
	Integer incrementResendCountByCustomerIdAndEmail(@Param ("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("email")String email);


	@Override
	public void deleteAll();
	
}
