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
	@Query(value="update customerdetails set mobile=:mobile,ismobileverified=:isMobileVerified where customerid=:customerId",nativeQuery=true)
	Integer updateMobileAndMobileVerificationStatus(@Param("customerId")Long customerId,@Param("mobile")Long mobile,@Param("isMobileVerified")Boolean isMobileVerified);
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set secondarymobile=:secondaryMobile,issecondarymobileverified=:isSeconadryMobileVerified where customerid=:customerId",nativeQuery=true)
	Integer updateSecMobileAndSecMobileVerificationStatus(@Param("customerId")Long customerId,@Param("secondaryMobile")Long secondaryMobile, @Param("isSeconadryMobileVerified")Boolean isSecondaryMobileVerified);
	
	@Transactional
	@Modifying
	@Query(value="update customerdetails set email=:email,isemailverified=:isEmailVerified where customerid=:customerId",nativeQuery=true)
	Integer updateEmailAndMEmailVerificationStatus(@Param("customerId")Long customerId,@Param("email")String email,@Param("isEmailVerified")Boolean isEmailVerified);
		
}
