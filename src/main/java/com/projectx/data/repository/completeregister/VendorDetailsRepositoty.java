package com.projectx.data.repository.completeregister;

import java.util.Date;

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
	@Query(value="update vendordetails set mobile=:mobile,ismobileverified=:isMobileVerified,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where vendorid=:vendorId",nativeQuery=true)
	Integer updateIsMobileVerified(@Param("vendorId")Long vendorId,@Param("mobile")Long mobile,@Param("isMobileVerified")Boolean isMobileVerified,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	
	@Transactional
	@Modifying
	@Query(value="update vendordetails set email=:email,isemailverified=:isEmailVerified,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where vendorid=:vendorId",nativeQuery=true)
	Integer updateIsEmailVerified(@Param("vendorId")Long vendorId,@Param("email")String email,@Param("isEmailVerified")Boolean isEmailVerified,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	

}
