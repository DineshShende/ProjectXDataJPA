package com.projectx.data.repository.completeregister;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.DriverDetails;

@Repository
public interface DriverDetailsRepository extends
		CrudRepository<DriverDetails, Long> {

	
	@Transactional
	@Modifying
	@Query(value="update driverdetails set mobile=:mobile,ismobileverified=:isMobileVerified,"
			+ "UPDATETIME=:updateTime,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where driverid=:driverId",nativeQuery=true)
	Integer updateMobileAndMobileVerificationStaus(@Param("driverId")Long driverId,@Param("mobile")Long mobile,@Param("isMobileVerified")Boolean isMobileVerified,
			@Param("updateTime") Date updateTime,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
	@Query(value="select * from driverdetails where venderid=:vendorId",nativeQuery=true)
	List<DriverDetails> getDriverListByVendorId(@Param("vendorId") Long vendorId);
	
	DriverDetails findByLicenceNumber(String licenceNumber);
	
}
