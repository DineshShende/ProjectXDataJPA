package com.projectx.data.repository.request;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.projectx.data.domain.request.FreightRequestByVendor;

public interface FreightRequestByVendorRepository extends CrudRepository<FreightRequestByVendor, Long>,FreightRequestByVendorCustomRepository {

	List<FreightRequestByVendor> findByVendorId(Long vendorId);
	
	@Transactional
	@Modifying
	@Query(value="update freightrequestbyvendor set status=:newStatus,reservedby=:freightRequestByCustomerId,"
			+ " updatetime=:updateTime where requestid=:freightRequestByVendorId and status=:oldStatus",nativeQuery=true)
	Integer updateVerificationStatus(@Param("freightRequestByVendorId")Long freightRequestByVendorId,
			@Param("oldStatus")String oldStatus,@Param("newStatus")String newStatus,@Param("freightRequestByCustomerId")Long freightRequestByCustomerId,
			@Param("updateTime")Date updateTime);
	
}
