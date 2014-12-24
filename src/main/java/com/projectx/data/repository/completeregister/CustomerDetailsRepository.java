package com.projectx.data.repository.completeregister;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Long> {

	@Override
	CustomerDetails save(CustomerDetails customerDetails);
	
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set ismobileverified=:isMobileVerified where customerid=:customerId",nativeQuery=true)
	Integer updateIsMobileVerified(@Param("customerId")Long customerId,@Param("isMobileVerified")Boolean isMobileVerified);
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set issecondarymobileverified=:isSeconadryMobileVerified where customerid=:customerId",nativeQuery=true)
	Integer updateIsSecondaryMobileVerified(@Param("customerId")Long customerId,@Param("isSeconadryMobileVerified")Boolean isSecondaryMobileVerified);
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set isemailverified=:isEmailVerified where customerid=:customerId",nativeQuery=true)
	Integer updateIsEmailVerified(@Param("customerId")Long customerId,@Param("isEmailVerified")Boolean isEmailVerified);
		
}
