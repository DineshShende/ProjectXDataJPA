package com.projectx.data.repository.completeregister;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.VendorDetails;

@Repository
public interface VendorDetailsRepositoty extends CrudRepository<VendorDetails, Long> {
	

	@Transactional
	@Modifying
	@Query(value="update vendordetails set ismobileverified=:isMobileVerified where vendorid=:vendorId",nativeQuery=true)
	Integer updateIsMobileVerified(@Param("vendorId")Long vendorId,@Param("isMobileVerified")Boolean isMobileVerified);
	
	
	@Transactional
	@Modifying
	@Query(value="update vendordetails set isemailverified=:isEmailVerified where vendorid=:vendorId",nativeQuery=true)
	Integer updateIsEmailVerified(@Param("vendorId")Long vendorId,@Param("isEmailVerified")Boolean isEmailVerified);
	
	

}
