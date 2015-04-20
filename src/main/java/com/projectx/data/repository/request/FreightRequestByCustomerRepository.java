package com.projectx.data.repository.request;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.request.FreightRequestByCustomer;

@Repository
public interface FreightRequestByCustomerRepository extends
		CrudRepository<FreightRequestByCustomer, Long>,FreightRequestByCustomerCustomRepository {
	
	List<FreightRequestByCustomer> findByCustomerId(Long customerId);
	
	@Transactional
	@Modifying
	@Query(value="update freightrequestbycustomer set allocationstatus=:newStatus,allocatedfor=:freightRequestByVendorId,"
			+ "updatedby=:updatedBy,updatedbyid=:updatedById,updatetime=:updateTime where requestid=:freightRequestByCustomerId and allocationstatus=:oldStatus",nativeQuery=true)
	Integer updateVerificationStatus(@Param("freightRequestByCustomerId")Long freightRequestByCustomerId,
			@Param("oldStatus")String oldStatus,@Param("newStatus")String newStatus,@Param("freightRequestByVendorId")Long freightRequestByVendorId,
			@Param("updateTime")Date updateTime,@Param("updatedBy")Integer updatedBy,@Param("updatedById")Long updatedById);

}
