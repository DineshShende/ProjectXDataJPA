package com.projectx.data.repository.completeregister;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Long>,QueryDslPredicateExecutor<CustomerDetails> {

	@Override
	CustomerDetails save(CustomerDetails customerDetails);
	
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set mobile=:mobile,ismobileverified=:isMobileVerified,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where customerid=:customerId",nativeQuery=true)
	Integer updateMobileAndMobileVerificationStatus(@Param("customerId")Long customerId,@Param("mobile")Long mobile,@Param("isMobileVerified")Boolean isMobileVerified,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set secondarymobile=:secondaryMobile,issecondarymobileverified=:isSeconadryMobileVerified,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where customerid=:customerId",nativeQuery=true)
	Integer updateSecMobileAndSecMobileVerificationStatus(@Param("customerId")Long customerId,@Param("secondaryMobile")Long secondaryMobile, @Param("isSeconadryMobileVerified")Boolean isSecondaryMobileVerified,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set email=:email,isemailverified=:isEmailVerified,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where customerid=:customerId",nativeQuery=true)
	Integer updateEmailAndMEmailVerificationStatus(@Param("customerId")Long customerId,@Param("email")String email,@Param("isEmailVerified")Boolean isEmailVerified,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
		
}
