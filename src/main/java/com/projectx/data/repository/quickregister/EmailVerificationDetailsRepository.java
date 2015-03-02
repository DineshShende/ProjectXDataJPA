
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
@Profile(value={"Test","Prod","Dev"})
public interface EmailVerificationDetailsRepository extends CrudRepository<EmailVerificationDetails, EmailVerificationKey> {
	
	EmailVerificationDetails save(EmailVerificationDetails increment);
	
	@Override
	EmailVerificationDetails findOne(EmailVerificationKey id);

	@Query(value="select * from emailverificationdetails where email=:email",nativeQuery=true)
	EmailVerificationDetails findByEmail(@Param("email")String email);

	@Transactional
	@Modifying
	@Query(value="update emailverificationdetails set EMAIL=:email,EMAILHASH=:emailHash,EMAILHASHSENTTIME=:emailHashSentTime,RESENDCOUNT=:resendCount,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and EMAILTYPE=:emailType",nativeQuery=true)
	Integer updateEmail(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("emailType")Integer emailType,@Param("email")String email,
				@Param("emailHash")String emailHash, @Param("emailHashSentTime")Date emailHashSentTime,@Param("resendCount")Integer resendCount,
				@Param("updateTime") Date updateTime,@Param("updatedBy") String updatedBy);

	
	@Transactional
	@Modifying
	@Query(value="update emailverificationdetails set EMAILHASH=:emailHash,EMAILHASHSENTTIME=:emailHashSentTime,RESENDCOUNT=:resendCount,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and EMAILTYPE=:emailType",nativeQuery=true)
	Integer resetEmailHashAndEmailHashSentTime(@Param("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("emailType")Integer emailType,
				@Param("emailHash")String emailHash, @Param("emailHashSentTime")Date emailHashSentTime,@Param("resendCount")Integer resendCount,
				@Param("updateTime") Date updateTime,@Param("updatedBy") String updatedBy);
	
	
	@Transactional
	@Modifying
	@Query(value="update emailverificationdetails set RESENDCOUNT=RESENDCOUNT+1,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType and EMAILTYPE=:emailType",nativeQuery=true)
	Integer incrementResendCountByCustomerIdAndEmail(@Param ("customerId")Long customerId,@Param("customerType") Integer customerType,@Param("emailType")Integer emailType,
			@Param("updateTime") Date updateTime,@Param("updatedBy") String updatedBy);


	@Override
	public void deleteAll();
	
	@Override
	public void delete(EmailVerificationKey id);
		
}
